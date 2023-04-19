package com.ruoyi.web.controller.jianguan.manage.map;

import java.util.Arrays;

import com.ruoyi.common.core.domain.PageQuery;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.jianguan.manage.map.domain.vo.MapPlanServerVo;
import lombok.RequiredArgsConstructor;

import cn.dev33.satoken.annotation.SaCheckPermission;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;
import com.ruoyi.common.annotation.RepeatSubmit;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.jianguan.manage.map.domain.bo.MapPlanServerBo;
import com.ruoyi.jianguan.manage.map.service.IMapPlanServerService;

import javax.validation.constraints.NotEmpty;

/**
 * 地图方案服务关联
 *
 * @author ruoyi
 * @date 2023-04-10
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/map/planServer")
public class MapPlanServerController extends BaseController {

    private final IMapPlanServerService mapPlanServerService;

    /**
     * 根据地图方案ID查询关联地图服务列表
     * @param planId 地图方案ID
     * @param pageQuery
     * @return
     */
    @SaCheckPermission("map:planServer:list")
    @GetMapping("/list/{planId}")
    public TableDataInfo<MapPlanServerVo> listMapServerConfigByPlanId(@PathVariable("planId") Long planId, PageQuery pageQuery) {
        return mapPlanServerService.queryPageList(planId, pageQuery);
    }

    /**
     * 批量导入地图方案服务关联
     */
    @SaCheckPermission("system:planServer:import")
    @Log(title = "地图方案服务关联" , businessType = BusinessType.IMPORT)
    @RepeatSubmit()
    @PostMapping("/importMapServerConfig")
    public R<Void> add(@Validated(AddGroup.class) @RequestBody MapPlanServerBo bo) {
        return toAjax(mapPlanServerService.importBatchByBo(bo));
    }

    /**
     * 删除地图方案管理
     *
     * @param ids 主键串
     */
    @SaCheckPermission("map:planServer:remove")
    @Log(title = "地图方案管理" , businessType = BusinessType.DELETE)
    @DeleteMapping("/delete/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(mapPlanServerService.deleteWithValidByIds(Arrays.asList(ids), true) ? 1 : 0);
    }
}
