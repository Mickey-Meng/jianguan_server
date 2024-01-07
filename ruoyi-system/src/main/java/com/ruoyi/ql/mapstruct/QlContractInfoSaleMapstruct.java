package com.ruoyi.ql.mapstruct;

import com.ruoyi.ql.domain.bo.QlContractInfoSaleBo;
import com.ruoyi.ql.domain.export.QlContractInfoSaleExport;
import com.ruoyi.ql.domain.importvo.QlContractInfoSaleImport;
import com.ruoyi.ql.domain.vo.QlContractInfoSaleVo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface QlContractInfoSaleMapstruct {
    QlContractInfoSaleMapstruct INSTANCES = Mappers.getMapper(QlContractInfoSaleMapstruct.class);

    QlContractInfoSaleBo convert(QlContractInfoSaleImport qlContractInfoSaleImport);

    List<QlContractInfoSaleExport> convert(List<QlContractInfoSaleVo> qlContractInfoSaleVos);


    QlContractInfoSaleExport convert(QlContractInfoSaleVo qlContractInfoSaleVos);

}
