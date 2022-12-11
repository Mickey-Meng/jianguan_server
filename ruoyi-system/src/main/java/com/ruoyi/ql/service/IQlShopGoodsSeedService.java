package com.ruoyi.ql.service;

import com.ruoyi.ql.domain.QlShopGoodsSeed;
import com.ruoyi.ql.domain.vo.QlShopGoodsSeedVo;
import com.ruoyi.ql.domain.bo.QlShopGoodsSeedBo;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 商品库存信息Service接口
 *
 * @author ruoyi
 * @date 2022-12-11
 */
public interface IQlShopGoodsSeedService {

    /**
     * 查询商品库存信息
     */
    QlShopGoodsSeedVo queryById(Long id);

    /**
     * 查询商品库存信息列表
     */
    TableDataInfo<QlShopGoodsSeedVo> queryPageList(QlShopGoodsSeedBo bo, PageQuery pageQuery);

    /**
     * 查询商品库存信息列表
     */
    List<QlShopGoodsSeedVo> queryList(QlShopGoodsSeedBo bo);

    /**
     * 新增商品库存信息
     */
    Boolean insertByBo(QlShopGoodsSeedBo bo);

    /**
     * 修改商品库存信息
     */
    Boolean updateByBo(QlShopGoodsSeedBo bo);

    /**
     * 校验并批量删除商品库存信息信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
