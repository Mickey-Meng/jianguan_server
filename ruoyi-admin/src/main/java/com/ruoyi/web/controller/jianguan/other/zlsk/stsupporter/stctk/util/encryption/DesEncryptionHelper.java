package com.ruoyi.web.controller.jianguan.other.zlsk.stsupporter.stctk.util.encryption;

public class DesEncryptionHelper
{
    public static String desDecryption(final String s) {
        return hexStr2Str(toHex(new DesEncryption().decodeStr(s, s.length())));
    }

    public static byte[] desDecryption(final byte[] array, final int n) {
        return new DesEncryption().decode(array, n);
    }

    public static String desEncryption(final String s) {
        return hexStr2Str(new DesEncryption().encodeStr(s, s.length()));
    }

    public static byte[] desEncryption(final byte[] array, final int n) {
        return new DesEncryption().encode(array, n);
    }

    public static String toHex(final String s) {
        final String s2 = "0123456789ABCDEF";
        final byte[] bytes = s.getBytes();
        final StringBuilder sb = new StringBuilder(bytes.length << 1);
        final byte[] array = bytes;
        for (int length = bytes.length, i = 0; i < length; ++i) {
            final byte b = array[i];
            final char char1 = s2.charAt((b & 0xF0) >> 4);
            final char char2 = s2.charAt(b & 0xF);
            if (char1 != '0' || char2 != '0') {
                sb.append(char1);
                sb.append(char2);
            }
        }
        return sb.toString();
    }

    public static String hexStr2Str(final String s) {
        final String s2 = "0123456789ABCDEF";
        final char[] charArray = s.toCharArray();
        final byte[] array = new byte[s.length() / 2];
        for (int i = 0; i < array.length; ++i) {
            array[i] = (byte)((s2.indexOf(charArray[2 * i]) << 4) + s2.indexOf(charArray[2 * i + 1]));
        }
        return new String(array);
    }
}
