package com.ruoyi.web.controller.jianguan.business.quality;

import com.ruoyi.jianguan.business.quality.domain.dto.QualityReportPageDTO;
import com.ruoyi.jianguan.business.quality.domain.dto.QualityReportSaveDTO;
import com.ruoyi.jianguan.business.quality.service.QualityReportService;
import com.ruoyi.jianguan.business.quality.domain.vo.QualityReportDetailVo;
import com.ruoyi.jianguan.business.quality.domain.vo.QualityReportPageVo;
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
* 质量简报
* @author qiaoxulin
* @date 2022-06-02
*/
@Api(value = "质量简报", tags = {"质量简报"})
@RestController
@RequestMapping("/web/api/v1/qualityReport")
public class QualityReportController {


    @Autowired
    private QualityReportService  qualityReportService;
    @Autowired
    private FlowApiService flowApiService;

    /**
    * 新增或者更新质量简报数据
    * @param
    */
    @PostMapping(value = "/addOrUpdate", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "新增或者更新质量简报数据")
    public ResponseBase addOrUpdate(@RequestBody @ApiParam(name = "saveDto") QualityReportSaveDTO saveDto) {
        return qualityReportService.addOrUpdate(saveDto);
    }


    /**
    * 通过id删除一条质量简报 数据
    * @param id
    */
    @GetMapping(value = "/id", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "通过id删除一条质量简报数据")
    @Transactional(rollbackFor = Exception.class)
    public boolean removeById(@ApiParam(name = "id") Long id)  {
        flowApiService.deleteProcessInstanceByBusinessKey(String.valueOf(id));
        return qualityReportService.removeById(id);
    }


    /**
    * 通过id获取一条质量简报数据
    * @param id
    */
    @GetMapping(value = "/detail/id", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "通过id获取一条质量简报数据")
    public QualityReportDetailVo getById(@ApiParam(name = "id") Long id)  {
        return qualityReportService.getInfoById(id);
    }


    /**
    * 分页查询质量简报 数据
    * @param pageDto
    */
    @PostMapping(value = "/page", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "分页查询质量简报数据")
    public PageInfo<QualityReportPageVo> page(@RequestBody QualityReportPageDTO pageDto)  {
        return qualityReportService.getPageInfo(pageDto);
    }

    /**
     * 质量简报导出
     *
     * @param pageDto
     */
    @PostMapping(value = "/export", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "质量简报导出")
    public void export(@RequestBody @ApiParam(name = "pageDto") QualityReportPageDTO pageDto, HttpServletResponse response) {
        qualityReportService.export(pageDto, response);
    }

}
