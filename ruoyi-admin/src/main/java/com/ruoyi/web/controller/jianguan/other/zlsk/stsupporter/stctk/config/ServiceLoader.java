package com.ruoyi.web.controller.jianguan.other.zlsk.stsupporter.stctk.config;

import com.ruoyi.web.controller.jianguan.other.zlsk.stsupporter.stctk.stbase.StSupporterBase;
import java.lang.reflect.*;
import java.io.*;
import java.net.*;

public class ServiceLoader extends StSupporterBase
{
    private static final String a;
    private static Method b;
    private static URLClassLoader c;

    public void loadService() {
        if (this.a()) {
            final String string = new File(this.getClass().getResource("/").getPath()).getParentFile().getPath() + File.separator + ServiceLoader.a;
            System.out.println(string);
            this.a(new File(string));
            URL[] urLs;
            for (int length = (urLs = ServiceLoader.c.getURLs()).length, i = 0; i < length; ++i) {
                final URL url = urLs[i];
                try {
                    System.out.println(url.toURI().toString());
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    private boolean a() {
        try {
            (ServiceLoader.b = URLClassLoader.class.getDeclaredMethod("addURL", URL.class)).setAccessible(true);
            ServiceLoader.c = (URLClassLoader)ClassLoader.getSystemClassLoader();
            ServiceLoader.c = (URLClassLoader)this.getClass().getClassLoader();
            return true;
        }
        catch (Exception ex) {
            ServiceLoader.b = null;
            ServiceLoader.c = null;
            ex.printStackTrace();
            return false;
        }
    }

    private void a(final File file) {
        if (file == null || !file.exists()) {
            return;
        }
        if (!file.isDirectory()) {
            if (file.isFile() && file.getAbsolutePath().endsWith(".jar")) {
                b(file);
            }
            return;
        }
        if (file.getAbsolutePath().toLowerCase().contains("_deprecated")) {
            return;
        }
        b(file);
        File[] listFiles;
        for (int length = (listFiles = file.listFiles()).length, i = 0; i < length; ++i) {
            this.a(listFiles[i]);
        }
    }

    private static void b(final File file) {
        try {
            ServiceLoader.b.invoke(ServiceLoader.c, file.toURI().toURL());
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    static {
        a = "service" + File.separator;
        ServiceLoader.b = null;
        ServiceLoader.c = null;
    }
}
