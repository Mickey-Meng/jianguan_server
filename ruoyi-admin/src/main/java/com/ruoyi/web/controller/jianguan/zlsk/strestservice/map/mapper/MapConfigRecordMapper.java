package com.ruoyi.web.controller.jianguan.zlsk.strestservice.map.mapper;

import com.ruoyi.web.controller.jianguan.zlsk.strestservice.map.model.MapConfigRecord;
import java.util.*;
import org.apache.ibatis.annotations.*;

public interface MapConfigRecordMapper
{
    List<Map<String, Object>> selectMapConfigs(final MapConfigRecord p0);

    List<Map<String, Object>> selectRecordByPlan(@Param("mpid") final int p0);

    List<Map<String, Object>> selectRecordByPlanParent(@Param("mpids") final String p0);

    List<Map<String, Object>> selectNoPlans(final MapConfigRecord p0);

    int getTotal(final MapConfigRecord p0);

    int insertMapConfig(final MapConfigRecord p0);

    int updateMapConfig(final MapConfigRecord p0);

    int delMapMapConfig(@Param("id") final int p0);
}
