package com.ruoyi.jianguan.manage.produce.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.util.ObjectUtil;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ruoyi.common.utils.TreeBuildUtils;
import com.ruoyi.jianguan.manage.produce.domain.bo.PubProduceLibraryBo;
import com.ruoyi.jianguan.manage.produce.domain.vo.PubProduceLibraryVo;
import com.ruoyi.jianguan.manage.produce.mapper.PubProduceLibraryMapper;
import com.ruoyi.jianguan.manage.produce.service.IPubProduceLibraryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.ruoyi.jianguan.manage.produce.domain.entity.PubProduceLibrary;

import java.util.List;
import java.util.Map;
import java.util.Collection;

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
        lqw.eq(StringUtils.isNotBlank(bo.getProjectId()), PubProduceLibrary::getProjectId, bo.getProjectId());
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
    public Boolean deleteWithValidByIds(Collection<Integer> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
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
                .eq(ObjectUtil.isNotNull(bo.getParentId()), PubProduceLibrary::getParentId, bo.getParentId())
                .like(StringUtils.isNotBlank(bo.getName()), PubProduceLibrary::getName, bo.getName())
                .orderByAsc(PubProduceLibrary::getParentId)
                .orderByAsc(PubProduceLibrary::getOrderNum);
        List<PubProduceLibrary> pubProduceLibraryList = baseMapper.selectList(lqw);
        return TreeBuildUtils.build(pubProduceLibraryList, (pubProduceLibrary, tree) ->
                tree.setId(pubProduceLibrary.getId())
                        .setParentId(pubProduceLibrary.getParentId())
                        .setName(pubProduceLibrary.getName())
                        .setWeight(pubProduceLibrary.getOrderNum()));
    }
}
