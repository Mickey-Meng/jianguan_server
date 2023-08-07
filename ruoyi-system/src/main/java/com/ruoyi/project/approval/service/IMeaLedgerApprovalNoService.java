package com.ruoyi.project.approval.service;


import com.ruoyi.common.core.domain.PageQuery;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.project.approval.domain.bo.MeaLedgerApprovalNoBo;
import com.ruoyi.project.approval.domain.vo.MeaLedgerApprovalNoVo;

import java.util.Collection;
import java.util.List;

/**
 * 期数管理Service接口
 *
 * @author ruoyi
 * @date 2022-12-03
 */
public interface IMeaLedgerApprovalNoService {

    /**
     * 查询期数管理
     */
    MeaLedgerApprovalNoVo queryById(String id);
    MeaLedgerApprovalNoVo queryMax();

    /**
     * 查询期数管理列表
     */
    TableDataInfo<MeaLedgerApprovalNoVo> queryPageList(MeaLedgerApprovalNoBo bo, PageQuery pageQuery);

    /**
     * 查询期数管理列表
     */
    List<MeaLedgerApprovalNoVo> queryList(MeaLedgerApprovalNoBo bo);

    /**
     * 查询期数数量
     * @param bo
     * @return
     */
    long count(MeaLedgerApprovalNoBo bo);

    /**
     * 新增期数管理
     */
    Boolean insertByBo(MeaLedgerApprovalNoBo bo);

    /**
     * 修改期数管理
     */
    Boolean updateByBo(MeaLedgerApprovalNoBo bo);

    /**
     * 校验并批量删除期数管理信息
     */
    Boolean deleteWithValidByIds(Collection<String> ids, Boolean isValid);
}
