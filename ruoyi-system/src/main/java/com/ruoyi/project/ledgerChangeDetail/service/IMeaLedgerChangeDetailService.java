package com.ruoyi.project.ledgerChangeDetail.service;


import com.ruoyi.common.core.domain.PageQuery;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.project.ledgerChangeDetail.domain.bo.MeaLedgerChangeDetailBo;
import com.ruoyi.project.ledgerChangeDetail.domain.vo.MeaLedgerChangeDetailVo;

import java.util.Collection;
import java.util.List;

/**
 * 台账变更/工程变更 明细Service接口
 *
 * @author ruoyi
 * @date 2022-12-04
 */
public interface IMeaLedgerChangeDetailService {

    /**
     * 查询台账变更/工程变更 明细
     */
    MeaLedgerChangeDetailVo queryById(String zmh);

    /**
     * 查询台账变更/工程变更 明细列表
     */
    TableDataInfo<MeaLedgerChangeDetailVo> queryPageList(MeaLedgerChangeDetailBo bo, PageQuery pageQuery);

    /**
     * 查询台账变更/工程变更 明细列表
     */
    List<MeaLedgerChangeDetailVo> queryList(MeaLedgerChangeDetailBo bo);

    /**
     * 新增台账变更/工程变更 明细
     */
    Boolean insertByBo(MeaLedgerChangeDetailBo bo);

    /**
     * 修改台账变更/工程变更 明细
     */
    Boolean updateByBo(MeaLedgerChangeDetailBo bo);

    /**
     * 校验并批量删除台账变更/工程变更 明细信息
     */
    Boolean deleteWithValidByIds(Collection<String> ids, Boolean isValid);
}
