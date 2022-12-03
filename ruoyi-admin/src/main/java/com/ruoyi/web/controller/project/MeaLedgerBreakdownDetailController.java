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
import com.ruoyi.project.ledger.domain.bo.MeaLedgerBreakdownDetailBo;
import com.ruoyi.project.ledger.domain.vo.MeaLedgerBreakdownDetailVo;
import com.ruoyi.project.ledger.service.IMeaLedgerBreakdownDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.List;

/**
 * 台账分解明细
 *
 * @author ruoyi
 * @date 2022-12-03
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/ledgerDetail/ledgerBreakdownDetail")
public class MeaLedgerBreakdownDetailController extends BaseController {

    private final IMeaLedgerBreakdownDetailService iMeaLedgerBreakdownDetailService;

    /**
     * 查询台账分解明细列表
     */
    @SaCheckPermission("ledgerDetail:ledgerBreakdownDetail:list")
    @GetMapping("/list")
    public TableDataInfo<MeaLedgerBreakdownDetailVo> list(MeaLedgerBreakdownDetailBo bo, PageQuery pageQuery) {
        return iMeaLedgerBreakdownDetailService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出台账分解明细列表
     */
    @SaCheckPermission("ledgerDetail:ledgerBreakdownDetail:export")
    @Log(title = "台账分解明细", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(MeaLedgerBreakdownDetailBo bo, HttpServletResponse response) {
        List<MeaLedgerBreakdownDetailVo> list = iMeaLedgerBreakdownDetailService.queryList(bo);
        ExcelUtil.exportExcel(list, "台账分解明细", MeaLedgerBreakdownDetailVo.class, response);
    }

    /**
     * 获取台账分解明细详细信息
     *
     * @param tzfjbh 主键
     */
    @SaCheckPermission("ledgerDetail:ledgerBreakdownDetail:query")
    @GetMapping("/{tzfjbh}")
    public R<MeaLedgerBreakdownDetailVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable String tzfjbh) {
        return R.ok(iMeaLedgerBreakdownDetailService.queryById(tzfjbh));
    }

    /**
     * 新增台账分解明细
     */
    @SaCheckPermission("ledgerDetail:ledgerBreakdownDetail:add")
    @Log(title = "台账分解明细", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody MeaLedgerBreakdownDetailBo bo) {
        return toAjax(iMeaLedgerBreakdownDetailService.insertByBo(bo) ? 1 : 0);
    }

    /**
     * 修改台账分解明细
     */
    @SaCheckPermission("ledgerDetail:ledgerBreakdownDetail:edit")
    @Log(title = "台账分解明细", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody MeaLedgerBreakdownDetailBo bo) {
        return toAjax(iMeaLedgerBreakdownDetailService.updateByBo(bo) ? 1 : 0);
    }

    /**
     * 删除台账分解明细
     *
     * @param tzfjbhs 主键串
     */
    @SaCheckPermission("ledgerDetail:ledgerBreakdownDetail:remove")
    @Log(title = "台账分解明细", businessType = BusinessType.DELETE)
    @DeleteMapping("/{tzfjbhs}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable String[] tzfjbhs) {
        return toAjax(iMeaLedgerBreakdownDetailService.deleteWithValidByIds(Arrays.asList(tzfjbhs), true) ? 1 : 0);
    }
}
