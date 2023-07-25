package com.ruoyi.common.utils.file;

import cn.hutool.core.io.FileUtil;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.poi.util.IOUtils;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * 文件处理工具类
 *
 * @author Lion Li
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FileUtils extends FileUtil {

    /**
     * 下载文件名重新编码
     *
     * @param response     响应对象
     * @param realFileName 真实文件名
     */
    public static void setAttachmentResponseHeader(HttpServletResponse response, String realFileName) throws UnsupportedEncodingException {
        String percentEncodedFileName = percentEncode(realFileName);

        StringBuilder contentDispositionValue = new StringBuilder();
        contentDispositionValue.append("attachment; filename=")
            .append(percentEncodedFileName)
            .append(";")
            .append("filename*=")
            .append("utf-8''")
            .append(percentEncodedFileName);

        response.addHeader("Access-Control-Expose-Headers", "Content-Disposition,download-filename");
        response.setHeader("Content-disposition", contentDispositionValue.toString());
        response.setHeader("download-filename", percentEncodedFileName);
    }

    /**
     * 百分号编码工具方法
     *
     * @param s 需要百分号编码的字符串
     * @return 百分号编码后的字符串
     */
    public static String percentEncode(String s) throws UnsupportedEncodingException {
        String encode = URLEncoder.encode(s, StandardCharsets.UTF_8.toString());
        return encode.replaceAll("\\+", "%20");
    }

    public static MultipartFile getMultipartFile(File file) throws IOException {
        final DiskFileItem diskFileItem = new DiskFileItem("file", MediaType.MULTIPART_FORM_DATA_VALUE, true, file.getName(), 100000000, file.getParentFile());
        try(InputStream inputStream = new FileInputStream(file);
            OutputStream outputStream = diskFileItem.getOutputStream()) {
            IOUtils.copy(inputStream, outputStream);
            return new CommonsMultipartFile(diskFileItem);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }
}
