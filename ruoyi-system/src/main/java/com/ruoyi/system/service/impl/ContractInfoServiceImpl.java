package com.ruoyi.system.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.ruoyi.system.domain.bo.ContractInfoBo;
import com.ruoyi.system.domain.vo.ContractInfoVo;
import com.ruoyi.system.domain.ContractInfo;
import com.ruoyi.system.mapper.ContractInfoMapper;
import com.ruoyi.system.service.IContractInfoService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 合同条款Service业务层处理
 *
 * @author ruoyi
 * @date 2022-12-02
 */
@RequiredArgsConstructor
@Service
public class ContractInfoServiceImpl implements IContractInfoService {

    private final ContractInfoMapper baseMapper;

    /**
     * 查询合同条款
     */
    @Override
    public ContractInfoVo queryById(String HTBH){
        return baseMapper.selectVoById(HTBH);
    }

    /**
     * 查询合同条款列表
     */
    @Override
    public TableDataInfo<ContractInfoVo> queryPageList(ContractInfoBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<ContractInfo> lqw = buildQueryWrapper(bo);
        Page<ContractInfoVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询合同条款列表
     */
    @Override
    public List<ContractInfoVo> queryList(ContractInfoBo bo) {
        LambdaQueryWrapper<ContractInfo> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<ContractInfo> buildQueryWrapper(ContractInfoBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<ContractInfo> lqw = Wrappers.lambdaQuery();
        lqw.eq(StringUtils.isNotBlank(bo.getBDBH()), ContractInfo::getBDBH, bo.getBDBH());
        lqw.eq(bo.getKGRQ() != null, ContractInfo::getKGRQ, bo.getKGRQ());
        lqw.eq(bo.getJGRQ() != null, ContractInfo::getJGRQ, bo.getJGRQ());
        lqw.eq(StringUtils.isNotBlank(bo.getXMMC()), ContractInfo::getXMMC, bo.getXMMC());
        lqw.eq(bo.getHTZJE() != null, ContractInfo::getHTZJE, bo.getHTZJE());
        lqw.eq(StringUtils.isNotBlank(bo.getHTD()), ContractInfo::getHTD, bo.getHTD());
        lqw.eq(bo.getGCLQDJE() != null, ContractInfo::getGCLQDJE, bo.getGCLQDJE());
        lqw.eq(bo.getKGYFKJE() != null, ContractInfo::getKGYFKJE, bo.getKGYFKJE());
        lqw.eq(bo.getZLJJE() != null, ContractInfo::getZLJJE, bo.getZLJJE());
        lqw.eq(StringUtils.isNotBlank(bo.getHTGQ()), ContractInfo::getHTGQ, bo.getHTGQ());
        lqw.eq(bo.getKGYFKQKBL() != null, ContractInfo::getKGYFKQKBL, bo.getKGYFKQKBL());
        lqw.eq(bo.getKGYFKJZBL() != null, ContractInfo::getKGYFKJZBL, bo.getKGYFKJZBL());
        lqw.eq(bo.getZBJKKBL() != null, ContractInfo::getZBJKKBL, bo.getZBJKKBL());
        lqw.eq(bo.getNMGGZBZJKKBL() != null, ContractInfo::getNMGGZBZJKKBL, bo.getNMGGZBZJKKBL());
        lqw.eq(bo.getZBJZE() != null, ContractInfo::getZBJZE, bo.getZBJZE());
        lqw.eq(bo.getJRGJE() != null, ContractInfo::getJRGJE, bo.getJRGJE());
        lqw.eq(bo.getBDCD() != null, ContractInfo::getBDCD, bo.getBDCD());
        lqw.eq(StringUtils.isNotBlank(bo.getQQZH()), ContractInfo::getQQZH, bo.getQQZH());
        lqw.eq(StringUtils.isNotBlank(bo.getKGYJKZFQK()), ContractInfo::getKGYJKZFQK, bo.getKGYJKZFQK());
        lqw.eq(StringUtils.isNotBlank(bo.getKGYFKKHGD()), ContractInfo::getKGYFKKHGD, bo.getKGYFKKHGD());
        lqw.eq(StringUtils.isNotBlank(bo.getCLYKFZFQK()), ContractInfo::getCLYKFZFQK, bo.getCLYKFZFQK());
        lqw.eq(StringUtils.isNotBlank(bo.getCLYFKKHGD()), ContractInfo::getCLYFKKHGD, bo.getCLYFKKHGD());
        lqw.eq(StringUtils.isNotBlank(bo.getStatus()), ContractInfo::getStatus, bo.getStatus());
        return lqw;
    }

    /**
     * 新增合同条款
     */
    @Override
    public Boolean insertByBo(ContractInfoBo bo) {
        ContractInfo add = BeanUtil.toBean(bo, ContractInfo.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setHTBH(add.getHTBH());
        }
        return flag;
    }

    /**
     * 修改合同条款
     */
    @Override
    public Boolean updateByBo(ContractInfoBo bo) {
        ContractInfo update = BeanUtil.toBean(bo, ContractInfo.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(ContractInfo entity){
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
