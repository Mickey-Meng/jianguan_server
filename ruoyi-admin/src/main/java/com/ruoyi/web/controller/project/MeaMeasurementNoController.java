package com.ruoyi.web.controller.project;


import cn.dev33.satoken.annotation.SaCheckPermission;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.annotation.RepeatSubmit;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.PageQuery;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.project.measurementNo.domain.bo.MeaMeasurementNoBo;
import com.ruoyi.project.measurementNo.domain.vo.MeaMeasurementNoVo;
import com.ruoyi.project.measurementNo.service.IMeaMeasurementNoService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.List;

/**
 * 中间计量期数管理
 *
 * @author ruoyi
 * @date 2022-12-03
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/measurementNo/measurementNo")
public class MeaMeasurementNoController extends BaseController {

    private final IMeaMeasurementNoService iMeaMeasurementNoService;

    /**
     * 查询中间计量期数管理列表
     */
    @SaCheckPermission("measurementNo:measurementNo:list")
    @GetMapping("/list")
    public TableDataInfo<MeaMeasurementNoVo> list(MeaMeasurementNoBo bo, PageQuery pageQuery) {
        return iMeaMeasurementNoService.queryPageList(bo, pageQuery);
    }

    /**
     * 查询中间计量期数管理列表
     */
    @SaCheckPermission("measurementNo:measurementNo:list")
    @GetMapping("/sortList")
    public R sortList() {
        return iMeaMeasurementNoService.sortList();
    }


    /**
     * 导出中间计量期数管理列表
     */
    @SaCheckPermission("measurementNo:measurementNo:export")
    @Log(title = "中间计量期数管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(MeaMeasurementNoBo bo, HttpServletResponse response) {
        List<MeaMeasurementNoVo> list = iMeaMeasurementNoService.queryList(bo);
        ExcelUtil.exportExcel(list, "中间计量期数管理", MeaMeasurementNoVo.class, response);
    }

    /**
     * 获取中间计量期数管理详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("measurementNo:measurementNo:query")
    @GetMapping("/{id}")
    public R<MeaMeasurementNoVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable String id) {
        return R.ok(iMeaMeasurementNoService.queryById(id));
    }

    /**
     * 新增中间计量期数管理
     */
    @SaCheckPermission("measurementNo:measurementNo:add")
    @Log(title = "中间计量期数管理", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody MeaMeasurementNoBo bo) {
        return toAjax(iMeaMeasurementNoService.insertByBo(bo) ? 1 : 0);
    }

    /**
     * 修改中间计量期数管理
     */
    @SaCheckPermission("measurementNo:measurementNo:edit")
    @Log(title = "中间计量期数管理", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody MeaMeasurementNoBo bo) {
        return toAjax(iMeaMeasurementNoService.updateByBo(bo) ? 1 : 0);
    }

    /**
     * 删除中间计量期数管理
     *
     * @param ids 主键串
     */
    @SaCheckPermission("measurementNo:measurementNo:remove")
    @Log(title = "中间计量期数管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable String[] ids) {
        return toAjax(iMeaMeasurementNoService.deleteWithValidByIds(Arrays.asList(ids), true) ? 1 : 0);
    }
}
