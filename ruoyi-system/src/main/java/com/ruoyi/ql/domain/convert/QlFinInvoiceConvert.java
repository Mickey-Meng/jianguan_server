package com.ruoyi.ql.domain.convert;

import com.ruoyi.ql.domain.bo.QlFinInvoiceBo;
import com.ruoyi.ql.domain.bo.QlFinInvoiceSaleBo;
import com.ruoyi.ql.domain.export.query.QlFinInvoiceReportQuery;
import com.ruoyi.ql.domain.export.query.QlFinInvoiceSaleReportQuery;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface QlFinInvoiceConvert {

    QlFinInvoiceConvert INSTANCE = Mappers.getMapper(QlFinInvoiceConvert.class);

    QlFinInvoiceBo conver(QlFinInvoiceReportQuery qlFinInvoiceReportQuery);
}
