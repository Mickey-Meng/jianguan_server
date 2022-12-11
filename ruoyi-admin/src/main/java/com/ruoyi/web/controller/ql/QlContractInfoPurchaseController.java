package com.ruoyi.web.controller.ql;

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
import com.ruoyi.ql.domain.vo.QlContractInfoPurchaseVo;
import com.ruoyi.ql.domain.bo.QlContractInfoPurchaseBo;
import com.ruoyi.ql.service.IQlContractInfoPurchaseService;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 采购合同
 *
 * @author ruoyi
 * @date 2022-12-11
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/contractInfoPurchase/contractInfoPurchase")
public class QlContractInfoPurchaseController extends BaseController {

    private final IQlContractInfoPurchaseService iQlContractInfoPurchaseService;

    /**
     * 查询采购合同 列表
     */
    @SaCheckPermission("contractInfoPurchase:contractInfoPurchase:list")
    @GetMapping("/list")
    public TableDataInfo<QlContractInfoPurchaseVo> list(QlContractInfoPurchaseBo bo, PageQuery pageQuery) {
        return iQlContractInfoPurchaseService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出采购合同 列表
     */
    @SaCheckPermission("contractInfoPurchase:contractInfoPurchase:export")
    @Log(title = "采购合同 ", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(QlContractInfoPurchaseBo bo, HttpServletResponse response) {
        List<QlContractInfoPurchaseVo> list = iQlContractInfoPurchaseService.queryList(bo);
        ExcelUtil.exportExcel(list, "采购合同 ", QlContractInfoPurchaseVo.class, response);
    }

    /**
     * 获取采购合同 详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("contractInfoPurchase:contractInfoPurchase:query")
    @GetMapping("/{id}")
    public R<QlContractInfoPurchaseVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(iQlContractInfoPurchaseService.queryById(id));
    }

    /**
     * 新增采购合同
     */
    @SaCheckPermission("contractInfoPurchase:contractInfoPurchase:add")
    @Log(title = "采购合同 ", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody QlContractInfoPurchaseBo bo) {
        return toAjax(iQlContractInfoPurchaseService.insertByBo(bo) ? 1 : 0);
    }

    /**
     * 修改采购合同
     */
    @SaCheckPermission("contractInfoPurchase:contractInfoPurchase:edit")
    @Log(title = "采购合同 ", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody QlContractInfoPurchaseBo bo) {
        return toAjax(iQlContractInfoPurchaseService.updateByBo(bo) ? 1 : 0);
    }

    /**
     * 删除采购合同
     *
     * @param ids 主键串
     */
    @SaCheckPermission("contractInfoPurchase:contractInfoPurchase:remove")
    @Log(title = "采购合同 ", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(iQlContractInfoPurchaseService.deleteWithValidByIds(Arrays.asList(ids), true) ? 1 : 0);
    }
}
