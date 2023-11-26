package com.ruoyi.ql.domain.convert;

import com.ruoyi.ql.domain.bo.QlOutboundBo;
import com.ruoyi.ql.domain.bo.QlWarehousingBo;
import com.ruoyi.ql.domain.export.query.QlOutboundReportQuery;
import com.ruoyi.ql.domain.export.query.QlWarehousingReportQuery;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface WarehousingConvert {

    WarehousingConvert INSTANCE = Mappers.getMapper(WarehousingConvert.class);

    QlWarehousingBo conver(QlWarehousingReportQuery qlWarehousingReportQuery);
}
