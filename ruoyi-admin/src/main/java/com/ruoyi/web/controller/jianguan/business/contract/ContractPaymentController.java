package com.ruoyi.web.controller.jianguan.business.contract;

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
import com.ruoyi.jianguan.business.contract.domain.bo.ContractPaymentBo;
import com.ruoyi.jianguan.business.contract.domain.vo.ContractPaymentVo;
import com.ruoyi.jianguan.business.contract.service.IContractPaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.List;

/**
 * 合同付款
 *
 * @author mickey
 * @date 2023-06-07
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/produce/contractPayment")
public class ContractPaymentController extends BaseController {

    private final IContractPaymentService iContractPaymentService;

    /**
     * 查询合同付款列表
     */
    @SaCheckPermission("produce:contractPayment:list")
    @GetMapping("/list")
    public TableDataInfo<ContractPaymentVo> list(ContractPaymentBo bo, PageQuery pageQuery) {
        return iContractPaymentService.queryPageList(bo, pageQuery);
    }

    /**
     * 分页查询合同付款列表
     */
    @SaCheckPermission("produce:contractPayment:list")
    @GetMapping("/page")

    public TableDataInfo<ContractPaymentVo> page(ContractPaymentBo bo, PageQuery pageQuery) {
        return iContractPaymentService.queryPageList(bo, pageQuery);
    }


    /**
     * 导出合同付款列表
     */
    @SaCheckPermission("produce:contractPayment:export")
    @Log(title = "合同付款" , businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(ContractPaymentBo bo, HttpServletResponse response) {
        List<ContractPaymentVo> list = iContractPaymentService.queryList(bo);
        ExcelUtil.exportExcel(list, "合同付款" , ContractPaymentVo.class, response);
    }

    /**
     * 获取合同付款详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("produce:contractPayment:query")
    @GetMapping("/{id}")
    public R<ContractPaymentVo> getInfo(@NotNull(message = "主键不能为空")
                                          @PathVariable Long id) {
        return R.ok(iContractPaymentService.queryById(id));
    }

    /**
     * 新增合同付款
     */
    @SaCheckPermission("produce:contractPayment:add")
    @Log(title = "合同付款" , businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody ContractPaymentBo bo) {
        return toAjax(iContractPaymentService.insertByBo(bo) ? 1 : 0);
    }

    /**
     * 修改合同付款
     */
    @SaCheckPermission("produce:contractPayment:edit")
    @Log(title = "合同付款" , businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody ContractPaymentBo bo) {
        return toAjax(iContractPaymentService.updateByBo(bo) ? 1 : 0);
    }

    /**
     * 删除合同付款
     *
     * @param ids 主键串
     */
    @SaCheckPermission("produce:contractPayment:remove")
    @Log(title = "合同付款" , businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(iContractPaymentService.deleteWithValidByIds(Arrays.asList(ids), true) ? 1 : 0);
    }
}