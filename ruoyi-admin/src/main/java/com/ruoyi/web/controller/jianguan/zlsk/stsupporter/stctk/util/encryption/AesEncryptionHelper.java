package com.ruoyi.web.controller.jianguan.zlsk.stsupporter.stctk.util.encryption;

public class AesEncryptionHelper
{
    private static final byte[] a;

    public static String aesDecryption(final String s) {
        String decrypt = "";
        try {
            decrypt = AesEncryption.decrypt("\u55b5\u55b5\u55b5", s);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return decrypt;
    }

    public static byte[] aesDecryption(final byte[] array) {
        try {
            return AesEncryption.decryptByte(AesEncryptionHelper.a, array);
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static String aesEncryption(final String s) {
        String encrypt = "";
        try {
            encrypt = AesEncryption.encrypt("\u55b5\u55b5\u55b5", s);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return encrypt;
    }

    public static byte[] aesEncryption(final byte[] array) {
        try {
            return AesEncryption.encryptByte(AesEncryptionHelper.a, array);
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    static {
        a = new byte[] { -27, -106, -75, -27, -106, -75, -27, -106, -75 };
    }
}
