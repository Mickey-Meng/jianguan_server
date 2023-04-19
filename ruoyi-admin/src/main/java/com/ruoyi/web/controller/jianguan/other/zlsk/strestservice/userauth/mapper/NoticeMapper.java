package com.ruoyi.web.controller.jianguan.other.zlsk.strestservice.userauth.mapper;

import java.util.*;

import com.ruoyi.web.controller.jianguan.other.zlsk.strestservice.userauth.model.Notice;
import org.apache.ibatis.annotations.*;

public interface NoticeMapper
{
    List<Map<String, Object>> select(final Notice p0);

    List<Map<String, Object>> selectExist(final Notice p0);

    int selectTotal(final Notice p0);

    int insert(final Notice p0);

    int update(final Notice p0);

    int delete(@Param("id") final int p0);
}
