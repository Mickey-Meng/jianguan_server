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
import com.ruoyi.ql.domain.vo.QlBasisCustomerVo;
import com.ruoyi.ql.domain.bo.QlBasisCustomerBo;
import com.ruoyi.ql.service.IQlBasisCustomerService;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 客户资料
 *
 * @author ruoyi
 * @date 2022-12-11
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/basisCustomer/basisCustomer")
public class QlBasisCustomerController extends BaseController {

    private final IQlBasisCustomerService iQlBasisCustomerService;

    /**
     * 查询客户资料列表
     */
    @SaCheckPermission("basisCustomer:basisCustomer:list")
    @GetMapping("/list")
    public TableDataInfo<QlBasisCustomerVo> list(QlBasisCustomerBo bo, PageQuery pageQuery) {
        return iQlBasisCustomerService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出客户资料列表
     */
    @SaCheckPermission("basisCustomer:basisCustomer:export")
    @Log(title = "客户资料", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(QlBasisCustomerBo bo, HttpServletResponse response) {
        List<QlBasisCustomerVo> list = iQlBasisCustomerService.queryList(bo);
        ExcelUtil.exportExcel(list, "客户资料", QlBasisCustomerVo.class, response);
    }

    /**
     * 获取客户资料详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("basisCustomer:basisCustomer:query")
    @GetMapping("/{id}")
    public R<QlBasisCustomerVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(iQlBasisCustomerService.queryById(id));
    }

    /**
     * 新增客户资料
     */
    @SaCheckPermission("basisCustomer:basisCustomer:add")
    @Log(title = "客户资料", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody QlBasisCustomerBo bo) {
        return toAjax(iQlBasisCustomerService.insertByBo(bo) ? 1 : 0);
    }

    /**
     * 修改客户资料
     */
    @SaCheckPermission("basisCustomer:basisCustomer:edit")
    @Log(title = "客户资料", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody QlBasisCustomerBo bo) {
        return toAjax(iQlBasisCustomerService.updateByBo(bo) ? 1 : 0);
    }

    /**
     * 删除客户资料
     *
     * @param ids 主键串
     */
    @SaCheckPermission("basisCustomer:basisCustomer:remove")
    @Log(title = "客户资料", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(iQlBasisCustomerService.deleteWithValidByIds(Arrays.asList(ids), true) ? 1 : 0);
    }
}
