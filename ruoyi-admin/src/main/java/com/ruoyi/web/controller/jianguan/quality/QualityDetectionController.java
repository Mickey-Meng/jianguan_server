package com.ruoyi.web.controller.jianguan.quality;

import com.ruoyi.common.core.domain.vo.EnumsVo;
import com.ruoyi.jianguan.quality.domain.dto.QualityDetectionPageDTO;
import com.ruoyi.jianguan.quality.domain.dto.QualityDetectionSaveDTO;
import com.ruoyi.jianguan.quality.service.QualityDetectionService;
import com.ruoyi.jianguan.quality.domain.vo.QualityDetectionDetailVo;
import com.ruoyi.jianguan.quality.domain.vo.QualityDetectionPageVo;
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
import java.util.List;

/**
 * 质量检测
 *
 * @author qiaoxulin
 * @date 2022-05-11
 */
@Api(value = "质量检测", tags = {"质量检测"})
@RestController
@RequestMapping("/web/api/v1/qualityDetection")
public class QualityDetectionController {


    @Autowired
    private QualityDetectionService qualityDetectionService;
    @Autowired
    private FlowApiService flowApiService;

    /**
     * 新增或者更新质量检测数据
     *
     * @param qualityDetectionDto
     */
    @PostMapping(value = "/addOrUpdate", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "新增或者更新质量检测数据")
    public ResponseBase addOrUpdate(@RequestBody @ApiParam(name = "qualityDetectionDto") QualityDetectionSaveDTO qualityDetectionDto) {
        return qualityDetectionService.addOrUpdate(qualityDetectionDto);
    }


    /**
     * 通过id删除一条质量检测 数据
     *
     * @param id
     */
    @GetMapping(value = "/id", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "通过id删除一条质量检测数据")
    @Transactional(rollbackFor = Exception.class)
    public boolean removeById(@ApiParam(name = "id") Long id) {
        flowApiService.deleteProcessInstanceByBusinessKey(String.valueOf(id));
        return qualityDetectionService.removeById(id);
    }


    /**
     * 通过id获取一条质量检测数据
     *
     * @param id
     */
    @GetMapping(value = "/detail/id", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "通过id获取一条质量检测数据")
    public QualityDetectionDetailVo getById(@ApiParam(name = "id") Long id) {
        return qualityDetectionService.getInfoById(id);
    }


    /**
     * 质量检测分页查询
     *
     * @param pageDto
     */
    @PostMapping(value = "/page", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "质量检测分页查询")
    public PageInfo<QualityDetectionPageVo> page(@RequestBody @ApiParam(name = "pageDto") QualityDetectionPageDTO pageDto) {
        return qualityDetectionService.getPageInfo(pageDto);
    }

    /**
     * 获取材料枚举
     */
    @GetMapping(value = "/material/enums", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "获取材料枚举")
    public List<EnumsVo> getMaterialEnum() {
        return qualityDetectionService.getMaterialEnum();
    }

    /**
     * 质量检测导出
     *
     * @param pageDto
     */
    @PostMapping(value = "/export", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "质量检测导出")
    public void export(@RequestBody @ApiParam(name = "pageDto") QualityDetectionPageDTO pageDto, HttpServletResponse response) {
        qualityDetectionService.export(pageDto, response);
    }

}
