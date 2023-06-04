package com.ruoyi.web.controller.jianguan.manage.produce;

import java.util.List;
import java.util.Arrays;

import com.ruoyi.jianguan.manage.produce.domain.bo.PubComponentTypeBo;
import com.ruoyi.jianguan.manage.produce.domain.vo.PubComponentTypeVo;
import com.ruoyi.jianguan.manage.produce.service.IPubComponentTypeService;
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
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 构建类型
 *
 * @author ruoyi
 * @date 2023-06-03
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/system/jg/componentType")
public class PubComponentTypeController extends BaseController {

    private final IPubComponentTypeService iPubComponentTypeService;

/**
 * 查询构建类型列表
 */
@SaCheckPermission("system:componentType:list")
@GetMapping("/list")
    public TableDataInfo<PubComponentTypeVo> list(PubComponentTypeBo bo, PageQuery pageQuery) {
        return iPubComponentTypeService.queryPageList(bo, pageQuery);
    }

    /**
     * 分页查询构建类型列表
     */
    @SaCheckPermission("system:componentType:list")
    @GetMapping("/page")

    public TableDataInfo<PubComponentTypeVo> page(PubComponentTypeBo bo, PageQuery pageQuery) {
        return iPubComponentTypeService.queryPageList(bo, pageQuery);
    }


    /**
     * 导出构建类型列表
     */
    @SaCheckPermission("system:componentType:export")
    @Log(title = "构建类型" , businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(PubComponentTypeBo bo, HttpServletResponse response) {
        List<PubComponentTypeVo> list = iPubComponentTypeService.queryList(bo);
        ExcelUtil.exportExcel(list, "构建类型" , PubComponentTypeVo.class, response);
    }

    /**
     * 获取构建类型详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("system:componentType:query")
    @GetMapping("/{id}")
    public R<PubComponentTypeVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Integer id) {
        return R.ok(iPubComponentTypeService.queryById(id));
    }

    /**
     * 新增构建类型
     */
    @SaCheckPermission("system:componentType:add")
    @Log(title = "构建类型" , businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody PubComponentTypeBo bo) {
        return toAjax(iPubComponentTypeService.insertByBo(bo) ? 1 : 0);
    }

    /**
     * 修改构建类型
     */
    @SaCheckPermission("system:componentType:edit")
    @Log(title = "构建类型" , businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody PubComponentTypeBo bo) {
        return toAjax(iPubComponentTypeService.updateByBo(bo) ? 1 : 0);
    }

    /**
     * 删除构建类型
     *
     * @param ids 主键串
     */
    @SaCheckPermission("system:componentType:remove")
    @Log(title = "构建类型" , businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Integer[] ids) {
        return toAjax(iPubComponentTypeService.deleteWithValidByIds(Arrays.asList(ids), true) ? 1 : 0);
    }
}
