package com.ruoyi.project.ledger.service;


import com.ruoyi.common.core.domain.PageQuery;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.project.ledger.domain.bo.MeaLedgerBreakdownDetailBo;
import com.ruoyi.project.ledger.domain.vo.MeaLedgerBreakdownDetailVo;

import java.util.Collection;
import java.util.List;

/**
 * 台账分解明细Service接口
 *
 * @author ruoyi
 * @date 2022-12-03
 */
public interface IMeaLedgerBreakdownDetailService {

    /**
     * 查询台账分解明细
     */
    MeaLedgerBreakdownDetailVo queryById(String tzfjbh);

    /**
     * 查询台账分解明细列表
     */
    TableDataInfo<MeaLedgerBreakdownDetailVo> queryPageList(MeaLedgerBreakdownDetailBo bo, PageQuery pageQuery);

    /**
     * 查询台账分解明细列表
     */
    List<MeaLedgerBreakdownDetailVo> queryList(MeaLedgerBreakdownDetailBo bo);

    /**
     * 新增台账分解明细
     */
    Boolean insertByBo(MeaLedgerBreakdownDetailBo bo);

    /**
     * 修改台账分解明细
     */
    Boolean updateByBo(MeaLedgerBreakdownDetailBo bo);

    /**
     * 校验并批量删除台账分解明细信息
     */
    Boolean deleteWithValidByIds(Collection<String> ids, Boolean isValid);
}
