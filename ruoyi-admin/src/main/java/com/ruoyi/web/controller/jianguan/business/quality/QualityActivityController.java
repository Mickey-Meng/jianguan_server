package com.ruoyi.web.controller.jianguan.business.quality;

import com.ruoyi.flowable.service.FlowApiService;
import com.ruoyi.jianguan.business.quality.domain.dto.QualityActivityPageDTO;
import com.ruoyi.jianguan.business.quality.domain.dto.QualityActivitySaveDTO;
import com.ruoyi.jianguan.business.quality.service.QualityActivityService;
import com.ruoyi.jianguan.business.quality.domain.vo.QualityActivityDetailVo;
import com.ruoyi.jianguan.business.quality.domain.vo.QualityActivityPageVo;
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
* 质量活动
* @author qiaoxulin
* @date 2022-06-11
*/
@Api(value = "质量活动", tags = {"质量活动"})
@RestController
@RequestMapping("/web/api/v1/qualityActivity")
public class QualityActivityController {


    @Autowired
    private QualityActivityService  qualityActivityService;
    @Autowired
    private FlowApiService flowApiService;

    /**
    * 新增或者更新质量活动数据
    * @param
    */
    @PostMapping(value = "/addOrUpdate", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "新增或者更新质量活动数据")
    public ResponseBase addOrUpdate(@RequestBody @ApiParam(name = "saveDto") QualityActivitySaveDTO saveDto) {
        return qualityActivityService.addOrUpdate(saveDto);
    }


    /**
    * 通过id删除一条质量活动 数据
    * @param id
    */
    @GetMapping(value = "/id", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "通过id删除一条质量活动数据")
    @Transactional(rollbackFor = Exception.class)
    public boolean removeById(@ApiParam(name = "id") Long id)  {
        flowApiService.deleteProcessInstanceByBusinessKey(String.valueOf(id));
        return qualityActivityService.removeById(id);
    }


    /**
    * 通过id获取一条质量活动数据
    * @param id
    */
    @GetMapping(value = "/detail/id", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "通过id获取一条质量活动数据")
    public QualityActivityDetailVo getById(@ApiParam(name = "id") Long id)  {
        return qualityActivityService.getInfoById(id);
    }


    /**
    * 分页查询质量活动 数据
    * @param pageDto
    */
    @PostMapping(value = "/page", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "分页查询质量活动数据")
    public PageInfo<QualityActivityPageVo> page(@RequestBody QualityActivityPageDTO pageDto)  {
        return qualityActivityService.getPageInfo(pageDto);
    }

    /**
     * 质量活动导出
     *
     * @param pageDto
     */
    @PostMapping(value = "/export", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "质量活动导出")
    public void export(@RequestBody @ApiParam(name = "pageDto") QualityActivityPageDTO pageDto, HttpServletResponse response) {
        qualityActivityService.export(pageDto, response);
    }

}
