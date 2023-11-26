package com.ruoyi.jianguan.manage.produce.service.impl;

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
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ruoyi.common.utils.file.FileUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.utils.poi.LuckySheetUtil;
import com.ruoyi.jianguan.manage.produce.domain.bo.PubProduceBo;
import com.ruoyi.jianguan.manage.produce.domain.entity.PubProduce;
import com.ruoyi.jianguan.manage.produce.domain.vo.PubProduceVo;
import com.ruoyi.jianguan.manage.produce.mapper.PubProduceMapper;
import com.ruoyi.jianguan.manage.produce.service.IPubProduceService;
import com.ruoyi.system.domain.SysOss;
import com.ruoyi.system.service.ISysOssService;
import liquibase.pro.packaged.S;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 工序信息Service业务层处理
 *
 * @author ruoyi
 * @date 2023-06-03
 */
@RequiredArgsConstructor
@Service
public class PubProduceServiceImpl implements IPubProduceService {

    @Value("${temporary.template}")
    private String tempTemplateFilePath;

    private final PubProduceMapper baseMapper;

    private final ISysOssService sysOssService;

    /**
     * 查询工序信息
     */
    @Override
    public PubProduceVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询工序信息列表
     */
    @Override
    public TableDataInfo<PubProduceVo> queryPageList(PubProduceBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<PubProduce> lqw = buildQueryWrapper(bo);
        Page<PubProduceVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询工序信息列表
     */
    @Override
    public List<PubProduceVo> queryList(PubProduceBo bo) {
        LambdaQueryWrapper<PubProduce> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<PubProduce> buildQueryWrapper(PubProduceBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<PubProduce> lqw = Wrappers.lambdaQuery();
        lqw.eq(!Objects.isNull(bo.getComponentTypeId()), PubProduce::getComponentTypeId, bo.getComponentTypeId());
        lqw.eq(StringUtils.isNotBlank(bo.getComponentTypeCode()), PubProduce::getComponentTypeCode, bo.getComponentTypeCode());
        lqw.like(StringUtils.isNotBlank(bo.getName()), PubProduce::getName, bo.getName());
        lqw.eq(bo.getOrderNum() != null, PubProduce::getOrderNum, bo.getOrderNum());
        lqw.eq(bo.getIsEffect() != null, PubProduce::getIsEffect, bo.getIsEffect());
        return lqw;
    }

    /**
     * 新增工序信息
     */
    @Override
    public Boolean insertByBo(PubProduceBo bo) {
        PubProduce add = BeanUtil.toBean(bo, PubProduce.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改工序信息
     */
    @Override
    public Boolean updateByBo(PubProduceBo bo) {
        PubProduce update = BeanUtil.toBean(bo, PubProduce.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(PubProduce entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除工序信息
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }

    /**
     * 校验主键是否唯一工序信息信息
     */
    @Override
    public Boolean checkUniqueByPrimaryKey(Long id) {
        int count = baseMapper.selectCountByPrimaryKey(id);
        return count <= 1;
    }

    @Override
    public List<PubProduce> getProduceListByTypeId(Long typeId) {
        return baseMapper.selectProduceListByTypeId(typeId);
    }

    @Override
    public Boolean doImportProduces(Long[] ids, PubProduceBo bo) {
        // 1、先重置构建类型原关联数据
        this.restOldRelatedType(bo);
        // 2、关联当前选择的工序
        List<PubProduce> pubProduceList = baseMapper.selectVoBatchIds(Arrays.asList(ids), PubProduce.class);
        pubProduceList.forEach(pubProduce -> {
            pubProduce.setComponentTypeId(bo.getComponentTypeId());
            pubProduce.setComponentTypeCode(bo.getComponentTypeCode());
        });
        return baseMapper.updateBatchById(pubProduceList);
    }


    @Override
    public Map<String, String> getFillDataTemplate(Long id , String originalTemplateUrl) {
        Map<String, Object> fillDataMap = Maps.newHashMap();
        fillDataMap.put("supervisorCompany", "中国软件");
        fillDataMap.put("unitProject", "路基工程");
        fillDataMap.put("itemProject", "混凝土排水管安装(砌筑)");
        fillDataMap.put("constructionData", DateUtils.getNowDate());
        fillDataMap.put("sectionProject", "排水工程");
        fillDataMap.put("stationNum", "混凝土排水管安装");
        fillDataMap.put("recordDate", DateUtils.getNowDate());
        fillDataMap.put("selfCheckRemarks", "自我评价:优秀");
        fillDataMap.put("supervisorReview", "监理自评:优秀+");
        // 原始模板
        String templateOriginalName = "混凝土排水管安装浙路(JS)101施工放样现场记录(监抽)";
        String fillDataTemplateName = ExcelUtil.encodingFilename(templateOriginalName);
        String fillDataTemplatePath = this.tempTemplateFilePath + System.getProperty("file.separator") + fillDataTemplateName;
        try (ExcelWriter excelWriter = EasyExcel
                .write(fillDataTemplatePath)
                .withTemplate(HttpUtil.downloadFileFromUrl(originalTemplateUrl, this.tempTemplateFilePath + System.getProperty("file.separator") + templateOriginalName + ".xlsx"))
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

    /**
     * 保存填写的模板文件
     * @param id
     * @param luckySheetJson
     * @return
     * @throws IOException
     */
    @Override
    public Optional<List<String>> saveFillDataTemplate(Long id, String luckySheetJson) throws IOException {
        JSONObject jsonObject = (JSONObject) JSON.parse(luckySheetJson);
        JSONArray luckySheetJsonArray = jsonObject.getJSONArray("data");
        List<JSONObject> jsonObjectList = luckySheetJsonArray.stream()
                .map(luckySheetJsonObject -> (JSONObject) luckySheetJsonObject).collect(Collectors.toList());
        String fillDataTemplatePath = this.tempTemplateFilePath + System.getProperty("file.separator") + ExcelUtil.encodingFilename(jsonObject.get("title").toString());
        OutputStream outputStream = null;
        SysOss sysOss = null;
        try {
            String sheetName = "第1页";
            int commentsRowNumber = 28;
            // 1、保存填写后的模板数据至磁盘
            outputStream = new FileOutputStream(fillDataTemplatePath);
            LuckySheetUtil.createWorkbook(outputStream, true, jsonObjectList);
            // 2、磁盘文件获取审批意见内容
            Map<String, List<Map<Integer,List<String>>>> workbookContents = LuckySheetUtil.getWorkbookContents(fillDataTemplatePath);
            Optional<List<String>> approvalComments = workbookContents.get(sheetName).stream().filter(rowMap -> rowMap.containsKey(commentsRowNumber)).map(rowMap -> rowMap.get(commentsRowNumber)).findFirst();
            if (approvalComments.isPresent()) {
                System.out.println(approvalComments.get().get(0)); // 自检说明:自我评价 优秀
                System.out.println(approvalComments.get().get(1)); // 监理检评:监理自评 优秀+
            }
            // 3、磁盘文件上传至文件服务器
            sysOssService.upload(FileUtils.getMultipartFile(FileUtils.file(fillDataTemplatePath)));
            return approvalComments;
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
    /**
     * 先重置构建类型原关联数据
     * @param bo
     */
    private void restOldRelatedType(PubProduceBo bo) {
        List<PubProduce> oldPubProduceList = baseMapper.selectList(this.buildQueryWrapper(bo));
        oldPubProduceList.forEach(pubProduce -> {
            pubProduce.setComponentTypeId(Long.valueOf(0));
            pubProduce.setComponentTypeCode("");
        });
        baseMapper.updateBatchById(oldPubProduceList);
    }
}
