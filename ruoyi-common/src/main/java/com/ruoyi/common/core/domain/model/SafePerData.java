package com.ruoyi.common.core.domain.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * @author ：lin_zhixiong
 * @date ：Created in 2021/7/23 10:19 上午
 * @version: V1.0
 * @slogan: 天下风云出我辈，一入代码苦耕耘
 * @description:
 **/
public class SafePerData {

    @ApiModelProperty(value = "月时间,获取安全检查的时候必传，",required=false)
    @JsonFormat(pattern = "yyyy-MM")
    private Date  askTime;
    @ApiModelProperty(value = "开始时间-获取构件数据的时候必传",required=false)
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date sttime;
    @ApiModelProperty(value = "结束时间-获取构件数据的时候必传",required=false)
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date endtime;

    @ApiModelProperty(value = "项目类型（QL || SD || LM || other）", required = false)
    private String type;
    @ApiModelProperty(value = "构件类型（如：ZJ、CT）")
    private String compontentType;
    @ApiModelProperty(value = "projectId")
    private Integer projectId;

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Date getAskTime() {
        return askTime;
    }

    public void setAskTime(Date askTime) {
        this.askTime = askTime;
    }

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCompontentType() {
        return compontentType;
    }

    public void setCompontentType(String compontentType) {
        this.compontentType = compontentType;
    }
}
