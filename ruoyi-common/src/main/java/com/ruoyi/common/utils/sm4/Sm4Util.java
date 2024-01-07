package com.ruoyi.common.utils.sm4;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.security.Security;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;

/**
 * 国密SM4分组密码算法工具类（对称加密）
 */
public class Sm4Util {

    private static final String ALGORITHM_NAME = "SM4";
    private static final String ALGORITHM_ECB_PKCS5PADDING = "SM4/ECB/PKCS5Padding";

    /**
     * SM4算法目前只支持128位（即密钥16字节）
     */
    private static final int DEFAULT_KEY_SIZE = 128;

    static {
        // 防止内存中出现多次BouncyCastleProvider的实例
        if (null == Security.getProvider(BouncyCastleProvider.PROVIDER_NAME)) {
            Security.addProvider(new BouncyCastleProvider());
        }
    }

    private Sm4Util() {
    }

    /**
     * 生成密钥
     * <p>建议使用org.bouncycastle.util.encoders.Hex将二进制转成HEX字符串</p>
     *
     * @return 密钥16位
     * @throws Exception 生成密钥异常
     */
    public static byte[] generateKey() throws Exception {
        KeyGenerator kg = KeyGenerator.getInstance(ALGORITHM_NAME, BouncyCastleProvider.PROVIDER_NAME);
        kg.init(DEFAULT_KEY_SIZE, new SecureRandom());
        return kg.generateKey().getEncoded();
    }

    /**
     * Hex转byte[]，两种情况，Hex长度为奇数最后一个字符会被舍去
     */
    public static byte[] hexTobytes(String hex) {
        if (hex.length() < 1) {
            return null;
        } else {
            byte[] result = new byte[hex.length() / 2];
            int j = 0;
            for(int i = 0; i < hex.length(); i+=2) {
                result[j++] = (byte)Integer.parseInt(hex.substring(i,i+2), 16);
            }
            return result;
        }
    }

    /**
     * 加密，SM4-ECB-PKCS5Padding
     *
     * @param data 要加密的明文
     * @param key  密钥16字节，使用Sm4Util.generateKey()生成
     * @return 加密后的密文
     * @throws Exception 加密异常
     */
    public static byte[] encryptEcbPkcs5Padding(byte[] data, byte[] key) throws Exception {
        return sm4(data, key, ALGORITHM_ECB_PKCS5PADDING, null, Cipher.ENCRYPT_MODE);
    }

    /**
     * 解密，SM4-ECB-PKCS5Padding
     *
     * @param data 要解密的密文
     * @param key  密钥16字节，使用Sm4Util.generateKey()生成
     * @return 解密后的明文
     * @throws Exception 解密异常
     */
    public static byte[] decryptEcbPkcs5Padding(byte[] data, byte[] key) throws Exception {
        return sm4(data, key, ALGORITHM_ECB_PKCS5PADDING, null, Cipher.DECRYPT_MODE);
    }

    /**
     * SM4对称加解密
     *
     * @param input   明文（加密模式）或密文（解密模式）
     * @param key     密钥
     * @param sm4mode sm4加密模式
     * @param iv      初始向量(ECB模式下传NULL)
     * @param mode    Cipher.ENCRYPT_MODE - 加密；Cipher.DECRYPT_MODE - 解密
     * @return 密文（加密模式）或明文（解密模式）
     * @throws Exception 加解密异常
     */
    private static byte[] sm4(byte[] input, byte[] key, String sm4mode, byte[] iv, int mode)
            throws Exception {
        IvParameterSpec ivParameterSpec = null;
        if (null != iv) {
            ivParameterSpec = new IvParameterSpec(iv);
        }
        SecretKeySpec sm4Key = new SecretKeySpec(key, ALGORITHM_NAME);
        Cipher cipher = Cipher.getInstance(sm4mode, BouncyCastleProvider.PROVIDER_NAME);
        if (null == ivParameterSpec) {
            cipher.init(mode, sm4Key);
        } else {
            cipher.init(mode, sm4Key, ivParameterSpec);
        }
        return cipher.doFinal(input);
    }

    /**
     * parseByte2HexStr
     */
    public static String parseByte2HexStr(byte buf[]) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }

    public static void main(String[] args) throws Exception {
        // 需要加密的字符，【&】符号是必要的，用来解密后区分账号和密码
        String txt = "userName&password";
        // 加密和解密使用的私钥
        byte[] key = hexTobytes("51d95b1dc43a9faaad0570f81c755fcc");
        // 加密
        byte[] output = Sm4Util.encryptEcbPkcs5Padding(txt.getBytes(StandardCharsets.UTF_8), key);
        // 提供给前端的加密字符
        String hex = Hex.toHexString(output);
        System.out.println("加密后结果 ==>"+hex);
        // 解密
        byte[] input = Hex.decode(hex);
        output = Sm4Util.decryptEcbPkcs5Padding(input, key);
        String s = new String(output, StandardCharsets.UTF_8);
        System.out.println("解密后结果 ==>"+s);
        System.out.println("key is ==>"+Hex.toHexString(key));
    }

    public static String encrypt(String encryptStr) throws Exception {
        byte[] key = hexTobytes("51d95b1dc43a9faaad0570f81c755fcc");
        // 加密
        byte[] output = Sm4Util.encryptEcbPkcs5Padding(encryptStr.getBytes(StandardCharsets.UTF_8), key);
        // 提供给前端的加密字符
        String hex = Hex.toHexString(output);
        return hex;
    }
}