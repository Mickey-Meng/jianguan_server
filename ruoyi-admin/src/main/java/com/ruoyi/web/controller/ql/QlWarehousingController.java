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
import com.ruoyi.ql.domain.bo.QlWarehousingBo;
import com.ruoyi.ql.domain.vo.QlWarehousingVo;
import com.ruoyi.ql.domain.vo.WarehousingVo;
import com.ruoyi.ql.mapstruct.OutboundAndWarehousingMapstruct;
import com.ruoyi.ql.service.IQlWarehousingService;
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
 * 入库管理
 *
 * @author ruoyi
 * @date 2022-12-11
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/warehousing/warehousing")
public class QlWarehousingController extends BaseController {

    private final IQlWarehousingService iQlWarehousingService;

    /**
     * 查询入库管理列表
     */
    @SaCheckPermission("warehousing:warehousing:list")
    @GetMapping("/list")
    public TableDataInfo<QlWarehousingVo> list(QlWarehousingBo bo, PageQuery pageQuery) {
        return iQlWarehousingService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出入库管理列表
     */
    @SaCheckPermission("warehousing:warehousing:export")
    @Log(title = "入库管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(QlWarehousingBo bo, HttpServletResponse response) {
        List<QlWarehousingVo> list = iQlWarehousingService.queryList(bo);
        ExcelUtil.exportExcel(list, "入库管理", QlWarehousingVo.class, response);
    }
    /**
     * 入库单 明细导入
     */
    @PostMapping("/import")
    public void uploadExcel(MultipartFile file) throws IOException {
        InputStream inputStream = file.getInputStream();
        ExcelReader reader = cn.hutool.poi.excel.ExcelUtil.getReader(inputStream);
        List<WarehousingVo> dmsOpsList = reader.read(2, 3, Integer.MAX_VALUE, WarehousingVo.class);
        List<QlWarehousingBo> entity = OutboundAndWarehousingMapstruct.INSTANCES.toBQlWarehousingBos(dmsOpsList);
        iQlWarehousingService.batchInsertBo(entity);
    }

    /**
     * 获取入库管理详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("warehousing:warehousing:query")
    @GetMapping("/{id}")
    public R<QlWarehousingVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(iQlWarehousingService.queryById(id));
    }

    /**
     * 新增入库管理
     */
    @SaCheckPermission("warehousing:warehousing:add")
    @Log(title = "入库管理", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody QlWarehousingBo bo) {
        return toAjax(iQlWarehousingService.insertByBo(bo) ? 1 : 0);
    }

    /**
     * 修改入库管理
     */
    @SaCheckPermission("warehousing:warehousing:edit")
    @Log(title = "入库管理", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody QlWarehousingBo bo) {
        return toAjax(iQlWarehousingService.updateByBo(bo) ? 1 : 0);
    }

    /**
     * 删除入库管理
     *
     * @param ids 主键串
     */
    @SaCheckPermission("warehousing:warehousing:remove")
    @Log(title = "入库管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(iQlWarehousingService.deleteWithValidByIds(Arrays.asList(ids), true) ? 1 : 0);
    }
}
