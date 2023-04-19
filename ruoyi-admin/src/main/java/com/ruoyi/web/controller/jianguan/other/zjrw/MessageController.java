package com.ruoyi.web.controller.jianguan.other.zjrw;

import com.ruoyi.common.core.domain.object.ResponseBase;
import com.ruoyi.jianguan.common.service.MessageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @version : 1.0
 * @Author : chenzhiwei
 * @date : Create file in 2022/2/25 11:02
 * @description :
 **/
@RestController
@RequestMapping("/message")
@Api(value = "消息管理")
public class MessageController {

    @Autowired
    MessageService messageService;

    @PostMapping("getMessage1")
    @ResponseBody
    @ApiOperation(value = "监理获取消息提示详情(质量、安全、工序审核)")
    public ResponseBase getSupervisorMessagePrompt(){

        return messageService.getSupervisorMessagePrompt();
    }

    @PostMapping("getMessage2")
    @ResponseBody
    @ApiOperation(value = "施工获取消息提示详情(质量、安全、工序审核)")
    public ResponseBase getConstructionMessagePrompt(){
        return messageService.getConstructionMessagePrompt();
    }

    @ResponseBody
    @PostMapping("/readProduce")
    @ApiOperation(value = "被抄送者已读工序")
    public ResponseBase readProduce(@RequestParam(value = "id", required = true)Integer id){
        return messageService.readProduce(id);
    }
}
