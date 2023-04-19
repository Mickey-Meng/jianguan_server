package com.ruoyi.web.controller.jianguan.other.zlsk.strestservice.map.mapper;

import java.util.*;

import com.ruoyi.web.controller.jianguan.other.zlsk.strestservice.map.model.MapPlan;
import com.ruoyi.web.controller.jianguan.other.zlsk.strestservice.map.model.Map_Record_Plan;
import org.apache.ibatis.annotations.*;

public interface MapPlanMapper1
{
    List<Map<String, Object>> selectMapPlans(final MapPlan p0);

    List<Map<String, Object>> selectNoPer(@Param("mId") final int p0, @Param("pname") final String p1, @Param("pid") final int p2);

    List<Map<String, Object>> selectPlanByPer(@Param("sql") final String p0);

    int insertMapPlan(final MapPlan p0);

    int insertMapRecordPlan(final Map_Record_Plan p0);

    int updateMapPlan(final MapPlan p0);

    int delMapPlan(@Param("id") final int p0);

    int delChildrenMapPlan(@Param("id") final int p0);

    int delAllMapPlan();

    int delMap_Record_Plan(@Param("mcrId") final int p0);

    int updateLayers(@Param("v") final int p0, @Param("id") final int p1);
}
