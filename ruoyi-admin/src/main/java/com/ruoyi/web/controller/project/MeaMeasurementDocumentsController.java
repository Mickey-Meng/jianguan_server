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
import com.ruoyi.project.measurementDocuments.domain.bo.MeaMeasurementDocumentsBo;
import com.ruoyi.project.measurementDocuments.domain.vo.MeaMeasurementDocumentsVo;
import com.ruoyi.project.measurementDocuments.service.IMeaMeasurementDocumentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.List;

/**
 * 计量凭证，设计计量、变更计量共用一张凭证，明细分开。
 *
 * @author ruoyi
 * @date 2022-12-03
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/measurementDocuments/measurementDocuments")
public class MeaMeasurementDocumentsController extends BaseController {

    private final IMeaMeasurementDocumentsService iMeaMeasurementDocumentsService;

    /**
     * 查询计量凭证，设计计量、变更计量共用一张凭证，明细分开。列表
     */
    @SaCheckPermission("measurementDocuments:measurementDocuments:list")
    @GetMapping("/list")
    public TableDataInfo<MeaMeasurementDocumentsVo> list(MeaMeasurementDocumentsBo bo, PageQuery pageQuery) {
        return iMeaMeasurementDocumentsService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出计量凭证，设计计量、变更计量共用一张凭证，明细分开。列表
     */
    @SaCheckPermission("measurementDocuments:measurementDocuments:export")
    @Log(title = "计量凭证，设计计量、变更计量共用一张凭证，明细分开。", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(MeaMeasurementDocumentsBo bo, HttpServletResponse response) {
        List<MeaMeasurementDocumentsVo> list = iMeaMeasurementDocumentsService.queryList(bo);
        ExcelUtil.exportExcel(list, "计量凭证，设计计量、变更计量共用一张凭证，明细分开。", MeaMeasurementDocumentsVo.class, response);
    }

    /**
     * 获取计量凭证，设计计量、变更计量共用一张凭证，明细分开。详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("measurementDocuments:measurementDocuments:query")
    @GetMapping("/{id}")
    public R<MeaMeasurementDocumentsVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable String id) {
        return R.ok(iMeaMeasurementDocumentsService.queryById(id));
    }

    /**
     * 新增计量凭证，设计计量、变更计量共用一张凭证，明细分开。
     */
    @SaCheckPermission("measurementDocuments:measurementDocuments:add")
    @Log(title = "计量凭证，设计计量、变更计量共用一张凭证，明细分开。", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody MeaMeasurementDocumentsBo bo) {
        return toAjax(iMeaMeasurementDocumentsService.insertByBo(bo) ? 1 : 0);
    }

    /**
     * 修改计量凭证，设计计量、变更计量共用一张凭证，明细分开。
     */
    @SaCheckPermission("measurementDocuments:measurementDocuments:edit")
    @Log(title = "计量凭证，设计计量、变更计量共用一张凭证，明细分开。", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody MeaMeasurementDocumentsBo bo) {
        return toAjax(iMeaMeasurementDocumentsService.updateByBo(bo) ? 1 : 0);
    }

    /**
     * 删除计量凭证，设计计量、变更计量共用一张凭证，明细分开。
     *
     * @param ids 主键串
     */
    @SaCheckPermission("measurementDocuments:measurementDocuments:remove")
    @Log(title = "计量凭证，设计计量、变更计量共用一张凭证，明细分开。", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable String[] ids) {
        return toAjax(iMeaMeasurementDocumentsService.deleteWithValidByIds(Arrays.asList(ids), true) ? 1 : 0);
    }
}
