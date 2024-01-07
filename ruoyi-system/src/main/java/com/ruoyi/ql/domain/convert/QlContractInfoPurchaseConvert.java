package com.ruoyi.ql.domain.convert;

import com.ruoyi.ql.domain.bo.QlContractInfoPurchaseBo;
import com.ruoyi.ql.domain.bo.QlFinInvoiceBo;
import com.ruoyi.ql.domain.export.query.QlContractInfoPurchaseReportQuery;
import com.ruoyi.ql.domain.export.query.QlFinInvoiceReportQuery;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface QlContractInfoPurchaseConvert {

    QlContractInfoPurchaseConvert INSTANCE = Mappers.getMapper(QlContractInfoPurchaseConvert.class);

    QlContractInfoPurchaseBo conver(QlContractInfoPurchaseReportQuery qlFinInvoiceReportQuery);
}
