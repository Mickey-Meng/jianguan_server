package com.ruoyi.web.controller.project;


import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
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
import com.ruoyi.project.approval.domain.bo.MeaLedgerApprovalNoBo;
import com.ruoyi.project.approval.domain.vo.MeaLedgerApprovalNoVo;
import com.ruoyi.project.approval.service.IMeaLedgerApprovalNoService;
import com.ruoyi.project.measurementNo.domain.vo.MeaMeasurementNoVo;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.List;

/**
 * 期数管理
 *
 * @author ruoyi
 * @date 2022-12-03
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/approval/ledgerApprovalNo")
public class MeaLedgerApprovalNoController extends BaseController {

    private final IMeaLedgerApprovalNoService iMeaLedgerApprovalNoService;

    /**
     * 查询期数管理列表
     */
    @SaCheckPermission("approval:ledgerApprovalNo:list")
    @GetMapping("/list")
    public TableDataInfo<MeaLedgerApprovalNoVo> list(MeaLedgerApprovalNoBo bo, PageQuery pageQuery) {
        return iMeaLedgerApprovalNoService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出期数管理列表
     */
    @SaCheckPermission("approval:ledgerApprovalNo:export")
    @Log(title = "期数管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(MeaLedgerApprovalNoBo bo, HttpServletResponse response) {
        List<MeaLedgerApprovalNoVo> list = iMeaLedgerApprovalNoService.queryList(bo);
        ExcelUtil.exportExcel(list, "期数管理", MeaLedgerApprovalNoVo.class, response);
    }

    /**
     * 获取期数管理详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("approval:ledgerApprovalNo:query")
    @GetMapping("/{id}")
    public R<MeaLedgerApprovalNoVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable String id) {
        return R.ok(iMeaLedgerApprovalNoService.queryById(id));
    }

    /**
     * 新增期数管理
     */
    @SaCheckPermission("approval:ledgerApprovalNo:add")
    @Log(title = "期数管理", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody MeaLedgerApprovalNoBo bo) {
        // TODO: 2023/3/8 待调整后端查询mapper, 增加reviewCodes查询条件  目前先用查询全量，java代码过滤是否包含审批中的数据来控制是否可以新增
        List<MeaLedgerApprovalNoVo> meaLedgerApprovalNoVos = iMeaLedgerApprovalNoService.queryList(new MeaLedgerApprovalNoBo());
        if(CollUtil.isNotEmpty(meaLedgerApprovalNoVos)) {
            long count = meaLedgerApprovalNoVos.stream().filter(meaLedgerApprovalNoVo -> StrUtil.equalsAny(meaLedgerApprovalNoVo.getReviewCode(), "0", "1")).count();
            // 存在未审批和审批中的数据，不能创建新的申报期数
            if (count > 0) {
                return R.fail("存在未审批和审批中的数据，不能创建新的申报期数");
            }
        }
        return iMeaLedgerApprovalNoService.insertByBo(bo) ? R.ok() : R.fail();
    }

    /**
     * 修改期数管理
     */
    @SaCheckPermission("approval:ledgerApprovalNo:edit")
    @Log(title = "期数管理", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody MeaLedgerApprovalNoBo bo) {
        return toAjax(iMeaLedgerApprovalNoService.updateByBo(bo) ? 1 : 0);
    }

    /**
     * 删除期数管理
     *
     * @param ids 主键串
     */
    @SaCheckPermission("approval:ledgerApprovalNo:remove")
    @Log(title = "期数管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable String[] ids) {
        return toAjax(iMeaLedgerApprovalNoService.deleteWithValidByIds(Arrays.asList(ids), true) ? 1 : 0);
    }


    @GetMapping("/getMaxInfo")
    public R<MeaLedgerApprovalNoVo> getMaxInfo() {
        return R.ok(iMeaLedgerApprovalNoService.queryMax());
    }
}
