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
import com.ruoyi.ql.domain.bo.QlWhWarehouseBo;
import com.ruoyi.ql.domain.vo.QlWhWarehouseVo;
import com.ruoyi.ql.domain.QlWhWarehouse;
import com.ruoyi.ql.mapper.QlWhWarehouseMapper;
import com.ruoyi.ql.service.IQlWhWarehouseService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 仓库设置Service业务层处理
 *
 * @author ruoyi
 * @date 2022-12-11
 */
@RequiredArgsConstructor
@Service
public class QlWhWarehouseServiceImpl implements IQlWhWarehouseService {

    private final QlWhWarehouseMapper baseMapper;

    /**
     * 查询仓库设置
     */
    @Override
    public QlWhWarehouseVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询仓库设置列表
     */
    @Override
    public TableDataInfo<QlWhWarehouseVo> queryPageList(QlWhWarehouseBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<QlWhWarehouse> lqw = buildQueryWrapper(bo);
        Page<QlWhWarehouseVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询仓库设置列表
     */
    @Override
    public List<QlWhWarehouseVo> queryList(QlWhWarehouseBo bo) {
        LambdaQueryWrapper<QlWhWarehouse> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<QlWhWarehouse> buildQueryWrapper(QlWhWarehouseBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<QlWhWarehouse> lqw = Wrappers.lambdaQuery();
        lqw.eq(StringUtils.isNotBlank(bo.getWarehouseCode()), QlWhWarehouse::getWarehouseCode, bo.getWarehouseCode());
        lqw.like(StringUtils.isNotBlank(bo.getWarehouseName()), QlWhWarehouse::getWarehouseName, bo.getWarehouseName());
        lqw.eq(StringUtils.isNotBlank(bo.getCity()), QlWhWarehouse::getCity, bo.getCity());
        lqw.eq(StringUtils.isNotBlank(bo.getAddress()), QlWhWarehouse::getAddress, bo.getAddress());
        lqw.eq(StringUtils.isNotBlank(bo.getPrincipal()), QlWhWarehouse::getPrincipal, bo.getPrincipal());
        lqw.eq(StringUtils.isNotBlank(bo.getTelephone()), QlWhWarehouse::getTelephone, bo.getTelephone());
        return lqw;
    }

    /**
     * 新增仓库设置
     */
    @Override
    public Boolean insertByBo(QlWhWarehouseBo bo) {
        QlWhWarehouse add = BeanUtil.toBean(bo, QlWhWarehouse.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改仓库设置
     */
    @Override
    public Boolean updateByBo(QlWhWarehouseBo bo) {
        QlWhWarehouse update = BeanUtil.toBean(bo, QlWhWarehouse.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(QlWhWarehouse entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除仓库设置
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
