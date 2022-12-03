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
import com.ruoyi.project.other.domain.bo.MeaOtherPaymentBo;
import com.ruoyi.project.other.domain.vo.MeaOtherPaymentVo;
import com.ruoyi.project.other.service.IMeaOtherPaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.List;

/**
 * 其他款项
 *
 * @author ruoyi
 * @date 2022-12-03
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/otherPayment/otherPayment")
public class MeaOtherPaymentController extends BaseController {

    private final IMeaOtherPaymentService iMeaOtherPaymentService;

    /**
     * 查询其他款项列表
     */
    @SaCheckPermission("otherPayment:otherPayment:list")
    @GetMapping("/list")
    public TableDataInfo<MeaOtherPaymentVo> list(MeaOtherPaymentBo bo, PageQuery pageQuery) {
        return iMeaOtherPaymentService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出其他款项列表
     */
    @SaCheckPermission("otherPayment:otherPayment:export")
    @Log(title = "其他款项", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(MeaOtherPaymentBo bo, HttpServletResponse response) {
        List<MeaOtherPaymentVo> list = iMeaOtherPaymentService.queryList(bo);
        ExcelUtil.exportExcel(list, "其他款项", MeaOtherPaymentVo.class, response);
    }

    /**
     * 获取其他款项详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("otherPayment:otherPayment:query")
    @GetMapping("/{id}")
    public R<MeaOtherPaymentVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable String id) {
        return R.ok(iMeaOtherPaymentService.queryById(id));
    }

    /**
     * 新增其他款项
     */
    @SaCheckPermission("otherPayment:otherPayment:add")
    @Log(title = "其他款项", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody MeaOtherPaymentBo bo) {
        return toAjax(iMeaOtherPaymentService.insertByBo(bo) ? 1 : 0);
    }

    /**
     * 修改其他款项
     */
    @SaCheckPermission("otherPayment:otherPayment:edit")
    @Log(title = "其他款项", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody MeaOtherPaymentBo bo) {
        return toAjax(iMeaOtherPaymentService.updateByBo(bo) ? 1 : 0);
    }

    /**
     * 删除其他款项
     *
     * @param ids 主键串
     */
    @SaCheckPermission("otherPayment:otherPayment:remove")
    @Log(title = "其他款项", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable String[] ids) {
        return toAjax(iMeaOtherPaymentService.deleteWithValidByIds(Arrays.asList(ids), true) ? 1 : 0);
    }
}
