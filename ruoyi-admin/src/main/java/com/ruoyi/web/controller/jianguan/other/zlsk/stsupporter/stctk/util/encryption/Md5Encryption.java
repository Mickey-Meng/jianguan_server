package com.ruoyi.web.controller.jianguan.other.zlsk.stsupporter.stctk.util.encryption;

import java.security.*;

public class Md5Encryption
{
    public static String getMD5(final String s) {
        try {
            final MessageDigest instance;
            (instance = MessageDigest.getInstance("MD5")).update(s.getBytes());
            final StringBuilder sb = new StringBuilder();
            byte[] digest;
            for (int length = (digest = instance.digest()).length, i = 0; i < length; ++i) {
                final byte b = digest[i];
                sb.append(Integer.toHexString(b >> 4 & 0xF));
                sb.append(Integer.toHexString(b & 0xF));
            }
            return sb.toString();
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
