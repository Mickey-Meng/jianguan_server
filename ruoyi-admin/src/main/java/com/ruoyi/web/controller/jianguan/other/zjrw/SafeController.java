package com.ruoyi.web.controller.jianguan.other.zjrw;

import com.ruoyi.common.core.domain.object.ResponseBase;
import com.ruoyi.jianguan.common.domain.dto.DoEvent;
import com.ruoyi.jianguan.common.domain.dto.SafeEventUpload;
import com.ruoyi.jianguan.common.domain.dto.SubMitDelayEvent;
import com.ruoyi.jianguan.common.domain.dto.SubmitDealWithSafeEvent;
import com.ruoyi.jianguan.common.service.SafeService;
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
 * @date ：Created in 2021/6/25 9:14 上午
 * @version: V1.0
 * @slogan: 天下风云出我辈，一入代码苦耕耘
 * @description:
 **/

@RestController
@RequestMapping("/safe")
@Api(value="安全事件管理")
public class SafeController {

    @Autowired
    SafeService safeService;

    @GetMapping("/getTree")
    @ResponseBody
    @ApiOperation(value="获取安全大小类数据")
    public ResponseBase getTree(@RequestParam(value ="id", required = false) Integer id,
                                @RequestParam(value = "projectId", required = false) Integer projectId){
        if (projectId == null || projectId.equals("")){
            projectId = 3;
        }
        if(ObjectUtils.isEmpty(id) || id==null){
            id = 0;
        }
        return safeService.getTree(id, projectId);
    }

    @GetMapping("/getcheck")
    @ResponseBody
    @ApiOperation(value="获取每个工区的负责人")
    public ResponseBase getcheck(){
        return safeService.getcheck();
    }

    @PostMapping("/uploadData")
    @ResponseBody
    @ApiOperation(value="监理上传安全检查")
    public ResponseBase uploadCheck(@RequestBody SafeEventUpload safeEventUpload){
        return safeService.uploadCheck(safeEventUpload);
    }

    @GetMapping("/uploadAddr")
    @ResponseBody
    @ApiOperation(value="监理上传安全检查")
    public ResponseBase uploadAddr(@RequestParam(name="id",required = false) Integer id){
        if(ObjectUtils.isEmpty(id)){
            return safeService.getQu();
        }
        return safeService.getProject(id);
    }

    @GetMapping("/getSafeEvent")
    @ResponseBody
    @ApiOperation(value="施工方获取安全检查事件")
    public ResponseBase getSafeEvent(@RequestParam(value = "projectId", required = false) Integer projectId){
        return safeService.getSafeEvent(projectId);
    }

    @PostMapping("/submitDelaySafeEvent")
    @ResponseBody
    @ApiOperation(value="施工方对安全事件进行延期处理")
    public ResponseBase submitDelaySafeEvent(@RequestBody SubMitDelayEvent doDelayEvent){
        return safeService.submitDelaySafeEvent(doDelayEvent);
    }

    @GetMapping("/getDelaySafeEvent")
    @ResponseBody
    @ApiOperation(value="监理方查询延期安全延期代办")
    public ResponseBase getDelaySafeEvent(@RequestParam(value = "projectId", required = false) Integer projectId){
        return safeService.getDelaySafeEvent(projectId);
    }

    @PostMapping("/doDelaySafeEvent")
    @ResponseBody
    @ApiOperation(value="监理方确认安全事件延期处理代办")
    public ResponseBase doDelaySafeEvent(@RequestBody DoEvent doDelayEvent){
        return safeService.doDelaySafeEvent(doDelayEvent);
    }

    @PostMapping("/submitDealWithSafeEvent")
    @ResponseBody
    @ApiOperation(value="施工方对安全事件进行处理")
    public ResponseBase submitDealWithSafeEvent(@RequestBody SubmitDealWithSafeEvent submitDealWithSafeEvent){
        return safeService.submitDealWithSafeEvent(submitDealWithSafeEvent);
    }

    @GetMapping("/getNotDoneSafeEvent")
    @ResponseBody
    @ApiOperation(value="监理方查询安全整改代办")
    public ResponseBase getNotDoneSafeEvent(@RequestParam(value = "projectId", required = false) Integer projectId){
        return safeService.getNotDoneSafeEvent(projectId);
    }

    @PostMapping("/doNotDoneSafeEvent")
    @ResponseBody
    @ApiOperation(value="监理方处理安全整改代办")
    public ResponseBase doNotDoneSafeEvent(@RequestBody DoEvent doDelayEvent){
        return safeService.doNotDoneSafeEvent(doDelayEvent);
    }

    @GetMapping("/getAllStatusSafeEvent")
    @ResponseBody
    @ApiOperation(value="监理人查询所有安全检查事件")
    public ResponseBase getAllStatusSafeEvent(@RequestParam(value = "projectId", required = false)Integer projectId,
                                              @RequestParam(value = "singleProjectId", required = false)Integer singleProjectId){
        if (projectId == null || projectId.equals("")){
            projectId = 3;
        }
        return safeService.getAllStatusSafeEvent(singleProjectId, projectId);
    }

    @GetMapping("/getDoneSafeEvent")
    @ResponseBody
    @ApiOperation(value="监理方查询已办安全检查事件")
    public ResponseBase getDoneSafeEvent(){
        return safeService.getDoneSafeEvent();
    }



    @GetMapping("/getAllSafeEvent")
    @ResponseBody
    @ApiOperation(value="获取所有的安全事件")
    public ResponseBase getAllSafeEvent(@RequestParam(value = "projectId" , required = false)Integer projectId,
                                        @RequestParam(value = "singleProjectId", required = false) Integer singleProjectId){
        return safeService.getAllSafeEvent(projectId, singleProjectId);
    }


    @PostMapping("/upload")
    @ApiOperation(value="上传安全事件")
    public ResponseBase upload(@RequestParam("uploadFile") MultipartFile uploadFile){
        try {
            InputStream inputStream = uploadFile.getInputStream();
            List<List<Object>> list = MyExcelUtil.getCourseListByExcel(inputStream, uploadFile.getOriginalFilename());
            inputStream.close();
            safeService.uploadxyz(list);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseBase("上传成功");
    }


    @GetMapping("/newInterface")
    @ApiOperation(value="安全总览统计")
    public ResponseBase newInterface(@RequestParam("count") Integer count,
                                     @RequestParam(value = "projectId", required = false) Integer projectId){
        return safeService.newInterface(count, projectId);
    }

    @GetMapping("/group")
    @ApiOperation(value="安全分组")
    public ResponseBase group(@RequestParam("count") Integer count,
                              @RequestParam(value = "projectId", required = false) Integer projectId){
        return safeService.group(count, projectId);
    }

    @GetMapping("/getgqAll")
    @ApiOperation(value="获取工区")
    public ResponseBase getgqAll(){
        return safeService.getgqAll();
    }

    @GetMapping("/getprojectBygongqu")
    @ApiOperation(value="根据工区获取项目")
    public ResponseBase getprojectBygongqu(@RequestParam("id") Integer id){
        return safeService.getprojectBygongqu(id);
    }

    @GetMapping("getPerday")
    @ApiOperation(value="获取每天数据")
    public ResponseBase getPerday(@RequestParam("date")String date,
                                  @RequestParam(value = "projectId", required = false)Integer projectId){
        return safeService.getPerday(date, projectId);
    }

    @GetMapping("getDay")
    @ApiOperation(value="获取每天数据")
    public ResponseBase getDay(@RequestParam("date")String date, @RequestParam("gqid")Integer gqid){
        return safeService.getDay(date,gqid);
    }

    @PostMapping("deleteEvent")
    @ApiOperation(value="删除指定安全事件")
    public ResponseBase deleteEvent(@RequestParam("gids")List<Integer> gids){
        return safeService.deleteEvent(gids);
    }

    @PostMapping("/getGroups")
    @ApiOperation(value = "获取工区")
    public ResponseBase getGroups(@RequestParam(value = "projectId") String projectId){
        // modify by yangaogao 20230331 需要支持多项目，前端必须传id，不能默认
      /*  if (projectId == null || projectId.equals("")){
            //暂时默认为一标段
            projectId = 3;
        }*/
        return safeService.getGroups(Integer.parseInt(projectId));
    }
    @GetMapping("/getGroups/{projectId}")
    @ApiOperation(value = "获取工区")
    public ResponseBase getGroupsByProjectId(@PathVariable String projectId) {
        return safeService.getGroups(Integer.parseInt(projectId));
    }

    @PostMapping("/getPersonLiableByGroup")
    @ApiOperation(value = "通过工区查看对应责任人")
    public ResponseBase getPersonLiableByGroup(@RequestParam(value = "projectId", required = false) Integer projectId,
                                               @RequestParam("group")Integer group){
        if (projectId == null || projectId.equals("")){
            projectId = 3;
        }
        return safeService.getPersonLiableByGroup(projectId, group);
    }

    @PostMapping("/updateByEvent")
    @ApiOperation(value = "修改历史遗留分部项目问题")
    public ResponseBase updateByEvent(@RequestParam("uploadFile") MultipartFile file){
        return safeService.updateByEvent(file);
    }



}
