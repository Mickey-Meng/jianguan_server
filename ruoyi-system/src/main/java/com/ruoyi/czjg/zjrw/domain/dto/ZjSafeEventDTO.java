package com.ruoyi.czjg.zjrw.domain.dto;

import com.ruoyi.czjg.zjrw.domain.entity.SafeGongQugroup;
import com.ruoyi.czjg.zjrw.domain.entity.SafeGongQugroupOverdue;

import java.util.List;

/**
 * @version : 1.0
 * @Author : chenzhiwei
 * @date : Create file in 2022/3/1 15:37
 * @description :
 **/
public class ZjSafeEventDTO {

    private List<SafeGongQugroup> total;

    private List<SafeGongQugroupOverdue> overdueList;

    public List<SafeGongQugroup> getTotal() {
        return total;
    }

    public void setTotal(List<SafeGongQugroup> total) {
        this.total = total;
    }

    public List<SafeGongQugroupOverdue> getOverdueList() {
        return overdueList;
    }

    public void setOverdueList(List<SafeGongQugroupOverdue> overdueList) {
        this.overdueList = overdueList;
    }
}
