package com.ruoyi.ql.mapstruct;

import com.ruoyi.ql.domain.bo.QlWarehousingDetailBo;
import com.ruoyi.ql.domain.importvo.QlOutboundImport;
import com.ruoyi.ql.domain.importvo.QlWarehousingImport;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author bx 2023/5/10
 */
@Mapper
public interface QlWarehousingDetailMapstruct {

    QlWarehousingDetailMapstruct INSTANCES = Mappers.getMapper(QlWarehousingDetailMapstruct.class);

    List<QlWarehousingDetailBo> outboundImportToBos(List<QlOutboundImport> qlOutboundImports);

    List<QlWarehousingDetailBo> WarehousingImportToBos(List<QlWarehousingImport> qlWarehousingImports);

}