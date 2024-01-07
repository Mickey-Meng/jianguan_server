package com.ruoyi.web.controller.ql;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelWriter;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.ql.domain.bo.*;
import com.ruoyi.ql.domain.convert.QlContractInfoSaleConvert;
import com.ruoyi.ql.domain.export.QlContractInfoSaleExport;
import com.ruoyi.ql.domain.export.query.QlContractInfoSaleReportQuery;
import com.ruoyi.ql.domain.importvo.QlContractInfoSaleImport;
import com.ruoyi.ql.domain.vo.*;
import com.ruoyi.ql.mapstruct.QlContractInfoSaleMapstruct;
import com.ruoyi.ql.service.IQlBasisCustomerService;
import com.ruoyi.ql.service.IQlShopGoodsService;
import lombok.RequiredArgsConstructor;

import javax.servlet.ServletOutputStream;
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
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.ql.service.IQlContractInfoSaleService;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 合同管理
 *
 * @author ruoyi
 * @date 2022-12-11
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/contractInfoSale/contractInfoSale")
public class QlContractInfoSaleController extends BaseController {

    private final IQlContractInfoSaleService iQlContractInfoSaleService;

    private final IQlBasisCustomerService iQlBasisCustomerService;

    private final IQlShopGoodsService iQlShopGoodsService;

    /**
     * 查询合同管理列表
     */
    @SaCheckPermission("contractInfoSale:contractInfoSale:list")
    @GetMapping("/list")
    public TableDataInfo<QlContractInfoSaleVo> list(QlContractInfoSaleBo bo, PageQuery pageQuery) {
        return iQlContractInfoSaleService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出合同管理列表
     */
    @SaCheckPermission("contractInfoSale:contractInfoSale:export")
    @Log(title = "合同管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(QlContractInfoSaleReportQuery bo, HttpServletResponse response) throws IOException {
        List<QlContractInfoSaleVo> qlContractInfoSaleVos = null;
        if(Constants.EXPORT_ALL.equals(bo.getExportAll())) {
            qlContractInfoSaleVos = iQlContractInfoSaleService.queryList(QlContractInfoSaleConvert.INSTANCE.conver(bo));
        } else {
            PageQuery pageQuery = new PageQuery();
            pageQuery.setPageNum(bo.getPageNum());
            pageQuery.setPageSize(bo.getPageSize());
            TableDataInfo<QlContractInfoSaleVo> qlContractInfoSaleVoTableDataInfo = iQlContractInfoSaleService.queryPageList(QlContractInfoSaleConvert.INSTANCE.conver(bo), pageQuery);
            qlContractInfoSaleVos = qlContractInfoSaleVoTableDataInfo.getRows();
        }

        List<QlContractInfoSaleExport> qlContractInfoSaleExports = new ArrayList<>();
        for (QlContractInfoSaleVo qlContractInfoSaleVo : qlContractInfoSaleVos) {
            List<QlContractGoodsRelVo> qlContractGoodsRelVos = qlContractInfoSaleVo.getContractGoodsRels();
            for (QlContractGoodsRelVo qlContractGoodsRelVo : qlContractGoodsRelVos) {
                QlContractInfoSaleExport qlContractInfoSaleExport = QlContractInfoSaleMapstruct.INSTANCES.convert(qlContractInfoSaleVo);
                qlContractInfoSaleExport.setGoodsName(qlContractGoodsRelVo.getGoodsName());
                qlContractInfoSaleExport.setPrice(qlContractGoodsRelVo.getPrice());
                qlContractInfoSaleExport.setGoodsNum(qlContractGoodsRelVo.getGoodsNum());
                qlContractInfoSaleExports.add(qlContractInfoSaleExport);
            }
        }

        // 通过工具类创建writer
        ExcelWriter writer = cn.hutool.poi.excel.ExcelUtil.getWriter(true);
        // 合并单元格后的标题行
        writer.merge(16, "销售合同");

        List<LinkedHashMap<String, String>> headers = new LinkedList<>();

        LinkedHashMap<String, String> columnDesc = new LinkedHashMap<>();
        columnDesc.put("contractCode","合同编码");
        columnDesc.put("contractName","合同名称");
        columnDesc.put("projectName","项目名称");
        columnDesc.put("customerName","客户名称");
        columnDesc.put("retentionAmount","质保金");
        columnDesc.put("contactDate","合同签订时间");
        columnDesc.put("retentionDate","质保金到期时间");
        columnDesc.put("rate","税率");
        columnDesc.put("area","发货地");
        columnDesc.put("purchaser","销售人员");
        columnDesc.put("startDate","开始时间");
        columnDesc.put("endDate","结束时间");
        columnDesc.put("contractStatus","合同是否签订");
        columnDesc.put("accountPeriod","账期");
        columnDesc.put("goodsName","产品名称");
        columnDesc.put("price","单价");
        columnDesc.put("goodsNum","数量");

        headers.add(columnDesc);

        LinkedHashMap<String, String> columnNames = new LinkedHashMap<>();
        columnNames.put("contractCode","contractCode");
        columnNames.put("contractName","contractName");
        columnNames.put("projectName","projectName");
        columnNames.put("customerName","customerName");
        columnNames.put("retentionAmount","retentionAmount");
        columnNames.put("contactDate","contactDate");
        columnNames.put("retentionDate","retentionDate");
        columnNames.put("rate","rate");
        columnNames.put("area","area");
        columnNames.put("purchaser","purchaser");
        columnNames.put("startDate","startDate");
        columnNames.put("endDate","endDate");
        columnNames.put("contractStatus","contractStatus");
        columnNames.put("accountPeriod","accountPeriod");
        columnNames.put("goodsName","goodsName");
        columnNames.put("price","price");
        columnNames.put("goodsNum","goodsNum");



        writer.write(headers, false);
        // 一次性写出内容
        writer.write(qlContractInfoSaleExports, false);
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
     * 导出合同管理列表
     */
    @SaCheckPermission("contractInfoSale:contractInfoSale:export")
    @Log(title = "合同管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export1")
    public void export1(QlContractInfoSaleReportQuery bo, HttpServletResponse response) {
        List<QlContractInfoSaleVo> list = null;
        if(Constants.EXPORT_ALL.equals(bo.getExportAll())) {
            list = iQlContractInfoSaleService.queryList(QlContractInfoSaleConvert.INSTANCE.conver(bo));
        } else {
            PageQuery pageQuery = new PageQuery();
            pageQuery.setPageNum(bo.getPageNum());
            pageQuery.setPageSize(bo.getPageSize());
            TableDataInfo<QlContractInfoSaleVo> qlContractInfoSaleVoTableDataInfo = iQlContractInfoSaleService.queryPageList(QlContractInfoSaleConvert.INSTANCE.conver(bo), pageQuery);
            list = qlContractInfoSaleVoTableDataInfo.getRows();
        }
        ExcelUtil.exportExcel(list, "合同管理", QlContractInfoSaleVo.class, response);
    }


    /**
     * 获取合同管理详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("contractInfoSale:contractInfoSale:query")
    @GetMapping("/{id}")
    public R<QlContractInfoSaleVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(iQlContractInfoSaleService.queryById(id));
    }

    /**
     * 新增合同管理
     */
    @SaCheckPermission("contractInfoSale:contractInfoSale:add")
    @Log(title = "合同管理", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody QlContractInfoSaleBo bo) {
        return toAjax(iQlContractInfoSaleService.insertByBo(bo) ? 1 : 0);
    }

    /**
     * 修改合同管理
     */
    @SaCheckPermission("contractInfoSale:contractInfoSale:edit")
    @Log(title = "合同管理", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody QlContractInfoSaleBo bo) {
        return toAjax(iQlContractInfoSaleService.updateByBo(bo) ? 1 : 0);
    }

    /**
     * 删除合同管理
     *
     * @param ids 主键串
     */
    @SaCheckPermission("contractInfoSale:contractInfoSale:remove")
    @Log(title = "合同管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(iQlContractInfoSaleService.deleteWithValidByIds(Arrays.asList(ids), true) ? 1 : 0);
    }


    /**
     * 入库单 明细导入
     */
    @PostMapping("/import")
    public void uploadExcel(MultipartFile file) throws IOException {
        InputStream inputStream = file.getInputStream();
        ExcelReader reader = cn.hutool.poi.excel.ExcelUtil.getReader(inputStream);
        List<QlContractInfoSaleImport> qlContractInfoSaleImports = reader.read(2, 3, Integer.MAX_VALUE, QlContractInfoSaleImport.class);
        checkImport(qlContractInfoSaleImports);
        List<QlContractInfoSaleBo> qlContractInfoPurchaseBos = importToBo(qlContractInfoSaleImports);
        iQlContractInfoSaleService.batchInsertBo(qlContractInfoPurchaseBos);
    }

    private void checkImport(List<QlContractInfoSaleImport> qlContractInfoSaleImportList) {

    }

    private List<QlContractInfoSaleBo> importToBo(List<QlContractInfoSaleImport> qlContractInfoSaleImports) {
        List<String> customerNames = qlContractInfoSaleImports.stream().map(QlContractInfoSaleImport::getCustomerName).collect(Collectors.toList());
        QlBasisCustomerBo qlBasisCustomerBo = new QlBasisCustomerBo();
        qlBasisCustomerBo.setCustomerNames(customerNames);
        List<QlBasisCustomerVo> qlBasisCustomerVos = iQlBasisCustomerService.queryList(qlBasisCustomerBo);
        Map<String, QlBasisCustomerVo> qlBasisCustomerVoMap = qlBasisCustomerVos.stream().collect(Collectors.toMap(QlBasisCustomerVo::getCustomerName, qlBasisSupplierVo -> qlBasisSupplierVo));

        List<String> goodsNames = qlContractInfoSaleImports.stream().map(QlContractInfoSaleImport::getGoodsName).collect(Collectors.toList());
        QlShopGoodsBo qlShopGoodsBo = new QlShopGoodsBo();
        qlShopGoodsBo.setGoodsNames(goodsNames);
        List<QlShopGoodsVo> qlShopGoodsVos = iQlShopGoodsService.queryList(qlShopGoodsBo);
        Map<String, QlShopGoodsVo> qlShopGoodsVoMap = qlShopGoodsVos.stream().collect(Collectors.toMap(QlShopGoodsVo::getGoodsName, qlShopGoodsVo -> qlShopGoodsVo, (oldObj, newObj) -> newObj));

        List<QlContractInfoSaleBo> qlContractInfoPurchaseBos = new ArrayList<>();
        Map<String, List<QlContractInfoSaleImport>> qlContractInfoSaleImportGroup = qlContractInfoSaleImports.stream().collect(Collectors.groupingBy(QlContractInfoSaleImport::getContractCode));
        qlContractInfoSaleImportGroup.forEach((contractCode, qlContractInfoSaleImportList) -> {
            QlContractInfoSaleImport qlContractInfoSaleImport = qlContractInfoSaleImportList.get(0);

            QlContractInfoSaleBo qlContractInfoSaleBo = QlContractInfoSaleMapstruct.INSTANCES.convert(qlContractInfoSaleImport);
            // 查询客户
            QlBasisCustomerVo qlBasisCustomerVo = qlBasisCustomerVoMap.get(qlContractInfoSaleImport.getCustomerName());
            qlContractInfoSaleBo.setCustomerId(String.valueOf(qlBasisCustomerVo.getId()));
            qlContractInfoSaleBo.setContactPerson(qlBasisCustomerVo.getContactPerson());
            qlContractInfoSaleBo.setMobilePhone(qlBasisCustomerVo.getMobilePhone());
            qlContractInfoSaleBo.setContractStatus(qlContractInfoSaleImport.getContractStatus().equals("是")?"Y":"N");

            BigDecimal totalAmount = qlContractInfoSaleImportList.stream().map(contractInfoSaleImport -> contractInfoSaleImport.getPrice().multiply(new BigDecimal(contractInfoSaleImport.getGoodsNum()))).reduce(BigDecimal.ZERO, BigDecimal::add);
            qlContractInfoSaleBo.setAmount(totalAmount);
            List<QlContractGoodsRelBo> contractGoodsRels = new ArrayList<>();
            for (QlContractInfoSaleImport contractInfoSaleImport : qlContractInfoSaleImportList) {
                // 查询产品
                QlShopGoodsVo qlShopGoodsVo = qlShopGoodsVoMap.get(qlContractInfoSaleImport.getGoodsName());
                QlContractGoodsRelBo qlContractGoodsRelBo = new QlContractGoodsRelBo();
                qlContractGoodsRelBo.setGoodsId(qlShopGoodsVo.getId());
                qlContractGoodsRelBo.setGoodsName(qlShopGoodsVo.getGoodsName());
                qlContractGoodsRelBo.setGoodsNum(contractInfoSaleImport.getGoodsNum());
                qlContractGoodsRelBo.setPrice(contractInfoSaleImport.getPrice());
                qlContractGoodsRelBo.setContractType("sale");
                qlContractGoodsRelBo.setGoodsUnit(qlShopGoodsVo.getGoodsUnit());
                qlContractGoodsRelBo.setRemark(contractInfoSaleImport.getRemark());

                contractGoodsRels.add(qlContractGoodsRelBo);
            }
            qlContractInfoSaleBo.setContractGoodsRels(contractGoodsRels);
            qlContractInfoPurchaseBos.add(qlContractInfoSaleBo);
        });

        return qlContractInfoPurchaseBos;
    }
}
