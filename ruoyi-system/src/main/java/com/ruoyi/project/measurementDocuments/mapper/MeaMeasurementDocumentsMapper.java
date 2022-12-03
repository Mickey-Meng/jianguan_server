package com.ruoyi.project.measurementDocuments.mapper;


import com.ruoyi.common.core.mapper.BaseMapperPlus;
import com.ruoyi.project.measurementDocuments.domain.MeaMeasurementDocuments;
import com.ruoyi.project.measurementDocuments.domain.vo.MeaMeasurementDocumentsVo;

/**
 * 计量凭证，设计计量、变更计量共用一张凭证，明细分开。Mapper接口
 *
 * @author ruoyi
 * @date 2022-12-03
 */
public interface MeaMeasurementDocumentsMapper extends BaseMapperPlus<MeaMeasurementDocumentsMapper, MeaMeasurementDocuments, MeaMeasurementDocumentsVo> {

}
