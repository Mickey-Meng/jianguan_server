package com.ruoyi.web.controller.jianguan.zlsk.strestservice.dp.service.impl;

import com.ruoyi.web.controller.jianguan.zlsk.strestservice.dp.enums.CostTypeEnum;
import com.ruoyi.web.controller.jianguan.zlsk.strestservice.dp.enums.RateEnum;
import com.ruoyi.web.controller.jianguan.zlsk.strestservice.dp.mapper.BaseMapper;
import com.ruoyi.web.controller.jianguan.zlsk.strestservice.dp.mapper.DpEngineeringMapper;
import com.ruoyi.web.controller.jianguan.zlsk.strestservice.dp.mapper.DpOtherCostMapper;
import com.ruoyi.web.controller.jianguan.zlsk.strestservice.dp.model.DpOtherCost;
import com.ruoyi.web.controller.jianguan.zlsk.strestservice.dp.service.IDpOtherCostService;
import org.springframework.stereotype.*;
import org.springframework.beans.factory.annotation.*;
import java.math.*;
import java.util.*;

@Service
public class DpOtherCostServiceImpl extends BaseServiceImpl<DpOtherCost> implements IDpOtherCostService
{
    @Autowired
    private DpOtherCostMapper dpOtherCostMapper;
    @Autowired
    private DpEngineeringMapper dpEngineeringMapper;

    @Override
    public BaseMapper<DpOtherCost> getBaseMapper() {
        return this.dpOtherCostMapper;
    }

    @Override
    public List<DpOtherCost> workList(final Integer engineeringId, final Integer typeId) {
        final List<DpOtherCost> dpOtherCosts = this.dpOtherCostMapper.selectList(engineeringId, typeId);
        if (dpOtherCosts.size() != 0) {
            return dpOtherCosts;
        }
        final Double ciCost = this.dpEngineeringMapper.selectCiCost(engineeringId);
        final Double otherFee = new BigDecimal(Double.toString(ciCost)).multiply(new BigDecimal(Double.toString(RateEnum.OTHER_FEE_RATE.getRate()))).doubleValue();
        final BigDecimal add1 = new BigDecimal(Double.toString(ciCost)).add(new BigDecimal(Double.toString(otherFee)));
        final Double budgetReserve = add1.multiply(new BigDecimal(Double.toString(RateEnum.BUDGET_RESERVE_RATE.getRate()))).doubleValue();
        final Double administrativeExpense = 0.0;
        final Double financialExpense = 0.0;
        final DpOtherCost dpOtherCost = new DpOtherCost();
        dpOtherCost.setEngineeringId(engineeringId);
        dpOtherCost.setCostType(typeId);
        if (typeId == CostTypeEnum.OTHER_FEE.getTypeId()) {
            dpOtherCost.setProjectName(CostTypeEnum.OTHER_FEE.getName());
            dpOtherCost.setCost(otherFee);
        }
        else if (typeId == CostTypeEnum.BUDGET_RESERVE.getTypeId()) {
            dpOtherCost.setProjectName(CostTypeEnum.BUDGET_RESERVE.getName());
            dpOtherCost.setCost(budgetReserve);
        }
        else if (typeId == CostTypeEnum.ADMINISTRATIVE_EXPENSE.getTypeId()) {
            dpOtherCost.setProjectName(CostTypeEnum.ADMINISTRATIVE_EXPENSE.getName());
            dpOtherCost.setCost(administrativeExpense);
        }
        else if (typeId == CostTypeEnum.FINANCIAL_EXPENSE.getTypeId()) {
            dpOtherCost.setProjectName(CostTypeEnum.ADMINISTRATIVE_EXPENSE.getName());
            dpOtherCost.setCost(financialExpense);
        }
        this.dpOtherCostMapper.insert(dpOtherCost);
        return Arrays.asList(dpOtherCost);
    }
}
