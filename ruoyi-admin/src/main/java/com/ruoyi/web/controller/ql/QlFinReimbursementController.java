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
import com.ruoyi.ql.domain.vo.QlFinReimbursementVo;
import com.ruoyi.ql.domain.bo.QlFinReimbursementBo;
import com.ruoyi.ql.service.IQlFinReimbursementService;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 费用报销
 *
 * @author ruoyi
 * @date 2022-12-11
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/finReimbursement/finReimbursement")
public class QlFinReimbursementController extends BaseController {

    private final IQlFinReimbursementService iQlFinReimbursementService;

    /**
     * 查询费用报销列表
     */
    @SaCheckPermission("finReimbursement:finReimbursement:list")
    @GetMapping("/list")
    public TableDataInfo<QlFinReimbursementVo> list(QlFinReimbursementBo bo, PageQuery pageQuery) {
        return iQlFinReimbursementService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出费用报销列表
     */
    @SaCheckPermission("finReimbursement:finReimbursement:export")
    @Log(title = "费用报销", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(QlFinReimbursementBo bo, HttpServletResponse response) {
        List<QlFinReimbursementVo> list = iQlFinReimbursementService.queryList(bo);
        ExcelUtil.exportExcel(list, "费用报销", QlFinReimbursementVo.class, response);
    }

    /**
     * 获取费用报销详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("finReimbursement:finReimbursement:query")
    @GetMapping("/{id}")
    public R<QlFinReimbursementVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(iQlFinReimbursementService.queryById(id));
    }

    /**
     * 新增费用报销
     */
    @SaCheckPermission("finReimbursement:finReimbursement:add")
    @Log(title = "费用报销", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody QlFinReimbursementBo bo) {
        return toAjax(iQlFinReimbursementService.insertByBo(bo) ? 1 : 0);
    }

    /**
     * 修改费用报销
     */
    @SaCheckPermission("finReimbursement:finReimbursement:edit")
    @Log(title = "费用报销", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody QlFinReimbursementBo bo) {
        return toAjax(iQlFinReimbursementService.updateByBo(bo) ? 1 : 0);
    }

    /**
     * 删除费用报销
     *
     * @param ids 主键串
     */
    @SaCheckPermission("finReimbursement:finReimbursement:remove")
    @Log(title = "费用报销", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(iQlFinReimbursementService.deleteWithValidByIds(Arrays.asList(ids), true) ? 1 : 0);
    }
}
