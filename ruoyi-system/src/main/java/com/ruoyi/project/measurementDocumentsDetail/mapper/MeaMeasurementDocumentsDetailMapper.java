package com.ruoyi.project.measurementDocumentsDetail.mapper;


import com.ruoyi.common.core.mapper.BaseMapperPlus;
import com.ruoyi.flowable.domain.dto.FlowAuditEntryPageDTO;
import com.ruoyi.flowable.domain.entity.FlowAuditEntry;
import com.ruoyi.project.measurementDocumentsDetail.domain.MeaMeasurementDocumentsDetail;
import com.ruoyi.project.measurementDocumentsDetail.domain.vo.MeaMeasurementDocumentsDetailVo;
import com.ruoyi.project.measurementNo.domain.vo.MeaMeasurementNoVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 台账变更/工程变更 明细Mapper接口
 *
 * @author ruoyi
 * @date 2022-12-03
 */
public interface MeaMeasurementDocumentsDetailMapper extends BaseMapperPlus<MeaMeasurementDocumentsDetailMapper, MeaMeasurementDocumentsDetail, MeaMeasurementDocumentsDetailVo> {

}
