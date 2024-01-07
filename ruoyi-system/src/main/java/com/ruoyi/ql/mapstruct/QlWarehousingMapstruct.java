package com.ruoyi.ql.mapstruct;

import com.ruoyi.ql.domain.bo.QlWarehousingBo;
import com.ruoyi.ql.domain.importvo.QlWarehousingImport;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author bx 2023/5/10
 */
@Mapper
public interface QlWarehousingMapstruct {

    QlWarehousingMapstruct INSTANCES = Mappers.getMapper(QlWarehousingMapstruct.class);

    QlWarehousingBo importToBo(QlWarehousingImport qlWarehousingImport);
}