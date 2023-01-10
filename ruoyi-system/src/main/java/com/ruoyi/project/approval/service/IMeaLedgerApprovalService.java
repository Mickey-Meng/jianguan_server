package com.ruoyi.project.approval.service;


import com.ruoyi.common.core.domain.PageQuery;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.project.approval.domain.bo.MeaLedgerApprovalBo;
import com.ruoyi.project.approval.domain.vo.MeaLedgerApprovalBreakDownVo;
import com.ruoyi.project.approval.domain.vo.MeaLedgerApprovalVo;

import java.util.Collection;
import java.util.List;

/**
 * 台账报审Service接口
 *
 * @author ruoyi
 * @date 2022-12-03
 */
public interface IMeaLedgerApprovalService {

    /**
     * 查询台账报审
     */
    MeaLedgerApprovalVo queryById(Long id);

    /**
     * 查询台账报审列表
     */
    TableDataInfo<MeaLedgerApprovalVo> queryPageList(MeaLedgerApprovalBo bo, PageQuery pageQuery);

    /**
     * 查询台账报审列表
     */
    List<MeaLedgerApprovalVo> queryList(MeaLedgerApprovalBo bo);
    /**
     * 查询台账报审列表
     */
    List<MeaLedgerApprovalBreakDownVo> queryMeaLedgerApprovalBreakDownList(MeaLedgerApprovalBo bo);
    /**
     * 新增台账报审
     */
    Boolean insertByBo(MeaLedgerApprovalBo bo);

    /**
     * 修改台账报审
     */
    Boolean updateByBo(MeaLedgerApprovalBo bo);

    /**
     * 校验并批量删除台账报审信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

    boolean insertByListBo(List<MeaLedgerApprovalBo> bo);
}
