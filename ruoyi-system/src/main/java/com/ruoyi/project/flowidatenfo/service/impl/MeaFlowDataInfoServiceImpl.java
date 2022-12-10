package com.ruoyi.project.flowidatenfo.service.impl;


import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.common.core.domain.PageQuery;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.project.flowidatenfo.domain.MeaFlowDataInfo;
import com.ruoyi.project.flowidatenfo.domain.bo.MeaFlowDataInfoBo;
import com.ruoyi.project.flowidatenfo.domain.vo.MeaFlowDataInfoVo;
import com.ruoyi.project.flowidatenfo.mapper.MeaFlowDataInfoMapper;
import com.ruoyi.project.flowidatenfo.service.IMeaFlowDataInfoService;
import io.micrometer.core.instrument.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 工作流单数据Service业务层处理
 *
 * @author ruoyi
 * @date 2022-12-10
 */
@RequiredArgsConstructor
@Service
public class MeaFlowDataInfoServiceImpl implements IMeaFlowDataInfoService {

    private final MeaFlowDataInfoMapper baseMapper;

    /**
     * 查询工作流单数据
     */
    @Override
    public MeaFlowDataInfoVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询工作流单数据列表
     */
    @Override
    public TableDataInfo<MeaFlowDataInfoVo> queryPageList(MeaFlowDataInfoBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<MeaFlowDataInfo> lqw = buildQueryWrapper(bo);
        Page<MeaFlowDataInfoVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询工作流单数据列表
     */
    @Override
    public List<MeaFlowDataInfoVo> queryList(MeaFlowDataInfoBo bo) {
        LambdaQueryWrapper<MeaFlowDataInfo> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<MeaFlowDataInfo> buildQueryWrapper(MeaFlowDataInfoBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<MeaFlowDataInfo> lqw = Wrappers.lambdaQuery();
        lqw.eq(StringUtils.isNotBlank(bo.getTaskId()), MeaFlowDataInfo::getTaskId, bo.getTaskId());
        lqw.eq(StringUtils.isNotBlank(bo.getBussinessKey()), MeaFlowDataInfo::getBussinessKey, bo.getBussinessKey());
        lqw.eq(StringUtils.isNotBlank(bo.getBussinessData()), MeaFlowDataInfo::getBussinessData, bo.getBussinessData());
        lqw.eq(StringUtils.isNotBlank(bo.getStatus()), MeaFlowDataInfo::getStatus, bo.getStatus());
        return lqw;
    }

    /**
     * 新增工作流单数据
     */
    @Override
    public Boolean insertByBo(MeaFlowDataInfoBo bo) {
        MeaFlowDataInfo add = BeanUtil.toBean(bo, MeaFlowDataInfo.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改工作流单数据
     */
    @Override
    public Boolean updateByBo(MeaFlowDataInfoBo bo) {
        MeaFlowDataInfo update = BeanUtil.toBean(bo, MeaFlowDataInfo.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(MeaFlowDataInfo entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除工作流单数据
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
