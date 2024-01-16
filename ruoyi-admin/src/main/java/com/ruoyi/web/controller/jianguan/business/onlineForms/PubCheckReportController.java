package com.ruoyi.web.controller.jianguan.business.onlineForms;

import java.io.IOException;
import java.util.List;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import com.ruoyi.common.core.domain.object.ResponseBase;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.*;

import cn.dev33.satoken.annotation.SaCheckPermission;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;
import com.ruoyi.common.annotation.RepeatSubmit;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.PageQuery;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import com.ruoyi.common.core.validate.QueryGroup;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.jianguan.business.onlineForms.domain.vo.PubCheckReportVo;
import com.ruoyi.jianguan.business.onlineForms.domain.bo.PubCheckReportBo;
import com.ruoyi.jianguan.business.onlineForms.service.IPubCheckReportService;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 评定填报关联信息
 *
 * @author mickey
 * @date 2024-01-16
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/onlineForms/checkReport")
public class PubCheckReportController extends BaseController {

    private final IPubCheckReportService iPubCheckReportService;

    /**
     * 查询评定填报关联信息列表
     */
    @GetMapping("/list")
    public TableDataInfo<PubCheckReportVo> list(PubCheckReportBo bo, PageQuery pageQuery) {
        return iPubCheckReportService.queryPageList(bo, pageQuery);
    }

    /**
     * 分页查询评定填报关联信息列表
     */
    @GetMapping("/page")

    public TableDataInfo<PubCheckReportVo> page(PubCheckReportBo bo, PageQuery pageQuery) {
        return iPubCheckReportService.queryPageList(bo, pageQuery);
    }


    /**
     * 导出评定填报关联信息列表
     */
    @Log(title = "评定填报关联信息" , businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(PubCheckReportBo bo, HttpServletResponse response) {
        List<PubCheckReportVo> list = iPubCheckReportService.queryList(bo);
        ExcelUtil.exportExcel(list, "评定填报关联信息" , PubCheckReportVo.class, response);
    }

    /**
     * 获取评定填报关联信息详细信息
     *
     * @param id 主键
     */
    @GetMapping("/{id}")
    public R<PubCheckReportVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(iPubCheckReportService.queryById(id));
    }

    /**
     * 新增评定填报关联信息
     */
    @Log(title = "评定填报关联信息" , businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody PubCheckReportBo bo) {
        return toAjax(iPubCheckReportService.insertByBo(bo) ? 1 : 0);
    }

    /**
     * 修改评定填报关联信息
     */
    @Log(title = "评定填报关联信息" , businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody PubCheckReportBo bo) {
        return toAjax(iPubCheckReportService.updateByBo(bo) ? 1 : 0);
    }

    /**
     * 删除评定填报关联信息
     *
     * @param ids 主键串
     */
    @Log(title = "评定填报关联信息" , businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(iPubCheckReportService.deleteWithValidByIds(Arrays.asList(ids), true) ? 1 : 0);
    }

    @GetMapping("/loadOnlineCheckReport")
    public R<PubCheckReportVo> loadOnlineCheckReport(@RequestParam(value ="componentId",required = false) Integer componentId) throws IOException {
        return R.ok(iPubCheckReportService.loadOnlineCheckReport(componentId));
    }
}
