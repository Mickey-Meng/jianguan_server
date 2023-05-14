package com.ruoyi.web.controller.ql;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.hutool.poi.excel.ExcelReader;
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
import com.ruoyi.ql.domain.bo.QlOutboundBo;
import com.ruoyi.ql.domain.vo.OutboundVo;
import com.ruoyi.ql.domain.vo.QlOutboundVo;
import com.ruoyi.ql.mapstruct.OutboundAndWarehousingMapstruct;
import com.ruoyi.ql.service.IQlOutboundService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

/**
 * 出库管理
 *
 * @author ruoyi
 * @date 2023-05-05
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/ql/outbound")
public class QlOutboundController extends BaseController {

    private final IQlOutboundService iQlOutboundService;

/**
 * 查询出库管理列表
 */
@SaCheckPermission("ql:outbound:list")
@GetMapping("/list")
    public TableDataInfo<QlOutboundVo> list(QlOutboundBo bo, PageQuery pageQuery) {
        return iQlOutboundService.queryPageList(bo, pageQuery);
    }

    /**
     * 分页查询出库管理列表
     */
    @SaCheckPermission("ql:outbound:list")
    @GetMapping("/page")

    public TableDataInfo<QlOutboundVo> page(QlOutboundBo bo, PageQuery pageQuery) {
        return iQlOutboundService.queryPageList(bo, pageQuery);
    }

    /**
     * 出库单 明细导入
     */
    @PostMapping("/import")
    public void uploadExcel(MultipartFile file) throws IOException {
        InputStream inputStream = file.getInputStream();
        ExcelReader reader = cn.hutool.poi.excel.ExcelUtil.getReader(inputStream);
        List<OutboundVo> dmsOpsList = reader.read(2, 3, Integer.MAX_VALUE, OutboundVo.class);
        List<QlOutboundBo> entity = OutboundAndWarehousingMapstruct.INSTANCES.toBos(dmsOpsList);
        iQlOutboundService.batchInsertBo(entity);
    }

    /**
     * 导出出库管理列表
     */
    @SaCheckPermission("ql:outbound:export")
    @Log(title = "出库管理" , businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(QlOutboundBo bo, HttpServletResponse response) {
        List<QlOutboundVo> list = iQlOutboundService.queryList(bo);
        ExcelUtil.exportExcel(list, "出库管理" , QlOutboundVo.class, response);
    }

    /**
     * 获取出库管理详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("ql:outbound:query")
    @GetMapping("/{id}")
    public R<QlOutboundVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(iQlOutboundService.queryById(id));
    }

    /**
     * 新增出库管理
     */
    @SaCheckPermission("ql:outbound:add")
    @Log(title = "出库管理" , businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody QlOutboundBo bo) {
        return toAjax(iQlOutboundService.insertByBo(bo) ? 1 : 0);
    }

    /**
     * 修改出库管理
     */
    @SaCheckPermission("ql:outbound:edit")
    @Log(title = "出库管理" , businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody QlOutboundBo bo) {
        return toAjax(iQlOutboundService.updateByBo(bo) ? 1 : 0);
    }

    /**
     * 删除出库管理
     *
     * @param ids 主键串
     */
    @SaCheckPermission("ql:outbound:remove")
    @Log(title = "出库管理" , businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(iQlOutboundService.deleteWithValidByIds(Arrays.asList(ids), true) ? 1 : 0);
    }
}
