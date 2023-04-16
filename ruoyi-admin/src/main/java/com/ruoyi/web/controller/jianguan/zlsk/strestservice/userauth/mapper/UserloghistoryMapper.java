package com.ruoyi.web.controller.jianguan.zlsk.strestservice.userauth.mapper;

import com.ruoyi.web.controller.jianguan.zlsk.strestservice.userauth.model.Userloghistory;

import java.util.*;

public interface UserloghistoryMapper
{
    List<Map<String, Object>> select(final Userloghistory p0);

    int delete(final Map p0);

    int insert(final Userloghistory p0);
}
