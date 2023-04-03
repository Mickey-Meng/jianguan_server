package com.ruoyi.czjg.zjrw.domain.dto;

import com.ruoyi.common.core.domain.entity.Conponent;
import com.ruoyi.common.core.domain.entity.Produce;
import com.ruoyi.common.core.domain.entity.Produceandrecode;

import java.io.Serializable;
import java.util.List;

/**
 * @Author : chenzhiwei
 * @Date : Create file in 2022/3/31 15:47
 * @Version : 1.0
 * @Description :
 **/
public class ComponentDataDetail implements Serializable {

    private ComponentProgressDetails progressDetails;

    private Conponent conponent;

    private List<Produceandrecode> produceandrecodes;

    private List<Produce> produces;

    public List<Produce> getProduces() {
        return produces;
    }

    public void setProduces(List<Produce> produces) {
        this.produces = produces;
    }

    public ComponentProgressDetails getProgressDetails() {
        return progressDetails;
    }

    public void setProgressDetails(ComponentProgressDetails progressDetails) {
        this.progressDetails = progressDetails;
    }

    public Conponent getConponent() {
        return conponent;
    }

    public void setConponent(Conponent conponent) {
        this.conponent = conponent;
    }

    public List<Produceandrecode> getProduceandrecodes() {
        return produceandrecodes;
    }

    public void setProduceandrecodes(List<Produceandrecode> produceandrecodes) {
        this.produceandrecodes = produceandrecodes;
    }

    @Override
    public String toString() {
        return "ComponentDataDetail{" +
                "progressDetails=" + progressDetails +
                ", conponent=" + conponent +
                ", produceandrecodes=" + produceandrecodes +
                ", produces=" + produces +
                '}';
    }
}
