package com.ruoyi.jianguan.business.contract.mapper;


import com.ruoyi.common.core.mapper.BaseDaoMapper;
import com.ruoyi.jianguan.business.contract.domain.dto.ContractPaymentPageDTO;
import com.ruoyi.jianguan.business.contract.domain.entity.ContractPayment;
import com.ruoyi.jianguan.business.contract.domain.vo.ContractPaymentPageVo;
import com.ruoyi.jianguan.business.metrology.domain.entity.Metrology;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 合同付款Mapper接口
 *
 * @author mickey
 * @date 2023-06-07
 */
public interface ContractPaymentMapper extends BaseDaoMapper<ContractPayment> {

    /**
     * 分页查询施工专业分包合同表数据
     *
     * @param pageDto
     * @return
     */
    List<ContractPaymentPageVo> getPageInfo(@Param("pageDto") ContractPaymentPageDTO pageDto);

}