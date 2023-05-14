package com.ruoyi.ql.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.common.core.domain.PageQuery;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.ql.domain.QlOutbound;
import com.ruoyi.ql.domain.bo.QlOutboundBo;
import com.ruoyi.ql.domain.vo.QlOutboundVo;
import com.ruoyi.ql.mapper.QlOutboundMapper;
import com.ruoyi.ql.mapstruct.OutboundAndWarehousingMapstruct;
import com.ruoyi.ql.service.IQlOutboundService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 出库管理Service业务层处理
 *
 * @author ruoyi
 * @date 2023-05-05
 */
@RequiredArgsConstructor
@Service
public class QlOutboundServiceImpl implements IQlOutboundService {

    private final QlOutboundMapper baseMapper;

    /**
     * 查询出库管理
     */
    @Override
    public QlOutboundVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询出库管理列表
     */
    @Override
    public TableDataInfo<QlOutboundVo> queryPageList(QlOutboundBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<QlOutbound> lqw = buildQueryWrapper(bo);
        Page<QlOutboundVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询出库管理列表
     */
    @Override
    public List<QlOutboundVo> queryList(QlOutboundBo bo) {
        LambdaQueryWrapper<QlOutbound> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<QlOutbound> buildQueryWrapper(QlOutboundBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<QlOutbound> lqw = Wrappers.lambdaQuery();
        lqw.eq(StringUtils.isNotBlank(bo.getOutboundCode()), QlOutbound::getOutboundCode, bo.getOutboundCode());
        lqw.eq(bo.getOutboundDate() != null, QlOutbound::getOutboundDate, bo.getOutboundDate());
        lqw.eq(StringUtils.isNotBlank(bo.getSalesperson()), QlOutbound::getSalesperson, bo.getSalesperson());
        lqw.eq(StringUtils.isNotBlank(bo.getSaleContractCode()), QlOutbound::getSaleContractCode, bo.getSaleContractCode());
        lqw.eq(StringUtils.isNotBlank(bo.getPurchaseContractCode()), QlOutbound::getPurchaseContractCode, bo.getPurchaseContractCode());
        lqw.like(StringUtils.isNotBlank(bo.getCustomerName()), QlOutbound::getCustomerName, bo.getCustomerName());
        lqw.eq(StringUtils.isNotBlank(bo.getTelephone()), QlOutbound::getTelephone, bo.getTelephone());
        lqw.eq(StringUtils.isNotBlank(bo.getAddress()), QlOutbound::getAddress, bo.getAddress());
        lqw.eq(StringUtils.isNotBlank(bo.getProudctId()), QlOutbound::getProudctId, bo.getProudctId());
        lqw.like(StringUtils.isNotBlank(bo.getProudctName()), QlOutbound::getProudctName, bo.getProudctName());
        lqw.eq(StringUtils.isNotBlank(bo.getGoodsSearchstandard()), QlOutbound::getGoodsSearchstandard, bo.getGoodsSearchstandard());
        lqw.eq(StringUtils.isNotBlank(bo.getGoodsUnit()), QlOutbound::getGoodsUnit, bo.getGoodsUnit());
        lqw.eq(bo.getBasePrice() != null, QlOutbound::getBasePrice, bo.getBasePrice());
        lqw.eq(bo.getExtraPrice() != null, QlOutbound::getExtraPrice, bo.getExtraPrice());
        lqw.eq(StringUtils.isNotBlank(bo.getFj()), QlOutbound::getFj, bo.getFj());
        lqw.eq(bo.getSaleDate() != null, QlOutbound::getSaleDate, bo.getSaleDate());
        lqw.eq(bo.getSaleNumber() != null, QlOutbound::getSaleNumber, bo.getSaleNumber());
        lqw.eq(bo.getSaleAmount() != null, QlOutbound::getSaleAmount, bo.getSaleAmount());
        lqw.like(StringUtils.isNotBlank(bo.getOutboundUsername()), QlOutbound::getOutboundUsername, bo.getOutboundUsername());
        lqw.eq(StringUtils.isNotBlank(bo.getOutboundReleaseuser()), QlOutbound::getOutboundReleaseuser, bo.getOutboundReleaseuser());
        lqw.eq(bo.getOutboundNumber() != null, QlOutbound::getOutboundNumber, bo.getOutboundNumber());
        lqw.eq(StringUtils.isNotBlank(bo.getProjectId()), QlOutbound::getProjectId, bo.getProjectId());
        lqw.like(StringUtils.isNotBlank(bo.getProjectName()), QlOutbound::getProjectName, bo.getProjectName());
        return lqw;
    }

    /**
     * 新增出库管理
     */
    @Override
    public Boolean insertByBo(QlOutboundBo bo) {
        QlOutbound add = BeanUtil.toBean(bo, QlOutbound.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    @Override
    public void batchInsertBo(List<QlOutboundBo> bos) {
        List<QlOutbound> entity = OutboundAndWarehousingMapstruct.INSTANCES.toEos(bos);
        baseMapper.insertBatch(entity);
    }

    /**
     * 修改出库管理
     */
    @Override
    public Boolean updateByBo(QlOutboundBo bo) {
        QlOutbound update = BeanUtil.toBean(bo, QlOutbound.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(QlOutbound entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除出库管理
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }


}
