package com.ruoyi.web.controller.jianguan.other.zlsk.strestservice.dp.model;

import java.io.*;
import io.swagger.annotations.*;

public class BaseEntity implements Serializable
{
    @ApiModelProperty(value = "\u5de5\u7a0bid", required = true)
    private Integer engineeringId;
    @ApiModelProperty("\u4e3b\u952eid \u4fee\u6539\u65f6\u4f20\u503c")
    private Integer id;
    @ApiModelProperty("\u9879\u76ee\u540d\u79f0")
    private String projectName;

    public Integer getEngineeringId() {
        return this.engineeringId;
    }

    public Integer getId() {
        return this.id;
    }

    public String getProjectName() {
        return this.projectName;
    }

    public BaseEntity setEngineeringId(final Integer engineeringId) {
        this.engineeringId = engineeringId;
        return this;
    }

    public BaseEntity setId(final Integer id) {
        this.id = id;
        return this;
    }

    public BaseEntity setProjectName(final String projectName) {
        this.projectName = projectName;
        return this;
    }

    @Override
    public String toString() {
        return "BaseEntity(engineeringId=" + this.getEngineeringId() + ", id=" + this.getId() + ", projectName=" + this.getProjectName() + ")";
    }

    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof BaseEntity)) {
            return false;
        }
        final BaseEntity other = (BaseEntity)o;
        if (!other.canEqual(this)) {
            return false;
        }
        final Object this$engineeringId = this.getEngineeringId();
        final Object other$engineeringId = other.getEngineeringId();
        Label_0065: {
            if (this$engineeringId == null) {
                if (other$engineeringId == null) {
                    break Label_0065;
                }
            }
            else if (this$engineeringId.equals(other$engineeringId)) {
                break Label_0065;
            }
            return false;
        }
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        Label_0102: {
            if (this$id == null) {
                if (other$id == null) {
                    break Label_0102;
                }
            }
            else if (this$id.equals(other$id)) {
                break Label_0102;
            }
            return false;
        }
        final Object this$projectName = this.getProjectName();
        final Object other$projectName = other.getProjectName();
        if (this$projectName == null) {
            if (other$projectName == null) {
                return true;
            }
        }
        else if (this$projectName.equals(other$projectName)) {
            return true;
        }
        return false;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof BaseEntity;
    }

    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $engineeringId = this.getEngineeringId();
        result = result * 59 + (($engineeringId == null) ? 43 : $engineeringId.hashCode());
        final Object $id = this.getId();
        result = result * 59 + (($id == null) ? 43 : $id.hashCode());
        final Object $projectName = this.getProjectName();
        result = result * 59 + (($projectName == null) ? 43 : $projectName.hashCode());
        return result;
    }
}
