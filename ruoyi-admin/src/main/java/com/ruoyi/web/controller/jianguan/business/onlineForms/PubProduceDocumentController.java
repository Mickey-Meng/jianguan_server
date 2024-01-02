package com.ruoyi.jianguan.business.onlineForms.controller;

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
import com.ruoyi.jianguan.business.onlineForms.domain.vo.PubProduceDocumentVo;
import com.ruoyi.jianguan.business.onlineForms.domain.bo.PubProduceDocumentBo;
import com.ruoyi.jianguan.business.onlineForms.service.IPubProduceDocumentService;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 工序附件信息
 *
 * @author mickey
 * @date 2024-01-02
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/produce/produceDocument")
public class PubProduceDocumentController extends BaseController {

    private final IPubProduceDocumentService iPubProduceDocumentService;

/**
 * 查询工序附件信息列表
 */
// @SaCheckPermission("produce:produceDocument:list")
@GetMapping("/list")
    public TableDataInfo<PubProduceDocumentVo> list(PubProduceDocumentBo bo, PageQuery pageQuery) {
        return iPubProduceDocumentService.queryPageList(bo, pageQuery);
    }

    /**
     * 分页查询工序附件信息列表
     */
    @SaCheckPermission("produce:produceDocument:list")
    @GetMapping("/page")

    public TableDataInfo<PubProduceDocumentVo> page(PubProduceDocumentBo bo, PageQuery pageQuery) {
        return iPubProduceDocumentService.queryPageList(bo, pageQuery);
    }


    /**
     * 导出工序附件信息列表
     */
    @SaCheckPermission("produce:produceDocument:export")
    @Log(title = "工序附件信息" , businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(PubProduceDocumentBo bo, HttpServletResponse response) {
        List<PubProduceDocumentVo> list = iPubProduceDocumentService.queryList(bo);
        ExcelUtil.exportExcel(list, "工序附件信息" , PubProduceDocumentVo.class, response);
    }

    /**
     * 获取工序附件信息详细信息
     *
     * @param id 主键
     */
    // @SaCheckPermission("produce:produceDocument:query")
    @GetMapping("/{id}")
    public R<PubProduceDocumentVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(iPubProduceDocumentService.queryById(id));
    }

    /**
     * 新增工序附件信息
     */
    @SaCheckPermission("produce:produceDocument:add")
    @Log(title = "工序附件信息" , businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody PubProduceDocumentBo bo) {
        return toAjax(iPubProduceDocumentService.insertByBo(bo) ? 1 : 0);
    }

    /**
     * 修改工序附件信息
     */
    // @SaCheckPermission("produce:produceDocument:edit")
    @Log(title = "工序附件信息" , businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody PubProduceDocumentBo bo) {
        return toAjax(iPubProduceDocumentService.updateByBo(bo) ? 1 : 0);
    }

    /**
     * 删除工序附件信息
     *
     * @param ids 主键串
     */
    @SaCheckPermission("produce:produceDocument:remove")
    @Log(title = "工序附件信息" , businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(iPubProduceDocumentService.deleteWithValidByIds(Arrays.asList(ids), true) ? 1 : 0);
    }
}
