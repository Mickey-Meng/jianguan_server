package com.ruoyi.ql.mapstruct;

/**
 * @author bx 2023/5/17
 */

import com.ruoyi.ql.domain.vo.QlContractInfoPurchaseExport;
import com.ruoyi.ql.domain.vo.QlContractInfoPurchaseVo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface QlContractInfoPurchaseMapstruct {
    QlContractInfoPurchaseMapstruct INSTANCES = Mappers.getMapper(QlContractInfoPurchaseMapstruct.class);
    List<QlContractInfoPurchaseExport> toQlContractInfoPurchaseExports(List<QlContractInfoPurchaseVo> qlContractInfoPurchaseVos);

}
