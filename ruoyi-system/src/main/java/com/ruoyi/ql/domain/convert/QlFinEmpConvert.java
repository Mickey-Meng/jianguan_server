package com.ruoyi.ql.domain.convert;

import com.ruoyi.ql.domain.bo.QlBasisSupplierBo;
import com.ruoyi.ql.domain.bo.QlFinEmpBo;
import com.ruoyi.ql.domain.export.query.QlBasisSupplierReportQuery;
import com.ruoyi.ql.domain.export.query.QlFinEmpReportQuery;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface QlFinEmpConvert {

    QlFinEmpConvert INSTANCE = Mappers.getMapper(QlFinEmpConvert.class);

    QlFinEmpBo conver(QlFinEmpReportQuery qlFinEmpReportQuery);
}
