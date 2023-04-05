package com.ruoyi.web.controller.jianguan.quality;

import com.ruoyi.jianguan.quality.domain.dto.SupervisionOrderPageDTO;
import com.ruoyi.jianguan.quality.domain.dto.SupervisionOrderSaveDTO;
import com.ruoyi.jianguan.quality.service.SupervisionOrderService;
import com.ruoyi.jianguan.quality.domain.vo.SupervisionOrderDetailVo;
import com.ruoyi.jianguan.quality.domain.vo.SupervisionOrderPageVo;
import com.github.pagehelper.PageInfo;
import com.ruoyi.common.core.domain.object.ResponseBase;
import com.ruoyi.flowable.service.FlowApiService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
* 监理指令
* @author qiaoxulin
* @date 2022-06-14
*/
@Api(value = "监理指令", tags = {"监理指令"})
@RestController
@RequestMapping("/web/api/v1/supervisionOrder")
public class SupervisionOrderController {


    @Autowired
    private SupervisionOrderService  supervisionOrderService;
    @Autowired
    private FlowApiService flowApiService;

    /**
    * 新增或者更新监理指令数据
    * @param
    */
    @PostMapping(value = "/addOrUpdate", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "新增或者更新监理指令数据")
    public ResponseBase addOrUpdate(@RequestBody @ApiParam(name = "saveDto") SupervisionOrderSaveDTO saveDto) {
        return supervisionOrderService.addOrUpdate(saveDto);
    }


    /**
    * 通过id删除一条监理指令 数据
    * @param id
    */
    @GetMapping(value = "/id", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "通过id删除一条监理指令数据")
    @Transactional(rollbackFor = Exception.class)
    public boolean removeById(@ApiParam(name = "id") Long id)  {
        flowApiService.deleteProcessInstanceByBusinessKey(String.valueOf(id));
        return supervisionOrderService.removeById(id);
    }


    /**
    * 通过id获取一条监理指令数据
    * @param id
    */
    @GetMapping(value = "/detail/id", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "通过id获取一条监理指令数据")
    public SupervisionOrderDetailVo getById(@ApiParam(name = "id") Long id)  {
        return supervisionOrderService.getInfoById(id);
    }


    /**
    * 分页查询监理指令 数据
    * @param pageDto
    */
    @PostMapping(value = "/page", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "分页查询监理指令数据")
    public PageInfo<SupervisionOrderPageVo> page(@RequestBody SupervisionOrderPageDTO pageDto)  {
        return supervisionOrderService.getPageInfo(pageDto);
    }

    /**
     * 监理指令导出
     *
     * @param pageDto
     */
    @PostMapping(value = "/export", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "监理指令导出")
    public void export(@RequestBody @ApiParam(name = "pageDto") SupervisionOrderPageDTO pageDto, HttpServletResponse response) {
        supervisionOrderService.export(pageDto, response);
    }
}
