package com.ruoyi.web.controller.jianguan.zlsk.strestservice.userauth.util;

import java.text.*;
import java.util.*;

public class DateUtil
{
    public static String getCurrentDate() {
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(new Date());
    }

    public static String getDate(final Long timestamp) {
        final Date date = new Date(timestamp);
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(date);
    }
}
