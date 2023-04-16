package com.ruoyi.web.controller.jianguan.zlsk.stframebase.util;

import java.util.*;

public class STEncryption
{
    public String strEnc(final String data, final String firstKey, final String secondKey, final String thirdKey) {
        final int leng = data.length();
        String encData = "";
        List firstKeyBt = null;
        List secondKeyBt = null;
        List thirdKeyBt = null;
        int firstLength = 0;
        int secondLength = 0;
        int thirdLength = 0;
        if (firstKey != null && firstKey != "") {
            firstKeyBt = this.getKeyBytes(firstKey);
            firstLength = firstKeyBt.size();
        }
        if (secondKey != null && secondKey != "") {
            secondKeyBt = this.getKeyBytes(secondKey);
            secondLength = secondKeyBt.size();
        }
        if (thirdKey != null && thirdKey != "") {
            thirdKeyBt = this.getKeyBytes(thirdKey);
            thirdLength = thirdKeyBt.size();
        }
        if (leng > 0) {
            if (leng < 4) {
                final int[] bt = this.strToBt(data);
                int[] encByte = null;
                if (firstKey != null && firstKey != "" && secondKey != null && secondKey != "" && thirdKey != null && thirdKey != "") {
                    int[] tempBt = bt;
                    for (int x = 0; x < firstLength; ++x) {
                        tempBt = this.enc(tempBt, (int[]) firstKeyBt.get(x));
                    }
                    for (int y = 0; y < secondLength; ++y) {
                        tempBt = this.enc(tempBt, (int[]) secondKeyBt.get(y));
                    }
                    for (int z = 0; z < thirdLength; ++z) {
                        tempBt = this.enc(tempBt, (int[]) thirdKeyBt.get(z));
                    }
                    encByte = tempBt;
                }
                else if (firstKey != null && firstKey != "" && secondKey != null && secondKey != "") {
                    int[] tempBt = bt;
                    for (int x = 0; x < firstLength; ++x) {
                        tempBt = this.enc(tempBt, (int[]) firstKeyBt.get(x));
                    }
                    for (int y = 0; y < secondLength; ++y) {
                        tempBt = this.enc(tempBt, (int[]) secondKeyBt.get(y));
                    }
                    encByte = tempBt;
                }
                else if (firstKey != null && firstKey != "") {
                    int x = 0;
                    int[] tempBt = bt;
                    for (x = 0; x < firstLength; ++x) {
                        tempBt = this.enc(tempBt, (int[]) firstKeyBt.get(x));
                    }
                    encByte = tempBt;
                }
                encData = this.bt64ToHex(encByte);
            }
            else {
                final int iterator = leng / 4;
                final int remainder = leng % 4;
                int i;
                String tempData;
                int[] tempByte;
                int[] encByte2;
                int[] tempBt2;
                int x2;
                int y2;
                int z2;
                for (i = 0, i = 0; i < iterator; ++i) {
                    tempData = data.substring(i * 4 + 0, i * 4 + 4);
                    tempByte = this.strToBt(tempData);
                    encByte2 = null;
                    if (firstKey != null && firstKey != "" && secondKey != null && secondKey != "" && thirdKey != null && thirdKey != "") {
                        tempBt2 = tempByte;
                        for (x2 = 0; x2 < firstLength; ++x2) {
                            tempBt2 = this.enc(tempBt2, (int[]) firstKeyBt.get(x2));
                        }
                        for (y2 = 0; y2 < secondLength; ++y2) {
                            tempBt2 = this.enc(tempBt2, (int[]) secondKeyBt.get(y2));
                        }
                        for (z2 = 0; z2 < thirdLength; ++z2) {
                            tempBt2 = this.enc(tempBt2, (int[]) thirdKeyBt.get(z2));
                        }
                        encByte2 = tempBt2;
                    }
                    else if (firstKey != null && firstKey != "" && secondKey != null && secondKey != "") {
                        tempBt2 = tempByte;
                        for (x2 = 0; x2 < firstLength; ++x2) {
                            tempBt2 = this.enc(tempBt2, (int[]) firstKeyBt.get(x2));
                        }
                        for (y2 = 0; y2 < secondLength; ++y2) {
                            tempBt2 = this.enc(tempBt2, (int[]) secondKeyBt.get(y2));
                        }
                        encByte2 = tempBt2;
                    }
                    else if (firstKey != null && firstKey != "") {
                        tempBt2 = tempByte;
                        for (x2 = 0; x2 < firstLength; ++x2) {
                            tempBt2 = this.enc(tempBt2, (int[]) firstKeyBt.get(x2));
                        }
                        encByte2 = tempBt2;
                    }
                    encData += this.bt64ToHex(encByte2);
                }
                if (remainder > 0) {
                    final String remainderData = data.substring(iterator * 4 + 0, leng);
                    tempByte = this.strToBt(remainderData);
                    encByte2 = null;
                    if (firstKey != null && firstKey != "" && secondKey != null && secondKey != "" && thirdKey != null && thirdKey != "") {
                        tempBt2 = tempByte;
                        for (x2 = 0; x2 < firstLength; ++x2) {
                            tempBt2 = this.enc(tempBt2, (int[]) firstKeyBt.get(x2));
                        }
                        for (y2 = 0; y2 < secondLength; ++y2) {
                            tempBt2 = this.enc(tempBt2, (int[]) secondKeyBt.get(y2));
                        }
                        for (z2 = 0; z2 < thirdLength; ++z2) {
                            tempBt2 = this.enc(tempBt2, (int[]) thirdKeyBt.get(z2));
                        }
                        encByte2 = tempBt2;
                    }
                    else if (firstKey != null && firstKey != "" && secondKey != null && secondKey != "") {
                        tempBt2 = tempByte;
                        for (x2 = 0; x2 < firstLength; ++x2) {
                            tempBt2 = this.enc(tempBt2, (int[]) firstKeyBt.get(x2));
                        }
                        for (y2 = 0; y2 < secondLength; ++y2) {
                            tempBt2 = this.enc(tempBt2, (int[]) secondKeyBt.get(y2));
                        }
                        encByte2 = tempBt2;
                    }
                    else if (firstKey != null && firstKey != "") {
                        tempBt2 = tempByte;
                        for (x2 = 0; x2 < firstLength; ++x2) {
                            tempBt2 = this.enc(tempBt2, (int[]) firstKeyBt.get(x2));
                        }
                        encByte2 = tempBt2;
                    }
                    encData += this.bt64ToHex(encByte2);
                }
            }
        }
        return encData;
    }

    public String strDec(final String data, final String firstKey, final String secondKey, final String thirdKey) {
        final int leng = data.length();
        String decStr = "";
        List firstKeyBt = null;
        List secondKeyBt = null;
        List thirdKeyBt = null;
        int firstLength = 0;
        int secondLength = 0;
        int thirdLength = 0;
        if (firstKey != null && firstKey != "") {
            firstKeyBt = this.getKeyBytes(firstKey);
            firstLength = firstKeyBt.size();
        }
        if (secondKey != null && secondKey != "") {
            secondKeyBt = this.getKeyBytes(secondKey);
            secondLength = secondKeyBt.size();
        }
        if (thirdKey != null && thirdKey != "") {
            thirdKeyBt = this.getKeyBytes(thirdKey);
            thirdLength = thirdKeyBt.size();
        }
        int iterator;
        int i;
        String tempData;
        String strByte;
        int[] intByte;
        int j;
        int[] decByte;
        int[] tempBt;
        int x;
        int y;
        int z;
        for (iterator = leng / 16, i = 0, i = 0; i < iterator; ++i) {
            tempData = data.substring(i * 16 + 0, i * 16 + 16);
            strByte = this.hexToBt64(tempData);
            intByte = new int[64];
            for (j = 0, j = 0; j < 64; ++j) {
                intByte[j] = Integer.parseInt(strByte.substring(j, j + 1));
            }
            decByte = null;
            if (firstKey != null && firstKey != "" && secondKey != null && secondKey != "" && thirdKey != null && thirdKey != "") {
                tempBt = intByte;
                for (x = thirdLength - 1; x >= 0; --x) {
                    tempBt = this.dec(tempBt, (int[]) thirdKeyBt.get(x));
                }
                for (y = secondLength - 1; y >= 0; --y) {
                    tempBt = this.dec(tempBt, (int[]) secondKeyBt.get(y));
                }
                for (z = firstLength - 1; z >= 0; --z) {
                    tempBt = this.dec(tempBt, (int[]) firstKeyBt.get(z));
                }
                decByte = tempBt;
            }
            else if (firstKey != null && firstKey != "" && secondKey != null && secondKey != "") {
                tempBt = intByte;
                for (x = secondLength - 1; x >= 0; --x) {
                    tempBt = this.dec(tempBt, (int[]) secondKeyBt.get(x));
                }
                for (y = firstLength - 1; y >= 0; --y) {
                    tempBt = this.dec(tempBt, (int[]) firstKeyBt.get(y));
                }
                decByte = tempBt;
            }
            else if (firstKey != null && firstKey != "") {
                tempBt = intByte;
                for (x = firstLength - 1; x >= 0; --x) {
                    tempBt = this.dec(tempBt, (int[]) firstKeyBt.get(x));
                }
                decByte = tempBt;
            }
            decStr += this.byteToString(decByte);
        }
        return decStr;
    }

    public List getKeyBytes(final String key) {
        final List keyBytes = new ArrayList();
        final int leng = key.length();
        final int iterator = leng / 4;
        final int remainder = leng % 4;
        int i;
        for (i = 0, i = 0; i < iterator; ++i) {
            keyBytes.add(i, this.strToBt(key.substring(i * 4 + 0, i * 4 + 4)));
        }
        if (remainder > 0) {
            keyBytes.add(i, this.strToBt(key.substring(i * 4 + 0, leng)));
        }
        return keyBytes;
    }

    public int[] strToBt(final String str) {
        final int leng = str.length();
        final int[] bt = new int[64];
        if (leng < 4) {
            int i = 0;
            int j = 0;
            int p = 0;
            int q = 0;
            for (i = 0; i < leng; ++i) {
                final int k = str.charAt(i);
                for (j = 0; j < 16; ++j) {
                    int pow = 1;
                    int m;
                    for (m = 0, m = 15; m > j; --m) {
                        pow *= 2;
                    }
                    bt[16 * i + j] = k / pow % 2;
                }
            }
            for (p = leng; p < 4; ++p) {
                final int k = 0;
                for (q = 0; q < 16; ++q) {
                    int pow = 1;
                    int m;
                    for (m = 0, m = 15; m > q; --m) {
                        pow *= 2;
                    }
                    bt[16 * p + q] = k / pow % 2;
                }
            }
        }
        else {
            for (int i = 0; i < 4; ++i) {
                final int l = str.charAt(i);
                for (int j2 = 0; j2 < 16; ++j2) {
                    int pow2 = 1;
                    for (int m2 = 15; m2 > j2; --m2) {
                        pow2 *= 2;
                    }
                    bt[16 * i + j2] = l / pow2 % 2;
                }
            }
        }
        return bt;
    }

    public String bt4ToHex(final String binary) {
        String hex = "";
        if (binary.equalsIgnoreCase("0000")) {
            hex = "0";
        }
        else if (binary.equalsIgnoreCase("0001")) {
            hex = "1";
        }
        else if (binary.equalsIgnoreCase("0010")) {
            hex = "2";
        }
        else if (binary.equalsIgnoreCase("0011")) {
            hex = "3";
        }
        else if (binary.equalsIgnoreCase("0100")) {
            hex = "4";
        }
        else if (binary.equalsIgnoreCase("0101")) {
            hex = "5";
        }
        else if (binary.equalsIgnoreCase("0110")) {
            hex = "6";
        }
        else if (binary.equalsIgnoreCase("0111")) {
            hex = "7";
        }
        else if (binary.equalsIgnoreCase("1000")) {
            hex = "8";
        }
        else if (binary.equalsIgnoreCase("1001")) {
            hex = "9";
        }
        else if (binary.equalsIgnoreCase("1010")) {
            hex = "A";
        }
        else if (binary.equalsIgnoreCase("1011")) {
            hex = "B";
        }
        else if (binary.equalsIgnoreCase("1100")) {
            hex = "C";
        }
        else if (binary.equalsIgnoreCase("1101")) {
            hex = "D";
        }
        else if (binary.equalsIgnoreCase("1110")) {
            hex = "E";
        }
        else if (binary.equalsIgnoreCase("1111")) {
            hex = "F";
        }
        return hex;
    }

    public String hexToBt4(final String hex) {
        String binary = "";
        if (hex.equalsIgnoreCase("0")) {
            binary = "0000";
        }
        else if (hex.equalsIgnoreCase("1")) {
            binary = "0001";
        }
        if (hex.equalsIgnoreCase("2")) {
            binary = "0010";
        }
        if (hex.equalsIgnoreCase("3")) {
            binary = "0011";
        }
        if (hex.equalsIgnoreCase("4")) {
            binary = "0100";
        }
        if (hex.equalsIgnoreCase("5")) {
            binary = "0101";
        }
        if (hex.equalsIgnoreCase("6")) {
            binary = "0110";
        }
        if (hex.equalsIgnoreCase("7")) {
            binary = "0111";
        }
        if (hex.equalsIgnoreCase("8")) {
            binary = "1000";
        }
        if (hex.equalsIgnoreCase("9")) {
            binary = "1001";
        }
        if (hex.equalsIgnoreCase("A")) {
            binary = "1010";
        }
        if (hex.equalsIgnoreCase("B")) {
            binary = "1011";
        }
        if (hex.equalsIgnoreCase("C")) {
            binary = "1100";
        }
        if (hex.equalsIgnoreCase("D")) {
            binary = "1101";
        }
        if (hex.equalsIgnoreCase("E")) {
            binary = "1110";
        }
        if (hex.equalsIgnoreCase("F")) {
            binary = "1111";
        }
        return binary;
    }

    public String byteToString(final int[] byteData) {
        String str = "";
        for (int i = 0; i < 4; ++i) {
            int count = 0;
            for (int j = 0; j < 16; ++j) {
                int pow = 1;
                for (int m = 15; m > j; --m) {
                    pow *= 2;
                }
                count += byteData[16 * i + j] * pow;
            }
            if (count != 0) {
                str = str + "" + (char)count;
            }
        }
        return str;
    }

    public String bt64ToHex(final int[] byteData) {
        String hex = "";
        for (int i = 0; i < 16; ++i) {
            String bt = "";
            for (int j = 0; j < 4; ++j) {
                bt += byteData[i * 4 + j];
            }
            hex += this.bt4ToHex(bt);
        }
        return hex;
    }

    public String hexToBt64(final String hex) {
        String binary = "";
        for (int i = 0; i < 16; ++i) {
            binary += this.hexToBt4(hex.substring(i, i + 1));
        }
        return binary;
    }

    public int[] enc(final int[] dataByte, final int[] keyByte) {
        final int[][] keys = this.generateKeys(keyByte);
        final int[] ipByte = this.initPermute(dataByte);
        final int[] ipLeft = new int[32];
        final int[] ipRight = new int[32];
        final int[] tempLeft = new int[32];
        int i = 0;
        int j = 0;
        int k = 0;
        int m = 0;
        int n = 0;
        for (k = 0; k < 32; ++k) {
            ipLeft[k] = ipByte[k];
            ipRight[k] = ipByte[32 + k];
        }
        for (i = 0; i < 16; ++i) {
            for (j = 0; j < 32; ++j) {
                tempLeft[j] = ipLeft[j];
                ipLeft[j] = ipRight[j];
            }
            final int[] key = new int[48];
            for (m = 0; m < 48; ++m) {
                key[m] = keys[i][m];
            }
            final int[] tempRight = this.xor(this.pPermute(this.sBoxPermute(this.xor(this.expandPermute(ipRight), key))), tempLeft);
            for (n = 0; n < 32; ++n) {
                ipRight[n] = tempRight[n];
            }
        }
        final int[] finalData = new int[64];
        for (i = 0; i < 32; ++i) {
            finalData[i] = ipRight[i];
            finalData[32 + i] = ipLeft[i];
        }
        return this.finallyPermute(finalData);
    }

    public int[] dec(final int[] dataByte, final int[] keyByte) {
        final int[][] keys = this.generateKeys(keyByte);
        final int[] ipByte = this.initPermute(dataByte);
        final int[] ipLeft = new int[32];
        final int[] ipRight = new int[32];
        final int[] tempLeft = new int[32];
        int i = 0;
        int j = 0;
        int k = 0;
        int m = 0;
        int n = 0;
        for (k = 0; k < 32; ++k) {
            ipLeft[k] = ipByte[k];
            ipRight[k] = ipByte[32 + k];
        }
        for (i = 15; i >= 0; --i) {
            for (j = 0; j < 32; ++j) {
                tempLeft[j] = ipLeft[j];
                ipLeft[j] = ipRight[j];
            }
            final int[] key = new int[48];
            for (m = 0; m < 48; ++m) {
                key[m] = keys[i][m];
            }
            final int[] tempRight = this.xor(this.pPermute(this.sBoxPermute(this.xor(this.expandPermute(ipRight), key))), tempLeft);
            for (n = 0; n < 32; ++n) {
                ipRight[n] = tempRight[n];
            }
        }
        final int[] finalData = new int[64];
        for (i = 0; i < 32; ++i) {
            finalData[i] = ipRight[i];
            finalData[32 + i] = ipLeft[i];
        }
        return this.finallyPermute(finalData);
    }

    public int[] initPermute(final int[] originalData) {
        final int[] ipByte = new int[64];
        int i;
        int m;
        int n;
        int j;
        int k;
        for (i = 0, m = 1, n = 0, i = 0, m = 1, n = 0; i < 4; ++i, m += 2, n += 2) {
            for (j = 7, k = 0; j >= 0; --j, ++k) {
                ipByte[i * 8 + k] = originalData[j * 8 + m];
                ipByte[i * 8 + k + 32] = originalData[j * 8 + n];
            }
        }
        return ipByte;
    }

    public int[] expandPermute(final int[] rightData) {
        final int[] epByte = new int[48];
        for (int i = 0; i < 8; ++i) {
            if (i == 0) {
                epByte[i * 6 + 0] = rightData[31];
            }
            else {
                epByte[i * 6 + 0] = rightData[i * 4 - 1];
            }
            epByte[i * 6 + 1] = rightData[i * 4 + 0];
            epByte[i * 6 + 2] = rightData[i * 4 + 1];
            epByte[i * 6 + 3] = rightData[i * 4 + 2];
            epByte[i * 6 + 4] = rightData[i * 4 + 3];
            if (i == 7) {
                epByte[i * 6 + 5] = rightData[0];
            }
            else {
                epByte[i * 6 + 5] = rightData[i * 4 + 4];
            }
        }
        return epByte;
    }

    public int[] xor(final int[] byteOne, final int[] byteTwo) {
        final int[] xorByte = new int[byteOne.length];
        for (int i = 0; i < byteOne.length; ++i) {
            xorByte[i] = (byteOne[i] ^ byteTwo[i]);
        }
        return xorByte;
    }

    public int[] sBoxPermute(final int[] expandByte) {
        final int[] sBoxByte = new int[32];
        String binary = "";
        final int[][] s1 = { { 14, 4, 13, 1, 2, 15, 11, 8, 3, 10, 6, 12, 5, 9, 0, 7 }, { 0, 15, 7, 4, 14, 2, 13, 1, 10, 6, 12, 11, 9, 5, 3, 8 }, { 4, 1, 14, 8, 13, 6, 2, 11, 15, 12, 9, 7, 3, 10, 5, 0 }, { 15, 12, 8, 2, 4, 9, 1, 7, 5, 11, 3, 14, 10, 0, 6, 13 } };
        final int[][] s2 = { { 15, 1, 8, 14, 6, 11, 3, 4, 9, 7, 2, 13, 12, 0, 5, 10 }, { 3, 13, 4, 7, 15, 2, 8, 14, 12, 0, 1, 10, 6, 9, 11, 5 }, { 0, 14, 7, 11, 10, 4, 13, 1, 5, 8, 12, 6, 9, 3, 2, 15 }, { 13, 8, 10, 1, 3, 15, 4, 2, 11, 6, 7, 12, 0, 5, 14, 9 } };
        final int[][] s3 = { { 10, 0, 9, 14, 6, 3, 15, 5, 1, 13, 12, 7, 11, 4, 2, 8 }, { 13, 7, 0, 9, 3, 4, 6, 10, 2, 8, 5, 14, 12, 11, 15, 1 }, { 13, 6, 4, 9, 8, 15, 3, 0, 11, 1, 2, 12, 5, 10, 14, 7 }, { 1, 10, 13, 0, 6, 9, 8, 7, 4, 15, 14, 3, 11, 5, 2, 12 } };
        final int[][] s4 = { { 7, 13, 14, 3, 0, 6, 9, 10, 1, 2, 8, 5, 11, 12, 4, 15 }, { 13, 8, 11, 5, 6, 15, 0, 3, 4, 7, 2, 12, 1, 10, 14, 9 }, { 10, 6, 9, 0, 12, 11, 7, 13, 15, 1, 3, 14, 5, 2, 8, 4 }, { 3, 15, 0, 6, 10, 1, 13, 8, 9, 4, 5, 11, 12, 7, 2, 14 } };
        final int[][] s5 = { { 2, 12, 4, 1, 7, 10, 11, 6, 8, 5, 3, 15, 13, 0, 14, 9 }, { 14, 11, 2, 12, 4, 7, 13, 1, 5, 0, 15, 10, 3, 9, 8, 6 }, { 4, 2, 1, 11, 10, 13, 7, 8, 15, 9, 12, 5, 6, 3, 0, 14 }, { 11, 8, 12, 7, 1, 14, 2, 13, 6, 15, 0, 9, 10, 4, 5, 3 } };
        final int[][] s6 = { { 12, 1, 10, 15, 9, 2, 6, 8, 0, 13, 3, 4, 14, 7, 5, 11 }, { 10, 15, 4, 2, 7, 12, 9, 5, 6, 1, 13, 14, 0, 11, 3, 8 }, { 9, 14, 15, 5, 2, 8, 12, 3, 7, 0, 4, 10, 1, 13, 11, 6 }, { 4, 3, 2, 12, 9, 5, 15, 10, 11, 14, 1, 7, 6, 0, 8, 13 } };
        final int[][] s7 = { { 4, 11, 2, 14, 15, 0, 8, 13, 3, 12, 9, 7, 5, 10, 6, 1 }, { 13, 0, 11, 7, 4, 9, 1, 10, 14, 3, 5, 12, 2, 15, 8, 6 }, { 1, 4, 11, 13, 12, 3, 7, 14, 10, 15, 6, 8, 0, 5, 9, 2 }, { 6, 11, 13, 8, 1, 4, 10, 7, 9, 5, 0, 15, 14, 2, 3, 12 } };
        final int[][] s8 = { { 13, 2, 8, 4, 6, 15, 11, 1, 10, 9, 3, 14, 5, 0, 12, 7 }, { 1, 15, 13, 8, 10, 3, 7, 4, 12, 5, 6, 11, 0, 14, 9, 2 }, { 7, 11, 4, 1, 9, 12, 14, 2, 0, 6, 10, 13, 15, 3, 5, 8 }, { 2, 1, 14, 7, 4, 10, 8, 13, 15, 12, 9, 0, 3, 5, 6, 11 } };
        for (int m = 0; m < 8; ++m) {
            int i = 0;
            int j = 0;
            i = expandByte[m * 6 + 0] * 2 + expandByte[m * 6 + 5];
            j = expandByte[m * 6 + 1] * 2 * 2 * 2 + expandByte[m * 6 + 2] * 2 * 2 + expandByte[m * 6 + 3] * 2 + expandByte[m * 6 + 4];
            switch (m) {
                case 0: {
                    binary = this.getBoxBinary(s1[i][j]);
                    break;
                }
                case 1: {
                    binary = this.getBoxBinary(s2[i][j]);
                    break;
                }
                case 2: {
                    binary = this.getBoxBinary(s3[i][j]);
                    break;
                }
                case 3: {
                    binary = this.getBoxBinary(s4[i][j]);
                    break;
                }
                case 4: {
                    binary = this.getBoxBinary(s5[i][j]);
                    break;
                }
                case 5: {
                    binary = this.getBoxBinary(s6[i][j]);
                    break;
                }
                case 6: {
                    binary = this.getBoxBinary(s7[i][j]);
                    break;
                }
                case 7: {
                    binary = this.getBoxBinary(s8[i][j]);
                    break;
                }
            }
            sBoxByte[m * 4 + 0] = Integer.parseInt(binary.substring(0, 1));
            sBoxByte[m * 4 + 1] = Integer.parseInt(binary.substring(1, 2));
            sBoxByte[m * 4 + 2] = Integer.parseInt(binary.substring(2, 3));
            sBoxByte[m * 4 + 3] = Integer.parseInt(binary.substring(3, 4));
        }
        return sBoxByte;
    }

    public int[] pPermute(final int[] sBoxByte) {
        final int[] pBoxPermute = { sBoxByte[15], sBoxByte[6], sBoxByte[19], sBoxByte[20], sBoxByte[28], sBoxByte[11], sBoxByte[27], sBoxByte[16], sBoxByte[0], sBoxByte[14], sBoxByte[22], sBoxByte[25], sBoxByte[4], sBoxByte[17], sBoxByte[30], sBoxByte[9], sBoxByte[1], sBoxByte[7], sBoxByte[23], sBoxByte[13], sBoxByte[31], sBoxByte[26], sBoxByte[2], sBoxByte[8], sBoxByte[18], sBoxByte[12], sBoxByte[29], sBoxByte[5], sBoxByte[21], sBoxByte[10], sBoxByte[3], sBoxByte[24] };
        return pBoxPermute;
    }

    public int[] finallyPermute(final int[] endByte) {
        final int[] fpByte = { endByte[39], endByte[7], endByte[47], endByte[15], endByte[55], endByte[23], endByte[63], endByte[31], endByte[38], endByte[6], endByte[46], endByte[14], endByte[54], endByte[22], endByte[62], endByte[30], endByte[37], endByte[5], endByte[45], endByte[13], endByte[53], endByte[21], endByte[61], endByte[29], endByte[36], endByte[4], endByte[44], endByte[12], endByte[52], endByte[20], endByte[60], endByte[28], endByte[35], endByte[3], endByte[43], endByte[11], endByte[51], endByte[19], endByte[59], endByte[27], endByte[34], endByte[2], endByte[42], endByte[10], endByte[50], endByte[18], endByte[58], endByte[26], endByte[33], endByte[1], endByte[41], endByte[9], endByte[49], endByte[17], endByte[57], endByte[25], endByte[32], endByte[0], endByte[40], endByte[8], endByte[48], endByte[16], endByte[56], endByte[24] };
        return fpByte;
    }

    public String getBoxBinary(final int i) {
        String binary = "";
        switch (i) {
            case 0: {
                binary = "0000";
                break;
            }
            case 1: {
                binary = "0001";
                break;
            }
            case 2: {
                binary = "0010";
                break;
            }
            case 3: {
                binary = "0011";
                break;
            }
            case 4: {
                binary = "0100";
                break;
            }
            case 5: {
                binary = "0101";
                break;
            }
            case 6: {
                binary = "0110";
                break;
            }
            case 7: {
                binary = "0111";
                break;
            }
            case 8: {
                binary = "1000";
                break;
            }
            case 9: {
                binary = "1001";
                break;
            }
            case 10: {
                binary = "1010";
                break;
            }
            case 11: {
                binary = "1011";
                break;
            }
            case 12: {
                binary = "1100";
                break;
            }
            case 13: {
                binary = "1101";
                break;
            }
            case 14: {
                binary = "1110";
                break;
            }
            case 15: {
                binary = "1111";
                break;
            }
        }
        return binary;
    }

    public int[][] generateKeys(final int[] keyByte) {
        final int[] key = new int[56];
        final int[][] keys = new int[16][48];
        final int[] loop = { 1, 1, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 1 };
        for (int i = 0; i < 7; ++i) {
            for (int j = 0, k = 7; j < 8; ++j, --k) {
                key[i * 8 + j] = keyByte[8 * k + i];
            }
        }
        int i;
        int tempLeft;
        int tempRight;
        int l;
        int m;
        int[] tempKey;
        int m2;
        for (i = 0, i = 0; i < 16; ++i) {
            tempLeft = 0;
            tempRight = 0;
            for (l = 0; l < loop[i]; ++l) {
                tempLeft = key[0];
                tempRight = key[28];
                for (m = 0; m < 27; ++m) {
                    key[m] = key[m + 1];
                    key[28 + m] = key[29 + m];
                }
                key[27] = tempLeft;
                key[55] = tempRight;
            }
            tempKey = new int[] { key[13], key[16], key[10], key[23], key[0], key[4], key[2], key[27], key[14], key[5], key[20], key[9], key[22], key[18], key[11], key[3], key[25], key[7], key[15], key[6], key[26], key[19], key[12], key[1], key[40], key[51], key[30], key[36], key[46], key[54], key[29], key[39], key[50], key[44], key[32], key[47], key[43], key[48], key[38], key[55], key[33], key[52], key[45], key[41], key[49], key[35], key[28], key[31] };
            switch (i) {
                case 0: {
                    for (m2 = 0; m2 < 48; ++m2) {
                        keys[0][m2] = tempKey[m2];
                    }
                    break;
                }
                case 1: {
                    for (m2 = 0; m2 < 48; ++m2) {
                        keys[1][m2] = tempKey[m2];
                    }
                    break;
                }
                case 2: {
                    for (m2 = 0; m2 < 48; ++m2) {
                        keys[2][m2] = tempKey[m2];
                    }
                    break;
                }
                case 3: {
                    for (m2 = 0; m2 < 48; ++m2) {
                        keys[3][m2] = tempKey[m2];
                    }
                    break;
                }
                case 4: {
                    for (m2 = 0; m2 < 48; ++m2) {
                        keys[4][m2] = tempKey[m2];
                    }
                    break;
                }
                case 5: {
                    for (m2 = 0; m2 < 48; ++m2) {
                        keys[5][m2] = tempKey[m2];
                    }
                    break;
                }
                case 6: {
                    for (m2 = 0; m2 < 48; ++m2) {
                        keys[6][m2] = tempKey[m2];
                    }
                    break;
                }
                case 7: {
                    for (m2 = 0; m2 < 48; ++m2) {
                        keys[7][m2] = tempKey[m2];
                    }
                    break;
                }
                case 8: {
                    for (m2 = 0; m2 < 48; ++m2) {
                        keys[8][m2] = tempKey[m2];
                    }
                    break;
                }
                case 9: {
                    for (m2 = 0; m2 < 48; ++m2) {
                        keys[9][m2] = tempKey[m2];
                    }
                    break;
                }
                case 10: {
                    for (m2 = 0; m2 < 48; ++m2) {
                        keys[10][m2] = tempKey[m2];
                    }
                    break;
                }
                case 11: {
                    for (m2 = 0; m2 < 48; ++m2) {
                        keys[11][m2] = tempKey[m2];
                    }
                    break;
                }
                case 12: {
                    for (m2 = 0; m2 < 48; ++m2) {
                        keys[12][m2] = tempKey[m2];
                    }
                    break;
                }
                case 13: {
                    for (m2 = 0; m2 < 48; ++m2) {
                        keys[13][m2] = tempKey[m2];
                    }
                    break;
                }
                case 14: {
                    for (m2 = 0; m2 < 48; ++m2) {
                        keys[14][m2] = tempKey[m2];
                    }
                    break;
                }
                case 15: {
                    for (m2 = 0; m2 < 48; ++m2) {
                        keys[15][m2] = tempKey[m2];
                    }
                    break;
                }
            }
        }
        return keys;
    }
}
