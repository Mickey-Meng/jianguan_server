package com.ruoyi.web.controller.jianguan.other.zjrw;


import cn.hutool.core.util.ObjUtil;
import com.alibaba.fastjson.JSONObject;
import com.mongodb.client.gridfs.GridFSFindIterable;
import com.mongodb.client.gridfs.model.GridFSFile;
import com.ruoyi.common.constant.Constant;
import com.ruoyi.common.core.domain.object.ResponseBase;
import com.ruoyi.common.enums.BimFlowKey;
import com.ruoyi.common.helper.LoginHelper;
import com.ruoyi.common.utils.JwtUtil;
import com.ruoyi.flowable.domain.dto.FlowTaskCommentDto;
import com.ruoyi.flowable.service.FlowStaticPageService;
import com.ruoyi.jianguan.common.dao.FileMapper;
import com.ruoyi.jianguan.common.dao.ProjectsDAO;
import com.ruoyi.jianguan.common.domain.dto.ZjFileDTO;
import com.ruoyi.jianguan.common.domain.entity.ZjFile;
import com.ruoyi.jianguan.common.service.FileService;
import com.ruoyi.jianguan.manage.project.domain.vo.DataDictionaryVo;
import com.ruoyi.jianguan.manage.project.service.IDataDictionaryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.bson.BsonObjectId;
import org.bson.BsonValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.*;

@RestController
@RequestMapping("/mong")
@Api(value="图片上传新")
@Slf4j
public class MongController {

    @Autowired
    private FileService fileService;
    @Autowired
    private FileMapper mapper;
    @Autowired
    private ProjectsDAO projectsDAO;
    @Autowired
    private FlowStaticPageService flowStaticPageService;

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
    public ResponseBase download(@RequestParam(name = "fileid") String fileid,@RequestParam(name = "fileName",required = false) String fileName, HttpServletResponse response, HttpServletRequest request) {
        log.info("**********"   );
        log.info("fileid:" + fileid);
        log.info("fileName:" + fileName);
        if (!StringUtils.hasLength(fileid)) {
            return new ResponseBase(500, "文件ID不能为空");
        }
        String[] split = fileid.split("\\.");
        try {
            fileService.download(split[0], response,request,fileName);
        } catch (Exception e) {
            e.printStackTrace();
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
    @Resource
    private IDataDictionaryService dataDictionaryService;

    @PostMapping(value = "/selectByPrimaryKey")
    @ApiOperation(value = "查询文件详情")
    public ResponseBase selectByPrimaryKey(@RequestParam(value = "id") String id){
        ZjFile zjFile = fileService.selectByPrimaryKey(Integer.parseInt(id));
        Integer type = zjFile.getType();
        DataDictionaryVo dataDictionaryVo = dataDictionaryService.queryById(type.longValue());
        zjFile.setTypeDesc(dataDictionaryVo.getName());
        return new ResponseBase(200, "查询文件详情成功", zjFile);
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
        boolean isStartFlow = false;
        if(ObjUtil.isNull(zjFile.getId())) {
            isStartFlow = true;
        }
        zjFile.setCreateUserId(LoginHelper.getUserId());
        zjFile.setStatus(0);
        Integer integer = fileService.storZjFile(zjFile);
        if (integer == 1 && isStartFlow && "DSFJCDWZLGL".equals(zjFile.getFileType())) {
            String processDefinitionKey = BimFlowKey.DSFJCDWZLGL.getName();
            String businessKey = zjFile.getId().toString();
            //设置流程的审批人
            Map<String, Object> auditUser = zjFile.getAuditUser();
            if (auditUser.isEmpty()) {
                throw new RuntimeException("流程的审批人不能为空！");
            }

            //发起流程
            try {
                FlowTaskCommentDto flowTaskComment = new FlowTaskCommentDto();
                flowTaskComment.setApprovalType("save");
                flowTaskComment.setComment("第三方检测单位资料");
                JSONObject taskVariableData = new JSONObject(auditUser);
                flowStaticPageService.startAndTakeUserTask(processDefinitionKey, flowTaskComment, taskVariableData, null, null, null, businessKey);
            } catch (Exception e) {
                log.error("流程启动失败！e=" + e.getMessage());
                throw new RuntimeException("流程启动失败！e=" + e.getMessage());
            }
        }

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
        zjFile.setStatus(2);
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

    /***
     * 通过父级数据字典目录的code来获取该字典下面所有字典类型的文件清单
     * @param pCode
     * @param projectId
     * @return
     */
    @GetMapping(value = "/getStoreFileByPcode")
    public ResponseBase getStoreFileByPcode(@RequestParam(value = "pCode", required = false) String pCode,
                                         @RequestParam(value = "projectId", required = false) Integer projectId) {
        if (projectId <= 0){
            return new ResponseBase(200, "请输入有效的项目id!");
        }
        Integer count1 = projectsDAO.getCountById(projectId);
        if (count1 <= 0){
            return new ResponseBase(200, "该项目id无数据!");
        }
        try {
            List<ZjFileDTO> storeFileType = fileService.getStoreFileByPcode(pCode, projectId);
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

    /**
     * 获取app最新版本
     * @return
     */
    @GetMapping(value = "/getAppLastVersion")
    @ApiOperation(value = "获取最新的app版本号和文件地址")
    public ZjFile getAppLastVersion() {
        List<ZjFile> storeFileType = fileService.getStoreFileType(17, null);
        storeFileType.sort(Comparator.comparing(ZjFile::getUploadtime).reversed());
        return storeFileType.get(0);
    }
}
