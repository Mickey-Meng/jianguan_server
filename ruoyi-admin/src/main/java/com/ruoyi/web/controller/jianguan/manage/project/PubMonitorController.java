package com.ruoyi.web.controller.jianguan.manage.project;

import java.util.List;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import com.ruoyi.jianguan.manage.project.domain.bo.PubMonitorBo;
import com.ruoyi.jianguan.manage.project.domain.vo.PubMonitorVo;
import com.ruoyi.jianguan.manage.project.service.IPubMonitorService;
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
 * 设备监控
 *
 * @author ruoyi
 * @date 2023-05-28
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/system/jg/project/monitor")
public class PubMonitorController extends BaseController {

    private final IPubMonitorService iPubMonitorService;

/**
 * 查询设备监控列表
 */
@SaCheckPermission("system:monitor:list")
@GetMapping("/list")
    public TableDataInfo<PubMonitorVo> list(PubMonitorBo bo, PageQuery pageQuery) {
        return iPubMonitorService.queryPageList(bo, pageQuery);
    }

    /**
     * 分页查询设备监控列表
     */
    @SaCheckPermission("system:monitor:list")
    @GetMapping("/page")

    public TableDataInfo<PubMonitorVo> page(PubMonitorBo bo, PageQuery pageQuery) {
        return iPubMonitorService.queryPageList(bo, pageQuery);
    }


    /**
     * 导出设备监控列表
     */
    @SaCheckPermission("system:monitor:export")
    @Log(title = "设备监控" , businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(PubMonitorBo bo, HttpServletResponse response) {
        List<PubMonitorVo> list = iPubMonitorService.queryList(bo);
        ExcelUtil.exportExcel(list, "设备监控" , PubMonitorVo.class, response);
    }

    /**
     * 获取设备监控详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("system:monitor:query")
    @GetMapping("/{id}")
    public R<PubMonitorVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Integer id) {
        return R.ok(iPubMonitorService.queryById(id));
    }

    /**
     * 新增设备监控
     */
    @SaCheckPermission("system:monitor:add")
    @Log(title = "设备监控" , businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody PubMonitorBo bo) {
        return toAjax(iPubMonitorService.insertByBo(bo) ? 1 : 0);
    }

    /**
     * 修改设备监控
     */
    @SaCheckPermission("system:monitor:edit")
    @Log(title = "设备监控" , businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody PubMonitorBo bo) {
        return toAjax(iPubMonitorService.updateByBo(bo) ? 1 : 0);
    }

    /**
     * 删除设备监控
     *
     * @param ids 主键串
     */
    @SaCheckPermission("system:monitor:remove")
    @Log(title = "设备监控" , businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Integer[] ids) {
        return toAjax(iPubMonitorService.deleteWithValidByIds(Arrays.asList(ids), true) ? 1 : 0);
    }
}
