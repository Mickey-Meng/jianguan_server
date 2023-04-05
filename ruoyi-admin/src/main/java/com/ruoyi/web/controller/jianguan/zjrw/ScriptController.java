package com.ruoyi.web.controller.jianguan.zjrw;

import com.ruoyi.common.core.domain.object.ResponseBase;
import com.ruoyi.jianguan.common.service.ScriptService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author : Chen_ZhiWei
 * @Date : Create file in 2022/6/21 17:20
 * @Version : 1.0
 * @Description : 项目的一些脚本接口
 **/
@RestController
@RequestMapping("/script")
public class ScriptController {

    @Autowired
    private ScriptService scriptService;

    @PostMapping("/moveProduce")
    @ApiOperation(value = "同步迁移produceandrecode表及其相关数据")
    public ResponseBase moveProduce(){
        return scriptService.moveProduce();
    }

    @PostMapping("/moveSafeEvent")
    @ApiOperation(value = "同步迁移zjSafeEvent表及其相关数据")
    public ResponseBase moveSafeEvent(){
        return scriptService.moveSafeEvent();
    }

    @PostMapping("/moveQualityEvent")
    @ApiOperation(value = "同步迁移zjQualityEvent表及其相关数据")
    public ResponseBase moveQualityEvent(){
        return scriptService.moveQualityEvent();
    }

    @PostMapping("/getProduceTime")
    @ApiOperation(value = "获取zjconponentproducetime表多余构件code")
    public ResponseBase getProduceTime(){
        return scriptService.getProduceTime();
    }

    @PostMapping("/updateProduceId")
    @ApiOperation(value = "修改zjconponentproducetime表构件id")
    public ResponseBase updateProduceId(){
        return scriptService.updateProduceId();
    }

    @PostMapping("/updateUserOnlineRole")
    @ApiOperation(value = "修改人员在线表的人员对应角色id")
    public ResponseBase updateUserOnlineRole(){
        return scriptService.updateUserOnline();
    }

    @PostMapping("/updateProduceTime")
    @ApiOperation(value = "同步6月27号到7月3日的构件完成时间")
    public ResponseBase updateProduceTime(){
        return scriptService.updateProduceTime();
    }
}
