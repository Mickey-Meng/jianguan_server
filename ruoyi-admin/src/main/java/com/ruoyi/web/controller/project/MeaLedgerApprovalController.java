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
import com.ruoyi.project.approval.domain.bo.MeaLedgerApprovalBo;
import com.ruoyi.project.approval.domain.vo.MeaLedgerApprovalVo;
import com.ruoyi.project.approval.service.IMeaLedgerApprovalService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.List;

/**
 * 台账报审
 *
 * @author ruoyi
 * @date 2022-12-03
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/ledgerapproval/ledgerApproval")
public class MeaLedgerApprovalController extends BaseController {

    private final IMeaLedgerApprovalService iMeaLedgerApprovalService;

    /**
     * 查询台账报审列表
     */
    @SaCheckPermission("ledgerapproval:ledgerApproval:list")
    @GetMapping("/list")
    public TableDataInfo<MeaLedgerApprovalVo> list(MeaLedgerApprovalBo bo, PageQuery pageQuery) {
        return iMeaLedgerApprovalService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出台账报审列表
     */
    @SaCheckPermission("ledgerapproval:ledgerApproval:export")
    @Log(title = "台账报审", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(MeaLedgerApprovalBo bo, HttpServletResponse response) {
        List<MeaLedgerApprovalVo> list = iMeaLedgerApprovalService.queryList(bo);
        ExcelUtil.exportExcel(list, "台账报审", MeaLedgerApprovalVo.class, response);
    }

    /**
     * 获取台账报审详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("ledgerapproval:ledgerApproval:query")
    @GetMapping("/{id}")
    public R<MeaLedgerApprovalVo> getInfo(@NotNull(message = "主键不能为空")
                                          @PathVariable Long id) {
        return R.ok(iMeaLedgerApprovalService.queryById(id));
    }

    /**
     * 新增台账报审
     */
    @SaCheckPermission("ledgerapproval:ledgerApproval:add")
    @Log(title = "台账报审", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody List<MeaLedgerApprovalBo> bo) {

        boolean b = iMeaLedgerApprovalService.insertByListBo(bo);
        if (b) {

        }
        return toAjax(b ? 1 : 0);
    }

    /**
     * 修改台账报审
     */
    @SaCheckPermission("ledgerapproval:ledgerApproval:edit")
    @Log(title = "台账报审", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody MeaLedgerApprovalBo bo) {
        return toAjax(iMeaLedgerApprovalService.updateByBo(bo) ? 1 : 0);
    }

    /**
     * 删除台账报审
     *
     * @param ids 主键串
     */
    @SaCheckPermission("ledgerapproval:ledgerApproval:remove")
    @Log(title = "台账报审", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(iMeaLedgerApprovalService.deleteWithValidByIds(Arrays.asList(ids), true) ? 1 : 0);
    }
}
