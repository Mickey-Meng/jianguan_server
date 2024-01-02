package com.ruoyi.jianguan.business.onlineForms.service;

import com.ruoyi.jianguan.business.onlineForms.domain.PubProduceDocument;
import com.ruoyi.jianguan.business.onlineForms.domain.vo.PubProduceDocumentVo;
import com.ruoyi.jianguan.business.onlineForms.domain.bo.PubProduceDocumentBo;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 工序附件信息Service接口
 *
 * @author mickey
 * @date 2024-01-02
 */
public interface IPubProduceDocumentService {

    /**
     * 查询工序附件信息
     */
    PubProduceDocumentVo queryById(Long id);

    /**
     * 查询工序附件信息列表
     */
    TableDataInfo<PubProduceDocumentVo> queryPageList(PubProduceDocumentBo bo, PageQuery pageQuery);

    /**
     * 查询工序附件信息列表
     */
    List<PubProduceDocumentVo> queryList(PubProduceDocumentBo bo);

    /**
     * 新增工序附件信息
     */
    Boolean insertByBo(PubProduceDocumentBo bo);

    /**
     * 修改工序附件信息
     */
    Boolean updateByBo(PubProduceDocumentBo bo);

    /**
     * 校验并批量删除工序附件信息信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

    /**
     * 校验主键是否唯一工序附件信息信息
     */
    Boolean checkUniqueByPrimaryKey(Long id);

}
