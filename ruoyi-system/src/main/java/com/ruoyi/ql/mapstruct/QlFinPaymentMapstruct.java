package com.ruoyi.ql.mapstruct;

import com.ruoyi.ql.domain.vo.QlFinPaymentExport;
import com.ruoyi.ql.domain.vo.QlFinPaymentVo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author bx 2023/5/17
 */
@Mapper
public interface QlFinPaymentMapstruct {
    QlFinPaymentMapstruct INSTANCES = Mappers.getMapper(QlFinPaymentMapstruct.class);

    List<QlFinPaymentExport> toQlFinPaymentExports(List<QlFinPaymentVo> qlFinPaymentVos);


}
