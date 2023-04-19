package com.ruoyi.web.controller.jianguan.other.zlsk.strestservice.map.util;

import java.util.*;

public class StringUtil
{
    public static String[] chars;

    public static boolean isStringNull(final String params) {
        return params == null || params.equals("");
    }

    public static String generateShortUuid() {
        final StringBuffer shortBuffer = new StringBuffer();
        final String uuid = UUID.randomUUID().toString().replace("-", "");
        for (int i = 0; i < 8; ++i) {
            final String str = uuid.substring(i * 4, i * 4 + 4);
            final int x = Integer.parseInt(str, 16);
            shortBuffer.append(StringUtil.chars[x % 62]);
        }
        return shortBuffer.toString();
    }

    static {
        StringUtil.chars = new String[] { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z" };
    }
}
