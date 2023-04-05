package com.ruoyi.web.controller.jianguan.quality;

import com.ruoyi.jianguan.quality.domain.dto.FirstAcceptPageDTO;
import com.ruoyi.jianguan.quality.domain.dto.FirstAcceptSaveDTO;
import com.ruoyi.jianguan.quality.service.FirstAcceptService;
import com.ruoyi.jianguan.quality.domain.vo.FirstAcceptDetailVo;
import com.ruoyi.jianguan.quality.domain.vo.FirstAcceptPageVo;
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
 * 首件认可
 *
 * @author qiaoxulin
 * @date 2022-06-01
 */
@Api(value = "首件认可", tags = {"首件认可"})
@RestController
@RequestMapping("/web/api/v1/firstAccept")
public class FirstAcceptController {


    @Autowired
    private FirstAcceptService firstAcceptService;
    @Autowired
    private FlowApiService flowApiService;

    /**
     * 新增或者更新首件认可数据
     *
     * @param
     */
    @PostMapping(value = "/addOrUpdate", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "新增或者更新首件认可数据")
    public ResponseBase addOrUpdate(@RequestBody @ApiParam(name = "saveDto") FirstAcceptSaveDTO saveDto) {
        return firstAcceptService.addOrUpdate(saveDto);
    }


    /**
     * 通过id删除一条首件认可 数据
     *
     * @param id
     */
    @GetMapping(value = "/id", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "通过id删除一条首件认可数据")
    @Transactional(rollbackFor = Exception.class)
    public boolean removeById(@ApiParam(name = "id") Long id) {
        flowApiService.deleteProcessInstanceByBusinessKey(String.valueOf(id));
        return firstAcceptService.removeById(id);
    }


    /**
     * 通过id获取一条首件认可数据
     *
     * @param id
     */
    @GetMapping(value = "/detail/id", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "通过id获取一条首件认可数据")
    public FirstAcceptDetailVo getById(@ApiParam(name = "id") Long id) {
        return firstAcceptService.getInfoById(id);
    }


    /**
     * 分页查询首件认可 数据
     *
     * @param pageDto
     */
    @PostMapping(value = "/page", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "分页查询首件认可数据")
    public PageInfo<FirstAcceptPageVo> page(@RequestBody FirstAcceptPageDTO pageDto) {
        return firstAcceptService.getPageInfo(pageDto);
    }

    /**
     * 首件认可导出
     *
     * @param pageDto
     */
    @PostMapping(value = "/export", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "首件认可导出")
    public void export(@RequestBody @ApiParam(name = "pageDto") FirstAcceptPageDTO pageDto, HttpServletResponse response) {
        firstAcceptService.export(pageDto, response);
    }

}
