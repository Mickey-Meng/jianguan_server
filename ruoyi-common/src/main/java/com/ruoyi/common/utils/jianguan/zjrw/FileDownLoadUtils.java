package com.ruoyi.common.utils.jianguan.zjrw;


import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Base64;


/**
 * @author ：lin_zhixiong
 * @date ：Created in 2021/6/28 9:49 上午
 * @version: V1.0
 * @slogan: 天下风云出我辈，一入代码苦耕耘
 * @description:
 **/
public class FileDownLoadUtils {

    /**
     * 下载文件或者在线预览文件，在线预览只支持jpg,png等
     *
     * @param filename  下载的文件名称
     * @param bytes     文件转换的字节码
     * @param review    是否在线预览
     * @param userAgent 浏览器类型  使用 request.getHeader("User-Agent")获取
     * @return
     * @throws UnsupportedEncodingException
     */
    public static ResponseEntity<Resource> download(String filename, byte[] bytes, boolean review,
                                                    String userAgent) throws UnsupportedEncodingException
    {
        return download(filename, bytes, review ? parseMediaType(filename) : MediaType.APPLICATION_OCTET_STREAM,
                userAgent);
    }

    private static ResponseEntity<Resource> download(String filename, byte[] bytes, MediaType mediaType,
                                                     String userAgent) throws UnsupportedEncodingException
    {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        headers.add("charset", "utf-8");
        String finalName = URLEncoder.encode(filename, "UTF-8");
        if(userAgent != null) {
            if(userAgent.toLowerCase().indexOf("firefox") > -1) {
                finalName = "=?UTF-8?B?" + new String(Base64.getEncoder().encode(filename.getBytes("UTF-8"))) + "?=";
            }
            else if(userAgent.toLowerCase().indexOf("safari") > -1) {
                finalName = new String(finalName.getBytes("UTF-8"), "ISO8859-1");
            }
        }

        headers.add("Content-Disposition", "filename=\"" + finalName + "\"");
        return ((ResponseEntity.BodyBuilder) ResponseEntity.ok().headers(headers)).contentLength((long) bytes.length)
                .contentType(mediaType)
                .body(new ByteArrayResource(bytes));
    }

    /**
     * 根据文件名称转换文件
     * @param fileName
     * @return
     */
    public static MediaType parseMediaType(String fileName) {
        MediaType mediaType = MediaType.APPLICATION_OCTET_STREAM;
        int dot = fileName != null ? fileName.lastIndexOf(".") : -1;
        if(dot < 0) {
            return mediaType;
        }
        else {
            String suffix = fileName.substring(dot + 1).toUpperCase();
            if(suffix.equalsIgnoreCase("GIF")) {
                mediaType = MediaType.IMAGE_GIF;
            }
            else if(suffix.equalsIgnoreCase("PNG")) {
                mediaType = MediaType.IMAGE_PNG;
            }
            else if(!suffix.equalsIgnoreCase("JPG") && !suffix.equalsIgnoreCase("JPEG")) {
                if(suffix.equalsIgnoreCase("XML")) {
                    mediaType = MediaType.APPLICATION_XML;
                }
                else if(suffix.equalsIgnoreCase("PDF")) {
                    mediaType = MediaType.APPLICATION_PDF;
                }
            }
            else {
                mediaType = MediaType.IMAGE_JPEG;
            }

            return mediaType;
        }
    }

    /**
     * 删除文件夹里面的所有内容
     * @param file
     * @throws IOException
     */
    public static void deleteFile(File file) throws IOException {
        /**
         * File[] listFiles()
         *  返回一个抽象路径名数组，这些路径名表示此抽象路径名表示的目录中的文件。
         */
        File[] files = file.listFiles();
        if (files!=null){//如果包含文件进行删除操作
            for (int i = 0; i <files.length ; i++) {
                if (files[i].isFile()){
                    //删除子文件
                    files[i].delete();
                }else if (files[i].isDirectory()){
                    //通过递归的方法找到子目录的文件
                    deleteFile(files[i]);
                }
                files[i].delete();//删除子目录
            }
        }
    }
}

