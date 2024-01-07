package com.ruoyi.ql.domain.convert;

import com.ruoyi.ql.domain.bo.QlWarehousingBo;
import com.ruoyi.ql.domain.export.query.QlWarehousingReportQuery;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface QlWarehousingConvert {

    QlWarehousingConvert INSTANCE = Mappers.getMapper(QlWarehousingConvert.class);

    QlWarehousingBo conver(QlWarehousingReportQuery qlWarehousingReportQuery);
}
