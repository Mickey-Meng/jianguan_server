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
import com.ruoyi.ql.domain.bo.QlOutboundBo;
import com.ruoyi.ql.domain.vo.QlOutboundVo;
import com.ruoyi.ql.domain.QlOutbound;
import com.ruoyi.ql.mapper.QlOutboundMapper;
import com.ruoyi.ql.service.IQlOutboundService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 出库管理Service业务层处理
 *
 * @author ruoyi
 * @date 2022-12-11
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
        lqw.like(StringUtils.isNotBlank(bo.getOutboundUsername()), QlOutbound::getOutboundUsername, bo.getOutboundUsername());
        lqw.eq(bo.getOutboundDate() != null, QlOutbound::getOutboundDate, bo.getOutboundDate());
        lqw.like(StringUtils.isNotBlank(bo.getProudctName()), QlOutbound::getProudctName, bo.getProudctName());
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
