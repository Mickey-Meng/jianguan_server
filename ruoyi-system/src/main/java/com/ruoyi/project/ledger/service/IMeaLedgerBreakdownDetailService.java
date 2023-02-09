package com.ruoyi.project.ledger.service;


import com.ruoyi.common.core.domain.PageQuery;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.project.ledger.domain.bo.MeaLedgerBreakdownDetailBo;
import com.ruoyi.project.ledger.domain.vo.MeaLedgerBreakdownDetailInfoVo;
import com.ruoyi.project.ledger.domain.vo.MeaLedgerBreakdownDetailVo;

import java.util.Collection;
import java.util.List;

/**
 * 台账分解明细Service接口
 *
 * @author ruoyi
 * @date 2022-12-04
 */
public interface IMeaLedgerBreakdownDetailService {

    /**
     * 查询台账分解明细
     */
    MeaLedgerBreakdownDetailVo queryById(String id);

    /**
     * 查询台账分解明细列表
     */
    TableDataInfo<MeaLedgerBreakdownDetailVo> queryPageList(MeaLedgerBreakdownDetailBo bo, PageQuery pageQuery);

    /**
     * 查询台账分解明细列表
     */
    List<MeaLedgerBreakdownDetailVo> queryList(MeaLedgerBreakdownDetailBo bo);
    /**
     * 查询台账分解明细叶子节点列表
     */
    List<MeaLedgerBreakdownDetailVo> getLeafList(String reviewCode);
    /**
     * 新增台账分解明细
     */
    Boolean insertByBo(MeaLedgerBreakdownDetailBo bo);
    Boolean insertByListBo(List<MeaLedgerBreakdownDetailBo> bo);

    /**
     * 修改台账分解明细
     */
    Boolean updateByBo(MeaLedgerBreakdownDetailBo bo);

    /**
     * 校验并批量删除台账分解明细信息
     */
    Boolean deleteWithValidByIds(Collection<String> ids, Boolean isValid);

    TableDataInfo<MeaLedgerBreakdownDetailInfoVo> listInfo(MeaLedgerBreakdownDetailBo bo, PageQuery pageQuery);
}
