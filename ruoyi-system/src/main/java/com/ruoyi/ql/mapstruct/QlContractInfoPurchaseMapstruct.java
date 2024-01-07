package com.ruoyi.ql.mapstruct;

/**
 * @author bx 2023/5/17
 */

import com.ruoyi.ql.domain.QlContractInfoPurchase;
import com.ruoyi.ql.domain.bo.QlContractInfoPurchaseBo;
import com.ruoyi.ql.domain.export.QlContractInfoPurchaseExport;
import com.ruoyi.ql.domain.importvo.QlContractInfoPurchaseImport;
import com.ruoyi.ql.domain.vo.QlContractInfoPurchaseVo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface QlContractInfoPurchaseMapstruct {
    QlContractInfoPurchaseMapstruct INSTANCES = Mappers.getMapper(QlContractInfoPurchaseMapstruct.class);

    QlContractInfoPurchaseBo convert(QlContractInfoPurchaseImport qlContractInfoPurchaseImport);

    QlContractInfoPurchaseExport convert(QlContractInfoPurchaseVo qlContractInfoPurchaseVo);

    List<QlContractInfoPurchase> convert(List<QlContractInfoPurchaseBo> qlContractInfoPurchaseBos);
}
