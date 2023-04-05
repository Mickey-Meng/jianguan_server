package com.ruoyi.web.controller.jianguan.mytask;

import com.github.pagehelper.PageInfo;
import com.ruoyi.flowable.domain.dto.FlowTypePageDTO;
import com.ruoyi.flowable.domain.vo.FlowAuditEntryDetailVo;
import com.ruoyi.flowable.domain.vo.FlowTypeDetailVo;
import com.ruoyi.flowable.model.FlowType;
import com.ruoyi.flowable.service.FlowTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 流程类型
 *
 * @author qiaoxulin
 * @date 2022-06-11
 */
@Api(value = "流程类型", tags = {"流程类型"})
@RestController
@RequestMapping("/web/api/v1/flowType")
public class FlowTypeController {


    @Autowired
    private FlowTypeService flowTypeService;


    /**
     * 新增或者更新流程类型数据
     *
     * @param
     */
    @PostMapping(value = "/addOrUpdate", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "新增或者更新流程类型数据")
    public boolean addOrUpdate(@RequestBody @ApiParam(name = "flowType") FlowType flowType) {
        return flowTypeService.addOrUpdate(flowType);
    }


    /**
     * 通过id删除一条流程类型 数据
     *
     * @param id
     */
    @GetMapping(value = "/id", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "通过id删除一条流程类型数据")
    public boolean removeById(@ApiParam(name = "id") Long id) {
        return flowTypeService.delById(id);
    }


    /**
     * 通过id获取一条流程类型数据
     *
     * @param id
     */
    @GetMapping(value = "/detail/id", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "通过id获取一条流程类型数据")
    public FlowType getById(@ApiParam(name = "id") Long id) {
        return flowTypeService.getById(id);
    }


    /**
     * 分页查询流程类型 数据
     *
     * @param pageDto
     */
    @PostMapping(value = "/page", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "分页查询流程类型数据")
    public PageInfo<FlowTypeDetailVo> page(@RequestBody FlowTypePageDTO pageDto) {
        return flowTypeService.getPageInfo(pageDto);
    }

    /**
     * 通过流程分类id查询流程审核人员 数据
     *
     * @param projectId
     */
    @GetMapping(value = "/getAuditInfoByTypeId", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "通过流程分类id查询流程审核人数据")
    public List<FlowAuditEntryDetailVo> getAuditInfoByTypeId(Integer projectId, Long id, Integer buildSection) {
        return flowTypeService.getAuditInfoByTypeId(projectId, id, buildSection);
    }

}
