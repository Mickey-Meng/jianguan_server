package com.ruoyi.web.controller.jianguan.zlsk.strestservice.dp.service;

import java.util.*;

public interface BaseService<Entity>
{
    List<Entity> workList(final Integer p0);

    List<Entity> workList(final Integer p0, final String p1);

    Integer createWork(final Entity p0);

    String deleteWorkInfo(final List<Integer> p0);
}
