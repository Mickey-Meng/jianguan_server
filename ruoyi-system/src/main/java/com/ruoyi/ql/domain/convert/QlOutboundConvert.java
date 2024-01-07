package com.ruoyi.ql.domain.convert;

import com.ruoyi.ql.domain.bo.QlOutboundBo;
import com.ruoyi.ql.domain.export.query.QlOutboundReportQuery;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface QlOutboundConvert {

    QlOutboundConvert INSTANCE = Mappers.getMapper(QlOutboundConvert.class);

    QlOutboundBo conver(QlOutboundReportQuery qlOutboundReportQuery);
}
