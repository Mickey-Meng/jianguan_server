package com.ruoyi.web.controller.jianguan.contract;

import com.github.pagehelper.PageInfo;
import com.ruoyi.common.core.domain.object.ResponseBase;
import com.ruoyi.flowable.service.FlowApiService;
import com.ruoyi.jianguan.contract.domain.dto.EnterExitPageDTO;
import com.ruoyi.jianguan.contract.domain.dto.EnterExitSaveDTO;
import com.ruoyi.jianguan.contract.domain.vo.EnterExitDetailVo;
import com.ruoyi.jianguan.contract.domain.vo.EnterExitPageVo;
import com.ruoyi.jianguan.contract.service.EnterExitService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * 进退场
 *
 * @author qiaoxulin
 * @date 2022-05-27
 */
@Api(value = "进退场", tags = {"进退场"})
@RestController
@RequestMapping("/web/api/v1/enterExit")
public class EnterExitController {


    @Autowired
    private EnterExitService enterExitService;
    @Autowired
    private FlowApiService flowApiService;

    /**
     * 新增或者更新进退场数据
     *
     * @param
     */
    @PostMapping(value = "/addOrUpdate", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "新增或者更新进退场数据")
    public ResponseBase addOrUpdate(@RequestBody @ApiParam(name = "saveDto") EnterExitSaveDTO saveDto) {
        return enterExitService.addOrUpdate(saveDto);
    }


    /**
     * 通过id删除一条进退场 数据
     *
     * @param id
     */
    @GetMapping(value = "/id", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "通过id删除一条进退场数据")
    @Transactional(rollbackFor = Exception.class)
    public boolean removeById(@ApiParam(name = "id") Long id) {
        flowApiService.deleteProcessInstanceByBusinessKey(String.valueOf(id));
        return enterExitService.delById(id);
    }


    /**
     * 通过id获取一条进退场数据
     *
     * @param id
     */
    @GetMapping(value = "/detail/id", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "通过id获取一条进退场数据")
    public EnterExitDetailVo getById(@ApiParam(name = "id") Long id) {
        return enterExitService.getInfoById(id);
    }


    /**
     * 分页查询进退场 数据
     *
     * @param pageDto
     */
    @PostMapping(value = "/page", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "分页查询进退场数据")
    public PageInfo<EnterExitPageVo> page(@RequestBody EnterExitPageDTO pageDto) {
        return enterExitService.getPageInfo(pageDto);
    }

    /**
     * 进退场导出
     *
     * @param pageDto
     */
    @PostMapping(value = "/export", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "进退场导出")
    public void export(@RequestBody @ApiParam(name = "pageDto") EnterExitPageDTO pageDto, HttpServletResponse response) {
        enterExitService.export(pageDto, response);
    }
}
