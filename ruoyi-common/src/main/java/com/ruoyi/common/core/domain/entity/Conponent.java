package com.ruoyi.common.core.domain.entity;

import java.io.Serializable;
import java.util.List;

/**
 * @author
 *
 */
public class Conponent implements Serializable {
    /**
     * 构件id
     */
    private Integer id;

    /**
     * 模型id
     */
    private String mouldid;

    /**
     * 构件类型
     */
    private String conponenttype;

    /**
     * 项目编号
     */
    private String projectcode;

    /**
     * 层级1
     */
    private String w1;

    /**
     * 层级1code
     */
    private String w1code;

    private String w2;

    private String w2code;

    private String w3;

    private String w3code;

    private String w4;

    private String w4code;

    private String w5;

    private String w5code;

    private String w6;

    private String w6code;

    private String w7;

    private String w7code;

    private String w8;

    private String w8code;

    private String w9;

    private String w9code;

    private String w10;

    private String w10code;

    /**
     * 工序id
     */
    private Integer produceid;

    private Integer zhuangnumber;

    private Integer dunnumber;

    private String mark;

    /**
     * 构件编码
     */
    private String conponentcode;

    private Integer conponentstatus;

    private String belongw6;

    private Integer issafe;

    /**
     * 是否删除
     */
    private Integer isvaild;

    /**
     * 父级code
     */
    private String pcode;

    /**
     * 父级name
     */
    private String pname;

    /**
     * 名字
     */
    private String name;

    /**
     * 本级code
     */
    private String code;

    /**
     * 父级的id
     */
    private Integer pid;

    /**
     * 项目类型
     */
    private String projecttype;

    /**
     * 构件类型名字
     */
    private String conponenttypename;

    /**
     * 服务名称，用以展示模型
     */
    private String layername;

    /**
     * WBS编码，后面对应省平台时的编码
     */
    private String wbscode;

    /**
     * 构件级别，用于进度预警
     */
    private String conponentlevel;

    public String getConponentlevel() {
        return conponentlevel;
    }

    public void setConponentlevel(String conponentlevel) {
        this.conponentlevel = conponentlevel;
    }

    /**
     * 老BIM编码
     */
    private String oldconponentcode;
    /**
     * 部门id, 关联ss_f_groups的项目(grouplevel)    2022-05-18,替换成ss_f_projects
     */
    private Integer groupid;

    public Integer getGroupid() {
        return groupid;
    }

    public void setGroupid(Integer groupid) {
        this.groupid = groupid;
    }

    public String getOldconponentcode() {
        return oldconponentcode;
    }

    public void setOldconponentcode(String oldconponentcode) {
        this.oldconponentcode = oldconponentcode;
    }

    public String getWbscode() {
        return wbscode;
    }

    public void setWbscode(String wbscode) {
        this.wbscode = wbscode;
    }

    public String getLayername() {
        return layername;
    }

    public void setLayername(String layername) {
        this.layername = layername;
    }

    private List<Conponent> child;

    public List<Conponent> getChild() {
        return child;
    }

    public void setChild(List<Conponent> child) {
        this.child = child;
    }

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMouldid() {
        return mouldid;
    }

    public void setMouldid(String mouldid) {
        this.mouldid = mouldid;
    }

    public String getConponenttype() {
        return conponenttype;
    }

    public void setConponenttype(String conponenttype) {
        this.conponenttype = conponenttype;
    }

    public String getProjectcode() {
        return projectcode;
    }

    public void setProjectcode(String projectcode) {
        this.projectcode = projectcode;
    }

    public String getW1() {
        return w1;
    }

    public void setW1(String w1) {
        this.w1 = w1;
    }

    public String getW1code() {
        return w1code;
    }

    public void setW1code(String w1code) {
        this.w1code = w1code;
    }

    public String getW2() {
        return w2;
    }

    public void setW2(String w2) {
        this.w2 = w2;
    }

    public String getW2code() {
        return w2code;
    }

    public void setW2code(String w2code) {
        this.w2code = w2code;
    }

    public String getW3() {
        return w3;
    }

    public void setW3(String w3) {
        this.w3 = w3;
    }

    public String getW3code() {
        return w3code;
    }

    public void setW3code(String w3code) {
        this.w3code = w3code;
    }

    public String getW4() {
        return w4;
    }

    public void setW4(String w4) {
        this.w4 = w4;
    }

    public String getW4code() {
        return w4code;
    }

    public String getW4CodeAndW4Name(){
        return w4code+"_"+w4;
    }

    public String getW5CodeAndW5Name(){
        return w5code+"_"+w5;
    }

    public String getW6CodeAndW6Name(){
        return w6code+"_"+w6;
    }

    public String getW7CodeAndW7Name(){
        return w7code+"_"+w7;
    }

    public void setW4code(String w4code) {
        this.w4code = w4code;
    }

    public String getW5() {
        return w5;
    }

    public void setW5(String w5) {
        this.w5 = w5;
    }

    public String getW5code() {
        return w5code;
    }

    public void setW5code(String w5code) {
        this.w5code = w5code;
    }

    public String getW6() {
        return w6;
    }

    public void setW6(String w6) {
        this.w6 = w6;
    }

    public String getW6code() {
        return w6code;
    }

    public void setW6code(String w6code) {
        this.w6code = w6code;
    }

    public String getW7() {
        return w7;
    }

    public void setW7(String w7) {
        this.w7 = w7;
    }

    public String getW7code() {
        return w7code;
    }

    public void setW7code(String w7code) {
        this.w7code = w7code;
    }

    public String getW8() {
        return w8;
    }

    public void setW8(String w8) {
        this.w8 = w8;
    }

    public String getW8code() {
        return w8code;
    }

    public void setW8code(String w8code) {
        this.w8code = w8code;
    }

    public String getW9() {
        return w9;
    }

    public void setW9(String w9) {
        this.w9 = w9;
    }

    public String getW9code() {
        return w9code;
    }

    public void setW9code(String w9code) {
        this.w9code = w9code;
    }

    public String getW10() {
        return w10;
    }

    public void setW10(String w10) {
        this.w10 = w10;
    }

    public String getW10code() {
        return w10code;
    }

    public void setW10code(String w10code) {
        this.w10code = w10code;
    }

    public Integer getProduceid() {
        return produceid;
    }

    public void setProduceid(Integer produceid) {
        this.produceid = produceid;
    }

    public Integer getZhuangnumber() {
        return zhuangnumber;
    }

    public void setZhuangnumber(Integer zhuangnumber) {
        this.zhuangnumber = zhuangnumber;
    }

    public Integer getDunnumber() {
        return dunnumber;
    }

    public void setDunnumber(Integer dunnumber) {
        this.dunnumber = dunnumber;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getConponentcode() {
        return conponentcode;
    }

    public void setConponentcode(String conponentcode) {
        this.conponentcode = conponentcode;
    }

    public Integer getConponentstatus() {
        return conponentstatus;
    }

    public void setConponentstatus(Integer conponentstatus) {
        this.conponentstatus = conponentstatus;
    }

    public String getBelongw6() {
        return belongw6;
    }

    public void setBelongw6(String belongw6) {
        this.belongw6 = belongw6;
    }

    public Integer getIssafe() {
        return issafe;
    }

    public void setIssafe(Integer issafe) {
        this.issafe = issafe;
    }

    public Integer getIsvaild() {
        return isvaild;
    }

    public void setIsvaild(Integer isvaild) {
        this.isvaild = isvaild;
    }

    public String getPcode() {
        return pcode;
    }

    public void setPcode(String pcode) {
        this.pcode = pcode;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getPid() {
        return pid;
    }

    public String groupType(){
        return this.conponenttypename+"_"+this.conponenttype;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getProjecttype() {
        return projecttype;
    }

    public void setProjecttype(String projecttype) {
        this.projecttype = projecttype;
    }

    public String getConponenttypename() {
        return conponenttypename;
    }

    public void setConponenttypename(String conponenttypename) {
        this.conponenttypename = conponenttypename;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Conponent other = (Conponent) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getMouldid() == null ? other.getMouldid() == null : this.getMouldid().equals(other.getMouldid()))
                && (this.getConponenttype() == null ? other.getConponenttype() == null : this.getConponenttype().equals(other.getConponenttype()))
                && (this.getProjectcode() == null ? other.getProjectcode() == null : this.getProjectcode().equals(other.getProjectcode()))
                && (this.getW1() == null ? other.getW1() == null : this.getW1().equals(other.getW1()))
                && (this.getW1code() == null ? other.getW1code() == null : this.getW1code().equals(other.getW1code()))
                && (this.getW2() == null ? other.getW2() == null : this.getW2().equals(other.getW2()))
                && (this.getW2code() == null ? other.getW2code() == null : this.getW2code().equals(other.getW2code()))
                && (this.getW3() == null ? other.getW3() == null : this.getW3().equals(other.getW3()))
                && (this.getW3code() == null ? other.getW3code() == null : this.getW3code().equals(other.getW3code()))
                && (this.getW4() == null ? other.getW4() == null : this.getW4().equals(other.getW4()))
                && (this.getW4code() == null ? other.getW4code() == null : this.getW4code().equals(other.getW4code()))
                && (this.getW5() == null ? other.getW5() == null : this.getW5().equals(other.getW5()))
                && (this.getW5code() == null ? other.getW5code() == null : this.getW5code().equals(other.getW5code()))
                && (this.getW6() == null ? other.getW6() == null : this.getW6().equals(other.getW6()))
                && (this.getW6code() == null ? other.getW6code() == null : this.getW6code().equals(other.getW6code()))
                && (this.getW7() == null ? other.getW7() == null : this.getW7().equals(other.getW7()))
                && (this.getW7code() == null ? other.getW7code() == null : this.getW7code().equals(other.getW7code()))
                && (this.getW8() == null ? other.getW8() == null : this.getW8().equals(other.getW8()))
                && (this.getW8code() == null ? other.getW8code() == null : this.getW8code().equals(other.getW8code()))
                && (this.getW9() == null ? other.getW9() == null : this.getW9().equals(other.getW9()))
                && (this.getW9code() == null ? other.getW9code() == null : this.getW9code().equals(other.getW9code()))
                && (this.getW10() == null ? other.getW10() == null : this.getW10().equals(other.getW10()))
                && (this.getW10code() == null ? other.getW10code() == null : this.getW10code().equals(other.getW10code()))
                && (this.getProduceid() == null ? other.getProduceid() == null : this.getProduceid().equals(other.getProduceid()))
                && (this.getZhuangnumber() == null ? other.getZhuangnumber() == null : this.getZhuangnumber().equals(other.getZhuangnumber()))
                && (this.getDunnumber() == null ? other.getDunnumber() == null : this.getDunnumber().equals(other.getDunnumber()))
                && (this.getMark() == null ? other.getMark() == null : this.getMark().equals(other.getMark()))
                && (this.getConponentcode() == null ? other.getConponentcode() == null : this.getConponentcode().equals(other.getConponentcode()))
                && (this.getConponentstatus() == null ? other.getConponentstatus() == null : this.getConponentstatus().equals(other.getConponentstatus()))
                && (this.getBelongw6() == null ? other.getBelongw6() == null : this.getBelongw6().equals(other.getBelongw6()))
                && (this.getIssafe() == null ? other.getIssafe() == null : this.getIssafe().equals(other.getIssafe()))
                && (this.getIsvaild() == null ? other.getIsvaild() == null : this.getIsvaild().equals(other.getIsvaild()))
                && (this.getPcode() == null ? other.getPcode() == null : this.getPcode().equals(other.getPcode()))
                && (this.getPname() == null ? other.getPname() == null : this.getPname().equals(other.getPname()))
                && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
                && (this.getCode() == null ? other.getCode() == null : this.getCode().equals(other.getCode()))
                && (this.getPid() == null ? other.getPid() == null : this.getPid().equals(other.getPid()))
                && (this.getProjecttype() == null ? other.getProjecttype() == null : this.getProjecttype().equals(other.getProjecttype()))
                && (this.getConponenttypename() == null ? other.getConponenttypename() == null : this.getConponenttypename().equals(other.getConponenttypename()))
                && (this.getLayername() == null ? other.getLayername() == null : this.getLayername().equals(other.getLayername()))
                && (this.getOldconponentcode() == null ? other.getOldconponentcode() == null : this.getOldconponentcode().equals(other.getOldconponentcode()))
                && (this.getWbscode() == null ? other.getWbscode() == null : this.getWbscode().equals(other.getWbscode()))
                && (this.getGroupid() == null ? other.getGroupid() == null : this.getGroupid().equals(other.getGroupid()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getMouldid() == null) ? 0 : getMouldid().hashCode());
        result = prime * result + ((getConponenttype() == null) ? 0 : getConponenttype().hashCode());
        result = prime * result + ((getProjectcode() == null) ? 0 : getProjectcode().hashCode());
        result = prime * result + ((getW1() == null) ? 0 : getW1().hashCode());
        result = prime * result + ((getW1code() == null) ? 0 : getW1code().hashCode());
        result = prime * result + ((getW2() == null) ? 0 : getW2().hashCode());
        result = prime * result + ((getW2code() == null) ? 0 : getW2code().hashCode());
        result = prime * result + ((getW3() == null) ? 0 : getW3().hashCode());
        result = prime * result + ((getW3code() == null) ? 0 : getW3code().hashCode());
        result = prime * result + ((getW4() == null) ? 0 : getW4().hashCode());
        result = prime * result + ((getW4code() == null) ? 0 : getW4code().hashCode());
        result = prime * result + ((getW5() == null) ? 0 : getW5().hashCode());
        result = prime * result + ((getW5code() == null) ? 0 : getW5code().hashCode());
        result = prime * result + ((getW6() == null) ? 0 : getW6().hashCode());
        result = prime * result + ((getW6code() == null) ? 0 : getW6code().hashCode());
        result = prime * result + ((getW7() == null) ? 0 : getW7().hashCode());
        result = prime * result + ((getW7code() == null) ? 0 : getW7code().hashCode());
        result = prime * result + ((getW8() == null) ? 0 : getW8().hashCode());
        result = prime * result + ((getW8code() == null) ? 0 : getW8code().hashCode());
        result = prime * result + ((getW9() == null) ? 0 : getW9().hashCode());
        result = prime * result + ((getW9code() == null) ? 0 : getW9code().hashCode());
        result = prime * result + ((getW10() == null) ? 0 : getW10().hashCode());
        result = prime * result + ((getW10code() == null) ? 0 : getW10code().hashCode());
        result = prime * result + ((getProduceid() == null) ? 0 : getProduceid().hashCode());
        result = prime * result + ((getZhuangnumber() == null) ? 0 : getZhuangnumber().hashCode());
        result = prime * result + ((getDunnumber() == null) ? 0 : getDunnumber().hashCode());
        result = prime * result + ((getMark() == null) ? 0 : getMark().hashCode());
        result = prime * result + ((getConponentcode() == null) ? 0 : getConponentcode().hashCode());
        result = prime * result + ((getConponentstatus() == null) ? 0 : getConponentstatus().hashCode());
        result = prime * result + ((getBelongw6() == null) ? 0 : getBelongw6().hashCode());
        result = prime * result + ((getIssafe() == null) ? 0 : getIssafe().hashCode());
        result = prime * result + ((getIsvaild() == null) ? 0 : getIsvaild().hashCode());
        result = prime * result + ((getPcode() == null) ? 0 : getPcode().hashCode());
        result = prime * result + ((getPname() == null) ? 0 : getPname().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getCode() == null) ? 0 : getCode().hashCode());
        result = prime * result + ((getPid() == null) ? 0 : getPid().hashCode());
        result = prime * result + ((getProjecttype() == null) ? 0 : getProjecttype().hashCode());
        result = prime * result + ((getConponenttypename() == null) ? 0 : getConponenttypename().hashCode());
        result = prime * result + ((getLayername() == null) ? 0 : getLayername().hashCode());
        result = prime * result + ((getOldconponentcode() == null) ? 0 : getOldconponentcode().hashCode());
        result = prime * result + ((getWbscode() == null) ? 0 : getWbscode().hashCode());
        result = prime * result + ((getGroupid() == null) ? 0 : getGroupid().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", mouldid=").append(mouldid);
        sb.append(", conponenttype=").append(conponenttype);
        sb.append(", projectcode=").append(projectcode);
        sb.append(", w1=").append(w1);
        sb.append(", w1code=").append(w1code);
        sb.append(", w2=").append(w2);
        sb.append(", w2code=").append(w2code);
        sb.append(", w3=").append(w3);
        sb.append(", w3code=").append(w3code);
        sb.append(", w4=").append(w4);
        sb.append(", w4code=").append(w4code);
        sb.append(", w5=").append(w5);
        sb.append(", w5code=").append(w5code);
        sb.append(", w6=").append(w6);
        sb.append(", w6code=").append(w6code);
        sb.append(", w7=").append(w7);
        sb.append(", w7code=").append(w7code);
        sb.append(", w8=").append(w8);
        sb.append(", w8code=").append(w8code);
        sb.append(", w9=").append(w9);
        sb.append(", w9code=").append(w9code);
        sb.append(", w10=").append(w10);
        sb.append(", w10code=").append(w10code);
        sb.append(", produceid=").append(produceid);
        sb.append(", zhuangnumber=").append(zhuangnumber);
        sb.append(", dunnumber=").append(dunnumber);
        sb.append(", mark=").append(mark);
        sb.append(", conponentcode=").append(conponentcode);
        sb.append(", conponentstatus=").append(conponentstatus);
        sb.append(", belongw6=").append(belongw6);
        sb.append(", issafe=").append(issafe);
        sb.append(", isvaild=").append(isvaild);
        sb.append(", pcode=").append(pcode);
        sb.append(", pname=").append(pname);
        sb.append(", name=").append(name);
        sb.append(", code=").append(code);
        sb.append(", pid=").append(pid);
        sb.append(", projecttype=").append(projecttype);
        sb.append(", conponenttypename=").append(conponenttypename);
        sb.append(", layername=").append(layername);
        sb.append(", oldconponentcode=").append(oldconponentcode);
        sb.append(", wbscode=").append(wbscode);
        sb.append(", groupid=").append(groupid);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
