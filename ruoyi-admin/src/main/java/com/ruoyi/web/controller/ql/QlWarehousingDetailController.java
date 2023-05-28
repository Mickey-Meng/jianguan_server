package com.ruoyi.web.controller.ql;


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
import com.ruoyi.ql.domain.bo.QlWarehousingDetailBo;
import com.ruoyi.ql.domain.vo.QlWarehousingDetailVo;
import com.ruoyi.ql.service.IQlWarehousingDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.List;

/**
 * 入库单明细
 *
 * @author ruoyi
 * @date 2023-05-18
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/system/warehousingDetail")
public class QlWarehousingDetailController extends BaseController {

    private final IQlWarehousingDetailService iQlWarehousingDetailService;

    /**
     * 查询入库单明细列表
     */
    @SaCheckPermission("system:warehousingDetail:list")
    @GetMapping("/list")
    public TableDataInfo<QlWarehousingDetailVo> list(QlWarehousingDetailBo bo, PageQuery pageQuery) {
        return iQlWarehousingDetailService.queryPageList(bo, pageQuery);
    }

    /**
     * 分页查询入库单明细列表
     */
    @SaCheckPermission("system:warehousingDetail:list")
    @GetMapping("/page")

    public TableDataInfo<QlWarehousingDetailVo> page(QlWarehousingDetailBo bo, PageQuery pageQuery) {
        return iQlWarehousingDetailService.queryPageList(bo, pageQuery);
    }


    /**
     * 导出入库单明细列表
     */
    @SaCheckPermission("system:warehousingDetail:export")
    @Log(title = "入库单明细" , businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(QlWarehousingDetailBo bo, HttpServletResponse response) {
        List<QlWarehousingDetailVo> list = iQlWarehousingDetailService.queryList(bo);
        ExcelUtil.exportExcel(list, "入库单明细" , QlWarehousingDetailVo.class, response);
    }

    /**
     * 获取入库单明细详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("system:warehousingDetail:query")
    @GetMapping("/{id}")
    public R<QlWarehousingDetailVo> getInfo(@NotNull(message = "主键不能为空")
                                            @PathVariable Long id) {
        return R.ok(iQlWarehousingDetailService.queryById(id));
    }

    /**
     * 新增入库单明细
     */
    @SaCheckPermission("system:warehousingDetail:add")
    @Log(title = "入库单明细" , businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody QlWarehousingDetailBo bo) {
        return toAjax(iQlWarehousingDetailService.insertByBo(bo) ? 1 : 0);
    }

    /**
     * 修改入库单明细
     */
    @SaCheckPermission("system:warehousingDetail:edit")
    @Log(title = "入库单明细" , businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody QlWarehousingDetailBo bo) {
        return toAjax(iQlWarehousingDetailService.updateByBo(bo) ? 1 : 0);
    }

    /**
     * 删除入库单明细
     *
     * @param ids 主键串
     */
    @SaCheckPermission("system:warehousingDetail:remove")
    @Log(title = "入库单明细" , businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(iQlWarehousingDetailService.deleteWithValidByIds(Arrays.asList(ids), true) ? 1 : 0);
    }
}