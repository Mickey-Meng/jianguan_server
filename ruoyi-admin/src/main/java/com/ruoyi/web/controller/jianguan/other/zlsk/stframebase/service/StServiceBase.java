package com.ruoyi.web.controller.jianguan.other.zlsk.stframebase.service;

import com.ruoyi.web.controller.jianguan.other.zlsk.stsupporter.stctk.util.NetUtil;

import java.util.*;
import javax.servlet.http.*;

import org.yaml.snakeyaml.*;
import java.io.*;

public class StServiceBase
{
    private static final String TEMP_DIR;
    private static final String FILES_DIR;
    private static final String SUPPORT_DIR;
    private static Map<String, Object> ccfg;

    public File getClassFile() {
        return new File(this.getClassFilePath());
    }

    public String getClassFilePath() {
        return this.getClass().getResource("/").getPath() + File.separator;
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
        return this.getWebInfFile().getParentFile().getPath() + File.separator + StServiceBase.TEMP_DIR;
    }

    public String getFilesPath() {
        return this.getWebInfFilePath() + StServiceBase.FILES_DIR;
    }

    public String getSupporterPath() {
        return this.getWebInfFilePath() + StServiceBase.SUPPORT_DIR;
    }

    public File getUserHomeFile() {
        return new File(this.getUserHomePath());
    }

    public String getUserHomePath() {
        return System.getProperty("user.home") + File.separator;
    }

    public String getJarBaseName() {
        String s = this.getClass().getSimpleName();
        s = s.substring(2, s.length() - 7).toLowerCase() + "restservice";
        return s;
    }

    public String getServiceConfigPath() {
        return this.getFilesPath() + "st_" + this.getJarBaseName() + File.separator;
    }

    public Object getServiceConfig(final String fileName) {
        return this.getServiceConfig(fileName, false);
    }

    public Object getServiceConfig(final String fileName, final boolean forceReload) {
        if (StServiceBase.ccfg == null) {
            StServiceBase.ccfg = new HashMap<String, Object>();
        }
        Object m = null;
        final String fp = this.getServiceConfigPath() + fileName;
        if (!forceReload && StServiceBase.ccfg.containsKey(fp)) {
            m = StServiceBase.ccfg.get(fp);
        }
        else {
            m = this.loadYamlFile(fp);
            if (m == null) {
                return null;
            }
            StServiceBase.ccfg.put(fp, m);
        }
        return m;
    }

    public Object getServiceConfig(final String fileName, final String key) {
        final Object m = this.getServiceConfig(fileName);
        if (m == null) {
            return null;
        }
        return ((Map)m).get(key);
    }

    public Object getServiceConfig(final String fileName, final String key, final boolean forceReload) {
        final Object m = this.getServiceConfig(fileName, forceReload);
        if (m == null) {
            return null;
        }
        return ((Map)m).get(key);
    }

    public String getServiceConfigString(final String fileName, final String key) {
        final Object o = this.getServiceConfig(fileName, key);
        return (o == null) ? null : o.toString();
    }

    public String getServiceConfigString(final String fileName, final String key, final boolean forceReload) {
        final Object o = this.getServiceConfig(fileName, key, forceReload);
        return (o == null) ? null : o.toString();
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

    private Object loadYamlFile(final String filePath) {
        try {
            final Yaml yaml = new Yaml();
            final FileInputStream fis = new FileInputStream(filePath);
            final Object obj = yaml.load((InputStream)fis);
            fis.close();
            return obj;
        }
        catch (Exception ex) {
            return null;
        }
    }

    static {
        TEMP_DIR = "temp" + File.separator;
        FILES_DIR = "files" + File.separator;
        SUPPORT_DIR = "supporter" + File.separator;
        StServiceBase.ccfg = null;
    }
}
