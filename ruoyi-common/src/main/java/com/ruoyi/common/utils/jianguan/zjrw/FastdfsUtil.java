package com.ruoyi.common.utils.jianguan.zjrw;//package com.ruoyi.common.utils.zjbim.zjrw;
//
//import com.github.tobato.fastdfs.domain.fdfs.MetaData;
//import com.github.tobato.fastdfs.domain.fdfs.StorePath;
//import com.github.tobato.fastdfs.domain.proto.storage.DownloadByteArray;
//import com.github.tobato.fastdfs.service.FastFileStorageClient;
//import com.ruoyi.czjg.zjrw.dao.ZjFileDAO;
//import com.ruoyi.czjg.zjrw.domain.entity.ZjFile;
//import org.apache.commons.io.FilenameUtils;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.web.multipart.MultipartFile;
//
//import javax.servlet.ServletOutputStream;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.net.URLEncoder;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
///**
// * @author ：lin_zhixiong
// * @date ：Created in 2021/6/29 3:44 下午
// * @version: V1.0
// * @slogan: 天下风云出我辈，一入代码苦耕耘
// * @description:
// **/
//
//
//@Component
//public class FastdfsUtil {
//
//    public static final String DEFAULT_CHARSET = "UTF-8";
//
//    @Autowired
//    private FastFileStorageClient fastFileStorageClient;
//
//    @Autowired
//    ZjFileDAO zjFileDAO;
//
//
//    public Integer storZjFile(ZjFile zjFile){
//        int insert = zjFileDAO.insert(zjFile);
//        return  insert;
//    }
//
//    public Integer modifyFile(ZjFile zjFile){
//        int i = zjFileDAO.updateByPrimaryKeySelective(zjFile);
//        return i;
//    }
//
//    /**
//     * 上传
//     */
//    public StorePath upload(MultipartFile file) throws IOException {
//        // 设置文件信息
//        Set<MetaData> mataData = new HashSet<>();
//        mataData.add(new MetaData("author", "fastdfs"));
//        mataData.add(new MetaData("description", file.getOriginalFilename()));
//        // 上传
//
//        return fastFileStorageClient.uploadFile(file.getInputStream(), file.getSize(), FilenameUtils.getExtension(file.getOriginalFilename()), null);
//    }
//
//    /**
//     * 删除
//     */
//    public void delete(String path) {
//        fastFileStorageClient.deleteFile(path);
//    }
//
//    /**
//     * 删除
//     */
//    public void delete(String group, String path) {
//        fastFileStorageClient.deleteFile(group, path);
//    }
//
//    /**
//     * 文件下载
//     *
//     * @param path     文件路径，例如：/group1/M00/00/00/itstyle.png
//     * @param filename 下载的文件命名
//     */
//    public void download(String path, String filename, HttpServletResponse response) throws IOException {
//        // 获取文件
//        StorePath storePath = StorePath.parseFromUrl(path);
//        if (StringUtils.isBlank(filename)) {
//            filename = FilenameUtils.getName(storePath.getPath());
//        }
//        byte[] bytes = fastFileStorageClient.downloadFile(storePath.getGroup(),
//                storePath.getPath(), new DownloadByteArray());
//        response.reset();
//        response.setContentType("applicatoin/octet-stream");
//        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(filename, "UTF-8"));
//        ServletOutputStream out = response.getOutputStream();
//        out.write(bytes);
//        out.close();
//    }
//
//    public List<ZjFile> getStoreFileType(Integer type) {
//        List<ZjFile> files =zjFileDAO.selectByType(type);
//        return files;
//    }
//
//    public Integer del(Integer id) {
//        int i = zjFileDAO.deleteByPrimaryKey(id);
//        return i;
//    }
//}
