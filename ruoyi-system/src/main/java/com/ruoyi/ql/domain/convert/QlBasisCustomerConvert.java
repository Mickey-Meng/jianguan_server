package com.ruoyi.ql.domain.convert;

import com.ruoyi.ql.domain.bo.QlBasisCustomerBo;
import com.ruoyi.ql.domain.bo.QlBasisSupplierBo;
import com.ruoyi.ql.domain.export.query.QlBasisCustomerReportQuery;
import com.ruoyi.ql.domain.export.query.QlBasisSupplierReportQuery;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface QlBasisCustomerConvert {

    QlBasisCustomerConvert INSTANCE = Mappers.getMapper(QlBasisCustomerConvert.class);

    QlBasisCustomerBo conver(QlBasisCustomerReportQuery qlBasisCustomerReportQuery);
}
