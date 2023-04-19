package com.ruoyi.web.controller.jianguan.other.zlsk.strestservice.userauth.mapper;

import com.ruoyi.web.controller.jianguan.other.zlsk.strestservice.userauth.model.Fun;

import java.util.*;

public interface FunMapper
{
    int insert(final Fun p0);

    int delete(final int p0);

    List<Map<String, Object>> select(final Fun p0);

    List<Map<String, Object>> selectFun(final Map<String, Object> p0);
}
