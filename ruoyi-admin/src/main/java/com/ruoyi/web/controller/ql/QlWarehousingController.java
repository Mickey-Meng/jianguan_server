package com.ruoyi.web.controller.ql;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
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
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.ql.domain.bo.*;
import com.ruoyi.ql.domain.convert.WarehousingConvert;
import com.ruoyi.ql.domain.export.WarehousingExportVo;
import com.ruoyi.ql.domain.export.query.QlWarehousingReportQuery;
import com.ruoyi.ql.domain.importvo.WarehousingImportVo;
import com.ruoyi.ql.domain.vo.*;
import com.ruoyi.ql.mapstruct.QlWarehousingDetailMapstruct;
import com.ruoyi.ql.mapstruct.WarehousingMapstruct;
import com.ruoyi.ql.service.*;
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
import java.util.*;
import java.util.stream.Collectors;

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

    private final IQlContractGoodsRelService iQlContractGoodsRelService;

    private final IQlContractInfoPurchaseService contractInfoPurchaseService;

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
    public void export(QlWarehousingReportQuery bo, HttpServletResponse response) throws IOException {
        List<QlWarehousingVo> warehousings = null;
        if(Constants.EXPORT_ALL.equals(bo.getExportAll())) {
            warehousings = iQlWarehousingService.queryList(WarehousingConvert.INSTANCE.conver(bo));
        } else {
            PageQuery pageQuery = new PageQuery();
            pageQuery.setPageNum(bo.getPageNum());
            pageQuery.setPageSize(bo.getPageSize());
            TableDataInfo<QlWarehousingVo> qlWarehousingVoTableDataInfo = iQlWarehousingService.queryPageList(WarehousingConvert.INSTANCE.conver(bo), pageQuery);
            warehousings = qlWarehousingVoTableDataInfo.getRows();
        }

        List<WarehousingExportVo> warehousingExports = new ArrayList<>();
        for (QlWarehousingVo warehousing : warehousings) {
            if(CollUtil.isEmpty(warehousing.getWarehousingDetails())) {
                continue;
            }
            for (QlWarehousingDetailVo warehousingDetail : warehousing.getWarehousingDetails()) {
                WarehousingExportVo warehousingExport = new WarehousingExportVo();
                BeanUtil.copyProperties(warehousing, warehousingExport);
                BeanUtil.copyProperties(warehousingDetail, warehousingExport);
                warehousingExports.add(warehousingExport);
            }
        }

        // 通过工具类创建writer
        ExcelWriter writer = ExcelUtil.getWriter(true);
        // 合并单元格后的标题行
        writer.merge(19, "入库明细");

        List<LinkedHashMap<String, String>> headers = new LinkedList<>();

        LinkedHashMap<String, String> columnDesc = new LinkedHashMap<>();
        columnDesc.put("warehousingCode","入库编号");
        columnDesc.put("purchaser","采购员");
        columnDesc.put("contractCode","采购合同编号");
        columnDesc.put("supplierName","供应商名称");
        columnDesc.put("address","供应商地址");
        columnDesc.put("mobilePhone","供应商电话");
        columnDesc.put("arrivalDate","到货日期");
        columnDesc.put("lastPaymentDate","最后付款日期");
        columnDesc.put("warehousingUsername","制单人");
        columnDesc.put("warehousingReleaseuser","审核人");
        columnDesc.put("goodsName","产品名称");
        columnDesc.put("goodsSearchstandard","产品规格");
        columnDesc.put("goodsUnit","产品单位");
        columnDesc.put("inventoryNumber","采购数量");
        columnDesc.put("basePrice","基准价");
        columnDesc.put("incomePrice","进货价");
        columnDesc.put("extraPrice","附加价格");
        columnDesc.put("amount","总价");
        columnDesc.put("remark","备注");
        headers.add(columnDesc);

        LinkedHashMap<String, String> columnNames = new LinkedHashMap<>();
        columnNames.put("warehousingCode","warehousingCode");
        columnNames.put("purchaser","purchaser");
        columnNames.put("contractCode","contractCode");
        columnNames.put("supplierName","supplierName");
        columnNames.put("address","address");
        columnNames.put("mobilePhone","mobilePhone");
        columnNames.put("arrivalDate","arrivalDate");
        columnNames.put("lastPaymentDate","lastPaymentDate");
        columnNames.put("warehousingUsername","warehousingUsername");
        columnNames.put("warehousingReleaseuser","warehousingReleaseuser");
        columnNames.put("goodsName","goodsName");
        columnNames.put("goodsSearchstandard","goodsSearchstandard");
        columnNames.put("goodsUnit","goodsUnit");
        columnNames.put("inventoryNumber","inventoryNumber");
        columnNames.put("basePrice","basePrice");
        columnNames.put("incomePrice","incomePrice");
        columnNames.put("extraPrice","extraPrice");
        columnNames.put("amount","amount");
        columnNames.put("remark","remark");

        headers.add(columnNames);

        writer.write(headers, false);
        // 一次性写出内容
        writer.write(warehousingExports, false);
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
     * 入库单 明细导入
     */
    @PostMapping("/import")
    public void uploadExcel(MultipartFile file) throws IOException {
        InputStream inputStream = file.getInputStream();
        ExcelReader reader = cn.hutool.poi.excel.ExcelUtil.getReader(inputStream);
        List<WarehousingImportVo> warehousingImports = reader.read(2, 3, Integer.MAX_VALUE, WarehousingImportVo.class);
        List<QlWarehousingBo> warehousings = importToBo(warehousingImports);
        iQlWarehousingService.batchInsertBo(warehousings);
    }

    /**
     * @param warehousingImports
     * @return
     */
    private List<QlWarehousingBo> importToBo(List<WarehousingImportVo> warehousingImports) {
        if (CollUtil.isEmpty(warehousingImports)) {
            return new ArrayList<>();
        }
        List<QlWarehousingBo> warehousings = new ArrayList<>();
        List<QlContractInfoPurchaseVo> contractInfos = findContractInfoPurchase(warehousingImports);
        Map<String, QlContractInfoPurchaseVo> contractInfoMap = contractInfos.stream().collect(Collectors.toMap(QlContractInfoPurchaseVo::getContractCode, contractInfo -> contractInfo));

        List<QlContractGoodsRelVo> qlContractGoodsRelVos = findContractGoodsRel(contractInfos);
        Map<String, Long> goodsIds = buildGoodsIds(contractInfos, qlContractGoodsRelVos);

        Map<String, List<WarehousingImportVo>> warehousingImportMap = warehousingImports.stream().collect(Collectors.groupingBy(WarehousingImportVo::getWarehousingCode));

        warehousingImportMap.forEach((key, value) -> {
             QlWarehousingBo warehousing = WarehousingMapstruct.INSTANCES.importToBo(value.get(0));

            // 销售合同编号查询客户Id[customerId]
            setSupplierId(warehousing, contractInfoMap);

            // 销售合同Id和产品名称查询产品id[goodsId]
            QlContractInfoPurchaseVo contractInfoPurchase = contractInfoMap.get(warehousing.getContractCode());

            List<QlWarehousingDetailBo> warehousingDetails = QlWarehousingDetailMapstruct.INSTANCES.WarehousingImportToBos(value);
            for (QlWarehousingDetailBo warehousingDetail : warehousingDetails) {
                Long goodsId = goodsIds.get(contractInfoPurchase.getId() + "-" + warehousingDetail.getGoodsName());
                if(ObjectUtil.isNull(goodsId)) {
                    throw new ServiceException("未查询到采购合同："+warehousing.getContractCode()+"下的"+warehousingDetail.getGoodsName()+"产品采购信息");
                }
                warehousingDetail.setGoodsId(String.valueOf(goodsId));
                warehousingDetail.setInventoryDirection("warehousing");
            }
            warehousing.setWarehousingDetails(warehousingDetails);

            warehousings.add(warehousing);
        });
        return warehousings;
    }

    private void setSupplierId(QlWarehousingBo warehousing, Map<String, QlContractInfoPurchaseVo> contractInfoMap) {
        if(!contractInfoMap.containsKey(warehousing.getContractCode())) {
            throw new ServiceException("合同编码未找到对应的合同信息");
        }
        warehousing.setSupplierId(Long.parseLong(contractInfoMap.get(warehousing.getContractCode()).getSupplierId()));
    }

    private List<QlContractInfoPurchaseVo> findContractInfoPurchase(List<WarehousingImportVo> warehousingImports) {
        List<String> contractCodes = warehousingImports.stream().map(WarehousingImportVo::getContractCode).collect(Collectors.toList());
        QlContractInfoPurchaseBo contractInfoPurchase = new QlContractInfoPurchaseBo();
        contractInfoPurchase.setContractCodes(contractCodes);
        return contractInfoPurchaseService.queryList(contractInfoPurchase);
    }

    private List<QlContractGoodsRelVo> findContractGoodsRel(List<QlContractInfoPurchaseVo> contractInfoPurchases) {
        QlContractGoodsRelBo contractGoodsRel = new QlContractGoodsRelBo();
        List<Long> contractSaleIds = contractInfoPurchases.stream().map(QlContractInfoPurchaseVo::getId).distinct().collect(Collectors.toList());
        contractGoodsRel.setContractIds(contractSaleIds);
        return iQlContractGoodsRelService.queryList(contractGoodsRel);
    }

    private Map<String, Long> buildGoodsIds(List<QlContractInfoPurchaseVo> contractInfos, List<QlContractGoodsRelVo> qlContractGoodsRelVos) {
        Map<String, QlContractInfoPurchaseVo> contractInfoMap = contractInfos.stream().collect(Collectors.toMap(contractInfoSale-> String.valueOf(contractInfoSale.getId()), qlContractInfoSaleVo -> qlContractInfoSaleVo));
        Map<String, Long> goodsIds = new HashMap<>();
        for (QlContractGoodsRelVo contractGoodsRel : qlContractGoodsRelVos) {
            String contractId = String.valueOf(contractGoodsRel.getContractId());
            QlContractInfoPurchaseVo contractInfoPurchaseVo = contractInfoMap.get(contractId);
            if (ObjectUtil.isNull(contractInfoPurchaseVo)) {
                throw new ServiceException("未查询到销售合同下的产品销售信息");
            }
            String key = contractId + "-" + contractGoodsRel.getGoodsName();
            goodsIds.put(key, contractGoodsRel.getGoodsId());
        }
        return goodsIds;
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
        QlWarehousingBo qlWarehousingBo = new QlWarehousingBo();
        qlWarehousingBo.setId(bo.getId());
        List<QlWarehousingVo> qlWarehousingVos = iQlWarehousingService.queryList(qlWarehousingBo);
        for (QlWarehousingVo qlWarehousingVo : qlWarehousingVos) {
            if(LOCK.equals(qlWarehousingVo.getLockStatus())) {
                return R.fail("入库单已锁定，不能修改");
            }
        }
        return toAjax(iQlWarehousingService.updateByBo(bo) ? 1 : 0);
    }

    private static final String LOCK = "lock";

    private static final String UNLOCK = "unlock";

    /**
     * 修改入库管理
     */
    @SaCheckPermission("warehousing:warehousing:lock")
    @Log(title = "入库管理", businessType = BusinessType.LOCK)
    @RepeatSubmit()
    @GetMapping("/lock/{ids}")
    public R<Void> lock(@NotEmpty(message = "主键不能为空") @PathVariable Long[] ids) {
        // 锁定状态的数据不需要重复操作
        QlWarehousingBo qlWarehousingBo = new QlWarehousingBo();
        qlWarehousingBo.setIds(CollUtil.newArrayList(ids));
        List<QlWarehousingVo> qlWarehousingVos = iQlWarehousingService.queryList(qlWarehousingBo);
        for (QlWarehousingVo qlWarehousingVo : qlWarehousingVos) {
            if(LOCK.equals(qlWarehousingVo.getLockStatus())) {
                return R.fail("重复锁定");
            }
        }
        for (QlWarehousingVo qlWarehousingVo : qlWarehousingVos) {
            qlWarehousingVo.setLockStatus(LOCK);
            QlWarehousingBo qlWarehousing = BeanUtil.copyProperties(qlWarehousingVo, QlWarehousingBo.class);
            iQlWarehousingService.updateByBo(qlWarehousing);
        }
        return R.ok();
    }

    private final IQlPaymentWarehousingRelService iQlPaymentWarehousingRelService;

    /**
     * 修改入库管理
     */
    @SaCheckPermission("warehousing:warehousing:unlock")
    @Log(title = "入库管理", businessType = BusinessType.UNLOCK)
    @RepeatSubmit()
    @GetMapping("/unlock/{ids}")
    public R<Void> unlock(@NotEmpty(message = "主键不能为空") @PathVariable Long[] ids) {
        // 锁定状态的数据不需要重复操作
        QlWarehousingBo qlWarehousingBo = new QlWarehousingBo();
        qlWarehousingBo.setIds(CollUtil.newArrayList(ids));
        List<QlWarehousingVo> qlWarehousingVos = iQlWarehousingService.queryList(qlWarehousingBo);
        for (QlWarehousingVo qlWarehousingVo : qlWarehousingVos) {
            if(UNLOCK.equals(qlWarehousingVo.getLockStatus())) {
                return R.fail("重复解锁");
            }
        }
        for (QlWarehousingVo qlWarehousingVo : qlWarehousingVos) {
            // 查询是否有支付记录--有支付记录，抛出异常
            QlPaymentWarehousingRelBo qlPaymentWarehousingRelBo = new QlPaymentWarehousingRelBo();
            qlPaymentWarehousingRelBo.setWarehousingCode(qlWarehousingVo.getWarehousingCode());
            List<QlPaymentWarehousingRelVo> qlPaymentWarehousingRelVos = iQlPaymentWarehousingRelService.queryList(qlPaymentWarehousingRelBo);
            if(CollUtil.isNotEmpty(qlPaymentWarehousingRelVos)) {
                return R.fail("出库单已有支付记录，不能解锁");
            }
            qlWarehousingVo.setLockStatus(UNLOCK);
            QlWarehousingBo qlWarehousing = BeanUtil.copyProperties(qlWarehousingVo, QlWarehousingBo.class);
            iQlWarehousingService.updateByBo(qlWarehousing);
        }
        return R.ok();
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
        QlWarehousingBo qlWarehousingBo = new QlWarehousingBo();
        qlWarehousingBo.setIds(CollUtil.newArrayList(ids));
        List<QlWarehousingVo> qlWarehousingVos = iQlWarehousingService.queryList(qlWarehousingBo);
        for (QlWarehousingVo qlWarehousingVo : qlWarehousingVos) {
            if(LOCK.equals(qlWarehousingVo.getLockStatus())) {
                return R.fail("入库单已锁定，不能删除");
            }
        }
        return toAjax(iQlWarehousingService.deleteWithValidByIds(Arrays.asList(ids), true) ? 1 : 0);
    }


    /**
     * 获取最新入库单编号
     */
    @SaCheckPermission("warehousing:warehousing:list")
    @GetMapping("/getInventoryId")
    public String getInventoryId(String type) {
        String inventNo = iQlWarehousingService.getInventoryId(type);
        return inventNo;
    }
}
