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
import com.ruoyi.project.startup.domain.bo.MeaStartupPrepaymentBo;
import com.ruoyi.project.startup.domain.vo.MeaStartupPrepaymentVo;
import com.ruoyi.project.startup.service.IMeaStartupPrepaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.List;

/**
 * 开工预付款
 *
 * @author ruoyi
 * @date 2022-12-03
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/startup/startupPrepayment")
public class MeaStartupPrepaymentController extends BaseController {

    private final IMeaStartupPrepaymentService iMeaStartupPrepaymentService;

    /**
     * 查询开工预付款列表
     */
    @SaCheckPermission("startup:startupPrepayment:list")
    @GetMapping("/list")
    public TableDataInfo<MeaStartupPrepaymentVo> list(MeaStartupPrepaymentBo bo, PageQuery pageQuery) {
        return iMeaStartupPrepaymentService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出开工预付款列表
     */
    @SaCheckPermission("startup:startupPrepayment:export")
    @Log(title = "开工预付款", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(MeaStartupPrepaymentBo bo, HttpServletResponse response) {
        List<MeaStartupPrepaymentVo> list = iMeaStartupPrepaymentService.queryList(bo);
        ExcelUtil.exportExcel(list, "开工预付款", MeaStartupPrepaymentVo.class, response);
    }

    /**
     * 获取开工预付款详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("startup:startupPrepayment:query")
    @GetMapping("/{id}")
    public R<MeaStartupPrepaymentVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable String id) {
        return R.ok(iMeaStartupPrepaymentService.queryById(id));
    }

    /**
     * 新增开工预付款
     */
    @SaCheckPermission("startup:startupPrepayment:add")
    @Log(title = "开工预付款", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody MeaStartupPrepaymentBo bo) {
        return toAjax(iMeaStartupPrepaymentService.insertByBo(bo) ? 1 : 0);
    }

    /**
     * 修改开工预付款
     */
    @SaCheckPermission("startup:startupPrepayment:edit")
    @Log(title = "开工预付款", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody MeaStartupPrepaymentBo bo) {
        return toAjax(iMeaStartupPrepaymentService.updateByBo(bo) ? 1 : 0);
    }

    /**
     * 删除开工预付款
     *
     * @param ids 主键串
     */
    @SaCheckPermission("startup:startupPrepayment:remove")
    @Log(title = "开工预付款", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable String[] ids) {
        return toAjax(iMeaStartupPrepaymentService.deleteWithValidByIds(Arrays.asList(ids), true) ? 1 : 0);
    }
}
