package com.ruoyi.ql.domain.convert;

import com.ruoyi.ql.domain.bo.QlFinReceivableBo;
import com.ruoyi.ql.domain.export.query.QlFinReceivableReportQuery;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface QlFinReceivableConvert {

    QlFinReceivableConvert INSTANCE = Mappers.getMapper(QlFinReceivableConvert.class);

    QlFinReceivableBo conver(QlFinReceivableReportQuery qlFinReceivableReportQuery);
}
