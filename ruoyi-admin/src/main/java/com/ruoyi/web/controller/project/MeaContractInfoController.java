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
import com.ruoyi.project.contract.domain.bo.MeaContractInfoBo;
import com.ruoyi.project.contract.domain.vo.MeaContractInfoVo;
import com.ruoyi.project.contract.service.IMeaContractInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.List;

/**
 * 合同条款
 *
 * @author ruoyi
 * @date 2022-12-03
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/contractInfo/contractInfo")
public class MeaContractInfoController extends BaseController {

    private final IMeaContractInfoService iMeaContractInfoService;

    /**
     * 查询合同条款列表
     */
    @SaCheckPermission("contractInfo:contractInfo:list")
    @GetMapping("/list")
    public TableDataInfo<MeaContractInfoVo> list(MeaContractInfoBo bo, PageQuery pageQuery) {
        return iMeaContractInfoService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出合同条款列表
     */
    @SaCheckPermission("contractInfo:contractInfo:export")
    @Log(title = "合同条款", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(MeaContractInfoBo bo, HttpServletResponse response) {
        List<MeaContractInfoVo> list = iMeaContractInfoService.queryList(bo);
        ExcelUtil.exportExcel(list, "合同条款", MeaContractInfoVo.class, response);
    }

    /**
     * 获取合同条款详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("contractInfo:contractInfo:query")
    @GetMapping("/{id}")
    public R<MeaContractInfoVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable String id) {
        return R.ok(iMeaContractInfoService.queryById(id));
    }

    /**
     * 新增合同条款
     */
    @SaCheckPermission("contractInfo:contractInfo:add")
    @Log(title = "合同条款", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody MeaContractInfoBo bo) {
        return toAjax(iMeaContractInfoService.insertByBo(bo) ? 1 : 0);
    }

    /**
     * 修改合同条款
     */
    @SaCheckPermission("contractInfo:contractInfo:edit")
    @Log(title = "合同条款", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody MeaContractInfoBo bo) {
        return toAjax(iMeaContractInfoService.updateByBo(bo) ? 1 : 0);
    }

    /**
     * 删除合同条款
     *
     * @param ids 主键串
     */
    @SaCheckPermission("contractInfo:contractInfo:remove")
    @Log(title = "合同条款", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable String[] ids) {
        return toAjax(iMeaContractInfoService.deleteWithValidByIds(Arrays.asList(ids), true) ? 1 : 0);
    }
}
