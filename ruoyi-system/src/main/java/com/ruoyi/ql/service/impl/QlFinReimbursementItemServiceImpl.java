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
import com.ruoyi.ql.domain.bo.QlFinReimbursementItemBo;
import com.ruoyi.ql.domain.vo.QlFinReimbursementItemVo;
import com.ruoyi.ql.domain.QlFinReimbursementItem;
import com.ruoyi.ql.mapper.QlFinReimbursementItemMapper;
import com.ruoyi.ql.service.IQlFinReimbursementItemService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 费用报销明细Service业务层处理
 *
 * @author ruoyi
 * @date 2022-12-11
 */
@RequiredArgsConstructor
@Service
public class QlFinReimbursementItemServiceImpl implements IQlFinReimbursementItemService {

    private final QlFinReimbursementItemMapper baseMapper;

    /**
     * 查询费用报销明细
     */
    @Override
    public QlFinReimbursementItemVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询费用报销明细列表
     */
    @Override
    public TableDataInfo<QlFinReimbursementItemVo> queryPageList(QlFinReimbursementItemBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<QlFinReimbursementItem> lqw = buildQueryWrapper(bo);
        Page<QlFinReimbursementItemVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询费用报销明细列表
     */
    @Override
    public List<QlFinReimbursementItemVo> queryList(QlFinReimbursementItemBo bo) {
        LambdaQueryWrapper<QlFinReimbursementItem> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<QlFinReimbursementItem> buildQueryWrapper(QlFinReimbursementItemBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<QlFinReimbursementItem> lqw = Wrappers.lambdaQuery();
        lqw.eq(StringUtils.isNotBlank(bo.getReimbursementOrderId()), QlFinReimbursementItem::getReimbursementOrderId, bo.getReimbursementOrderId());
        lqw.eq(StringUtils.isNotBlank(bo.getFinReimbursementType()), QlFinReimbursementItem::getFinReimbursementType, bo.getFinReimbursementType());
        lqw.eq(bo.getReimbursementDate() != null, QlFinReimbursementItem::getReimbursementDate, bo.getReimbursementDate());
        lqw.eq(bo.getFinAmount() != null, QlFinReimbursementItem::getFinAmount, bo.getFinAmount());
        return lqw;
    }

    /**
     * 新增费用报销明细
     */
    @Override
    public Boolean insertByBo(QlFinReimbursementItemBo bo) {
        QlFinReimbursementItem add = BeanUtil.toBean(bo, QlFinReimbursementItem.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改费用报销明细
     */
    @Override
    public Boolean updateByBo(QlFinReimbursementItemBo bo) {
        QlFinReimbursementItem update = BeanUtil.toBean(bo, QlFinReimbursementItem.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(QlFinReimbursementItem entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除费用报销明细
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
