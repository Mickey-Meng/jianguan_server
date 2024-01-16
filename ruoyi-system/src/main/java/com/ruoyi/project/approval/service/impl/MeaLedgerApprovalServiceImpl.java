package com.ruoyi.project.approval.service.impl;


import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.common.core.domain.PageQuery;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.project.approval.domain.MeaLedgerApproval;
import com.ruoyi.project.approval.domain.MeaLedgerApprovalNo;
import com.ruoyi.project.approval.domain.bo.MeaLedgerApprovalBo;
import com.ruoyi.project.approval.domain.vo.MeaLedgerApprovalBreakDownVo;
import com.ruoyi.project.approval.domain.vo.MeaLedgerApprovalVo;
import com.ruoyi.project.approval.mapper.MeaLedgerApprovalMapper;
import com.ruoyi.project.approval.mapper.MeaLedgerApprovalNoMapper;
import com.ruoyi.project.approval.service.IMeaLedgerApprovalService;
import com.ruoyi.project.ledger.domain.vo.MeaLedgerBreakdownDetailVo;
import com.ruoyi.project.ledger.mapper.MeaLedgerBreakdownDetailMapper;
import io.micrometer.core.instrument.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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

    private final MeaLedgerApprovalMapper meaLedgerApprovalMapper;

    private final MeaLedgerApprovalNoMapper meaLedgerApprovalNoMapper;

    private final MeaLedgerBreakdownDetailMapper meaLedgerBreakdownDetailMapper;

    /**
     * 查询台账报审
     */
    @Override
    public MeaLedgerApprovalVo queryById(Long id) {
        return meaLedgerApprovalMapper.selectVoById(id);
    }

    /**
     * 查询台账报审列表
     */
    @Override
    public TableDataInfo<MeaLedgerApprovalVo> queryPageList(MeaLedgerApprovalBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<MeaLedgerApproval> lqw = buildQueryWrapper(bo);
        Page<MeaLedgerApprovalVo> result = meaLedgerApprovalMapper.selectVoPage(pageQuery.build(), lqw);

        return TableDataInfo.build(result);
    }

    /**
     * 查询台账报审列表
     */
    @Override
    public List<MeaLedgerApprovalVo> queryList(MeaLedgerApprovalBo bo) {
        LambdaQueryWrapper<MeaLedgerApproval> lqw = buildQueryWrapper(bo);
        return meaLedgerApprovalMapper.selectVoList(lqw);
    }

    @Override
    public List<MeaLedgerApprovalBreakDownVo> queryMeaLedgerApprovalBreakDownList(MeaLedgerApprovalBo bo) {

        List<MeaLedgerApprovalBreakDownVo> meaLedgerApprovalBreakDownVoList = new ArrayList<>();
        LambdaQueryWrapper<MeaLedgerApproval> lqw = buildQueryWrapper(bo);
        List<MeaLedgerApprovalVo> meaLedgerApprovalVos =  meaLedgerApprovalMapper.selectVoList(lqw);
        for (MeaLedgerApprovalVo meaLedgerApprovalVo : meaLedgerApprovalVos) {
            // 查询审批中或审批完成的台账分解明细
            MeaLedgerBreakdownDetailVo meaLedgerBreakdownDetailVo =  meaLedgerBreakdownDetailMapper.selectVoById(meaLedgerApprovalVo.getBreakdownId());
            MeaLedgerApprovalBreakDownVo meaLedgerApprovalBreakDownVo = new MeaLedgerApprovalBreakDownVo();
            BeanUtil.copyProperties(meaLedgerApprovalVo,meaLedgerApprovalBreakDownVo);
            BeanUtil.copyProperties(meaLedgerBreakdownDetailVo,meaLedgerApprovalBreakDownVo);
            if(meaLedgerBreakdownDetailVo != null){
                meaLedgerApprovalBreakDownVo.setGcbw(meaLedgerBreakdownDetailVo.getFjmulu());
            }
            meaLedgerApprovalBreakDownVoList.add(meaLedgerApprovalBreakDownVo);
        }
        return  meaLedgerApprovalBreakDownVoList;
    }

    private LambdaQueryWrapper<MeaLedgerApproval> buildQueryWrapper(MeaLedgerApprovalBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<MeaLedgerApproval> lqw = Wrappers.lambdaQuery();
        lqw.eq(StringUtils.isNotBlank(bo.getBdbh()), MeaLedgerApproval::getBdbh, bo.getBdbh());
        lqw.eq(StringUtils.isNotBlank(bo.getSqqc()), MeaLedgerApproval::getSqqc, bo.getSqqc());
        // 增加查询条件
        lqw.likeRight(StringUtils.isNotBlank(bo.getTzfjbh()), MeaLedgerApproval::getTzfjbh, bo.getTzfjbh());
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
    @Transactional
    public Boolean insertByBo(MeaLedgerApprovalBo bo) {
        MeaLedgerApproval add = BeanUtil.toBean(bo, MeaLedgerApproval.class);
        validEntityBeforeSave(add);
        add.setReviewCode("1");
        boolean flag = meaLedgerApprovalMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }

        LambdaQueryWrapper<MeaLedgerApprovalNo> lqw = Wrappers.lambdaQuery();
        lqw.eq(StringUtils.isNotBlank(bo.getSqqc()), MeaLedgerApprovalNo::getSqqc, bo.getSqqc());
        MeaLedgerApprovalNo meaLedgerApprovalNo =  meaLedgerApprovalNoMapper.selectOne(lqw) ;
        meaLedgerApprovalNo.setReviewCode("1");
        meaLedgerApprovalNoMapper.updateById(meaLedgerApprovalNo);

        return flag;
    }

    /**
     * 修改台账报审
     */
    @Override
    public Boolean updateByBo(MeaLedgerApprovalBo bo) {
        MeaLedgerApproval update = BeanUtil.toBean(bo, MeaLedgerApproval.class);
        validEntityBeforeSave(update);
        return meaLedgerApprovalMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(MeaLedgerApproval entity) {
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除台账报审
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if (isValid) {
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return meaLedgerApprovalMapper.deleteBatchIds(ids) > 0;
    }

    @Override
    public boolean insertByListBo(List<MeaLedgerApprovalBo> bo) {



        List<MeaLedgerApproval> meaLedgerApprovals = BeanUtil.copyToList(bo, MeaLedgerApproval.class);
//        validEntityBeforeSave(meaLedgerApprovals);

        boolean b = meaLedgerApprovalMapper.insertBatch(meaLedgerApprovals);

        return b;

    }

    @Override
    public List<MeaLedgerApprovalBreakDownVo> getInfoData(List<MeaLedgerApprovalBo> bos) {
        List<MeaLedgerApprovalBreakDownVo> meaLedgerApprovalBreakDownVoList = new ArrayList<>();
        for (MeaLedgerApprovalBo meaLedgerApprovalBo : bos) {
            MeaLedgerBreakdownDetailVo meaLedgerBreakdownDetailVo =  meaLedgerBreakdownDetailMapper.selectVoById(meaLedgerApprovalBo.getBreakdownId());
            MeaLedgerApprovalBreakDownVo meaLedgerApprovalBreakDownVo = new MeaLedgerApprovalBreakDownVo();
            BeanUtil.copyProperties(meaLedgerApprovalBo,meaLedgerApprovalBreakDownVo);
            BeanUtil.copyProperties(meaLedgerBreakdownDetailVo,meaLedgerApprovalBreakDownVo);
            if(meaLedgerBreakdownDetailVo != null){
                meaLedgerApprovalBreakDownVo.setGcbw(meaLedgerBreakdownDetailVo.getFjmulu());
            }
            meaLedgerApprovalBreakDownVoList.add(meaLedgerApprovalBreakDownVo);
        }
        return  meaLedgerApprovalBreakDownVoList;
    }
}
