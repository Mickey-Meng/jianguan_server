package com.ruoyi.ql.domain.convert;

import com.ruoyi.ql.domain.bo.QlBasisCustomerBo;
import com.ruoyi.ql.domain.bo.QlProjectInfoBo;
import com.ruoyi.ql.domain.export.query.QlBasisCustomerReportQuery;
import com.ruoyi.ql.domain.export.query.QlProjectInfoReportQuery;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface QlProjectInfoConvert {

    QlProjectInfoConvert INSTANCE = Mappers.getMapper(QlProjectInfoConvert.class);

    QlProjectInfoBo conver(QlProjectInfoReportQuery qlBasisCustomerReportQuery);
}
