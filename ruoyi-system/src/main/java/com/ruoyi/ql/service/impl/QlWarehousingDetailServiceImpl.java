package com.ruoyi.ql.service.impl;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ruoyi.ql.domain.QlWarehousingDetail;
import com.ruoyi.ql.domain.bo.QlWarehousingDetailBo;
import com.ruoyi.ql.domain.vo.QlWarehousingDetailVo;
import com.ruoyi.ql.mapper.QlWarehousingDetailMapper;
import com.ruoyi.ql.service.IQlWarehousingDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 入库单明细Service业务层处理
 *
 * @author ruoyi
 * @date 2023-05-18
 */
@RequiredArgsConstructor
@Service
public class QlWarehousingDetailServiceImpl implements IQlWarehousingDetailService {

    private final QlWarehousingDetailMapper baseMapper;

    /**
     * 查询入库单明细
     */
    @Override
    public QlWarehousingDetailVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询入库单明细列表
     */
    @Override
    public TableDataInfo<QlWarehousingDetailVo> queryPageList(QlWarehousingDetailBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<QlWarehousingDetail> lqw = buildQueryWrapper(bo);
        Page<QlWarehousingDetailVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询入库单明细列表
     */
    @Override
    public List<QlWarehousingDetailVo> queryList(QlWarehousingDetailBo bo) {
        LambdaQueryWrapper<QlWarehousingDetail> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<QlWarehousingDetail> buildQueryWrapper(QlWarehousingDetailBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<QlWarehousingDetail> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getInventoryId() != null, QlWarehousingDetail::getInventoryId, bo.getInventoryId());
        lqw.eq(StringUtils.isNotBlank(bo.getInventoryCode()), QlWarehousingDetail::getInventoryCode, bo.getInventoryCode());
        lqw.eq(StringUtils.isNotBlank(bo.getGoodsId()), QlWarehousingDetail::getGoodsId, bo.getGoodsId());
        lqw.like(StringUtils.isNotBlank(bo.getGoodsName()), QlWarehousingDetail::getGoodsName, bo.getGoodsName());
        lqw.eq(bo.getBasePrice() != null, QlWarehousingDetail::getBasePrice, bo.getBasePrice());
        lqw.eq(bo.getIncomePrice() != null, QlWarehousingDetail::getIncomePrice, bo.getIncomePrice());
        lqw.eq(bo.getExtraPrice() != null, QlWarehousingDetail::getExtraPrice, bo.getExtraPrice());
        lqw.eq(bo.getInventoryNumber() != null, QlWarehousingDetail::getInventoryNumber, bo.getInventoryNumber());
        lqw.eq(bo.getAmount() != null, QlWarehousingDetail::getAmount, bo.getAmount());
        lqw.eq(bo.getDeptId() != null, QlWarehousingDetail::getDeptId, bo.getDeptId());
        lqw.in(CollUtil.isNotEmpty(bo.getInventoryIds()), QlWarehousingDetail:: getInventoryId, bo.getInventoryIds());
        lqw.in(CollUtil.isNotEmpty(bo.getGoodsIds()), QlWarehousingDetail:: getGoodsId, bo.getGoodsIds());
        return lqw;
    }

    /**
     * 新增入库单明细
     */
    @Override
    public Boolean insertByBo(QlWarehousingDetailBo bo) {
        QlWarehousingDetail add = BeanUtil.toBean(bo, QlWarehousingDetail.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改入库单明细
     */
    @Override
    public Boolean updateByBo(QlWarehousingDetailBo bo) {
        QlWarehousingDetail update = BeanUtil.toBean(bo, QlWarehousingDetail.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(QlWarehousingDetail entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除入库单明细
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}