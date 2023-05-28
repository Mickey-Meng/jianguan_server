package com.ruoyi.ql.mapstruct;

import com.ruoyi.ql.domain.QlOutbound;
import com.ruoyi.ql.domain.QlWarehousing;
import com.ruoyi.ql.domain.bo.QlOutboundBo;
import com.ruoyi.ql.domain.bo.QlWarehousingBo;
import com.ruoyi.ql.domain.importvo.OutboundImportVo;
import com.ruoyi.ql.domain.importvo.WarehousingImportVo;
import com.ruoyi.ql.domain.vo.OutboundVo;
import com.ruoyi.ql.domain.vo.WarehousingVo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author bx 2023/5/10
 */
@Mapper
public interface WarehousingMapstruct {

    WarehousingMapstruct INSTANCES = Mappers.getMapper(WarehousingMapstruct.class);

    QlWarehousingBo importToBo(WarehousingImportVo warehousingImport);
}