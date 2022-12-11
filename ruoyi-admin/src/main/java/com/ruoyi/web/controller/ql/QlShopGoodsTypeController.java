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
import com.ruoyi.ql.domain.vo.QlShopGoodsTypeVo;
import com.ruoyi.ql.domain.bo.QlShopGoodsTypeBo;
import com.ruoyi.ql.service.IQlShopGoodsTypeService;

/**
 * 商品类别
 *
 * @author ruoyi
 * @date 2022-12-11
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/shopGoodsType/shopGoodsType")
public class QlShopGoodsTypeController extends BaseController {

    private final IQlShopGoodsTypeService iQlShopGoodsTypeService;

    /**
     * 查询商品类别列表
     */
    @SaCheckPermission("shopGoodsType:shopGoodsType:list")
    @GetMapping("/list")
    public R<List<QlShopGoodsTypeVo>> list(QlShopGoodsTypeBo bo) {
        List<QlShopGoodsTypeVo> list = iQlShopGoodsTypeService.queryList(bo);
        return R.ok(list);
    }

    /**
     * 导出商品类别列表
     */
    @SaCheckPermission("shopGoodsType:shopGoodsType:export")
    @Log(title = "商品类别", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(QlShopGoodsTypeBo bo, HttpServletResponse response) {
        List<QlShopGoodsTypeVo> list = iQlShopGoodsTypeService.queryList(bo);
        ExcelUtil.exportExcel(list, "商品类别", QlShopGoodsTypeVo.class, response);
    }

    /**
     * 获取商品类别详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("shopGoodsType:shopGoodsType:query")
    @GetMapping("/{id}")
    public R<QlShopGoodsTypeVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(iQlShopGoodsTypeService.queryById(id));
    }

    /**
     * 新增商品类别
     */
    @SaCheckPermission("shopGoodsType:shopGoodsType:add")
    @Log(title = "商品类别", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody QlShopGoodsTypeBo bo) {
        return toAjax(iQlShopGoodsTypeService.insertByBo(bo) ? 1 : 0);
    }

    /**
     * 修改商品类别
     */
    @SaCheckPermission("shopGoodsType:shopGoodsType:edit")
    @Log(title = "商品类别", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody QlShopGoodsTypeBo bo) {
        return toAjax(iQlShopGoodsTypeService.updateByBo(bo) ? 1 : 0);
    }

    /**
     * 删除商品类别
     *
     * @param ids 主键串
     */
    @SaCheckPermission("shopGoodsType:shopGoodsType:remove")
    @Log(title = "商品类别", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(iQlShopGoodsTypeService.deleteWithValidByIds(Arrays.asList(ids), true) ? 1 : 0);
    }
}
