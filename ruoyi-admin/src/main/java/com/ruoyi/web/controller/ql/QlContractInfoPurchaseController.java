package com.ruoyi.web.controller.ql;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelWriter;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.annotation.RepeatSubmit;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.PageQuery;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.ql.domain.bo.QlBasisSupplierBo;
import com.ruoyi.ql.domain.bo.QlContractGoodsRelBo;
import com.ruoyi.ql.domain.bo.QlContractInfoPurchaseBo;
import com.ruoyi.ql.domain.bo.QlShopGoodsBo;
import com.ruoyi.ql.domain.convert.QlContractInfoPurchaseConvert;
import com.ruoyi.ql.domain.export.QlContractInfoPurchaseExport;
import com.ruoyi.ql.domain.export.QlContractInfoSaleExport;
import com.ruoyi.ql.domain.export.query.QlContractInfoPurchaseReportQuery;
import com.ruoyi.ql.domain.importvo.QlContractInfoPurchaseImport;
import com.ruoyi.ql.domain.vo.*;
import com.ruoyi.ql.mapstruct.QlContractInfoPurchaseMapstruct;
import com.ruoyi.ql.mapstruct.QlContractInfoSaleMapstruct;
import com.ruoyi.ql.service.IQlBasisSupplierService;
import com.ruoyi.ql.service.IQlContractInfoPurchaseService;
import com.ruoyi.ql.service.IQlShopGoodsService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 采购合同
 *
 * @author ruoyi
 * @date 2022-12-11
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/contractInfoPurchase/contractInfoPurchase")
public class QlContractInfoPurchaseController extends BaseController {

    private final IQlContractInfoPurchaseService iQlContractInfoPurchaseService;

    private final IQlBasisSupplierService iQlBasisSupplierService;

    private final IQlShopGoodsService iQlShopGoodsService;


    /**
     * 查询采购合同 列表
     */
    @SaCheckPermission("contractInfoPurchase:contractInfoPurchase:list")
    @GetMapping("/list")
    public TableDataInfo<QlContractInfoPurchaseVo> list(QlContractInfoPurchaseBo bo, PageQuery pageQuery) {
        return iQlContractInfoPurchaseService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出采购合同 列表
     */
    @SaCheckPermission("contractInfoPurchase:contractInfoPurchase:export")
    @Log(title = "采购合同 ", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(QlContractInfoPurchaseReportQuery bo, HttpServletResponse response) throws IOException {
        List<QlContractInfoPurchaseVo> qlContractInfoPurchaseVos = null;
        if(Constants.EXPORT_ALL.equals(bo.getExportAll())) {
            qlContractInfoPurchaseVos = iQlContractInfoPurchaseService.queryList(QlContractInfoPurchaseConvert.INSTANCE.conver(bo));
        } else {
            PageQuery pageQuery = new PageQuery();
            pageQuery.setPageNum(bo.getPageNum());
            pageQuery.setPageSize(bo.getPageSize());
            TableDataInfo<QlContractInfoPurchaseVo> qlContractInfoPurchaseVoTableDataInfo = iQlContractInfoPurchaseService.queryPageList(QlContractInfoPurchaseConvert.INSTANCE.conver(bo), pageQuery);
            qlContractInfoPurchaseVos = qlContractInfoPurchaseVoTableDataInfo.getRows();
        }

        List<QlContractInfoPurchaseExport> qlContractInfoPurchaseExports = new ArrayList<>();
        for (QlContractInfoPurchaseVo qlContractInfoPurchaseVo : qlContractInfoPurchaseVos) {
            List<QlContractGoodsRelVo> qlContractGoodsRelVos = qlContractInfoPurchaseVo.getContractGoodsRels();
            for (QlContractGoodsRelVo qlContractGoodsRelVo : qlContractGoodsRelVos) {
                QlContractInfoPurchaseExport qlContractInfoPurchaseExport = QlContractInfoPurchaseMapstruct.INSTANCES.convert(qlContractInfoPurchaseVo);
                qlContractInfoPurchaseExport.setGoodsName(qlContractGoodsRelVo.getGoodsName());
                qlContractInfoPurchaseExport.setPrice(qlContractGoodsRelVo.getPrice());
                qlContractInfoPurchaseExport.setGoodsNum(qlContractGoodsRelVo.getGoodsNum());
                qlContractInfoPurchaseExports.add(qlContractInfoPurchaseExport);
            }
        }

        // 通过工具类创建writer
        ExcelWriter writer = cn.hutool.poi.excel.ExcelUtil.getWriter(true);
        // 合并单元格后的标题行
        writer.merge(14, "采购合同");

        List<LinkedHashMap<String, String>> headers = new LinkedList<>();

        LinkedHashMap<String, String> columnDesc = new LinkedHashMap<>();
        columnDesc.put("contractCode","合同编码");
        columnDesc.put("contractName","合同名称");
        columnDesc.put("supplierName","供应商名称");
        columnDesc.put("accountPeriod","账期");
        columnDesc.put("purchaser","采购人员");
        columnDesc.put("startDate","开始时间");
        columnDesc.put("endDate","结束时间");
        columnDesc.put("contractStatus","合同是否签订");
        columnDesc.put("contactDate","合同签订时间");
        columnDesc.put("rate","税率");
        columnDesc.put("remark","备注");
        columnDesc.put("goodsName","产品名称");
        columnDesc.put("price","单价");
        columnDesc.put("goodsNum","数量");
        columnDesc.put("totalAmountDollar","美元总额");

        headers.add(columnDesc);

        LinkedHashMap<String, String> columnNames = new LinkedHashMap<>();
        columnNames.put("contractCode","contractCode");
        columnNames.put("contractName","contractName");
        columnNames.put("supplierName","supplierName");
        columnNames.put("accountPeriod","accountPeriod");
        columnNames.put("purchaser","purchaser");
        columnNames.put("startDate","startDate");
        columnNames.put("endDate","endDate");
        columnNames.put("contractStatus","contractStatus");
        columnNames.put("contactDate","contactDate");
        columnNames.put("rate","rate");
        columnNames.put("remark","remark");
        columnNames.put("goodsName","goodsName");
        columnNames.put("price","price");
        columnNames.put("goodsNum","goodsNum");
        columnNames.put("totalAmountDollar","totalAmountDollar");
        headers.add(columnNames);

        writer.write(headers, false);
        // 一次性写出内容
        writer.write(qlContractInfoPurchaseExports, false);
        // 此处的response.setContentType 和教程里的不同
        response.setContentType("application/octet-stream");
        // filename就是表格的名字，这个无所谓，反正前端还会重命名
        response.setHeader("Content-Disposition","attachment;filename=warehousing-"+ RandomUtil.randomNumbers(5) +".xlsx");
        // 输出流
        ServletOutputStream servletOutputStream = response.getOutputStream();

        writer.flush(servletOutputStream);
        // 关闭writer，释放内存
        writer.close();
        // 关闭输出Servlet流
        IoUtil.close(servletOutputStream);

    }


    /**
     * 获取采购合同 详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("contractInfoPurchase:contractInfoPurchase:query")
    @GetMapping("/{id}")
    public R<QlContractInfoPurchaseVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        R<QlContractInfoPurchaseVo> response = R.ok(iQlContractInfoPurchaseService.queryById(id));

        if(ObjectUtil.isNull(response) || ObjectUtil.isNull(response.getData())) {
            return response;
        }
        List<QlContractGoodsRelVo> contractGoodsRels = response.getData().getContractGoodsRels();
        if(ObjectUtil.isNull(contractGoodsRels) || CollUtil.isEmpty(contractGoodsRels)) {
            return response;
        }
        for (QlContractGoodsRelVo contractGoodsRel : contractGoodsRels) {
            if (ObjectUtil.isNotNull(contractGoodsRel.getTotalAmountDollar())) {
                continue;
            }
            contractGoodsRel.setTotalAmountDollar(new BigDecimal("0"));
        }

        return response;
    }

    /**
     * 新增采购合同
     */
    @SaCheckPermission("contractInfoPurchase:contractInfoPurchase:add")
    @Log(title = "采购合同 ", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody QlContractInfoPurchaseBo bo) {
        return toAjax(iQlContractInfoPurchaseService.insertByBo(bo) ? 1 : 0);
    }

    /**
     * 修改采购合同
     */
    @SaCheckPermission("contractInfoPurchase:contractInfoPurchase:edit")
    @Log(title = "采购合同 ", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody QlContractInfoPurchaseBo bo) {
        return toAjax(iQlContractInfoPurchaseService.updateByBo(bo) ? 1 : 0);
    }

    /**
     * 删除采购合同
     *
     * @param ids 主键串
     */
    @SaCheckPermission("contractInfoPurchase:contractInfoPurchase:remove")
    @Log(title = "采购合同 ", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(iQlContractInfoPurchaseService.deleteWithValidByIds(Arrays.asList(ids), true) ? 1 : 0);
    }

    /**
     * 入库单 明细导入
     */
    @PostMapping("/import")
    public void uploadExcel(MultipartFile file) throws IOException {
        InputStream inputStream = file.getInputStream();
        ExcelReader reader = cn.hutool.poi.excel.ExcelUtil.getReader(inputStream);
        List<QlContractInfoPurchaseImport> qlContractInfoPurchaseImports = reader.read(2, 3, Integer.MAX_VALUE, QlContractInfoPurchaseImport.class);
        checkImport(qlContractInfoPurchaseImports);
        List<QlContractInfoPurchaseBo> qlContractInfoPurchaseBos = importToBo(qlContractInfoPurchaseImports);
        iQlContractInfoPurchaseService.batchInsertBo(qlContractInfoPurchaseBos);
    }

    private void checkImport(List<QlContractInfoPurchaseImport> qlContractInfoPurchaseImports) {

    }

    private List<QlContractInfoPurchaseBo> importToBo(List<QlContractInfoPurchaseImport> qlContractInfoPurchaseImports) {
        List<String> supplierNames = qlContractInfoPurchaseImports.stream().map(QlContractInfoPurchaseImport::getSupplierName).collect(Collectors.toList());
        QlBasisSupplierBo qlBasisSupplierBo = new QlBasisSupplierBo();
        qlBasisSupplierBo.setSupplierNames(supplierNames);
        List<QlBasisSupplierVo> qlBasisSupplierVos = iQlBasisSupplierService.queryList(qlBasisSupplierBo);
        Map<String, QlBasisSupplierVo> qlBasisSupplierVoMap = qlBasisSupplierVos.stream().collect(Collectors.toMap(QlBasisSupplierVo::getSupplierName, qlBasisSupplierVo -> qlBasisSupplierVo));

        List<String> goodsNames = qlContractInfoPurchaseImports.stream().map(QlContractInfoPurchaseImport::getGoodsName).collect(Collectors.toList());
        QlShopGoodsBo qlShopGoodsBo = new QlShopGoodsBo();
        qlShopGoodsBo.setGoodsNames(goodsNames);
        List<QlShopGoodsVo> qlShopGoodsVos = iQlShopGoodsService.queryList(qlShopGoodsBo);
        Map<String, QlShopGoodsVo> qlShopGoodsVoMap = qlShopGoodsVos.stream().collect(Collectors.toMap(QlShopGoodsVo::getGoodsName, qlShopGoodsVo -> qlShopGoodsVo, (oldObj, newObj)->newObj));

        List<QlContractInfoPurchaseBo> qlContractInfoPurchaseBos = new ArrayList<>();
        Map<String, List<QlContractInfoPurchaseImport>> qlContractInfoPurchaseImportGroup = qlContractInfoPurchaseImports.stream().collect(Collectors.groupingBy(QlContractInfoPurchaseImport::getContractCode));
        qlContractInfoPurchaseImportGroup.forEach((contractCode, qlContractInfoPurchaseImportList) -> {
            QlContractInfoPurchaseImport qlContractInfoPurchaseImport = qlContractInfoPurchaseImportList.get(0);
            QlContractInfoPurchaseBo qlContractInfoPurchaseBo = QlContractInfoPurchaseMapstruct.INSTANCES.convert(qlContractInfoPurchaseImport);
            // 查询供应商
            QlBasisSupplierVo qlBasisSupplierVo = qlBasisSupplierVoMap.get(qlContractInfoPurchaseImport.getSupplierName());
            qlContractInfoPurchaseBo.setSupplierId(String.valueOf(qlBasisSupplierVo.getId()));
            qlContractInfoPurchaseBo.setContactPerson(qlBasisSupplierVo.getContactPerson());
            qlContractInfoPurchaseBo.setMobilePhone(qlBasisSupplierVo.getMobilePhone());
            qlContractInfoPurchaseBo.setContractStatus(qlContractInfoPurchaseImport.getContractStatus().equals("是")?"Y":"N");

            BigDecimal totalAmount = qlContractInfoPurchaseImportList.stream().map(contractInfoPurchaseImport -> contractInfoPurchaseImport.getPrice().multiply(new BigDecimal(contractInfoPurchaseImport.getGoodsNum()))).reduce(BigDecimal.ZERO, BigDecimal::add);
            qlContractInfoPurchaseBo.setAmount(totalAmount);
            List<QlContractGoodsRelBo> contractGoodsRels = new ArrayList<>();
            for (QlContractInfoPurchaseImport contractInfoPurchaseImport : qlContractInfoPurchaseImportList) {
                // 查询产品
                QlShopGoodsVo qlShopGoodsVo = qlShopGoodsVoMap.get(qlContractInfoPurchaseImport.getGoodsName());
                QlContractGoodsRelBo qlContractGoodsRelBo = new QlContractGoodsRelBo();
                qlContractGoodsRelBo.setGoodsId(qlShopGoodsVo.getId());
                qlContractGoodsRelBo.setGoodsName(qlShopGoodsVo.getGoodsName());
                qlContractGoodsRelBo.setGoodsNum(contractInfoPurchaseImport.getGoodsNum());
                qlContractGoodsRelBo.setPrice(contractInfoPurchaseImport.getPrice());
                qlContractGoodsRelBo.setContractType("purchase");
                qlContractGoodsRelBo.setGoodsUnit(qlShopGoodsVo.getGoodsUnit());
                qlContractGoodsRelBo.setTotalAmountDollar(contractInfoPurchaseImport.getTotalAmountDollar());
                qlContractGoodsRelBo.setRemark(contractInfoPurchaseImport.getRemark());
                contractGoodsRels.add(qlContractGoodsRelBo);
            }
            qlContractInfoPurchaseBo.setContractGoodsRels(contractGoodsRels);
            qlContractInfoPurchaseBos.add(qlContractInfoPurchaseBo);
        });

        return qlContractInfoPurchaseBos;
    }

}
