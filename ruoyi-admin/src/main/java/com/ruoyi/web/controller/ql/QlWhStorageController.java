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
import com.ruoyi.ql.domain.vo.QlWhStorageVo;
import com.ruoyi.ql.domain.bo.QlWhStorageBo;
import com.ruoyi.ql.service.IQlWhStorageService;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 库位(储位)设置
 *
 * @author ruoyi
 * @date 2022-12-11
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/whStorage/whStorage")
public class QlWhStorageController extends BaseController {

    private final IQlWhStorageService iQlWhStorageService;

    /**
     * 查询库位(储位)设置列表
     */
    @SaCheckPermission("whStorage:whStorage:list")
    @GetMapping("/list")
    public TableDataInfo<QlWhStorageVo> list(QlWhStorageBo bo, PageQuery pageQuery) {
        return iQlWhStorageService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出库位(储位)设置列表
     */
    @SaCheckPermission("whStorage:whStorage:export")
    @Log(title = "库位(储位)设置", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(QlWhStorageBo bo, HttpServletResponse response) {
        List<QlWhStorageVo> list = iQlWhStorageService.queryList(bo);
        ExcelUtil.exportExcel(list, "库位(储位)设置", QlWhStorageVo.class, response);
    }

    /**
     * 获取库位(储位)设置详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("whStorage:whStorage:query")
    @GetMapping("/{id}")
    public R<QlWhStorageVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(iQlWhStorageService.queryById(id));
    }

    /**
     * 新增库位(储位)设置
     */
    @SaCheckPermission("whStorage:whStorage:add")
    @Log(title = "库位(储位)设置", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody QlWhStorageBo bo) {
        return toAjax(iQlWhStorageService.insertByBo(bo) ? 1 : 0);
    }

    /**
     * 修改库位(储位)设置
     */
    @SaCheckPermission("whStorage:whStorage:edit")
    @Log(title = "库位(储位)设置", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody QlWhStorageBo bo) {
        return toAjax(iQlWhStorageService.updateByBo(bo) ? 1 : 0);
    }

    /**
     * 删除库位(储位)设置
     *
     * @param ids 主键串
     */
    @SaCheckPermission("whStorage:whStorage:remove")
    @Log(title = "库位(储位)设置", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(iQlWhStorageService.deleteWithValidByIds(Arrays.asList(ids), true) ? 1 : 0);
    }
}
