package com.ruoyi.ql.service;

import com.ruoyi.ql.domain.QlFinPayment;
import com.ruoyi.ql.domain.vo.QlFinPaymentVo;
import com.ruoyi.ql.domain.bo.QlFinPaymentBo;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 供应商付款Service接口
 *
 * @author ruoyi
 * @date 2022-12-11
 */
public interface IQlFinPaymentService {

    /**
     * 查询供应商付款
     */
    QlFinPaymentVo queryById(Long id);

    /**
     * 查询供应商付款列表
     */
    TableDataInfo<QlFinPaymentVo> queryPageList(QlFinPaymentBo bo, PageQuery pageQuery);

    /**
     * 查询供应商付款列表
     */
    List<QlFinPaymentVo> queryList(QlFinPaymentBo bo);

    /**
     * 新增供应商付款
     */
    Boolean insertByBo(QlFinPaymentBo bo);

    /**
     * 新增付款，
     * 签订采购合同，增加一笔负向付款
     * 完成一笔付款，添加一笔正向付款
     * 更新供应商主表中，对应的已付款金额和欠款金额
     * @param bo
     * @return
     */
    Boolean insertPaymentByBo(QlFinPaymentBo bo);

    /**
     * 修改供应商付款
     */
    Boolean updateByBo(QlFinPaymentBo bo);

    /**
     * 校验并批量删除供应商付款信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
