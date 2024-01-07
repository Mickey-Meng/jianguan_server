package com.ruoyi.web.controller.ql;

import java.util.List;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import com.ruoyi.common.constant.Constants;
import com.ruoyi.ql.domain.convert.QlFinInvoiceConvert;
import com.ruoyi.ql.domain.export.query.QlFinInvoiceReportQuery;
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
import com.ruoyi.ql.domain.vo.QlFinInvoiceVo;
import com.ruoyi.ql.domain.bo.QlFinInvoiceBo;
import com.ruoyi.ql.service.IQlFinInvoiceService;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 供应商开票
 *
 * @author ruoyi
 * @date 2022-12-11
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/finInvoice/finInvoice")
public class QlFinInvoiceController extends BaseController {

    private final IQlFinInvoiceService iQlFinInvoiceService;

    /**
     * 查询供应商开票列表
     */
    @SaCheckPermission("finInvoice:finInvoice:list")
    @GetMapping("/list")
    public TableDataInfo<QlFinInvoiceVo> list(QlFinInvoiceBo bo, PageQuery pageQuery) {
        return iQlFinInvoiceService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出供应商开票列表
     */
    @SaCheckPermission("finInvoice:finInvoice:export")
    @Log(title = "供应商开票", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(QlFinInvoiceReportQuery bo, HttpServletResponse response) {
        List<QlFinInvoiceVo> list = null;
        if(Constants.EXPORT_ALL.equals(bo.getExportAll())) {
            list = iQlFinInvoiceService.queryList(QlFinInvoiceConvert.INSTANCE.conver(bo));
        } else {
            PageQuery pageQuery = new PageQuery();
            pageQuery.setPageNum(bo.getPageNum());
            pageQuery.setPageSize(bo.getPageSize());
            TableDataInfo<QlFinInvoiceVo> qlFinInvoiceVoTableDataInfo = iQlFinInvoiceService.queryPageList(QlFinInvoiceConvert.INSTANCE.conver(bo), pageQuery);
            list = qlFinInvoiceVoTableDataInfo.getRows();
        }
        ExcelUtil.exportExcel(list, "供应商开票", QlFinInvoiceVo.class, response);
    }

    /**
     * 获取供应商开票详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("finInvoice:finInvoice:query")
    @GetMapping("/{id}")
    public R<QlFinInvoiceVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(iQlFinInvoiceService.queryById(id));
    }

    /**
     * 新增供应商开票
     */
    @SaCheckPermission("finInvoice:finInvoice:add")
    @Log(title = "供应商开票", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody QlFinInvoiceBo bo) {
        return toAjax(iQlFinInvoiceService.insertByBo(bo) ? 1 : 0);
    }

    /**
     * 修改供应商开票
     */
    @SaCheckPermission("finInvoice:finInvoice:edit")
    @Log(title = "供应商开票", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody QlFinInvoiceBo bo) {
        return toAjax(iQlFinInvoiceService.updateByBo(bo) ? 1 : 0);
    }

    /**
     * 删除供应商开票
     *
     * @param ids 主键串
     */
    @SaCheckPermission("finInvoice:finInvoice:remove")
    @Log(title = "供应商开票", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(iQlFinInvoiceService.deleteWithValidByIds(Arrays.asList(ids), true) ? 1 : 0);
    }
}
