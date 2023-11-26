package com.ruoyi.web.controller.ql;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelWriter;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.ql.domain.QlBasisSupplier;
import com.ruoyi.ql.domain.bo.QlBasisSupplierBo;
import com.ruoyi.ql.domain.bo.QlOutboundBo;
import com.ruoyi.ql.domain.bo.QlWarehousingDetailBo;
import com.ruoyi.ql.domain.export.OutboundExportVo;
import com.ruoyi.ql.domain.export.QlShopGoodsExport;
import com.ruoyi.ql.domain.importvo.OutboundImportVo;
import com.ruoyi.ql.domain.importvo.QlShopGoodsImport;
import com.ruoyi.ql.domain.vo.*;
import com.ruoyi.ql.mapstruct.OutboundAndWarehousingMapstruct;
import com.ruoyi.ql.mapstruct.QlWarehousingDetailMapstruct;
import com.ruoyi.ql.service.IQlBasisSupplierService;
import lombok.RequiredArgsConstructor;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.*;
import cn.dev33.satoken.annotation.SaCheckPermission;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.ruoyi.ql.domain.bo.QlShopGoodsBo;
import com.ruoyi.ql.service.IQlShopGoodsService;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

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
     * 出库单 明细导入
     */
    @PostMapping("/import")
    public void uploadExcel(MultipartFile file) throws IOException {
        InputStream inputStream = file.getInputStream();
        ExcelReader reader = cn.hutool.poi.excel.ExcelUtil.getReader(inputStream);
        List<QlShopGoodsImport> shopGoodsImports = reader.read(2, 3, Integer.MAX_VALUE, QlShopGoodsImport.class);
        List<QlShopGoodsBo> qlShopGoodsBos = importToBo(shopGoodsImports);
        if (CollUtil.isEmpty(qlShopGoodsBos)) {
            return;
        }
        for (QlShopGoodsBo qlShopGoodsBo : qlShopGoodsBos) {
            iQlShopGoodsService.insertByBo(qlShopGoodsBo);
        }
    }

    private final IQlBasisSupplierService iQlBasisSupplierService;

    /**
     * @param qlShopGoodsImports
     * @return
     */
    private List<QlShopGoodsBo> importToBo(List<QlShopGoodsImport> qlShopGoodsImports) {
        if (CollUtil.isEmpty(qlShopGoodsImports)) {
            return new ArrayList<>();
        }

        List<String> supplierNames = qlShopGoodsImports.stream().map(QlShopGoodsImport::getSupplierName).collect(Collectors.toList());
        QlBasisSupplierBo qlBasisSupplierBo = new QlBasisSupplierBo();
        qlBasisSupplierBo.setSupplierNames(supplierNames);
        List<QlBasisSupplierVo> qlBasisSupplierVos = iQlBasisSupplierService.queryList(qlBasisSupplierBo);
        Map<String, Long> supplierMap = qlBasisSupplierVos.stream().collect(Collectors.toMap(QlBasisSupplierVo::getSupplierName, QlBasisSupplierVo::getId));

        List<QlShopGoodsBo> qlShopGoodsBos = new ArrayList<>();
        for (QlShopGoodsImport qlShopGoodsImport : qlShopGoodsImports) {
            QlShopGoodsBo qlShopGoodsBo = new QlShopGoodsBo();
            Long supplierId = supplierMap.get(qlShopGoodsImport.getSupplierName());
            BeanUtil.copyProperties(qlShopGoodsImport, qlShopGoodsBo);
            qlShopGoodsBo.setSupplierId(supplierId);
            qlShopGoodsBos.add(qlShopGoodsBo);
        }
        return qlShopGoodsBos;
    }

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

        List<QlShopGoodsVo> qlShopGoodsVos = iQlShopGoodsService.queryList(bo);
        List<QlShopGoodsExport> shopGoodsExports = new ArrayList<>();
        for (QlShopGoodsVo qlShopGoodsVo : qlShopGoodsVos) {
            QlShopGoodsExport qlShopGoodsExport = new QlShopGoodsExport();
            BeanUtil.copyProperties(qlShopGoodsVo, qlShopGoodsExport);
            shopGoodsExports.add(qlShopGoodsExport);
        }

        // 通过工具类创建writer
        ExcelWriter writer = cn.hutool.poi.excel.ExcelUtil.getWriter(true);
        // 合并单元格后的标题行
        writer.merge(8, "商品名称");

        List<LinkedHashMap<String, String>> headers = new LinkedList<>();

        LinkedHashMap<String, String> columnDesc = new LinkedHashMap<>();
        columnDesc.put("goodsCode","产品编码");
        columnDesc.put("goodsName","产品名称");
        columnDesc.put("goodsBrand","品牌");
        columnDesc.put("supplierName","供应商名称");
        columnDesc.put("goodsSearchstandard","规格");
        columnDesc.put("goodsUnit","单位");
        columnDesc.put("costPrice","成本价");
        columnDesc.put("safetyStock","预警库存");
        headers.add(columnDesc);

        LinkedHashMap<String, String> columnNames = new LinkedHashMap<>();
        columnNames.put("goodsCode","goodsCode");
        columnNames.put("goodsName","goodsName");
        columnNames.put("goodsBrand","goodsBrand");
        columnNames.put("supplierName","supplierName");
        columnNames.put("goodsSearchstandard","goodsSearchstandard");
        columnNames.put("goodsUnit","goodsUnit");
        columnNames.put("costPrice","costPrice");
        columnNames.put("safetyStock","safetyStock");

        headers.add(columnNames);

        writer.write(headers, false);
        // 一次性写出内容
        writer.write(shopGoodsExports, false);
        // 此处的response.setContentType 和教程里的不同
        response.setContentType("application/octet-stream");
        // filename就是表格的名字，这个无所谓，反正前端还会重命名
        response.setHeader("Content-Disposition","attachment;filename=warehousing-"+ RandomUtil.randomNumbers(5) +".xlsx");
        // 输出流
        ServletOutputStream servletOutputStream = null;
        try {
            servletOutputStream = response.getOutputStream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        writer.flush(servletOutputStream);
        // 关闭writer，释放内存
        writer.close();
        // 关闭输出Servlet流
        IoUtil.close(servletOutputStream);


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
