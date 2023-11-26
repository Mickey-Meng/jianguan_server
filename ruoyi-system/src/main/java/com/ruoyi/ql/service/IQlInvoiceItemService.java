package com.ruoyi.ql.service;

import com.ruoyi.common.core.domain.PageQuery;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.ql.domain.bo.QlInvoiceItemBo;
import com.ruoyi.ql.domain.vo.QlInvoiceItemVo;

import java.util.Collection;
import java.util.List;

/**
 * 【请填写功能名称】Service接口
 *
 * @author mickey
 * @date 2023-11-07
 */
public interface IQlInvoiceItemService {

    /**
     * 查询【请填写功能名称】
     */
    QlInvoiceItemVo queryById(Long id);

    /**
     * 查询【请填写功能名称】列表
     */
    TableDataInfo<QlInvoiceItemVo> queryPageList(QlInvoiceItemBo bo, PageQuery pageQuery);

    /**
     * 查询【请填写功能名称】列表
     */
    List<QlInvoiceItemVo> queryList(QlInvoiceItemBo bo);

    /**
     * 新增【请填写功能名称】
     */
    Boolean insertByBo(QlInvoiceItemBo bo);

    /**
     * 修改【请填写功能名称】
     */
    Boolean updateByBo(QlInvoiceItemBo bo);

    /**
     * 校验并批量删除【请填写功能名称】信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

    /**
     * 来源Id删除
     * @param sourceId
     * @return
     */
    Boolean deleteBySourceId(Long sourceId);

}
