package com.ruoyi.ql.controller;

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
import com.ruoyi.ql.domain.vo.QlBasisCustomerAccinfoVo;
import com.ruoyi.ql.domain.bo.QlBasisCustomerAccinfoBo;
import com.ruoyi.ql.service.IQlBasisCustomerAccinfoService;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 客户账户信息
 *
 * @author ruoyi
 * @date 2022-12-11
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/basisCustomerAccinfo/basisCustomerAccinfo")
public class QlBasisCustomerAccinfoController extends BaseController {

    private final IQlBasisCustomerAccinfoService iQlBasisCustomerAccinfoService;

    /**
     * 查询客户账户信息列表
     */
    @SaCheckPermission("basisCustomerAccinfo:basisCustomerAccinfo:list")
    @GetMapping("/list")
    public TableDataInfo<QlBasisCustomerAccinfoVo> list(QlBasisCustomerAccinfoBo bo, PageQuery pageQuery) {
        return iQlBasisCustomerAccinfoService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出客户账户信息列表
     */
    @SaCheckPermission("basisCustomerAccinfo:basisCustomerAccinfo:export")
    @Log(title = "客户账户信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(QlBasisCustomerAccinfoBo bo, HttpServletResponse response) {
        List<QlBasisCustomerAccinfoVo> list = iQlBasisCustomerAccinfoService.queryList(bo);
        ExcelUtil.exportExcel(list, "客户账户信息", QlBasisCustomerAccinfoVo.class, response);
    }

    /**
     * 获取客户账户信息详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("basisCustomerAccinfo:basisCustomerAccinfo:query")
    @GetMapping("/{id}")
    public R<QlBasisCustomerAccinfoVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(iQlBasisCustomerAccinfoService.queryById(id));
    }

    /**
     * 新增客户账户信息
     */
    @SaCheckPermission("basisCustomerAccinfo:basisCustomerAccinfo:add")
    @Log(title = "客户账户信息", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody QlBasisCustomerAccinfoBo bo) {
        return toAjax(iQlBasisCustomerAccinfoService.insertByBo(bo) ? 1 : 0);
    }

    /**
     * 修改客户账户信息
     */
    @SaCheckPermission("basisCustomerAccinfo:basisCustomerAccinfo:edit")
    @Log(title = "客户账户信息", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody QlBasisCustomerAccinfoBo bo) {
        return toAjax(iQlBasisCustomerAccinfoService.updateByBo(bo) ? 1 : 0);
    }

    /**
     * 删除客户账户信息
     *
     * @param ids 主键串
     */
    @SaCheckPermission("basisCustomerAccinfo:basisCustomerAccinfo:remove")
    @Log(title = "客户账户信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(iQlBasisCustomerAccinfoService.deleteWithValidByIds(Arrays.asList(ids), true) ? 1 : 0);
    }
}
