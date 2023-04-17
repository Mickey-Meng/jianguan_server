package com.ruoyi.web.controller.jianguan.zlsk.strestservice.map.util;

import java.util.*;
import java.text.*;

public class DateMonthUtil
{
    public static String getLastDayOfMonth(final int month) {
        final Calendar cal = Calendar.getInstance();
        cal.set(2, month - 1);
        cal.set(5, cal.getActualMaximum(5));
        return new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
    }

    public static String getFirstDayOfMonth(final int month) {
        final Calendar cal = Calendar.getInstance();
        cal.set(2, month - 1);
        cal.set(5, cal.getMinimum(5));
        return new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
    }
}
