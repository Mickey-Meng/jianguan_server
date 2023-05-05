package com.ruoyi.ql.service;

import com.ruoyi.ql.domain.QlFinInvoiceSale;
import com.ruoyi.ql.domain.vo.QlFinInvoiceSaleVo;
import com.ruoyi.ql.domain.bo.QlFinInvoiceSaleBo;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 销售开票Service接口
 *
 * @author ruoyi
 * @date 2023-05-05
 */
public interface IQlFinInvoiceSaleService {

    /**
     * 查询销售开票
     */
    QlFinInvoiceSaleVo queryById(Long id);

    /**
     * 查询销售开票列表
     */
    TableDataInfo<QlFinInvoiceSaleVo> queryPageList(QlFinInvoiceSaleBo bo, PageQuery pageQuery);

    /**
     * 查询销售开票列表
     */
    List<QlFinInvoiceSaleVo> queryList(QlFinInvoiceSaleBo bo);

    /**
     * 新增销售开票
     */
    Boolean insertByBo(QlFinInvoiceSaleBo bo);

    /**
     * 修改销售开票
     */
    Boolean updateByBo(QlFinInvoiceSaleBo bo);

    /**
     * 校验并批量删除销售开票信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
