package com.ruoyi.web.controller.jianguan.zlsk.strestservice.userauth.mapper;

import com.ruoyi.web.controller.jianguan.zlsk.strestservice.userauth.model.SubSystem;
import org.apache.ibatis.annotations.*;
import java.util.*;

public interface SubSystemMapper
{
    int insert(final SubSystem p0);

    int delete(@Param("id") final int p0);

    int updateSys(final SubSystem p0);

    int selectTotal(final SubSystem p0);

    List<Map<String, Object>> select(final SubSystem p0);

    List<Map<String, Object>> selectSubsystemType(final SubSystem p0);
}
