package com.ruoyi.web.controller.jianguan.zjrw;

import com.ruoyi.common.core.domain.object.ResponseBase;
import com.ruoyi.jianguan.common.domain.entity.ZjPersonFence;
import com.ruoyi.jianguan.common.domain.entity.ZjPersonFenceTime;
import com.ruoyi.jianguan.common.service.FenceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author : Chen_ZhiWei
 * @Date : Create file in 2022/5/30 16:03
 * @Version : 1.0
 * @Description : 电子围栏
 **/
@Api("电子围栏")
@RestController
@RequestMapping("/fence")
public class FenceController {

    @Autowired
    private FenceService fenceService;

    @PostMapping("/addFence")
    @ApiOperation(value = "添加电子围栏信息")
    public ResponseBase addFence(@RequestBody ZjPersonFence fence){
        return fenceService.addFence(fence);
    }

    @PostMapping("/delFence")
    @ApiOperation(value = "通过外面的序号id删除指定电子围栏信息")
    public ResponseBase delFence(@RequestParam("projectId")Integer projectId,
                                 @RequestParam("id") Integer id){
        return fenceService.delFence(projectId, id);
    }

    @PostMapping("/updateFence")
    @ApiOperation(value = "通过指定id修改指定电子围栏信息")
    public ResponseBase updateFence(@RequestBody ZjPersonFence fence){
        return fenceService.updateFence(fence);
    }

    @PostMapping("/getFence")
    @ApiOperation(value = "获取电子围栏信息")
    public ResponseBase getFence(@RequestParam("projectId")Integer projectId,
                                 @RequestParam(value = "fenceId", required = false) Integer fenceId){
        return fenceService.getFence(projectId, fenceId);
    }

    @PostMapping("/addClock")
    @ApiOperation(value = "添加打卡信息")
    public ResponseBase addClock(@RequestBody ZjPersonFenceTime fence){
        return fenceService.addClock(fence);
    }

    @PostMapping("/delClock")
    @ApiOperation(value = "通过外面的序号id删除指定打卡信息")
    public ResponseBase delClock(@RequestParam("projectId")Integer projectId,
                                 @RequestParam("id") Integer id){
        return fenceService.delClock(projectId, id);
    }

    @PostMapping("/updateClock")
    @ApiOperation(value = "通过指定id修改指定打卡信息")
    public ResponseBase updateClock(@RequestBody ZjPersonFenceTime fence){
        return fenceService.updateClock(fence);
    }

    @PostMapping("/getClock")
    @ApiOperation(value = "获取所有打卡信息")
    public ResponseBase getClock(@RequestParam("projectId")Integer projectId){
        return fenceService.getClock(projectId);
    }

    @PostMapping("/getClockByUserId")
    @ApiOperation(value = "通过postId获取该用户打卡信息")
    public ResponseBase getClockByUserId(@RequestParam("projectId")Integer projectId){
        return fenceService.getClockByUserId(projectId);
    }
}
