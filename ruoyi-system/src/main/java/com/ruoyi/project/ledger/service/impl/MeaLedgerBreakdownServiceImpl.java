package com.ruoyi.project.ledger.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.project.ledger.domain.MeaLedgerBreakdown;
import com.ruoyi.project.ledger.domain.bo.MeaLedgerBreakdownBo;
import com.ruoyi.project.ledger.domain.vo.MeaLedgerBreakdownVo;
import com.ruoyi.project.ledger.mapper.MeaLedgerBreakdownMapper;
import com.ruoyi.project.ledger.service.IMeaLedgerBreakdownService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 台账分解Service业务层处理
 *
 * @author ruoyi
 * @date 2022-12-07
 */
@RequiredArgsConstructor
@Service
public class MeaLedgerBreakdownServiceImpl implements IMeaLedgerBreakdownService {

    private final MeaLedgerBreakdownMapper baseMapper;

    /**
     * 查询台账分解
     */
    @Override
    public MeaLedgerBreakdownVo queryById(Long id) {
        return baseMapper.selectVoById(id);
    }


    /**
     * 查询台账分解列表
     */
    @Override
    public List<MeaLedgerBreakdownVo> queryList(MeaLedgerBreakdownBo bo) {
        LambdaQueryWrapper<MeaLedgerBreakdown> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<MeaLedgerBreakdown> buildQueryWrapper(MeaLedgerBreakdownBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<MeaLedgerBreakdown> lqw = Wrappers.lambdaQuery();
        lqw.eq(StringUtils.isNotBlank(bo.getBdbh()), MeaLedgerBreakdown::getBdbh, bo.getBdbh());
        lqw.eq(StringUtils.isNotBlank(bo.getTzfjbh()), MeaLedgerBreakdown::getTzfjbh, bo.getTzfjbh());
        lqw.eq(StringUtils.isNotBlank(bo.getTzfjbhParent()), MeaLedgerBreakdown::getTzfjbhParent, bo.getTzfjbhParent());
        lqw.eq(StringUtils.isNotBlank(bo.getTzfjbhAncestors()), MeaLedgerBreakdown::getTzfjbhAncestors, bo.getTzfjbhAncestors());
        lqw.eq(StringUtils.isNotBlank(bo.getTzfjmc()), MeaLedgerBreakdown::getTzfjmc, bo.getTzfjmc());
        lqw.eq(StringUtils.isNotBlank(bo.getFjlx()), MeaLedgerBreakdown::getFjlx, bo.getFjlx());
        lqw.eq(StringUtils.isNotBlank(bo.getStatus()), MeaLedgerBreakdown::getStatus, bo.getStatus());
        lqw.eq(bo.getParentId() != null, MeaLedgerBreakdown::getParentId, bo.getParentId());
        return lqw;
    }


    /**
     * 生成台账分解编号
     *
     * @return
     */
    public String generateTzfjbh() {


        return "";
    }


    /**
     * 生成台账分解编号
     *
     * @return
     */
    public String resolvePrentTzfjbh(String prentTzfjbh) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 1; i < prentTzfjbh.length(); i++) {

            if (i % 3 == 0) {
                stringBuffer.append(prentTzfjbh.substring(0, i) + ",");
            }

        }

        stringBuffer.append(prentTzfjbh);
        return stringBuffer.toString();
    }


    /**
     * 新增台账分解
     */
    @Override
    public R insertByBo(MeaLedgerBreakdownBo bo) {


        if (bo.getTzfjbhParent().equals("0")) {
            bo.setTzfjbhParent("101");

        }

        String nextCode = baseMapper.getNextCode(bo.getTzfjbhParent());

        if (nextCode == null) {
            nextCode = bo.getTzfjbhParent() + "101";
        } else {
            nextCode = new BigDecimal(nextCode).add(new BigDecimal("1")).toString();
        }
        bo.setTzfjbh(nextCode);
        System.out.println(resolvePrentTzfjbh(bo.getTzfjbhParent()));
        bo.setTzfjbhAncestors(resolvePrentTzfjbh(bo.getTzfjbhParent()));

        MeaLedgerBreakdown add = BeanUtil.toBean(bo, MeaLedgerBreakdown.class);

        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return R.ok(flag);
    }

    /**
     * 修改台账分解
     */
    @Override
    public Boolean updateByBo(MeaLedgerBreakdownBo bo) {
        MeaLedgerBreakdown update = BeanUtil.toBean(bo, MeaLedgerBreakdown.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(MeaLedgerBreakdown entity) {
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除台账分解
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if (isValid) {
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
