package com.ruoyi.web.controller.jianguan.business.quality;

import com.ruoyi.jianguan.business.quality.domain.dto.BuildTechBottomPageDTO;
import com.ruoyi.jianguan.business.quality.domain.dto.BuildTechBottomSaveDTO;
import com.ruoyi.jianguan.business.quality.service.BuildTechBottomService;
import com.ruoyi.jianguan.business.quality.domain.vo.BuildTechBottomDetailVo;
import com.ruoyi.jianguan.business.quality.domain.vo.BuildTechBottomPageVo;
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
* 施工技术交底
* @author qiaoxulin
* @date 2022-05-26
*/
@Api(value = "施工技术交底", tags = {"施工技术交底"})
@RestController
@RequestMapping("/web/api/v1/buildTechBottom")
public class BuildTechBottomController {


    @Autowired
    private BuildTechBottomService  buildTechBottomService;
    @Autowired
    private FlowApiService flowApiService;

    /**
    * 新增或者更新施工技术交底数据
    * @param
    */
    @PostMapping(value = "/addOrUpdate", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "新增或者更新施工技术交底数据")
    public ResponseBase addOrUpdate(@RequestBody @ApiParam(name = "saveDto") BuildTechBottomSaveDTO saveDto) {
        return buildTechBottomService.addOrUpdate(saveDto);
    }


    /**
    * 通过id删除一条施工技术交底 数据
    * @param id
    */
    @GetMapping(value = "/id", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "通过id删除一条施工技术交底数据")
    @Transactional(rollbackFor = Exception.class)
    public boolean removeById(@ApiParam(name = "id") Long id)  {
        flowApiService.deleteProcessInstanceByBusinessKey(String.valueOf(id));
        return buildTechBottomService.removeById(id);
    }


    /**
    * 通过id获取一条施工技术交底数据
    * @param id
    */
    @GetMapping(value = "/detail/id", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "通过id获取一条施工技术交底数据")
    public BuildTechBottomDetailVo getById(@ApiParam(name = "id") Long id)  {
        return buildTechBottomService.getInfoById(id);
    }


    /**
    * 分页查询施工技术交底 数据
    * @param pageDto
    */
    @PostMapping(value = "/page", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "分页查询施工技术交底数据")
    public PageInfo<BuildTechBottomPageVo> page(@RequestBody BuildTechBottomPageDTO pageDto)  {
        return buildTechBottomService.getPageInfo(pageDto);
    }


    /**
     * 施工技术交底导出
     *
     * @param pageDto
     */
    @PostMapping(value = "/export", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "施工技术交底导出")
    public void export(@RequestBody @ApiParam(name = "pageDto") BuildTechBottomPageDTO pageDto, HttpServletResponse response) {
        buildTechBottomService.export(pageDto, response);
    }

}
