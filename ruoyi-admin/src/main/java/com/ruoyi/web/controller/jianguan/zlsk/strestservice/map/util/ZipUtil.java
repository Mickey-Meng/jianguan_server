package com.ruoyi.web.controller.jianguan.zlsk.strestservice.map.util;

import java.util.zip.*;
import java.io.*;

public class ZipUtil
{
    public static void doCompress(final String[] filePaths, final String zipFile) throws IOException {
        doCompress(filePaths, new File(zipFile));
    }

    public static void doCompress(final String[] filePaths, final File zipFile) throws IOException {
        ZipOutputStream out = null;
        try {
            out = new ZipOutputStream(new FileOutputStream(zipFile));
            for (int i = 0; i < filePaths.length; ++i) {
                final File file = new File(filePaths[i]);
                doZip(file, out, "");
            }
        }
        catch (Exception e) {
            throw e;
        }
        finally {
            out.close();
        }
    }

    public static void doZip(final File file, final ZipOutputStream out, final String dir) throws IOException {
        String entryName = null;
        if (!"".equals(dir)) {
            entryName = dir + "/" + file.getName();
        }
        else {
            entryName = file.getName();
        }
        final ZipEntry entry = new ZipEntry(entryName);
        out.putNextEntry(entry);
        int len = 0;
        final byte[] buffer = new byte[1024];
        final FileInputStream fis = new FileInputStream(file);
        while ((len = fis.read(buffer)) > 0) {
            out.write(buffer, 0, len);
            out.flush();
        }
        out.closeEntry();
        fis.close();
    }
}
