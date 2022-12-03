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
import com.ruoyi.project.materialprepayment.domain.bo.MeaMaterialPrepaymentBo;
import com.ruoyi.project.materialprepayment.domain.vo.MeaMaterialPrepaymentVo;
import com.ruoyi.project.materialprepayment.service.IMeaMaterialPrepaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.List;

/**
 * 材料预付款
 *
 * @author ruoyi
 * @date 2022-12-03
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/materialPrepayment/materialPrepayment")
public class MeaMaterialPrepaymentController extends BaseController {

    private final IMeaMaterialPrepaymentService iMeaMaterialPrepaymentService;

    /**
     * 查询材料预付款列表
     */
    @SaCheckPermission("materialPrepayment:materialPrepayment:list")
    @GetMapping("/list")
    public TableDataInfo<MeaMaterialPrepaymentVo> list(MeaMaterialPrepaymentBo bo, PageQuery pageQuery) {
        return iMeaMaterialPrepaymentService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出材料预付款列表
     */
    @SaCheckPermission("materialPrepayment:materialPrepayment:export")
    @Log(title = "材料预付款", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(MeaMaterialPrepaymentBo bo, HttpServletResponse response) {
        List<MeaMaterialPrepaymentVo> list = iMeaMaterialPrepaymentService.queryList(bo);
        ExcelUtil.exportExcel(list, "材料预付款", MeaMaterialPrepaymentVo.class, response);
    }

    /**
     * 获取材料预付款详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("materialPrepayment:materialPrepayment:query")
    @GetMapping("/{id}")
    public R<MeaMaterialPrepaymentVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable String id) {
        return R.ok(iMeaMaterialPrepaymentService.queryById(id));
    }

    /**
     * 新增材料预付款
     */
    @SaCheckPermission("materialPrepayment:materialPrepayment:add")
    @Log(title = "材料预付款", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody MeaMaterialPrepaymentBo bo) {
        return toAjax(iMeaMaterialPrepaymentService.insertByBo(bo) ? 1 : 0);
    }

    /**
     * 修改材料预付款
     */
    @SaCheckPermission("materialPrepayment:materialPrepayment:edit")
    @Log(title = "材料预付款", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody MeaMaterialPrepaymentBo bo) {
        return toAjax(iMeaMaterialPrepaymentService.updateByBo(bo) ? 1 : 0);
    }

    /**
     * 删除材料预付款
     *
     * @param ids 主键串
     */
    @SaCheckPermission("materialPrepayment:materialPrepayment:remove")
    @Log(title = "材料预付款", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable String[] ids) {
        return toAjax(iMeaMaterialPrepaymentService.deleteWithValidByIds(Arrays.asList(ids), true) ? 1 : 0);
    }
}
