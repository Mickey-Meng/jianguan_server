package com.ruoyi.web.controller.jianguan.manage.project;

import java.util.List;
import java.util.Map;

import com.google.common.collect.Maps;
import com.ruoyi.jianguan.manage.project.domain.bo.PubMonitorBo;
import com.ruoyi.jianguan.manage.project.domain.vo.PubMonitorVo;
import com.ruoyi.jianguan.manage.project.service.IPubMonitorService;
import lombok.RequiredArgsConstructor;

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
import com.ruoyi.common.enums.BusinessType;
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
     * 分页查询设备监控列表
     */
    @SaCheckPermission("jgProject:monitor:list")
    @GetMapping("/page")
    public TableDataInfo<PubMonitorVo> page(PubMonitorBo bo, PageQuery pageQuery) {
        return iPubMonitorService.queryPageList(bo, pageQuery);
    }

    /**
     * 获取设备监控详细信息
     *
     * @param projectId 主键
     */
    @SaCheckPermission("jgProject:monitor:query")
    @GetMapping("/{projectId}")
    public R<Map<String, List<PubMonitorVo>>> getMonitors(@NotNull(message = "项目ID不能为空")
                                     @PathVariable String projectId) {
        PubMonitorBo bo = new PubMonitorBo();
        bo.setProjectId(projectId);
        Map<String, List<PubMonitorVo>> dataMap = Maps.newHashMap();
        dataMap.put("monitorDevices", iPubMonitorService.queryList(bo));
        return R.ok(dataMap);
    }

    /**
     * 新增设备监控
     */
    @SaCheckPermission("jgProject:monitor:add")
    @Log(title = "设备监控" , businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping("/save/{projectId}")
    public R<Void> saveMonitors(@Validated(AddGroup.class)
                                @PathVariable String projectId,
                                @RequestBody List<PubMonitorBo> boList) {
        return toAjax(iPubMonitorService.saveMonitors(projectId, boList));
    }
}
