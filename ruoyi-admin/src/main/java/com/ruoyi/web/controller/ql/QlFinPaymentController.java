package com.ruoyi.web.controller.ql;

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
import com.ruoyi.ql.domain.bo.QlFinPaymentBo;
import com.ruoyi.ql.domain.vo.QlFinPaymentExport;
import com.ruoyi.ql.domain.vo.QlFinPaymentVo;
import com.ruoyi.ql.mapstruct.QlFinPaymentMapstruct;
import com.ruoyi.ql.service.IQlFinPaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.List;

/**
 * 供应商付款
 *
 * @author ruoyi
 * @date 2022-12-11
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/finPayment/finPayment")
public class QlFinPaymentController extends BaseController {

    private final IQlFinPaymentService iQlFinPaymentService;

    /**
     * 查询供应商付款列表
     */
    @SaCheckPermission("finPayment:finPayment:list")
    @GetMapping("/list")
    public TableDataInfo<QlFinPaymentVo> list(QlFinPaymentBo bo, PageQuery pageQuery) {
        return iQlFinPaymentService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出供应商付款列表
     */
    @SaCheckPermission("finPayment:finPayment:export")
    @Log(title = "供应商付款", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(QlFinPaymentBo bo, HttpServletResponse response) {
        List<QlFinPaymentVo> list = iQlFinPaymentService.queryList(bo);
        List<QlFinPaymentExport> qlFinPaymentExports = QlFinPaymentMapstruct.INSTANCES.toQlFinPaymentExports(list);
        ExcelUtil.exportExcel(qlFinPaymentExports, "供应商付款", QlFinPaymentExport.class, response);
    }

    /**
     * 获取供应商付款详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("finPayment:finPayment:query")
    @GetMapping("/{id}")
    public R<QlFinPaymentVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(iQlFinPaymentService.queryById(id));
    }

    /**
     * 新增供应商付款
     */
    @SaCheckPermission("finPayment:finPayment:add")
    @Log(title = "供应商付款", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody QlFinPaymentBo bo) {
        return toAjax(iQlFinPaymentService.insertPaymentByBo(bo) ? 1 : 0);
    }

    /**
     * 修改供应商付款
     */
    @SaCheckPermission("finPayment:finPayment:edit")
    @Log(title = "供应商付款", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody QlFinPaymentBo bo) {
        return toAjax(iQlFinPaymentService.updateByBo(bo) ? 1 : 0);
    }

    /**
     * 删除供应商付款
     *
     * @param ids 主键串
     */
    @SaCheckPermission("finPayment:finPayment:remove")
    @Log(title = "供应商付款", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(iQlFinPaymentService.deleteWithValidByIds(Arrays.asList(ids), true) ? 1 : 0);
    }
}
