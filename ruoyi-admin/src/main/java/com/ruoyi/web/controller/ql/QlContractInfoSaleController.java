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
import com.ruoyi.ql.domain.vo.QlContractInfoSaleVo;
import com.ruoyi.ql.domain.bo.QlContractInfoSaleBo;
import com.ruoyi.ql.service.IQlContractInfoSaleService;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 合同管理
 *
 * @author ruoyi
 * @date 2022-12-11
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/contractInfoSale/contractInfoSale")
public class QlContractInfoSaleController extends BaseController {

    private final IQlContractInfoSaleService iQlContractInfoSaleService;

    /**
     * 查询合同管理列表
     */
    @SaCheckPermission("contractInfoSale:contractInfoSale:list")
    @GetMapping("/list")
    public TableDataInfo<QlContractInfoSaleVo> list(QlContractInfoSaleBo bo, PageQuery pageQuery) {
        return iQlContractInfoSaleService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出合同管理列表
     */
    @SaCheckPermission("contractInfoSale:contractInfoSale:export")
    @Log(title = "合同管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(QlContractInfoSaleBo bo, HttpServletResponse response) {
        List<QlContractInfoSaleVo> list = iQlContractInfoSaleService.queryList(bo);
        ExcelUtil.exportExcel(list, "合同管理", QlContractInfoSaleVo.class, response);
    }

    /**
     * 获取合同管理详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("contractInfoSale:contractInfoSale:query")
    @GetMapping("/{id}")
    public R<QlContractInfoSaleVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(iQlContractInfoSaleService.queryById(id));
    }

    /**
     * 新增合同管理
     */
    @SaCheckPermission("contractInfoSale:contractInfoSale:add")
    @Log(title = "合同管理", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody QlContractInfoSaleBo bo) {
        return toAjax(iQlContractInfoSaleService.insertByBo(bo) ? 1 : 0);
    }

    /**
     * 修改合同管理
     */
    @SaCheckPermission("contractInfoSale:contractInfoSale:edit")
    @Log(title = "合同管理", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody QlContractInfoSaleBo bo) {
        return toAjax(iQlContractInfoSaleService.updateByBo(bo) ? 1 : 0);
    }

    /**
     * 删除合同管理
     *
     * @param ids 主键串
     */
    @SaCheckPermission("contractInfoSale:contractInfoSale:remove")
    @Log(title = "合同管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(iQlContractInfoSaleService.deleteWithValidByIds(Arrays.asList(ids), true) ? 1 : 0);
    }
}
