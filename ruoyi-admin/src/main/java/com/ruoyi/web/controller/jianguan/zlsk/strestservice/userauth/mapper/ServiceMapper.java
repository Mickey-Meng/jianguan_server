package com.ruoyi.web.controller.jianguan.zlsk.strestservice.userauth.mapper;

import com.ruoyi.web.controller.jianguan.zlsk.strestservice.userauth.model.Service;

import java.util.*;

public interface ServiceMapper
{
    int insert(final Service p0);

    int delete(final int p0);

    List<Map<String, Object>> select(final Service p0);
}
