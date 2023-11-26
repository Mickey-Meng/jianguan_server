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
import com.ruoyi.ql.domain.QlReceivableOutboundRel;
import com.ruoyi.ql.domain.bo.*;
import com.ruoyi.ql.domain.convert.OutboundConvert;
import com.ruoyi.ql.domain.export.OutboundExportVo;
import com.ruoyi.ql.domain.export.query.QlOutboundReportQuery;
import com.ruoyi.ql.domain.importvo.OutboundImportVo;
import com.ruoyi.ql.domain.vo.*;
import com.ruoyi.ql.mapstruct.OutboundAndWarehousingMapstruct;
import com.ruoyi.ql.mapstruct.QlWarehousingDetailMapstruct;
import com.ruoyi.ql.service.*;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections.ArrayStack;
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

    private final IQlContractGoodsRelService iQlContractGoodsRelService;

    private final IQlContractInfoSaleService iQlContractInfoSaleService;

    private final IQlProjectInfoService iQlProjectInfoService;

    private final IQlReceivableOutboundRelService iQlReceivableOutboundRelService;
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
        List<OutboundImportVo> outboundImports = reader.read(2, 3, Integer.MAX_VALUE, OutboundImportVo.class);
        List<QlOutboundBo> outboundBos = importToBo(outboundImports);
        if (CollUtil.isEmpty(outboundBos)) {
            return;
        }
        iQlOutboundService.batchInsertBo(outboundBos);
    }

    /**
     * @param outboundImports
     * @return
     */
    private List<QlOutboundBo> importToBo(List<OutboundImportVo> outboundImports) {
        if (CollUtil.isEmpty(outboundImports)) {
            return new ArrayList<>();
        }
        List<QlOutboundBo> outbounds = new ArrayList<>();
        List<QlContractInfoSaleVo> contractInfoSales = findContractInfoSale(outboundImports);
        Map<String, QlContractInfoSaleVo> contractInfoSaleMap = contractInfoSales.stream().collect(Collectors.toMap(QlContractInfoSaleVo::getContractCode, qlContractInfoSaleVo -> qlContractInfoSaleVo));

        List<QlProjectInfoVo> projectInfos = findProjectInfo(outboundImports);
        Map<String, QlProjectInfoVo> projectInfoMap = projectInfos.stream().collect(Collectors.toMap(QlProjectInfoVo::getProjectName, project -> project));

        List<QlContractGoodsRelVo> qlContractGoodsRelVos = findContractGoodsRel(contractInfoSales);
        Map<String, Long> goodsIds = buildGoodsIds(contractInfoSales, qlContractGoodsRelVos);

        Map<String, List<OutboundImportVo>> outboundImportMap = outboundImports.stream().collect(Collectors.groupingBy(OutboundImportVo::getOutboundCode));

        outboundImportMap.forEach((key, value) -> {
            QlOutboundBo outbound = OutboundAndWarehousingMapstruct.INSTANCES.importToBo(value.get(0));

            // 销售合同编号查询客户Id[customerId]
            setCustomerId(outbound, contractInfoSaleMap);

            // 项目名称查询项目Id[projectId]
            setProjectId(outbound, projectInfoMap);

            // 销售合同Id和产品名称查询产品id[goodsId]
            QlContractInfoSaleVo qlContractInfoSaleVo = contractInfoSaleMap.get(outbound.getSaleContractCode());

            List<QlWarehousingDetailBo> warehousingDetails = QlWarehousingDetailMapstruct.INSTANCES.outboundImportToBos(value);
            for (QlWarehousingDetailBo warehousingDetail : warehousingDetails) {
                Long goodsId = goodsIds.get(qlContractInfoSaleVo.getId() + "-" + warehousingDetail.getGoodsName());
                if(ObjectUtil.isNull(goodsId)) {
                    throw new ServiceException("未查询到销售合同下的"+warehousingDetail.getGoodsName()+"产品销售信息");
                }
                warehousingDetail.setGoodsId(String.valueOf(goodsId));
                warehousingDetail.setInventoryDirection("outbound");
            }
            outbound.setWarehousingDetails(warehousingDetails);

            outbounds.add(outbound);
        });
        return outbounds;
    }

    private List<QlContractInfoSaleVo> findContractInfoSale(List<OutboundImportVo> outboundImports) {
        // 销售合同编码查询合同Id
        QlContractInfoSaleBo qlContractInfoSaleBo = new QlContractInfoSaleBo();
        List<String> contractCodes = outboundImports.stream().map(OutboundImportVo:: getSaleContractCode).collect(Collectors.toList());
        qlContractInfoSaleBo.setContractCodes(contractCodes);
        return iQlContractInfoSaleService.queryList(qlContractInfoSaleBo);
    }

    private List<QlProjectInfoVo>  findProjectInfo(List<OutboundImportVo> outboundImports) {
        List<String> projectNames = outboundImports.stream().map(OutboundImportVo::getProjectName).distinct().collect(Collectors.toList());
        QlProjectInfoBo projectInfo = new QlProjectInfoBo();
        projectInfo.setProjectNames(projectNames);
        return iQlProjectInfoService.queryList(projectInfo);
    }

    private List<QlContractGoodsRelVo> findContractGoodsRel(List<QlContractInfoSaleVo> contractInfoSales) {
        QlContractGoodsRelBo contractGoodsRel = new QlContractGoodsRelBo();
        List<Long> contractSaleIds = contractInfoSales.stream().map(QlContractInfoSaleVo::getId).distinct().collect(Collectors.toList());
        contractGoodsRel.setContractIds(contractSaleIds);
        return iQlContractGoodsRelService.queryList(contractGoodsRel);
    }

    private Map<String, Long> buildGoodsIds(List<QlContractInfoSaleVo> contractInfoSales, List<QlContractGoodsRelVo> qlContractGoodsRelVos) {
        Map<String, QlContractInfoSaleVo> contractInfoSaleMap = contractInfoSales.stream().collect(Collectors.toMap(contractInfoSale-> String.valueOf(contractInfoSale.getId()), qlContractInfoSaleVo -> qlContractInfoSaleVo));
        Map<String, Long> goodsIds = new HashMap<>();
        for (QlContractGoodsRelVo qlContractGoodsRelVo : qlContractGoodsRelVos) {
            String contractId = String.valueOf(qlContractGoodsRelVo.getContractId());
            QlContractInfoSaleVo qlContractInfoSaleVo = contractInfoSaleMap.get(contractId);
            if (ObjectUtil.isNull(qlContractInfoSaleVo)) {
                throw new ServiceException("未查询到销售合同下的产品销售信息");
            }
            String key = contractId + "-" + qlContractGoodsRelVo.getGoodsName();
            goodsIds.put(key, qlContractGoodsRelVo.getGoodsId());
        }
        return goodsIds;
    }

    private void setCustomerId(QlOutboundBo outbound, Map<String, QlContractInfoSaleVo> contractInfoSaleMap) {
        if(!contractInfoSaleMap.containsKey(outbound.getSaleContractCode())) {
            throw new ServiceException("合同编码未找到对应的合同信息");
        }
        outbound.setCustomerId(Long.parseLong(contractInfoSaleMap.get(outbound.getSaleContractCode()).getCustomerId()));
    }

    private void setProjectId(QlOutboundBo outbound, Map<String, QlProjectInfoVo> projectInfoMap) {
        if(!projectInfoMap.containsKey(outbound.getProjectName())) {
            throw new ServiceException("项目名称未查询到对应的项目信息");
        }
        outbound.setProjectId(String.valueOf(projectInfoMap.get(outbound.getProjectName()).getId()));
    }

    /**
     * 导出出库管理列表
     */
    @SaCheckPermission("ql:outbound:export")
    @Log(title = "出库管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(QlOutboundReportQuery bo, HttpServletResponse response) throws IOException {
        List<QlOutboundVo> qutbounds = null;
        if(Constants.EXPORT_ALL.equals(bo.getExportAll())) {
            qutbounds = iQlOutboundService.queryList(OutboundConvert.INSTANCE.conver(bo));
        }else {
            PageQuery pageQuery = new PageQuery();
            pageQuery.setPageNum(bo.getPageNum());
            pageQuery.setPageSize(bo.getPageSize());
            TableDataInfo<QlOutboundVo> qlOutboundVoTableDataInfo = iQlOutboundService.queryPageList(OutboundConvert.INSTANCE.conver(bo), pageQuery);
            qutbounds = qlOutboundVoTableDataInfo.getRows();
        }
        List<OutboundExportVo> outboundExports = new ArrayList<>();
        for (QlOutboundVo outbound : qutbounds) {
            if(CollUtil.isEmpty(outbound.getWarehousingDetails())) {
                continue;
            }
            for (QlWarehousingDetailVo warehousingDetail : outbound.getWarehousingDetails()) {
                OutboundExportVo outboundExport = new OutboundExportVo();
                BeanUtil.copyProperties(outbound, outboundExport);
                BeanUtil.copyProperties(warehousingDetail, outboundExport);
                outboundExports.add(outboundExport);
            }
        }

//        ExcelUtil.exportExcel(outboundExports, "出库管理", OutboundExportVo.class, response);


        // 通过工具类创建writer
        ExcelWriter writer = ExcelUtil.getWriter(true);
        // 合并单元格后的标题行
        writer.merge(22, "出库明细");

        List<LinkedHashMap<String, String>> headers = new LinkedList<>();

        LinkedHashMap<String, String> columnDesc = new LinkedHashMap<>();
        columnDesc.put("outboundCode","单据编号");
        columnDesc.put("projectName","项目名称");
        columnDesc.put("outboundDate","出货日期");
        columnDesc.put("salesperson","销售员");
        columnDesc.put("saleContractCode","销售合同编号");
        columnDesc.put("purchaseContractCode","采购合同编号");
        columnDesc.put("customerName","客户名称");
        columnDesc.put("telephone","客户电话");
        columnDesc.put("address","客户地址");
        columnDesc.put("saleDate","销售日期");
        columnDesc.put("saleAmount","销售金额");
        columnDesc.put("outboundUsername","出库对接人");
        columnDesc.put("outboundReleaseuser","出库审核人");
        columnDesc.put("goodsName","产品名称");
        columnDesc.put("goodsSearchstandard","产品规格");
        columnDesc.put("goodsUnit","产品单位");
        columnDesc.put("basePrice","销售数量");
        columnDesc.put("inventoryNumber","基准价");
        columnDesc.put("salePrice","销售价");
        columnDesc.put("extraPrice","附加价格");
        columnDesc.put("amount","总价");
        columnDesc.put("remark","备注");
        headers.add(columnDesc);

        LinkedHashMap<String, String> columnNames = new LinkedHashMap<>();
        columnNames.put("outboundCode","outboundCode");
        columnNames.put("projectName","projectName");
        columnNames.put("outboundDate","outboundDate");
        columnNames.put("salesperson","salesperson");
        columnNames.put("saleContractCode","saleContractCode");
        columnNames.put("purchaseContractCode","purchaseContractCode");
        columnNames.put("customerName","customerName");
        columnNames.put("telephone","telephone");
        columnNames.put("address","address");
        columnNames.put("saleDate","saleDate");
        columnNames.put("saleAmount","saleAmount");
        columnNames.put("outboundUsername","outboundUsername");
        columnNames.put("outboundReleaseuser","outboundReleaseuser");
        columnNames.put("goodsName","goodsName");
        columnNames.put("goodsSearchstandard","goodsSearchstandard");
        columnNames.put("goodsUnit","goodsUnit");
        columnNames.put("basePrice","basePrice");
        columnNames.put("inventoryNumber","inventoryNumber");
        columnNames.put("salePrice","salePrice");
        columnNames.put("extraPrice","extraPrice");
        columnNames.put("amount","amount");
        columnNames.put("remark","remark");

        headers.add(columnNames);

        writer.write(headers, false);
        // 一次性写出内容
        writer.write(outboundExports, false);
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
    @Log(title = "出库管理", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody QlOutboundBo bo) {
        return toAjax(iQlOutboundService.insertByBo(bo) ? 1 : 0);
    }

    /**
     * 修改出库管理
     */
    @SaCheckPermission("ql:outbound:edit")
    @Log(title = "出库管理", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody QlOutboundBo bo) {
        QlOutboundBo qlOutboundBo = new QlOutboundBo();
        qlOutboundBo.setId(bo.getId());
        List<QlOutboundVo> qlOutboundVos = iQlOutboundService.queryList(qlOutboundBo);
        for (QlOutboundVo qlWarehousingVo : qlOutboundVos) {
            if(LOCK.equals(qlWarehousingVo.getLockStatus())) {
                return R.fail("出库单已锁定，不能修改");
            }
        }
        return toAjax(iQlOutboundService.updateByBo(bo) ? 1 : 0);
    }

    /**
     * 删除出库管理
     *
     * @param ids 主键串
     */
    @SaCheckPermission("ql:outbound:remove")
    @Log(title = "出库管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        QlOutboundBo qlOutboundBo = new QlOutboundBo();
        qlOutboundBo.setIds(CollUtil.newArrayList(ids));
        List<QlOutboundVo> qlOutboundVos = iQlOutboundService.queryList(qlOutboundBo);
        for (QlOutboundVo qlWarehousingVo : qlOutboundVos) {
            if(LOCK.equals(qlWarehousingVo.getLockStatus())) {
                return R.fail("出库单已锁定，不能删除");
            }
        }
        return toAjax(iQlOutboundService.deleteWithValidByIds(Arrays.asList(ids), true) ? 1 : 0);
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
        QlOutboundBo qlOutboundBo = new QlOutboundBo();
        qlOutboundBo.setIds(CollUtil.newArrayList(ids));
        List<QlOutboundVo> qlOutboundVos = iQlOutboundService.queryList(qlOutboundBo);
        for (QlOutboundVo qlOutboundVo : qlOutboundVos) {
            if(LOCK.equals(qlOutboundVo.getLockStatus())) {
                return R.fail("重复锁定");
            }
        }
        for (QlOutboundVo qlOutboundVo : qlOutboundVos) {
            qlOutboundVo.setLockStatus(LOCK);
            qlOutboundBo = BeanUtil.copyProperties(qlOutboundVo, QlOutboundBo.class);
            iQlOutboundService.updateByBo(qlOutboundBo);
        }
        return R.ok();
    }

    /**
     * 修改入库管理
     */
    @SaCheckPermission("warehousing:warehousing:unlock")
    @Log(title = "入库管理", businessType = BusinessType.UNLOCK)
    @RepeatSubmit()
    @GetMapping("/unlock/{ids}")
    public R<Void> unlock(@NotEmpty(message = "主键不能为空") @PathVariable Long[] ids) {
        // 锁定状态的数据不需要重复操作
        QlOutboundBo qlOutboundBo = new QlOutboundBo();
        qlOutboundBo.setIds(CollUtil.newArrayList(ids));
        List<QlOutboundVo> qlOutboundVos = iQlOutboundService.queryList(qlOutboundBo);
        for (QlOutboundVo qlOutboundVo : qlOutboundVos) {
            if(UNLOCK.equals(qlOutboundVo.getLockStatus())) {
                return R.fail("重复解锁");
            }
        }
        for (QlOutboundVo qlOutboundVo : qlOutboundVos) {
            // 查询是否有支付记录--有支付记录，抛出异常
            QlReceivableOutboundRelBo qlReceivableOutboundRelBo = new QlReceivableOutboundRelBo();
            qlReceivableOutboundRelBo.setOutboundCode(qlOutboundVo.getOutboundCode());
            List<QlReceivableOutboundRelVo> qlReceivableOutboundRelVos = iQlReceivableOutboundRelService.queryList(qlReceivableOutboundRelBo);
            if(CollUtil.isNotEmpty(qlReceivableOutboundRelVos)) {
                return R.fail("入库单已有收款记录，不能解锁");
            }
            qlOutboundVo.setLockStatus(UNLOCK);
            qlOutboundBo = BeanUtil.copyProperties(qlOutboundVo, QlOutboundBo.class);
            iQlOutboundService.updateByBo(qlOutboundBo);
        }
        return R.ok();
    }
}
