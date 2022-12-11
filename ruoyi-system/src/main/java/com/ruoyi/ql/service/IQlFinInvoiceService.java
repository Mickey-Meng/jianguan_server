package com.ruoyi.ql.service;

import com.ruoyi.ql.domain.QlFinInvoice;
import com.ruoyi.ql.domain.vo.QlFinInvoiceVo;
import com.ruoyi.ql.domain.bo.QlFinInvoiceBo;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 供应商开票Service接口
 *
 * @author ruoyi
 * @date 2022-12-11
 */
public interface IQlFinInvoiceService {

    /**
     * 查询供应商开票
     */
    QlFinInvoiceVo queryById(Long id);

    /**
     * 查询供应商开票列表
     */
    TableDataInfo<QlFinInvoiceVo> queryPageList(QlFinInvoiceBo bo, PageQuery pageQuery);

    /**
     * 查询供应商开票列表
     */
    List<QlFinInvoiceVo> queryList(QlFinInvoiceBo bo);

    /**
     * 新增供应商开票
     */
    Boolean insertByBo(QlFinInvoiceBo bo);

    /**
     * 修改供应商开票
     */
    Boolean updateByBo(QlFinInvoiceBo bo);

    /**
     * 校验并批量删除供应商开票信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
