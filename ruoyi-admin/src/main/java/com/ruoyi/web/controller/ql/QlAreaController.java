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
import com.ruoyi.ql.domain.vo.QlAreaVo;
import com.ruoyi.ql.domain.bo.QlAreaBo;
import com.ruoyi.ql.service.IQlAreaService;

/**
 * 省市区
 *
 * @author ruoyi
 * @date 2022-12-11
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/area/area")
public class QlAreaController extends BaseController {

    private final IQlAreaService iQlAreaService;

    /**
     * 查询省市区列表
     */
    @SaCheckPermission("area:area:list")
    @GetMapping("/list")
    public R<List<QlAreaVo>> list(QlAreaBo bo) {
        List<QlAreaVo> list = iQlAreaService.queryList(bo);
        return R.ok(list);
    }

    /**
     * 导出省市区列表
     */
    @SaCheckPermission("area:area:export")
    @Log(title = "省市区", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(QlAreaBo bo, HttpServletResponse response) {
        List<QlAreaVo> list = iQlAreaService.queryList(bo);
        ExcelUtil.exportExcel(list, "省市区", QlAreaVo.class, response);
    }

    /**
     * 获取省市区详细信息
     *
     * @param Id 主键
     */
    @SaCheckPermission("area:area:query")
    @GetMapping("/{Id}")
    public R<QlAreaVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long Id) {
        return R.ok(iQlAreaService.queryById(Id));
    }

    /**
     * 新增省市区
     */
    @SaCheckPermission("area:area:add")
    @Log(title = "省市区", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody QlAreaBo bo) {
        return toAjax(iQlAreaService.insertByBo(bo) ? 1 : 0);
    }

    /**
     * 修改省市区
     */
    @SaCheckPermission("area:area:edit")
    @Log(title = "省市区", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody QlAreaBo bo) {
        return toAjax(iQlAreaService.updateByBo(bo) ? 1 : 0);
    }

    /**
     * 删除省市区
     *
     * @param Ids 主键串
     */
    @SaCheckPermission("area:area:remove")
    @Log(title = "省市区", businessType = BusinessType.DELETE)
    @DeleteMapping("/{Ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] Ids) {
        return toAjax(iQlAreaService.deleteWithValidByIds(Arrays.asList(Ids), true) ? 1 : 0);
    }
}
