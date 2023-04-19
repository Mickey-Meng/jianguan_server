package com.ruoyi.web.controller.jianguan.other.zlsk.strestservice.userauth.mapper;

import com.ruoyi.web.controller.jianguan.other.zlsk.strestservice.userauth.model.Menu;
import com.ruoyi.web.controller.jianguan.other.zlsk.strestservice.userauth.model.SubsystemMenu;
import com.ruoyi.web.controller.jianguan.other.zlsk.strestservice.userauth.model.Userauth;
import org.apache.ibatis.annotations.*;
import java.util.*;

public interface MenuMapper
{
    int insert(final Menu p0);

    int update(final Menu p0);

    int delete(final int p0);

    int addSubMenu(final SubsystemMenu p0);

    int delSubMenu(@Param("id") final int p0);

    List<Map<String, Object>> select(final Menu p0);

    List<Map<String, Object>> selectMenuBySys(final Menu p0);

    List<Map<String, Object>> selectMenuByUserauth(final Userauth p0);

    List<Map<String, Object>> selectMenu(final Map<String, Object> p0);

    List<Map<String, Object>> selectByUserauth(final Userauth p0);
}
