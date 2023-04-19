package com.ruoyi.web.controller.jianguan.other.zlsk.strestservice.dp.mapper;

import com.ruoyi.web.controller.jianguan.other.zlsk.strestservice.dp.model.LadAcreage;
import org.apache.ibatis.annotations.*;

public interface LadAcreageMapper
{
    Integer insertDefaultValue(@Param("engineeringId") final Integer p0, @Param("ccArea") final Double p1, @Param("plotRatio") final Double p2);

    LadAcreage selectByEngineeringId(final Integer p0);

    Integer updateInfo(final LadAcreage p0);
}
