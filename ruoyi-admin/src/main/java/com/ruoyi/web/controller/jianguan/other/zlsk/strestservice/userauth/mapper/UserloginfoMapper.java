package com.ruoyi.web.controller.jianguan.other.zlsk.strestservice.userauth.mapper;

import com.ruoyi.web.controller.jianguan.other.zlsk.strestservice.userauth.model.Userloginfo;

import java.util.*;

public interface UserloginfoMapper
{
    int insert(final Userloginfo p0);

    int update(final Userloginfo p0);

    List<Map<String, Object>> select(final Userloginfo p0);
}
