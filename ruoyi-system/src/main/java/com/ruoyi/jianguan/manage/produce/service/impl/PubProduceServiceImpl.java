package com.ruoyi.jianguan.manage.produce.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ruoyi.jianguan.manage.produce.domain.bo.PubProduceBo;
import com.ruoyi.jianguan.manage.produce.domain.entity.PubProduce;
import com.ruoyi.jianguan.manage.produce.domain.vo.PubProduceVo;
import com.ruoyi.jianguan.manage.produce.mapper.PubProduceMapper;
import com.ruoyi.jianguan.manage.produce.service.IPubProduceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 工序信息Service业务层处理
 *
 * @author ruoyi
 * @date 2023-06-03
 */
@RequiredArgsConstructor
@Service
public class PubProduceServiceImpl implements IPubProduceService {

    private final PubProduceMapper baseMapper;

    /**
     * 查询工序信息
     */
    @Override
    public PubProduceVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询工序信息列表
     */
    @Override
    public TableDataInfo<PubProduceVo> queryPageList(PubProduceBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<PubProduce> lqw = buildQueryWrapper(bo);
        Page<PubProduceVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询工序信息列表
     */
    @Override
    public List<PubProduceVo> queryList(PubProduceBo bo) {
        LambdaQueryWrapper<PubProduce> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<PubProduce> buildQueryWrapper(PubProduceBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<PubProduce> lqw = Wrappers.lambdaQuery();
        lqw.eq(StringUtils.isNotBlank(bo.getComponentTypeCode()), PubProduce::getComponentTypeCode, bo.getComponentTypeCode());
        lqw.like(StringUtils.isNotBlank(bo.getName()), PubProduce::getName, bo.getName());
        lqw.eq(bo.getRangee() != null, PubProduce::getRangee, bo.getRangee());
        lqw.eq(bo.getIsvaild() != null, PubProduce::getIsvaild, bo.getIsvaild());
        return lqw;
    }

    /**
     * 新增工序信息
     */
    @Override
    public Boolean insertByBo(PubProduceBo bo) {
        PubProduce add = BeanUtil.toBean(bo, PubProduce.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改工序信息
     */
    @Override
    public Boolean updateByBo(PubProduceBo bo) {
        PubProduce update = BeanUtil.toBean(bo, PubProduce.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(PubProduce entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除工序信息
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }

    /**
     * 校验主键是否唯一工序信息信息
     */
    @Override
    public Boolean checkUniqueByPrimaryKey(Long id) {
        int count = baseMapper.selectCountByPrimaryKey(id);
        return count <= 1;
    }

    @Override
    public List<PubProduce> getProduceListByTypeId(Long typeId) {
        return baseMapper.selectProduceListByTypeId(typeId);
    }
}
