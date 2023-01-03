package com.ruoyi.ql.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ruoyi.ql.domain.vo.QlWhWarehouseVo;
import com.ruoyi.ql.mapper.QlWhWarehouseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.ruoyi.ql.domain.bo.QlWhReservoirBo;
import com.ruoyi.ql.domain.vo.QlWhReservoirVo;
import com.ruoyi.ql.domain.QlWhReservoir;
import com.ruoyi.ql.mapper.QlWhReservoirMapper;
import com.ruoyi.ql.service.IQlWhReservoirService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 库区设置Service业务层处理
 *
 * @author ruoyi
 * @date 2022-12-11
 */
@RequiredArgsConstructor
@Service
public class QlWhReservoirServiceImpl implements IQlWhReservoirService {

    private final QlWhReservoirMapper baseMapper;
    /** 仓库  **/
    private final QlWhWarehouseMapper qlWhWarehouseMapper;

    /**
     * 查询库区设置
     */
    @Override
    public QlWhReservoirVo queryById(Long id){
        QlWhReservoirVo qlWhReservoirVo = baseMapper.selectVoById(id);
        QlWhWarehouseVo qlWhWarehouseVo = qlWhWarehouseMapper.selectVoById(qlWhReservoirVo.getWarehouseId());
        qlWhReservoirVo.setWarehouseName(qlWhWarehouseVo.getWarehouseName());
        return qlWhReservoirVo;
    }

    /**
     * 查询库区设置列表
     */
    @Override
    public TableDataInfo<QlWhReservoirVo> queryPageList(QlWhReservoirBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<QlWhReservoir> lqw = buildQueryWrapper(bo);
        Page<QlWhReservoirVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询库区设置列表
     */
    @Override
    public List<QlWhReservoirVo> queryList(QlWhReservoirBo bo) {
        LambdaQueryWrapper<QlWhReservoir> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<QlWhReservoir> buildQueryWrapper(QlWhReservoirBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<QlWhReservoir> lqw = Wrappers.lambdaQuery();
        lqw.eq(StringUtils.isNotBlank(bo.getReservoirCode()), QlWhReservoir::getReservoirCode, bo.getReservoirCode());
        lqw.like(StringUtils.isNotBlank(bo.getReservoirName()), QlWhReservoir::getReservoirName, bo.getReservoirName());
        lqw.eq(bo.getWarehouseId() != null, QlWhReservoir::getWarehouseId, bo.getWarehouseId());
        return lqw;
    }

    /**
     * 新增库区设置
     */
    @Override
    public Boolean insertByBo(QlWhReservoirBo bo) {
        QlWhReservoir add = BeanUtil.toBean(bo, QlWhReservoir.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改库区设置
     */
    @Override
    public Boolean updateByBo(QlWhReservoirBo bo) {
        QlWhReservoir update = BeanUtil.toBean(bo, QlWhReservoir.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(QlWhReservoir entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除库区设置
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
