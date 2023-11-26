package com.ruoyi.common.utils.aes;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;


public class AesUtils {

    public static SecretKey generateAESKey() throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(128);
        return keyGenerator.generateKey();
    }

    public static String encrypt(String plainText, SecretKey secretKey) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedBytes = cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    public static String decrypt(String encryptedText, SecretKey secretKey) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedText));
        return new String(decryptedBytes, StandardCharsets.UTF_8);
    }


    public static void main(String[] args) {
        try {
            SecretKey secretKey = generateAESKey();
            String plainText = "这是一个待加密的字符串";
            String encryptedText = encrypt(plainText, secretKey);
            String decryptedText = decrypt(encryptedText, secretKey);

            System.out.println("原始文本： " + plainText);
            System.out.println("加密后的文本： " + encryptedText);
            System.out.println("解密后的文本： " + decryptedText);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
