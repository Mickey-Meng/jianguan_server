package com.ruoyi.jianguan.manage.produce.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.util.ObjectUtil;
import com.google.common.collect.Maps;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ruoyi.common.utils.TreeBuildUtils;
import com.ruoyi.jianguan.manage.produce.domain.bo.PubProduceLibraryBo;
import com.ruoyi.jianguan.manage.produce.domain.entity.PubComponentType;
import com.ruoyi.jianguan.manage.produce.domain.entity.PubProduce;
import com.ruoyi.jianguan.manage.produce.domain.vo.PubProduceLibraryVo;
import com.ruoyi.jianguan.manage.produce.mapper.PubComponentTypeMapper;
import com.ruoyi.jianguan.manage.produce.mapper.PubProduceLibraryMapper;
import com.ruoyi.jianguan.manage.produce.mapper.PubProduceMapper;
import com.ruoyi.jianguan.manage.produce.service.IPubProduceLibraryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.ruoyi.jianguan.manage.produce.domain.entity.PubProduceLibrary;

import java.util.List;
import java.util.Map;
import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 工序库Service业务层处理
 *
 * @author ruoyi
 * @date 2023-06-03
 */
@RequiredArgsConstructor
@Service
public class PubProduceLibraryServiceImpl implements IPubProduceLibraryService {

    private final PubProduceLibraryMapper baseMapper;
    private final PubComponentTypeMapper componentTypeMapper;
    private final PubProduceMapper pubProduceMapper;

    /**
     * 查询工序库
     */
    @Override
    public PubProduceLibraryVo queryById(Integer id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询工序库列表
     */
    @Override
    public TableDataInfo<PubProduceLibraryVo> queryPageList(PubProduceLibraryBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<PubProduceLibrary> lqw = buildQueryWrapper(bo);
        Page<PubProduceLibraryVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询工序库列表
     */
    @Override
    public List<PubProduceLibraryVo> queryList(PubProduceLibraryBo bo) {
        LambdaQueryWrapper<PubProduceLibrary> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<PubProduceLibrary> buildQueryWrapper(PubProduceLibraryBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<PubProduceLibrary> lqw = Wrappers.lambdaQuery();
        lqw.like(StringUtils.isNotBlank(bo.getName()), PubProduceLibrary::getName, bo.getName());
        lqw.eq(StringUtils.isNotBlank(bo.getCode()), PubProduceLibrary::getCode, bo.getCode());
        return lqw;
    }

    /**
     * 新增工序库
     */
    @Override
    public Boolean insertByBo(PubProduceLibraryBo bo) {
        PubProduceLibrary add = BeanUtil.toBean(bo, PubProduceLibrary.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改工序库
     */
    @Override
    public Boolean updateByBo(PubProduceLibraryBo bo) {
        PubProduceLibrary update = BeanUtil.toBean(bo, PubProduceLibrary.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(PubProduceLibrary entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除工序库
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
            /**
             Map<String, Object> columnMap = Maps.newHashMap();
             columnMap.put("library_id", ids);
             columnMap.put("component_type_id", componentTypeIdList);
            **/
            LambdaQueryWrapper<PubComponentType> lqw1 = new LambdaQueryWrapper<>();
            lqw1.in(CollectionUtil.isNotEmpty(ids), PubComponentType::getLibraryId, ids);

            List<Long> componentTypeIdList = componentTypeMapper.selectList(lqw1)
                    .stream().map(PubComponentType::getId).collect(Collectors.toList());

            LambdaQueryWrapper<PubProduce> lqw2 = new LambdaQueryWrapper<>();
            lqw2.in(CollectionUtil.isNotEmpty(componentTypeIdList), PubProduce::getComponentTypeId, componentTypeIdList);
            // 1、删除构建类型关联的工序数据
            pubProduceMapper.delete(lqw2);
            // 2、删除工序库关联的构建类型数据
            componentTypeMapper.delete(lqw1);
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }

    /**
     * 校验主键是否唯一工序库信息
     */
    @Override
    public Boolean checkUniqueByPrimaryKey(Integer id) {
        int count = baseMapper.selectCountByPrimaryKey(id);
        return count <= 1;
    }

    @Override
    public List<Tree<Long>> getProduceLibraryTree(PubProduceLibraryBo bo) {
        LambdaQueryWrapper<PubProduceLibrary> lqw = new LambdaQueryWrapper<>();
        lqw.eq(ObjectUtil.isNotNull(bo.getId()), PubProduceLibrary::getId, bo.getId())
               // .eq(ObjectUtil.isNotNull(bo.getParentId()), PubProduceLibrary::getParentId, bo.getParentId())
                .like(StringUtils.isNotBlank(bo.getName()), PubProduceLibrary::getName, bo.getName())
               // .orderByAsc(PubProduceLibrary::getParentId)
                .orderByAsc(PubProduceLibrary::getOrderNum);
        List<PubProduceLibrary> pubProduceLibraryList = baseMapper.selectList(lqw);
        return TreeBuildUtils.build(pubProduceLibraryList, (pubProduceLibrary, tree) ->
                tree.setId(pubProduceLibrary.getId())
                       // .setParentId(pubProduceLibrary.getParentId())
                        .setName(pubProduceLibrary.getName())
                        .setWeight(pubProduceLibrary.getOrderNum()));
    }

    /**
     * 复制工序库
     * @param bo
     * @return
     */
    @Override
    public Boolean copyProduceLibrary(PubProduceLibraryBo bo) {
        boolean flag = true;
        try {
            // 被复制的工序库ID
            Long copyLibraryId = bo.getId();
            // 1、保存填写的工序库信息
            bo.setId(null);
            this.insertByBo(bo);
            // 当前操作的工序库ID
            Long currentLibraryId = bo.getId();
            // 2、复制对应的构建类型
            LambdaQueryWrapper<PubComponentType> lqw = new LambdaQueryWrapper<>();
            lqw.eq(ObjectUtil.isNotNull(copyLibraryId), PubComponentType::getLibraryId, copyLibraryId);
            List<PubComponentType> componentTypeList = componentTypeMapper.selectList(lqw);
            componentTypeList.forEach(pubComponentType -> {
                // 根据当前构建类型查询其关联的工序数据
                LambdaQueryWrapper<PubProduce> lqw1 = new LambdaQueryWrapper<>();
                lqw1.eq(ObjectUtil.isNotNull(pubComponentType.getId()), PubProduce::getComponentTypeId, pubComponentType.getId());
                List<PubProduce> produceList = pubProduceMapper.selectList(lqw1);
                // 保存复制的构建类型数据
                pubComponentType.setRemark("从构建类型ID[" + pubComponentType.getId() + "]复制");
                pubComponentType.setId(null);
                pubComponentType.setLibraryId(currentLibraryId);

                componentTypeMapper.insert(pubComponentType);
                // 处理工序数据&保存
                produceList.forEach(pubProduce -> {
                    pubProduce.setRemark("从工序ID[" + pubProduce.getId() + "]复制");
                    pubProduce.setId(null);
                    pubProduce.setComponentTypeId(pubComponentType.getId());
                });
                pubProduceMapper.insertBatch(produceList);
            });
        } catch (Exception e) {
            e.printStackTrace();
            flag = false;
        }
        return flag;
    }
}
