package com.ruoyi.web.controller.jianguan.business.constructionDesign;

import com.github.pagehelper.PageInfo;
import com.ruoyi.common.core.domain.object.ResponseBase;
import com.ruoyi.jianguan.business.constructionDesign.domain.dto.ConstructionDesignPageDTO;
import com.ruoyi.jianguan.business.constructionDesign.domain.dto.PlanConstructionDesignSaveDTO;
import com.ruoyi.jianguan.business.constructionDesign.domain.vo.PlanConstructionDesignVo;
import com.ruoyi.jianguan.business.constructionDesign.service.ConstructionDesignService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;


/**
 * 计划报审-施工图管理
 *
 * @author mickey
 * @date 2023-06-19
 */
@Slf4j
@Api(value = "计划报审-施工图管理", tags = {"计划报审-施工图管理"})
@RestController
@RequestMapping("/web/api/v1/constructionDesign/plan")
public class PlanConstructionDesignController {

    @Autowired
    private ConstructionDesignService constructionDesignService;

    /**
     * 分页查询计划报审-施工图管理表 数据
     * @param pageDto
     */
    @PostMapping(value = "/page", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "分页查询数据")
    public PageInfo<PlanConstructionDesignVo> page(@RequestBody ConstructionDesignPageDTO pageDto) {
        PageInfo<PlanConstructionDesignVo> pageInfo = constructionDesignService.getPlanPageInfo(pageDto);
        log.info("PlanConstructionDesignPageVo: {}", pageInfo);
        return pageInfo;
    }

    /**
     * 新增或者更新计划报审-施工图管理表数据
     * @param
     */
    @PostMapping(value = "/addOrUpdate", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "新增或者更新")
    public ResponseBase addOrUpdate(@RequestBody @ApiParam(name = "saveDto") PlanConstructionDesignSaveDTO saveDto) {
        return constructionDesignService.addOrUpdate(saveDto);
    }

    /**
     * 通过id删除一条计划报审-施工图管理表 数据
     * @param id
     */
    @GetMapping(value = "/id", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "通过id删除一条数据")
    @Transactional(rollbackFor = Exception.class)
    public boolean removeById(@ApiParam(name = "id") Long id) {
        return constructionDesignService.removeById(id);
    }

    /**
     * 通过id获取一条计划报审-施工图管理表数据
     * @param id
     */
    @GetMapping(value = "/detail/id", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "通过id获取一条数据")
    public PlanConstructionDesignVo getById(@ApiParam(name = "id") Long id) {
        return constructionDesignService.getPlanInfoById(id);
    }

}