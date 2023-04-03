package com.ruoyi.czjg.zjrw.domain.dto;

import com.ruoyi.czjg.zjrw.domain.entity.Recode;
import com.ruoyi.czjg.zjrw.domain.entity.Recodedetail;

/**
 * @author ：lin_zhixiong
 * @date ：Created in 2021/7/14 4:33 下午
 * @version: V1.0
 * @slogan: 天下风云出我辈，一入代码苦耕耘
 * @description:
 **/
public class ConPonentRecond  implements Comparable<ConPonentRecond>{
    private Integer produceid;
    private String produceName;

    public Integer getProduceid() {
        return produceid;
    }

    public void setProduceid(Integer produceid) {
        this.produceid = produceid;
    }

    public String getProduceName() {
        return produceName;
    }

    public void setProduceName(String produceName) {
        this.produceName = produceName;
    }

    private Integer result;
    private Recode recode;

    private  String checkName;

    public String getCheckName() {
        return checkName;
    }

    public void setCheckName(String checkName) {
        this.checkName = checkName;
    }

    private Recodedetail upload;
    private Recodedetail submit;

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public Recode getRecode() {
        return recode;
    }

    public void setRecode(Recode recode) {
        this.recode = recode;
    }

    public Recodedetail getUpload() {
        return upload;
    }

    public void setUpload(Recodedetail upload) {
        this.upload = upload;
    }

    public Recodedetail getSubmit() {
        return submit;
    }

    public void setSubmit(Recodedetail submit) {
        this.submit = submit;
    }

    @Override
    public int compareTo(ConPonentRecond o) {
        Integer i = this.produceid.compareTo(o.getProduceid());

        return i;
    }
}
