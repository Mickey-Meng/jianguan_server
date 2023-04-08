package com.ruoyi.web.controller.map;

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
import com.ruoyi.map.domain.vo.MapPlanVo;
import com.ruoyi.map.domain.bo.MapPlanBo;
import com.ruoyi.map.service.IMapPlanService;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 地图方案管理
 *
 * @author ruoyi
 * @date 2023-04-08
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/system/plan")
public class MapPlanController extends BaseController {

    private final IMapPlanService iMapPlanService;

/**
 * 查询地图方案管理列表
 */
@SaCheckPermission("map:mapPlan:list")
@GetMapping("/list")
    public TableDataInfo<MapPlanVo> list(MapPlanBo bo, PageQuery pageQuery) {
        return iMapPlanService.queryPageList(bo, pageQuery);
    }

    /**
     * 分页查询地图方案管理列表
     */
    @SaCheckPermission("map:mapPlan:list")
    @GetMapping("/page")

    public TableDataInfo<MapPlanVo> page(MapPlanBo bo, PageQuery pageQuery) {
        return iMapPlanService.queryPageList(bo, pageQuery);
    }


    /**
     * 导出地图方案管理列表
     */
    @SaCheckPermission("map:mapPlan:export")
    @Log(title = "地图方案管理" , businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(MapPlanBo bo, HttpServletResponse response) {
        List<MapPlanVo> list = iMapPlanService.queryList(bo);
        ExcelUtil.exportExcel(list, "地图方案管理" , MapPlanVo.class, response);
    }

    /**
     * 获取地图方案管理详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("map:mapPlan:query")
    @GetMapping("/{id}")
    public R<MapPlanVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(iMapPlanService.queryById(id));
    }

    /**
     * 新增地图方案管理
     */
    @SaCheckPermission("map:mapPlan:add")
    @Log(title = "地图方案管理" , businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody MapPlanBo bo) {
        return toAjax(iMapPlanService.insertByBo(bo) ? 1 : 0);
    }

    /**
     * 修改地图方案管理
     */
    @SaCheckPermission("map:mapPlan:edit")
    @Log(title = "地图方案管理" , businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody MapPlanBo bo) {
        return toAjax(iMapPlanService.updateByBo(bo) ? 1 : 0);
    }

    /**
     * 删除地图方案管理
     *
     * @param ids 主键串
     */
    @SaCheckPermission("map:mapPlan:remove")
    @Log(title = "地图方案管理" , businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(iMapPlanService.deleteWithValidByIds(Arrays.asList(ids), true) ? 1 : 0);
    }
}