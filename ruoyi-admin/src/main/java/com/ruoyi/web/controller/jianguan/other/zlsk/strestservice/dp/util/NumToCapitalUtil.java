package com.ruoyi.web.controller.jianguan.other.zlsk.strestservice.dp.util;

public class NumToCapitalUtil
{
    private static final String[] NUMBER2;

    public static String changeCN(final String szNum) {
        final StringBuilder sb = new StringBuilder();
        final String str = szNum.trim();
        final int sl = str.length();
        int sp = 0;
        if (sl < 1) {
            return NumToCapitalUtil.NUMBER2[0];
        }
        while (sp < sl) {
            if (str.charAt(sp) >= '0' && str.charAt(sp) <= '9') {
                sb.append(NumToCapitalUtil.NUMBER2[str.charAt(sp) - '0']);
            }
            else {
                sb.append(str.charAt(sp));
            }
            ++sp;
        }
        return sb.toString();
    }

    static {
        NUMBER2 = new String[] { "\u3007", "\u4e00", "\u4e8c", "\u4e09", "\u56db", "\u4e94", "\u516d", "\u4e03", "\u516b", "\u4e5d" };
    }
}
