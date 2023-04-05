package com.ruoyi.web.controller.jianguan.zjrw;

import com.ruoyi.common.core.domain.object.ResponseBase;
import com.ruoyi.jianguan.common.domain.dto.ProgressData;
import com.ruoyi.common.core.domain.model.RightData;
import com.ruoyi.jianguan.common.service.ProgressService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author ：lin_zhixiong
 * @date ：Created in 2021/7/21 2:54 下午
 * @version: V1.0
 * @slogan: 天下风云出我辈，一入代码苦耕耘
 * @description:
 **/
@RestController
@RequestMapping("/progress")
@Api(value="进度管理")
public class ProgressController {

    @Autowired
    private ProgressService progressService;

    //根据一种订单类型获取所有记录
    @PostMapping("/uploadProgress")
    @ApiOperation("批量更新进度构件的计划开始时间，计划结束时间，实际开始时间，实际结束时间")
    public ResponseBase uploadProgress(@RequestBody ProgressData progressData){

        return progressService.uploadProgress(progressData);
    }

    //根据一种订单类型获取所有记录
    @PostMapping("/getByConponentid")
    @ApiOperation("批量更新进度构件的计划开始时间，计划结束时间，实际开始时间，实际结束时间")
    public ResponseBase uploadProgress(@RequestParam(value = "conid") Integer conid){

        return progressService.getByConponentid(conid);
//        return  null;
    }

    @PostMapping("/getStatus")
    @ApiOperation("获取进度的右侧数据")
    public ResponseBase getStatus(@RequestBody RightData rightData){

        return progressService.getStatus(rightData);
    }


    @GetMapping("/getMiddleData")
    @ApiOperation("获取进度中间表格数据")
    public ResponseBase getMiddleData(@RequestParam(value = "projectId", required = false) Integer projectId){
        return progressService.getMiddleData(projectId);
    }

    @GetMapping("/projectSelect")
    @ApiOperation("获取项目下拉选数据")
    public ResponseBase projectSelect(){
        return progressService.projectSelect();
    }

    @GetMapping("/getYcData")
    @ApiOperation("获取环境监控数据")
    public ResponseBase getYcData(){
        return progressService.getYcData();
    }


    @GetMapping("/getConponentStatus")
    @ApiOperation("获取每个构件的完成情况")
    public ResponseBase getConponentStatus(@RequestParam(value = "projectId", required = false) Integer projectId){
        return progressService.getConponentStatus(projectId);
    }















}
