package com.ruoyi.web.controller.jianguan.quality;

import com.ruoyi.jianguan.quality.domain.dto.BuildPlanPageDTO;
import com.ruoyi.jianguan.quality.domain.dto.BuildPlanSaveDTO;
import com.ruoyi.jianguan.quality.service.BuildPlanService;
import com.ruoyi.jianguan.quality.domain.vo.BuildPlanDetailVo;
import com.ruoyi.jianguan.quality.domain.vo.BuildPlanPageVo;
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
* 施工方案
* @author qiaoxulin
* @date 2022-05-25
*/
@Api(value = "施工方案", tags = {"施工方案"})
@RestController
@RequestMapping("/web/api/v1/buildPlan")
public class BuildPlanController {


    @Autowired
    private BuildPlanService  buildPlanService;
    @Autowired
    private FlowApiService flowApiService;

    /**
    * 新增或者更新施工方案数据
    * @param
    */
    @PostMapping(value = "/addOrUpdate", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "新增或者更新施工方案数据")
    public ResponseBase addOrUpdate(@RequestBody @ApiParam(name = "saveDto") BuildPlanSaveDTO saveDto) {
        return buildPlanService.addOrUpdate(saveDto);
    }


    /**
    * 通过id删除一条施工方案 数据
    * @param id
    */
    @GetMapping(value = "/id", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "通过id删除一条施工方案数据")
    @Transactional(rollbackFor = Exception.class)
    public boolean removeById(@ApiParam(name = "id") Long id)  {
        flowApiService.deleteProcessInstanceByBusinessKey(String.valueOf(id));
        return buildPlanService.removeById(id);
    }


    /**
    * 通过id获取一条施工方案数据
    * @param id
    */
    @GetMapping(value = "/detail/id", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "通过id获取一条施工方案数据")
    public BuildPlanDetailVo getById(@ApiParam(name = "id") Long id)  {
        return buildPlanService.getInfoById(id);
    }


    /**
    * 分页查询施工方案 数据
    * @param pageDto
    */
    @PostMapping(value = "/page", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "分页查询施工方案数据")
    public PageInfo<BuildPlanPageVo> page(@RequestBody BuildPlanPageDTO pageDto)  {
        return buildPlanService.getPageInfo(pageDto);
    }

    /**
     * 施工方案导出
     *
     * @param pageDto
     */
    @PostMapping(value = "/export", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "施工方案导出")
    public void export(@RequestBody @ApiParam(name = "pageDto") BuildPlanPageDTO pageDto, HttpServletResponse response) {
        buildPlanService.export(pageDto, response);
    }

}
