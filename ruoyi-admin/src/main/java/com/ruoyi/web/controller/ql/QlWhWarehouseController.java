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
import com.ruoyi.ql.domain.vo.QlWhWarehouseVo;
import com.ruoyi.ql.domain.bo.QlWhWarehouseBo;
import com.ruoyi.ql.service.IQlWhWarehouseService;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 仓库设置
 *
 * @author ruoyi
 * @date 2022-12-11
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/whWarehouse/whWarehouse")
public class QlWhWarehouseController extends BaseController {

    private final IQlWhWarehouseService iQlWhWarehouseService;

    /**
     * 查询仓库设置列表
     */
    @SaCheckPermission("whWarehouse:whWarehouse:list")
    @GetMapping("/list")
    public TableDataInfo<QlWhWarehouseVo> list(QlWhWarehouseBo bo, PageQuery pageQuery) {
        return iQlWhWarehouseService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出仓库设置列表
     */
    @SaCheckPermission("whWarehouse:whWarehouse:export")
    @Log(title = "仓库设置", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(QlWhWarehouseBo bo, HttpServletResponse response) {
        List<QlWhWarehouseVo> list = iQlWhWarehouseService.queryList(bo);
        ExcelUtil.exportExcel(list, "仓库设置", QlWhWarehouseVo.class, response);
    }

    /**
     * 获取仓库设置详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("whWarehouse:whWarehouse:query")
    @GetMapping("/{id}")
    public R<QlWhWarehouseVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(iQlWhWarehouseService.queryById(id));
    }

    /**
     * 新增仓库设置
     */
    @SaCheckPermission("whWarehouse:whWarehouse:add")
    @Log(title = "仓库设置", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody QlWhWarehouseBo bo) {
        return toAjax(iQlWhWarehouseService.insertByBo(bo) ? 1 : 0);
    }

    /**
     * 修改仓库设置
     */
    @SaCheckPermission("whWarehouse:whWarehouse:edit")
    @Log(title = "仓库设置", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody QlWhWarehouseBo bo) {
        return toAjax(iQlWhWarehouseService.updateByBo(bo) ? 1 : 0);
    }

    /**
     * 删除仓库设置
     *
     * @param ids 主键串
     */
    @SaCheckPermission("whWarehouse:whWarehouse:remove")
    @Log(title = "仓库设置", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(iQlWhWarehouseService.deleteWithValidByIds(Arrays.asList(ids), true) ? 1 : 0);
    }
}
