package com.ruoyi.jianguan.business.onlineForms.service;

import com.ruoyi.common.core.domain.PageQuery;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.jianguan.business.onlineForms.domain.bo.PubProduceDocumentBo;
import com.ruoyi.jianguan.business.onlineForms.domain.vo.PubProduceDocumentVo;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

/**
 * 工序附件信息Service接口
 *
 * @author mickey
 * @date 2024-01-02
 */
public interface IOnlineFormsService {


    Object saveFillDataTemplate(Long id, String luckySheetJson) throws IOException;
}
