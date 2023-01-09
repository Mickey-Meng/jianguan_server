package com.ruoyi.web.controller.ql;

import java.util.List;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import com.ruoyi.ql.domain.vo.TreeVo;
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
import com.ruoyi.ql.domain.vo.QlShopGoodsVo;
import com.ruoyi.ql.domain.bo.QlShopGoodsBo;
import com.ruoyi.ql.service.IQlShopGoodsService;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 商品信息
 *
 * @author ruoyi
 * @date 2022-12-11
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/shopGoods/shopGoods")
public class QlShopGoodsController extends BaseController {

    private final IQlShopGoodsService iQlShopGoodsService;

    /**
     * 查询商品信息列表
     */
    @SaCheckPermission("shopGoods:shopGoods:list")
    @GetMapping("/list")
    public TableDataInfo<QlShopGoodsVo> list(QlShopGoodsBo bo, PageQuery pageQuery) {
        return iQlShopGoodsService.queryPageList(bo, pageQuery);
    }

    /**
     * 商品类别树
     */
    @SaCheckPermission("shopGoods:shopGoods:list")
    @GetMapping("/goodsTree")
    public List<TreeVo> goodsTree() {
        return iQlShopGoodsService.goodsTree();
    }

    /**
     * 导出商品信息列表
     */
    @SaCheckPermission("shopGoods:shopGoods:export")
    @Log(title = "商品信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(QlShopGoodsBo bo, HttpServletResponse response) {
        List<QlShopGoodsVo> list = iQlShopGoodsService.queryList(bo);
        ExcelUtil.exportExcel(list, "商品信息", QlShopGoodsVo.class, response);
    }

    /**
     * 获取商品信息详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("shopGoods:shopGoods:query")
    @GetMapping("/{id}")
    public R<QlShopGoodsVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(iQlShopGoodsService.queryById(id));
    }

    /**
     * 新增商品信息
     */
    @SaCheckPermission("shopGoods:shopGoods:add")
    @Log(title = "商品信息", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody QlShopGoodsBo bo) {
        return toAjax(iQlShopGoodsService.insertByBo(bo) ? 1 : 0);
    }

    /**
     * 修改商品信息
     */
    @SaCheckPermission("shopGoods:shopGoods:edit")
    @Log(title = "商品信息", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody QlShopGoodsBo bo) {
        return toAjax(iQlShopGoodsService.updateByBo(bo) ? 1 : 0);
    }

    /**
     * 删除商品信息
     *
     * @param ids 主键串
     */
    @SaCheckPermission("shopGoods:shopGoods:remove")
    @Log(title = "商品信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(iQlShopGoodsService.deleteWithValidByIds(Arrays.asList(ids), true) ? 1 : 0);
    }
}
