package com.ruoyi.web.controller.ql;

import java.util.List;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import lombok.RequiredArgsConstructor;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.*;
import cn.dev33.satoken.annotation.SaCheckPermission;
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
import com.ruoyi.ql.domain.vo.QlBasisSupplierVo;
import com.ruoyi.ql.domain.bo.QlBasisSupplierBo;
import com.ruoyi.ql.service.IQlBasisSupplierService;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 供应商管理
 *
 * @author ruoyi
 * @date 2022-12-11
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/basisSupplier/basisSupplier")
public class QlBasisSupplierController extends BaseController {

    private final IQlBasisSupplierService iQlBasisSupplierService;

    /**
     * 查询供应商管理列表
     */
    @SaCheckPermission("basisSupplier:basisSupplier:list")
    @GetMapping("/list")
    public TableDataInfo<QlBasisSupplierVo> list(QlBasisSupplierBo bo, PageQuery pageQuery) {
        return iQlBasisSupplierService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出供应商管理列表
     */
    @SaCheckPermission("basisSupplier:basisSupplier:export")
    @Log(title = "供应商管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(QlBasisSupplierBo bo, HttpServletResponse response) {
        List<QlBasisSupplierVo> list = iQlBasisSupplierService.queryList(bo);
        ExcelUtil.exportExcel(list, "供应商管理", QlBasisSupplierVo.class, response);
    }

    /**
     * 获取供应商管理详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("basisSupplier:basisSupplier:query")
    @GetMapping("/{id}")
    public R<QlBasisSupplierVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(iQlBasisSupplierService.queryById(id));
    }

    /**
     * 新增供应商管理
     */
    @SaCheckPermission("basisSupplier:basisSupplier:add")
    @Log(title = "供应商管理", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody QlBasisSupplierBo bo) {
        return toAjax(iQlBasisSupplierService.insertByBo(bo) ? 1 : 0);
    }

    /**
     * 修改供应商管理
     */
    @SaCheckPermission("basisSupplier:basisSupplier:edit")
    @Log(title = "供应商管理", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody QlBasisSupplierBo bo) {
        return toAjax(iQlBasisSupplierService.updateByBo(bo) ? 1 : 0);
    }

    /**
     * 删除供应商管理
     *
     * @param ids 主键串
     */
    @SaCheckPermission("basisSupplier:basisSupplier:remove")
    @Log(title = "供应商管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(iQlBasisSupplierService.deleteWithValidByIds(Arrays.asList(ids), true) ? 1 : 0);
    }
}
