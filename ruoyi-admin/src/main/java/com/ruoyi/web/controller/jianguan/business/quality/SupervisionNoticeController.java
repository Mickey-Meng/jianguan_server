package com.ruoyi.web.controller.jianguan.business.quality;

import com.ruoyi.jianguan.business.quality.domain.dto.SupervisionNoticePageDTO;
import com.ruoyi.jianguan.business.quality.domain.dto.SupervisionNoticeSaveDTO;
import com.ruoyi.jianguan.business.quality.domain.entity.SupervisionNotice;
import com.ruoyi.jianguan.business.quality.service.SupervisionNoticeService;
import com.ruoyi.jianguan.business.quality.domain.vo.SupervisionNoticePageVo;
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
* 监理通知
* @author qiaoxulin
* @date 2022-06-11
*/
@Api(value = "监理通知", tags = {"监理通知"})
@RestController
@RequestMapping("/web/api/v1/supervisionNotice")
public class SupervisionNoticeController {


    @Autowired
    private SupervisionNoticeService  supervisionNoticeService;
    @Autowired
    private FlowApiService flowApiService;

    /**
    * 新增或者更新监理通知数据
    * @param
    */
    @PostMapping(value = "/addOrUpdate", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "新增或者更新监理通知数据")
    public ResponseBase addOrUpdate(@RequestBody @ApiParam(name = "saveDto") SupervisionNoticeSaveDTO saveDto) {
        return supervisionNoticeService.addOrUpdate(saveDto);
    }


    /**
    * 通过id删除一条监理通知 数据
    * @param id
    */
    @GetMapping(value = "/id", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "通过id删除一条监理通知数据")
    @Transactional(rollbackFor = Exception.class)
    public boolean removeById(@ApiParam(name = "id") Long id)  {
        flowApiService.deleteProcessInstanceByBusinessKey(String.valueOf(id));
        return supervisionNoticeService.removeById(id);
    }


    /**
    * 通过id获取一条监理通知数据
    * @param id
    */
    @GetMapping(value = "/detail/id", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "通过id获取一条监理通知数据")
    public SupervisionNotice getById(@ApiParam(name = "id") Long id)  {
        return supervisionNoticeService.getById(id);
    }


    /**
    * 分页查询监理通知 数据
    * @param pageDto
    */
    @PostMapping(value = "/page", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "分页查询监理通知数据")
    public PageInfo<SupervisionNoticePageVo> page(@RequestBody SupervisionNoticePageDTO pageDto)  {
        return supervisionNoticeService.getPageInfo(pageDto);
    }

    /**
     * 监理通知导出
     *
     * @param pageDto
     */
    @PostMapping(value = "/export", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "监理通知导出")
    public void export(@RequestBody @ApiParam(name = "pageDto") SupervisionNoticePageDTO pageDto, HttpServletResponse response) {
        supervisionNoticeService.export(pageDto, response);
    }

}
