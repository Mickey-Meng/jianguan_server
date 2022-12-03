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
import com.ruoyi.project.ledgerChangeDetail.domain.bo.MeaLedgerChangeDetailBo;
import com.ruoyi.project.ledgerChangeDetail.domain.vo.MeaLedgerChangeDetailVo;
import com.ruoyi.project.ledgerChangeDetail.service.IMeaLedgerChangeDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.List;

/**
 * 台账变更/工程变更 明细
 *
 * @author ruoyi
 * @date 2022-12-03
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/ledgerChangeDetail/ledgerChangeDetail")
public class MeaLedgerChangeDetailController extends BaseController {

    private final IMeaLedgerChangeDetailService iMeaLedgerChangeDetailService;

    /**
     * 查询台账变更/工程变更 明细列表
     */
    @SaCheckPermission("ledgerChangeDetail:ledgerChangeDetail:list")
    @GetMapping("/list")
    public TableDataInfo<MeaLedgerChangeDetailVo> list(MeaLedgerChangeDetailBo bo, PageQuery pageQuery) {
        return iMeaLedgerChangeDetailService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出台账变更/工程变更 明细列表
     */
    @SaCheckPermission("ledgerChangeDetail:ledgerChangeDetail:export")
    @Log(title = "台账变更/工程变更 明细", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(MeaLedgerChangeDetailBo bo, HttpServletResponse response) {
        List<MeaLedgerChangeDetailVo> list = iMeaLedgerChangeDetailService.queryList(bo);
        ExcelUtil.exportExcel(list, "台账变更/工程变更 明细", MeaLedgerChangeDetailVo.class, response);
    }

    /**
     * 获取台账变更/工程变更 明细详细信息
     *
     * @param zmh 主键
     */
    @SaCheckPermission("ledgerChangeDetail:ledgerChangeDetail:query")
    @GetMapping("/{zmh}")
    public R<MeaLedgerChangeDetailVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable String zmh) {
        return R.ok(iMeaLedgerChangeDetailService.queryById(zmh));
    }

    /**
     * 新增台账变更/工程变更 明细
     */
    @SaCheckPermission("ledgerChangeDetail:ledgerChangeDetail:add")
    @Log(title = "台账变更/工程变更 明细", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody MeaLedgerChangeDetailBo bo) {
        return toAjax(iMeaLedgerChangeDetailService.insertByBo(bo) ? 1 : 0);
    }

    /**
     * 修改台账变更/工程变更 明细
     */
    @SaCheckPermission("ledgerChangeDetail:ledgerChangeDetail:edit")
    @Log(title = "台账变更/工程变更 明细", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody MeaLedgerChangeDetailBo bo) {
        return toAjax(iMeaLedgerChangeDetailService.updateByBo(bo) ? 1 : 0);
    }

    /**
     * 删除台账变更/工程变更 明细
     *
     * @param zmhs 主键串
     */
    @SaCheckPermission("ledgerChangeDetail:ledgerChangeDetail:remove")
    @Log(title = "台账变更/工程变更 明细", businessType = BusinessType.DELETE)
    @DeleteMapping("/{zmhs}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable String[] zmhs) {
        return toAjax(iMeaLedgerChangeDetailService.deleteWithValidByIds(Arrays.asList(zmhs), true) ? 1 : 0);
    }
}
