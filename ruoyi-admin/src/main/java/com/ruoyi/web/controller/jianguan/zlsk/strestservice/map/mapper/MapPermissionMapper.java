package com.ruoyi.web.controller.jianguan.zlsk.strestservice.map.mapper;

import com.ruoyi.web.controller.jianguan.zlsk.strestservice.map.model.MapPermission;
import java.util.*;
import org.apache.ibatis.annotations.*;

public interface MapPermissionMapper
{
    List<Map<String, Object>> selectMapPermission(final MapPermission p0);

    List<Map<String, Object>> query(@Param("sql") final String p0);

    int insertMapPermission(final MapPermission p0);

    int updateMapPermission(final MapPermission p0);

    int delMapPermission(@Param("mid") final int p0);

    int delMapPermissionByMid(@Param("mid") final int p0, @Param("ptype") final int p1, @Param("aid") final int p2);

    int delMapPermissionByAid(@Param("aid") final int p0);

    List<Map<String, Object>> selectTotal(@Param("type") final int p0);
}
