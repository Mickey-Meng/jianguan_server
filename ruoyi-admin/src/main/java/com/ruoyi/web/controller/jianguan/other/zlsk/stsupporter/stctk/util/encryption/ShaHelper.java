package com.ruoyi.web.controller.jianguan.other.zlsk.stsupporter.stctk.util.encryption;

import org.apache.commons.codec.digest.*;

public class ShaHelper
{
    public static String sha1Hex(final byte[] array) {
        return DigestUtils.sha1Hex(array);
    }

    public static String sha1Hex(final String s) {
        return DigestUtils.sha1Hex(s);
    }
}
