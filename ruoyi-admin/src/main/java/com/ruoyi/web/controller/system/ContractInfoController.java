package com.ruoyi.web.controller.system;

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
import com.ruoyi.system.domain.vo.ContractInfoVo;
import com.ruoyi.system.domain.bo.ContractInfoBo;
import com.ruoyi.system.service.IContractInfoService;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 合同条款
 *
 * @author ruoyi
 * @date 2022-12-02
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/contract/contract")
public class ContractInfoController extends BaseController {

    private final IContractInfoService iContractInfoService;

    /**
     * 查询合同条款列表
     */
    @SaCheckPermission("contract:contract:list")
    @GetMapping("/list")
    public TableDataInfo<ContractInfoVo> list(ContractInfoBo bo, PageQuery pageQuery) {
        return iContractInfoService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出合同条款列表
     */
    @SaCheckPermission("contract:contract:export")
    @Log(title = "合同条款", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(ContractInfoBo bo, HttpServletResponse response) {
        List<ContractInfoVo> list = iContractInfoService.queryList(bo);
        ExcelUtil.exportExcel(list, "合同条款", ContractInfoVo.class, response);
    }

    /**
     * 获取合同条款详细信息
     *
     * @param HTBH 主键
     */
    @SaCheckPermission("contract:contract:query")
    @GetMapping("/{HTBH}")
    public R<ContractInfoVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable String HTBH) {
        return R.ok(iContractInfoService.queryById(HTBH));
    }

    /**
     * 新增合同条款
     */
    @SaCheckPermission("contract:contract:add")
    @Log(title = "合同条款", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated({AddGroup.class}) @RequestBody ContractInfoBo bo) {
        return toAjax(iContractInfoService.insertByBo(bo) ? 1 : 0);
    }

    /**
     * 修改合同条款
     */
    @SaCheckPermission("contract:contract:edit")
    @Log(title = "合同条款", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody ContractInfoBo bo) {
        return toAjax(iContractInfoService.updateByBo(bo) ? 1 : 0);
    }

    /**
     * 删除合同条款
     *
     * @param HTBHs 主键串
     */
    @SaCheckPermission("contract:contract:remove")
    @Log(title = "合同条款", businessType = BusinessType.DELETE)
    @DeleteMapping("/{HTBHs}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable String[] HTBHs) {
        return toAjax(iContractInfoService.deleteWithValidByIds(Arrays.asList(HTBHs), true) ? 1 : 0);
    }
}
