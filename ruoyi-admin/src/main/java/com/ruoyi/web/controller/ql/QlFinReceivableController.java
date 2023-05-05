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
import com.ruoyi.ql.domain.vo.QlFinReceivableVo;
import com.ruoyi.ql.domain.bo.QlFinReceivableBo;
import com.ruoyi.ql.service.IQlFinReceivableService;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 收款记录
 *
 * @author ruoyi
 * @date 2023-05-05
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/ql/finReceivable")
public class QlFinReceivableController extends BaseController {

    private final IQlFinReceivableService iQlFinReceivableService;

/**
 * 查询收款记录列表
 */
@SaCheckPermission("ql:finReceivable:list")
@GetMapping("/list")
    public TableDataInfo<QlFinReceivableVo> list(QlFinReceivableBo bo, PageQuery pageQuery) {
        return iQlFinReceivableService.queryPageList(bo, pageQuery);
    }

    /**
     * 分页查询收款记录列表
     */
    @SaCheckPermission("ql:finReceivable:list")
    @GetMapping("/page")

    public TableDataInfo<QlFinReceivableVo> page(QlFinReceivableBo bo, PageQuery pageQuery) {
        return iQlFinReceivableService.queryPageList(bo, pageQuery);
    }


    /**
     * 导出收款记录列表
     */
    @SaCheckPermission("ql:finReceivable:export")
    @Log(title = "收款记录" , businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(QlFinReceivableBo bo, HttpServletResponse response) {
        List<QlFinReceivableVo> list = iQlFinReceivableService.queryList(bo);
        ExcelUtil.exportExcel(list, "收款记录" , QlFinReceivableVo.class, response);
    }

    /**
     * 获取收款记录详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("ql:finReceivable:query")
    @GetMapping("/{id}")
    public R<QlFinReceivableVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(iQlFinReceivableService.queryById(id));
    }

    /**
     * 新增收款记录
     */
    @SaCheckPermission("ql:finReceivable:add")
    @Log(title = "收款记录" , businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody QlFinReceivableBo bo) {
        return toAjax(iQlFinReceivableService.insertByBo(bo) ? 1 : 0);
    }

    /**
     * 修改收款记录
     */
    @SaCheckPermission("ql:finReceivable:edit")
    @Log(title = "收款记录" , businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody QlFinReceivableBo bo) {
        return toAjax(iQlFinReceivableService.updateByBo(bo) ? 1 : 0);
    }

    /**
     * 删除收款记录
     *
     * @param ids 主键串
     */
    @SaCheckPermission("ql:finReceivable:remove")
    @Log(title = "收款记录" , businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(iQlFinReceivableService.deleteWithValidByIds(Arrays.asList(ids), true) ? 1 : 0);
    }
}
