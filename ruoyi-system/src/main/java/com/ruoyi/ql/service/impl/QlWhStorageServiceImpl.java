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
import com.ruoyi.ql.domain.bo.QlWhStorageBo;
import com.ruoyi.ql.domain.vo.QlWhStorageVo;
import com.ruoyi.ql.domain.QlWhStorage;
import com.ruoyi.ql.mapper.QlWhStorageMapper;
import com.ruoyi.ql.service.IQlWhStorageService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 库位(储位)设置Service业务层处理
 *
 * @author ruoyi
 * @date 2022-12-11
 */
@RequiredArgsConstructor
@Service
public class QlWhStorageServiceImpl implements IQlWhStorageService {

    private final QlWhStorageMapper baseMapper;

    /**
     * 查询库位(储位)设置
     */
    @Override
    public QlWhStorageVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询库位(储位)设置列表
     */
    @Override
    public TableDataInfo<QlWhStorageVo> queryPageList(QlWhStorageBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<QlWhStorage> lqw = buildQueryWrapper(bo);
        Page<QlWhStorageVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询库位(储位)设置列表
     */
    @Override
    public List<QlWhStorageVo> queryList(QlWhStorageBo bo) {
        LambdaQueryWrapper<QlWhStorage> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<QlWhStorage> buildQueryWrapper(QlWhStorageBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<QlWhStorage> lqw = Wrappers.lambdaQuery();
        lqw.eq(StringUtils.isNotBlank(bo.getStorageCode()), QlWhStorage::getStorageCode, bo.getStorageCode());
        lqw.like(StringUtils.isNotBlank(bo.getStorageName()), QlWhStorage::getStorageName, bo.getStorageName());
        lqw.eq(StringUtils.isNotBlank(bo.getStorageBarcode()), QlWhStorage::getStorageBarcode, bo.getStorageBarcode());
        lqw.eq(bo.getReservoirId() != null, QlWhStorage::getReservoirId, bo.getReservoirId());
        lqw.eq(StringUtils.isNotBlank(bo.getIsEmpty()), QlWhStorage::getIsEmpty, bo.getIsEmpty());
        lqw.eq(StringUtils.isNotBlank(bo.getIsDisable()), QlWhStorage::getIsDisable, bo.getIsDisable());
        return lqw;
    }

    /**
     * 新增库位(储位)设置
     */
    @Override
    public Boolean insertByBo(QlWhStorageBo bo) {
        QlWhStorage add = BeanUtil.toBean(bo, QlWhStorage.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改库位(储位)设置
     */
    @Override
    public Boolean updateByBo(QlWhStorageBo bo) {
        QlWhStorage update = BeanUtil.toBean(bo, QlWhStorage.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(QlWhStorage entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除库位(储位)设置
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
