package com.ruoyi.ql.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.ruoyi.ql.domain.bo.QlShopGoodsSeedBo;
import com.ruoyi.ql.domain.vo.QlShopGoodsSeedVo;
import com.ruoyi.ql.domain.QlShopGoodsSeed;
import com.ruoyi.ql.mapper.QlShopGoodsSeedMapper;
import com.ruoyi.ql.service.IQlShopGoodsSeedService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 商品库存信息Service业务层处理
 *
 * @author ruoyi
 * @date 2022-12-11
 */
@RequiredArgsConstructor
@Service
public class QlShopGoodsSeedServiceImpl implements IQlShopGoodsSeedService {

    private final QlShopGoodsSeedMapper baseMapper;

    /**
     * 查询商品库存信息
     */
    @Override
    public QlShopGoodsSeedVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询商品库存信息列表
     */
    @Override
    public TableDataInfo<QlShopGoodsSeedVo> queryPageList(QlShopGoodsSeedBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<QlShopGoodsSeed> lqw = buildQueryWrapper(bo);
        Page<QlShopGoodsSeedVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询商品库存信息列表
     */
    @Override
    public List<QlShopGoodsSeedVo> queryList(QlShopGoodsSeedBo bo) {
        LambdaQueryWrapper<QlShopGoodsSeed> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<QlShopGoodsSeed> buildQueryWrapper(QlShopGoodsSeedBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<QlShopGoodsSeed> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getGoodsId() != null, QlShopGoodsSeed::getGoodsId, bo.getGoodsId());
        lqw.eq(bo.getSafetyStock() != null, QlShopGoodsSeed::getSafetyStock, bo.getSafetyStock());
        lqw.eq(bo.getStockNumber() != null, QlShopGoodsSeed::getStockNumber, bo.getStockNumber());
        return lqw;
    }

    /**
     * 新增商品库存信息
     */
    @Override
    public Boolean insertByBo(QlShopGoodsSeedBo bo) {
        QlShopGoodsSeed add = BeanUtil.toBean(bo, QlShopGoodsSeed.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改商品库存信息
     */
    @Override
    public Boolean updateByBo(QlShopGoodsSeedBo bo) {
        QlShopGoodsSeed update = BeanUtil.toBean(bo, QlShopGoodsSeed.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(QlShopGoodsSeed entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除商品库存信息
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
