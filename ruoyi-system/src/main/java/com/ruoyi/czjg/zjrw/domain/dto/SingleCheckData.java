package com.ruoyi.czjg.zjrw.domain.dto;

import com.ruoyi.common.core.domain.entity.Produceandrecode;
import com.ruoyi.czjg.zjrw.domain.entity.Recode;
import com.ruoyi.czjg.zjrw.domain.entity.Recodedetail;

/**
 * @author ：lin_zhixiong
 * @date ：Created in 2021/7/21 1:57 下午
 * @version: V1.0
 * @slogan: 天下风云出我辈，一入代码苦耕耘
 * @description:
 **/
public class SingleCheckData {
    private Produceandrecode produceandrecode;
    private Recode recode;
    private Recodedetail fillin;
    private Recodedetail check;

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

    public Recodedetail getFillin() {
        return fillin;
    }

    public void setFillin(Recodedetail fillin) {
        this.fillin = fillin;
    }

    public Recodedetail getCheck() {
        return check;
    }

    public void setCheck(Recodedetail check) {
        this.check = check;
    }
}
