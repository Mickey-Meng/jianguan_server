package com.ruoyi.web.controller.jianguan.quality;

import com.ruoyi.jianguan.quality.domain.dto.ProjectOpenPageDTO;
import com.ruoyi.jianguan.quality.domain.dto.ProjectOpenSaveDTO;
import com.ruoyi.jianguan.quality.service.ProjectOpenService;
import com.ruoyi.jianguan.quality.domain.vo.ProjectOpenDetailVo;
import com.ruoyi.jianguan.quality.domain.vo.ProjectOpenPageVo;
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
 * 项目开工申请
 *
 * @author qiaoxulin
 * @date 2022-06-01
 */
@Api(value = "项目开工申请", tags = {"项目开工申请"})
@RestController
@RequestMapping("/web/api/v1/projectOpen")
public class ProjectOpenController {


    @Autowired
    private ProjectOpenService projectOpenService;
    @Autowired
    private FlowApiService flowApiService;

    /**
     * 新增或者更新项目开工申请数据
     *
     * @param
     */
    @PostMapping(value = "/addOrUpdate", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "新增或者更新项目开工申请数据")
    public ResponseBase addOrUpdate(@RequestBody ProjectOpenSaveDTO saveDto) {
        return projectOpenService.addOrUpdate(saveDto);
    }


    /**
     * 通过id删除一条项目开工申请 数据
     *
     * @param id
     */
    @GetMapping(value = "/id", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "通过id删除一条项目开工申请数据")
    @Transactional(rollbackFor = Exception.class)
    public boolean removeById(@ApiParam(name = "id") Long id) {
        flowApiService.deleteProcessInstanceByBusinessKey(String.valueOf(id));
        return projectOpenService.removeById(id);
    }


    /**
     * 通过id获取一条项目开工申请数据
     *
     * @param id
     */
    @GetMapping(value = "/detail/id", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "通过id获取一条项目开工申请数据")
    public ProjectOpenDetailVo getById(@ApiParam(name = "id") Long id) {
        return projectOpenService.getInfoById(id);
    }


    /**
     * 分页查询项目开工申请 数据
     *
     * @param pageDto
     */
    @PostMapping(value = "/page", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "分页查询项目开工申请数据")
    public PageInfo<ProjectOpenPageVo> page(@RequestBody ProjectOpenPageDTO pageDto) {
        return projectOpenService.getPageInfo(pageDto);
    }

    /**
     * 项目开工申请导出
     *
     * @param pageDto
     */
    @PostMapping(value = "/export", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "项目开工申请导出")
    public void export(@RequestBody @ApiParam(name = "pageDto") ProjectOpenPageDTO pageDto, HttpServletResponse response) {
        projectOpenService.export(pageDto, response);
    }

}
