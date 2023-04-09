package com.ruoyi.web.controller.map;

import java.util.List;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import cn.hutool.core.convert.Convert;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.generator.domain.GenTable;
import com.ruoyi.map.domain.MapServerConfig;
import com.ruoyi.map.domain.bo.MapServerConfigBo;
import com.ruoyi.map.domain.vo.MapServerConfigVo;
import com.ruoyi.map.service.IMapServerConfigService;
import com.ruoyi.system.service.ISysUserService;
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
 * 地图服务注册
 *
 * @author ruoyi
 * @date 2023-04-08
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/map/mapServerConfig")
public class MapServerConfigController extends BaseController {

    private final IMapServerConfigService mapServerConfigService;

/**
 * 查询地图服务注册列表
 */
@SaCheckPermission("map:mapServerConfig:list")
@GetMapping("/list")
    public TableDataInfo<MapServerConfigVo> list(MapServerConfigBo bo, PageQuery pageQuery) {
        return mapServerConfigService.queryPageList(bo, pageQuery);
    }

    /**
     * 分页查询地图服务注册列表
     */
    @SaCheckPermission("map:mapServerConfig:list")
    @GetMapping("/page")

    public TableDataInfo<MapServerConfigVo> page(MapServerConfigBo bo, PageQuery pageQuery) {
        return mapServerConfigService.queryPageList(bo, pageQuery);
    }


    /**
     * 导出地图服务注册列表
     */
    @SaCheckPermission("map:mapServerConfig:export")
    @Log(title = "地图服务注册" , businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(MapServerConfigBo bo, HttpServletResponse response) {
        List<MapServerConfigVo> list = mapServerConfigService.queryList(bo);
        ExcelUtil.exportExcel(list, "地图服务注册" , MapServerConfigVo.class, response);
    }

    /**
     * 获取地图服务注册详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("map:mapServerConfig:query")
    @GetMapping("/{id}")
    public R<MapServerConfigVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(mapServerConfigService.queryById(id));
    }

    /**
     * 新增地图服务注册
     */
    @SaCheckPermission("map:mapServerConfig:add")
    @Log(title = "地图服务注册" , businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping("/add")
    public R<Void> add(@Validated(AddGroup.class) @RequestBody MapServerConfigBo bo) {
        return toAjax(mapServerConfigService.insertByBo(bo) ? 1 : 0);
    }

    /**
     * 修改地图服务注册
     */
    @SaCheckPermission("map:mapServerConfig:edit")
    @Log(title = "地图服务注册" , businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping("/update")
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody MapServerConfigBo bo) {
        return toAjax(mapServerConfigService.updateByBo(bo) ? 1 : 0);
    }

    /**
     * 状态修改
     */
    @SaCheckPermission("map:mapServerConfig:edit")
    @Log(title = "用户管理", businessType = BusinessType.UPDATE)
    @PutMapping("/changeStatus")
    public R<Void> changeStatus(@RequestBody MapServerConfigBo bo) {
        return toAjax(mapServerConfigService.updateServerStatus(bo));
    }

    /**
     * 删除地图服务注册
     *
     * @param ids 主键串
     */
    @SaCheckPermission("map:mapServerConfig:remove")
    @Log(title = "地图服务注册" , businessType = BusinessType.DELETE)
    @DeleteMapping("/delete/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(mapServerConfigService.deleteWithValidByIds(Arrays.asList(ids), true) ? 1 : 0);
    }

    /**
     * 导入地图服务（保存）
     *
     * @param mapServerIds ID串
     */
    @SaCheckPermission("map:mapServerConfig:import")
    @Log(title = "导入地图服务", businessType = BusinessType.IMPORT)
    @PostMapping("/importMapConfig")
    public R<Void> importMapConfigSave(String mapServerIds, Long mapPlanId) {
        String[] mapServerIdArray = Convert.toStrArray(mapServerIds);
        mapServerConfigService.doImportMapConfigSave(Arrays.asList(mapServerIdArray), mapPlanId);
        return R.ok();
    }
}
