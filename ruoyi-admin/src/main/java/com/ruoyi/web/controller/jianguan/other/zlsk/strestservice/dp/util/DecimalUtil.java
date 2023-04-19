package com.ruoyi.web.controller.jianguan.other.zlsk.strestservice.dp.util;

import com.ruoyi.web.controller.jianguan.other.zlsk.strestservice.dp.model.LadCost;

import java.math.*;
import java.util.*;

public class DecimalUtil
{
    public static Double ladCostDecimal(final List<LadCost> lad, final Double ladUnforeseenRate) {
        BigDecimal totalDecimal = new BigDecimal(Double.toString(0.0));
        for (final LadCost ladCost : lad) {
            if (ladCost.getTotalCost() == null) {
                ladCost.setTotalCost(0.0);
            }
            totalDecimal = totalDecimal.add(new BigDecimal(Double.toString(ladCost.getTotalCost())));
        }
        final BigDecimal multiply = totalDecimal.multiply(new BigDecimal(Double.toString(ladUnforeseenRate)));
        return totalDecimal.add(multiply).doubleValue();
    }
}
