package com.ruoyi.web.controller.jianguan.business.metrology;


import com.github.pagehelper.PageInfo;
import com.ruoyi.common.core.domain.object.ResponseBase;
import com.ruoyi.flowable.service.FlowApiService;
import com.ruoyi.jianguan.business.metrology.domain.dto.MetrologyPageDTO;
import com.ruoyi.jianguan.business.metrology.domain.dto.MetrologySaveDTO;
import com.ruoyi.jianguan.business.metrology.domain.entity.Metrology;
import com.ruoyi.jianguan.business.metrology.domain.vo.MetrologyDetailVo;
import com.ruoyi.jianguan.business.metrology.domain.vo.MetrologyPageVo;
import com.ruoyi.jianguan.business.metrology.service.MetrologyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 计量
 *
 * @author G.X.L
 * @date 2023-03-29
 */
@Api(value = "计量", tags = {"计量"})
@RestController
@RequestMapping("/web/api/v1/metrology")
public class MetrologyController {


    @Autowired
    private MetrologyService metrologyService;

    @Autowired
    private FlowApiService flowApiService;

    /**
     * 新增或者更新施工专业分包合同表数据
     *
     * @param
     */
    @PostMapping(value = "/addOrUpdate", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "新增或者更新")
    public ResponseBase addOrUpdate(@RequestBody @ApiParam(name = "saveDto") MetrologySaveDTO saveDto) {
        return metrologyService.addOrUpdate(saveDto);
    }


    /**
     * 通过id删除一条施工专业分包合同表 数据
     *
     * @param id
     */
    @GetMapping(value = "/id", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "通过id删除一条数据")
    @Transactional(rollbackFor = Exception.class)
    public boolean removeById(@ApiParam(name = "id") Long id) {
        return metrologyService.removeById(id);
    }


    /**
     * 通过id获取一条施工专业分包合同表数据
     *
     * @param id
     */
    @GetMapping(value = "/detail/id", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "通过id获取一条数据")
    public MetrologyDetailVo getById(@ApiParam(name = "id") Long id) {
        return metrologyService.getInfoById(id);
    }


    /**
     * 分页查询施工专业分包合同表 数据
     *
     * @param pageDto
     */
    @PostMapping(value = "/page", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "分页查询数据")
    public PageInfo<MetrologyPageVo> page(@RequestBody MetrologyPageDTO pageDto) {
        return metrologyService.getPageInfo(pageDto);
    }


    /**
     * 查询所有施工专业分包合同数据
     */
    @GetMapping(value = "/list", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "查询所有数据")
    public List<Metrology> list() {
        return metrologyService.list();
    }

    /**
     * 施工专业分包合同导出
     *
     * @param pageDto
     */
    @PostMapping(value = "/export", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "导出")
    public void export(@RequestBody @ApiParam(name = "pageDto") MetrologyPageDTO pageDto, HttpServletResponse response) {
        metrologyService.export(pageDto, response);
    }
}
