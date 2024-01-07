package com.ruoyi.ql.domain.convert;

import com.ruoyi.ql.domain.bo.QlBasisSupplierBo;
import com.ruoyi.ql.domain.bo.QlShopGoodsBo;
import com.ruoyi.ql.domain.export.query.QlBasisSupplierReportQuery;
import com.ruoyi.ql.domain.export.query.QlShopGoodsReportQuery;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface QlShopGoodsConvert {

    QlShopGoodsConvert INSTANCE = Mappers.getMapper(QlShopGoodsConvert.class);

    QlShopGoodsBo conver(QlShopGoodsReportQuery qlShopGoodsReportQuery);
}
