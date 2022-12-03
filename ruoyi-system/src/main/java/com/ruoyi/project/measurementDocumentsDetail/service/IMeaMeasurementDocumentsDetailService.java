package com.ruoyi.project.measurementDocumentsDetail.service;


import com.ruoyi.common.core.domain.PageQuery;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.project.measurementDocumentsDetail.domain.bo.MeaMeasurementDocumentsDetailBo;
import com.ruoyi.project.measurementDocumentsDetail.domain.vo.MeaMeasurementDocumentsDetailVo;

import java.util.Collection;
import java.util.List;

/**
 * 台账变更/工程变更 明细Service接口
 *
 * @author ruoyi
 * @date 2022-12-03
 */
public interface IMeaMeasurementDocumentsDetailService {

    /**
     * 查询台账变更/工程变更 明细
     */
    MeaMeasurementDocumentsDetailVo queryById(String id);

    /**
     * 查询台账变更/工程变更 明细列表
     */
    TableDataInfo<MeaMeasurementDocumentsDetailVo> queryPageList(MeaMeasurementDocumentsDetailBo bo, PageQuery pageQuery);

    /**
     * 查询台账变更/工程变更 明细列表
     */
    List<MeaMeasurementDocumentsDetailVo> queryList(MeaMeasurementDocumentsDetailBo bo);

    /**
     * 新增台账变更/工程变更 明细
     */
    Boolean insertByBo(MeaMeasurementDocumentsDetailBo bo);

    /**
     * 修改台账变更/工程变更 明细
     */
    Boolean updateByBo(MeaMeasurementDocumentsDetailBo bo);

    /**
     * 校验并批量删除台账变更/工程变更 明细信息
     */
    Boolean deleteWithValidByIds(Collection<String> ids, Boolean isValid);
}
