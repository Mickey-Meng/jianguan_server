package com.ruoyi.project.measurementDocuments.service;


import com.ruoyi.common.core.domain.PageQuery;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.project.measurementDocuments.domain.bo.MeaMeasurementDocumentsAndDeBo;
import com.ruoyi.project.measurementDocuments.domain.bo.MeaMeasurementDocumentsBo;
import com.ruoyi.project.measurementDocuments.domain.vo.MeaMeasurementDocumentsVo;

import java.util.Collection;
import java.util.List;

/**
 * 计量凭证，设计计量、变更计量共用一张凭证，明细分开。Service接口
 *
 * @author ruoyi
 * @date 2022-12-03
 */
public interface IMeaMeasurementDocumentsService {

    /**
     * 查询计量凭证，设计计量、变更计量共用一张凭证，明细分开。
     */
    MeaMeasurementDocumentsVo queryById(String id);

    /**
     * 查询计量凭证，设计计量、变更计量共用一张凭证，明细分开。列表
     */
    TableDataInfo<MeaMeasurementDocumentsVo> queryPageList(MeaMeasurementDocumentsBo bo, PageQuery pageQuery);

    /**
     * 查询计量凭证，设计计量、变更计量共用一张凭证，明细分开。列表
     */
    List<MeaMeasurementDocumentsVo> queryList(MeaMeasurementDocumentsBo bo);

    /**
     * 新增计量凭证，设计计量、变更计量共用一张凭证，明细分开。
     */
    Boolean insertByBo(MeaMeasurementDocumentsBo bo);

    /**
     * 修改计量凭证，设计计量、变更计量共用一张凭证，明细分开。
     */
    Boolean updateByBo(MeaMeasurementDocumentsBo bo);

    /**
     * 校验并批量删除计量凭证，设计计量、变更计量共用一张凭证，明细分开。信息
     */
    Boolean deleteWithValidByIds(Collection<String> ids, Boolean isValid);

    boolean saveMeaMeasurementDocumentsAndDeBo(MeaMeasurementDocumentsAndDeBo bo);
}
