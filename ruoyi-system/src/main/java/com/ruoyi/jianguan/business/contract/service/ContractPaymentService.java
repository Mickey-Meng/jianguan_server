package com.ruoyi.jianguan.business.contract.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.ruoyi.common.core.domain.object.ResponseBase;
import com.ruoyi.jianguan.business.contract.domain.dto.ContractPaymentSaveDTO;
import com.ruoyi.jianguan.business.contract.domain.dto.ContractPaymentPageDTO;
import com.ruoyi.jianguan.business.contract.domain.entity.ContractPayment;
import com.ruoyi.jianguan.business.contract.domain.vo.ContractPaymentPageVo;
import com.ruoyi.jianguan.business.contract.domain.vo.ContractPaymentDetailVo;

/**
 * 合同付款Service接口
 *
 * @author mickey
 * @date 2023-06-07
 */
public interface ContractPaymentService extends IService<ContractPayment> {


    /**
     * 新增或者更新施工专业分包合同表数据
     *
     * @param saveDto
     * @return
     */
    ResponseBase addOrUpdate(ContractPaymentSaveDTO saveDto);

    /**
     * 通过id获取一条施工专业分包合同表数据
     *
     * @param id
     * @return
     */
    ContractPaymentDetailVo getInfoById(Long id);

    /**
     * 分页查询施工专业分包合同表数据
     *
     * @param pageDto
     * @return
     */
    PageInfo<ContractPaymentPageVo> getPageInfo(ContractPaymentPageDTO pageDto);



}