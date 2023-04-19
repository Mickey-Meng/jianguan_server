package com.ruoyi.web.controller.jianguan.other.zlsk.stsupporter.stctk.util.encryption;

import java.io.UnsupportedEncodingException;

public class Base64Encryption
{
    private static char[] a;
    private static byte[] b;

    public static String encodeStr(final String s) throws UnsupportedEncodingException {
        return encode(s.getBytes("UTF-8"));
    }

    public static String decodeStr(final String s) throws UnsupportedEncodingException {
        return new String(decode(s), "UTF-8");
    }

    public static String encode(final byte[] array) {
        final StringBuilder sb = new StringBuilder();
        final int length = array.length;
        int i = 0;
        while (i < length) {
            final int n = array[i++] & 0xFF;
            if (i == length) {
                sb.append(Base64Encryption.a[n >>> 2]);
                sb.append(Base64Encryption.a[(n & 0x3) << 4]);
                sb.append("==");
                break;
            }
            final int n2 = array[i++] & 0xFF;
            if (i == length) {
                sb.append(Base64Encryption.a[n >>> 2]);
                sb.append(Base64Encryption.a[(n & 0x3) << 4 | (n2 & 0xF0) >>> 4]);
                sb.append(Base64Encryption.a[(n2 & 0xF) << 2]);
                sb.append("=");
                break;
            }
            final int n3 = array[i++] & 0xFF;
            sb.append(Base64Encryption.a[n >>> 2]);
            sb.append(Base64Encryption.a[(n & 0x3) << 4 | (n2 & 0xF0) >>> 4]);
            sb.append(Base64Encryption.a[(n2 & 0xF) << 2 | (n3 & 0xC0) >>> 6]);
            sb.append(Base64Encryption.a[n3 & 0x3F]);
        }
        return sb.toString();
    }

    public static byte[] decode(final String s) throws UnsupportedEncodingException {
        final StringBuilder sb = new StringBuilder();
        final byte[] bytes;
        final int length = (bytes = s.getBytes("US-ASCII")).length;
        int i = 0;
    Label_0239:
        while (i < length) {
            byte b;
            do {
                b = Base64Encryption.b[bytes[i++]];
            } while (i < length && b == -1);
            if (b == -1) {
                break;
            }
            byte b2;
            do {
                b2 = Base64Encryption.b[bytes[i++]];
            } while (i < length && b2 == -1);
            if (b2 != -1) {
                sb.append((char)(b << 2 | (b2 & 0x30) >>> 4));
                byte b3;
                while ((b3 = bytes[i++]) != 61) {
                    final byte b4 = Base64Encryption.b[b3];
                    if (i >= length || b4 != -1) {
                        if (b4 != -1) {
                            sb.append((char)((b2 & 0xF) << 4 | (b4 & 0x3C) >>> 2));
                            byte b5;
                            while ((b5 = bytes[i++]) != 61) {
                                final byte b6 = Base64Encryption.b[b5];
                                if (i >= length || b6 != -1) {
                                    if (b6 != -1) {
                                        sb.append((char)((b4 & 0x3) << 6 | b6));
                                        continue Label_0239;
                                    }
                                    break Label_0239;
                                }
                            }
                            return sb.toString().getBytes("iso8859-1");
                        }
                        break Label_0239;
                    }
                }
                return sb.toString().getBytes("iso8859-1");
            }
            break;
        }
        return sb.toString().getBytes("iso8859-1");
    }

    static {
        Base64Encryption.a = new char[] { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/' };
        Base64Encryption.b = new byte[] { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, -1, -1, 63, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, -1, -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -1, -1, -1, -1, -1 };
    }
}
