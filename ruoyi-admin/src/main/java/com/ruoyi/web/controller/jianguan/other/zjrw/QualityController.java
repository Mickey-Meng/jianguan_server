package com.ruoyi.web.controller.jianguan.other.zjrw;

import com.ruoyi.common.core.domain.object.ResponseBase;
import com.ruoyi.jianguan.common.domain.dto.DoEvent;
import com.ruoyi.jianguan.common.domain.dto.QualityEventUpload;
import com.ruoyi.jianguan.common.domain.dto.SubMitDelayEvent;
import com.ruoyi.jianguan.common.domain.dto.SubmitDealWithSafeEvent;
import com.ruoyi.jianguan.common.service.QualityService;
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
@RequestMapping("/quality")
@Api(value="安全事件管理")
public class QualityController {


    @Autowired
    QualityService qualityService;

    @Autowired
    SafeService safeService;


    @GetMapping("/getTree")
    @ResponseBody
    @ApiOperation(value="获取质量大小类数据")
    public ResponseBase getTree(@RequestParam(value ="id",required = false) Integer id,
                                @RequestParam(value = "projectId", required = false)Integer projectId){
        if (projectId == null || projectId.equals("")){
            projectId = 3;
        }
        if(ObjectUtils.isEmpty(id) || id == null){
            id = 0;
        }
        return qualityService.getTree(id, projectId);
    }

    @GetMapping("/getcheck")
    @ResponseBody
    @ApiOperation(value="获取每个工区的负责人")
    public ResponseBase getcheck(){
        return safeService.getcheck();
    }

    @PostMapping("/uploadData")
    @ResponseBody
    @ApiOperation(value="监理上传质量检查")
    public ResponseBase uploadCheck(@RequestBody QualityEventUpload qualityEventUpload){
        return qualityService.uploadCheck(qualityEventUpload);
    }

    @GetMapping("/uploadAddr")
    @ResponseBody
    @ApiOperation(value="监理上传质量检查")
   public ResponseBase uploadAddr(@RequestParam(name="id",required = false) Integer id){
        if(ObjectUtils.isEmpty(id)){
            return safeService.getQu();
        }
        return safeService.getProject(id);
    }

    @GetMapping("/getQualityEvent")
    @ResponseBody
    @ApiOperation(value="施工方获取安全检查事件")
    public ResponseBase getSafeEvent(@RequestParam(value = "projectId", required = false) Integer projectId){
        return qualityService.getQualityEvent(projectId);
    }

    @PostMapping("/submitDelaySafeEvent")
    @ResponseBody
    @ApiOperation(value="施工方对安全事件进行延期处理")
    public ResponseBase submitDelaySafeEvent(@RequestBody SubMitDelayEvent doDelayEvent){
        return qualityService.submitDelaySafeEvent(doDelayEvent);
    }

    @GetMapping("/getDelaySafeEvent")
    @ResponseBody
    @ApiOperation(value="监理方查询延期安全延期代办")
    public ResponseBase getDelaySafeEvent(@RequestParam(value = "projectId", required = false) Integer projectId){
        return qualityService.getDelaySafeEvent(projectId);
    }

    @PostMapping("/doDelaySafeEvent")
    @ResponseBody
    @ApiOperation(value="监理方确认安全事件延期处理代办")
    public ResponseBase doDelaySafeEvent(@RequestBody DoEvent doDelayEvent){
        return qualityService.doDelaySafeEvent(doDelayEvent);
    }

    @PostMapping("/submitDealWithSafeEvent")
    @ResponseBody
    @ApiOperation(value="施工方对安全事件进行处理")
    public ResponseBase submitDealWithSafeEvent(@RequestBody SubmitDealWithSafeEvent submitDealWithSafeEvent){
        return qualityService.submitDealWithSafeEvent(submitDealWithSafeEvent);
    }

    @GetMapping("/getNotDoneSafeEvent")
    @ResponseBody
    @ApiOperation(value="监理方查询安全整改代办")
    public ResponseBase getNotDoneSafeEvent(@RequestParam(value = "projectId", required = false) Integer projectId){
        return qualityService.getNotDoneSafeEvent(projectId);
    }

    @PostMapping("/doNotDoneSafeEvent")
    @ResponseBody
    @ApiOperation(value="监理方处理安全整改代办")
    public ResponseBase doNotDoneSafeEvent(@RequestBody DoEvent doDelayEvent){
        return qualityService.doNotDoneSafeEvent(doDelayEvent);
    }

    @GetMapping("/getDoneSafeEvent")
    @ResponseBody
    @ApiOperation(value="监理方查询已办安全检查事件")
    public ResponseBase getDoneSafeEvent(){
        return qualityService.getDoneSafeEvent();
    }

    @GetMapping("/getAllStatusQualityEvent")
    @ResponseBody
    @ApiOperation(value="监理人查询所有质量检查事件")
    public ResponseBase getAllStatusQualityEvent(@RequestParam(value = "projectId", required = false)Integer projectId,
                                                 @RequestParam(value = "singleProjectId", required = false)Integer singleProjectId){
        if (projectId == null || projectId.equals("")){
            projectId = 3;
        }
        return qualityService.getAllStatusQualityEvent(projectId, singleProjectId);
    }

    @GetMapping("/getAllSafeEvent")
    @ResponseBody
    @ApiOperation(value="获取所有的质量事件")
    public ResponseBase getAllSafeEvent(@RequestParam(value = "projectId" , required = false)Integer projectId,
                                        @RequestParam(value = "singleProjectId", required = false) Integer singleProjectId){
        return qualityService.getAllSafeEvent(projectId, singleProjectId);
    }


    @PostMapping("/upload")
    @ApiOperation(value="上传安全事件")
    public ResponseBase upload(@RequestParam("uploadFile") MultipartFile uploadFile){
        try {
            InputStream inputStream = uploadFile.getInputStream();
            List<List<Object>> list = MyExcelUtil.getCourseListByExcel(inputStream, uploadFile.getOriginalFilename());
            inputStream.close();
            qualityService.uploadxyz(list);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseBase("上传成功");
    }

    @GetMapping("/newInterface")
    @ApiOperation(value="质量总览统计")
    public ResponseBase newInterface(@RequestParam("count") Integer count,
                                     @RequestParam(value = "projectId", required = false) Integer projectId){
        return qualityService.newInterface(count, projectId);
    }

    @GetMapping("/group")
    @ApiOperation(value="质量分组")
    public ResponseBase group(@RequestParam("count") Integer count,
                              @RequestParam(value = "projectId", required = false)Integer projectId){
        return qualityService.group(count, projectId);
    }

    @GetMapping("getPerday")
    @ApiOperation(value="获取每天数据")
    public ResponseBase getPerday(@RequestParam("date")String date,
                                  @RequestParam(value = "projectId", required = false)Integer projectId){
        return qualityService.getPerday(date, projectId);

    }

    @GetMapping("getDay")
    @ApiOperation(value="获取每天数据")
    public ResponseBase getDay(@RequestParam("date")String date, @RequestParam("gqid")Integer gqid){
        return qualityService.getDay(date,gqid);
    }

    @PostMapping("deleteEvent")
    @ApiOperation(value="删除指定安全事件")
    public ResponseBase deleteEvent(@RequestParam("gids")List<Integer> gids){
        return qualityService.deleteEvent(gids);
    }

    @PostMapping("/getGroups")
    @ApiOperation(value = "获取工区")
    public ResponseBase getGroups(@RequestParam(value = "projectId", required = false)Integer projectId){
        if (projectId == null || projectId.equals("")){
            //暂时默认为一标段
            projectId = 3;
        }
        return safeService.getGroups(projectId);
    }

    @PostMapping("/getPersonLiableByGroup")
    @ApiOperation(value = "通过工区查看对应责任人")
    public ResponseBase getPersonLiableByGroup(@RequestParam(value = "projectId", required = false) Integer projectId,
                                               @RequestParam("group")Integer group){
        if (projectId == null || projectId.equals("")){
            //暂时默认为G235国道一标段
            projectId = 3;
        }
        return safeService.getPersonLiableByGroup(projectId, group);
    }

    @PostMapping("/updateByEvent")
    @ApiOperation(value = "修改历史遗留分部项目问题")
    public ResponseBase updateByEvent(@RequestParam("uploadFile") MultipartFile file){
        return qualityService.updateByEvent(file);
    }









































}
