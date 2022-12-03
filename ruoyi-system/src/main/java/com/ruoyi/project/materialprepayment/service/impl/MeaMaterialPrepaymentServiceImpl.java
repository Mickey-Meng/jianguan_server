package com.ruoyi.project.materialprepayment.service.impl;


import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.common.core.domain.PageQuery;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.project.materialprepayment.domain.MeaMaterialPrepayment;
import com.ruoyi.project.materialprepayment.domain.bo.MeaMaterialPrepaymentBo;
import com.ruoyi.project.materialprepayment.domain.vo.MeaMaterialPrepaymentVo;
import com.ruoyi.project.materialprepayment.mapper.MeaMaterialPrepaymentMapper;
import com.ruoyi.project.materialprepayment.service.IMeaMaterialPrepaymentService;
import io.micrometer.core.instrument.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 材料预付款Service业务层处理
 *
 * @author ruoyi
 * @date 2022-12-03
 */
@RequiredArgsConstructor
@Service
public class MeaMaterialPrepaymentServiceImpl implements IMeaMaterialPrepaymentService {

    private final MeaMaterialPrepaymentMapper baseMapper;

    /**
     * 查询材料预付款
     */
    @Override
    public MeaMaterialPrepaymentVo queryById(String id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询材料预付款列表
     */
    @Override
    public TableDataInfo<MeaMaterialPrepaymentVo> queryPageList(MeaMaterialPrepaymentBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<MeaMaterialPrepayment> lqw = buildQueryWrapper(bo);
        Page<MeaMaterialPrepaymentVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询材料预付款列表
     */
    @Override
    public List<MeaMaterialPrepaymentVo> queryList(MeaMaterialPrepaymentBo bo) {
        LambdaQueryWrapper<MeaMaterialPrepayment> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<MeaMaterialPrepayment> buildQueryWrapper(MeaMaterialPrepaymentBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<MeaMaterialPrepayment> lqw = Wrappers.lambdaQuery();
        lqw.eq(StringUtils.isNotBlank(bo.getBdbh()), MeaMaterialPrepayment::getBdbh, bo.getBdbh());
        lqw.eq(StringUtils.isNotBlank(bo.getJlqsbh()), MeaMaterialPrepayment::getJlqsbh, bo.getJlqsbh());
        lqw.eq(StringUtils.isNotBlank(bo.getClbh()), MeaMaterialPrepayment::getClbh, bo.getClbh());
        lqw.eq(StringUtils.isNotBlank(bo.getClmc()), MeaMaterialPrepayment::getClmc, bo.getClmc());
        lqw.eq(StringUtils.isNotBlank(bo.getDw()), MeaMaterialPrepayment::getDw, bo.getDw());
        lqw.eq(bo.getDj() != null, MeaMaterialPrepayment::getDj, bo.getDj());
        lqw.eq(bo.getSl() != null, MeaMaterialPrepayment::getSl, bo.getSl());
        lqw.eq(bo.getJe() != null, MeaMaterialPrepayment::getJe, bo.getJe());
        lqw.eq(bo.getYfbl() != null, MeaMaterialPrepayment::getYfbl, bo.getYfbl());
        lqw.eq(bo.getYfje() != null, MeaMaterialPrepayment::getYfje, bo.getYfje());
        lqw.eq(StringUtils.isNotBlank(bo.getClly()), MeaMaterialPrepayment::getClly, bo.getClly());
        lqw.eq(StringUtils.isNotBlank(bo.getDjbh()), MeaMaterialPrepayment::getDjbh, bo.getDjbh());
        lqw.eq(StringUtils.isNotBlank(bo.getZbsbh()), MeaMaterialPrepayment::getZbsbh, bo.getZbsbh());
        lqw.eq(StringUtils.isNotBlank(bo.getCjbgbh()), MeaMaterialPrepayment::getCjbgbh, bo.getCjbgbh());
        lqw.eq(StringUtils.isNotBlank(bo.getStatus()), MeaMaterialPrepayment::getStatus, bo.getStatus());
        return lqw;
    }

    /**
     * 新增材料预付款
     */
    @Override
    public Boolean insertByBo(MeaMaterialPrepaymentBo bo) {
        MeaMaterialPrepayment add = BeanUtil.toBean(bo, MeaMaterialPrepayment.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改材料预付款
     */
    @Override
    public Boolean updateByBo(MeaMaterialPrepaymentBo bo) {
        MeaMaterialPrepayment update = BeanUtil.toBean(bo, MeaMaterialPrepayment.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(MeaMaterialPrepayment entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除材料预付款
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<String> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
