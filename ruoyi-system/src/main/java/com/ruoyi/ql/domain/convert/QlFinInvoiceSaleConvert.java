package com.ruoyi.ql.domain.convert;

import com.ruoyi.ql.domain.bo.QlFinInvoiceSaleBo;
import com.ruoyi.ql.domain.bo.QlFinReceivableBo;
import com.ruoyi.ql.domain.export.query.QlFinInvoiceSaleReportQuery;
import com.ruoyi.ql.domain.export.query.QlFinReceivableReportQuery;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface QlFinInvoiceSaleConvert {

    QlFinInvoiceSaleConvert INSTANCE = Mappers.getMapper(QlFinInvoiceSaleConvert.class);

    QlFinInvoiceSaleBo conver(QlFinInvoiceSaleReportQuery qlFinInvoiceSaleReportQuery);
}
