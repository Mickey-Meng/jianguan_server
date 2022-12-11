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
import com.ruoyi.ql.domain.vo.QlShopGoodsSeedVo;
import com.ruoyi.ql.domain.bo.QlShopGoodsSeedBo;
import com.ruoyi.ql.service.IQlShopGoodsSeedService;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 商品库存信息
 *
 * @author ruoyi
 * @date 2022-12-11
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/shopGoodsSeed/shopGoodsSeed")
public class QlShopGoodsSeedController extends BaseController {

    private final IQlShopGoodsSeedService iQlShopGoodsSeedService;

    /**
     * 查询商品库存信息列表
     */
    @SaCheckPermission("shopGoodsSeed:shopGoodsSeed:list")
    @GetMapping("/list")
    public TableDataInfo<QlShopGoodsSeedVo> list(QlShopGoodsSeedBo bo, PageQuery pageQuery) {
        return iQlShopGoodsSeedService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出商品库存信息列表
     */
    @SaCheckPermission("shopGoodsSeed:shopGoodsSeed:export")
    @Log(title = "商品库存信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(QlShopGoodsSeedBo bo, HttpServletResponse response) {
        List<QlShopGoodsSeedVo> list = iQlShopGoodsSeedService.queryList(bo);
        ExcelUtil.exportExcel(list, "商品库存信息", QlShopGoodsSeedVo.class, response);
    }

    /**
     * 获取商品库存信息详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("shopGoodsSeed:shopGoodsSeed:query")
    @GetMapping("/{id}")
    public R<QlShopGoodsSeedVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(iQlShopGoodsSeedService.queryById(id));
    }

    /**
     * 新增商品库存信息
     */
    @SaCheckPermission("shopGoodsSeed:shopGoodsSeed:add")
    @Log(title = "商品库存信息", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody QlShopGoodsSeedBo bo) {
        return toAjax(iQlShopGoodsSeedService.insertByBo(bo) ? 1 : 0);
    }

    /**
     * 修改商品库存信息
     */
    @SaCheckPermission("shopGoodsSeed:shopGoodsSeed:edit")
    @Log(title = "商品库存信息", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody QlShopGoodsSeedBo bo) {
        return toAjax(iQlShopGoodsSeedService.updateByBo(bo) ? 1 : 0);
    }

    /**
     * 删除商品库存信息
     *
     * @param ids 主键串
     */
    @SaCheckPermission("shopGoodsSeed:shopGoodsSeed:remove")
    @Log(title = "商品库存信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(iQlShopGoodsSeedService.deleteWithValidByIds(Arrays.asList(ids), true) ? 1 : 0);
    }
}
