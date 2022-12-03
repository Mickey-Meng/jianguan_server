package com.ruoyi.web.controller.project;


import cn.dev33.satoken.annotation.SaCheckPermission;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.annotation.RepeatSubmit;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.PageQuery;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.project.measurementDocumentsDetail.domain.bo.MeaMeasurementDocumentsDetailBo;
import com.ruoyi.project.measurementDocumentsDetail.domain.vo.MeaMeasurementDocumentsDetailVo;
import com.ruoyi.project.measurementDocumentsDetail.service.IMeaMeasurementDocumentsDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.List;

/**
 * 台账变更/工程变更 明细
 *
 * @author ruoyi
 * @date 2022-12-03
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/measurementDocumentsDetail/measurementDocumentsDetail")
public class MeaMeasurementDocumentsDetailController extends BaseController {

    private final IMeaMeasurementDocumentsDetailService iMeaMeasurementDocumentsDetailService;

    /**
     * 查询台账变更/工程变更 明细列表
     */
    @SaCheckPermission("measurementDocumentsDetail:measurementDocumentsDetail:list")
    @GetMapping("/list")
    public TableDataInfo<MeaMeasurementDocumentsDetailVo> list(MeaMeasurementDocumentsDetailBo bo, PageQuery pageQuery) {
        return iMeaMeasurementDocumentsDetailService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出台账变更/工程变更 明细列表
     */
    @SaCheckPermission("measurementDocumentsDetail:measurementDocumentsDetail:export")
    @Log(title = "台账变更/工程变更 明细", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(MeaMeasurementDocumentsDetailBo bo, HttpServletResponse response) {
        List<MeaMeasurementDocumentsDetailVo> list = iMeaMeasurementDocumentsDetailService.queryList(bo);
        ExcelUtil.exportExcel(list, "台账变更/工程变更 明细", MeaMeasurementDocumentsDetailVo.class, response);
    }

    /**
     * 获取台账变更/工程变更 明细详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("measurementDocumentsDetail:measurementDocumentsDetail:query")
    @GetMapping("/{id}")
    public R<MeaMeasurementDocumentsDetailVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable String id) {
        return R.ok(iMeaMeasurementDocumentsDetailService.queryById(id));
    }

    /**
     * 新增台账变更/工程变更 明细
     */
    @SaCheckPermission("measurementDocumentsDetail:measurementDocumentsDetail:add")
    @Log(title = "台账变更/工程变更 明细", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody MeaMeasurementDocumentsDetailBo bo) {
        return toAjax(iMeaMeasurementDocumentsDetailService.insertByBo(bo) ? 1 : 0);
    }

    /**
     * 修改台账变更/工程变更 明细
     */
    @SaCheckPermission("measurementDocumentsDetail:measurementDocumentsDetail:edit")
    @Log(title = "台账变更/工程变更 明细", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody MeaMeasurementDocumentsDetailBo bo) {
        return toAjax(iMeaMeasurementDocumentsDetailService.updateByBo(bo) ? 1 : 0);
    }

    /**
     * 删除台账变更/工程变更 明细
     *
     * @param ids 主键串
     */
    @SaCheckPermission("measurementDocumentsDetail:measurementDocumentsDetail:remove")
    @Log(title = "台账变更/工程变更 明细", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable String[] ids) {
        return toAjax(iMeaMeasurementDocumentsDetailService.deleteWithValidByIds(Arrays.asList(ids), true) ? 1 : 0);
    }
}
