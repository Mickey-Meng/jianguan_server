package com.ruoyi.web.controller.project;


import cn.dev33.satoken.annotation.SaCheckPermission;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.annotation.RepeatSubmit;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.project.ledger.domain.bo.MeaLedgerBreakdownBo;
import com.ruoyi.project.ledger.domain.vo.MeaLedgerBreakdownVo;
import com.ruoyi.project.ledger.service.IMeaLedgerBreakdownService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.List;

/**
 * 台账分解
 *
 * @author ruoyi
 * @date 2022-12-03
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/ledger/ledgerBreakdown")
public class MeaLedgerBreakdownController extends BaseController {

    private final IMeaLedgerBreakdownService iMeaLedgerBreakdownService;

    /**
     * 查询台账分解列表
     */
    @SaCheckPermission("ledger:ledgerBreakdown:list")
    @GetMapping("/list")
    public R<List<MeaLedgerBreakdownVo>> list(MeaLedgerBreakdownBo bo) {
        List<MeaLedgerBreakdownVo> list = iMeaLedgerBreakdownService.queryList(bo);
        return R.ok(list);
    }

    /**
     * 导出台账分解列表
     */
    @SaCheckPermission("ledger:ledgerBreakdown:export")
    @Log(title = "台账分解", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(MeaLedgerBreakdownBo bo, HttpServletResponse response) {
        List<MeaLedgerBreakdownVo> list = iMeaLedgerBreakdownService.queryList(bo);
        ExcelUtil.exportExcel(list, "台账分解", MeaLedgerBreakdownVo.class, response);
    }

    /**
     * 获取台账分解详细信息
     *
     * @param tzfjbh 主键
     */
    @SaCheckPermission("ledger:ledgerBreakdown:query")
    @GetMapping("/{tzfjbh}")
    public R<MeaLedgerBreakdownVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable String tzfjbh) {
        return R.ok(iMeaLedgerBreakdownService.queryById(tzfjbh));
    }

    /**
     * 新增台账分解
     */
    @SaCheckPermission("ledger:ledgerBreakdown:add")
    @Log(title = "台账分解", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody MeaLedgerBreakdownBo bo) {
        return toAjax(iMeaLedgerBreakdownService.insertByBo(bo) ? 1 : 0);
    }

    /**
     * 修改台账分解
     */
    @SaCheckPermission("ledger:ledgerBreakdown:edit")
    @Log(title = "台账分解", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody MeaLedgerBreakdownBo bo) {
        return toAjax(iMeaLedgerBreakdownService.updateByBo(bo) ? 1 : 0);
    }

    /**
     * 删除台账分解
     *
     * @param tzfjbhs 主键串
     */
    @SaCheckPermission("ledger:ledgerBreakdown:remove")
    @Log(title = "台账分解", businessType = BusinessType.DELETE)
    @DeleteMapping("/{tzfjbhs}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable String[] tzfjbhs) {
        return toAjax(iMeaLedgerBreakdownService.deleteWithValidByIds(Arrays.asList(tzfjbhs), true) ? 1 : 0);
    }
}
