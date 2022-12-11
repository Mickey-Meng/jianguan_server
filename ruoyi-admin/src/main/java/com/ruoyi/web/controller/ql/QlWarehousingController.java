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
import com.ruoyi.ql.domain.vo.QlWarehousingVo;
import com.ruoyi.ql.domain.bo.QlWarehousingBo;
import com.ruoyi.ql.service.IQlWarehousingService;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 入库管理
 *
 * @author ruoyi
 * @date 2022-12-11
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/warehousing/warehousing")
public class QlWarehousingController extends BaseController {

    private final IQlWarehousingService iQlWarehousingService;

    /**
     * 查询入库管理列表
     */
    @SaCheckPermission("warehousing:warehousing:list")
    @GetMapping("/list")
    public TableDataInfo<QlWarehousingVo> list(QlWarehousingBo bo, PageQuery pageQuery) {
        return iQlWarehousingService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出入库管理列表
     */
    @SaCheckPermission("warehousing:warehousing:export")
    @Log(title = "入库管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(QlWarehousingBo bo, HttpServletResponse response) {
        List<QlWarehousingVo> list = iQlWarehousingService.queryList(bo);
        ExcelUtil.exportExcel(list, "入库管理", QlWarehousingVo.class, response);
    }

    /**
     * 获取入库管理详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("warehousing:warehousing:query")
    @GetMapping("/{id}")
    public R<QlWarehousingVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(iQlWarehousingService.queryById(id));
    }

    /**
     * 新增入库管理
     */
    @SaCheckPermission("warehousing:warehousing:add")
    @Log(title = "入库管理", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody QlWarehousingBo bo) {
        return toAjax(iQlWarehousingService.insertByBo(bo) ? 1 : 0);
    }

    /**
     * 修改入库管理
     */
    @SaCheckPermission("warehousing:warehousing:edit")
    @Log(title = "入库管理", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody QlWarehousingBo bo) {
        return toAjax(iQlWarehousingService.updateByBo(bo) ? 1 : 0);
    }

    /**
     * 删除入库管理
     *
     * @param ids 主键串
     */
    @SaCheckPermission("warehousing:warehousing:remove")
    @Log(title = "入库管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(iQlWarehousingService.deleteWithValidByIds(Arrays.asList(ids), true) ? 1 : 0);
    }
}
