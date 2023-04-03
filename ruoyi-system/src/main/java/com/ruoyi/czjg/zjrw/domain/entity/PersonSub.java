package com.ruoyi.czjg.zjrw.domain.entity;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Objects;

/**
 * @Author : Chen_ZhiWei
 * @Date : Create file in 2022/5/10 09:29
 * @Version : 1.0
 * @Description :
 **/
public class PersonSub implements Serializable {

    /**
     * id
     */
    @ApiModelProperty(value = "id")
    private Integer id;
    /**
     * person的上级id(zj_person)
     */
    @ApiModelProperty(value = "person的上级id(zj_person,合同id)")
    private Integer gid;
    /**
     * 用户id
     */
    @ApiModelProperty(value = "用户id")
    private Integer userId;
    /**
     * 姓名
     */
    @ApiModelProperty(value = "姓名")
    private String name;
    /**
     * 身份证
     */
    @ApiModelProperty(value = "身份证")
    private String identityId;
    /**
     * 身份证有效时间:   "2022-01-01 00:00:00至2022-02-01 00:00:00"
     */
    @ApiModelProperty(value = "身份证有效时间")
    private String identityTime;
    /**
     * 性别: 0-女, 1-男
     */
    @ApiModelProperty(value = "性别")
    private Integer gender;
    /**
     * 角色(岗位)id
     */
    @ApiModelProperty(value = "角色(岗位)id")
    private Integer roleId;
    /**
     * 岗位
     */
    @ApiModelProperty(value = "岗位")
    private String post;
    /**
     * 证件名称
     */
    @ApiModelProperty(value = "证件名称")
    private String certificateName;
    /**
     * 证件编码
     */
    @ApiModelProperty(value = "证件编码")
    private String certificateCode;
    /**
     * 发证单位
     */
    @ApiModelProperty(value = "发证单位")
    private String issuer;
    /**
     * 有效时间
     */
    @ApiModelProperty(value = "有效时间")
    private String effectiveTime;
    /**
     * 文化程度
     */
    @ApiModelProperty(value = "文化程度")
    private String education;
    /**
     * 人员照片
     */
    @ApiModelProperty(value = "人员照片")
    private String peoplePic;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGid() {
        return gid;
    }

    public void setGid(Integer gid) {
        this.gid = gid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getCertificateName() {
        return certificateName;
    }

    public void setCertificateName(String certificateName) {
        this.certificateName = certificateName;
    }

    public String getCertificateCode() {
        return certificateCode;
    }

    public void setCertificateCode(String certificateCode) {
        this.certificateCode = certificateCode;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public String getEffectiveTime() {
        return effectiveTime;
    }

    public void setEffectiveTime(String effectiveTime) {
        this.effectiveTime = effectiveTime;
    }

    public String getIdentityId() {
        return identityId;
    }

    public void setIdentityId(String identityId) {
        this.identityId = identityId;
    }

    public String getIdentityTime() {
        return identityTime;
    }

    public void setIdentityTime(String identityTime) {
        this.identityTime = identityTime;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getPeoplePic() {
        return peoplePic;
    }

    public void setPeoplePic(String peoplePic) {
        this.peoplePic = peoplePic;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PersonSub)) return false;
        PersonSub personSub = (PersonSub) o;
        return Objects.equals(getId(), personSub.getId()) &&
                Objects.equals(getGid(), personSub.getGid()) &&
                Objects.equals(getUserId(), personSub.getUserId()) &&
                Objects.equals(getName(), personSub.getName()) &&
                Objects.equals(getIdentityId(), personSub.getIdentityId()) &&
                Objects.equals(getIdentityTime(), personSub.getIdentityTime()) &&
                Objects.equals(getGender(), personSub.getGender()) &&
                Objects.equals(getRoleId(), personSub.getRoleId()) &&
                Objects.equals(getPost(), personSub.getPost()) &&
                Objects.equals(getCertificateName(), personSub.getCertificateName()) &&
                Objects.equals(getCertificateCode(), personSub.getCertificateCode()) &&
                Objects.equals(getIssuer(), personSub.getIssuer()) &&
                Objects.equals(getEffectiveTime(), personSub.getEffectiveTime()) &&
                Objects.equals(getEducation(), personSub.getEducation()) &&
                Objects.equals(getPeoplePic(), personSub.getPeoplePic());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getGid(), getUserId(), getName(), getIdentityId(), getIdentityTime(), getGender(), getRoleId(), getPost(), getCertificateName(), getCertificateCode(), getIssuer(), getEffectiveTime(), getEducation(), getPeoplePic());
    }

    @Override
    public String toString() {
        return "PersonSub{" +
                "id=" + id +
                ", gid=" + gid +
                ", userId=" + userId +
                ", name='" + name + '\'' +
                ", identityId='" + identityId + '\'' +
                ", identityTime='" + identityTime + '\'' +
                ", gender=" + gender +
                ", roleId=" + roleId +
                ", post='" + post + '\'' +
                ", certificateName='" + certificateName + '\'' +
                ", certificateCode='" + certificateCode + '\'' +
                ", issuer='" + issuer + '\'' +
                ", effectiveTime='" + effectiveTime + '\'' +
                ", education='" + education + '\'' +
                ", peoplePic='" + peoplePic + '\'' +
                '}';
    }
}
