package com.ruoyi.web.controller.jianguan.business.quality;

import com.ruoyi.flowable.service.FlowApiService;
import com.ruoyi.jianguan.business.quality.domain.dto.SubitemOpenPageDTO;
import com.ruoyi.jianguan.business.quality.domain.dto.SubitemOpenSaveDTO;
import com.ruoyi.jianguan.business.quality.service.SubitemOpenService;
import com.ruoyi.jianguan.business.quality.domain.vo.SubitemOpenDetailVo;
import com.ruoyi.jianguan.business.quality.domain.vo.SubitemOpenPageVo;
import com.github.pagehelper.PageInfo;
import com.ruoyi.common.core.domain.object.ResponseBase;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * 分项开工申请
 *
 * @author qiaoxulin
 * @date 2022-06-01
 */
@Api(value = "分项开工申请", tags = {"分项开工申请"})
@RestController
@RequestMapping("/web/api/v1/subitemOpen")
public class SubitemOpenController {


    @Autowired
    private SubitemOpenService subitemOpenService;
    @Autowired
    private FlowApiService flowApiService;

    /**
     * 新增或者更新分项开工申请数据
     *
     * @param
     */
    @PostMapping(value = "/addOrUpdate", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "新增或者更新分项开工申请数据")
    public ResponseBase addOrUpdate(@RequestBody @ApiParam(name = "saveDto") SubitemOpenSaveDTO saveDto) {
        return subitemOpenService.addOrUpdate(saveDto);
    }


    /**
     * 通过id删除一条分项开工申请 数据
     *
     * @param id
     */
    @GetMapping(value = "/id", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "通过id删除一条分项开工申请数据")
    @Transactional(rollbackFor = Exception.class)
    public boolean removeById(@ApiParam(name = "id") Long id) {
        flowApiService.deleteProcessInstanceByBusinessKey(String.valueOf(id));
        return subitemOpenService.removeById(id);
    }


    /**
     * 通过id获取一条分项开工申请数据
     *
     * @param id
     */
    @GetMapping(value = "/detail/id", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "通过id获取一条分项开工申请数据")
    public SubitemOpenDetailVo getById(@ApiParam(name = "id") Long id) {
        return subitemOpenService.getInfoById(id);
    }


    /**
     * 分页查询分项开工申请 数据
     *
     * @param pageDto
     */
    @PostMapping(value = "/page", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "分页查询分项开工申请数据")
    public PageInfo<SubitemOpenPageVo> page(@RequestBody SubitemOpenPageDTO pageDto) {
        return subitemOpenService.getPageInfo(pageDto);
    }

    /**
     * 分项开工申请导出
     *
     * @param pageDto
     */
    @PostMapping(value = "/export", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "分项开工申请导出")
    public void export(@RequestBody @ApiParam(name = "pageDto") SubitemOpenPageDTO pageDto, HttpServletResponse response) {
        subitemOpenService.export(pageDto, response);
    }

}
