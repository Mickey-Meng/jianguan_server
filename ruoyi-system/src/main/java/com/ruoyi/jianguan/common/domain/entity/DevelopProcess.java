package com.ruoyi.jianguan.common.domain.entity;//package com.ruoyi.czjg.zjrw.domain.entity;
//
//import io.swagger.annotations.ApiModelProperty;
//
//import java.io.Serializable;
//import java.util.Objects;
//
///**
// * @Author : Chen_ZhiWei
// * @Date : Create file in 2022/5/23 09:26
// * @Version : 1.0
// * @Description : 制定流程
// **/
//public class DevelopProcess implements Serializable {
//
//    @ApiModelProperty(value = "流程定义的key; 可随机填写, 例: renyuanbaoshen1")
//    private String processKey;
//
//    @ApiModelProperty(value = "保存数据的id, 如发起人员报审的这条数据的id")
//    private Integer personId;
//
//    @ApiModelProperty(value = "下一级处理人username", required = false)
//    private String variables;
//
//    public String getProcessKey() {
//        return processKey;
//    }
//
//    public void setProcessKey(String processKey) {
//        this.processKey = processKey;
//    }
//
//    public Integer getPersonId() {
//        return personId;
//    }
//
//    public void setPersonId(Integer personId) {
//        this.personId = personId;
//    }
//
//    public String getVariables() {
//        return variables;
//    }
//
//    public void setVariables(String variables) {
//        this.variables = variables;
//    }
//
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof DevelopProcess)) return false;
//        DevelopProcess that = (DevelopProcess) o;
//        return Objects.equals(getProcessKey(), that.getProcessKey()) &&
//                Objects.equals(getPersonId(), that.getPersonId()) &&
//                Objects.equals(getVariables(), that.getVariables());
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(getProcessKey(), getPersonId(), getVariables());
//    }
//
//    @Override
//    public String toString() {
//        return "DevelopProcess{" +
//                "processKey='" + processKey + '\'' +
//                ", personId=" + personId +
//                ", variables='" + variables + '\'' +
//                '}';
//    }
//}
