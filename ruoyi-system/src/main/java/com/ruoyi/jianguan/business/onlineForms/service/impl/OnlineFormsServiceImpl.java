package com.ruoyi.jianguan.business.onlineForms.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.http.HttpUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.fill.FillConfig;
import com.alibaba.excel.write.style.column.LongestMatchColumnWidthStyleStrategy;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.ruoyi.common.core.domain.PageQuery;
import com.ruoyi.common.core.domain.entity.Conponent;
import com.ruoyi.common.core.domain.entity.Produceandrecode;
import com.ruoyi.common.core.domain.entity.ZjConponentProducetime;
import com.ruoyi.common.core.domain.object.ResponseBase;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BimFlowKey;
import com.ruoyi.common.helper.LoginHelper;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.file.FileUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.utils.poi.LuckySheetUtil;
import com.ruoyi.flowable.domain.dto.FlowTaskCommentDto;
import com.ruoyi.flowable.service.FlowStaticPageService;
import com.ruoyi.jianguan.business.onlineForms.domain.PubCheckReport;
import com.ruoyi.jianguan.business.onlineForms.domain.PubProduceDocument;
import com.ruoyi.jianguan.business.onlineForms.domain.TemplateCode;
import com.ruoyi.jianguan.business.onlineForms.domain.bo.PubProduceDocumentBo;
import com.ruoyi.jianguan.business.onlineForms.domain.vo.PubProduceDocumentVo;
import com.ruoyi.jianguan.business.onlineForms.mapper.PubCheckReportMapper;
import com.ruoyi.jianguan.business.onlineForms.mapper.PubProduceDocumentMapper;
import com.ruoyi.jianguan.business.onlineForms.service.IOnlineFormsService;
import com.ruoyi.jianguan.business.onlineForms.service.IPubCheckReportService;
import com.ruoyi.jianguan.business.onlineForms.service.IPubProduceDocumentService;
import com.ruoyi.jianguan.common.dao.ConponentDAO;
import com.ruoyi.jianguan.common.dao.ProduceandrecodeDAO;
import com.ruoyi.jianguan.common.dao.RecodeDAO;
import com.ruoyi.jianguan.common.dao.ZjConponentProducetimeDAO;
import com.ruoyi.jianguan.common.domain.dto.RecodeUploadData;
import com.ruoyi.jianguan.common.domain.entity.Recode;
import com.ruoyi.jianguan.manage.produce.domain.entity.PubProduce;
import com.ruoyi.jianguan.manage.produce.mapper.PubProduceMapper;
import com.ruoyi.system.domain.SysOss;
import com.ruoyi.system.service.ISysOssService;
import liquibase.pro.packaged.P;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 工序附件信息Service业务层处理
 *
 * @author mickey
 * @date 2024-01-02
 */
@RequiredArgsConstructor
@Service
public class OnlineFormsServiceImpl implements IOnlineFormsService {

    @Value("${temporary.template}")
    private String tempTemplateFilePath;

    private String defaultSheet = "Sheet1";

    private final ISysOssService sysOssService;

    private final PubProduceDocumentMapper pubProduceDocumentMapper;

    @Autowired
    private RecodeDAO recodeDAO;

    @Autowired
    private ConponentDAO conponentDAO;

    @Autowired
    private ZjConponentProducetimeDAO zjConponentProducetimeDAO;

    @Autowired
    @Qualifier("zjProduceandrecodeDAO")
    private ProduceandrecodeDAO produceandrecodeDAO;

    @Autowired
    private FlowStaticPageService flowStaticPageService;

    private final PubCheckReportMapper pubCheckReportMapper;

    @Override
    public Object saveFillDataTemplate(Long id, String luckySheetJson) throws IOException {
        JSONObject jsonObject = (JSONObject) JSON.parse(luckySheetJson);
        JSONArray luckySheetJsonArray = jsonObject.getJSONArray("data");
        List<JSONObject> jsonObjectList = luckySheetJsonArray.stream()
                .map(luckySheetJsonObject -> (JSONObject) luckySheetJsonObject).collect(Collectors.toList());
        String fillDataTemplatePath = this.tempTemplateFilePath + System.getProperty("file.separator") + ExcelUtil.encodingFilename(jsonObject.get("title").toString());
        OutputStream outputStream = null;
        SysOss sysOss = null;
        try {
            int commentsRowNumber = 28;
            // 1、保存填写后的模板数据至磁盘
            outputStream = new FileOutputStream(fillDataTemplatePath);
            LuckySheetUtil.createWorkbook(outputStream, true, jsonObjectList);
            // 2、磁盘文件获取审批意见内容
            Map<String, List<Map<Integer,List<String>>>> workbookContents = LuckySheetUtil.getWorkbookContents(fillDataTemplatePath);
            Optional<List<String>> approvalComments = workbookContents.get(defaultSheet).stream().filter(rowMap -> rowMap.containsKey(commentsRowNumber)).map(rowMap -> rowMap.get(commentsRowNumber)).findFirst();
            if (approvalComments.isPresent()) {
                // System.out.println(approvalComments.get().get(0)); // 自检说明:自我评价 优秀
                // System.out.println(approvalComments.get().get(1)); // 监理检评:监理自评 优秀+
            }
            // 3、磁盘文件上传至文件服务器
            sysOss = sysOssService.upload(FileUtils.getMultipartFile(FileUtils.file(fillDataTemplatePath)));
            PubProduceDocument pubProduceDocument = pubProduceDocumentMapper.selectById(id);
            pubProduceDocument.setDocumentStatus(1L);
            pubProduceDocument.setRemark(pubProduceDocument.getDocumentUrl());
            pubProduceDocument.setDocumentUrl(sysOss.getUrl());
            pubProduceDocumentMapper.updateById(pubProduceDocument);
            if (Objects.equals("浙路(ZP)106(802)钢筋安装分项工程质量检验评定表.xlsx",pubProduceDocument.getDocumentName())) {
                Map<String,List<String>> rowsContentsMapData = this.getSingleExcelContentsByRows(pubProduceDocument, Arrays.asList(10, 12, 17, 18), "");
                System.err.println("=========================== 浙路(ZP)106(802)钢筋安装分项工程质量检验评定表 ==============================");
                rowsContentsMapData.forEach((key, value) -> {
                    System.err.println("key :" + key + " | value : " + Arrays.toString(value.toArray()));
                });
                /**
                 暂时在打卡填报表格时更新，此处预留
                // 1、查询评定、报验关联数据
                PubCheckReport pubCheckReport = pubCheckReportMapper.selectVoOne(new LambdaQueryWrapper<PubCheckReport>()
                        .eq(!Objects.isNull(pubProduceDocument.getComponentId()), PubCheckReport::getCheckComponentId, pubProduceDocument.getComponentId()), PubCheckReport.class);
                // 更新查询评定、报验关联数据
                pubCheckReport.setScxmStatus((Objects.equals("合格", fillDataMap.get("row10IsPass")) && Objects.equals("合格", fillDataMap.get("row12IsPass"))) ? 1L : 0L);
                pubCheckReport.setZlwzxStatus(Objects.equals("合格", fillDataMap.get("infoIsCompleted")) ? 1L : 0L);
                pubCheckReport.setWgzlStatus(Objects.equals("合格", fillDataMap.get("qualityCheck")) ? 1L : 0L);
                pubCheckReport.setCheckResult(checkResult ? 1L : 0L);
                pubCheckReportMapper.updateById(pubCheckReport);
                 */
            }
            return pubProduceDocument;
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        } finally {
            if (outputStream != null) {
                outputStream.close();
                if (!Objects.isNull(sysOss)) {
                    // 上传成功后删除生成的磁盘文件
                    FileUtils.del(fillDataTemplatePath);
                }
            }
        }
    }

    @Override
    public ResponseBase submitReport(RecodeUploadData recodeData) {
        /******************************************************************************/
        if (recodeData.getSubmitType().equals("report")) {

        }
        //默认为一标段
        if (recodeData.getProjectId() == null || recodeData.getProjectId().equals("")){
            //默认为一标段
            recodeData.setProjectId(3);
        }
        //produceService.uploadRecode(recodeData);

        //往recode表查一个数据
        Recode recode = new Recode();
        recode.setCreatetime(new Date());
        //新加四个url
        recode.setRemark(recodeData.getRemark());
        recode.setAccrecodeurl(recodeData.getAccrecodeurl());
        recode.setStandbyrecode(recodeData.getStandbyrecode());
        recode.setTesturl(recodeData.getTesturl());

        recodeDAO.insert(recode);

        ZjConponentProducetime zjConponentProducetime =
                zjConponentProducetimeDAO.getZjByConponentId(recodeData.getConpoentid());
        Produceandrecode produceandrecode = new Produceandrecode();
        //审核通过后才设置完成时间
//        produceandrecode.setUpdatetime(new Date());
        produceandrecode.setConponentcode(zjConponentProducetime.getConponentcode());

        produceandrecode.setUpdatetime(new Date());
        produceandrecode.setCheckusername(recodeData.getCheckusername());
        produceandrecode.setCheckuser(recodeData.getCheckid());
        produceandrecode.setUpdateusername(recodeData.getUpdateusername());
        //这里前端有可能没有传上传用户id,修改成从token中获取
        produceandrecode.setUpdateuser(LoginHelper.getUserId().intValue());
        produceandrecode.setConponentname(recodeData.getConponentname());
        produceandrecode.setConponenttype(recodeData.getConponenttype());
        produceandrecode.setConponentid(recodeData.getConpoentid());
        produceandrecode.setProducename(recodeData.getProduceidname());
        produceandrecode.setProjectcode(recodeData.getProjectcode());
        produceandrecode.setProduceid(recodeData.getProduceid());
        produceandrecode.setRecodeid(recode.getId());
        //新增工序状态
        produceandrecode.setStatus(0);
        produceandrecode.setProjectId(recodeData.getProjectId());
        //在插入之前先通过编码查询该条数据是否存在，如存在则修改
        Integer count = produceandrecodeDAO.getCountById(produceandrecode.getConponentid(), produceandrecode.getProduceid());
        Integer dbCount = 0;
        if (count > 0){
            Produceandrecode produceandrecode1 =  produceandrecodeDAO.getByIdAndProduceId(recodeData.getConpoentid(),recodeData.getProduceid());
            produceandrecode.setId(produceandrecode1.getId());
            dbCount = produceandrecodeDAO.updateByCode(produceandrecode);
        } else {
            //插入数据工序填报
            dbCount = produceandrecodeDAO.insert(produceandrecode);
        }
        /******************************************************************************/
        // 查询记录表新增数据的ID
        Integer currentMaxId = recodeDAO.selectPrimaryKey();
        if (dbCount > 0) {
            String processDefinitionKey = BimFlowKey.produceOnlineReport.getName();
            String businessKey = produceandrecode.getId() + "_" + currentMaxId + "_" + processDefinitionKey;
            //设置流程的审批人
            Map<String, Object> auditUser = recodeData.getAuditUser();
            if (auditUser.isEmpty()) {
                throw new RuntimeException("流程的审批人不能为空！");
            }
            //发起流程
            try {
                FlowTaskCommentDto flowTaskComment = new FlowTaskCommentDto();
                flowTaskComment.setApprovalType("save");
                flowTaskComment.setComment("在线表单-工序报验表单创建");
                JSONObject taskVariableData = new JSONObject(auditUser);
                flowStaticPageService.startAndTakeUserTask(processDefinitionKey, flowTaskComment, taskVariableData, null, null, recodeData.getCopyData(), businessKey);

                // 更新报验信息
                PubCheckReport pubCheckReport = pubCheckReportMapper.selectVoOne(new LambdaQueryWrapper<PubCheckReport>()
                        .eq(!Objects.isNull(produceandrecode.getProduceid()), PubCheckReport::getReportProduceId, produceandrecode.getProduceid()), PubCheckReport.class);
                pubCheckReport.setReportUser(LoginHelper.getNickName());
                pubCheckReport.setReportTime(new Date());
                pubCheckReportMapper.updateById(pubCheckReport);
            } catch (Exception e) {
                throw new RuntimeException("流程启动失败！e=" + e.getMessage());
            }
        }
        return ResponseBase.success("流程启动成功");
    }

    public Produceandrecode updateFlowStatusById(String id, Integer status, String type) {
        Produceandrecode produceandrecode = produceandrecodeDAO.selectByPrimaryKey(Integer.valueOf(id));
        if (type.equals("check")) {
            produceandrecode.setCheckresult(status);
        }
        produceandrecode.setStatus(status);
        produceandrecode.setCheckuser(LoginHelper.getUserId().intValue());
        produceandrecode.setStime(new Date());
        produceandrecodeDAO.updateByPrimaryKey(produceandrecode);

        if (type.equals("report") && status == 1) {
            // 更新报验信息
            PubCheckReport pubCheckReport = pubCheckReportMapper.selectVoOne(new LambdaQueryWrapper<PubCheckReport>()
                    .eq(!Objects.isNull(produceandrecode.getProduceid()), PubCheckReport::getReportProduceId, produceandrecode.getProduceid()), PubCheckReport.class);
            pubCheckReport.setCheckUser(LoginHelper.getNickName());
            pubCheckReportMapper.updateById(pubCheckReport);
        }
        return produceandrecode;
    }

    @Override
    public ResponseBase getProduceReportInfoById(Integer id, Integer documentType) {
        Produceandrecode produceandrecode = produceandrecodeDAO.selectByPrimaryKey(id);
        Conponent conponent = conponentDAO.selectByPrimaryKey(produceandrecode.getConponentid());
        Map resultMap = Maps.newHashMap();
        if (documentType == 1) {
            Recode recode = recodeDAO.selectByPrimaryKey(produceandrecode.getRecodeid());
            resultMap.put("attachment", recode.getRemark());
        }

        Map<String, Object> conditionMap = Maps.newHashMap();
        conditionMap.put("component_id", conponent.getId());
        conditionMap.put("produce_id", produceandrecode.getProduceid());
        conditionMap.put("document_type", documentType);
        List<PubProduceDocumentVo> templateListData = pubProduceDocumentMapper.selectVoByMap(conditionMap);

        resultMap.put("produceandrecode", produceandrecode);
        resultMap.put("conponent", conponent);
        resultMap.put("templateListData", templateListData);
        return ResponseBase.success(resultMap);
    }

    @Override
    public ResponseBase submitCheck(RecodeUploadData recodeData) {
        Produceandrecode  dbProduceandrecode = produceandrecodeDAO.getByIdAndProduceId(recodeData.getConpoentid(), recodeData.getProduceid());
        /******************************************************************************/

        dbProduceandrecode.setCheckresult(3);
        int count = produceandrecodeDAO.updateByPrimaryKey(dbProduceandrecode);
        // 查询记录表新增数据的ID
        Integer currentMaxId = recodeDAO.selectPrimaryKey();
        if (!Objects.isNull(dbProduceandrecode) && count > 0){
            String processDefinitionKey = BimFlowKey.produceOnlineCheck.getName();
            String businessKey = dbProduceandrecode.getId() + "_" + currentMaxId + "_" + processDefinitionKey;
            //设置流程的审批人
            Map<String, Object> auditUser = recodeData.getAuditUser();
            if (auditUser.isEmpty()) {
                throw new RuntimeException("流程的审批人不能为空！");
            }
            //发起流程
            try {
                FlowTaskCommentDto flowTaskComment = new FlowTaskCommentDto();
                flowTaskComment.setApprovalType("save");
                flowTaskComment.setComment("在线表单-质量评定表单创建");
                JSONObject taskVariableData = new JSONObject(auditUser);
                flowStaticPageService.startAndTakeUserTask(processDefinitionKey, flowTaskComment, taskVariableData, null, null, recodeData.getCopyData(), businessKey);
            } catch (Exception e) {
                throw new RuntimeException("流程启动失败！e=" + e.getMessage());
            }
        }
        return ResponseBase.success("流程启动成功");
    }

    @Override
    public Map<String, String> getFillDataTemplate(Long id) throws IOException {
        PubProduceDocumentVo pubProduceDocument = pubProduceDocumentMapper.selectVoById(id);
        if (Objects.isNull(pubProduceDocument)) {
            return null;
        }
        Map<String, Object> fillDataMap = this.getFillDataMap(pubProduceDocument);
        // 原始模板
        String templateOriginalName = pubProduceDocument.getDocumentName();
        String fillDataTemplateName = ExcelUtil.encodingFilename(templateOriginalName);
        String fillDataTemplatePath = this.tempTemplateFilePath + System.getProperty("file.separator") + fillDataTemplateName;
        try (ExcelWriter excelWriter = EasyExcel
                .write(fillDataTemplatePath)
                .withTemplate(HttpUtil.downloadFileFromUrl(pubProduceDocument.getDocumentUrl(), this.tempTemplateFilePath + System.getProperty("file.separator") + templateOriginalName + ".xlsx"))
                .registerWriteHandler(new LongestMatchColumnWidthStyleStrategy()).build()) {
            WriteSheet writeSheet = EasyExcel.writerSheet().build();
            // 这里注意 入参用了forceNewRow 代表在写入list的时候不管list下面有没有空行 都会创建一行，然后下面的数据往后移动。默认 是false，会直接使用下一行，如果没有则创建。
            // forceNewRow 如果设置了true,有个缺点 就是他会把所有的数据都放到内存了，所以慎用
            // 简单的说 如果你的模板有list,且list不是最后一行，下面还有数据需要填充 就必须设置 forceNewRow=true 但是这个就会把所有数据放到内存 会很耗内存
            // 如果数据量大 list不是最后一行 参照下一个
            FillConfig fillConfig = FillConfig.builder().forceNewRow(Boolean.FALSE).build();
            excelWriter.fill(Lists.newArrayList(fillDataMap), fillConfig, writeSheet);

            Map<String, String> templateDataMap = Maps.newHashMap();
            templateDataMap.put("templateName", fillDataTemplateName);
            templateDataMap.put("templatePath", fillDataTemplatePath);
            return templateDataMap;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ResponseBase getOnlineReportTemplate(String componentCode, String projectId) {
        Conponent component = conponentDAO.getConponentByCode(componentCode);
        return ResponseBase.success(component);
    }

    @NotNull
    private Map<String, Object> getFillDataMap(PubProduceDocumentVo produceDocument) throws IOException {
        int componentId = produceDocument.getComponentId().intValue();
        Conponent component = conponentDAO.selectByPrimaryKey(componentId);
        Map<String, Object> fillDataMap = Maps.newHashMap();
        // 通用填充数据
        fillDataMap.put("ShiGongDanWei", null); // 施工单位
        fillDataMap.put("JianLiDanWei", null); // 监理单位
        fillDataMap.put("HeTongHao", null); // 合同号
        fillDataMap.put("BianHao", null); // 编  号
        fillDataMap.put("DanDeiGongCheng", component.getW3()); // 单位工程
        fillDataMap.put("FenBuGongCheng", component.getW4()); // 分部工程
        fillDataMap.put("FenXiangGongCheng", component.getW6()); // 分项工程
        fillDataMap.put("GouJianMingCheng", component.getName()); // 构件名称
        fillDataMap.put("ZhuangHaoBuWei", component.getW7()); // 桩号、部位
        fillDataMap.put("ShiGongRiQi", DateUtils.getNowDate()); // 施工日期
        fillDataMap.put("JianCeRiQi", DateUtils.getNowDate()); // 检测记录日期
        fillDataMap.put("XianChangJianLi", null); // 现场监理
        fillDataMap.put("XianChangJianLiRiQi", null);
        fillDataMap.put("ShiGongFuZeRen", null); // 施工负责人
        fillDataMap.put("ShiGongFuZeRenRiQi", DateUtils.getNowDate());
        fillDataMap.put("ZhiJianYuan", null); // 质检员
        fillDataMap.put("ZhiJianRiQi", DateUtils.getNowDate());
        fillDataMap.put("BanZuZhang", null); // 班组长
        fillDataMap.put("BanZuZhangRiQi", DateUtils.getNowDate());
        // 根据构件类型封装自定义填充数据
        switch (component.getConponenttype()) {
            case "HTMPD_GJ":
                // 1、查询评定、报验关联数据
                PubCheckReport pubCheckReport = pubCheckReportMapper.selectVoOne(new LambdaQueryWrapper<PubCheckReport>()
                        .eq(!Objects.isNull(componentId), PubCheckReport::getCheckComponentId, componentId), PubCheckReport.class);
                // 2、查询关联工序的文档信息(多组)
                Map documentQueryMap = Maps.newHashMap();
                documentQueryMap.put("produce_id",pubCheckReport.getReportProduceId());
                List<PubProduceDocument> pubProduceDocumentList = pubProduceDocumentMapper.selectByMap(documentQueryMap);
                // 3、获取工序数据源的模板集合
                //String fillSourceTemplateName = "浙路(JS)107钢筋安装现场检测记录表.xlsx";
                List<PubProduceDocument> filledSourceTemplateList = pubProduceDocumentList.stream()
                                                                                          .filter(pubProduceDocument -> Objects.equals(TemplateCode.工序_JS_钢筋安装现场检测记录表.getCode(), pubProduceDocument.getDocumentCode()))
                                                                                          .sorted(Comparator.comparingLong(PubProduceDocument::getId))
                                                                                          .collect(Collectors.toList());

                if (Objects.equals(TemplateCode.评定_ZP_106_1钢筋安装分项工程质量检验评定表附表.getCode(),produceDocument.getDocumentCode())) {
                    // 钢筋评定:13-11,17-15
                    Map<String,List<String>> rowsContentsMapData = this.getMultipleExcelContentsByRows(filledSourceTemplateList, Arrays.asList(13, 17), "偏差值,");
                    System.err.println("==============================================================");
                    rowsContentsMapData.forEach((key, value) ->  System.err.println("key :" + key + " | value : " + Arrays.toString(value.toArray())) );
                    System.err.println("==============================================================");
                    for (int index = 0; index < 30 ; index++) {
                        // System.err.println("key : row11_" + (i + 1) + " | value : " + rowsContentsMapData.get("row13Data").get(i));
                        // System.err.println("key : row15_" + (i + 1) + " | value : " + rowsContentsMapData.get("row17Data").get(i));
                        if (index < rowsContentsMapData.get("row13Data").size()) {
                            if (index < 15) {
                                fillDataMap.put("row11_" + (index + 1), Math.ceil(new Double(rowsContentsMapData.get("row13Data").get(index))));
                            } else {
                                fillDataMap.put("row12_" + (index - 14), Math.ceil(new Double(rowsContentsMapData.get("row13Data").get(index))));
                            }
                        }
                        if (index < rowsContentsMapData.get("row17Data").size()) {
                            if (index < 15) {
                                fillDataMap.put("row15_" + (index + 1), Math.ceil(new Double(rowsContentsMapData.get("row17Data").get(index))));
                            } else {
                                fillDataMap.put("row16_" + (index - 14), Math.ceil(new Double(rowsContentsMapData.get("row17Data").get(index))));
                            }
                        }
                    }
                } else if (Objects.equals(TemplateCode.评定_ZP_106_802钢筋安装分项工程质量检验评定表.getCode(),produceDocument.getDocumentCode())) {
                    Map<String,List<String>> rowsContentsMapData = this.getMultipleExcelContentsByRows(filledSourceTemplateList, Arrays.asList(12, 16), ",");
                    Map<String, BigDecimal> averagePassRateMap = Maps.newHashMap();
                    rowsContentsMapData.forEach((key, value) -> {
                        // 多个合格率平均值
                        double averagePassRate = value.stream().map(num -> {
                            return num.contains("%") ? new BigDecimal(num.replace("%", "")).divide(new BigDecimal(100)) : new BigDecimal(num);
                        }).mapToDouble(BigDecimal::doubleValue).average().getAsDouble();
                        BigDecimal percentAveragePassRateMap = new BigDecimal(averagePassRate).multiply(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_HALF_UP);
                        averagePassRateMap.put(key, percentAveragePassRateMap);
                        System.err.println("key :" + key + " | value : " + Arrays.toString(value.toArray()) + " | averagePassRate : " + percentAveragePassRateMap);
                    });
                    // 封装填充的合格率
                    fillDataMap.put("row10PassRate", averagePassRateMap.get("row12Data") + "%");
                    fillDataMap.put("row10IsPass", averagePassRateMap.get("row12Data").compareTo(new BigDecimal(80)) < 0 ? "不合格" : "合格");
                    fillDataMap.put("row12PassRate", averagePassRateMap.get("row16Data") + "%");
                    fillDataMap.put("row12IsPass", averagePassRateMap.get("row16Data").compareTo(new BigDecimal(80)) < 0 ? "不合格" : "合格");
                    // 完整性
                    long notFillCount = pubProduceDocumentList.stream().map(PubProduceDocument::getDocumentStatus)
                                                                       .filter(documentStatus -> Objects.equals("0", String.valueOf(documentStatus))).count();
                    fillDataMap.put("infoIsCompleted", notFillCount > 0 ? "资料缺失" : "资料齐全");
                    // 外观质量
                    List<PubProduceDocument> qualitySourceTemplateList = pubProduceDocumentList.stream().filter(qualitySourceTemplate -> Objects.equals(TemplateCode.工序_JS_802钢筋加工及安装工程现场质量检验报告单.getCode(), qualitySourceTemplate.getDocumentCode())).collect(Collectors.toList());
                    Map<String,List<String>> qualityCheckMapDataMap = this.getMultipleExcelContentsByRows(qualitySourceTemplateList, Arrays.asList(26, 27), ",");
                    long notPassQualityCheckCount = qualityCheckMapDataMap.values().stream().filter(qualityCheckListData -> !qualityCheckListData.contains("符合要求")).count();
                    fillDataMap.put("qualityCheck", notPassQualityCheckCount > 0 ? "不合格" : "合格");

                    // 评定结果
                    boolean checkResult = Objects.equals("合格", fillDataMap.get("row10IsPass")) && Objects.equals("合格", fillDataMap.get("row12IsPass")) &&
                                            Objects.equals("合格", fillDataMap.get("qualityCheck")) && Objects.equals("资料齐全", fillDataMap.get("infoIsCompleted"));

                    fillDataMap.put("checkResult", checkResult ? "合格" : "不合格");

                    /**************************************************************************************************/
                    // 更新查询评定、报验关联数据
                    pubCheckReport.setScxmStatus((Objects.equals("合格", fillDataMap.get("row10IsPass")) && Objects.equals("合格", fillDataMap.get("row12IsPass"))) ? 1L : 0L);
                    pubCheckReport.setWgzlStatus(Objects.equals("合格", fillDataMap.get("qualityCheck")) ? 1L : 0L);
                    pubCheckReport.setZlwzxStatus(Objects.equals("资料齐全", fillDataMap.get("infoIsCompleted")) ? 1L : 0L);
                    pubCheckReport.setCheckResult(checkResult ? 1L : 0L);
                    pubCheckReportMapper.updateById(pubCheckReport);
                }
                break;
            case "HTMPD_FXGC":
                // 分项工程评定
                break;
            default:
                System.out.println("评定构件类型不匹配");
        }

        return fillDataMap;
    }

    public Map<String,List<String>> getSingleExcelContentsByRows(PubProduceDocument pubProduceDocument, List<Integer> rowNumbers, String indexOfWord) throws IOException {
        String fillDataTemplatePath = this.tempTemplateFilePath + System.getProperty("file.separator") + ExcelUtil.encodingFilename(pubProduceDocument.getDocumentName());
        Map<String,List<String>> rowsContentsMapData = Maps.newHashMap();
        try {
            // 1、保存填写后的模板数据至磁盘
            HttpUtil.downloadFileFromUrl(pubProduceDocument.getDocumentUrl(), fillDataTemplatePath);
            // 2、磁盘文件获取审批意见内容
            Map<String, List<Map<Integer,List<String>>>> workbookContents = LuckySheetUtil.getWorkbookContents(fillDataTemplatePath);
            // 操作的sheet页
            List<Map<Integer,List<String>>> sheetContentsList = workbookContents.get(defaultSheet);
            rowNumbers.forEach(rowNumber -> {
                String rowData = sheetContentsList.stream().filter(rowMap -> rowMap.containsKey(rowNumber)).map(rowMap -> rowMap.get(rowNumber).stream().collect(Collectors.joining(",")).replaceAll("\\r|\\n", "")).collect(Collectors.joining());
                String rowDataMapKey = "row" + rowNumber + "Data";
                rowsContentsMapData.put(rowDataMapKey, Arrays.asList(rowData.substring(rowData.lastIndexOf(indexOfWord) + indexOfWord.length())));
            });
            return rowsContentsMapData;
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        } finally {
            // 删除生成的磁盘文件
            FileUtils.del(fillDataTemplatePath);
        }
    }

    @Override
    public Map<String, List<String>> getMultipleExcelContentsByRows(List<PubProduceDocument> pubProduceDocumentList, List<Integer> rowNumbers, String indexOfWord) throws IOException {
        Map<String,List<String>> rowsContentsMapData = Maps.newHashMap();
        pubProduceDocumentList.forEach(pubProduceDocument -> {
            String fillDataTemplatePath = this.tempTemplateFilePath + System.getProperty("file.separator") + ExcelUtil.encodingFilename(pubProduceDocument.getDocumentName());
            // 1、保存填写后的模板数据至磁盘
            HttpUtil.downloadFileFromUrl(pubProduceDocument.getDocumentUrl(), fillDataTemplatePath);
            // 2、磁盘文件获取审批意见内容
            Map<String, List<Map<Integer,List<String>>>> workbookContents = null;
            try {
                workbookContents = LuckySheetUtil.getWorkbookContents(fillDataTemplatePath);
                // 操作的sheet页
                List<Map<Integer,List<String>>> sheetContentsList = workbookContents.get(defaultSheet);
                rowNumbers.forEach(rowNumber -> {
                    String rowData = sheetContentsList.stream().filter(rowMap -> rowMap.containsKey(rowNumber)).map(rowMap -> rowMap.get(rowNumber).stream().collect(Collectors.joining(",")).replaceAll("\\r|\\n", "")).collect(Collectors.joining());
                    String rowDataMapKey = "row" + rowNumber + "Data";
                    List<String> currentRowData = Arrays.asList(rowData.substring(rowData.lastIndexOf(indexOfWord) + indexOfWord.length()).split(","));
                    if (rowsContentsMapData.containsKey(rowDataMapKey)) {
                        List<String> rowDataList = rowsContentsMapData.get(rowDataMapKey);
                        List<String> newRowDataList = Lists.newArrayList();
                        newRowDataList.addAll(rowDataList);
                        newRowDataList.addAll(currentRowData);
                        rowsContentsMapData.put(rowDataMapKey, newRowDataList);
                    } else {
                        rowsContentsMapData.put(rowDataMapKey, currentRowData);
                    }
                });
            } catch (IOException e) {
                throw new RuntimeException(e);
            }finally {
                // 删除生成的磁盘文件
                FileUtils.del(fillDataTemplatePath);
            }
        });
        return rowsContentsMapData;
    }

    @Override
    public ResponseBase getReportRecord(Integer componentId) {
        List<Produceandrecode> produceAndRecode = produceandrecodeDAO.getAllByConponentId(componentId);
        return ResponseBase.success(produceAndRecode);
    }
}
