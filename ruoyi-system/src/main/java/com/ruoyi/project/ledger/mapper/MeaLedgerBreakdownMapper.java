package com.ruoyi.project.ledger.mapper;


import com.ruoyi.common.core.mapper.BaseMapperPlus;
import com.ruoyi.project.ledger.domain.MeaLedgerBreakdown;
import com.ruoyi.project.ledger.domain.vo.MeaLedgerBreakdownVo;
import org.apache.ibatis.annotations.Param;

/**
 * 台账分解Mapper接口
 *
 * @author ruoyi
 * @date 2022-12-07
 */
public interface MeaLedgerBreakdownMapper extends BaseMapperPlus<MeaLedgerBreakdownMapper, MeaLedgerBreakdown, MeaLedgerBreakdownVo> {

    String getNextCode(@Param("tzfjbhParent") String tzfjbhParent);

}
