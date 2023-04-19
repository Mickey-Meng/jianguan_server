package com.ruoyi.web.controller.jianguan.other.zlsk.stsupporter.stctk.stad;

import cn.hutool.json.JSONObject;
import com.ruoyi.web.controller.jianguan.other.zlsk.stsupporter.stctk.util.NetUtil;
import org.yaml.snakeyaml.*;
import java.net.*;
import java.io.*;
import java.text.*;
import java.util.*;

public class Stat
{
    private static String a;
    private static Map<String, Object> b;
    private static final Map<String, String> c;
    private static final Map<String, Date> d;
    private FileFilter e;
    private FileFilter f;

    public Stat(String string) throws Exception {
        if (!this.getClass().getName().substring(0, 20).equals(string)) {
            throw new Exception();
        }
        if (Stat.b == null) {
            Stat.a = this.getClass().getResource("/").getPath();
            Stat.b = (Map<String, Object>)a(string = Stat.a + File.separator + "application.yml");
        }
    }

    private static void a(Object o) {
        o = o;
        System.out.println(o);
    }

    public String chk(final String s) {
        String string = null;
        final Map<String, Object> map;
        final Object value;
        if (Stat.b != null && Stat.b.containsKey("zlsk") && (map = (Map<String, Object>) Stat.b.get("zlsk")) != null && map.containsKey("staurl") && (value = map.get("staurl")) != null) {
            string = value.toString();
        }
        return this.chk(s, string);
    }

    public String chk(String s, String a) {
        try {
            final String hostAddress = InetAddress.getLocalHost().getHostAddress();
            a((Object)("ck0: " + hostAddress));
            if (a == null) {
                return this.check(hostAddress, s);
            }
            final StringBuilder sb = new StringBuilder();
            sb.append(a).append("/stauth/check?ck=").append(URLEncoder.encode(a(hostAddress.replace('.', 'C'), 20).toString(), "UTF-8"));
            a = "yyyyMMddHHmmss";
            final String string = a(a = a(new Date(), a), 25).toString();
            final int n = (int)Math.floor(Math.random() * 3.0) + 1;
            if (s != null) {
                s = a(s, n).append((CharSequence)a(new StringBuilder().append(n).toString(), 21)).append(string).toString();
                sb.append("&sk=").append(URLEncoder.encode(s, "UTF-8"));
            }
            else {
                sb.append("&sk=").append((CharSequence)a(new StringBuilder().append(n).toString(), 21)).append(string);
            }
            if ((s = b(s = sb.toString())) != null && !s.isEmpty()) {
                final JSONObject jsonObject;
                if ((jsonObject = new JSONObject(s)).getInt("meow") < 0) {
                    return null;
                }
                final int length = (s = jsonObject.getStr("data")).length();
                if (!a(s.substring(length - 14), -20).toString().equals(a)) {
                    return null;
                }
                return s = a(s.substring(0, 10) + " " + s.substring(10, length - 14), n).toString();
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public String check(final String s, final String s2) {
        final Date date = new Date();
        if (!Stat.c.containsKey(s) || !Stat.d.containsKey(s) || date.getTime() - Stat.d.get(s).getTime() >= 3600000.0) {
            final String lowerCase;
            a((Object)(lowerCase = System.getProperty("os.name").toLowerCase()));
            final File file;
            System.out.println((Object)(file = new File(new File(System.getProperty("user.home") + File.separator).getParent() + File.separator + "sta" + File.separator)).getAbsolutePath());
            if (!file.exists()) {
                file.mkdirs();
            }
            File[] array;
            if (((array = file.listFiles(this.e)) == null || array.length == 0) && ((array = file.listFiles(this.f)) == null || array.length == 0)) {
                if (!lowerCase.contains("windows")) {
                    return null;
                }
                final File file2;
                System.out.println((Object)(file2 = new File(System.getProperty("user.home").substring(0, 2) + "\\Users\\sta\\")).getAbsolutePath());
                if (((array = file2.listFiles(this.e)) == null || array.length == 0) && ((array = file2.listFiles(this.f)) == null || array.length == 0)) {
                    return null;
                }
            }
            a((Object)array[0].getPath());
            final String trim;
            if ((trim = c(array[0].getPath()).trim()) == null || trim.isEmpty()) {
                return null;
            }
            final String name;
            String s3;
            if ((name = array[0].getName()).endsWith("sta")) {
                s3 = this.a(s, trim, name);
            }
            else {
                s3 = this.b(s, trim, name);
            }
            if (s3.length() < 4) {
                System.out.println(s3);
            }
            Stat.d.put(s, date);
        }
        final String s4;
        if ((s4 = Stat.c.get(s)).length() < 19) {
            System.out.println((Object)s4);
            return null;
        }
        if (d(s4.substring(0, 19)) <= 0) {
            return null;
        }
        final String substring = s4.substring(19);
        if (s2 != null && !s2.isEmpty() && !substring.contains("_" + s2 + "_")) {
            return null;
        }
        return s4;
    }

    private static Object a(final String s) {
        try {
            return new Yaml().load((InputStream)new FileInputStream(s));
        }
        catch (Exception ex) {
            return null;
        }
    }

    private static String b(String line) {
        try {
            final HttpURLConnection httpURLConnection;
            (httpURLConnection = (HttpURLConnection)new URL(line).openConnection()).setRequestMethod("GET");
            if (httpURLConnection.getResponseCode() != 200) {
                return "{\"meow\":-1,\"msg\":\"\u8bf7\u6c42\u670d\u52a1\u5931\u8d25," + httpURLConnection.getResponseCode() + "\"}";
            }
            return line = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream())).readLine();
        }
        catch (Exception ex) {
            return "{\"meow\":-1,\"msg\":\"\u8bf7\u6c42\u670d\u52a1\u5931\u8d25, " + ex.getMessage() + "\"}";
        }
    }

    private static String c(final String s) {
        if (!new File(s).exists()) {
            return "";
        }
        try {
            final FileInputStream fileInputStream = new FileInputStream(s);
            byte[] array = new byte[1024];
            final StringBuffer sb = new StringBuffer();
            while (fileInputStream.read(array) != -1) {
                sb.append(new String(array, "UTF-8"));
                array = new byte[1024];
            }
            fileInputStream.close();
            return sb.toString();
        }
        catch (Exception ex) {
            return "";
        }
    }

    private String a(final String s, String s2, final String s3) {
        try {
            final String a = this.a(false);
            final String name = this.getClass().getName();
            final String substring = s2.substring(0, s2.length() - Integer.parseInt(s2.substring(s2.length() - 2)) - 2);
            s2 = s2.substring(s2.length() - 12, s2.length() - 2);
            final StringBuilder a2;
            final int int1 = Integer.parseInt((a2 = a(this.a(substring, a(a.substring(9, 18), 9).toString(), a(name.substring(0, 23) + "a" + name.substring(31), 6).toString(), a(s2, -1).toString().toLowerCase() + s3.substring(9, 18)), 9)).substring(0, 2), 16);
            final int int2 = Integer.parseInt(a2.substring(2, 5), 16);
            if (!Stat.c.containsKey(s) && Stat.c.size() >= int2) {
                return "2";
            }
            final int int3 = Integer.parseInt(a2.substring(5, 10), 16);
            final Date a3 = a(a2.substring(10, 24), "yyyyMMddHHmmss");
            final Date a4;
            final long n;
            if ((n = (a4 = a(a2.substring(24, 38), "yyyyMMddHHmmss")).getTime() - a3.getTime()) <= 0L) {
                return "3";
            }
            if (Math.floor(n / 8.64E7) != int3) {
                return "3";
            }
            if (int1 == 0) {
                final Date date;
                if ((date = new Date()).getTime() < a3.getTime() || date.getTime() > a4.getTime()) {
                    return "3.0";
                }
            }
            else {
                if (int1 != 1) {
                    return "1";
                }
                if ((Integer.parseInt(a2.substring(10, 14)) + 1) / 10000.0 != int1 || (Integer.parseInt(a2.substring(24, 28)) + 1) / 10000.0 != int1) {
                    return "3.1";
                }
            }
            final int index = a2.indexOf("_");
            final int lastIndex = a2.lastIndexOf("_");
            if (!a2.substring(38, index).equals(a)) {
                return "4";
            }
            s2 = a2.substring(index, lastIndex + 1);
            s2 = a(a4, "yyyy-MM-dd HH:mm:ss") + s2;
            Stat.c.put(s, s2);
            return s2;
        }
        catch (Exception ex) {
            return "0";
        }
    }

    private String b(final String s, String s2, final String s3) {
        try {
            final String string;
            if ((string = this.getClass().getName().substring(0, 21) + "sta." + this.getClass().getSimpleName()) == null || string.length() < 20) {
                return "4";
            }
            final String name = this.getClass().getName();
            final String substring = s2.substring(0, s2.length() - Integer.parseInt(s2.substring(s2.length() - 2)) - 2);
            s2 = s2.substring(s2.length() - 12, s2.length() - 2);
            final StringBuilder a;
            final int int1 = Integer.parseInt((a = a(this.a(substring, a(string.substring(9, 18), 9).toString(), a(name.substring(0, 23) + "a" + name.substring(31), 6).toString(), a(s2, -1).toString().toLowerCase() + s3.substring(9, 18)), 9)).substring(0, 2), 16);
            final int int2 = Integer.parseInt(a.substring(2, 5), 16);
            if (!Stat.c.containsKey(s) && Stat.c.size() >= int2) {
                a((Object)Stat.c.size());
                a((Object)int2);
                final Iterator<Map.Entry<String, String>> iterator = Stat.c.entrySet().iterator();
                while (iterator.hasNext()) {
                    a((Object)iterator.next().getKey());
                }
                return "2";
            }
            final int int3 = Integer.parseInt(a.substring(5, 10), 16);
            final Date a2 = a(a.substring(10, 24), "yyyyMMddHHmmss");
            final Date a3;
            final long n;
            if ((n = (a3 = a(a.substring(24, 38), "yyyyMMddHHmmss")).getTime() - a2.getTime()) <= 0L) {
                return "3";
            }
            final double floor;
            if ((floor = Math.floor(n / 8.64E7)) != int3 || floor > 366.0) {
                return "3";
            }
            if (int1 == 0 || int1 == 2) {
                final Date date;
                if ((date = new Date()).getTime() < a2.getTime() || date.getTime() > a3.getTime()) {
                    return "3.0";
                }
            }
            else {
                if (int1 != 1) {
                    return "1";
                }
                if ((Integer.parseInt(a.substring(10, 14)) + 1) / 10000.0 != int1 || (Integer.parseInt(a.substring(24, 28)) + 1) / 10000.0 != int1) {
                    return "3.1";
                }
            }
            final int index = a.indexOf("_");
            final int lastIndex = a.lastIndexOf("_");
            if (!a.substring(38, index).equals(string)) {
                return "4";
            }
            s2 = a.substring(index, lastIndex + 1);
            s2 = a(a3, "yyyy-MM-dd HH:mm:ss") + s2;
            Stat.c.put(s, s2);
            return s2;
        }
        catch (Exception ex) {
            return "0";
        }
    }

    private static int d(final String s) {
        try {
            final long n;
            if ((n = a(s, "yyyy-MM-dd HH:mm:ss").getTime() - new Date().getTime()) > 0L) {
                return 1;
            }
            if (n < 0L) {
                return -1;
            }
            return 0;
        }
        catch (Exception ex) {
            return -1;
        }
    }

    private static String a(final Date date, final String s) {
        return new SimpleDateFormat(s).format(date);
    }

    private static Date a(final String s, final String s2) {
        try {
            return new SimpleDateFormat(s2).parse(s);
        }
        catch (Exception ex) {
            return null;
        }
    }

    private String a(final boolean b) {
        try {
            final String lowerCase = System.getProperty("os.name").toLowerCase();
            final StringBuilder sb = new StringBuilder();
            if (lowerCase.contains("windows")) {
                final Process exec;
                (exec = Runtime.getRuntime().exec(new String[] { "wmic", "cpu", "get", "ProcessorId" })).getOutputStream().close();
                final Scanner scanner;
                if (!(scanner = new Scanner(exec.getInputStream())).hasNext()) {
                    System.out.println((Object)"-1: noinfo");
                    scanner.close();
                }
                else {
                    scanner.next();
                    if (!scanner.hasNext()) {
                        System.out.println((Object)"-1: null");
                        scanner.close();
                    }
                    else {
                        final String next;
                        if (e(next = scanner.next())) {
                            System.out.println((Object)("-1: unavailable: " + next));
                            scanner.close();
                        }
                        sb.append(next);
                        scanner.close();
                    }
                }
                final Process exec2;
                (exec2 = Runtime.getRuntime().exec(new String[] { "wmic", "diskdrive", "get", "SerialNumber" })).getOutputStream().close();
                final Scanner scanner2;
                if (!(scanner2 = new Scanner(exec2.getInputStream())).hasNext()) {
                    System.out.println((Object)"-2: noinfo");
                    scanner2.close();
                }
                else {
                    scanner2.next();
                    if (!scanner2.hasNext()) {
                        System.out.println((Object)"-2: null");
                        scanner2.close();
                    }
                    else {
                        final String next2;
                        if (e(next2 = scanner2.next())) {
                            System.out.println((Object)("-2: unavailable" + next2));
                            scanner2.close();
                        }
                        sb.append(next2);
                        scanner2.close();
                    }
                }
                final Process exec3;
                (exec3 = Runtime.getRuntime().exec(new String[] { "wmic", "baseboard", "get", "SerialNumber" })).getOutputStream().close();
                final Scanner scanner3;
                if (!(scanner3 = new Scanner(exec3.getInputStream())).hasNext()) {
                    System.out.println((Object)"-3: noinfo");
                    scanner3.close();
                }
                else {
                    scanner3.next();
                    if (!scanner3.hasNext()) {
                        System.out.println((Object)"-3: null");
                        scanner3.close();
                    }
                    else {
                        final String next3;
                        if (e(next3 = scanner3.next())) {
                            System.out.println((Object)("-3: unavailable: " + next3));
                            scanner3.close();
                        }
                        sb.append(next3);
                        scanner3.close();
                    }
                }
                String s;
                if ((s = sb.toString()).trim().equalsIgnoreCase("nonenonenone")) {
                    System.out.println((Object)"no mi");
                    return null;
                }
                while (s.length() < 20) {
                    s += s;
                }
                return s;
            }
            else {
                if (!lowerCase.contains("linux")) {
                    return null;
                }
                final List<String> macList;
                if ((macList = NetUtil.getMacList()) != null) {
                    final Iterator<String> iterator = macList.iterator();
                    while (iterator.hasNext()) {
                        sb.append(iterator.next());
                    }
                    if (sb.length() == 0) {
                        return null;
                    }
                    while (sb.length() < 20) {
                        sb.append(sb.toString());
                    }
                }
                if (sb.length() == 0) {
                    return null;
                }
                return sb.toString();
            }
        }
        catch (Exception ex) {
            System.out.println((Object)"exception");
            return null;
        }
    }

    private static boolean e(final String s) {
        return s == null || s.isEmpty() || s.equalsIgnoreCase("none");
    }

    private static StringBuilder a(final String s, final int n) {
        final byte[] bytes = s.getBytes();
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; ++i) {
            sb.append((char)(bytes[i] + n));
        }
        return sb;
    }

    private String a(final String s, final String s2, final String s3, final String s4) {
        final int length = s.length();
        String string = "";
        List<int[]> f = null;
        List<int[]> f2 = null;
        List<int[]> f3 = null;
        int size = 0;
        int size2 = 0;
        int size3 = 0;
        if (s2 != null && s2 != "") {
            size = (f = (List<int[]>)this.f(s2)).size();
        }
        if (s3 != null && s3 != "") {
            size2 = (f2 = (List<int[]>)this.f(s3)).size();
        }
        if (s4 != null && s4 != "") {
            size3 = (f3 = (List<int[]>)this.f(s4)).size();
        }
        for (int n = length / 16, i = 0; i < n; ++i) {
            final String substring = s.substring(i << 4, (i << 4) + 16);
            String string2 = "";
            for (int j = 0; j < 16; ++j) {
                final StringBuilder append = new StringBuilder().append(string2);
                final String substring2 = substring.substring(j, j + 1);
                String s5 = "";
                if (substring2.equalsIgnoreCase("0")) {
                    s5 = "0000";
                }
                else if (substring2.equalsIgnoreCase("1")) {
                    s5 = "0001";
                }
                if (substring2.equalsIgnoreCase("2")) {
                    s5 = "0010";
                }
                if (substring2.equalsIgnoreCase("3")) {
                    s5 = "0011";
                }
                if (substring2.equalsIgnoreCase("4")) {
                    s5 = "0100";
                }
                if (substring2.equalsIgnoreCase("5")) {
                    s5 = "0101";
                }
                if (substring2.equalsIgnoreCase("6")) {
                    s5 = "0110";
                }
                if (substring2.equalsIgnoreCase("7")) {
                    s5 = "0111";
                }
                if (substring2.equalsIgnoreCase("8")) {
                    s5 = "1000";
                }
                if (substring2.equalsIgnoreCase("9")) {
                    s5 = "1001";
                }
                if (substring2.equalsIgnoreCase("A")) {
                    s5 = "1010";
                }
                if (substring2.equalsIgnoreCase("B")) {
                    s5 = "1011";
                }
                if (substring2.equalsIgnoreCase("C")) {
                    s5 = "1100";
                }
                if (substring2.equalsIgnoreCase("D")) {
                    s5 = "1101";
                }
                if (substring2.equalsIgnoreCase("E")) {
                    s5 = "1110";
                }
                if (substring2.equalsIgnoreCase("F")) {
                    s5 = "1111";
                }
                string2 = append.append(s5).toString();
            }
            final String s6 = string2;
            final int[] array = new int[64];
            for (int k = 0; k < 64; ++k) {
                array[k] = Integer.parseInt(s6.substring(k, k + 1));
            }
            int[] array2 = null;
            if (s2 != null && s2 != "" && s3 != null && s3 != "" && s4 != null && s4 != "") {
                int[] array3 = array;
                for (int l = size3 - 1; l >= 0; --l) {
                    array3 = this.a(array3, f3.get(l));
                }
                for (int n2 = size2 - 1; n2 >= 0; --n2) {
                    array3 = this.a(array3, f2.get(n2));
                }
                for (int n3 = size - 1; n3 >= 0; --n3) {
                    array3 = this.a(array3, f.get(n3));
                }
                array2 = array3;
            }
            else if (s2 != null && s2 != "" && s3 != null && s3 != "") {
                int[] array4 = array;
                for (int n4 = size2 - 1; n4 >= 0; --n4) {
                    array4 = this.a(array4, f2.get(n4));
                }
                for (int n5 = size - 1; n5 >= 0; --n5) {
                    array4 = this.a(array4, f.get(n5));
                }
                array2 = array4;
            }
            else if (s2 != null && s2 != "") {
                int[] a = array;
                for (int n6 = size - 1; n6 >= 0; --n6) {
                    a = this.a(a, f.get(n6));
                }
                array2 = a;
            }
            string += a(array2);
        }
        return string;
    }

    private List f(final String s) {
        final ArrayList<int[]> list = new ArrayList<int[]>();
        final int length;
        final int n = (length = s.length()) / 4;
        final int n2 = length % 4;
        int i;
        for (i = 0; i < n; ++i) {
            list.add(i, g(s.substring(i << 2, (i << 2) + 4)));
        }
        if (n2 > 0) {
            list.add(i, g(s.substring(i << 2, length)));
        }
        return list;
    }

    private static int[] g(final String s) {
        final int length = s.length();
        final int[] array = new int[64];
        if (length < 4) {
            for (int i = 0; i < length; ++i) {
                final char char1 = s.charAt(i);
                for (int j = 0; j < 16; ++j) {
                    int n = 1;
                    for (int k = 15; k > j; --k) {
                        n <<= 1;
                    }
                    array[i * 16 + j] = char1 / n % 2;
                }
            }
            for (int l = length; l < 4; ++l) {
                for (int n2 = 0; n2 < 16; ++n2) {
                    int n3 = 1;
                    for (int n4 = 15; n4 > n2; --n4) {
                        n3 <<= 1;
                    }
                    array[l * 16 + n2] = 0 / n3 % 2;
                }
            }
        }
        else {
            for (int n5 = 0; n5 < 4; ++n5) {
                final char char2 = s.charAt(n5);
                for (int n6 = 0; n6 < 16; ++n6) {
                    int n7 = 1;
                    for (int n8 = 15; n8 > n6; --n8) {
                        n7 <<= 1;
                    }
                    array[n5 * 16 + n6] = char2 / n7 % 2;
                }
            }
        }
        return array;
    }

    private static String a(final int[] array) {
        String string = "";
        for (int i = 0; i < 4; ++i) {
            int n = 0;
            for (int j = 0; j < 16; ++j) {
                int n2 = 1;
                for (int k = 15; k > j; --k) {
                    n2 <<= 1;
                }
                n += array[i * 16 + j] * n2;
            }
            if (n != 0) {
                string += (char)n;
            }
        }
        return string;
    }

    private int[] a(int[] array, final int[] array2) {
        final int[] array3 = new int[56];
        final int[][] array4 = new int[16][48];
        final int[] array5 = { 1, 1, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 1 };
        for (int i = 0; i < 7; ++i) {
            for (int j = 0, n = 7; j < 8; ++j, --n) {
                array3[(i << 3) + j] = array2[n * 8 + i];
            }
        }
        for (int k = 0; k < 16; ++k) {
            for (int l = 0; l < array5[k]; ++l) {
                final int n2 = array3[0];
                final int n3 = array3[28];
                for (int n4 = 0; n4 < 27; ++n4) {
                    array3[n4] = array3[n4 + 1];
                    array3[n4 + 28] = array3[n4 + 29];
                }
                array3[27] = n2;
                array3[55] = n3;
            }
            final int[] array6;
            (array6 = new int[48])[0] = array3[13];
            array6[1] = array3[16];
            array6[2] = array3[10];
            array6[3] = array3[23];
            array6[4] = array3[0];
            array6[5] = array3[4];
            array6[6] = array3[2];
            array6[7] = array3[27];
            array6[8] = array3[14];
            array6[9] = array3[5];
            array6[10] = array3[20];
            array6[11] = array3[9];
            array6[12] = array3[22];
            array6[13] = array3[18];
            array6[14] = array3[11];
            array6[15] = array3[3];
            array6[16] = array3[25];
            array6[17] = array3[7];
            array6[18] = array3[15];
            array6[19] = array3[6];
            array6[20] = array3[26];
            array6[21] = array3[19];
            array6[22] = array3[12];
            array6[23] = array3[1];
            array6[24] = array3[40];
            array6[25] = array3[51];
            array6[26] = array3[30];
            array6[27] = array3[36];
            array6[28] = array3[46];
            array6[29] = array3[54];
            array6[30] = array3[29];
            array6[31] = array3[39];
            array6[32] = array3[50];
            array6[33] = array3[44];
            array6[34] = array3[32];
            array6[35] = array3[47];
            array6[36] = array3[43];
            array6[37] = array3[48];
            array6[38] = array3[38];
            array6[39] = array3[55];
            array6[40] = array3[33];
            array6[41] = array3[52];
            array6[42] = array3[45];
            array6[43] = array3[41];
            array6[44] = array3[49];
            array6[45] = array3[35];
            array6[46] = array3[28];
            array6[47] = array3[31];
            switch (k) {
                case 0: {
                    for (int n5 = 0; n5 < 48; ++n5) {
                        array4[0][n5] = array6[n5];
                    }
                    break;
                }
                case 1: {
                    for (int n6 = 0; n6 < 48; ++n6) {
                        array4[1][n6] = array6[n6];
                    }
                    break;
                }
                case 2: {
                    for (int n7 = 0; n7 < 48; ++n7) {
                        array4[2][n7] = array6[n7];
                    }
                    break;
                }
                case 3: {
                    for (int n8 = 0; n8 < 48; ++n8) {
                        array4[3][n8] = array6[n8];
                    }
                    break;
                }
                case 4: {
                    for (int n9 = 0; n9 < 48; ++n9) {
                        array4[4][n9] = array6[n9];
                    }
                    break;
                }
                case 5: {
                    for (int n10 = 0; n10 < 48; ++n10) {
                        array4[5][n10] = array6[n10];
                    }
                    break;
                }
                case 6: {
                    for (int n11 = 0; n11 < 48; ++n11) {
                        array4[6][n11] = array6[n11];
                    }
                    break;
                }
                case 7: {
                    for (int n12 = 0; n12 < 48; ++n12) {
                        array4[7][n12] = array6[n12];
                    }
                    break;
                }
                case 8: {
                    for (int n13 = 0; n13 < 48; ++n13) {
                        array4[8][n13] = array6[n13];
                    }
                    break;
                }
                case 9: {
                    for (int n14 = 0; n14 < 48; ++n14) {
                        array4[9][n14] = array6[n14];
                    }
                    break;
                }
                case 10: {
                    for (int n15 = 0; n15 < 48; ++n15) {
                        array4[10][n15] = array6[n15];
                    }
                    break;
                }
                case 11: {
                    for (int n16 = 0; n16 < 48; ++n16) {
                        array4[11][n16] = array6[n16];
                    }
                    break;
                }
                case 12: {
                    for (int n17 = 0; n17 < 48; ++n17) {
                        array4[12][n17] = array6[n17];
                    }
                    break;
                }
                case 13: {
                    for (int n18 = 0; n18 < 48; ++n18) {
                        array4[13][n18] = array6[n18];
                    }
                    break;
                }
                case 14: {
                    for (int n19 = 0; n19 < 48; ++n19) {
                        array4[14][n19] = array6[n19];
                    }
                    break;
                }
                case 15: {
                    for (int n20 = 0; n20 < 48; ++n20) {
                        array4[15][n20] = array6[n20];
                    }
                    break;
                }
            }
        }
        final int[][] array7 = array4;
        final int[] array8 = array;
        final int[] array9 = new int[64];
        for (int n21 = 0, n22 = 1, n23 = 0; n21 < 4; ++n21, n22 += 2, n23 += 2) {
            for (int n24 = 7, n25 = 0; n24 >= 0; --n24, ++n25) {
                array9[(n21 << 3) + n25] = array8[(n24 << 3) + n22];
                array9[(n21 << 3) + n25 + 32] = array8[(n24 << 3) + n23];
            }
        }
        array = array9;
        final int[] array10 = new int[32];
        final int[] array11 = new int[32];
        final int[] array12 = new int[32];
        for (int n26 = 0; n26 < 32; ++n26) {
            array10[n26] = array[n26];
            array11[n26] = array[n26 + 32];
        }
        for (int n27 = 15; n27 >= 0; --n27) {
            for (int n28 = 0; n28 < 32; ++n28) {
                array12[n28] = array10[n28];
                array10[n28] = array11[n28];
            }
            final int[] array13 = new int[48];
            for (int n29 = 0; n29 < 48; ++n29) {
                array13[n29] = array7[n27][n29];
            }
            final int[] array14 = array11;
            final int[] array15 = new int[48];
            for (int n30 = 0; n30 < 8; ++n30) {
                if (n30 == 0) {
                    array15[n30 * 6] = array14[31];
                }
                else {
                    array15[n30 * 6] = array14[(n30 << 2) - 1];
                }
                array15[n30 * 6 + 1] = array14[n30 << 2];
                array15[n30 * 6 + 2] = array14[(n30 << 2) + 1];
                array15[n30 * 6 + 3] = array14[(n30 << 2) + 2];
                array15[n30 * 6 + 4] = array14[(n30 << 2) + 3];
                if (n30 == 7) {
                    array15[n30 * 6 + 5] = array14[0];
                }
                else {
                    array15[n30 * 6 + 5] = array14[(n30 << 2) + 4];
                }
            }
            final int[] b = b(array15, array13);
            final int[] array16 = new int[32];
            String s = "";
            final int[][] array17 = { { 14, 4, 13, 1, 2, 15, 11, 8, 3, 10, 6, 12, 5, 9, 0, 7 }, { 0, 15, 7, 4, 14, 2, 13, 1, 10, 6, 12, 11, 9, 5, 3, 8 }, { 4, 1, 14, 8, 13, 6, 2, 11, 15, 12, 9, 7, 3, 10, 5, 0 }, { 15, 12, 8, 2, 4, 9, 1, 7, 5, 11, 3, 14, 10, 0, 6, 13 } };
            final int[][] array18 = { { 15, 1, 8, 14, 6, 11, 3, 4, 9, 7, 2, 13, 12, 0, 5, 10 }, { 3, 13, 4, 7, 15, 2, 8, 14, 12, 0, 1, 10, 6, 9, 11, 5 }, { 0, 14, 7, 11, 10, 4, 13, 1, 5, 8, 12, 6, 9, 3, 2, 15 }, { 13, 8, 10, 1, 3, 15, 4, 2, 11, 6, 7, 12, 0, 5, 14, 9 } };
            final int[][] array19 = { { 10, 0, 9, 14, 6, 3, 15, 5, 1, 13, 12, 7, 11, 4, 2, 8 }, { 13, 7, 0, 9, 3, 4, 6, 10, 2, 8, 5, 14, 12, 11, 15, 1 }, { 13, 6, 4, 9, 8, 15, 3, 0, 11, 1, 2, 12, 5, 10, 14, 7 }, { 1, 10, 13, 0, 6, 9, 8, 7, 4, 15, 14, 3, 11, 5, 2, 12 } };
            final int[][] array20 = { { 7, 13, 14, 3, 0, 6, 9, 10, 1, 2, 8, 5, 11, 12, 4, 15 }, { 13, 8, 11, 5, 6, 15, 0, 3, 4, 7, 2, 12, 1, 10, 14, 9 }, { 10, 6, 9, 0, 12, 11, 7, 13, 15, 1, 3, 14, 5, 2, 8, 4 }, { 3, 15, 0, 6, 10, 1, 13, 8, 9, 4, 5, 11, 12, 7, 2, 14 } };
            final int[][] array21 = { { 2, 12, 4, 1, 7, 10, 11, 6, 8, 5, 3, 15, 13, 0, 14, 9 }, { 14, 11, 2, 12, 4, 7, 13, 1, 5, 0, 15, 10, 3, 9, 8, 6 }, { 4, 2, 1, 11, 10, 13, 7, 8, 15, 9, 12, 5, 6, 3, 0, 14 }, { 11, 8, 12, 7, 1, 14, 2, 13, 6, 15, 0, 9, 10, 4, 5, 3 } };
            final int[][] array22 = { { 12, 1, 10, 15, 9, 2, 6, 8, 0, 13, 3, 4, 14, 7, 5, 11 }, { 10, 15, 4, 2, 7, 12, 9, 5, 6, 1, 13, 14, 0, 11, 3, 8 }, { 9, 14, 15, 5, 2, 8, 12, 3, 7, 0, 4, 10, 1, 13, 11, 6 }, { 4, 3, 2, 12, 9, 5, 15, 10, 11, 14, 1, 7, 6, 0, 8, 13 } };
            final int[][] array23 = { { 4, 11, 2, 14, 15, 0, 8, 13, 3, 12, 9, 7, 5, 10, 6, 1 }, { 13, 0, 11, 7, 4, 9, 1, 10, 14, 3, 5, 12, 2, 15, 8, 6 }, { 1, 4, 11, 13, 12, 3, 7, 14, 10, 15, 6, 8, 0, 5, 9, 2 }, { 6, 11, 13, 8, 1, 4, 10, 7, 9, 5, 0, 15, 14, 2, 3, 12 } };
            final int[][] array24 = { { 13, 2, 8, 4, 6, 15, 11, 1, 10, 9, 3, 14, 5, 0, 12, 7 }, { 1, 15, 13, 8, 10, 3, 7, 4, 12, 5, 6, 11, 0, 14, 9, 2 }, { 7, 11, 4, 1, 9, 12, 14, 2, 0, 6, 10, 13, 15, 3, 5, 8 }, { 2, 1, 14, 7, 4, 10, 8, 13, 15, 12, 9, 0, 3, 5, 6, 11 } };
            for (int n31 = 0; n31 < 8; ++n31) {
                final int n32 = (b[n31 * 6] << 1) + b[n31 * 6 + 5];
                final int n33 = (b[n31 * 6 + 1] << 1 << 1 << 1) + (b[n31 * 6 + 2] << 1 << 1) + (b[n31 * 6 + 3] << 1) + b[n31 * 6 + 4];
                switch (n31) {
                    case 0: {
                        s = a(array17[n32][n33]);
                        break;
                    }
                    case 1: {
                        s = a(array18[n32][n33]);
                        break;
                    }
                    case 2: {
                        s = a(array19[n32][n33]);
                        break;
                    }
                    case 3: {
                        s = a(array20[n32][n33]);
                        break;
                    }
                    case 4: {
                        s = a(array21[n32][n33]);
                        break;
                    }
                    case 5: {
                        s = a(array22[n32][n33]);
                        break;
                    }
                    case 6: {
                        s = a(array23[n32][n33]);
                        break;
                    }
                    case 7: {
                        s = a(array24[n32][n33]);
                        break;
                    }
                }
                array16[n31 << 2] = Integer.parseInt(s.substring(0, 1));
                array16[(n31 << 2) + 1] = Integer.parseInt(s.substring(1, 2));
                array16[(n31 << 2) + 2] = Integer.parseInt(s.substring(2, 3));
                array16[(n31 << 2) + 3] = Integer.parseInt(s.substring(3, 4));
            }
            final int[] array25 = array16;
            final int[] array26;
            (array26 = new int[32])[0] = array25[15];
            array26[1] = array25[6];
            array26[2] = array25[19];
            array26[3] = array25[20];
            array26[4] = array25[28];
            array26[5] = array25[11];
            array26[6] = array25[27];
            array26[7] = array25[16];
            array26[8] = array25[0];
            array26[9] = array25[14];
            array26[10] = array25[22];
            array26[11] = array25[25];
            array26[12] = array25[4];
            array26[13] = array25[17];
            array26[14] = array25[30];
            array26[15] = array25[9];
            array26[16] = array25[1];
            array26[17] = array25[7];
            array26[18] = array25[23];
            array26[19] = array25[13];
            array26[20] = array25[31];
            array26[21] = array25[26];
            array26[22] = array25[2];
            array26[23] = array25[8];
            array26[24] = array25[18];
            array26[25] = array25[12];
            array26[26] = array25[29];
            array26[27] = array25[5];
            array26[28] = array25[21];
            array26[29] = array25[10];
            array26[30] = array25[3];
            array26[31] = array25[24];
            final int[] b2 = b(array26, array12);
            for (int n34 = 0; n34 < 32; ++n34) {
                array11[n34] = b2[n34];
            }
        }
        final int[] array27 = new int[64];
        for (int n35 = 0; n35 < 32; ++n35) {
            array27[n35] = array11[n35];
            array27[n35 + 32] = array10[n35];
        }
        final int[] array28 = array27;
        final int[] array29;
        (array29 = new int[64])[0] = array28[39];
        array29[1] = array28[7];
        array29[2] = array28[47];
        array29[3] = array28[15];
        array29[4] = array28[55];
        array29[5] = array28[23];
        array29[6] = array28[63];
        array29[7] = array28[31];
        array29[8] = array28[38];
        array29[9] = array28[6];
        array29[10] = array28[46];
        array29[11] = array28[14];
        array29[12] = array28[54];
        array29[13] = array28[22];
        array29[14] = array28[62];
        array29[15] = array28[30];
        array29[16] = array28[37];
        array29[17] = array28[5];
        array29[18] = array28[45];
        array29[19] = array28[13];
        array29[20] = array28[53];
        array29[21] = array28[21];
        array29[22] = array28[61];
        array29[23] = array28[29];
        array29[24] = array28[36];
        array29[25] = array28[4];
        array29[26] = array28[44];
        array29[27] = array28[12];
        array29[28] = array28[52];
        array29[29] = array28[20];
        array29[30] = array28[60];
        array29[31] = array28[28];
        array29[32] = array28[35];
        array29[33] = array28[3];
        array29[34] = array28[43];
        array29[35] = array28[11];
        array29[36] = array28[51];
        array29[37] = array28[19];
        array29[38] = array28[59];
        array29[39] = array28[27];
        array29[40] = array28[34];
        array29[41] = array28[2];
        array29[42] = array28[42];
        array29[43] = array28[10];
        array29[44] = array28[50];
        array29[45] = array28[18];
        array29[46] = array28[58];
        array29[47] = array28[26];
        array29[48] = array28[33];
        array29[49] = array28[1];
        array29[50] = array28[41];
        array29[51] = array28[9];
        array29[52] = array28[49];
        array29[53] = array28[17];
        array29[54] = array28[57];
        array29[55] = array28[25];
        array29[56] = array28[32];
        array29[57] = array28[0];
        array29[58] = array28[40];
        array29[59] = array28[8];
        array29[60] = array28[48];
        array29[61] = array28[16];
        array29[62] = array28[56];
        array29[63] = array28[24];
        return array29;
    }

    private static int[] b(final int[] array, final int[] array2) {
        final int[] array3 = new int[array.length];
        for (int i = 0; i < array.length; ++i) {
            array3[i] = (array[i] ^ array2[i]);
        }
        return array3;
    }

    private static String a(final int n) {
        String s = "";
        switch (n) {
            case 0: {
                s = "0000";
                break;
            }
            case 1: {
                s = "0001";
                break;
            }
            case 2: {
                s = "0010";
                break;
            }
            case 3: {
                s = "0011";
                break;
            }
            case 4: {
                s = "0100";
                break;
            }
            case 5: {
                s = "0101";
                break;
            }
            case 6: {
                s = "0110";
                break;
            }
            case 7: {
                s = "0111";
                break;
            }
            case 8: {
                s = "1000";
                break;
            }
            case 9: {
                s = "1001";
                break;
            }
            case 10: {
                s = "1010";
                break;
            }
            case 11: {
                s = "1011";
                break;
            }
            case 12: {
                s = "1100";
                break;
            }
            case 13: {
                s = "1101";
                break;
            }
            case 14: {
                s = "1110";
                break;
            }
            case 15: {
                s = "1111";
                break;
            }
        }
        return s;
    }

    static {
        Stat.a = "";
        Stat.b = null;
        c = new HashMap<String, String>();
        d = new HashMap<String, Date>();
    }
}
