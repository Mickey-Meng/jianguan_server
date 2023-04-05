package com.ruoyi.jianguan.common.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @author
 *
 */
public class Recode implements Serializable {
    private Integer id;

    private Integer fillinid;

    /**
     * 监理意见
     */
    private String remark;

    private Integer checkid;

    /**
     * 验收报告
     */
    private String accrecodeurl;

    /**
     * 检测报告
     */
    private String testurl;

    /**
     * 关键数据
     */
    private String keydata;

    /**
     * 旁站记录
     */
    private String standbyrecode;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createtime;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFillinid() {
        return fillinid;
    }

    public void setFillinid(Integer fillinid) {
        this.fillinid = fillinid;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getCheckid() {
        return checkid;
    }

    public void setCheckid(Integer checkid) {
        this.checkid = checkid;
    }

    public String getAccrecodeurl() {
        return accrecodeurl;
    }

    public void setAccrecodeurl(String accrecodeurl) {
        this.accrecodeurl = accrecodeurl;
    }

    public String getTesturl() {
        return testurl;
    }

    public void setTesturl(String testurl) {
        this.testurl = testurl;
    }

    public String getKeydata() {
        return keydata;
    }

    public void setKeydata(String keydata) {
        this.keydata = keydata;
    }

    public String getStandbyrecode() {
        return standbyrecode;
    }

    public void setStandbyrecode(String standbyrecode) {
        this.standbyrecode = standbyrecode;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
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
        Recode other = (Recode) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getFillinid() == null ? other.getFillinid() == null : this.getFillinid().equals(other.getFillinid()))
                && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()))
                && (this.getCheckid() == null ? other.getCheckid() == null : this.getCheckid().equals(other.getCheckid()))
                && (this.getAccrecodeurl() == null ? other.getAccrecodeurl() == null : this.getAccrecodeurl().equals(other.getAccrecodeurl()))
                && (this.getTesturl() == null ? other.getTesturl() == null : this.getTesturl().equals(other.getTesturl()))
                && (this.getKeydata() == null ? other.getKeydata() == null : this.getKeydata().equals(other.getKeydata()))
                && (this.getStandbyrecode() == null ? other.getStandbyrecode() == null : this.getStandbyrecode().equals(other.getStandbyrecode()))
                && (this.getCreatetime() == null ? other.getCreatetime() == null : this.getCreatetime().equals(other.getCreatetime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getFillinid() == null) ? 0 : getFillinid().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        result = prime * result + ((getCheckid() == null) ? 0 : getCheckid().hashCode());
        result = prime * result + ((getAccrecodeurl() == null) ? 0 : getAccrecodeurl().hashCode());
        result = prime * result + ((getTesturl() == null) ? 0 : getTesturl().hashCode());
        result = prime * result + ((getKeydata() == null) ? 0 : getKeydata().hashCode());
        result = prime * result + ((getStandbyrecode() == null) ? 0 : getStandbyrecode().hashCode());
        result = prime * result + ((getCreatetime() == null) ? 0 : getCreatetime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", fillinid=").append(fillinid);
        sb.append(", remark=").append(remark);
        sb.append(", checkid=").append(checkid);
        sb.append(", accrecodeurl=").append(accrecodeurl);
        sb.append(", testurl=").append(testurl);
        sb.append(", keydata=").append(keydata);
        sb.append(", standbyrecode=").append(standbyrecode);
        sb.append(", createtime=").append(createtime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
