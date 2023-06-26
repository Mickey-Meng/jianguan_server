package com.ruoyi.jianguan.business.contract.domain.dto;

import com.ruoyi.common.core.domain.dto.PageDTO;
import lombok.Data;

import java.util.Date;

@Data
public class ConstructionPrototypePageDTO extends PageDTO {

    /**
     * 上报时间
     */
    private Date reportTime;

    /**
     * 状态 0 审批中 1 审批完成 2 驳回
     */
    private Integer status;
    private String content;
    private String reportPeople;
}
