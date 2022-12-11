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
import com.ruoyi.ql.domain.vo.QlWhReservoirVo;
import com.ruoyi.ql.domain.bo.QlWhReservoirBo;
import com.ruoyi.ql.service.IQlWhReservoirService;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 库区设置
 *
 * @author ruoyi
 * @date 2022-12-11
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/whReservoir/whReservoir")
public class QlWhReservoirController extends BaseController {

    private final IQlWhReservoirService iQlWhReservoirService;

    /**
     * 查询库区设置列表
     */
    @SaCheckPermission("whReservoir:whReservoir:list")
    @GetMapping("/list")
    public TableDataInfo<QlWhReservoirVo> list(QlWhReservoirBo bo, PageQuery pageQuery) {
        return iQlWhReservoirService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出库区设置列表
     */
    @SaCheckPermission("whReservoir:whReservoir:export")
    @Log(title = "库区设置", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(QlWhReservoirBo bo, HttpServletResponse response) {
        List<QlWhReservoirVo> list = iQlWhReservoirService.queryList(bo);
        ExcelUtil.exportExcel(list, "库区设置", QlWhReservoirVo.class, response);
    }

    /**
     * 获取库区设置详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("whReservoir:whReservoir:query")
    @GetMapping("/{id}")
    public R<QlWhReservoirVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(iQlWhReservoirService.queryById(id));
    }

    /**
     * 新增库区设置
     */
    @SaCheckPermission("whReservoir:whReservoir:add")
    @Log(title = "库区设置", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody QlWhReservoirBo bo) {
        return toAjax(iQlWhReservoirService.insertByBo(bo) ? 1 : 0);
    }

    /**
     * 修改库区设置
     */
    @SaCheckPermission("whReservoir:whReservoir:edit")
    @Log(title = "库区设置", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody QlWhReservoirBo bo) {
        return toAjax(iQlWhReservoirService.updateByBo(bo) ? 1 : 0);
    }

    /**
     * 删除库区设置
     *
     * @param ids 主键串
     */
    @SaCheckPermission("whReservoir:whReservoir:remove")
    @Log(title = "库区设置", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(iQlWhReservoirService.deleteWithValidByIds(Arrays.asList(ids), true) ? 1 : 0);
    }
}
