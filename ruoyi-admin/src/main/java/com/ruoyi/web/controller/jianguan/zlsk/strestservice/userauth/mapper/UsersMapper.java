package com.ruoyi.web.controller.jianguan.zlsk.strestservice.userauth.mapper;

import java.util.*;

import com.ruoyi.web.controller.jianguan.zlsk.strestservice.userauth.model.Users;
import org.apache.ibatis.annotations.*;

public interface UsersMapper
{
    int insert(final Users p0);

    int update(final Users p0);

    List<Map<String, Object>> select(final Users p0);

    int getUserid();

    List<Map<String, Object>> login(final Users p0);

    List<Map<String, Object>> codeLogin(final Users p0);

    List<Map<String, Object>> getLonlat(final Map p0);

    int updateLonlat(final Map p0);

    int addPosition(final Map p0);

    int addMobileType(@Param("userid") final int p0, @Param("mobile") final int p1);

    int updateMobile(@Param("userid") final int p0, @Param("mobile") final int p1);

    List<Map<String, Object>> selectMobileType(@Param("userid") final int p0);

    List<Map<String, Object>> selectUserPosition(@Param("userid") final int p0);

    String selectPWDById(final Integer p0);

    List<Map<String, Object>> selectByUserids(final List<Integer> p0);
}
