package com.ruoyi.ql.domain.convert;

import com.ruoyi.ql.domain.bo.QlFinInvoiceSaleBo;
import com.ruoyi.ql.domain.bo.QlFinPaymentBo;
import com.ruoyi.ql.domain.export.query.QlFinInvoiceSaleReportQuery;
import com.ruoyi.ql.domain.export.query.QlFinPaymentReportQuery;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface QlFinPaymentConvert {

    QlFinPaymentConvert INSTANCE = Mappers.getMapper(QlFinPaymentConvert.class);

    QlFinPaymentBo conver(QlFinPaymentReportQuery qlFinPaymentReportQuery);
}
