package com.ruoyi.web.controller.jianguan.business.constructionDesign;

import com.github.pagehelper.PageInfo;
import com.ruoyi.common.core.domain.object.ResponseBase;
import com.ruoyi.jianguan.business.constructionDesign.domain.dto.ConstructionDesignPageDTO;
import com.ruoyi.jianguan.business.constructionDesign.domain.dto.ProgressConstructionDesignSaveDTO;
import com.ruoyi.jianguan.business.constructionDesign.domain.vo.ProgressConstructionDesignVo;
import com.ruoyi.jianguan.business.constructionDesign.service.ConstructionDesignService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

/**
 * 进度管理-施工图管理
 *
 * @author mickey
 * @date 2023-06-19
 */
@Slf4j
@Api(value = "进度管理-施工图管理", tags = {"进度管理-施工图管理"})
@RestController
@RequestMapping("/web/api/v1/constructionDesign/progress")
public class ProgressConstructionDesignController {

    @Autowired
    private ConstructionDesignService constructionDesignService;

    /**
     * 分页查询进度管理-施工图管理表 数据
     *
     * @param pageDto
     */
    @PostMapping(value = "/page", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "分页查询数据")
    public PageInfo<ProgressConstructionDesignVo> page(@RequestBody ConstructionDesignPageDTO pageDto) {
        PageInfo<ProgressConstructionDesignVo> pageInfo = constructionDesignService.getProgressPageInfo(pageDto);
        log.info("ProgressConstructionDesignPageVo: {}", pageInfo);
        return pageInfo;
    }

    /**
     * 新增或者更新进度管理-施工图管理表数据
     *
     * @param
     */
    @PostMapping(value = "/addOrUpdate", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "新增或者更新")
    public ResponseBase addOrUpdate(@RequestBody @ApiParam(name = "saveDto") ProgressConstructionDesignSaveDTO saveDto) {
        return constructionDesignService.updateUploadFile(saveDto);
    }

    /**
     * 通过id删除一条进度管理-施工图管理表 数据
     *
     * @param id
     */
    @GetMapping(value = "/id", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "通过id删除一条数据")
    @Transactional(rollbackFor = Exception.class)
    public boolean removeById(@ApiParam(name = "id") Long id) {
        return constructionDesignService.removeById(id);
    }

    /**
     * 通过id获取一条进度管理-施工图管理表数据
     *
     * @param id
     */
    @GetMapping(value = "/detail/id", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "通过id获取一条数据")
    public ProgressConstructionDesignVo getById(@ApiParam(name = "id") Long id) {
        return constructionDesignService.getProgressInfoById(id);
    }

}