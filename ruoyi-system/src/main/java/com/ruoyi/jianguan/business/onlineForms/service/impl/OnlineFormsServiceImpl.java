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
import com.ruoyi.jianguan.business.onlineForms.domain.PubProduceDocument;
import com.ruoyi.jianguan.business.onlineForms.domain.bo.PubProduceDocumentBo;
import com.ruoyi.jianguan.business.onlineForms.domain.vo.PubProduceDocumentVo;
import com.ruoyi.jianguan.business.onlineForms.mapper.PubProduceDocumentMapper;
import com.ruoyi.jianguan.business.onlineForms.service.IOnlineFormsService;
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
            PubProduceDocumentVo pubProduceDocumentVo = pubProduceDocumentMapper.selectVoById(id);
            PubProduceDocument pubProduceDocument = BeanUtil.toBean(pubProduceDocumentVo, PubProduceDocument.class);
            pubProduceDocument.setDocumentStatus(1L);
            pubProduceDocument.setRemark(pubProduceDocument.getDocumentUrl());
            pubProduceDocument.setDocumentUrl(sysOss.getUrl());
            pubProduceDocumentMapper.updateById(pubProduceDocument);
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
    public Map<String, String> getFillDataTemplate(Long id) {
        PubProduceDocumentVo pubProduceDocument = pubProduceDocumentMapper.selectVoById(id);
        if (Objects.isNull(pubProduceDocument)) {
            return null;
        }
        Map<String, Object> fillDataMap = this.getFillDataMap(pubProduceDocument.getComponentId().intValue());
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

    @NotNull
    private Map<String, Object> getFillDataMap(Integer componentId) {
        Conponent component = conponentDAO.selectByPrimaryKey(componentId);
        Map<String, Object> fillDataMap = Maps.newHashMap();
        // fillDataMap.put("supervisorCompany", "中国软件");
        fillDataMap.put("unitProject", component.getW3()); // 单位工程
        fillDataMap.put("sectionProject", component.getW4()); // 分部工程
        fillDataMap.put("itemProject", component.getW6()); // 分项工程
        fillDataMap.put("stationNum", component.getW7()); // 桩号、部位
        fillDataMap.put("constructionData", DateUtils.getNowDate());
        fillDataMap.put("recordDate", DateUtils.getNowDate());
        return fillDataMap;
    }
}
