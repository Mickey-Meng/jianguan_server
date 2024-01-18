package com.ruoyi.jianguan.business.onlineForms.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.ruoyi.jianguan.business.onlineForms.domain.bo.PubProduceDocumentBo;
import com.ruoyi.jianguan.business.onlineForms.domain.vo.PubProduceDocumentVo;
import com.ruoyi.jianguan.business.onlineForms.domain.PubProduceDocument;
import com.ruoyi.jianguan.business.onlineForms.mapper.PubProduceDocumentMapper;
import com.ruoyi.jianguan.business.onlineForms.service.IPubProduceDocumentService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 工序附件信息Service业务层处理
 *
 * @author mickey
 * @date 2024-01-02
 */
@RequiredArgsConstructor
@Service
public class PubProduceDocumentServiceImpl implements IPubProduceDocumentService {

    private final PubProduceDocumentMapper baseMapper;

    /**
     * 查询工序附件信息
     */
    @Override
    public PubProduceDocumentVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询工序附件信息列表
     */
    @Override
    public TableDataInfo<PubProduceDocumentVo> queryPageList(PubProduceDocumentBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<PubProduceDocument> lqw = buildQueryWrapper(bo);
        Page<PubProduceDocumentVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询工序附件信息列表
     */
    @Override
    public List<PubProduceDocumentVo> queryList(PubProduceDocumentBo bo) {
        LambdaQueryWrapper<PubProduceDocument> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<PubProduceDocument> buildQueryWrapper(PubProduceDocumentBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<PubProduceDocument> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getComponentId() != null, PubProduceDocument::getComponentId, bo.getComponentId());
        lqw.eq(bo.getProduceId() != null, PubProduceDocument::getProduceId, bo.getProduceId());
        lqw.like(StringUtils.isNotBlank(bo.getDocumentName()), PubProduceDocument::getDocumentName, bo.getDocumentName());
        lqw.like(StringUtils.isNotBlank(bo.getDocumentCode()), PubProduceDocument::getDocumentCode, bo.getDocumentCode());
        lqw.like(StringUtils.isNotBlank(bo.getDocumentUrl()), PubProduceDocument::getDocumentUrl, bo.getDocumentUrl());
        lqw.eq(bo.getDocumentStatus() != null, PubProduceDocument::getDocumentStatus, bo.getDocumentStatus());
        lqw.eq(bo.getDocumentType() != null, PubProduceDocument::getDocumentType, bo.getDocumentType());
        return lqw;
    }

    /**
     * 新增工序附件信息
     */
    @Override
    public Boolean insertByBo(PubProduceDocumentBo bo) {
        PubProduceDocument add = BeanUtil.toBean(bo, PubProduceDocument.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改工序附件信息
     */
    @Override
    public Boolean updateByBo(PubProduceDocumentBo bo) {
        PubProduceDocument update = BeanUtil.toBean(bo, PubProduceDocument.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(PubProduceDocument entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除工序附件信息
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }

    /**
     * 校验主键是否唯一工序附件信息信息
     */
    @Override
    public Boolean checkUniqueByPrimaryKey(Long id) {
        int count = baseMapper.selectCountByPrimaryKey(id);
        return count <= 1;
    }
}
