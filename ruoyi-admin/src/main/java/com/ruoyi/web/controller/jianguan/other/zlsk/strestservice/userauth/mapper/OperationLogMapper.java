package com.ruoyi.web.controller.jianguan.other.zlsk.strestservice.userauth.mapper;

import com.ruoyi.web.controller.jianguan.other.zlsk.strestservice.userauth.model.OperationLog;

import java.util.*;

public interface OperationLogMapper
{
    List<Map<String, Object>> select(final OperationLog p0);

    int insert(final OperationLog p0);
}
