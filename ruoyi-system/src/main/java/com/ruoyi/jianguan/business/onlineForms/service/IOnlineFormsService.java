package com.ruoyi.jianguan.business.onlineForms.service;

import com.ruoyi.common.core.domain.PageQuery;
import com.ruoyi.common.core.domain.entity.Produceandrecode;
import com.ruoyi.common.core.domain.object.ResponseBase;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.jianguan.business.onlineForms.domain.PubProduceDocument;
import com.ruoyi.jianguan.business.onlineForms.domain.bo.PubProduceDocumentBo;
import com.ruoyi.jianguan.business.onlineForms.domain.vo.PubProduceDocumentVo;
import com.ruoyi.jianguan.common.domain.dto.RecodeUploadData;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 工序附件信息Service接口
 *
 * @author mickey
 * @date 2024-01-02
 */
public interface IOnlineFormsService {


    Object saveFillDataTemplate(Long id, String luckySheetJson) throws IOException;

    ResponseBase submitReport(RecodeUploadData recodeData);

    public Produceandrecode updateFlowStatusById(String id, Integer status, String type);

    ResponseBase getProduceReportInfoById(Integer id, Integer documentType);

    ResponseBase submitCheck(RecodeUploadData recodeData);

    Map<String, String> getFillDataTemplate(Long id);

    ResponseBase getOnlineReportTemplate(String componentCode, String projectId);

    public List<List<String>> getWorkbookContents(PubProduceDocument pubProduceDocument, int[] rowNumbers) throws IOException;
}
