package com.ruoyi.web.controller.jianguan.zlsk.stsupporter.stctk.util;

import com.ruoyi.web.controller.jianguan.zlsk.stsupporter.stctk.stbase.*;
import java.util.*;
import java.io.*;

public class FileUtil extends StSupporterBase
{
    public static boolean fileExists(final String s) {
        return s != null && fileExists(new File(s));
    }

    public static final boolean fileExists(final File file) {
        return file != null && file.exists() && file.isFile();
    }

    public static boolean directoryExists(final String s) {
        return s != null && directoryExists(new File(s));
    }

    public static final boolean directoryExists(final File file) {
        return file != null && file.exists() && file.isDirectory();
    }

    public static final boolean delete(final String s, final boolean b) {
        return delete(new File(s), b);
    }

    public static boolean delete(final File file, final boolean b) {
        if (file == null) {
            return false;
        }
        if (file.isFile()) {
            return deleteFile(file);
        }
        return file.isDirectory() && deleteDirectory(file, b);
    }

    public static boolean deleteFile(final String s) {
        final File file;
        if (!(file = new File(s)).isFile() || !file.exists()) {
            return false;
        }
        file.delete();
        return true;
    }

    public static boolean deleteFile(final File file) {
        if (file == null || !file.isFile() || !file.exists()) {
            return false;
        }
        file.delete();
        return true;
    }

    public static final boolean deleteDirectory(final String s, final boolean b) {
        return deleteDirectory(new File(s), b);
    }

    public static boolean deleteDirectory(final File file, final boolean b) {
        if (file == null || !file.isDirectory() || !file.exists()) {
            return false;
        }
        final File[] listFiles = file.listFiles();
        if (!b && listFiles.length > 0) {
            return false;
        }
        if (listFiles != null) {
            for (int i = 0; i < listFiles.length; ++i) {
                final File file2;
                if ((file2 = listFiles[i]).isFile()) {
                    file2.delete();
                }
                else if (file2.isDirectory() && b) {
                    deleteDirectory(file2, b);
                }
            }
        }
        file.delete();
        return true;
    }

    public static int clearDir(final File file, final boolean b, final boolean b2) {
        return clearDir(file, 604800000L, b, b2);
    }

    public static int clearDir(final File file, final long n, final boolean b, final boolean b2) {
        if (file == null || !file.isDirectory() || !file.exists()) {
            return 0;
        }
        final File[] listFiles = file.listFiles();
        if (!b && listFiles.length > 0) {
            return 0;
        }
        int n2 = 0;
        final long time = new Date().getTime();
        if (listFiles != null) {
            for (int i = 0; i < listFiles.length; ++i) {
                final File file2;
                if ((file2 = listFiles[i]).isFile() && time - file2.lastModified() >= n) {
                    file2.delete();
                    ++n2;
                }
                else if (file2.isDirectory() && b) {
                    n2 += clearDir(file2, n, b, b2);
                }
            }
        }
        if (b2 && file.listFiles().length == 0 && time - file.lastModified() >= n) {
            file.delete();
            ++n2;
        }
        return n2;
    }

    public static void saveFile(final byte[] array, final String s, final String s2) throws Exception {
        final File file;
        if (!(file = new File(s)).exists()) {
            file.mkdirs();
        }
        final FileOutputStream fileOutputStream;
        (fileOutputStream = new FileOutputStream(s + s2)).write(array);
        fileOutputStream.flush();
        fileOutputStream.close();
    }

    public static void saveFileToDb() {
    }
}
