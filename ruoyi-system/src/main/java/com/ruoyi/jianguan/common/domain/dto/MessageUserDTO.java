package com.ruoyi.jianguan.common.domain.dto;

import com.ruoyi.common.core.domain.entity.Produceandrecode;
import com.ruoyi.jianguan.common.domain.entity.ZjQualityEvent;
import com.ruoyi.jianguan.common.domain.entity.ZjSafeEvent;

import java.util.List;

/**
 * @version : 1.0
 * @Author : chenzhiwei
 * @date : Create file in 2022/2/25 11:34
 * @description :
 **/
public class MessageUserDTO {

    private List<ZjSafeEvent> zjSafeEventList;

    private List<ZjQualityEvent> zjQualityEventList;
    //安全延期
    private List<ZjSafeEvent> delaySafeEvent;
    //安全整改确认
    private List<ZjSafeEvent> doRectificationSafeEvent;
    //质量延期
    private List<ZjQualityEvent> delayQualityEvent;
    //质量整改确认
    private List<ZjQualityEvent> doRectificationQualityEvent;
    //工序填报
    private List<Produceandrecode> processFilling;
    //工序抄送
    private List<Produceandrecode> produceCopy;

    public List<Produceandrecode> getProcessFilling() {
        return processFilling;
    }

    public void setProcessFilling(List<Produceandrecode> processFilling) {
        this.processFilling = processFilling;
    }

    public List<ZjSafeEvent> getZjSafeEventList() {
        return zjSafeEventList;
    }

    public void setZjSafeEventList(List<ZjSafeEvent> zjSafeEventList) {
        this.zjSafeEventList = zjSafeEventList;
    }

    public List<ZjQualityEvent> getZjQualityEventList() {
        return zjQualityEventList;
    }

    public void setZjQualityEventList(List<ZjQualityEvent> zjQualityEventList) {
        this.zjQualityEventList = zjQualityEventList;
    }

    public List<ZjSafeEvent> getDelaySafeEvent() {
        return delaySafeEvent;
    }

    public void setDelaySafeEvent(List<ZjSafeEvent> delaySafeEvent) {
        this.delaySafeEvent = delaySafeEvent;
    }

    public List<ZjSafeEvent> getDoRectificationSafeEvent() {
        return doRectificationSafeEvent;
    }

    public void setDoRectificationSafeEvent(List<ZjSafeEvent> doRectificationSafeEvent) {
        this.doRectificationSafeEvent = doRectificationSafeEvent;
    }

    public List<ZjQualityEvent> getDelayQualityEvent() {
        return delayQualityEvent;
    }

    public void setDelayQualityEvent(List<ZjQualityEvent> delayQualityEvent) {
        this.delayQualityEvent = delayQualityEvent;
    }

    public List<ZjQualityEvent> getDoRectificationQualityEvent() {
        return doRectificationQualityEvent;
    }

    public void setDoRectificationQualityEvent(List<ZjQualityEvent> doRectificationQualityEvent) {
        this.doRectificationQualityEvent = doRectificationQualityEvent;
    }

    public List<Produceandrecode> getProduceCopy() {
        return produceCopy;
    }

    public void setProduceCopy(List<Produceandrecode> produceCopy) {
        this.produceCopy = produceCopy;
    }
}
