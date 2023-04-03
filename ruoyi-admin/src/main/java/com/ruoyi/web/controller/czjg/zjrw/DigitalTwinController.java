package com.ruoyi.web.controller.czjg.zjrw;

import com.ruoyi.common.core.domain.object.ResponseBase;
import com.ruoyi.czjg.zjrw.domain.entity.ZjDigitalTwin;
import com.ruoyi.czjg.zjrw.service.DigitalTwinService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @version : 1.0
 * @Author : chenzhiwei
 * @date : Create file in 2022/3/3 11:22
 * @description : 数字孪生-工点信息
 **/
@RestController
@RequestMapping("/digitalTwin")
@Api(value = "数字孪生")
public class DigitalTwinController {

    @Autowired
    DigitalTwinService twinService;

    @PostMapping("/addData")
    @ApiOperation(value="添加工点数据(单个添加)")
    public ResponseBase addData(@RequestBody ZjDigitalTwin zjDigitalTwin){
        return twinService.addData(zjDigitalTwin);
    }

    @PostMapping("/getData")
    @ApiOperation(value = "获取工点数据")
    public ResponseBase getData(@RequestParam("projectId")Integer projectId){
        return twinService.getData(projectId);
    }

    @PostMapping("/delData")
    @ApiOperation(value = "删除工点数据")
    public ResponseBase delData(@RequestParam("id")Integer id){
        return twinService.delData(id);
    }

    @PostMapping("/updateData")
    @ApiOperation(value = "修改工点数据")
    public ResponseBase updateData(@RequestBody ZjDigitalTwin zjDigitalTwin){
        return twinService.updateData(zjDigitalTwin);
    }
}
