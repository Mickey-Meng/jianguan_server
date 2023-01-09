package com.ruoyi.ql.service;

import com.ruoyi.ql.domain.QlShopGoods;
import com.ruoyi.ql.domain.vo.QlShopGoodsVo;
import com.ruoyi.ql.domain.bo.QlShopGoodsBo;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;

import com.ruoyi.ql.domain.vo.TreeVo;


import java.util.Collection;
import java.util.List;

/**
 * 商品信息Service接口
 *
 * @author ruoyi
 * @date 2022-12-11
 */
public interface IQlShopGoodsService {

    /**
     * 查询商品信息
     */
    QlShopGoodsVo queryById(Long id);

    /**
     * 查询商品信息列表
     */
    TableDataInfo<QlShopGoodsVo> queryPageList(QlShopGoodsBo bo, PageQuery pageQuery);

    /**
     * 查询商品信息列表
     */
    List<QlShopGoodsVo> queryList(QlShopGoodsBo bo);

    /**
     * 新增商品信息
     */
    Boolean insertByBo(QlShopGoodsBo bo);

    /**
     * 修改商品信息
     */
    Boolean updateByBo(QlShopGoodsBo bo);

    /**
     * 校验并批量删除商品信息信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

    List<TreeVo> goodsTree();
}
