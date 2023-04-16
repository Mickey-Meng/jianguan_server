package com.ruoyi.web.controller.jianguan.zlsk.stframebase.core;

import javax.servlet.annotation.*;
import java.lang.reflect.*;
import javax.servlet.*;
import java.net.*;
import java.io.*;

@WebListener
public class STServletContextListener implements ServletContextListener
{
    private static final String SERVICE_DIR;
    private static final String JAR_SUFFIX = ".jar";
    private static Method addURL;
    private static URLClassLoader classLoader;

    public void contextDestroyed(final ServletContextEvent arg0) {
    }

    public void contextInitialized(final ServletContextEvent arg0) {
    }

    private void loadService() {
        if (this.init()) {
            this.addToClassPath(this.getServicePath());
            final URL[] urLs;
            final URL[] urls = urLs = STServletContextListener.classLoader.getURLs();
            for (final URL url : urLs) {
                try {
                    System.out.println(url.toURI().toString());
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private boolean init() {
        try {
            (STServletContextListener.addURL = URLClassLoader.class.getDeclaredMethod("addURL", URL.class)).setAccessible(true);
            STServletContextListener.classLoader = (URLClassLoader)ClassLoader.getSystemClassLoader();
            return true;
        }
        catch (Exception e) {
            STServletContextListener.addURL = null;
            STServletContextListener.classLoader = null;
            e.printStackTrace();
            return false;
        }
    }

    private File getServicePath() {
        String filePath = this.getClass().getResource("/").getPath();
        final File fp = new File(filePath);
        filePath = fp.getParentFile().getPath() + File.separator + STServletContextListener.SERVICE_DIR;
        System.out.println(filePath);
        return new File(filePath);
    }

    private void addToClassPath(final File file) {
        if (file == null || !file.exists()) {
            return;
        }
        if (file.isDirectory()) {
            this.addUrl(file);
            final File[] listFiles;
            final File[] fa = listFiles = file.listFiles();
            for (final File f : listFiles) {
                this.addToClassPath(f);
            }
        }
        else if (file.isFile() && file.getAbsolutePath().endsWith(".jar")) {
            this.addUrl(file);
        }
    }

    private void addUrl(final File file) {
        try {
            STServletContextListener.addURL.invoke(STServletContextListener.classLoader, file.toURI().toURL());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    static {
        SERVICE_DIR = "service" + File.separator;
        STServletContextListener.addURL = null;
        STServletContextListener.classLoader = null;
    }
}
