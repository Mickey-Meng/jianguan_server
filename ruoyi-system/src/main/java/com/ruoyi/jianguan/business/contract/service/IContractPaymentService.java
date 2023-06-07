package com.ruoyi.jianguan.business.contract.service;

import com.ruoyi.common.core.domain.PageQuery;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.jianguan.business.contract.domain.bo.ContractPaymentBo;
import com.ruoyi.jianguan.business.contract.domain.vo.ContractPaymentVo;

import java.util.Collection;
import java.util.List;

/**
 * 合同付款Service接口
 *
 * @author mickey
 * @date 2023-06-07
 */
public interface IContractPaymentService {

    /**
     * 查询合同付款
     */
    ContractPaymentVo queryById(Long id);

    /**
     * 查询合同付款列表
     */
    TableDataInfo<ContractPaymentVo> queryPageList(ContractPaymentBo bo, PageQuery pageQuery);

    /**
     * 查询合同付款列表
     */
    List<ContractPaymentVo> queryList(ContractPaymentBo bo);

    /**
     * 新增合同付款
     */
    Boolean insertByBo(ContractPaymentBo bo);

    /**
     * 修改合同付款
     */
    Boolean updateByBo(ContractPaymentBo bo);

    /**
     * 校验并批量删除合同付款信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);


}