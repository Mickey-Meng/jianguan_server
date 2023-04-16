package com.ruoyi.web.controller.jianguan.zlsk.stsupporter.stctk.util.encryption;

import org.bouncycastle.util.encoders.*;
import javax.crypto.*;
import javax.crypto.spec.*;
import java.io.UnsupportedEncodingException;
import java.security.spec.*;
import org.bouncycastle.util.*;
import org.bouncycastle.jce.provider.*;
import org.springframework.security.crypto.codec.Hex;

import java.security.*;
import java.util.Arrays;

public class AesCbcHelper
{


    public static byte[] encryptPkcs5Padding(byte[] array, byte[] array2, byte[] array3) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        final byte[] array4 = array;
        final byte[] array5 = array2;
        array3 = array3;
        array2 = array5;
        array = array4;
        final Cipher instance = Cipher.getInstance("AES/CBC/PKCS5Padding");
        instance.init(1, new SecretKeySpec(array2, "AES"), new IvParameterSpec(array3));
        return instance.doFinal(array);
    }


    public static byte[] decryptPkcs5Padding(byte[] array, byte[] array2, byte[] array3) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        final byte[] array4 = array;
        final byte[] array5 = array2;
        array3 = array3;
        array2 = array5;
        array = array4;
        final Cipher instance = Cipher.getInstance("AES/CBC/PKCS5Padding");
        instance.init(2, new SecretKeySpec(array2, "AES"), new IvParameterSpec(array3));
        return instance.doFinal(array);
    }


    public static byte[] encryptPkcs7Padding(byte[] array, byte[] array2, byte[] array3) throws NoSuchPaddingException, NoSuchAlgorithmException, NoSuchProviderException, InvalidAlgorithmParameterException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        final byte[] array4 = array;
        final byte[] array5 = array2;
        array3 = array3;
        array2 = array5;
        array = array4;
        if (array2.length % 16 != 0) {
            final byte[] array6;
            Arrays.fill(array6 = new byte[array2.length / 16 + ((array2.length % 16 != 0) ? 1 : 0) << 4], (byte)0);
            System.arraycopy(array2, 0, array6, 0, array2.length);
            array2 = array6;
        }
        Security.addProvider((Provider)new BouncyCastleProvider());
        final Cipher instance = Cipher.getInstance("AES/CBC/PKCS7Padding", "BC");
        instance.init(1, new SecretKeySpec(array2, "AES"), new IvParameterSpec(array3));
        return instance.doFinal(array);
    }

    public static byte[] decryptPkcs7Padding(byte[] array, byte[] array2, byte[] array3) throws NoSuchPaddingException, NoSuchAlgorithmException, NoSuchProviderException, InvalidAlgorithmParameterException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        final byte[] array4 = array;
        final byte[] array5 = array2;
        array3 = array3;
        array2 = array5;
        array = array4;
        if (array2.length % 16 != 0) {
            final byte[] array6;
            Arrays.fill(array6 = new byte[array2.length / 16 + ((array2.length % 16 != 0) ? 1 : 0) << 4], (byte)0);
            System.arraycopy(array2, 0, array6, 0, array2.length);
            array2 = array6;
        }
        Security.addProvider((Provider)new BouncyCastleProvider());
        final Cipher instance = Cipher.getInstance("AES/CBC/PKCS7Padding", "BC");
        instance.init(2, new SecretKeySpec(array2, "AES"), new IvParameterSpec(array3));
        return instance.doFinal(array);
    }
}
