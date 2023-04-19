package com.ruoyi.web.controller.jianguan.other.zlsk.strestservice.map.util;

import java.io.*;

public class FileUtil
{
    public static void uploadFile(final byte[] file, final String filePath, final String fileName) throws IOException {
        final File targetFile = new File(filePath);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
        final FileOutputStream out = new FileOutputStream(filePath + fileName);
        out.write(file);
        out.flush();
        out.close();
    }

    public static boolean deleteFile(final String path) {
        final File file = new File(path);
        return file.exists() && file.delete();
    }

    public static String getProjectFile() {
        final String path = Thread.currentThread().getContextClassLoader().getResource("").toString();
        System.out.println(path);
        return path;
    }
}
