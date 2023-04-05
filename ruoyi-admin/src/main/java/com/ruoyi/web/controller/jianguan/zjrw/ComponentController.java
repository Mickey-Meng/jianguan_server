package com.ruoyi.web.controller.jianguan.zjrw;

import com.ruoyi.common.core.domain.BaseTree;
import com.ruoyi.common.core.domain.object.ResponseBase;
import com.ruoyi.jianguan.common.domain.dto.ComponentProgressDTO;
import com.ruoyi.jianguan.common.service.ComponentSevice;
import com.ruoyi.jianguan.common.service.RedisService;
import com.ruoyi.common.utils.jianguan.zjrw.MyExcelUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author ：lin_zhixiong
 * @date ：Created in 2021/6/18 8:54 上午
 * @version: V1.0
 * @slogan: 天下风云出我辈，一入代码苦耕耘
 * @description:
 **/
@RestController
@RequestMapping("/component")
@Api(value="构件管理")
public class ComponentController {

    @Autowired
    ComponentSevice componentSevice;

    @Autowired
    RedisService redisService;

    /**
     * @Author: lin_zhixiong
     * @Description: 批量导入构件数据
     * @DateTime: 2021/6/18 8:57 上午
     * @Params:
     * @Return 导入构件数据
     */
    @PostMapping(value = "/upload")
    public ResponseBase uploadExcel(@RequestParam("uploadFile") MultipartFile uploadFile){
        try {
            InputStream inputStream = uploadFile.getInputStream();
            List<List<String>> list = MyExcelUtil.getCourseListByExcel(inputStream, uploadFile.getOriginalFilename());
            inputStream.close();
            componentSevice.uploadExcel(list);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseBase("上传成功");
    }

    @GetMapping(value = "initgq")
    public ResponseBase initgq(){
        return componentSevice.initgq();
    }

    @GetMapping(value = "initxyzmouild")
    public ResponseBase initxyzmouild(){
        return componentSevice.initxyzmouild();
    }



    @PostMapping(value = "/uploadxyz")
    public ResponseBase uploadxyz(@RequestParam("uploadFile") MultipartFile uploadFile){
        try {
            InputStream inputStream = uploadFile.getInputStream();
            List<List<String>> list = MyExcelUtil.getCourseListByExcel(inputStream, uploadFile.getOriginalFilename());
            inputStream.close();
            componentSevice.uploadxyz(list);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseBase("上传成功");
    }

    @GetMapping(value = "/init")
    public ResponseBase init(){
        componentSevice.init();
        return new ResponseBase("更新成功");
    }

    @GetMapping(value = "/xyz")
    public ResponseBase initxyz(){
        componentSevice.initxyz();
        return new ResponseBase("上传成功");
    }

    @PostMapping(value = "/img")
    public ResponseBase img(@RequestParam("uploadFile") MultipartFile uploadFile){
        try {
            InputStream inputStream = uploadFile.getInputStream();
            List<List<String>> list = MyExcelUtil.getCourseListByExcel(inputStream, uploadFile.getOriginalFilename());
            inputStream.close();
            componentSevice.img(list);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseBase("上传成功");
    }

    @PostMapping(value = "/num")
    @ApiOperation(value = "批量上传构件进度")
    public ResponseBase num(@RequestParam("uploadFile") MultipartFile uploadFile){
        try {
            InputStream inputStream = uploadFile.getInputStream();
            List<List<String>> list = MyExcelUtil.getCourseListByExcel(inputStream, uploadFile.getOriginalFilename());
            inputStream.close();
            componentSevice.num(list);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseBase("上传成功");
    }

    @GetMapping("/getTree")
    @ResponseBody
    @ApiOperation(value="获取项目进度树列表")
    public ResponseBase getTree(@RequestParam(value ="projectId",required = false) Integer projectId,
                                @RequestParam(value ="type") String type){
        //当项目id没有传时, 默认为3
        if(ObjectUtils.isEmpty(projectId) || projectId == null){
            projectId = 3;
        }
        if(!type.equals("QL") && !type.equals("SD") && !type.equals("LM")){
            return new ResponseBase(601,"不支持的类型" + type, new BaseTree());
        }
        return componentSevice.getTree(projectId, type);
    }

    @GetMapping("/getProjectTree")
    @ResponseBody
    @ApiOperation(value="获取项目进度树列表")
    public ResponseBase getProjectTree(@RequestParam(value ="id",required = false) Integer id){
        if(ObjectUtils.isEmpty(id)||id==null){
            return new ResponseBase(500,"参数错误");
        }
        return componentSevice.getProjectTree(id);
    }

    @GetMapping("/initConponentproducetime")
    @ResponseBody
    @ApiOperation(value="获取项目进度树列表")
    public ResponseBase initConponentproducetime(){
        return componentSevice.initConponentproducetime();
    }

    @PostMapping("/getSubProject")
    @ResponseBody
    @ApiOperation(value = "获取分部工程（type= QL || SD || LM）")
    public ResponseBase getSubProject(@RequestParam(value ="type", required = false) String type){
        return componentSevice.getSubProject(type);
    }

    @PostMapping("/getComponentProgress")
    @ResponseBody
    @ApiOperation(value = "获取构建进度信息(id为模型id)")
    public ResponseBase getComponentData(@RequestBody ComponentProgressDTO progressDTO){
        return componentSevice.getComponentData(progressDTO);
    }

    @PostMapping("/addServiceName")
    @ResponseBody
    @ApiOperation(value = "通过文件往构建表批量插入服务名称")
    public ResponseBase addServiceName (@RequestParam("uploadFile") MultipartFile uploadFile){
        return componentSevice.addServiceName(uploadFile);
    }

    @PostMapping("/addCode")
    @ResponseBody
    @ApiOperation(value = "通过文件往构建表批量插入模型编码、老BIM编码、wbs编码")
    public ResponseBase addModelCode (@RequestParam("uploadFile") MultipartFile uploadFile){
        return componentSevice.addCode(uploadFile);
    }

    @PostMapping("/addGroups")
    @ResponseBody
    @ApiOperation(value = "通过文件批量添加工区数据")
    public ResponseBase addGroups(@RequestParam("uploadFile") MultipartFile file){
        return componentSevice.addGroups(file);
    }

    @PostMapping("/supplementData")
    @ResponseBody
    @ApiOperation(value = "补齐构件进度表的数据")
    public ResponseBase supplementData(){
        return componentSevice.supplementData();
    }

    @PostMapping("/updateProduceTimeGroupData")
    @ResponseBody
    @ApiOperation(value = "补齐构件进度表的工区数据")
    public ResponseBase updateProduceTimeGroupData(){
        return componentSevice.updateProduceTimeGroupData();
    }

    @PostMapping("/updateProduceData")
    @ResponseBody
    @ApiOperation(value = "修改工序表的编码为新编码")
    public ResponseBase updateProduceData(){
        return componentSevice.updateProduceData();
    }

    @PostMapping("/updateProgressDetail")
    @ResponseBody
    @ApiOperation("更新构件进度表的四个时间")
    public ResponseBase updateProgressDetail(){
        return componentSevice.updateProgressDetail();
    }

    @PostMapping("/updatePjtType")
    @ResponseBody
    @ApiOperation("更新构件进度表的构件结构")
    public ResponseBase updatePjtType(){
        return componentSevice.updatePjtType();
    }

    @PostMapping("/updateConponentXYZ")
    @ResponseBody
    @ApiOperation("更新构件表的XYZ")
    public ResponseBase updateConponentXYZ(@RequestParam("uploadFile") MultipartFile file){
        return componentSevice.updateConponentXYZ(file);
    }

    @PostMapping("/updateConponentProducetime")
    @ResponseBody
    @ApiOperation("更新构件进度表的时间")
    public ResponseBase updateConponentProducetime(@RequestParam("uploadFile") MultipartFile file){
        return componentSevice.updateConponentProducetime(file);
    }

    @PostMapping("/updateProduceId")
    @ApiOperation("/修改ss_f_projects与zj_f_groups_projects对应id关系")
    public ResponseBase updateProduceId(){
        return componentSevice.updateProduceId();
    }
}
