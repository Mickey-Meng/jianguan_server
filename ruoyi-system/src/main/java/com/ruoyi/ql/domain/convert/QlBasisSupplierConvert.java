package com.ruoyi.ql.domain.convert;

import com.ruoyi.ql.domain.bo.QlBasisSupplierBo;
import com.ruoyi.ql.domain.bo.QlContractInfoSaleBo;
import com.ruoyi.ql.domain.export.query.QlBasisSupplierReportQuery;
import com.ruoyi.ql.domain.export.query.QlContractInfoSaleReportQuery;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface QlBasisSupplierConvert {

    QlBasisSupplierConvert INSTANCE = Mappers.getMapper(QlBasisSupplierConvert.class);

    QlBasisSupplierBo conver(QlBasisSupplierReportQuery qlBasisSupplierReportQuery);
}
