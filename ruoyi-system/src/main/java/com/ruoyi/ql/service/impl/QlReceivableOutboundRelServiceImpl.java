package com.ruoyi.ql.service.impl;


import cn.hutool.core.bean.BeanUtil;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ruoyi.ql.domain.QlReceivableOutboundRel;
import com.ruoyi.ql.domain.bo.QlReceivableOutboundRelBo;
import com.ruoyi.ql.domain.vo.QlReceivableOutboundRelVo;
import com.ruoyi.ql.mapper.QlReceivableOutboundRelMapper;
import com.ruoyi.ql.service.IQlReceivableOutboundRelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 付款与入库关系Service业务层处理
 *
 * @author ruoyi
 * @date 2023-05-11
 */
@RequiredArgsConstructor
@Service
public class QlReceivableOutboundRelServiceImpl implements IQlReceivableOutboundRelService {

    private final QlReceivableOutboundRelMapper baseMapper;

    /**
     * 查询付款与入库关系
     */
    @Override
    public QlReceivableOutboundRelVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询付款与入库关系列表
     */
    @Override
    public TableDataInfo<QlReceivableOutboundRelVo> queryPageList(QlReceivableOutboundRelBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<QlReceivableOutboundRel> lqw = buildQueryWrapper(bo);
        Page<QlReceivableOutboundRelVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询付款与入库关系列表
     */
    @Override
    public List<QlReceivableOutboundRelVo> queryList(QlReceivableOutboundRelBo bo) {
        LambdaQueryWrapper<QlReceivableOutboundRel> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<QlReceivableOutboundRel> buildQueryWrapper(QlReceivableOutboundRelBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<QlReceivableOutboundRel> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getReceivableId() != null, QlReceivableOutboundRel::getReceivableId, bo.getReceivableId());
        lqw.eq(StringUtils.isNotBlank(bo.getOutboundCode()), QlReceivableOutboundRel::getOutboundCode, bo.getOutboundCode());
        lqw.eq(bo.getAmount() != null, QlReceivableOutboundRel::getAmount, bo.getAmount());
        lqw.like(StringUtils.isNotBlank(bo.getCustomerName()), QlReceivableOutboundRel::getCustomerName, bo.getCustomerName());
        lqw.eq(StringUtils.isNotBlank(bo.getSaleContractCode()), QlReceivableOutboundRel::getSaleContractCode, bo.getSaleContractCode());
        return lqw;
    }

    /**
     * 新增付款与入库关系
     */
    @Override
    public Boolean insertByBo(QlReceivableOutboundRelBo bo) {
        QlReceivableOutboundRel add = BeanUtil.toBean(bo, QlReceivableOutboundRel.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改付款与入库关系
     */
    @Override
    public Boolean updateByBo(QlReceivableOutboundRelBo bo) {
        QlReceivableOutboundRel update = BeanUtil.toBean(bo, QlReceivableOutboundRel.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(QlReceivableOutboundRel entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除付款与入库关系
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}