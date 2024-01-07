package com.ruoyi.ql.domain.convert;

import com.ruoyi.ql.domain.bo.QlContractInfoPurchaseBo;
import com.ruoyi.ql.domain.bo.QlContractInfoSaleBo;
import com.ruoyi.ql.domain.export.query.QlContractInfoPurchaseReportQuery;
import com.ruoyi.ql.domain.export.query.QlContractInfoSaleReportQuery;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface QlContractInfoSaleConvert {

    QlContractInfoSaleConvert INSTANCE = Mappers.getMapper(QlContractInfoSaleConvert.class);

    QlContractInfoSaleBo conver(QlContractInfoSaleReportQuery qlContractInfoSaleReportQuery);
}
