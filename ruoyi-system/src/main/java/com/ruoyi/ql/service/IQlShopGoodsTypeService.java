package com.ruoyi.ql.service;

import com.ruoyi.ql.domain.QlShopGoodsType;
import com.ruoyi.ql.domain.vo.QlShopGoodsTypeVo;
import com.ruoyi.ql.domain.bo.QlShopGoodsTypeBo;

import java.util.Collection;
import java.util.List;

/**
 * 商品类别Service接口
 *
 * @author ruoyi
 * @date 2022-12-11
 */
public interface IQlShopGoodsTypeService {

    /**
     * 查询商品类别
     */
    QlShopGoodsTypeVo queryById(Long id);


    /**
     * 查询商品类别列表
     */
    List<QlShopGoodsTypeVo> queryList(QlShopGoodsTypeBo bo);

    /**
     * 新增商品类别
     */
    Boolean insertByBo(QlShopGoodsTypeBo bo);

    /**
     * 修改商品类别
     */
    Boolean updateByBo(QlShopGoodsTypeBo bo);

    /**
     * 校验并批量删除商品类别信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
