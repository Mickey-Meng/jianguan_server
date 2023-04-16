package com.ruoyi.web.controller.jianguan.zlsk.strestservice.dp.model;

import java.io.*;
import io.swagger.annotations.*;
import java.util.*;

public class DpEngineering implements Serializable
{
    private static final long serialVersionUID = 1L;
    @ApiModelProperty("\u4e3b\u952eid")
    private Integer id;
    @ApiModelProperty(value = "\u5de5\u7a0b\u540d\u79f0", required = true)
    private String engineeringName;
    private Date createTime;
    private Date updateTime;
    @ApiModelProperty(value = "\u5750\u6807\u4e32", required = true)
    private String site;
    @ApiModelProperty("\u5907\u6ce8")
    private String remark;

    public Integer getId() {
        return this.id;
    }

    public String getEngineeringName() {
        return this.engineeringName;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public Date getUpdateTime() {
        return this.updateTime;
    }

    public String getSite() {
        return this.site;
    }

    public String getRemark() {
        return this.remark;
    }

    public DpEngineering setId(final Integer id) {
        this.id = id;
        return this;
    }

    public DpEngineering setEngineeringName(final String engineeringName) {
        this.engineeringName = engineeringName;
        return this;
    }

    public DpEngineering setCreateTime(final Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public DpEngineering setUpdateTime(final Date updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public DpEngineering setSite(final String site) {
        this.site = site;
        return this;
    }

    public DpEngineering setRemark(final String remark) {
        this.remark = remark;
        return this;
    }

    @Override
    public String toString() {
        return "DpEngineering(id=" + this.getId() + ", engineeringName=" + this.getEngineeringName() + ", createTime=" + this.getCreateTime() + ", updateTime=" + this.getUpdateTime() + ", site=" + this.getSite() + ", remark=" + this.getRemark() + ")";
    }

    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof DpEngineering)) {
            return false;
        }
        final DpEngineering other = (DpEngineering)o;
        if (!other.canEqual(this)) {
            return false;
        }
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        Label_0065: {
            if (this$id == null) {
                if (other$id == null) {
                    break Label_0065;
                }
            }
            else if (this$id.equals(other$id)) {
                break Label_0065;
            }
            return false;
        }
        final Object this$engineeringName = this.getEngineeringName();
        final Object other$engineeringName = other.getEngineeringName();
        Label_0102: {
            if (this$engineeringName == null) {
                if (other$engineeringName == null) {
                    break Label_0102;
                }
            }
            else if (this$engineeringName.equals(other$engineeringName)) {
                break Label_0102;
            }
            return false;
        }
        final Object this$createTime = this.getCreateTime();
        final Object other$createTime = other.getCreateTime();
        Label_0139: {
            if (this$createTime == null) {
                if (other$createTime == null) {
                    break Label_0139;
                }
            }
            else if (this$createTime.equals(other$createTime)) {
                break Label_0139;
            }
            return false;
        }
        final Object this$updateTime = this.getUpdateTime();
        final Object other$updateTime = other.getUpdateTime();
        Label_0176: {
            if (this$updateTime == null) {
                if (other$updateTime == null) {
                    break Label_0176;
                }
            }
            else if (this$updateTime.equals(other$updateTime)) {
                break Label_0176;
            }
            return false;
        }
        final Object this$site = this.getSite();
        final Object other$site = other.getSite();
        Label_0213: {
            if (this$site == null) {
                if (other$site == null) {
                    break Label_0213;
                }
            }
            else if (this$site.equals(other$site)) {
                break Label_0213;
            }
            return false;
        }
        final Object this$remark = this.getRemark();
        final Object other$remark = other.getRemark();
        if (this$remark == null) {
            if (other$remark == null) {
                return true;
            }
        }
        else if (this$remark.equals(other$remark)) {
            return true;
        }
        return false;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof DpEngineering;
    }

    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * 59 + (($id == null) ? 43 : $id.hashCode());
        final Object $engineeringName = this.getEngineeringName();
        result = result * 59 + (($engineeringName == null) ? 43 : $engineeringName.hashCode());
        final Object $createTime = this.getCreateTime();
        result = result * 59 + (($createTime == null) ? 43 : $createTime.hashCode());
        final Object $updateTime = this.getUpdateTime();
        result = result * 59 + (($updateTime == null) ? 43 : $updateTime.hashCode());
        final Object $site = this.getSite();
        result = result * 59 + (($site == null) ? 43 : $site.hashCode());
        final Object $remark = this.getRemark();
        result = result * 59 + (($remark == null) ? 43 : $remark.hashCode());
        return result;
    }
}
