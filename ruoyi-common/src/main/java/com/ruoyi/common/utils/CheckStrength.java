package com.ruoyi.common.utils;

public class CheckStrength {
    //判断输入是否为数字
    public static boolean isNumber(char s) {
        return s >= '0' && s <= '9';
    }
 
    //判断输入是否为大写字母
    public static boolean isUpper(char s) {
        return s >= 'A' && s <= 'Z';
    }
 
    //判断输入是否为小写字母
    public static boolean isLower(char s) {
        return s >= 'a' && s <= 'z';
    }
 
    //判断输入是否为特殊字符
    public static boolean isCharacter(char s) {
        return (s < 'a' || s > 'z') && (s < 'A' || s > 'Z') && (s < '0' || s > '9');
    }
 
    //判断输入长度是否合法
    public static boolean lengthOK(String s, int length) {
        return s.length() >= length;
    }
 
    //判断是否有连续相同输入
    public static boolean isSample(char[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (i < arr.length - 1 && (arr[i] == arr[i + 1])) {
                return false;
            }
        }
        return true;
    }
 
    //判断密码是否是强密码
    public static boolean strongPasswordChecker(String password) {
        //以五个boolean变量记录是否包含其指定字符，若包含，将对应值置为true
        boolean isSample = false, isNumber = false, isUpper = false, isLower = false, isCharacter = false;
        //首先判断长度，若长度不符，return false
        if (lengthOK(password, 6)) {
            //将输入转为数组，便于操作
            char[] arr = password.toCharArray();
            //判断是否有连续相同输入，若有则isSample=false
            isSample = isSample(arr);
            for (char c : arr) {
                //判断是否包含数字，若有则isNumber=true
                if (!isNumber) {
                    isNumber = isNumber(c);
                }
                //判断是否包含大写字母，若有则isUpper=true
                if (!isUpper) {
                    isUpper = isUpper(c);
                }
                //判断是否包含小写字母，若有则isLower=true
                if (!isLower) {
                    isLower = isLower(c);
                }
                //判断是否包含特殊字符，若有则isCharacter=true
                /**
                if (!isCharacter) {
                    isCharacter = isCharacter(c);
                }
                 */
            }
        }
        //若满足强密码所有条件，则返回true
        System.out.println("isNumber:" + isNumber + " || isUpper:" + isUpper + " || isLower:" + isLower + " || isSample:" + isSample);
        return isNumber && isUpper && isLower && isSample;
    }

    public static void main(String[] args) {
        boolean isStrength = CheckStrength.strongPasswordChecker("1a2W3");
        System.out.println("是否强密码:" + isStrength);
    }
}
