package com.ruoyi.web.controller.jianguan.other.zjrw;

import com.ruoyi.common.core.domain.dto.ConponentScheuleDto;
import com.ruoyi.common.core.domain.object.ResponseBase;
import com.ruoyi.jianguan.common.domain.dto.NewCheckData;
import com.ruoyi.jianguan.common.service.ConponentScheduleService;
import com.ruoyi.jianguan.common.service.CountService;
import com.ruoyi.system.service.ISysDictDataService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @author ：lin_zhixiong
 * @date ：Created in 2021/6/26 11:49 下午
 * @version: V1.0
 * @slogan: 天下风云出我辈，一入代码苦耕耘
 * @description:
 **/

@RestController
@RequestMapping("/schedule")
@Api(value="构件进度管理")
public class ConponentScheduleController {

    @Autowired
    ConponentScheduleService conponentScheduleService;

    @Autowired
    CountService countService;


    //todo   目前没有使用到
    @GetMapping(value = "/getScheuleAll")
    public ResponseBase getScheuleAll(){
        List<ConponentScheuleDto> scheuleAll =
                conponentScheduleService.getScheuleAll();
        return  new ResponseBase(200,"查询成功",scheuleAll);
    }

    @PostMapping("/getProcessWarning")
    @ResponseBody
    @ApiOperation(value="获取进度预警数据")
    public ResponseBase getProcessWarning(@RequestBody NewCheckData newCheckData){
        return conponentScheduleService.getProcessWarning(newCheckData);
    }


}
