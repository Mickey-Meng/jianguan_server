package com.ruoyi.jianguan.business.onlineForms.mapper;

import com.ruoyi.jianguan.business.onlineForms.domain.PubCheckReport;
import com.ruoyi.jianguan.business.onlineForms.domain.vo.PubCheckReportVo;
import com.ruoyi.common.core.mapper.BaseMapperPlus;
import org.apache.ibatis.annotations.Param;

/**
 * 评定填报关联信息Mapper接口
 *
 * @author mickey
 * @date 2024-01-16
 */
public interface PubCheckReportMapper extends BaseMapperPlus<PubCheckReportMapper, PubCheckReport, PubCheckReportVo> {

    Integer selectCountByPrimaryKey(@Param("id") Long id);
}
