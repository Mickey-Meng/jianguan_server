package com.ruoyi.web.controller.jianguan.other.zjrw;

import com.google.common.collect.Maps;
import com.ruoyi.common.core.domain.object.ResponseBase;
import com.ruoyi.common.enums.BimFlowKey;
import com.ruoyi.jianguan.business.certificate.domain.entity.CertificatePhotos;
import com.ruoyi.jianguan.business.certificate.service.CertificatePhotosService;
import com.ruoyi.jianguan.business.constructionDesign.domain.entity.ConstructionDesign;
import com.ruoyi.jianguan.business.constructionDesign.service.ConstructionDesignService;
import com.ruoyi.jianguan.business.contract.domain.entity.ConstructionPlan;
import com.ruoyi.jianguan.business.contract.service.ConstructionPlanService;
import com.ruoyi.jianguan.common.service.MessageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    @Autowired
    private CertificatePhotosService certificatePhotosService;
    @Autowired
    private ConstructionDesignService constructionDesignService;
    @Autowired
    private ConstructionPlanService constructionPlanService;

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

    @GetMapping("/getExpiryRemindersList")
    @ResponseBody
    @ApiOperation(value = "获取到期提醒数据")
    public ResponseBase getExpiryRemindersList(){
        Map<String, Object> dataMap = Maps.newHashMap();
        List<CertificatePhotos> certificatePhotosExpiryRemindersList = certificatePhotosService.getExpiryRemindersList(null);
        List<ConstructionDesign> constructionDesignExpiryRemindersList = constructionDesignService.getExpiryRemindersList(null);
        List<ConstructionPlan> constructionPlanExpiryRemindersList = constructionPlanService.getExpiryRemindersList(null);
        dataMap.put("certificatePhotos", certificatePhotosExpiryRemindersList.stream().map(CertificatePhotos::getName).collect(Collectors.joining(",")));
        dataMap.put("certificatePhotosCount", certificatePhotosExpiryRemindersList.size());
        dataMap.put("constructionDesign", constructionDesignExpiryRemindersList.stream().map(ConstructionDesign::getName).collect(Collectors.joining(",")));
        dataMap.put("constructionDesignCount", constructionDesignExpiryRemindersList.size());

        dataMap.put("constructionPlan", constructionPlanExpiryRemindersList.stream().map(ConstructionPlan::getName).collect(Collectors.joining(",")));
        dataMap.put("constructionPlanCount", constructionDesignExpiryRemindersList.size());
        return ResponseBase.success(dataMap);
    }
}
