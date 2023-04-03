package com.ruoyi.common.core.domain.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.List;

/**
 * @author ：lin_zhixiong
 * @date ：Created in 2021/7/21 5:48 下午
 * @version: V1.0
 * @slogan: 天下风云出我辈，一入代码苦耕耘
 * @description:
 **/
public class RightData {
    @JsonFormat(timezone="GMT+8",pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "type 是4可以不传，其他必传",required=false)
    private Date sttime;
    @JsonFormat(timezone="GMT+8",pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "type 是4可以不传，其他必传",required=false)
    private Date endtime;

    @ApiModelProperty(value = "周报1，月报2，自定义3，当前 4",required=true)
    private Integer type;
    @ApiModelProperty(value = "项目code ，不传默认为当前数据权限所有数据",required=false)
    private List<String> projectids;

    public Date getSttime() {
        return sttime;
    }

    public void setSttime(Date sttime) {
        this.sttime = sttime;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public List<String> getProjectids() {
        return projectids;
    }

    public void setProjectids(List<String> projectids) {
        this.projectids = projectids;
    }
}
