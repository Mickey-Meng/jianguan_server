package com.ruoyi.project.measurementDocuments.service.impl;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.common.core.domain.PageQuery;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.project.measurementDocuments.domain.MeaMeasurementDocuments;
import com.ruoyi.project.measurementDocuments.domain.bo.MeaMeasurementDocumentsAndDeBo;
import com.ruoyi.project.measurementDocuments.domain.bo.MeaMeasurementDocumentsBo;
import com.ruoyi.project.measurementDocuments.domain.vo.MeaMeasurementDocumentsVo;
import com.ruoyi.project.measurementDocuments.mapper.MeaMeasurementDocumentsMapper;
import com.ruoyi.project.measurementDocuments.service.IMeaMeasurementDocumentsService;
import com.ruoyi.project.measurementDocumentsDetail.domain.MeaMeasurementDocumentsDetail;
import com.ruoyi.project.measurementDocumentsDetail.domain.bo.MeaMeasurementDocumentsDetailBo;
import com.ruoyi.project.measurementDocumentsDetail.domain.vo.MeaMeasurementDocumentsDetailVo;
import com.ruoyi.project.measurementDocumentsDetail.mapper.MeaMeasurementDocumentsDetailMapper;
import io.micrometer.core.instrument.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 计量凭证，设计计量、变更计量共用一张凭证，明细分开。Service业务层处理
 *
 * @author ruoyi
 * @date 2022-12-03
 */
@RequiredArgsConstructor
@Service
public class MeaMeasurementDocumentsServiceImpl implements IMeaMeasurementDocumentsService {

    private final MeaMeasurementDocumentsMapper baseMapper;
    private final MeaMeasurementDocumentsDetailMapper baseDetailMapper;

    /**
     * 查询计量凭证，设计计量、变更计量共用一张凭证，明细分开。
     */
    @Override
    public MeaMeasurementDocumentsVo queryById(String id){
        MeaMeasurementDocumentsVo meaMeasurementDocumentsVo = baseMapper.selectVoById(id);
       if(meaMeasurementDocumentsVo!=null){
           LambdaQueryWrapper<MeaMeasurementDocumentsDetail> queryWrapper=new LambdaQueryWrapper<>();
           queryWrapper.eq(StringUtils.isNotBlank(meaMeasurementDocumentsVo.getPzbh()), MeaMeasurementDocumentsDetail::getPzbh, meaMeasurementDocumentsVo.getPzbh());
           List<MeaMeasurementDocumentsDetailVo> meaMeasurementDocumentsDetailVos = baseDetailMapper.selectVoList(queryWrapper);
           meaMeasurementDocumentsVo.setDetailBos(meaMeasurementDocumentsDetailVos);
       }
        return meaMeasurementDocumentsVo;
    }

    /**
     * 查询计量凭证，设计计量、变更计量共用一张凭证，明细分开。列表
     */
    @Override
    public TableDataInfo<MeaMeasurementDocumentsVo> queryPageList(MeaMeasurementDocumentsBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<MeaMeasurementDocuments> lqw = buildQueryWrapper(bo);
        Page<MeaMeasurementDocumentsVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询计量凭证，设计计量、变更计量共用一张凭证，明细分开。列表
     */
    @Override
    public List<MeaMeasurementDocumentsVo> queryList(MeaMeasurementDocumentsBo bo) {
        LambdaQueryWrapper<MeaMeasurementDocuments> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<MeaMeasurementDocuments> buildQueryWrapper(MeaMeasurementDocumentsBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<MeaMeasurementDocuments> lqw = Wrappers.lambdaQuery();
        lqw.eq(StringUtils.isNotBlank(bo.getBdbh()), MeaMeasurementDocuments::getBdbh, bo.getBdbh());
        lqw.eq(StringUtils.isNotBlank(bo.getJlqsbh()), MeaMeasurementDocuments::getJlqsbh, bo.getJlqsbh());
        lqw.eq(StringUtils.isNotBlank(bo.getTzfjbh()), MeaMeasurementDocuments::getTzfjbh, bo.getTzfjbh());
        lqw.eq(StringUtils.isNotBlank(bo.getPzbh()), MeaMeasurementDocuments::getPzbh, bo.getPzbh());
        lqw.eq(StringUtils.isNotBlank(bo.getJllx()), MeaMeasurementDocuments::getJllx, bo.getJllx());
        lqw.eq(bo.getJlrq() != null, MeaMeasurementDocuments::getJlrq, bo.getJlrq());
        lqw.eq(StringUtils.isNotBlank(bo.getJgzs()), MeaMeasurementDocuments::getJgzs, bo.getJgzs());
        lqw.eq(StringUtils.isNotBlank(bo.getGcbw()), MeaMeasurementDocuments::getGcbw, bo.getGcbw());
        lqw.eq(StringUtils.isNotBlank(bo.getJss()), MeaMeasurementDocuments::getJss, bo.getJss());
        lqw.eq(StringUtils.isNotBlank(bo.getJlbl()), MeaMeasurementDocuments::getJlbl, bo.getJlbl());
        lqw.eq(StringUtils.isNotBlank(bo.getFj()), MeaMeasurementDocuments::getFj, bo.getFj());
        lqw.eq(StringUtils.isNotBlank(bo.getStatus()), MeaMeasurementDocuments::getStatus, bo.getStatus());
        return lqw;
    }

    /**
     * 新增计量凭证，设计计量、变更计量共用一张凭证，明细分开。
     */
    @Override
    public Boolean insertByBo(MeaMeasurementDocumentsBo bo) {
        MeaMeasurementDocuments add = BeanUtil.toBean(bo, MeaMeasurementDocuments.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改计量凭证，设计计量、变更计量共用一张凭证，明细分开。
     */
    @Override
    public Boolean updateByBo(MeaMeasurementDocumentsBo bo) {
        MeaMeasurementDocuments update = BeanUtil.toBean(bo, MeaMeasurementDocuments.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(MeaMeasurementDocuments entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除计量凭证，设计计量、变更计量共用一张凭证，明细分开。
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<String> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveMeaMeasurementDocumentsAndDeBo(MeaMeasurementDocumentsAndDeBo bo) {
        MeaMeasurementDocuments add = BeanUtil.toBean(bo, MeaMeasurementDocuments.class);
        validEntityBeforeSave(add);
        add.setReviewCode("1");
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        if(CollUtil.isNotEmpty(bo.getDetailBos())){
            List<MeaMeasurementDocumentsDetailBo> detailBos = bo.getDetailBos();
            for(MeaMeasurementDocumentsDetailBo meaMeasurementDocumentsDetailBo:detailBos){
                MeaMeasurementDocumentsDetail bean = BeanUtil.toBean(meaMeasurementDocumentsDetailBo, MeaMeasurementDocumentsDetail.class);
                bean.setPzbh(add.getId());
                baseDetailMapper.insert(bean);
            }
        }
        return flag;
    }
}
