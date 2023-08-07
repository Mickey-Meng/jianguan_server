package com.ruoyi.project.approval.mapper;


import com.ruoyi.common.core.mapper.BaseMapperPlus;
import com.ruoyi.project.approval.domain.MeaLedgerApprovalNo;
import com.ruoyi.project.approval.domain.vo.MeaLedgerApprovalNoVo;

/**
 * 期数管理Mapper接口
 *
 * @author ruoyi
 * @date 2022-12-03
 */
public interface MeaLedgerApprovalNoMapper extends BaseMapperPlus<MeaLedgerApprovalNoMapper, MeaLedgerApprovalNo, MeaLedgerApprovalNoVo> {

    MeaLedgerApprovalNoVo getMax();

}
