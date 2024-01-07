package com.ruoyi.project.ledger.mapper;


import com.ruoyi.common.core.mapper.BaseMapperPlus;
import com.ruoyi.project.ledger.domain.MeaLedgerBreakdownDetail;
import com.ruoyi.project.ledger.domain.vo.MeaLedgerBreakdownDetailVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 台账分解明细Mapper接口
 *
 * @author ruoyi
 * @date 2022-12-04
 */
public interface MeaLedgerBreakdownDetailMapper extends BaseMapperPlus<MeaLedgerBreakdownDetailMapper, MeaLedgerBreakdownDetail, MeaLedgerBreakdownDetailVo> {
    List<MeaLedgerBreakdownDetailVo> selectMeaLedgerBreakdownDetails(@Param("tzfjbh") String tzfjbh);
    List<MeaLedgerBreakdownDetailVo> queryList4ledgerApproval(@Param("zmh") String zmh,@Param("zmmc") String zmmc);


}
