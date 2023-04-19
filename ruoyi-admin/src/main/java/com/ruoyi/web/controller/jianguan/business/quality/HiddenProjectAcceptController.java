package com.ruoyi.web.controller.jianguan.business.quality;

import com.ruoyi.jianguan.business.quality.domain.dto.HiddenProjectAcceptPageDTO;
import com.ruoyi.jianguan.business.quality.domain.dto.HiddenProjectAcceptSaveDTO;
import com.ruoyi.jianguan.business.quality.service.HiddenProjectAcceptService;
import com.ruoyi.jianguan.business.quality.domain.vo.HiddenProjectAccepDetailtVo;
import com.ruoyi.jianguan.business.quality.domain.vo.HiddenProjectAcceptPageVo;
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
 * 隐蔽工程验收记录
 *
 * @author qiaoxulin
 * @date 2022-05-12
 */
@Api(value = "隐蔽工程验收记录", tags = {"隐蔽工程验收记录"})
@RestController
@RequestMapping("/web/api/v1/hiddenProjectAccept")
public class HiddenProjectAcceptController {


    @Autowired
    private HiddenProjectAcceptService hiddenProjectAcceptService;
    @Autowired
    private FlowApiService flowApiService;

    /**
     * 新增或者更新隐蔽工程验收记录数据
     *
     * @param
     */
    @PostMapping(value = "/addOrUpdate", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "新增或者更新隐蔽工程验收记录数据")
    public ResponseBase addOrUpdate(@RequestBody @ApiParam(name = "saveDto") HiddenProjectAcceptSaveDTO saveDto) {
        return hiddenProjectAcceptService.addOrUpdate(saveDto);
    }


    /**
     * 通过id删除一条隐蔽工程验收记录 数据
     *
     * @param
     */
    @GetMapping(value = "/id", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "通过id删除一条隐蔽工程验收记录数据")
    @Transactional(rollbackFor = Exception.class)
    public boolean removeById(@ApiParam(name = "id") Long id) {
        flowApiService.deleteProcessInstanceByBusinessKey(String.valueOf(id));
        return hiddenProjectAcceptService.removeById(id);
    }


    /**
     * 通过id获取一条隐蔽工程验收记录数据
     *
     * @param
     */
    @GetMapping(value = "/detail/id", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "通过id获取一条隐蔽工程验收记录数据")
    public HiddenProjectAccepDetailtVo getById(@ApiParam(name = "id") Long id) {
        return hiddenProjectAcceptService.getInfoById(id);
    }


    /**
     * 分页查询隐蔽工程验收记录 数据
     *
     * @param
     */
    @PostMapping(value = "/page", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "分页查询隐蔽工程验收记录数据")
    public PageInfo<HiddenProjectAcceptPageVo> page(@RequestBody @ApiParam(name = "pageDto") HiddenProjectAcceptPageDTO pageDto) {
        return hiddenProjectAcceptService.getPageInfo(pageDto);
    }

    /**
     * 隐蔽工程验收记录导出
     *
     * @param pageDto
     */
    @PostMapping(value = "/export", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "隐蔽工程验收记录导出")
    public void export(@RequestBody @ApiParam(name = "pageDto") HiddenProjectAcceptPageDTO pageDto, HttpServletResponse response) {
        hiddenProjectAcceptService.export(pageDto, response);
    }
}
