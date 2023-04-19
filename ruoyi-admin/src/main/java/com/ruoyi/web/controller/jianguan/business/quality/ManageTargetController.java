package com.ruoyi.web.controller.jianguan.business.quality;

import com.ruoyi.jianguan.business.quality.domain.dto.ManageTargetSaveDTO;
import com.ruoyi.common.core.domain.dto.PageDTO;
import com.ruoyi.jianguan.business.quality.service.ManageTargetService;
import com.ruoyi.jianguan.business.quality.domain.vo.ManageTargetDetailVo;
import com.ruoyi.jianguan.business.quality.domain.vo.ManageTargetPageVo;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * 管理目标
 *
 * @author qiaoxulin
 * @date 2022-05-13
 */
@Api(value = "管理目标", tags = {"管理目标"})
@RestController
@RequestMapping("/web/api/v1/manageTarget")
public class ManageTargetController {


    @Autowired
    private ManageTargetService manageTargetService;


    /**
     * 新增或者更新管理目标数据
     *
     * @param
     */
    @PostMapping(value = "/addOrUpdate", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "新增或者更新管理目标数据")
    public boolean addOrUpdate(@RequestBody @ApiParam(name = "saveDto") ManageTargetSaveDTO saveDto) {
        return manageTargetService.addOrUpdate(saveDto);
    }


    /**
     * 通过id删除一条管理目标 数据
     *
     * @param
     */
    @GetMapping(value = "/id", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "通过id删除一条管理目标数据")
    public boolean removeById(@ApiParam(name = "id") Long id) {
        return manageTargetService.removeById(id);
    }


    /**
     * 通过id获取一条管理目标数据
     *
     * @param
     */
    @GetMapping(value = "/detail/id", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "通过id获取一条管理目标数据")
    public ManageTargetDetailVo getById(@ApiParam(name = "id") Long id) {
        return manageTargetService.getInfoById(id);
    }


    /**
     * 分页查询管理目标 数据
     *
     * @param
     */
    @PostMapping(value = "/page", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "分页查询管理目标数据")
    public PageInfo<ManageTargetPageVo> page(@RequestBody PageDTO pageDto) {
        return manageTargetService.getPageInfo(pageDto);
    }

    /**
     * 管理目标导出
     *
     * @param pageDto
     */
    @PostMapping(value = "/export", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "管理目标导出")
    public void export(@RequestBody @ApiParam(name = "pageDto") PageDTO pageDto, HttpServletResponse response) {
        manageTargetService.export(pageDto, response);
    }


}
