package com.ruoyi.web.controller.jianguan.zlsk.stsupporter.stctk.util;

import com.ruoyi.web.controller.jianguan.zlsk.stsupporter.stctk.stbase.*;
import java.util.*;
import java.text.*;

public class DateUtil extends StSupporterBase
{
    public static final String format(final Date date) {
        return format(date, "yyyy-MM-dd HH:mm:ss");
    }

    public static final String format(final Date date, final String s) {
        return new SimpleDateFormat(s).format(date);
    }

    public static final String formatNow() {
        return formatNow("yyyy-MM-dd HH:mm:ss");
    }

    public static final String formatNow(final String s) {
        return format(new Date(), s);
    }

    public static final Date parse(final String s) {
        return parse(s, "yyyy-MM-dd HH:mm:ss");
    }

    public static final Date parse(final String s, final String s2) {
        try {
            return new SimpleDateFormat(s2).parse(s);
        }
        catch (Exception ex) {
            return null;
        }
    }
}
