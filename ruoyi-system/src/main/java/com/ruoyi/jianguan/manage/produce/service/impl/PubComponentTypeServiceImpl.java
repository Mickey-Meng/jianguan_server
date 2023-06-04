package com.ruoyi.jianguan.manage.produce.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ruoyi.jianguan.manage.produce.domain.bo.PubComponentTypeBo;
import com.ruoyi.jianguan.manage.produce.domain.entity.PubComponentType;
import com.ruoyi.jianguan.manage.produce.domain.vo.PubComponentTypeVo;
import com.ruoyi.jianguan.manage.produce.mapper.PubComponentTypeMapper;
import com.ruoyi.jianguan.manage.produce.service.IPubComponentTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 构建类型Service业务层处理
 *
 * @author ruoyi
 * @date 2023-06-03
 */
@RequiredArgsConstructor
@Service
public class PubComponentTypeServiceImpl implements IPubComponentTypeService {

    private final PubComponentTypeMapper baseMapper;

    /**
     * 查询构建类型
     */
    @Override
    public PubComponentTypeVo queryById(Integer id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询构建类型列表
     */
    @Override
    public TableDataInfo<PubComponentTypeVo> queryPageList(PubComponentTypeBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<PubComponentType> lqw = buildQueryWrapper(bo);
        Page<PubComponentTypeVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询构建类型列表
     */
    @Override
    public List<PubComponentTypeVo> queryList(PubComponentTypeBo bo) {
        LambdaQueryWrapper<PubComponentType> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<PubComponentType> buildQueryWrapper(PubComponentTypeBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<PubComponentType> lqw = Wrappers.lambdaQuery();
        lqw.eq(StringUtils.isNotBlank(bo.getLibraryId()), PubComponentType::getLibraryId, bo.getLibraryId());
        lqw.like(StringUtils.isNotBlank(bo.getName()), PubComponentType::getName, bo.getName());
        lqw.eq(StringUtils.isNotBlank(bo.getCode()), PubComponentType::getCode, bo.getCode());
        return lqw;
    }

    /**
     * 新增构建类型
     */
    @Override
    public Boolean insertByBo(PubComponentTypeBo bo) {
        PubComponentType add = BeanUtil.toBean(bo, PubComponentType.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改构建类型
     */
    @Override
    public Boolean updateByBo(PubComponentTypeBo bo) {
        PubComponentType update = BeanUtil.toBean(bo, PubComponentType.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(PubComponentType entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除构建类型
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Integer> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }

    /**
     * 校验主键是否唯一构建类型信息
     */
    @Override
    public Boolean checkUniqueByPrimaryKey(Integer id) {
        int count = baseMapper.selectCountByPrimaryKey(id);
        return count <= 1;
    }
}
