package com.ruoyi.web.controller.jianguan.zlsk.strestservice.dp.model;

import java.io.*;

public class DpExcelTemplate implements Serializable
{
    private Integer id;
    private String name;
    private Integer type;
    private Integer parentId;
    private String remark;
    private String enumName;

    public Integer getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public Integer getType() {
        return this.type;
    }

    public Integer getParentId() {
        return this.parentId;
    }

    public String getRemark() {
        return this.remark;
    }

    public String getEnumName() {
        return this.enumName;
    }

    public DpExcelTemplate setId(final Integer id) {
        this.id = id;
        return this;
    }

    public DpExcelTemplate setName(final String name) {
        this.name = name;
        return this;
    }

    public DpExcelTemplate setType(final Integer type) {
        this.type = type;
        return this;
    }

    public DpExcelTemplate setParentId(final Integer parentId) {
        this.parentId = parentId;
        return this;
    }

    public DpExcelTemplate setRemark(final String remark) {
        this.remark = remark;
        return this;
    }

    public DpExcelTemplate setEnumName(final String enumName) {
        this.enumName = enumName;
        return this;
    }

    @Override
    public String toString() {
        return "DpExcelTemplate(id=" + this.getId() + ", name=" + this.getName() + ", type=" + this.getType() + ", parentId=" + this.getParentId() + ", remark=" + this.getRemark() + ", enumName=" + this.getEnumName() + ")";
    }

    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof DpExcelTemplate)) {
            return false;
        }
        final DpExcelTemplate other = (DpExcelTemplate)o;
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
        final Object this$type = this.getType();
        final Object other$type = other.getType();
        Label_0102: {
            if (this$type == null) {
                if (other$type == null) {
                    break Label_0102;
                }
            }
            else if (this$type.equals(other$type)) {
                break Label_0102;
            }
            return false;
        }
        final Object this$parentId = this.getParentId();
        final Object other$parentId = other.getParentId();
        Label_0139: {
            if (this$parentId == null) {
                if (other$parentId == null) {
                    break Label_0139;
                }
            }
            else if (this$parentId.equals(other$parentId)) {
                break Label_0139;
            }
            return false;
        }
        final Object this$name = this.getName();
        final Object other$name = other.getName();
        Label_0176: {
            if (this$name == null) {
                if (other$name == null) {
                    break Label_0176;
                }
            }
            else if (this$name.equals(other$name)) {
                break Label_0176;
            }
            return false;
        }
        final Object this$remark = this.getRemark();
        final Object other$remark = other.getRemark();
        Label_0213: {
            if (this$remark == null) {
                if (other$remark == null) {
                    break Label_0213;
                }
            }
            else if (this$remark.equals(other$remark)) {
                break Label_0213;
            }
            return false;
        }
        final Object this$enumName = this.getEnumName();
        final Object other$enumName = other.getEnumName();
        if (this$enumName == null) {
            if (other$enumName == null) {
                return true;
            }
        }
        else if (this$enumName.equals(other$enumName)) {
            return true;
        }
        return false;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof DpExcelTemplate;
    }

    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * 59 + (($id == null) ? 43 : $id.hashCode());
        final Object $type = this.getType();
        result = result * 59 + (($type == null) ? 43 : $type.hashCode());
        final Object $parentId = this.getParentId();
        result = result * 59 + (($parentId == null) ? 43 : $parentId.hashCode());
        final Object $name = this.getName();
        result = result * 59 + (($name == null) ? 43 : $name.hashCode());
        final Object $remark = this.getRemark();
        result = result * 59 + (($remark == null) ? 43 : $remark.hashCode());
        final Object $enumName = this.getEnumName();
        result = result * 59 + (($enumName == null) ? 43 : $enumName.hashCode());
        return result;
    }
}
