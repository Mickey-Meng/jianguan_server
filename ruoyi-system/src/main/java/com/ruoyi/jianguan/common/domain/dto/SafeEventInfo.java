package com.ruoyi.jianguan.common.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.jianguan.common.domain.entity.ZjSafeEvent;
import lombok.Data;

import java.util.Date;

/**
 * @version : 1.0
 * @Author : chenzhiwei
 * @date : Create file in 2022/3/1 17:34
 * @description :
 **/
@Data
public class SafeEventInfo {


    private ZjSafeEvent zjSafeEvent;

    private Integer isOverdue;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date overdueTime;

    private String gongquname;
    private String projectname;

}
