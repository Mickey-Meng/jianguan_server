package com.ruoyi.project.measurementNo.service.impl;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.common.core.domain.PageQuery;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.project.measurementDocuments.domain.MeaMeasurementDocuments;
import com.ruoyi.project.measurementDocuments.domain.vo.MeaMeasurementDocumentsVo;
import com.ruoyi.project.measurementDocuments.mapper.MeaMeasurementDocumentsMapper;
import com.ruoyi.project.measurementNo.domain.MeaMeasurementNo;
import com.ruoyi.project.measurementNo.domain.bo.MeaMeasurementNoBo;
import com.ruoyi.project.measurementNo.domain.vo.MeaMeasurementNoVo;
import com.ruoyi.project.measurementNo.mapper.MeaMeasurementNoMapper;
import com.ruoyi.project.measurementNo.service.IMeaMeasurementNoService;
import io.micrometer.core.instrument.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 中间计量期数管理Service业务层处理
 *
 * @author ruoyi
 * @date 2022-12-03
 */
@RequiredArgsConstructor
@Service
public class MeaMeasurementNoServiceImpl implements IMeaMeasurementNoService {

    private final MeaMeasurementNoMapper baseMapper;

    private final MeaMeasurementDocumentsMapper meaMeasurementDocumentsMapper;

    /**
     * 查询中间计量期数管理
     */
    @Override
    public MeaMeasurementNoVo queryById(String id){
        return baseMapper.selectVoById(id);
    }

    @Override
    public MeaMeasurementNoVo queryMax() {
        return baseMapper.getMax();
    }

    /**
     * 查询中间计量期数管理列表
     */
    @Override
    public TableDataInfo<MeaMeasurementNoVo> queryPageList(MeaMeasurementNoBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<MeaMeasurementNo> lqw = buildQueryWrapper(bo);
        Page<MeaMeasurementNoVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询中间计量期数管理列表
     */
    @Override
    public List<MeaMeasurementNoVo> queryList(MeaMeasurementNoBo bo) {
        LambdaQueryWrapper<MeaMeasurementNo> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<MeaMeasurementNo> buildQueryWrapper(MeaMeasurementNoBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<MeaMeasurementNo> lqw = Wrappers.lambdaQuery();
        lqw.eq(StringUtils.isNotBlank(bo.getBdbh()), MeaMeasurementNo::getBdbh, bo.getBdbh());
        lqw.eq(StringUtils.isNotBlank(bo.getJlqsbh()), MeaMeasurementNo::getJlqsbh, bo.getJlqsbh());
        lqw.eq(StringUtils.isNotBlank(bo.getJlqs()), MeaMeasurementNo::getJlqs, bo.getJlqs());
        lqw.eq(bo.getKsrq() != null, MeaMeasurementNo::getKsrq, bo.getKsrq());
        lqw.eq(bo.getJsrq() != null, MeaMeasurementNo::getJsrq, bo.getJsrq());
        lqw.eq(bo.getMrrq() != null, MeaMeasurementNo::getMrrq, bo.getMrrq());
        lqw.eq(StringUtils.isNotBlank(bo.getBbbh()), MeaMeasurementNo::getBbbh, bo.getBbbh());
        lqw.eq(StringUtils.isNotBlank(bo.getStatus()), MeaMeasurementNo::getStatus, bo.getStatus());
        return lqw;
    }

    /**
     * 新增中间计量期数管理
     */
    @Override
    public Boolean insertByBo(MeaMeasurementNoBo bo) {
        MeaMeasurementNo add = BeanUtil.toBean(bo, MeaMeasurementNo.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改中间计量期数管理
     */
    @Override
    public Boolean updateByBo(MeaMeasurementNoBo bo) {
        MeaMeasurementNo update = BeanUtil.toBean(bo, MeaMeasurementNo.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    @Override
    public String lockingByJlqcbh(String jlqcbh) {
        LambdaQueryWrapper<MeaMeasurementDocuments> lqw = Wrappers.lambdaQuery();
        lqw.eq(StringUtils.isNotBlank(jlqcbh), MeaMeasurementDocuments::getJlqsbh,jlqcbh);
        List<MeaMeasurementDocumentsVo> meaMeasurementDocumentsVos = meaMeasurementDocumentsMapper.selectVoList(lqw);
        if(CollUtil.isEmpty(meaMeasurementDocumentsVos)) {
            return "未进行中间计量，期次无法锁定";
        }

        List<String> pzbhList = new ArrayList<>();
        for (MeaMeasurementDocumentsVo meaMeasurementDocumentsVo : meaMeasurementDocumentsVos) {
            if(!"2".equalsIgnoreCase(meaMeasurementDocumentsVo.getReviewCode())){
                pzbhList.add(meaMeasurementDocumentsVo.getPzbh());
            }
        }
        if(CollUtil.isNotEmpty(pzbhList)) {
            return "有以下计量凭证流程未结束，不能锁定： "+ StrUtil.join(",", pzbhList);
        }

        LambdaQueryWrapper<MeaMeasurementNo> lqwMeaMeasurementNo = Wrappers.lambdaQuery();
        lqwMeaMeasurementNo.eq(StringUtils.isNotBlank(jlqcbh), MeaMeasurementNo::getJlqsbh, jlqcbh);
        MeaMeasurementNo meaMeasurementNo = baseMapper.selectOne(lqwMeaMeasurementNo);
        meaMeasurementNo.setStatus("1");//修改为已锁定
        baseMapper.updateById(meaMeasurementNo);
        return null;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(MeaMeasurementNo entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除中间计量期数管理
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<String> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }

    @Override
    public R sortList() {
        LambdaQueryWrapper<MeaMeasurementNo> lqw = new LambdaQueryWrapper<>();
        List<MeaMeasurementNoVo> meaMeasurementNoVos = baseMapper.selectVoList(lqw, MeaMeasurementNoVo.class);
        if(CollUtil.isNotEmpty(meaMeasurementNoVos)){
            SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
            meaMeasurementNoVos.forEach(meaMeasurementNoVo -> {
                String start = simpleDateFormat.format(meaMeasurementNoVo.getKsrq());
                final String end = simpleDateFormat.format(meaMeasurementNoVo.getKsrq());
                meaMeasurementNoVo.setName(meaMeasurementNoVo.getJlqs()+"("+start+"-"+end+")");
            });
        }
        return R.ok(meaMeasurementNoVos);
    }
}
