package com.ruoyi.web.controller.jianguan.zlsk.strestservice.userauth.mapper;

import com.ruoyi.web.controller.jianguan.zlsk.strestservice.userauth.model.Userauth;

import java.util.*;

public interface UserauthMapper
{
    int insert(final Userauth p0);

    int delete(final Userauth p0);

    List<Map<String, Object>> select(final Userauth p0);
}
