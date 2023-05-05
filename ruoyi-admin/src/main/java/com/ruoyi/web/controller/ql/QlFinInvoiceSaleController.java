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
import com.ruoyi.ql.domain.vo.QlFinInvoiceSaleVo;
import com.ruoyi.ql.domain.bo.QlFinInvoiceSaleBo;
import com.ruoyi.ql.service.IQlFinInvoiceSaleService;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 销售开票
 *
 * @author ruoyi
 * @date 2023-05-05
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/ql/finInvoiceSale")
public class QlFinInvoiceSaleController extends BaseController {

    private final IQlFinInvoiceSaleService iQlFinInvoiceSaleService;

/**
 * 查询销售开票列表
 */
@SaCheckPermission("ql:finInvoiceSale:list")
@GetMapping("/list")
    public TableDataInfo<QlFinInvoiceSaleVo> list(QlFinInvoiceSaleBo bo, PageQuery pageQuery) {
        return iQlFinInvoiceSaleService.queryPageList(bo, pageQuery);
    }

    /**
     * 分页查询销售开票列表
     */
    @SaCheckPermission("ql:finInvoiceSale:list")
    @GetMapping("/page")

    public TableDataInfo<QlFinInvoiceSaleVo> page(QlFinInvoiceSaleBo bo, PageQuery pageQuery) {
        return iQlFinInvoiceSaleService.queryPageList(bo, pageQuery);
    }


    /**
     * 导出销售开票列表
     */
    @SaCheckPermission("ql:finInvoiceSale:export")
    @Log(title = "销售开票" , businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(QlFinInvoiceSaleBo bo, HttpServletResponse response) {
        List<QlFinInvoiceSaleVo> list = iQlFinInvoiceSaleService.queryList(bo);
        ExcelUtil.exportExcel(list, "销售开票" , QlFinInvoiceSaleVo.class, response);
    }

    /**
     * 获取销售开票详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("ql:finInvoiceSale:query")
    @GetMapping("/{id}")
    public R<QlFinInvoiceSaleVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(iQlFinInvoiceSaleService.queryById(id));
    }

    /**
     * 新增销售开票
     */
    @SaCheckPermission("ql:finInvoiceSale:add")
    @Log(title = "销售开票" , businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody QlFinInvoiceSaleBo bo) {
        return toAjax(iQlFinInvoiceSaleService.insertByBo(bo) ? 1 : 0);
    }

    /**
     * 修改销售开票
     */
    @SaCheckPermission("ql:finInvoiceSale:edit")
    @Log(title = "销售开票" , businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody QlFinInvoiceSaleBo bo) {
        return toAjax(iQlFinInvoiceSaleService.updateByBo(bo) ? 1 : 0);
    }

    /**
     * 删除销售开票
     *
     * @param ids 主键串
     */
    @SaCheckPermission("ql:finInvoiceSale:remove")
    @Log(title = "销售开票" , businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(iQlFinInvoiceSaleService.deleteWithValidByIds(Arrays.asList(ids), true) ? 1 : 0);
    }
}
