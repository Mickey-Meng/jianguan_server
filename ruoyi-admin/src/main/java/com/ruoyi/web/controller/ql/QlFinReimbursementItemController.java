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
import com.ruoyi.ql.domain.vo.QlFinReimbursementItemVo;
import com.ruoyi.ql.domain.bo.QlFinReimbursementItemBo;
import com.ruoyi.ql.service.IQlFinReimbursementItemService;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 费用报销明细
 *
 * @author ruoyi
 * @date 2022-12-11
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/finReimbursementItem/finReimbursementItem")
public class QlFinReimbursementItemController extends BaseController {

    private final IQlFinReimbursementItemService iQlFinReimbursementItemService;

    /**
     * 查询费用报销明细列表
     */
    @SaCheckPermission("finReimbursementItem:finReimbursementItem:list")
    @GetMapping("/list")
    public TableDataInfo<QlFinReimbursementItemVo> list(QlFinReimbursementItemBo bo, PageQuery pageQuery) {
        return iQlFinReimbursementItemService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出费用报销明细列表
     */
    @SaCheckPermission("finReimbursementItem:finReimbursementItem:export")
    @Log(title = "费用报销明细", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(QlFinReimbursementItemBo bo, HttpServletResponse response) {
        List<QlFinReimbursementItemVo> list = iQlFinReimbursementItemService.queryList(bo);
        ExcelUtil.exportExcel(list, "费用报销明细", QlFinReimbursementItemVo.class, response);
    }

    /**
     * 获取费用报销明细详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("finReimbursementItem:finReimbursementItem:query")
    @GetMapping("/{id}")
    public R<QlFinReimbursementItemVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(iQlFinReimbursementItemService.queryById(id));
    }

    /**
     * 新增费用报销明细
     */
    @SaCheckPermission("finReimbursementItem:finReimbursementItem:add")
    @Log(title = "费用报销明细", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody QlFinReimbursementItemBo bo) {
        return toAjax(iQlFinReimbursementItemService.insertByBo(bo) ? 1 : 0);
    }

    /**
     * 修改费用报销明细
     */
    @SaCheckPermission("finReimbursementItem:finReimbursementItem:edit")
    @Log(title = "费用报销明细", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody QlFinReimbursementItemBo bo) {
        return toAjax(iQlFinReimbursementItemService.updateByBo(bo) ? 1 : 0);
    }

    /**
     * 删除费用报销明细
     *
     * @param ids 主键串
     */
    @SaCheckPermission("finReimbursementItem:finReimbursementItem:remove")
    @Log(title = "费用报销明细", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(iQlFinReimbursementItemService.deleteWithValidByIds(Arrays.asList(ids), true) ? 1 : 0);
    }
}
