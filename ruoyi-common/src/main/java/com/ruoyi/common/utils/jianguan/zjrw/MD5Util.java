package com.ruoyi.common.utils.jianguan.zjrw;

/**
 * @author ：lin_zhixiong
 * @date ：Created in 2021/7/8 3:05 下午
 * @version: V1.0
 * @slogan: 天下风云出我辈，一入代码苦耕耘
 * @description:
 **/

import com.auth0.jwt.JWT;
import com.google.common.collect.Maps;
import com.ruoyi.common.utils.JwtUtil;

import java.security.MessageDigest;
import java.util.Map;
import java.util.UUID;

/**
 * @author Kodak
 * @version 1.0
 * @create 2019/08/04/10:30
 */
public class MD5Util {
    /***
     * MD5加码 生成32位md5码
     */
    public static String string2MD5(String inStr) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
            return "";
        }
        char[] charArray = inStr.toCharArray();
        byte[] byteArray = new byte[charArray.length];

        for (int i = 0; i < charArray.length; i++) {
            byteArray[i] = (byte) charArray[i];
        }
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16){
                hexValue.append("0");
            }
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();

    }

    /**
     * 加密解密算法 执行一次加密，两次解密
     */
    public static String convertMD5(String inStr) {

        char[] a = inStr.toCharArray();
        for (int i = 0; i < a.length; i++) {
            a[i] = (char) (a[i] ^ 't');
        }
        String s = new String(a);
        return s;

    }

    // 测试主函数
    public static void main(String args[]) {
        String s = "123456";
        System.out.println("原始：" + s);
        System.out.println("MD5后：" + string2MD5(s));
        System.out.println("加密的：" + convertMD5(s));
        System.out.println("解密的：" + convertMD5(convertMD5(s)));

        System.out.println(UUID.randomUUID().toString());

        Long userId = JwtUtil.getTokenUser("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9" +
                ".eyJleHAiOjE2NTMzMDEyODAsInVzZXIiOiJ7XCJnb25ncXVzXCI6W10sXCJpZFwiO" +
                "jEsXCJwcm9qZWN0c1wiOltdLFwicm9sZVwiOjJ9In0.4aGSU-" +
                "nCf-oK6dfNn22JJAr8VdDYsszKNJ3UR33AbKg").getId().longValue();
        System.out.println(userId);

        Map<String, Object> map = Maps.newHashMap();
        map.put("variables", "sgjl");
        System.out.println(map);


        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJle" +
                "HAiOjE2NTMyMjcyNDUsInVzZXIiOiJ7XCJnb25ncXVzXCI6W10" +
                "sXCJpZFwiOjEzNyxcInByb2plY3RzXCI6W10sXCJyb2xl" +
                "XCI6Mn0ifQ.aCiXmoOVqoZayqhl94qGTF-UatpiO_uExSSSaS3xxs8";

        String json = JWT.decode(token).getClaim("user").asString();

        System.out.println(json);

    }



}
