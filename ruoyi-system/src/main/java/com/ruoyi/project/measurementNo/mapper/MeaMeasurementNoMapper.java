package com.ruoyi.project.measurementNo.mapper;


import com.ruoyi.common.core.mapper.BaseMapperPlus;
import com.ruoyi.flowable.domain.dto.FlowAuditEntryPageDTO;
import com.ruoyi.project.measurementNo.domain.MeaMeasurementNo;
import com.ruoyi.project.measurementNo.domain.vo.MeaMeasurementNoVo;
import org.apache.ibatis.annotations.Param;

/**
 * 中间计量期数管理Mapper接口
 *
 * @author ruoyi
 * @date 2022-12-03
 */
public interface MeaMeasurementNoMapper extends BaseMapperPlus<MeaMeasurementNoMapper, MeaMeasurementNo, MeaMeasurementNoVo> {

    MeaMeasurementNoVo  getMax();
}
