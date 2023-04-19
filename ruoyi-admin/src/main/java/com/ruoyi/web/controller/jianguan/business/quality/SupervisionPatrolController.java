package com.ruoyi.web.controller.jianguan.business.quality;

import com.ruoyi.jianguan.business.quality.domain.dto.SupervisionPatrolPageDTO;
import com.ruoyi.jianguan.business.quality.domain.dto.SupervisionPatrolSaveDTO;
import com.ruoyi.jianguan.business.quality.service.SupervisionPatrolService;
import com.ruoyi.jianguan.business.quality.domain.vo.SupervisionPatrolDetailVo;
import com.ruoyi.jianguan.business.quality.domain.vo.SupervisionPatrolPageVo;
import com.github.pagehelper.PageInfo;
import com.ruoyi.common.core.domain.object.ResponseBase;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
* 监理巡视
* @author qiaoxulin
* @date 2022-06-10
*/
@Api(value = "监理巡视", tags = {"监理巡视"})
@RestController
@RequestMapping("/web/api/v1/supervisionPatrol")
public class SupervisionPatrolController {


    @Autowired
    private SupervisionPatrolService  supervisionPatrolService;


    /**
    * 新增或者更新监理巡视数据
    * @param
    */
    @PostMapping(value = "/addOrUpdate", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "新增或者更新监理巡视数据")
    public ResponseBase addOrUpdate(@RequestBody @ApiParam(name = "saveDto") SupervisionPatrolSaveDTO saveDto) {
        return supervisionPatrolService.addOrUpdate(saveDto);
    }


    /**
    * 通过id删除一条监理巡视 数据
    * @param id
    */
    @GetMapping(value = "/id", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "通过id删除一条监理巡视数据")
    public boolean removeById(@ApiParam(name = "id") Long id)  {
        return supervisionPatrolService.removeById(id);
    }


    /**
    * 通过id获取一条监理巡视数据
    * @param id
    */
    @GetMapping(value = "/detail/id", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "通过id获取一条监理巡视数据")
    public SupervisionPatrolDetailVo getById(@ApiParam(name = "id") Long id)  {
        return supervisionPatrolService.getInfoById(id);
    }


    /**
    * 分页查询监理巡视 数据
    * @param pageDto
    */
    @PostMapping(value = "/page", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "分页查询监理巡视数据")
    public PageInfo<SupervisionPatrolPageVo> page(@RequestBody SupervisionPatrolPageDTO pageDto)  {
        return supervisionPatrolService.getPageInfo(pageDto);
    }


    /**
     * 监理巡视导出
     *
     * @param pageDto
     */
    @PostMapping(value = "/export", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "监理巡视导出")
    public void export(@RequestBody @ApiParam(name = "pageDto") SupervisionPatrolPageDTO pageDto, HttpServletResponse response) {
        supervisionPatrolService.export(pageDto, response);
    }

}
