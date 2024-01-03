package com.ruoyi.jianguan.business.onlineForms.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.common.core.domain.PageQuery;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.file.FileUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.utils.poi.LuckySheetUtil;
import com.ruoyi.jianguan.business.onlineForms.domain.PubProduceDocument;
import com.ruoyi.jianguan.business.onlineForms.domain.bo.PubProduceDocumentBo;
import com.ruoyi.jianguan.business.onlineForms.domain.vo.PubProduceDocumentVo;
import com.ruoyi.jianguan.business.onlineForms.mapper.PubProduceDocumentMapper;
import com.ruoyi.jianguan.business.onlineForms.service.IOnlineFormsService;
import com.ruoyi.jianguan.business.onlineForms.service.IPubProduceDocumentService;
import com.ruoyi.jianguan.manage.produce.domain.entity.PubProduce;
import com.ruoyi.jianguan.manage.produce.mapper.PubProduceMapper;
import com.ruoyi.system.domain.SysOss;
import com.ruoyi.system.service.ISysOssService;
import liquibase.pro.packaged.P;
import lombok.RequiredArgsConstructor;
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
}
