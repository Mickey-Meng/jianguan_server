package com.ruoyi.web.controller.jianguan.other.zlsk.stsupporter.stctk.util.encryption;

import javax.crypto.*;
import javax.crypto.spec.*;
import java.security.*;

public class AesEncryption
{
    public static String encrypt(final String s, final String s2) throws NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        return toHex(encrypt(a(s.getBytes()), s2.getBytes()));
    }

    public static byte[] encryptByte(final byte[] array, final byte[] array2) throws NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        return encrypt(a(array), array2);
    }

    public static String decrypt(final String s, final String s2) throws NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        return new String(decrypt(a(s.getBytes()), toByte(s2)));
    }

    public static byte[] decryptByte(final byte[] array, final byte[] array2) throws NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        return decrypt(a(array), array2);
    }

    private static byte[] a(final byte[] seed) throws NoSuchAlgorithmException {
        final KeyGenerator instance = KeyGenerator.getInstance("AES");
        final SecureRandom instance2;
        (instance2 = SecureRandom.getInstance("SHA1PRNG")).setSeed(seed);
        instance.init(128, instance2);
        return instance.generateKey().getEncoded();
    }

    public static byte[] encrypt(final byte[] array, final byte[] array2) throws NoSuchPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        final Cipher instance;
        (instance = Cipher.getInstance("AES")).init(1, new SecretKeySpec(array, "AES"));
        return instance.doFinal(array2);
    }

    public static byte[] decrypt(final byte[] array, final byte[] array2) throws NoSuchPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        final Cipher instance;
        (instance = Cipher.getInstance("AES")).init(2, new SecretKeySpec(array, "AES"));
        return instance.doFinal(array2);
    }

    public static String toHex(final String s) {
        return toHex(s.getBytes());
    }

    public static String fromHex(final String s) {
        return new String(toByte(s));
    }

    public static byte[] toByte(final String s) {
        final int n;
        final byte[] array = new byte[n = s.length() / 2];
        for (int i = 0; i < n; ++i) {
            array[i] = Integer.valueOf(s.substring(2 * i, 2 * i + 2), 16).byteValue();
        }
        return array;
    }

    public static String toHex(final byte[] array) {
        if (array == null) {
            return "";
        }
        final StringBuffer sb = new StringBuffer(2 * array.length);
        for (final byte b : array) {
            sb.append("0123456789ABCDEF".charAt(b >> 4 & 0xF)).append("0123456789ABCDEF".charAt(b & 0xF));
        }
        return sb.toString();
    }
}
