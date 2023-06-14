package com.ruoyi.jianguan.manage.project.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.jianguan.manage.project.domain.bo.DataDictionaryBo;
import com.ruoyi.jianguan.manage.project.domain.entity.DataDictionary;
import com.ruoyi.jianguan.manage.project.domain.vo.DataDictionaryVo;
import com.ruoyi.jianguan.manage.project.mapper.DataDictionaryMapper;
import com.ruoyi.jianguan.manage.project.service.IDataDictionaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 商品类别Service业务层处理
 *
 * @author ruoyi
 * @date 2022-12-11
 */
@RequiredArgsConstructor
@Service
public class DataDictionaryServiceImpl implements IDataDictionaryService {

    private final DataDictionaryMapper baseMapper;

    /**
     * 查询商品类别
     */
    @Override
    public DataDictionaryVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    @Override
    public DataDictionaryVo queryByCode(String code) {
        DataDictionary dataDictionary = new DataDictionary();
        dataDictionary.setCode(code);
        return baseMapper.selectVoOne(Wrappers.lambdaQuery(dataDictionary));
    }


    /**
     * 查询商品类别列表
     */
    @Override
    public List<DataDictionaryVo> queryList(DataDictionaryBo bo) {
        LambdaQueryWrapper<DataDictionary> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    @Override
    public List<DataDictionaryVo> getFileTypesByPCode(String pCode) {
        return baseMapper.selectVoListByPCode(pCode);
    }

    private LambdaQueryWrapper<DataDictionary> buildQueryWrapper(DataDictionaryBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<DataDictionary> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getParentId() != null, DataDictionary::getParentId, bo.getParentId());
        lqw.eq(StringUtils.isNotBlank(bo.getAncestors()), DataDictionary::getAncestors, bo.getAncestors());
        lqw.eq(StringUtils.isNotBlank(bo.getCode()), DataDictionary::getCode, bo.getCode());
        lqw.like(StringUtils.isNotBlank(bo.getName()), DataDictionary::getName, bo.getName());
        lqw.eq(bo.getOrderNum() != null, DataDictionary::getOrderNum, bo.getOrderNum());
        return lqw;
    }

    /**
     * 新增商品类别
     */
    @Override
    public Boolean insertByBo(DataDictionaryBo bo) {
        DataDictionary add = BeanUtil.toBean(bo, DataDictionary.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改商品类别
     */
    @Override
    public Boolean updateByBo(DataDictionaryBo bo) {
        DataDictionary update = BeanUtil.toBean(bo, DataDictionary.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(DataDictionary entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除商品类别
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
