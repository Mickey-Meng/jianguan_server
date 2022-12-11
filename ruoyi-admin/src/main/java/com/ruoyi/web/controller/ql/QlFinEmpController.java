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
import com.ruoyi.ql.domain.vo.QlFinEmpVo;
import com.ruoyi.ql.domain.bo.QlFinEmpBo;
import com.ruoyi.ql.service.IQlFinEmpService;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 员工信息管理
 *
 * @author ruoyi
 * @date 2022-12-11
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/finEmp/finEmp")
public class QlFinEmpController extends BaseController {

    private final IQlFinEmpService iQlFinEmpService;

    /**
     * 查询员工信息管理列表
     */
    @SaCheckPermission("finEmp:finEmp:list")
    @GetMapping("/list")
    public TableDataInfo<QlFinEmpVo> list(QlFinEmpBo bo, PageQuery pageQuery) {
        return iQlFinEmpService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出员工信息管理列表
     */
    @SaCheckPermission("finEmp:finEmp:export")
    @Log(title = "员工信息管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(QlFinEmpBo bo, HttpServletResponse response) {
        List<QlFinEmpVo> list = iQlFinEmpService.queryList(bo);
        ExcelUtil.exportExcel(list, "员工信息管理", QlFinEmpVo.class, response);
    }

    /**
     * 获取员工信息管理详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("finEmp:finEmp:query")
    @GetMapping("/{id}")
    public R<QlFinEmpVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(iQlFinEmpService.queryById(id));
    }

    /**
     * 新增员工信息管理
     */
    @SaCheckPermission("finEmp:finEmp:add")
    @Log(title = "员工信息管理", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody QlFinEmpBo bo) {
        return toAjax(iQlFinEmpService.insertByBo(bo) ? 1 : 0);
    }

    /**
     * 修改员工信息管理
     */
    @SaCheckPermission("finEmp:finEmp:edit")
    @Log(title = "员工信息管理", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody QlFinEmpBo bo) {
        return toAjax(iQlFinEmpService.updateByBo(bo) ? 1 : 0);
    }

    /**
     * 删除员工信息管理
     *
     * @param ids 主键串
     */
    @SaCheckPermission("finEmp:finEmp:remove")
    @Log(title = "员工信息管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(iQlFinEmpService.deleteWithValidByIds(Arrays.asList(ids), true) ? 1 : 0);
    }
}
