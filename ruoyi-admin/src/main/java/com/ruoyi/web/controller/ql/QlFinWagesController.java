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
import com.ruoyi.ql.domain.vo.QlFinWagesVo;
import com.ruoyi.ql.domain.bo.QlFinWagesBo;
import com.ruoyi.ql.service.IQlFinWagesService;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 工资管理
 *
 * @author ruoyi
 * @date 2022-12-11
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/finWages/finWages")
public class QlFinWagesController extends BaseController {

    private final IQlFinWagesService iQlFinWagesService;

    /**
     * 查询工资管理列表
     */
    @SaCheckPermission("finWages:finWages:list")
    @GetMapping("/list")
    public TableDataInfo<QlFinWagesVo> list(QlFinWagesBo bo, PageQuery pageQuery) {
        return iQlFinWagesService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出工资管理列表
     */
    @SaCheckPermission("finWages:finWages:export")
    @Log(title = "工资管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(QlFinWagesBo bo, HttpServletResponse response) {
        List<QlFinWagesVo> list = iQlFinWagesService.queryList(bo);
        ExcelUtil.exportExcel(list, "工资管理", QlFinWagesVo.class, response);
    }

    /**
     * 获取工资管理详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("finWages:finWages:query")
    @GetMapping("/{id}")
    public R<QlFinWagesVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(iQlFinWagesService.queryById(id));
    }

    /**
     * 新增工资管理
     */
    @SaCheckPermission("finWages:finWages:add")
    @Log(title = "工资管理", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody QlFinWagesBo bo) {
        return toAjax(iQlFinWagesService.insertByBo(bo) ? 1 : 0);
    }

    /**
     * 修改工资管理
     */
    @SaCheckPermission("finWages:finWages:edit")
    @Log(title = "工资管理", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody QlFinWagesBo bo) {
        return toAjax(iQlFinWagesService.updateByBo(bo) ? 1 : 0);
    }

    /**
     * 删除工资管理
     *
     * @param ids 主键串
     */
    @SaCheckPermission("finWages:finWages:remove")
    @Log(title = "工资管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(iQlFinWagesService.deleteWithValidByIds(Arrays.asList(ids), true) ? 1 : 0);
    }
}
