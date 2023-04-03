package com.ruoyi.czjg.zjrw.domain.entity;

import io.swagger.annotations.ApiModelProperty;

import java.util.Objects;

/**
 * @version : 1.0
 * @Author : chenzhiwei
 * @date : Create file in 2022/3/3 13:54
 * @description : 数字孪生
 **/
public class ZjDigitalTwin {

    // todo 暂定以下字段,后面可按需求再进行增加
    @ApiModelProperty(value = "主键id")
    private Integer id;  //主键id
    @ApiModelProperty(value = "单位工程id")
    private Integer groupid;  //部门id
    @ApiModelProperty(value = "单位工程名称")
    private String groupname; //部门名称
    @ApiModelProperty(value = "工单id")
    private String projectcode; //工点id
    @ApiModelProperty(value = "工单名称")
    private String projectname; //工点名称
    @ApiModelProperty(value = "施工工序")
    private String process; //施工工序
    @ApiModelProperty(value = "设计数量")
    private Integer desginnum; //设计数量
    @ApiModelProperty(value = "完成数量")
    private Integer finishnum; //完成数量
    @ApiModelProperty(value = "1-点, 2-面")
    private Integer type; //1-点, 2-面
    @ApiModelProperty(value = "高程")
    private Integer altitude; //高程
    @ApiModelProperty(value = "经纬度, 用 ',' 逗号分隔")
    private String longlatitude; //经纬度, 用"," 分隔
    @ApiModelProperty(value = "备注")
    private String remark;  //备注

    private Integer departmentid;

    private Integer projectId;

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getAltitude() {
        return altitude;
    }

    public void setAltitude(Integer altitude) {
        this.altitude = altitude;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGroupid() {
        return groupid;
    }

    public void setGroupid(Integer groupid) {
        this.groupid = groupid;
    }

    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }

    public String getProjectcode() {
        return projectcode;
    }

    public void setProjectcode(String projectcode) {
        this.projectcode = projectcode;
    }

    public String getProjectname() {
        return projectname;
    }

    public void setProjectname(String projectname) {
        this.projectname = projectname;
    }

    public String getProcess() {
        return process;
    }

    public void setProcess(String process) {
        this.process = process;
    }

    public Integer getDesginnum() {
        return desginnum;
    }

    public void setDesginnum(Integer desginnum) {
        this.desginnum = desginnum;
    }

    public Integer getFinishnum() {
        return finishnum;
    }

    public void setFinishnum(Integer finishnum) {
        this.finishnum = finishnum;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getLonglatitude() {
        return longlatitude;
    }

    public void setLonglatitude(String longlatitude) {
        this.longlatitude = longlatitude;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getDepartmentid() {
        return departmentid;
    }

    public void setDepartmentid(Integer departmentid) {
        this.departmentid = departmentid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ZjDigitalTwin)) return false;
        ZjDigitalTwin that = (ZjDigitalTwin) o;
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(getGroupid(), that.getGroupid()) &&
                Objects.equals(getGroupname(), that.getGroupname()) &&
                Objects.equals(getProjectcode(), that.getProjectcode()) &&
                Objects.equals(getProjectname(), that.getProjectname()) &&
                Objects.equals(getProcess(), that.getProcess()) &&
                Objects.equals(getDesginnum(), that.getDesginnum()) &&
                Objects.equals(getFinishnum(), that.getFinishnum()) &&
                Objects.equals(getType(), that.getType()) &&
                Objects.equals(getAltitude(), that.getAltitude()) &&
                Objects.equals(getLonglatitude(), that.getLonglatitude()) &&
                Objects.equals(getRemark(), that.getRemark()) &&
                Objects.equals(getDepartmentid(), that.getDepartmentid()) &&
                Objects.equals(getProjectId(), that.getProjectId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getGroupid(), getGroupname(), getProjectcode(), getProjectname(), getProcess(), getDesginnum(), getFinishnum(), getType(), getAltitude(), getLonglatitude(), getRemark(), getDepartmentid(), getProjectId());
    }

    @Override
    public String toString() {
        return "ZjDigitalTwin{" +
                "id=" + id +
                ", groupid=" + groupid +
                ", groupname='" + groupname + '\'' +
                ", projectcode='" + projectcode + '\'' +
                ", projectname='" + projectname + '\'' +
                ", process='" + process + '\'' +
                ", desginnum=" + desginnum +
                ", finishnum=" + finishnum +
                ", type=" + type +
                ", altitude=" + altitude +
                ", longlatitude='" + longlatitude + '\'' +
                ", remark='" + remark + '\'' +
                ", departmentid=" + departmentid +
                ", projectId=" + projectId +
                '}';
    }
}
