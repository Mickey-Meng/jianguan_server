package com.ruoyi.project.ledger.service;


import com.ruoyi.project.ledger.domain.bo.MeaLedgerBreakdownBo;
import com.ruoyi.project.ledger.domain.vo.MeaLedgerBreakdownVo;

import java.util.Collection;
import java.util.List;

/**
 * 台账分解Service接口
 *
 * @author ruoyi
 * @date 2022-12-03
 */
public interface IMeaLedgerBreakdownService {

    /**
     * 查询台账分解
     */
    MeaLedgerBreakdownVo queryById(String tzfjbh);


    /**
     * 查询台账分解列表
     */
    List<MeaLedgerBreakdownVo> queryList(MeaLedgerBreakdownBo bo);

    /**
     * 新增台账分解
     */
    Boolean insertByBo(MeaLedgerBreakdownBo bo);

    /**
     * 修改台账分解
     */
    Boolean updateByBo(MeaLedgerBreakdownBo bo);

    /**
     * 校验并批量删除台账分解信息
     */
    Boolean deleteWithValidByIds(Collection<String> ids, Boolean isValid);
}
