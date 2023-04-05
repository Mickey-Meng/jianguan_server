package com.ruoyi.jianguan.common.service;


import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.j256.simplemagic.ContentInfoUtil;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSDownloadStream;
import com.mongodb.client.gridfs.model.GridFSFile;
import com.ruoyi.common.core.domain.object.ResponseBase;
import com.ruoyi.jianguan.common.dao.FileMapper;
import com.ruoyi.jianguan.common.dao.ZjFileDAO;
import com.ruoyi.jianguan.common.domain.entity.ZjFile;
import com.ruoyi.common.utils.jianguan.zjrw.FileDownLoadUtils;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.codec.binary.Base64;
import org.apache.poi.util.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;


@Service
public class FileService {

    Logger log = LoggerFactory.getLogger(FileService.class);

    @Autowired
    GridFSBucket gridFSBucket;

    @Autowired
    private FileMapper fileMapper;
//    private static final String[] FILE_TYPES =
//            new String[] {
//                    "jpg", "bmp", "jpeg", "png", "gif", "tif", "pcx", "tga", "exif", "exif", "svg",
//                    "psd", "cdr", "pcd", "dxf", "ufo", "eps", "ai", "raw", "WMF", "webp"
//            };


    @Autowired
    ZjFileDAO zjFileDAO;

    @Value("${temporary.file}")
    private String fileUrl;
    @Value("${zhuji.video}")
    private String videoUrl;

    public Integer storZjFile(ZjFile zjFile) {
        int insert = zjFileDAO.insert(zjFile);
        return insert;
    }

    public Integer modifyFile(ZjFile zjFile) {
        int i = zjFileDAO.updateByPrimaryKeySelective(zjFile);
        return i;
    }

    public List<ZjFile> getStoreFileType(Integer type, Integer projectId) {
        List<ZjFile> files = zjFileDAO.selectByType(type, projectId);
        return files;
    }

    public Integer del(Integer id) {
        String fileId = zjFileDAO.getFileIdById(id);
        //删除mongdb对应的数据信息

        //先删除mongodb里面的文件，再删除数据库记录
        fileMapper.delete(fileId);
        int i = zjFileDAO.deleteByPrimaryKey(id);
        return i;
    }

    public String upload(MultipartHttpServletRequest request) {
        List<String> result = new ArrayList<>();
        //拿到文件名
        Iterator<String> fileNames = request.getFileNames();
        while (fileNames.hasNext()) {
            String fileName = fileNames.next();
            //根据文件名拿到文件
            MultipartFile file = request.getFile(fileName);
            //获取上传文件的原名
            String name = file.getOriginalFilename();
            String fileNameStr = Base64.encodeBase64String(name.getBytes());
            // 文件类型
            String contentType = file.getContentType();
            String save = "";
            if (contentType.equals("image/png") || contentType.equals("image/jpeg")) {
                try {
                    //本地创建一个临时的文件夹用来存放上传的图片文件
                    String folder = fileUrl;
                    File dataDir = new File(folder);
                    if (!dataDir.exists()) {
                        //创建多级目录
                        dataDir.mkdirs();
                    }
                    FileCopyUtils.copy(file.getBytes(), new File(folder + File.separator + name));
                    //临时文件所在路径及名称
                    String thumbnailPathName = folder + "/" + name;
                    File file2 = new File(thumbnailPathName);
                    long size = file2.length();
                    double scale = 1.0d;
                    if (size >= 200 * 1024) {
                        scale = (200 * 1024f) / size;
                    }
                    //拼接压缩过后生成的文件路径
                    String thumbnailFilePathName = thumbnailPathName.substring(0, thumbnailPathName.lastIndexOf("."));
                    if (size > 200 * 1024) {
                        if (contentType.equals("image/png")) {
                            // 图片尺寸不变，压缩图片文件大小outputQuality实现,参数1为最高质量
                            Thumbnails.of(thumbnailPathName).scale(0.5f).outputQuality(scale).outputFormat("png").toFile(thumbnailFilePathName);
                        } else {
                            Thumbnails.of(thumbnailPathName).scale(0.5f).outputQuality(scale).outputFormat("jpg").toFile(thumbnailFilePathName);
                        }
                    }
                    //再把压缩过后的图片存到mongodb中
                    InputStream is = new FileInputStream(thumbnailPathName);
                    save = fileMapper.save(is, fileNameStr, contentType);
                    //保存到mongodb成功之后，关掉流并删除临时文件夹
                    is.close();
                    //先删掉文件夹里面的所有内容
                    FileDownLoadUtils.deleteFile(dataDir);
                    dataDir.delete();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    save = fileMapper.save(file.getInputStream(), fileNameStr, contentType);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (null != save) {
                result.add(save);
            }
        }
        log.info("上传方法执行完毕！得到的该文件的id为：" + result.get(0));
        return result.get(0);
    }

    public ResponseBase uploadVideo(MultipartHttpServletRequest request) {
        try {
            List<Map<String, String>> result = Lists.newArrayList();
            Map<String, String> maps = Maps.newHashMap();
            MultiValueMap<String, MultipartFile> multiFileMap = request.getMultiFileMap();
            //拿到文件名
            Iterator<String> fileNames = request.getFileNames();
            while (fileNames.hasNext()) {
                List<MultipartFile> multipartFiles = multiFileMap.get(fileNames.next());
                for (MultipartFile file : multipartFiles) {
                    //获取上传文件的原名
                    String name = file.getOriginalFilename();
                    File file1 = new File(videoUrl);
                    if (!file1.exists()) {
                        file1.mkdirs();
                    }
                    FileCopyUtils.copy(file.getBytes(), new File(videoUrl + File.separator + name));
                    maps.put("fileUrl", videoUrl + "/" + name);
                    maps.put("fileName", name);
                    result.add(maps);
                }
            }
            return new ResponseBase(200, "上传成功!", result);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseBase(200, e.getMessage(), e.getStackTrace());
        }
    }

    public ResponseBase previewVideo(String url, HttpServletRequest request, HttpServletResponse response) {
        try {
            InputStream is = new FileInputStream(url);

            List<String> files = Arrays.asList(url.split("/"));
            String fileName = files.get(files.size() - 1);
            //处理浏览器兼容问题
            if (request.getHeader("User-Agent").toUpperCase().contains("TRIDENT") ||
                    request.getHeader("User-Agent").toUpperCase().contains("MSIE") ||
                    request.getHeader("User-Agent").toUpperCase().contains("EDGE")) {
                fileName = URLEncoder.encode(fileName, "UTF-8");
            } else {
                //非IE浏览器的处理：
                fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
            }
            response.setContentType(ContentInfoUtil.findExtensionMatch(fileName).getMimeType());
            response.setHeader("Content-Disposition", "inline;filename=\"" + fileName + "\"");
            IOUtils.copy(is, response.getOutputStream());

            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseBase(200, e.getMessage(), e.getStackTrace());
        }
    }

    public void download(String fileId, HttpServletResponse response, HttpServletRequest request) throws Exception {
        GridFSFile gridFSFile = fileMapper.find(fileId);
        byte[] bytes = Base64.decodeBase64(gridFSFile.getFilename());
        String fileName = new String(bytes);

        //针对IE或者以IE为内核的浏览器：
        if (request.getHeader("User-Agent").toUpperCase().contains("MSIE") ||
                request.getHeader("User-Agent").toUpperCase().contains("TRIDENT")
                || request.getHeader("User-Agent").toUpperCase().contains("EDGE")) {
            fileName = URLEncoder.encode(fileName, StandardCharsets.UTF_8.toString());
        } else {
            //非IE浏览器：
            fileName = new String(fileName.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1);
        }

        response.setContentType(ContentInfoUtil.findExtensionMatch(fileName).getMimeType());
        response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
        //文件长度
        response.setHeader("Content-Length", String.valueOf(gridFSFile.getLength()));
        gridFSBucket.downloadToStream(gridFSFile.getObjectId(), response.getOutputStream());
    }

    public ResponseBase downloadVideo(String url, String fileName, HttpServletRequest request, HttpServletResponse response){
        try {
            File file = new File(url);
            //针对IE或者以IE为内核的浏览器：
            if (request.getHeader("User-Agent").toUpperCase().contains("MSIE") ||
                    request.getHeader("User-Agent").toUpperCase().contains("TRIDENT")
                    || request.getHeader("User-Agent").toUpperCase().contains("EDGE")) {
                fileName = URLEncoder.encode(fileName, StandardCharsets.UTF_8.toString());
            } else {
                //非IE浏览器：
                fileName = new String(fileName.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1);
            }
            //获取文件输入流
            FileInputStream inputStream = new FileInputStream(file);
            response.setContentType(ContentInfoUtil.findExtensionMatch(fileName).getMimeType());
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName);

            //获取响应输出流
            ServletOutputStream outputStream = response.getOutputStream();
            //文件拷贝
            org.apache.tomcat.util.http.fileupload.IOUtils.copy(inputStream, outputStream);

            //最后关闭流
            org.apache.tomcat.util.http.fileupload.IOUtils.closeQuietly(inputStream);
            org.apache.tomcat.util.http.fileupload.IOUtils.closeQuietly(outputStream);
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseBase(200, e.getMessage(), e.getStackTrace());
        }
    }

    public ResponseBase viewImg(String fileId, HttpServletRequest request, HttpServletResponse response) throws
            Exception {
        GridFSFile gridFSFile = fileMapper.find(fileId);
        if (gridFSFile != null) {
            GridFSDownloadStream gridFSDownloadStream = gridFSBucket.openDownloadStream(gridFSFile.getObjectId());
            GridFsResource gridFsResource = new GridFsResource(gridFSFile, gridFSDownloadStream);

            String fileName = new String(Base64.decodeBase64(gridFSFile.getFilename()));
            if (request.getHeader("User-Agent").toUpperCase().contains("MSIE") ||
                    request.getHeader("User-Agent").toUpperCase().contains("TRIDENT")
                    || request.getHeader("User-Agent").toUpperCase().contains("EDGE")) {
                fileName = URLEncoder.encode(fileName, "UTF-8");
            } else {
                //非IE浏览器的处理：
                fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
            }
            response.setContentType(ContentInfoUtil.findExtensionMatch(fileName).getMimeType());
            response.setHeader("Content-Disposition", "inline;filename=\"" + fileName + "\"");
            IOUtils.copy(gridFsResource.getInputStream(), response.getOutputStream());

        }
        return null;
    }

    public String uploadFile(String namee) {
        String filePre = "C:\\safe";
        List<String> names = Arrays.asList(namee.split(","));
        StringBuilder sb = new StringBuilder();
        for (String name : names) {
            File file = new File(filePre + File.separator + name);
            if (!file.exists()) {
                file = new File(filePre + File.separator + name + ".pdf");
                if (!file.exists()) {
                    System.out.println(name + ".pdf");
                }
                continue;
            }

            Path path = Paths.get(filePre + File.separator + name);
            try {
                String contentType = Files.probeContentType(path);
                System.out.println(contentType);
                InputStream input = new FileInputStream(file);
                String fileNameStr = Base64.encodeBase64String(name.getBytes());
                String save = fileMapper.save(input, fileNameStr, "application/pdf");

                System.out.println(save);
                sb.append(save).append(",");
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return sb.toString();

    }

    public Integer updateZjFile(ZjFile zjFile) {
        zjFileDAO.deleteByPrimaryKey(zjFile.getId());
        zjFile.setId(null);
        int insert = zjFileDAO.insert(zjFile);
        return insert;
    }

    public ResponseBase uploadBase64(Map<String, Object> params) {
        List<String> result = new ArrayList<>();

        if (!params.containsKey("base64")) {
            return new ResponseBase(500, "base64缺少！");
        }
        if (!params.containsKey("name")) {
            return new ResponseBase(500, "name缺少！");
        }
        String getBase64 = (String) params.get("base64");
        String name = (String) params.get("name");
        byte[] bytes = Base64.decodeBase64(getBase64);
        //文件名加密
        String fileNameStr = Base64.encodeBase64String(name.getBytes());
        //获取文件类型
        String contentType = name.substring(name.indexOf(".") + 1);
        String save = "";
        //把解码过后的转成InputStream
        InputStream input = new ByteArrayInputStream(bytes);
        try {
            //存入mongodb
            save = fileMapper.save(input, fileNameStr, contentType);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (null != save) {
            result.add(save);
        }

        return new ResponseBase(200, "上传成功!", result.get(0));
    }

    public ResponseBase findFile(String id) {
        GridFSFile gridFSFile = fileMapper.find(id);
        Map<String, Object> maps = new HashMap<>();
        byte[] bytes = Base64.decodeBase64(gridFSFile.getFilename());
        String fileName = new String(bytes);
        Date time = gridFSFile.getUploadDate();
        maps.put("fileId", id);
        maps.put("fileName", fileName);
        maps.put("uploadTime", time);
        return new ResponseBase(200, "查询成功!", maps);
    }

    public Map<String, Object> newUpload(MultipartHttpServletRequest request) {
        List<String> result = new ArrayList<>();
        //拿到文件名
        Iterator<String> fileNames = request.getFileNames();
        while (fileNames.hasNext()) {
            String fileName = fileNames.next();
            //根据文件名拿到文件
            MultipartFile file = request.getFile(fileName);
            //获取上传文件的原名
            String name = file.getOriginalFilename();
            String fileNameStr = Base64.encodeBase64String(name.getBytes());
            // 文件类型
            String contentType = file.getContentType();
            String save = "";
            if (contentType.equals("image/png") || contentType.equals("image/jpeg")) {
                try {
                    //本地创建一个临时的文件夹用来存放上传的图片文件
                    String folder = fileUrl;
                    File dataDir = new File(folder);
                    if (!dataDir.exists()) {
                        //创建多级目录
                        dataDir.mkdirs();
                    }
                    FileCopyUtils.copy(file.getBytes(), new File(folder + File.separator + name));
                    //临时文件所在路径及名称
                    String thumbnailPathName = folder + "/" + name;
                    File file2 = new File(thumbnailPathName);
                    long size = file2.length();
                    double scale = 1.0d;
                    if (size >= 200 * 1024) {
                        scale = (200 * 1024f) / size;
                    }
                    //拼接压缩过后生成的文件路径
                    String thumbnailFilePathName = thumbnailPathName.substring(0, thumbnailPathName.lastIndexOf("."));
                    if (size > 200 * 1024) {
                        if (contentType.equals("image/png")) {
                            // 图片尺寸不变，压缩图片文件大小outputQuality实现,参数1为最高质量
                            Thumbnails.of(thumbnailPathName).scale(0.5f).outputQuality(scale).outputFormat("png").toFile(thumbnailFilePathName);
                        } else {
                            Thumbnails.of(thumbnailPathName).scale(0.5f).outputQuality(scale).outputFormat("jpg").toFile(thumbnailFilePathName);
                        }
                    }
                    //再把压缩过后的图片存到mongodb中
                    InputStream is = new FileInputStream(thumbnailPathName);
                    save = fileMapper.save(is, fileNameStr, contentType);
                    //保存到mongodb成功之后，关掉流并删除临时文件夹
                    is.close();
                    //先删掉文件夹里面的所有内容
                    FileDownLoadUtils.deleteFile(dataDir);
                    dataDir.delete();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    save = fileMapper.save(file.getInputStream(), fileNameStr, contentType);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (null != save) {
                result.add(save);
            }
        }

        GridFSFile gridFSFile = fileMapper.find(result.get(0));
        Map<String, Object> maps = new HashMap<>();
        byte[] bytes = Base64.decodeBase64(gridFSFile.getFilename());
        String fileName = new String(bytes);
        Date time = gridFSFile.getUploadDate();
        maps.put("fileId", result.get(0));
        maps.put("fileName", fileName);
        maps.put("uploadTime", time);
        return maps;
    }
}
