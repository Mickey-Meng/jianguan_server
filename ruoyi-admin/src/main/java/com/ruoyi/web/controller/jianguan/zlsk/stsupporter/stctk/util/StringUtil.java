package com.ruoyi.web.controller.jianguan.zlsk.stsupporter.stctk.util;

import com.ruoyi.web.controller.jianguan.zlsk.stsupporter.stctk.stbase.*;
import java.util.regex.*;

public class StringUtil extends StSupporterBase
{
    public static final boolean isNull(final String s) {
        return s == null;
    }

    public static final boolean isNullOrEmpty(final String s) {
        return s == null || s.isEmpty();
    }

    public static final boolean isNullOrWhiteSpace(final String s) {
        return s == null || s.trim().isEmpty();
    }

    public static String valueOrDefault(final String s, final String s2) {
        if (isNullOrEmpty(s)) {
            return s2;
        }
        return s;
    }

    public static String trimBegin(final String s, final String s2) {
        if (isNullOrEmpty(s) || isNullOrEmpty(s2)) {
            return s;
        }
        int length;
        int length2;
        int n;
        for (length = s.length(), length2 = s2.length(), n = 0; length > 0 && s.substring(n, n + length2).equals(s2); n += length2, length -= length2) {}
        return s.substring(n);
    }

    public static String trimEnd(final String s, final String s2) {
        if (isNullOrEmpty(s) || isNullOrEmpty(s2)) {
            return s;
        }
        int length;
        int length2;
        int n;
        for (length = s.length(), length2 = s2.length(), n = length - 1; length > 0 && s.substring(n - length2).equals(s2); length -= length2, n -= length2) {}
        return s.substring(0, n);
    }

    public static String trim(final String s, final String s2) {
        return trimEnd(trimBegin(s, s2), s2);
    }

    public static String replaceAll(final String s, final String s2, final IReplaceCallback replaceCallback) {
        return replaceAll(s, Pattern.compile(s2), replaceCallback);
    }

    public static String replaceAll(final String s, final Pattern pattern, final IReplaceCallback replaceCallback) {
        if (s == null) {
            return null;
        }
        final Matcher matcher = pattern.matcher(s);
        final StringBuffer sb = new StringBuffer();
        int n = 0;
        while (matcher.find()) {
            matcher.appendReplacement(sb, replaceCallback.replace(matcher.group(0), n++, matcher));
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    public static String replaceFirst(final String s, final String s2, final IReplaceCallback replaceCallback) {
        return replaceFirst(s, Pattern.compile(s2), replaceCallback);
    }

    public static String replaceFirst(final String s, final Pattern pattern, final IReplaceCallback replaceCallback) {
        if (s == null) {
            return null;
        }
        final Matcher matcher = pattern.matcher(s);
        final StringBuffer sb = new StringBuffer();
        if (matcher.find()) {
            matcher.appendReplacement(sb, replaceCallback.replace(matcher.group(0), 0, matcher));
        }
        matcher.appendTail(sb);
        return sb.toString();
    }
}
