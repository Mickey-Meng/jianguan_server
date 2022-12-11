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
import com.ruoyi.ql.domain.bo.QlWarehousingBo;
import com.ruoyi.ql.domain.vo.QlWarehousingVo;
import com.ruoyi.ql.domain.QlWarehousing;
import com.ruoyi.ql.mapper.QlWarehousingMapper;
import com.ruoyi.ql.service.IQlWarehousingService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

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
        lqw.eq(bo.getWarehousingDate() != null, QlWarehousing::getWarehousingDate, bo.getWarehousingDate());
        lqw.like(StringUtils.isNotBlank(bo.getProudctName()), QlWarehousing::getProudctName, bo.getProudctName());
        return lqw;
    }

    /**
     * 新增入库管理
     */
    @Override
    public Boolean insertByBo(QlWarehousingBo bo) {
        QlWarehousing add = BeanUtil.toBean(bo, QlWarehousing.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改入库管理
     */
    @Override
    public Boolean updateByBo(QlWarehousingBo bo) {
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
