package com.ruoyi.ql.mapstruct;

import com.ruoyi.ql.domain.QlOutbound;
import com.ruoyi.ql.domain.QlWarehousing;
import com.ruoyi.ql.domain.bo.QlOutboundBo;
import com.ruoyi.ql.domain.bo.QlWarehousingBo;
import com.ruoyi.ql.domain.importvo.QlOutboundImport;
import com.ruoyi.ql.domain.vo.OutboundVo;
import com.ruoyi.ql.domain.vo.WarehousingVo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author bx 2023/5/10
 */
@Mapper
public interface QlOutboundAndWarehousingMapstruct {

    QlOutboundAndWarehousingMapstruct INSTANCES = Mappers.getMapper(QlOutboundAndWarehousingMapstruct.class);

    List<QlOutbound> toEos(List<QlOutboundBo> microservicePoList);
    List<QlWarehousing> toQlWarehousing(List<QlWarehousingBo> microservicePoList);
    List<QlOutboundBo> toBos(List<OutboundVo> microservicePoList);
    List<QlWarehousingBo> toBQlWarehousingBos(List<WarehousingVo> microservicePoList);
    QlOutboundBo importToBo(QlOutboundImport qlOutboundImport);
}