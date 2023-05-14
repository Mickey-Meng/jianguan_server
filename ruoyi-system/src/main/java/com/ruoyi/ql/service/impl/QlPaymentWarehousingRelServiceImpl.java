package com.ruoyi.ql.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ruoyi.ql.domain.QlPaymentWarehousingRel;
import com.ruoyi.ql.domain.bo.QlPaymentWarehousingRelBo;
import com.ruoyi.ql.domain.vo.QlPaymentWarehousingRelVo;
import com.ruoyi.ql.mapper.QlPaymentWarehousingRelMapper;
import com.ruoyi.ql.service.IQlPaymentWarehousingRelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 付款与入库关系Service业务层处理
 *
 * @author ruoyi
 * @date 2023-05-10
 */
@RequiredArgsConstructor
@Service
public class QlPaymentWarehousingRelServiceImpl implements IQlPaymentWarehousingRelService {

    private final QlPaymentWarehousingRelMapper baseMapper;

    /**
     * 查询付款与入库关系
     */
    @Override
    public QlPaymentWarehousingRelVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询付款与入库关系列表
     */
    @Override
    public TableDataInfo<QlPaymentWarehousingRelVo> queryPageList(QlPaymentWarehousingRelBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<QlPaymentWarehousingRel> lqw = buildQueryWrapper(bo);
        Page<QlPaymentWarehousingRelVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询付款与入库关系列表
     */
    @Override
    public List<QlPaymentWarehousingRelVo> queryList(QlPaymentWarehousingRelBo bo) {
        LambdaQueryWrapper<QlPaymentWarehousingRel> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<QlPaymentWarehousingRel> buildQueryWrapper(QlPaymentWarehousingRelBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<QlPaymentWarehousingRel> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getPaymentId() != null, QlPaymentWarehousingRel::getPaymentId, bo.getPaymentId());
        lqw.eq(StringUtils.isNotBlank(bo.getWarehousingCode()), QlPaymentWarehousingRel::getWarehousingCode, bo.getWarehousingCode());
        return lqw;
    }

    /**
     * 新增付款与入库关系
     */
    @Override
    public Boolean insertByBo(QlPaymentWarehousingRelBo bo) {
        QlPaymentWarehousingRel add = BeanUtil.toBean(bo, QlPaymentWarehousingRel.class);
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
    public Boolean updateByBo(QlPaymentWarehousingRelBo bo) {
        QlPaymentWarehousingRel update = BeanUtil.toBean(bo, QlPaymentWarehousingRel.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(QlPaymentWarehousingRel entity){
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