package com.ruoyi.ql.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ruoyi.ql.domain.QlInvoiceItem;
import com.ruoyi.ql.domain.bo.QlInvoiceItemBo;
import com.ruoyi.ql.domain.vo.QlInvoiceItemVo;
import com.ruoyi.ql.mapper.QlInvoiceItemMapper;
import com.ruoyi.ql.service.IQlInvoiceItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 【请填写功能名称】Service业务层处理
 *
 * @author mickey
 * @date 2023-11-07
 */
@RequiredArgsConstructor
@Service
public class QlInvoiceItemServiceImpl implements IQlInvoiceItemService {

    private final QlInvoiceItemMapper baseMapper;

    /**
     * 查询【请填写功能名称】
     */
    @Override
    public QlInvoiceItemVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询【请填写功能名称】列表
     */
    @Override
    public TableDataInfo<QlInvoiceItemVo> queryPageList(QlInvoiceItemBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<QlInvoiceItem> lqw = buildQueryWrapper(bo);
        Page<QlInvoiceItemVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询【请填写功能名称】列表
     */
    @Override
    public List<QlInvoiceItemVo> queryList(QlInvoiceItemBo bo) {
        LambdaQueryWrapper<QlInvoiceItem> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<QlInvoiceItem> buildQueryWrapper(QlInvoiceItemBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<QlInvoiceItem> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getSourceId() != null, QlInvoiceItem::getSourceId, bo.getSourceId());
        lqw.eq(StringUtils.isNotBlank(bo.getSourceType()), QlInvoiceItem::getSourceType, bo.getSourceType());
        lqw.eq(StringUtils.isNotBlank(bo.getCode()), QlInvoiceItem::getCode, bo.getCode());
        lqw.eq(StringUtils.isNotBlank(bo.getContractCode()), QlInvoiceItem::getContractCode, bo.getContractCode());
        lqw.eq(bo.getAmount() != null, QlInvoiceItem::getAmount, bo.getAmount());
        lqw.eq(bo.getStatus() != null, QlInvoiceItem::getStatus, bo.getStatus());
        lqw.eq(bo.getCreateBy() != null, QlInvoiceItem::getCreateBy, bo.getCreateBy());
        lqw.eq(bo.getUpdateBy() != null, QlInvoiceItem::getUpdateBy, bo.getUpdateBy());
        lqw.in(CollUtil.isNotEmpty(bo.getSourceIds()), QlInvoiceItem::getSourceId, bo.getSourceIds());
        return lqw;
    }

    /**
     * 新增【请填写功能名称】
     */
    @Override
    public Boolean insertByBo(QlInvoiceItemBo bo) {
        QlInvoiceItem add = BeanUtil.toBean(bo, QlInvoiceItem.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改【请填写功能名称】
     */
    @Override
    public Boolean updateByBo(QlInvoiceItemBo bo) {
        QlInvoiceItem update = BeanUtil.toBean(bo, QlInvoiceItem.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(QlInvoiceItem entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除【请填写功能名称】
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }

    /**
     * 批量删除【请填写功能名称】
     */
    @Override
    public Boolean deleteBySourceId(Long sourceId) {
        if(ObjectUtil.isNull(sourceId)) {
            return false;
        }
        QlInvoiceItemBo bo = new QlInvoiceItemBo();
        bo.setSourceId(sourceId);
        LambdaQueryWrapper<QlInvoiceItem> qlInvoiceItemLambdaQueryWrapper = buildQueryWrapper(bo);
        return baseMapper.delete(qlInvoiceItemLambdaQueryWrapper) > 0;
    }

}
