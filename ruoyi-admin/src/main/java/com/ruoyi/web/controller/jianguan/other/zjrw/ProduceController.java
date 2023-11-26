package com.ruoyi.web.controller.jianguan.other.zjrw;

import com.ruoyi.common.core.domain.object.ResponseBase;
import com.ruoyi.jianguan.common.domain.dto.RecodeQueryData;
import com.ruoyi.jianguan.common.domain.dto.RecodeUploadData;
import com.ruoyi.jianguan.common.domain.dto.RecondSubmitData;
import com.ruoyi.jianguan.common.service.ProduceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * @author ：lin_zhixiong
 * @date ：Created in 2021/7/1 3:15 下午
 * @version: V1.0
 * @slogan: 天下风云出我辈，一入代码苦耕耘
 * @description:
 **/

@RestController
@RequestMapping("/produce")
@Api(value="工序管理")
public class ProduceController {

    @Autowired
    ProduceService produceService;

    @PostMapping("/uploadFile")
    @ApiOperation("获取工序图数据")
    public ResponseBase uploadTime(@RequestParam("uploadFile") MultipartFile uploadFile) throws Exception {
//        if(type==null){
//            type="ZJ";
//        }
//        if(!"ZJ".equals(type)){
//            return new ResponseBase(500,"目前不支持");
//        }
//        if(isfinish==null){
//            isfinish =2;
//        }
//        ResponseBase responseBase =produceService.getRecodeStatus(type,isfinish,0);
        //todo
        return produceService.uploadTime(uploadFile);
    }

    //根据一种订单类型获取所有记录
    @PostMapping("/getTypeStatus")
    @ApiOperation("获取工序图数据")
    public ResponseBase getRecodeStatus(@RequestBody RecodeQueryData recodeQueryData){
        if(recodeQueryData.getType()==null || "".equals(recodeQueryData.getType())){
            recodeQueryData.setType("ZJ");
            recodeQueryData.setProjectType("QL");
        }
        return produceService.getRecodeStatus(recodeQueryData);
    }

    @GetMapping("/getCheckDataByConponentId")
    @ApiOperation("根据构件编码查询构件的报检信息")
    public ResponseBase getCheckDataByConponentId(@RequestParam("id") Integer id){
        if(ObjectUtils.isEmpty(id)){
            return new ResponseBase(500,"没有构建编码");
        }
        // 第三层数据 查询所有的构件
        return produceService.getCheckDataByConponentId(id);
    }


    @GetMapping("/getCheckDataByrecod")
    @ApiOperation("根据构件记录查询构件的报检信息")
    public ResponseBase getCheckDataByrecod(@RequestParam("recodeid") Integer recodeid){
        if(ObjectUtils.isEmpty(recodeid)){
            return new ResponseBase(500,"recodeid为null");
        }
        // 第三层数据 查询所有的构件
        return new ResponseBase(200,"查询成功",produceService.getCheckDataByrecod(recodeid));
    }

    @GetMapping("/getCheckDataById")
    @ApiOperation("根据构件id查询每到工序的记录")
    public ResponseBase getCheckDataById(@RequestParam(value ="id",required = false) Integer id){
        if(ObjectUtils.isEmpty(id)){
            return new ResponseBase(500,"id为null");
        }
        // 查询所有的数据
        return produceService.getCheckDataById(id);
    }

    @PostMapping("/addRecode")
    @ApiOperation("上传填报记录")
    public ResponseBase uploadRecode(@RequestBody RecodeUploadData recodeData){
        if (recodeData.getProjectId() == null || recodeData.getProjectId().equals("")){
            //默认为一标段
            recodeData.setProjectId(3);
        }
        // 工序id  ， 是哪个记录 自检 ，专检  还是 报告 ，
        return produceService.uploadRecode(recodeData);
    }

    @PostMapping("/updateRecode")
    @ApiOperation("修改填报记录")
    public ResponseBase updateRecode(@RequestBody RecodeUploadData recodeData){
        return produceService.updateRecode(recodeData);
    }

    @PostMapping("/findDataByCode")
    @ApiOperation("获取填报记录")
    public ResponseBase findDataByCode(@RequestParam("conponentCode") String  conponentCode){
        // 工序id  ， 是哪个记录 自检 ，专检  还是 报告 ，
        return produceService.findDataByCode(conponentCode);
    }


    @PostMapping("/check")
    @ApiOperation("确认填报记录")
    public ResponseBase check(@RequestBody RecondSubmitData recondSubmitData){
        // 工序id  ， 是哪个记录 自检 ，专检  还是 报告 ，
        return produceService.check(recondSubmitData);
    }

    @PostMapping("/getAllcheckData")
    @ApiOperation("查询填报记录")
    public ResponseBase getAllcheckData(@RequestParam(value ="type",required = false) Integer type,
                                        @RequestParam(value = "projectId", required = false) Integer projectId){
        if (projectId == null || projectId.equals("")){
            return new ResponseBase(200, "请输入有效的项目id!");
        }
        // 工序id  ， 是哪个记录 自检 ，专检  还是 报告 ，
        return produceService.getAllcheckData(type, projectId);
    }

    @GetMapping("/getAgency")
    @ApiOperation("获取检查代办")
    public ResponseBase getAgency(@RequestParam(value = "projectId", required = false) Integer projectId){
        return produceService.getAgency(projectId);
    }

    @GetMapping("/getChecker")
    @ApiOperation("根据构件编码查询构件报检人信息")
    public ResponseBase getChecker(@RequestParam(value = "group", required = false)String group,
                                   @RequestParam(value = "projectId", required = false) Integer projectId){
        // group    GQ01   /  GQ02
        return produceService.getChecker(group, projectId);
    }

    @GetMapping("/getPorjectItem")
    @ApiOperation("获取当前人报检的项目，返回3个项目类型桥梁，地面，地道工程下面的每一个子项目")
    public ResponseBase getPorjectItem(){
        // 工序id  ， 是哪个记录 自检 ，专检  还是 报告
       return  produceService.getPorjectItem();
    }

    @GetMapping("/getUnitProjectCheckType")
    @ApiOperation("根据子项目获取项目的汇总构件类型")
    public ResponseBase getUnitProjectCheckType(@RequestParam(value ="projectid",required = true)String projectid){
        // 工序id  ， 是哪个记录 自检 ，专检  还是 报告
        return  produceService.getUnitProjectCheckType(projectid);
    }

    @PostMapping("/deleteProcess")
    @ApiOperation("删除工序")
    public ResponseBase deleteProcess(@RequestParam("conponentid")Integer conponentid,
                                      @RequestParam("recodeid") Integer recodeid){
        return produceService.deleteProcess(conponentid, recodeid);
    }

    @PostMapping("/getAllReverse")
    @ApiOperation(value = "获取所有工序（倒叙排列）")
    public ResponseBase getAllReverse(@RequestBody RecodeQueryData recodeQueryData){
        return produceService.getAllReverse(recodeQueryData.getProjectId());
    }

    @PostMapping("/syncData")
    @ApiOperation(value = "同步工序记录表与构建进度表数据")
    public ResponseBase syncData(@RequestParam("projectid") String projectId){
        return produceService.syncData(Integer.parseInt(projectId));
    }

    @PostMapping("/updateGroups")
    @ApiOperation(value = "同步工序记录表与构建表数据组织Code（如：QL16Z）")
    public ResponseBase updateGroups(@RequestParam(value = "projectId", required = false) String projectId){
        return produceService.updateGroups(Integer.parseInt(projectId));
    }

    @PostMapping("/getCopyInfos")
    @ApiOperation(value = "被抄送人获取可看见的工序数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "projectId", value = "项目标段id(必传)"),
            @ApiImplicitParam(name = "workAreaId", value = "工区id（当没有时也需要传，但为空字符串）"),
            @ApiImplicitParam(name = "unitProjectCode", value = "单位工程code（传参同上）"),
            @ApiImplicitParam(name = "startTime", value = "时间段开始 格式：yyyy-MM-dd（传参同上）"),
            @ApiImplicitParam(name = "endTime", value = "时间段结束（传参同上）")
    })
    public ResponseBase getCopyInfos(@RequestBody Map<String, String> data){
        return produceService.getCopyInfos(data);
    }


}
