package com.ruoyi.web.controller.jianguan.other.zjrw;


import com.mongodb.client.gridfs.GridFSFindIterable;
import com.mongodb.client.gridfs.model.GridFSFile;
import com.ruoyi.common.constant.Constant;
import com.ruoyi.common.core.domain.object.ResponseBase;
import com.ruoyi.common.helper.LoginHelper;
import com.ruoyi.common.utils.JwtUtil;
import com.ruoyi.jianguan.common.dao.FileMapper;
import com.ruoyi.jianguan.common.dao.ProjectsDAO;
import com.ruoyi.jianguan.common.domain.entity.ZjFile;
import com.ruoyi.jianguan.common.service.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.bson.BsonObjectId;
import org.bson.BsonValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/mong")
@Api(value="图片上传新")
public class MongController {

    @Autowired
    private FileService fileService;
    @Autowired
    private FileMapper mapper;
    @Autowired
    private ProjectsDAO projectsDAO;

    @ApiOperation(value = "上传文件", notes = "上传文件")
    @PostMapping(value = "/upload")
    @ResponseBody
    public ResponseBase upload(MultipartHttpServletRequest request) {
        if (null != request) {
            String upload = null;
            try {
                upload = fileService.upload(request);
            } catch (Exception e) {
                return new ResponseBase(Constant.EXCEPTION, e.getMessage());
            }
            return new ResponseBase(200, "上传成功",upload);
        }
        return new ResponseBase(500, "上传文件不能为空");
    }

    @ResponseBody
    @PostMapping("/uploadVideo")
    @ApiOperation(value = "上传视频(音频)")
    public ResponseBase uploadVideo(MultipartHttpServletRequest request){
        if (request != null){
            return fileService.uploadVideo(request);
        }
        return new ResponseBase(200, "上传文件不能为空!");
    }

    @ApiOperation(value = "下载文件", notes = "下载文件")
    @GetMapping(value = "/download")
    @ResponseBody
    public ResponseBase download(@RequestParam(name = "fileid") String fileId, HttpServletResponse response, HttpServletRequest request) {
        if (!StringUtils.hasLength(fileId)) {
            return new ResponseBase(500, "文件ID不能为空");
        }
        String[] split = fileId.split("\\.");
        try {
            fileService.download(split[0], response,request);
        } catch (Exception e) {
            return new ResponseBase(400, e.getMessage());
        }
        return null;
    }

    @ResponseBody
    @GetMapping(value = "/downloadVideo")
    @ApiOperation(value = "下载视频(音频)文件")
    public ResponseBase downloadVideo(@RequestParam("url")String url,
                                      @RequestParam("fileName")String fileName,
                                      HttpServletRequest request, HttpServletResponse response){
        if (url != null && !url.equals("") && fileName != null && !fileName.equals("")){
            return fileService.downloadVideo(url, fileName, request, response);
        } else{
            return new ResponseBase(200, "请核实文件路径的有效性!");
        }
    }

    @ApiOperation(value = "预览文件", notes = "预览文件")
    @GetMapping(value = "/preview")
    @ResponseBody
    public ResponseBase preview(@RequestParam(name = "fileid") String fileId, HttpServletResponse response, HttpServletRequest request) {
        if (fileId != null && !"null".equalsIgnoreCase(fileId)) {
            try {
                return fileService.viewImg(fileId, request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @ResponseBody
    @GetMapping(value = "/previewVideo")
    @ApiOperation(value = "预览视频/音频")
    @ApiImplicitParam(name = "url", value = "文件地址")
    public ResponseBase previewVideo(String url, HttpServletRequest request, HttpServletResponse response){
        if (url == null || url.equals("")){
            return new ResponseBase(200, "文件url为空!");
        } else {
            return fileService.previewVideo(url, request, response);
        }
    }

    @PostMapping(value = "/fileStore")
    @ApiOperation("档案管理: 上传文件")
    public ResponseBase num(@RequestBody ZjFile zjFile) {
        if (zjFile.getProjectId() <= 0){
            return new ResponseBase(200, "请输入有效的项目id!");
        }
        Integer count1 = projectsDAO.getCountById(zjFile.getProjectId());
        if (count1 <= 0){
            return new ResponseBase(200, "该项目id无数据!");
        }
        Integer userid = LoginHelper.getUserId().intValue();
        zjFile.setUploadid(userid);
        zjFile.setUploadtime(new Date());
        Integer integer = fileService.storZjFile(zjFile);

        if (integer > 0) {
            return new ResponseBase(200, "保存成功");
        }

        return new ResponseBase(500, "保存失败");
    }



    @PostMapping(value = "/update")
    @ApiOperation("档案管理: 编辑文件")
    public ResponseBase update(@RequestBody ZjFile zjFile) {
        if (zjFile.getProjectId() <= 0){
            return new ResponseBase(200, "请输入有效的项目id!");
        }
        Integer count1 = projectsDAO.getCountById(zjFile.getProjectId());
        if (count1 <= 0){
            return new ResponseBase(200, "该项目id无数据!");
        }

        Integer userid = LoginHelper.getUserId().intValue();
        zjFile.setUploadid(userid);
        zjFile.setUploadtime(new Date());

        Integer integer = fileService.updateZjFile(zjFile);

        if (integer > 0) {
            return new ResponseBase(200, "修改成功");
        }

        return new ResponseBase(500, "修改失败");
    }

    @PostMapping(value = "/modifyFile")
    @ApiOperation("文件管理")
    public ResponseBase modifyFile(@RequestBody ZjFile zjFile) {

        Integer userid = LoginHelper.getUserId().intValue();
        zjFile.setUploadid(userid);
        Integer integer = fileService.modifyFile(zjFile);

        if (integer > 0) {
            return new ResponseBase(200, "修改成功");
        }

        return new ResponseBase(500, "修改失败");
    }


    @GetMapping(value = "/delFile")
    @ApiOperation(value = "通过id删除文件")
    public ResponseBase delFile(@RequestParam(value = "id", required = false) Integer id) {

        Integer del = fileService.del(id);
        if (del > 0) {
            return new ResponseBase(200, "删除成功");
        }
        return new ResponseBase(500, "删除失败");
    }

    @GetMapping(value = "/getStoreFileType")
    public ResponseBase getStoreFileType(@RequestParam(value = "type", required = false) Integer type,
                                         @RequestParam(value = "projectId", required = false) Integer projectId) {
        if (projectId <= 0){
            return new ResponseBase(200, "请输入有效的项目id!");
        }
        Integer count1 = projectsDAO.getCountById(projectId);
        if (count1 <= 0){
            return new ResponseBase(200, "该项目id无数据!");
        }
        try {
            List<ZjFile> storeFileType = fileService.getStoreFileType(type, projectId);
            return new ResponseBase(200, "查询成功", storeFileType);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseBase(500, "修改失败");
        }
    }


    @PostMapping(value = "/uploadBase64")
    @ApiOperation(value = "上传base64文件")
    @ResponseBody
    public ResponseBase uploadBase64(@RequestBody Map<String,Object> params) {
        return fileService.uploadBase64(params);
    }

    @PostMapping(value = "/findFile")
    @ApiOperation(value = "通过id查询文件信息")
    public ResponseBase findFile(@RequestParam(value = "id") String id){
        return fileService.findFile(id);
    }

    @PostMapping(value = "/newUpload")
    @ApiOperation(value = "上传文件并返回文件信息")
    public ResponseBase newUpload(MultipartHttpServletRequest request){
        if (request != null) {
            Map<String, Object> maps = new HashMap<>();
            try {
                maps = fileService.newUpload(request);
            } catch (Exception e) {
                return new ResponseBase(Constant.EXCEPTION, e.getMessage());
            }
            return new ResponseBase(200, "上传成功", maps);
        }
        return new ResponseBase(500, "上传文件不能为空");
    }

    @PostMapping(value = "/findAll")
    @ApiOperation(value = "通过id查询文件信息")
    public ResponseBase findAll(){
        //获取所有存储桶里面的文件信息
        GridFSFindIterable it = mapper.findAll();
        //遍历, 获取文件对应的 _id 值
        for (GridFSFile gridFSFile : it) {
            BsonValue id = gridFSFile.getId();
            BsonObjectId bsonObjectId = id.asObjectId();
            System.out.println(bsonObjectId.getValue());
        }
        return new ResponseBase(200,"", it);
    }
}
