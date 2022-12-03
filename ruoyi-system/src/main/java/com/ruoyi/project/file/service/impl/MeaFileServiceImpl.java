package com.ruoyi.project.file.service.impl;


import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.common.core.domain.PageQuery;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.project.file.domain.MeaFile;
import com.ruoyi.project.file.domain.bo.MeaFileBo;
import com.ruoyi.project.file.domain.vo.MeaFileVo;
import com.ruoyi.project.file.mapper.MeaFileMapper;
import com.ruoyi.project.file.service.IMeaFileService;
import io.micrometer.core.instrument.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 附件记录Service业务层处理
 *
 * @author ruoyi
 * @date 2022-12-03
 */
@RequiredArgsConstructor
@Service
public class MeaFileServiceImpl implements IMeaFileService {

    private final MeaFileMapper baseMapper;

    /**
     * 查询附件记录
     */
    @Override
    public MeaFileVo queryById(String fileId){
        return baseMapper.selectVoById(fileId);
    }

    /**
     * 查询附件记录列表
     */
    @Override
    public TableDataInfo<MeaFileVo> queryPageList(MeaFileBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<MeaFile> lqw = buildQueryWrapper(bo);
        Page<MeaFileVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询附件记录列表
     */
    @Override
    public List<MeaFileVo> queryList(MeaFileBo bo) {
        LambdaQueryWrapper<MeaFile> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<MeaFile> buildQueryWrapper(MeaFileBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<MeaFile> lqw = Wrappers.lambdaQuery();
        lqw.like(StringUtils.isNotBlank(bo.getFileName()), MeaFile::getFileName, bo.getFileName());
        lqw.eq(StringUtils.isNotBlank(bo.getFlowId()), MeaFile::getFlowId, bo.getFlowId());
        lqw.eq(StringUtils.isNotBlank(bo.getUrl()), MeaFile::getUrl, bo.getUrl());
        lqw.eq(StringUtils.isNotBlank(bo.getPath()), MeaFile::getPath, bo.getPath());
        lqw.eq(StringUtils.isNotBlank(bo.getStatus()), MeaFile::getStatus, bo.getStatus());
        return lqw;
    }

    /**
     * 新增附件记录
     */
    @Override
    public Boolean insertByBo(MeaFileBo bo) {
        MeaFile add = BeanUtil.toBean(bo, MeaFile.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setFileId(add.getFileId());
        }
        return flag;
    }

    /**
     * 修改附件记录
     */
    @Override
    public Boolean updateByBo(MeaFileBo bo) {
        MeaFile update = BeanUtil.toBean(bo, MeaFile.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(MeaFile entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除附件记录
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<String> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
