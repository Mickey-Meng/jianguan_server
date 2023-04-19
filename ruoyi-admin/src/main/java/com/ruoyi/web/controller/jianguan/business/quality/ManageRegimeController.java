package com.ruoyi.web.controller.jianguan.business.quality;

import com.ruoyi.common.core.domain.dto.PageDTO;
import com.ruoyi.jianguan.business.quality.domain.dto.ManageRegimeSaveDTO;
import com.ruoyi.jianguan.business.quality.service.ManageRegimeService;
import com.ruoyi.jianguan.business.quality.domain.vo.ManageRegimeDetailVo;
import com.ruoyi.jianguan.business.quality.domain.vo.ManageRegimePageVo;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * 管理制度
 *
 * @author qiaoxulin
 * @date 2022-05-13
 */
@Api(value = "管理制度", tags = {"管理制度"})
@RestController
@RequestMapping("/web/api/v1/manageRegime")
public class ManageRegimeController {


    @Autowired
    private ManageRegimeService manageRegimeService;


    /**
     * 新增或者更新管理制度数据
     *
     * @param
     */
    @PostMapping(value = "/addOrUpdate", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "新增或者更新管理制度数据")
    public boolean addOrUpdate(@RequestBody @ApiParam(name = "saveDto") ManageRegimeSaveDTO saveDto) {
        return manageRegimeService.addOrUpdate(saveDto);
    }


    /**
     * 通过id删除一条管理制度 数据
     *
     * @param
     */
    @GetMapping(value = "/id", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "通过id删除一条管理制度数据")
    public boolean removeById(@ApiParam(name = "id") Long id) {
        return manageRegimeService.removeById(id);
    }


    /**
     * 通过id获取一条管理制度数据
     *
     * @param
     */
    @GetMapping(value = "/detail/id", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "通过id获取一条管理制度数据")
    public ManageRegimeDetailVo getById(@ApiParam(name = "id") Long id) {
        return manageRegimeService.getInfoById(id);
    }


    /**
     * 分页查询管理制度 数据
     *
     * @param
     */
    @PostMapping(value = "/page", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "分页查询管理制度数据")
    public PageInfo<ManageRegimePageVo> page(@RequestBody PageDTO pageDto) {
        return manageRegimeService.getPageInfo(pageDto);
    }

    /**
     * 管理制度导出
     *
     * @param pageDto
     */
    @PostMapping(value = "/export", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "管理制度导出")
    public void export(@RequestBody @ApiParam(name = "pageDto") PageDTO pageDto, HttpServletResponse response) {
        manageRegimeService.export(pageDto, response);
    }

}
