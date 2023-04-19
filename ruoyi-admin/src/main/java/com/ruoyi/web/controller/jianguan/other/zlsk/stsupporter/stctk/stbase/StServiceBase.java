package com.ruoyi.web.controller.jianguan.other.zlsk.stsupporter.stctk.stbase;

import com.ruoyi.web.controller.jianguan.other.zlsk.stsupporter.stctk.util.DateUtil;
import com.ruoyi.web.controller.jianguan.other.zlsk.stsupporter.stctk.util.FileUtil;
import com.ruoyi.web.controller.jianguan.other.zlsk.stsupporter.stctk.util.NetUtil;
import liquibase.pro.packaged.K;
import javax.servlet.http.*;
import org.springframework.web.multipart.*;
import org.yaml.snakeyaml.*;
import java.io.*;
import java.util.*;
import java.text.*;

public abstract class StServiceBase
{
    private static final String a;
    private static final String b;
    private static final String c;
    private static final String d;
    private static Map<String, Object> e;

    public File getClassFile() {
        return new File(this.getClassFilePath());
    }

    public String getClassFilePath() {
        File file;
        try {
            file = new File(this.getClass().getResource("/").toURI().getPath());
        }
        catch (Exception ex) {
            file = new File(this.getClass().getResource("/").getPath());
        }
        return file.getPath() + File.separator;
    }

    public File getWebInfFile() {
        return this.getClassFile().getParentFile();
    }

    public String getWebInfFilePath() {
        return this.getWebInfFile().getPath() + File.separator;
    }

    public File getTempFile() {
        return new File(this.getTempFilePath());
    }

    public String getTempFilePath() {
        return this.getWebInfFile().getParentFile().getPath() + File.separator + StServiceBase.a;
    }

    public String getConfigPath() {
        return this.getWebInfFilePath() + StServiceBase.b;
    }

    public String getFilesPath() {
        return this.getWebInfFilePath() + StServiceBase.c;
    }

    public String getSupporterPath() {
        return this.getWebInfFilePath() + StServiceBase.d;
    }

    public File getUserHomeFile() {
        return new File(this.getUserHomePath());
    }

    public String getUserHomePath() {
        return System.getProperty("user.home") + File.separator;
    }

    public String getJarBaseName() {
        final String simpleName = this.getClass().getSimpleName();
        return simpleName.substring(2, simpleName.length() - 7).toLowerCase() + "service";
    }

    public String getServiceConfigPath() {
        return this.getFilesPath() + "st_" + this.getJarBaseName() + File.separator;
    }

    public Object getServiceConfig(final String s) {
        return this.getServiceConfig(s, false);
    }

    public Object getServiceConfig(final String s, final boolean b) {
        if (StServiceBase.e == null) {
            StServiceBase.e = new HashMap<String, Object>();
        }
        final String string = this.getServiceConfigPath() + s;
        Object o;
        if (!b && StServiceBase.e.containsKey(string)) {
            o = StServiceBase.e.get(string);
        }
        else {
            if ((o = a(string)) == null) {
                return null;
            }
            StServiceBase.e.put(string, o);
        }
        return o;
    }

    public Object getServiceConfig(final String s, final String s2) {
        final Object serviceConfig;
        if ((serviceConfig = this.getServiceConfig(s)) == null) {
            return null;
        }
        return ((Map<K, Object>)serviceConfig).get(s2);
    }

    public Object getServiceConfig(final String s, final String s2, final boolean b) {
        final Object serviceConfig;
        if ((serviceConfig = this.getServiceConfig(s, b)) == null) {
            return null;
        }
        return ((Map<K, Object>)serviceConfig).get(s2);
    }

    public String getServiceConfigString(final String s, final String s2) {
        final Object serviceConfig;
        if ((serviceConfig = this.getServiceConfig(s, s2)) == null) {
            return null;
        }
        return serviceConfig.toString();
    }

    public String getServiceConfigString(final String s, final String s2, final boolean b) {
        final Object serviceConfig;
        if ((serviceConfig = this.getServiceConfig(s, s2, b)) == null) {
            return null;
        }
        return serviceConfig.toString();
    }

    public static HttpServletRequest getRequest() {
        return NetUtil.getRequest();
    }

    public static String getBasePath() {
        return NetUtil.getBasePath();
    }

    public static String getLocalHostIp() {
        try {
            return NetUtil.getLocalHostIp();
        }
        catch (Exception ex) {
            return null;
        }
    }

    public static String getRemoteIp() {
        try {
            return NetUtil.getRemoteIp();
        }
        catch (Exception ex) {
            return null;
        }
    }

    public static String getRealRemoteIp() {
        try {
            return NetUtil.getRealRemoteIp();
        }
        catch (Exception ex) {
            return null;
        }
    }

    public List<String> saveFile(HttpServletRequest iterator, String s, String string, String... string2) {
        final MultipartHttpServletRequest multipartHttpServletRequest;
        if ((multipartHttpServletRequest = (MultipartHttpServletRequest)iterator) == null) {
            return null;
        }
        final List files = multipartHttpServletRequest.getFiles((s == null || s.isEmpty()) ? "file" : s);
        SimpleDateFormat s1 = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        final StringBuilder sb = new StringBuilder();
        final StringBuilder sb2 = new StringBuilder();
        sb.append(string);
        for (int i = 0; i < string2.length; ++i) {
            sb.append(string2[i]).append(File.separator);
            sb2.append(string2[i]).append("/");
        }
        string = sb.toString();
        string2 = (String[])(Object)sb2.toString();
        final ArrayList<String> list = new ArrayList<String>();
        iterator = (HttpServletRequest)files.iterator();
        while (((Iterator)iterator).hasNext()) {
            final MultipartFile multipartFile;
            final String originalFilename;
            String s2;
            if ((originalFilename = (multipartFile = ((Iterator<MultipartFile>)iterator).next()).getOriginalFilename()).indexOf(".") >= 0) {
                final String[] split = originalFilename.split("\\.");
                s2 = DateUtil.format(new Date()) + "." + split[split.length - 1];
            }
            else {
                s2 = DateUtil.format(new Date());
            }
            try {
                FileUtil.saveFile(multipartFile.getBytes(), string, s2);
                list.add((String)(Object)string2 + s2);
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return list;
    }

    private static Object a(final String s) {
        try {
            final Yaml yaml = new Yaml();
            final FileInputStream fileInputStream = new FileInputStream(s);
            final Object load = yaml.load((InputStream)fileInputStream);
            fileInputStream.close();
            return load;
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    static {
        a = "temp" + File.separator;
        b = "config" + File.separator;
        c = "files" + File.separator;
        d = "supporter" + File.separator;
        StServiceBase.e = null;
    }
}
