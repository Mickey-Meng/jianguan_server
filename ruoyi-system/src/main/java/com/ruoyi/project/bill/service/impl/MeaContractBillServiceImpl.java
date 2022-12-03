package com.ruoyi.project.bill.service.impl;


import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ruoyi.project.bill.domain.MeaContractBill;
import com.ruoyi.project.bill.domain.bo.MeaContractBillBo;
import com.ruoyi.project.bill.domain.vo.MeaContractBillVo;
import com.ruoyi.project.bill.mapper.MeaContractBillMapper;
import com.ruoyi.project.bill.service.IMeaContractBillService;
import io.micrometer.core.instrument.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 工程量清单Service业务层处理
 *
 * @author ruoyi
 * @date 2022-12-03
 */
@RequiredArgsConstructor
@Service
public class MeaContractBillServiceImpl implements IMeaContractBillService {

    private final MeaContractBillMapper baseMapper;

    /**
     * 查询工程量清单
     */
    @Override
    public MeaContractBillVo queryById(String id){
        return baseMapper.selectVoById(id);
    }


    /**
     * 查询工程量清单列表
     */
    @Override
    public List<MeaContractBillVo> queryList(MeaContractBillBo bo) {
        LambdaQueryWrapper<MeaContractBill> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<MeaContractBill> buildQueryWrapper(MeaContractBillBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<MeaContractBill> lqw = Wrappers.lambdaQuery();
        lqw.eq(StringUtils.isNotBlank(bo.getBdbh()), MeaContractBill::getBdbh, bo.getBdbh());
        lqw.eq(StringUtils.isNotBlank(bo.getZmh()), MeaContractBill::getZmh, bo.getZmh());
        lqw.like(StringUtils.isNotBlank(bo.getZmmc()), MeaContractBill::getZmmc, bo.getZmmc());
        lqw.eq(StringUtils.isNotBlank(bo.getZmhParent()), MeaContractBill::getZmhParent, bo.getZmhParent());
        lqw.eq(StringUtils.isNotBlank(bo.getZmhAncestors()), MeaContractBill::getZmhAncestors, bo.getZmhAncestors());
        lqw.eq(StringUtils.isNotBlank(bo.getDw()), MeaContractBill::getDw, bo.getDw());
        lqw.eq(bo.getHtdj() != null, MeaContractBill::getHtdj, bo.getHtdj());
        lqw.eq(bo.getXzdj() != null, MeaContractBill::getXzdj, bo.getXzdj());
        lqw.eq(bo.getHtsl() != null, MeaContractBill::getHtsl, bo.getHtsl());
        lqw.eq(bo.getHtje() != null, MeaContractBill::getHtje, bo.getHtje());
        lqw.eq(bo.getShsl() != null, MeaContractBill::getShsl, bo.getShsl());
        lqw.eq(bo.getShje() != null, MeaContractBill::getShje, bo.getShje());
        lqw.eq(bo.getXzsl() != null, MeaContractBill::getXzsl, bo.getXzsl());
        lqw.eq(bo.getXzje() != null, MeaContractBill::getXzje, bo.getXzje());
        lqw.eq(bo.getZsl() != null, MeaContractBill::getZsl, bo.getZsl());
        lqw.eq(bo.getZje() != null, MeaContractBill::getZje, bo.getZje());
        lqw.eq(StringUtils.isNotBlank(bo.getStatus()), MeaContractBill::getStatus, bo.getStatus());
        return lqw;
    }

    /**
     * 新增工程量清单
     */
    @Override
    public Boolean insertByBo(MeaContractBillBo bo) {
        MeaContractBill add = BeanUtil.toBean(bo, MeaContractBill.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改工程量清单
     */
    @Override
    public Boolean updateByBo(MeaContractBillBo bo) {
        MeaContractBill update = BeanUtil.toBean(bo, MeaContractBill.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(MeaContractBill entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除工程量清单
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<String> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
