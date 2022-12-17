package com.ruoyi.project.approval.service.impl;


import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.common.core.domain.PageQuery;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.project.approval.domain.MeaLedgerApproval;
import com.ruoyi.project.approval.domain.bo.MeaLedgerApprovalBo;
import com.ruoyi.project.approval.domain.vo.MeaLedgerApprovalVo;
import com.ruoyi.project.approval.mapper.MeaLedgerApprovalMapper;
import com.ruoyi.project.approval.service.IMeaLedgerApprovalService;
import io.micrometer.core.instrument.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 台账报审Service业务层处理
 *
 * @author ruoyi
 * @date 2022-12-03
 */
@RequiredArgsConstructor
@Service
public class MeaLedgerApprovalServiceImpl implements IMeaLedgerApprovalService {

    private final MeaLedgerApprovalMapper baseMapper;

    /**
     * 查询台账报审
     */
    @Override
    public MeaLedgerApprovalVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询台账报审列表
     */
    @Override
    public TableDataInfo<MeaLedgerApprovalVo> queryPageList(MeaLedgerApprovalBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<MeaLedgerApproval> lqw = buildQueryWrapper(bo);
        Page<MeaLedgerApprovalVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询台账报审列表
     */
    @Override
    public List<MeaLedgerApprovalVo> queryList(MeaLedgerApprovalBo bo) {
        LambdaQueryWrapper<MeaLedgerApproval> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<MeaLedgerApproval> buildQueryWrapper(MeaLedgerApprovalBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<MeaLedgerApproval> lqw = Wrappers.lambdaQuery();
        lqw.eq(StringUtils.isNotBlank(bo.getBdbh()), MeaLedgerApproval::getBdbh, bo.getBdbh());
        lqw.eq(StringUtils.isNotBlank(bo.getSqqc()), MeaLedgerApproval::getSqqc, bo.getSqqc());
//        lqw.eq(StringUtils.isNotBlank(bo.getTzfjbh()), MeaLedgerApproval::getTzfjbh, bo.getTzfjbh());
        lqw.eq(StringUtils.isNotBlank(bo.getGcbw()), MeaLedgerApproval::getGcbw, bo.getGcbw());
        lqw.eq(StringUtils.isNotBlank(bo.getDataStatus()), MeaLedgerApproval::getDataStatus, bo.getDataStatus());
        lqw.eq(StringUtils.isNotBlank(bo.getSpzt()), MeaLedgerApproval::getSpzt, bo.getSpzt());
        lqw.eq(StringUtils.isNotBlank(bo.getStatus()), MeaLedgerApproval::getStatus, bo.getStatus());
        return lqw;
    }

    /**
     * 新增台账报审
     */
    @Override
    public Boolean insertByBo(MeaLedgerApprovalBo bo) {
        MeaLedgerApproval add = BeanUtil.toBean(bo, MeaLedgerApproval.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改台账报审
     */
    @Override
    public Boolean updateByBo(MeaLedgerApprovalBo bo) {
        MeaLedgerApproval update = BeanUtil.toBean(bo, MeaLedgerApproval.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(MeaLedgerApproval entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除台账报审
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
