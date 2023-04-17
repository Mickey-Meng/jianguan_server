package com.ruoyi.web.controller.jianguan.zlsk.stsupporter.stctk.util.encryption;

public class DesEncryption
{
    private static int[] a;
    private static int[] b;
    private static int[] c;
    private static int[] d;
    private static int[] e;
    private static int[] f;
    private static int[] g;
    private static int[] h;
    private static int[][][] i;
    private byte[][] j;
    private byte[][] k;
    private byte[][] l;

    public DesEncryption() {
        this.j = new byte[17][28];
        this.k = new byte[17][28];
        this.l = new byte[17][48];
    }

    private static int a(final byte b) {
        if (b < 0) {
            return b & 0xFF;
        }
        return b;
    }

    public static String byteHEX(final byte b) {
        final char[] array = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
        final char[] array2;
        (array2 = new char[2])[0] = array[b >>> 4 & 0xF];
        array2[1] = array[b & 0xF];
        return new String(array2);
    }

    private static void a(final byte[] array, final byte[] array2, final int n, final int n2, final int n3) {
        for (int i = 0; i < n3; ++i) {
            array[n + i] = array2[n2 + i];
        }
    }

    private static void a(final byte[] array, final byte[] array2) {
        for (int i = 0; i < 8; ++i) {
            int n = 7;
            for (int j = 0; j < 8; ++j) {
                array2[i * 8 + j] = (byte)(a(array[i]) >>> n & 0x1);
                --n;
            }
        }
    }

    private static void a(final byte[] array, final byte[] array2, final int n) {
        for (int i = 0; i < 28; ++i) {
            array2[i] = array[(i + n) % 28];
        }
    }

    private void a(final byte[] array) {
        for (int i = 0; i < 28; ++i) {
            this.j[0][i] = array[DesEncryption.a[i] - 1];
        }
        for (int j = 0; j < 28; ++j) {
            this.k[0][j] = array[DesEncryption.b[j] - 1];
        }
        for (int k = 0; k < 16; ++k) {
            a(this.j[k], this.j[k + 1], DesEncryption.d[k]);
            a(this.k[k], this.k[k + 1], DesEncryption.d[k]);
            final byte[] array2 = this.j[k + 1];
            final byte[] array3 = this.k[k + 1];
            final byte[] array4 = this.l[k + 1];
            final byte[] array5 = array3;
            final byte[] array6 = array2;
            final byte[] array7 = new byte[56];
            for (int l = 0; l < 28; ++l) {
                array7[l] = array6[l];
            }
            for (int n = 28; n < 56; ++n) {
                array7[n] = array5[n - 28];
            }
            for (int n2 = 0; n2 < 48; ++n2) {
                array4[n2] = array7[DesEncryption.c[n2] - 1];
            }
        }
    }

    private static void a(final byte[] array, final byte[] array2, final byte[] array3) {
        final byte[] array4 = new byte[64];
        a(array, array4);
        for (int i = 0; i < 32; ++i) {
            array2[i] = array4[DesEncryption.e[i] - 1];
        }
        for (int j = 0; j < 32; ++j) {
            array3[j] = array4[DesEncryption.e[j + 32] - 1];
        }
    }

    private void a(final int n, final byte[] array, final byte[] array2, final byte[] array3, final byte[] array4) {
        final byte[] array5 = new byte[64];
        final byte[] array6 = new byte[64];
        for (int i = 0; i < 48; ++i) {
            array5[i] = array2[DesEncryption.g[i] - 1];
        }
        for (int j = 0; j < 48; ++j) {
            array5[j] = (byte)(a(array5[j]) + a(this.l[n][j]) & 0x1);
        }
        final byte[] array7 = array6;
        final byte[] array8 = array5;
        final byte[] array9 = new byte[8];
        int n2 = 0;
        for (int k = 0; k < 8; ++k) {
            final int n3 = k * 6;
            array9[k] = (byte)DesEncryption.i[k][(a(array8[n3]) << 1) + a(array8[n3 + 5])][(a(array8[n3 + 1]) << 3) + (a(array8[n3 + 2]) << 2) + (a(array8[n3 + 3]) << 1) + a(array8[n3 + 4])];
            int n4 = 3;
            for (int l = 0; l < 4; ++l) {
                array7[n2++] = (byte)(a(array9[k]) >>> n4 & 0x1);
                --n4;
            }
        }
        for (int n5 = 0; n5 < 32; ++n5) {
            array5[n5] = array6[DesEncryption.h[n5] - 1];
        }
        for (int n6 = 0; n6 < 32; ++n6) {
            array4[n6] = (byte)(a(array5[n6]) + a(array[n6]) & 0x1);
        }
        for (int n7 = 0; n7 < 32; ++n7) {
            array3[n7] = array2[n7];
        }
    }

    private static void b(final byte[] array, final byte[] array2, final byte[] array3) {
        final byte[] array4 = new byte[64];
        for (int i = 0; i < 32; ++i) {
            array4[i] = array2[i];
        }
        for (int j = 32; j < 64; ++j) {
            array4[j] = array3[j - 32];
        }
        for (int k = 0; k < 64; ++k) {
            array[k] = array4[DesEncryption.f[k] - 1];
        }
    }

    private static void b(final byte[] array, final byte[] array2) {
        for (int i = 0; i < 8; ++i) {
            int n = 7;
            array2[i] = 0;
            for (int j = 0; j < 8; ++j) {
                array2[i] = (byte)(a(array2[i]) + (a(array[(i << 3) + j]) << n));
                --n;
            }
        }
    }

    public int encrypt(final byte[] array, final byte[] array2, final byte[] array3, final int n) {
        final byte[] array4 = new byte[8];
        final byte[] array5 = new byte[8];
        int i;
        for (i = 0; i < n; i += 8) {
            if (i + 8 > n) {
                a(array4, array2, 0, i, n - i);
                for (int j = n - i; j < 8; ++j) {
                    array4[j] = 0;
                }
            }
            else {
                a(array4, array2, 0, i, 8);
            }
            final byte[] array6 = new byte[64];
            a(array, array6);
            this.a(array6);
            final byte[] array7 = new byte[64];
            final byte[] array8 = new byte[64];
            final byte[] array9 = new byte[64];
            final byte[] array10 = new byte[64];
            final byte[] array11 = new byte[64];
            a(array4, array7, array8);
            for (int k = 1; k < 17; ++k) {
                this.a(k, array7, array8, array9, array10);
                for (int l = 0; l < 32; ++l) {
                    array7[l] = array9[l];
                    array8[l] = array10[l];
                }
            }
            b(array11, array8, array7);
            b(array11, array5);
            a(array3, array5, i, 0, 8);
        }
        return i;
    }

    public int decrypt(final byte[] array, final byte[] array2, final byte[] array3, final int n) {
        final byte[] array4 = new byte[8];
        final byte[] array5 = new byte[8];
        int i;
        for (i = 0; i < n; i += 8) {
            a(array5, array3, 0, i, 8);
            final byte[] array6 = new byte[64];
            a(array, array6);
            this.a(array6);
            final byte[] array7 = new byte[64];
            final byte[] array8 = new byte[64];
            final byte[] array9 = new byte[64];
            final byte[] array10 = new byte[64];
            final byte[] array11 = new byte[64];
            a(array5, array7, array8);
            for (int j = 16; j > 0; --j) {
                this.a(j, array7, array8, array9, array10);
                for (int k = 0; k < 32; ++k) {
                    array7[k] = array9[k];
                    array8[k] = array10[k];
                }
            }
            b(array11, array8, array7);
            b(array11, array4);
            a(array2, array4, i, 0, 8);
        }
        return i;
    }

    public static byte[] hexStr2ByteArr(final String s) {
        final byte[] bytes;
        final int length;
        final byte[] array = new byte[(length = (bytes = s.getBytes()).length) / 2];
        for (int i = 0; i < length; i += 2) {
            array[i / 2] = (byte)Integer.parseInt(new String(bytes, i, 2), 16);
        }
        return array;
    }

    public String encodeStr(final String s, final int n) {
        final byte[] array = new byte[n];
        final byte[] bytes = s.getBytes();
        final int encrypt = this.encrypt("lefalyHH".getBytes(), bytes, array, bytes.length);
        String string = "";
        for (int i = 0; i < encrypt; ++i) {
            string += byteHEX(array[i]);
        }
        return string;
    }

    public byte[] encode(final byte[] array, int n2) {
        int n = 0;
        final byte[] array2 = new byte[n + 8];
        final int encrypt;
        n2 = (int)(Object)new byte[encrypt = this.encrypt("lefalyHH".getBytes(), array, array2, n)];
        System.arraycopy(array2, 0, n2, 0, encrypt);
        try {
            System.out.println("DesEncryption: miWen = " + new String((byte[])(Object)n2, "UTF-8"));
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return (byte[])(Object)n2;
    }

    public String decodeStr(final String s, final int n) {
        final byte[] array = new byte[n];
        final DesEncryption desEncryption = new DesEncryption();
        final byte[] hexStr2ByteArr = hexStr2ByteArr(s);
        return new String(array, 0, desEncryption.decrypt("lefalyHH".getBytes(), array, hexStr2ByteArr, hexStr2ByteArr.length));
    }

    public byte[] decode(final byte[] array, int n2) {
        int n = 0;
        final byte[] array2 = new byte[n + 8];
        final int decrypt;
        n2 = (int)(Object)new byte[decrypt = new DesEncryption().decrypt("lefalyHH".getBytes(), array2, array, n)];
        System.arraycopy(array2, 0, n2, 0, decrypt);
        try {
            System.out.println("DesEncryption: mingWen = " + new String((byte[])(Object)n2, "UTF-8"));
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return (byte[])(Object)n2;
    }

    static {
        DesEncryption.a = new int[] { 57, 49, 41, 33, 25, 17, 9, 1, 58, 50, 42, 34, 26, 18, 10, 2, 59, 51, 43, 35, 27, 19, 11, 3, 60, 52, 44, 36 };
        DesEncryption.b = new int[] { 63, 55, 47, 39, 31, 23, 15, 7, 62, 54, 46, 38, 30, 22, 14, 6, 61, 53, 45, 37, 29, 21, 13, 5, 28, 20, 12, 4 };
        DesEncryption.c = new int[] { 14, 17, 11, 24, 1, 5, 3, 28, 15, 6, 21, 10, 23, 19, 12, 4, 26, 8, 16, 7, 27, 20, 13, 2, 41, 52, 31, 37, 47, 55, 30, 40, 51, 45, 33, 48, 44, 49, 39, 56, 34, 53, 46, 42, 50, 36, 29, 32 };
        DesEncryption.d = new int[] { 1, 1, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 1 };
        DesEncryption.e = new int[] { 58, 50, 42, 34, 26, 18, 10, 2, 60, 52, 44, 36, 28, 20, 12, 4, 62, 54, 46, 38, 30, 22, 14, 6, 64, 56, 48, 40, 32, 24, 16, 8, 57, 49, 41, 33, 25, 17, 9, 1, 59, 51, 43, 35, 27, 19, 11, 3, 61, 53, 45, 37, 29, 21, 13, 5, 63, 55, 47, 39, 31, 23, 15, 7 };
        DesEncryption.f = new int[] { 40, 8, 48, 16, 56, 24, 64, 32, 39, 7, 47, 15, 55, 23, 63, 31, 38, 6, 46, 14, 54, 22, 62, 30, 37, 5, 45, 13, 53, 21, 61, 29, 36, 4, 44, 12, 52, 20, 60, 28, 35, 3, 43, 11, 51, 19, 59, 27, 34, 2, 42, 10, 50, 18, 58, 26, 33, 1, 41, 9, 49, 17, 57, 25 };
        DesEncryption.g = new int[] { 32, 1, 2, 3, 4, 5, 4, 5, 6, 7, 8, 9, 8, 9, 10, 11, 12, 13, 12, 13, 14, 15, 16, 17, 16, 17, 18, 19, 20, 21, 20, 21, 22, 23, 24, 25, 24, 25, 26, 27, 28, 29, 28, 29, 30, 31, 32, 1 };
        DesEncryption.h = new int[] { 16, 7, 20, 21, 29, 12, 28, 17, 1, 15, 23, 26, 5, 18, 31, 10, 2, 8, 24, 14, 32, 27, 3, 9, 19, 13, 30, 6, 22, 11, 4, 25 };
        DesEncryption.i = new int[][][] { { { 14, 4, 13, 1, 2, 15, 11, 8, 3, 10, 6, 12, 5, 9, 0, 7 }, { 0, 15, 7, 4, 14, 2, 13, 1, 10, 6, 12, 11, 9, 5, 3, 8 }, { 4, 1, 14, 8, 13, 6, 2, 11, 15, 12, 9, 7, 3, 10, 5, 0 }, { 15, 12, 8, 2, 4, 9, 1, 7, 5, 11, 3, 14, 10, 0, 6, 13 } }, { { 15, 1, 8, 14, 6, 11, 3, 4, 9, 7, 2, 13, 12, 0, 5, 10 }, { 3, 13, 4, 7, 15, 2, 8, 14, 12, 0, 1, 10, 6, 9, 11, 5 }, { 0, 14, 7, 11, 10, 4, 13, 1, 5, 8, 12, 6, 9, 3, 2, 15 }, { 13, 8, 10, 1, 3, 15, 4, 2, 11, 6, 7, 12, 0, 5, 14, 9 } }, { { 10, 0, 9, 14, 6, 3, 15, 5, 1, 13, 12, 7, 11, 4, 2, 8 }, { 13, 7, 0, 9, 3, 4, 6, 10, 2, 8, 5, 14, 12, 11, 15, 1 }, { 13, 6, 4, 9, 8, 15, 3, 0, 11, 1, 2, 12, 5, 10, 14, 7 }, { 1, 10, 13, 0, 6, 9, 8, 7, 4, 15, 14, 3, 11, 5, 2, 12 } }, { { 7, 13, 14, 3, 0, 6, 9, 10, 1, 2, 8, 5, 11, 12, 4, 15 }, { 13, 8, 11, 5, 6, 15, 0, 3, 4, 7, 2, 12, 1, 10, 14, 9 }, { 10, 6, 9, 0, 12, 11, 7, 13, 15, 1, 3, 14, 5, 2, 8, 4 }, { 3, 15, 0, 6, 10, 1, 13, 8, 9, 4, 5, 11, 12, 7, 2, 14 } }, { { 2, 12, 4, 1, 7, 10, 11, 6, 8, 5, 3, 15, 13, 0, 14, 9 }, { 14, 11, 2, 12, 4, 7, 13, 1, 5, 0, 15, 10, 3, 9, 8, 6 }, { 4, 2, 1, 11, 10, 13, 7, 8, 15, 9, 12, 5, 6, 3, 0, 14 }, { 11, 8, 12, 7, 1, 14, 2, 13, 6, 15, 0, 9, 10, 4, 5, 3 } }, { { 12, 1, 10, 15, 9, 2, 6, 8, 0, 13, 3, 4, 14, 7, 5, 11 }, { 10, 15, 4, 2, 7, 12, 9, 5, 6, 1, 13, 14, 0, 11, 3, 8 }, { 9, 14, 15, 5, 2, 8, 12, 3, 7, 0, 4, 10, 1, 13, 11, 6 }, { 4, 3, 2, 12, 9, 5, 15, 10, 11, 14, 1, 7, 6, 0, 8, 13 } }, { { 4, 11, 2, 14, 15, 0, 8, 13, 3, 12, 9, 7, 5, 10, 6, 1 }, { 13, 0, 11, 7, 4, 9, 1, 10, 14, 3, 5, 12, 2, 15, 8, 6 }, { 1, 4, 11, 13, 12, 3, 7, 14, 10, 15, 6, 8, 0, 5, 9, 2 }, { 6, 11, 13, 8, 1, 4, 10, 7, 9, 5, 0, 15, 14, 2, 3, 12 } }, { { 13, 2, 8, 4, 6, 15, 11, 1, 10, 9, 3, 14, 5, 0, 12, 7 }, { 1, 15, 13, 8, 10, 3, 7, 4, 12, 5, 6, 11, 0, 14, 9, 2 }, { 7, 11, 4, 1, 9, 12, 14, 2, 0, 6, 10, 13, 15, 3, 5, 8 }, { 2, 1, 14, 7, 4, 10, 8, 13, 15, 12, 9, 0, 3, 5, 6, 11 } } };
    }
}
