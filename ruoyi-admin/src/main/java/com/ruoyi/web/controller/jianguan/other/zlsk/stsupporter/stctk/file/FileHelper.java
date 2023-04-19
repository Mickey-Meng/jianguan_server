package com.ruoyi.web.controller.jianguan.other.zlsk.stsupporter.stctk.file;

import com.ruoyi.web.controller.jianguan.other.zlsk.stsupporter.stctk.stbase.StSupporterBase;
import org.yaml.snakeyaml.*;
import java.io.*;
import java.util.*;

public class FileHelper extends StSupporterBase
{
    private static String a;
    private static String b;
    private static Map<String, Object> c;
    private static Map<String, String> d;

    public FileHelper() {
        FileHelper.a = this.getClass().getResource("/config").getPath();
        final String string = FileHelper.a + File.separator + "fileConfig.yml";
        if (FileHelper.c == null) {
            FileHelper.c = (Map<String, Object>)a(string);
        }
        FileHelper.b = new File(FileHelper.a).getParentFile().getParent() + File.separator + "files" + File.separator;
        final File file;
        if (!(file = new File(FileHelper.b)).exists()) {
            file.mkdir();
        }
    }

    public String readFile(String filePath, final String s, final String s2, String s3) {
        if ((filePath = this.getFilePath(filePath, s, s2)) == null) {
            return "";
        }
        if (!new File(filePath).exists()) {
            return "";
        }
        try {
            if (s3 == null) {
                s3 = "UTF-8";
            }
            final FileInputStream fileInputStream = new FileInputStream(filePath);
            byte[] array = new byte[1024];
            final StringBuffer sb = new StringBuffer();
            while (fileInputStream.read(array) != -1) {
                sb.append(new String(array, s3));
                array = new byte[1024];
            }
            fileInputStream.close();
            return sb.toString();
        }
        catch (Exception ex) {
            return "";
        }
    }

    public void writeFile(String filePath, final String s, final String s2, final String s3, String s4) {
        if (s3 == null || s3.equals("")) {
            return;
        }
        if ((filePath = this.getFilePath(filePath, s, s2)) == null) {
            return;
        }
        final File file;
        if ((file = new File(filePath)).exists()) {
            file.delete();
        }
        try {
            file.createNewFile();
            if (s4 == null) {
                s4 = "UTF-8";
            }
            final BufferedWriter bufferedWriter;
            (bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), s4))).write(s3);
            bufferedWriter.flush();
            bufferedWriter.close();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public String getFilePath(String s, final String s2, final String s3) {
        final String string = ((s2 == null) ? "" : s2) + "_" + s;
        if (FileHelper.d.containsKey(string)) {
            return FileHelper.d.get(string);
        }
        try {
            s = FileHelper.c.get("configs").toString();// .get(FileHelper.c.get("config").toString()).get(s).toString().replace("/", File.separator);
            s = FileHelper.b + s;
            final File file;
            if (!(file = new File(s)).exists()) {
                file.mkdirs();
            }
            s += ((s2 == null) ? "_c" : s2);
            if (s3 != null && !s3.equals("")) {
                s = s + "." + s3;
            }
            FileHelper.d.put(string, s);
            return s;
        }
        catch (Exception ex) {
            return null;
        }
    }

    private static Object a(final String s) {
        try {
            return new Yaml().load((InputStream)new FileInputStream(s));
        }
        catch (Exception ex) {
            return null;
        }
    }

    static {
        FileHelper.a = "";
        FileHelper.b = "";
        FileHelper.c = null;
        FileHelper.d = new HashMap<String, String>();
    }
}
