package com.ruoyi.web.controller.jianguan.zlsk.strestservice.dp.mapper;

import com.ruoyi.web.controller.jianguan.zlsk.strestservice.dp.model.DpEngineeringCost;
import org.apache.ibatis.annotations.*;

public interface DpEngineeringCostMapper
{
    DpEngineeringCost selectByEngineeringId(final Integer p0);

    Integer insertInfo(final DpEngineeringCost p0);

    Integer updateInfo(final DpEngineeringCost p0);

    Integer insertLadRate(@Param("engineeringId") final Integer p0, @Param("ladUnforeseenRate") final Double p1);

    Integer insertLadTotal(@Param("engineeringId") final Integer p0, @Param("totalCost") final Double p1);
}
