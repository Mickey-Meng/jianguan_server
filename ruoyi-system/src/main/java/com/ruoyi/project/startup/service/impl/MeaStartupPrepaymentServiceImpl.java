package com.ruoyi.project.startup.service.impl;


import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.common.core.domain.PageQuery;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.project.startup.domain.MeaStartupPrepayment;
import com.ruoyi.project.startup.domain.bo.MeaStartupPrepaymentBo;
import com.ruoyi.project.startup.domain.vo.MeaStartupPrepaymentVo;
import com.ruoyi.project.startup.mapper.MeaStartupPrepaymentMapper;
import com.ruoyi.project.startup.service.IMeaStartupPrepaymentService;
import io.micrometer.core.instrument.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 开工预付款Service业务层处理
 *
 * @author ruoyi
 * @date 2022-12-03
 */
@RequiredArgsConstructor
@Service
public class MeaStartupPrepaymentServiceImpl implements IMeaStartupPrepaymentService {

    private final MeaStartupPrepaymentMapper baseMapper;

    /**
     * 查询开工预付款
     */
    @Override
    public MeaStartupPrepaymentVo queryById(String id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询开工预付款列表
     */
    @Override
    public TableDataInfo<MeaStartupPrepaymentVo> queryPageList(MeaStartupPrepaymentBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<MeaStartupPrepayment> lqw = buildQueryWrapper(bo);
        Page<MeaStartupPrepaymentVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询开工预付款列表
     */
    @Override
    public List<MeaStartupPrepaymentVo> queryList(MeaStartupPrepaymentBo bo) {
        LambdaQueryWrapper<MeaStartupPrepayment> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<MeaStartupPrepayment> buildQueryWrapper(MeaStartupPrepaymentBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<MeaStartupPrepayment> lqw = Wrappers.lambdaQuery();
        lqw.eq(StringUtils.isNotBlank(bo.getBdbh()), MeaStartupPrepayment::getBdbh, bo.getBdbh());
        lqw.eq(StringUtils.isNotBlank(bo.getJlqsbh()), MeaStartupPrepayment::getJlqsbh, bo.getJlqsbh());
        lqw.like(StringUtils.isNotBlank(bo.getSqbh()), MeaStartupPrepayment::getSqbh, bo.getSqbh());
        lqw.eq(bo.getSqsj() != null, MeaStartupPrepayment::getSqsj, bo.getSqsj());
        lqw.eq(StringUtils.isNotBlank(bo.getSqlx()), MeaStartupPrepayment::getSqlx, bo.getSqlx());
        lqw.eq(bo.getSqcs() != null, MeaStartupPrepayment::getSqcs, bo.getSqcs());
        lqw.eq(bo.getYukje() != null, MeaStartupPrepayment::getYukje, bo.getYukje());
        lqw.eq(StringUtils.isNotBlank(bo.getSqyj()), MeaStartupPrepayment::getSqyj, bo.getSqyj());
        lqw.eq(StringUtils.isNotBlank(bo.getFj()), MeaStartupPrepayment::getFj, bo.getFj());
        lqw.eq(StringUtils.isNotBlank(bo.getStatus()), MeaStartupPrepayment::getStatus, bo.getStatus());
        return lqw;
    }

    /**
     * 新增开工预付款
     */
    @Override
    public Boolean insertByBo(MeaStartupPrepaymentBo bo) {
        MeaStartupPrepayment add = BeanUtil.toBean(bo, MeaStartupPrepayment.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改开工预付款
     */
    @Override
    public Boolean updateByBo(MeaStartupPrepaymentBo bo) {
        MeaStartupPrepayment update = BeanUtil.toBean(bo, MeaStartupPrepayment.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(MeaStartupPrepayment entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除开工预付款
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<String> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
