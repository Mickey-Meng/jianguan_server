package com.ruoyi.ql.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.common.core.domain.PageQuery;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.ql.domain.QlShopGoods;
import com.ruoyi.ql.domain.QlWarehousing;
import com.ruoyi.ql.domain.bo.QlWarehousingBo;
import com.ruoyi.ql.domain.vo.QlWarehousingVo;
import com.ruoyi.ql.mapper.QlShopGoodsMapper;
import com.ruoyi.ql.mapper.QlWarehousingMapper;
import com.ruoyi.ql.service.IQlWarehousingService;
import com.ruoyi.ql.mapstruct.OutboundAndWarehousingMapstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 入库管理Service业务层处理
 *
 * @author ruoyi
 * @date 2022-12-11
 */
@RequiredArgsConstructor
@Service
public class QlWarehousingServiceImpl implements IQlWarehousingService {

    private final QlWarehousingMapper baseMapper;
    private final QlShopGoodsMapper qlShopGoodsMapper;

    /**
     * 查询入库管理
     */
    @Override
    public QlWarehousingVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询入库管理列表
     */
    @Override
    public TableDataInfo<QlWarehousingVo> queryPageList(QlWarehousingBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<QlWarehousing> lqw = buildQueryWrapper(bo);
        Page<QlWarehousingVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询入库管理列表
     */
    @Override
    public List<QlWarehousingVo> queryList(QlWarehousingBo bo) {
        LambdaQueryWrapper<QlWarehousing> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<QlWarehousing> buildQueryWrapper(QlWarehousingBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<QlWarehousing> lqw = Wrappers.lambdaQuery();
        lqw.eq(StringUtils.isNotBlank(bo.getWarehousingCode()), QlWarehousing::getWarehousingCode, bo.getWarehousingCode());
        lqw.like(StringUtils.isNotBlank(bo.getWarehousingUsername()), QlWarehousing::getWarehousingUsername, bo.getWarehousingUsername());
        lqw.eq(StringUtils.isNotBlank(bo.getProudctId()), QlWarehousing::getProudctId, bo.getProudctId());
        lqw.eq(bo.getWarehousingNumber() != null, QlWarehousing::getWarehousingNumber, bo.getWarehousingNumber());
        lqw.eq(bo.getWarehousingDate() != null, QlWarehousing::getWarehousingDate, bo.getWarehousingDate());
        lqw.eq(StringUtils.isNotBlank(bo.getWarehousingStatus()), QlWarehousing::getWarehousingStatus, bo.getWarehousingStatus());
        lqw.eq(bo.getDeptId() != null, QlWarehousing::getDeptId, bo.getDeptId());
        lqw.like(StringUtils.isNotBlank(bo.getProudctName()), QlWarehousing::getProudctName, bo.getProudctName());
        lqw.eq(StringUtils.isNotBlank(bo.getPurchaseOrderId()), QlWarehousing::getPurchaseOrderId, bo.getPurchaseOrderId());
        return lqw;
    }

    /**
     * 新增入库管理
     */
    @Override
    @Transactional
    public Boolean insertByBo(QlWarehousingBo bo) {
        QlWarehousing add = BeanUtil.toBean(bo, QlWarehousing.class);
        validEntityBeforeSave(add);
        String productId = add.getProudctId();
        BigDecimal i = add.getWarehousingNumber();

        QlShopGoods qlShopGoods  = qlShopGoodsMapper.selectById(productId);
        BigDecimal seedNumber = qlShopGoods.getStockNumber();

        if ( i != null){
            qlShopGoods.setStockNumber(seedNumber.add(i));
            qlShopGoodsMapper.updateById(qlShopGoods);
        }

        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    @Override
    public void batchInsertBo(List<QlWarehousingBo> bos) {
        List<QlWarehousing> entity = OutboundAndWarehousingMapstruct.INSTANCES.toQlWarehousing(bos);
        baseMapper.insertBatch(entity);
    }

    /**
     * 修改入库管理
     */
    @Override
    @Transactional
    public Boolean updateByBo(QlWarehousingBo bo) {
        // 1 获取上一次入库清单

        QlWarehousing qlWarehousing = baseMapper.selectById(bo.getId());
        BigDecimal n = qlWarehousing.getWarehousingNumber();


        // 2  获取该产品实时库存数量
       /* QlShopGoods qlShopGoods  = qlShopGoodsMapper.selectById(bo.getProudctId());
        BigDecimal seedNumber = qlShopGoods.getStockNumber();
        // 3 修改后库存数据 =  实时库存 - 上一次入库数量 + 当前页面库存数量
        qlShopGoods.setStockNumber(seedNumber.add(bo.getWarehousingNumber()).subtract(n) );

        qlShopGoodsMapper.updateById(qlShopGoods);*/

        QlWarehousing update = BeanUtil.toBean(bo, QlWarehousing.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(QlWarehousing entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除入库管理
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
