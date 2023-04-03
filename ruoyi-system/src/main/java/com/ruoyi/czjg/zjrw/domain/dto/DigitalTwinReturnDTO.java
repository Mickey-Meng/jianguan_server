package com.ruoyi.czjg.zjrw.domain.dto;

/**
 * @Author : chenzhiwei
 * @Date : Create file in 2022/3/18 19:32
 * @Version : 1.0
 * @Description :
 **/
public class DigitalTwinReturnDTO {

    private Integer id;
    private Integer groupid;
    private String projectCode;  //工点code
    private String projectName;  //工点名称
    private Integer sum;        //总计
    private Integer finish;     //完成数量
    private Integer work;       //正在施工数量

    private Integer type;       //类型,1-点,2-面
    private Integer altitude;   //高程
    private String longlatitude;    //经纬度(放在一个字符串里面)

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Integer getSum() {
        return sum;
    }

    public void setSum(Integer sum) {
        this.sum = sum;
    }

    public Integer getFinish() {
        return finish;
    }

    public void setFinish(Integer finish) {
        this.finish = finish;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getAltitude() {
        return altitude;
    }

    public void setAltitude(Integer altitude) {
        this.altitude = altitude;
    }

    public String getLonglatitude() {
        return longlatitude;
    }

    public void setLonglatitude(String longlatitude) {
        this.longlatitude = longlatitude;
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

    public Integer getWork() {
        return work;
    }

    public void setWork(Integer work) {
        this.work = work;
    }

    @Override
    public String toString() {
        return "DigitalTwinReturnDTO{" +
                "id=" + id +
                ", groupid=" + groupid +
                ", projectCode='" + projectCode + '\'' +
                ", projectName='" + projectName + '\'' +
                ", sum=" + sum +
                ", finish=" + finish +
                ", work=" + work +
                ", type=" + type +
                ", altitude=" + altitude +
                ", longlatitude='" + longlatitude + '\'' +
                '}';
    }
}
