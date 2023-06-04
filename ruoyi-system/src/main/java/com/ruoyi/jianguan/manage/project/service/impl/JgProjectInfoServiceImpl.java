package com.ruoyi.jianguan.manage.project.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.util.ObjectUtil;
import com.google.common.collect.Maps;
import com.ruoyi.common.core.domain.entity.SysRole;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ruoyi.common.utils.TreeBuildUtils;
import com.ruoyi.jianguan.manage.map.domain.MapPlan;
import com.ruoyi.jianguan.manage.map.domain.MapPlanServer;
import com.ruoyi.jianguan.manage.project.domain.bo.JgProjectDeptBo;
import com.ruoyi.jianguan.manage.project.domain.bo.JgProjectInfoBo;
import com.ruoyi.jianguan.manage.project.domain.entity.JgProjectDept;
import com.ruoyi.jianguan.manage.project.domain.entity.JgProjectInfo;
import com.ruoyi.jianguan.manage.project.domain.entity.JgProjectItem;
import com.ruoyi.jianguan.manage.project.domain.vo.JgProjectInfoVo;
import com.ruoyi.jianguan.manage.project.mapper.JgProjectDeptMapper;
import com.ruoyi.jianguan.manage.project.mapper.JgProjectInfoMapper;
import com.ruoyi.jianguan.manage.project.mapper.JgProjectItemMapper;
import com.ruoyi.jianguan.manage.project.mapper.PubMonitorMapper;
import com.ruoyi.jianguan.manage.project.service.IJgProjectInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 项目信息Service业务层处理
 *
 * @author ruoyi
 * @date 2023-04-19
 */
@RequiredArgsConstructor
@Service
public class JgProjectInfoServiceImpl implements IJgProjectInfoService {

    private final JgProjectInfoMapper baseMapper;
    private final JgProjectItemMapper projectItemMapper;
    private final JgProjectDeptMapper projectDeptMapper;
    private final PubMonitorMapper monitorMapper;

    /**
     * 查询项目信息
     */
    @Override
    public JgProjectInfoVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询项目信息列表
     */
    @Override
    public TableDataInfo<JgProjectInfoVo> queryPageList(JgProjectInfoBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<JgProjectInfo> lqw = buildQueryWrapper(bo);
        Page<JgProjectInfoVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询项目信息列表
     */
    @Override
    public List<JgProjectInfoVo> queryList(JgProjectInfoBo bo) {
        LambdaQueryWrapper<JgProjectInfo> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<JgProjectInfo> buildQueryWrapper(JgProjectInfoBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<JgProjectInfo> lqw = Wrappers.lambdaQuery();
        lqw.like(StringUtils.isNotBlank(bo.getProjectName()), JgProjectInfo::getProjectName, bo.getProjectName());
        lqw.eq(StringUtils.isNotBlank(bo.getProjectCode()), JgProjectInfo::getProjectCode, bo.getProjectCode());
        lqw.eq(bo.getParentId() != null, JgProjectInfo::getParentId, bo.getParentId());
        lqw.eq(StringUtils.isNotBlank(bo.getProjectArea()), JgProjectInfo::getProjectArea, bo.getProjectArea());
        lqw.eq(bo.getGroupLevel() != null, JgProjectInfo::getGroupLevel, bo.getGroupLevel());
        lqw.eq(bo.getStatus() != null, JgProjectInfo::getStatus, bo.getStatus());
        lqw.eq(StringUtils.isNotBlank(bo.getVisible()), JgProjectInfo::getVisible, bo.getVisible());
        lqw.eq(bo.getOrderNum() != null, JgProjectInfo::getOrderNum, bo.getOrderNum());
        lqw.eq(StringUtils.isNotBlank(bo.getGroupId()), JgProjectInfo::getGroupId, bo.getGroupId());
        lqw.eq(bo.getIsAuto() != null, JgProjectInfo::getIsAuto, bo.getIsAuto());
        lqw.eq(StringUtils.isNotBlank(bo.getProjectPic()), JgProjectInfo::getProjectPic, bo.getProjectPic());
        lqw.eq(StringUtils.isNotBlank(bo.getContractNum()), JgProjectInfo::getContractNum, bo.getContractNum());
        lqw.eq(StringUtils.isNotBlank(bo.getCoordinate()), JgProjectInfo::getCoordinate, bo.getCoordinate());
        lqw.eq(bo.getInvestment() != null, JgProjectInfo::getInvestment, bo.getInvestment());
        lqw.eq(StringUtils.isNotBlank(bo.getProjectType()), JgProjectInfo::getProjectType, bo.getProjectType());
        lqw.eq(StringUtils.isNotBlank(bo.getProjectPoint()), JgProjectInfo::getProjectPoint, bo.getProjectPoint());
        lqw.eq(StringUtils.isNotBlank(bo.getProjectLine()), JgProjectInfo::getProjectLine, bo.getProjectLine());
        lqw.eq(StringUtils.isNotBlank(bo.getProjectSurface()), JgProjectInfo::getProjectSurface, bo.getProjectSurface());
        lqw.eq(StringUtils.isNotBlank(bo.getIntroduction()), JgProjectInfo::getIntroduction, bo.getIntroduction());
        return lqw;
    }

    /**
     * 新增项目信息
     */
    @Override
    public Boolean insertByBo(JgProjectInfoBo bo) {
        JgProjectInfo add = BeanUtil.toBean(bo, JgProjectInfo.class);
        validEntityBeforeSave(add);

        JgProjectInfoVo projectInfoVo = baseMapper.selectVoById(bo.getParentId());
        Integer currentLevel = Objects.isNull(projectInfoVo)? 1 : projectInfoVo.getGroupLevel() + 1;
        add.setGroupLevel(currentLevel);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改项目信息
     */
    @Override
    public Boolean updateByBo(JgProjectInfoBo bo) {
        JgProjectInfo update = BeanUtil.toBean(bo, JgProjectInfo.class);
        validEntityBeforeSave(update);
        JgProjectInfoVo projectInfoVo = baseMapper.selectVoById(bo.getParentId());
        Integer currentLevel = Objects.isNull(projectInfoVo)? 1 : projectInfoVo.getGroupLevel() + 1;
        update.setGroupLevel(currentLevel);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(JgProjectInfo entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除项目信息
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            Map<String, Object> columnMap = Maps.newHashMap();
            columnMap.put("project_id", ids);
            // 1、删除关联的项目明细数据
            projectItemMapper.deleteByMap(columnMap);
            // 2、删除关联的部门数据
            projectDeptMapper.deleteByMap(columnMap);
            // 3、删除关联的设备信息数据
            monitorMapper.deleteByMap(columnMap);
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }

    /**
     * 项目机构树
     * @param bo
     * @return
     */
    @Override
    public List<Tree<Long>> getProjectTree(JgProjectInfoBo bo) {
        LambdaQueryWrapper<JgProjectInfo> lqw = new LambdaQueryWrapper<>();
        lqw.eq(ObjectUtil.isNotNull(bo.getId()), JgProjectInfo::getId, bo.getId())
                .eq(ObjectUtil.isNotNull(bo.getParentId()), JgProjectInfo::getParentId, bo.getParentId())
                .like(StringUtils.isNotBlank(bo.getProjectName()), JgProjectInfo::getProjectName, bo.getProjectName())
                .eq(ObjectUtil.isNotEmpty(bo.getStatus()), JgProjectInfo::getStatus, bo.getStatus())
                .orderByAsc(JgProjectInfo::getParentId)
                .orderByAsc(JgProjectInfo::getOrderNum);
        List<JgProjectInfo> jgProjectInfoList = baseMapper.selectList(lqw);
        return TreeBuildUtils.build(jgProjectInfoList, (jgProjectInfo, tree) ->
                tree.setId(jgProjectInfo.getId())
                        .setParentId(jgProjectInfo.getParentId())
                        .setName(jgProjectInfo.getProjectName())
                        .setWeight(jgProjectInfo.getOrderNum()));
    }

    @Override
    public Boolean relatedDept(JgProjectDeptBo bo) {
        // 1、删除旧关联数据
        projectDeptMapper.deleteProjectDeptByProjectId(bo.getProjectId());
        // 2、保存新关联数据
       List<JgProjectDept> projectDeptList = Arrays.stream(Convert.toStrArray(bo.getDeptIds())).map(deptId -> {
            return new JgProjectDept(Long.valueOf(bo.getProjectId()), Long.valueOf(deptId));
        }).collect(Collectors.toList());
        return projectDeptMapper.insertBatch(projectDeptList);
    }
}
