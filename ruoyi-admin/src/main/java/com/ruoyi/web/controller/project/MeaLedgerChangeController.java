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
import com.ruoyi.project.ledgerChange.domain.bo.MeaLedgerChangeBo;
import com.ruoyi.project.ledgerChange.domain.vo.MeaLedgerChangeVo;
import com.ruoyi.project.ledgerChange.service.IMeaLedgerChangeService;
import com.ruoyi.project.ledgerChangeDetail.domain.bo.MeaLedgerChangeDetailBo;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.List;

/**
 * 台账变更/工程变更
 *
 * @author ruoyi
 * @date 2022-12-03
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/ledgerChange/ledgerChange")
public class MeaLedgerChangeController extends BaseController {

    private final IMeaLedgerChangeService iMeaLedgerChangeService;

    /**
     * 查询台账变更/工程变更列表
     */
    @SaCheckPermission("ledgerChange:ledgerChange:list")
    @GetMapping("/list")
    public TableDataInfo<MeaLedgerChangeVo> list(MeaLedgerChangeBo bo, PageQuery pageQuery) {
        return iMeaLedgerChangeService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出台账变更/工程变更列表
     */
    @SaCheckPermission("ledgerChange:ledgerChange:export")
    @Log(title = "台账变更/工程变更", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(MeaLedgerChangeBo bo, HttpServletResponse response) {
        List<MeaLedgerChangeVo> list = iMeaLedgerChangeService.queryList(bo);
        ExcelUtil.exportExcel(list, "台账变更/工程变更", MeaLedgerChangeVo.class, response);
    }

    /**
     * 获取台账变更/工程变更详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("ledgerChange:ledgerChange:query")
    @GetMapping("/{id}")
    public R<MeaLedgerChangeVo> getInfo(@NotNull(message = "主键不能为空")
                                        @PathVariable String id) {
        return R.ok(iMeaLedgerChangeService.queryById(id));
    }

    /**
     * 新增台账变更/工程变更
     */
    @SaCheckPermission("ledgerChange:ledgerChange:add")
    @Log(title = "台账变更/工程变更", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody MeaLedgerChangeBo bo) {
        return toAjax(iMeaLedgerChangeService.insertByBo(bo) ? 1 : 0);
    }


    /**
     * 新增台账变更/工程变更
     */
    @SaCheckPermission("ledgerChange:ledgerChange:add")
    @Log(title = "台账变更/工程变更", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping("insertList")
    public R<Void> add(@RequestBody List<MeaLedgerChangeDetailBo> bo) {
        return toAjax(iMeaLedgerChangeService.insertList(bo) ? 1 : 0);
    }



    /**
     * 修改台账变更/工程变更
     */
    @SaCheckPermission("ledgerChange:ledgerChange:edit")
    @Log(title = "台账变更/工程变更", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody MeaLedgerChangeBo bo) {
        return toAjax(iMeaLedgerChangeService.updateByBo(bo) ? 1 : 0);
    }


    /**
     * 删除台账变更/工程变更
     *
     * @param ids 主键串
     */
    @SaCheckPermission("ledgerChange:ledgerChange:remove")
    @Log(title = "台账变更/工程变更", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable String[] ids) {
        return toAjax(iMeaLedgerChangeService.deleteWithValidByIds(Arrays.asList(ids), true) ? 1 : 0);
    }


}
