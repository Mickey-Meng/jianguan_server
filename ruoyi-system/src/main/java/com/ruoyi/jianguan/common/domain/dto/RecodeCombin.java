package com.ruoyi.jianguan.common.domain.dto;

import com.ruoyi.common.core.domain.entity.Produceandrecode;
import com.ruoyi.jianguan.common.domain.entity.Recode;

public class RecodeCombin {
    private Recode recode;
    private Produceandrecode produceandrecode;

    public Produceandrecode getProduceandrecode() {
        return produceandrecode;
    }

    public void setProduceandrecode(Produceandrecode produceandrecode) {
        this.produceandrecode = produceandrecode;
    }

    public Recode getRecode() {
        return recode;
    }

    public void setRecode(Recode recode) {
        this.recode = recode;
    }

}
