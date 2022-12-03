package com.ruoyi.project.contract.service.impl;


import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.common.core.domain.PageQuery;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.project.contract.domain.MeaContractInfo;
import com.ruoyi.project.contract.domain.bo.MeaContractInfoBo;
import com.ruoyi.project.contract.domain.vo.MeaContractInfoVo;
import com.ruoyi.project.contract.mapper.MeaContractInfoMapper;
import com.ruoyi.project.contract.service.IMeaContractInfoService;
import io.micrometer.core.instrument.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 合同条款Service业务层处理
 *
 * @author ruoyi
 * @date 2022-12-03
 */
@RequiredArgsConstructor
@Service
public class MeaContractInfoServiceImpl implements IMeaContractInfoService {

    private final MeaContractInfoMapper baseMapper;

    /**
     * 查询合同条款
     */
    @Override
    public MeaContractInfoVo queryById(String id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询合同条款列表
     */
    @Override
    public TableDataInfo<MeaContractInfoVo> queryPageList(MeaContractInfoBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<MeaContractInfo> lqw = buildQueryWrapper(bo);
        Page<MeaContractInfoVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询合同条款列表
     */
    @Override
    public List<MeaContractInfoVo> queryList(MeaContractInfoBo bo) {
        LambdaQueryWrapper<MeaContractInfo> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<MeaContractInfo> buildQueryWrapper(MeaContractInfoBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<MeaContractInfo> lqw = Wrappers.lambdaQuery();
        lqw.eq(StringUtils.isNotBlank(bo.getBdbh()), MeaContractInfo::getBdbh, bo.getBdbh());
        lqw.eq(StringUtils.isNotBlank(bo.getHtbh()), MeaContractInfo::getHtbh, bo.getHtbh());
        lqw.eq(bo.getKgrq() != null, MeaContractInfo::getKgrq, bo.getKgrq());
        lqw.eq(bo.getJgrq() != null, MeaContractInfo::getJgrq, bo.getJgrq());
        lqw.eq(StringUtils.isNotBlank(bo.getXmmc()), MeaContractInfo::getXmmc, bo.getXmmc());
        lqw.eq(bo.getHtzje() != null, MeaContractInfo::getHtzje, bo.getHtzje());
        lqw.eq(StringUtils.isNotBlank(bo.getHtd()), MeaContractInfo::getHtd, bo.getHtd());
        lqw.eq(bo.getGclqdje() != null, MeaContractInfo::getGclqdje, bo.getGclqdje());
        lqw.eq(bo.getKgyfkje() != null, MeaContractInfo::getKgyfkje, bo.getKgyfkje());
        lqw.eq(bo.getZljje() != null, MeaContractInfo::getZljje, bo.getZljje());
        lqw.eq(StringUtils.isNotBlank(bo.getHtgq()), MeaContractInfo::getHtgq, bo.getHtgq());
        lqw.eq(bo.getKgyfkqkbl() != null, MeaContractInfo::getKgyfkqkbl, bo.getKgyfkqkbl());
        lqw.eq(bo.getKgyfkjzbl() != null, MeaContractInfo::getKgyfkjzbl, bo.getKgyfkjzbl());
        lqw.eq(bo.getZbjkkbl() != null, MeaContractInfo::getZbjkkbl, bo.getZbjkkbl());
        lqw.eq(bo.getNmggzbzjkkbl() != null, MeaContractInfo::getNmggzbzjkkbl, bo.getNmggzbzjkkbl());
        lqw.eq(bo.getZbjze() != null, MeaContractInfo::getZbjze, bo.getZbjze());
        lqw.eq(bo.getJrgje() != null, MeaContractInfo::getJrgje, bo.getJrgje());
        lqw.eq(bo.getBdcd() != null, MeaContractInfo::getBdcd, bo.getBdcd());
        lqw.eq(StringUtils.isNotBlank(bo.getQqzh()), MeaContractInfo::getQqzh, bo.getQqzh());
        lqw.eq(StringUtils.isNotBlank(bo.getKgyjkzfqk()), MeaContractInfo::getKgyjkzfqk, bo.getKgyjkzfqk());
        lqw.eq(StringUtils.isNotBlank(bo.getKgyfkkhgd()), MeaContractInfo::getKgyfkkhgd, bo.getKgyfkkhgd());
        lqw.eq(StringUtils.isNotBlank(bo.getClykfzfqk()), MeaContractInfo::getClykfzfqk, bo.getClykfzfqk());
        lqw.eq(StringUtils.isNotBlank(bo.getClyfkkhgd()), MeaContractInfo::getClyfkkhgd, bo.getClyfkkhgd());
        lqw.eq(StringUtils.isNotBlank(bo.getStatus()), MeaContractInfo::getStatus, bo.getStatus());
        return lqw;
    }

    /**
     * 新增合同条款
     */
    @Override
    public Boolean insertByBo(MeaContractInfoBo bo) {
        MeaContractInfo add = BeanUtil.toBean(bo, MeaContractInfo.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改合同条款
     */
    @Override
    public Boolean updateByBo(MeaContractInfoBo bo) {
        MeaContractInfo update = BeanUtil.toBean(bo, MeaContractInfo.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(MeaContractInfo entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除合同条款
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<String> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
