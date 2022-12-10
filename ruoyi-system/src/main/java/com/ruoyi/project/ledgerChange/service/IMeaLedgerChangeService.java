package com.ruoyi.project.ledgerChange.service;


import com.ruoyi.common.core.domain.PageQuery;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.project.ledgerChange.domain.bo.MeaLedgerChangeBo;
import com.ruoyi.project.ledgerChange.domain.vo.MeaLedgerChangeVo;
import com.ruoyi.project.ledgerChangeDetail.domain.bo.MeaLedgerChangeDetailBo;

import java.util.Collection;
import java.util.List;

/**
 * 台账变更/工程变更Service接口
 *
 * @author ruoyi
 * @date 2022-12-03
 */
public interface IMeaLedgerChangeService {

    /**
     * 查询台账变更/工程变更
     */
    MeaLedgerChangeVo queryById(String id);

    /**
     * 查询台账变更/工程变更列表
     */
    TableDataInfo<MeaLedgerChangeVo> queryPageList(MeaLedgerChangeBo bo, PageQuery pageQuery);

    /**
     * 查询台账变更/工程变更列表
     */
    List<MeaLedgerChangeVo> queryList(MeaLedgerChangeBo bo);

    /**
     * 新增台账变更/工程变更
     */
    Boolean insertByBo(MeaLedgerChangeBo bo);

    /**
     * 修改台账变更/工程变更
     */
    Boolean updateByBo(MeaLedgerChangeBo bo);

    /**
     * 校验并批量删除台账变更/工程变更信息
     */
    Boolean deleteWithValidByIds(Collection<String> ids, Boolean isValid);

    boolean insertList(List<MeaLedgerChangeDetailBo> bo);
}
