package com.ruoyi.web.controller.jianguan.other.zlsk.strestservice.dp.model;

import java.io.*;

public class LadCostDictionary implements Serializable
{
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String ladName;
    private String unitAmount;
    private String unitPricePerUnit;
    private String remark;

    public Integer getId() {
        return this.id;
    }

    public String getLadName() {
        return this.ladName;
    }

    public String getUnitAmount() {
        return this.unitAmount;
    }

    public String getUnitPricePerUnit() {
        return this.unitPricePerUnit;
    }

    public String getRemark() {
        return this.remark;
    }

    public LadCostDictionary setId(final Integer id) {
        this.id = id;
        return this;
    }

    public LadCostDictionary setLadName(final String ladName) {
        this.ladName = ladName;
        return this;
    }

    public LadCostDictionary setUnitAmount(final String unitAmount) {
        this.unitAmount = unitAmount;
        return this;
    }

    public LadCostDictionary setUnitPricePerUnit(final String unitPricePerUnit) {
        this.unitPricePerUnit = unitPricePerUnit;
        return this;
    }

    public LadCostDictionary setRemark(final String remark) {
        this.remark = remark;
        return this;
    }

    @Override
    public String toString() {
        return "LadCostDictionary(id=" + this.getId() + ", ladName=" + this.getLadName() + ", unitAmount=" + this.getUnitAmount() + ", unitPricePerUnit=" + this.getUnitPricePerUnit() + ", remark=" + this.getRemark() + ")";
    }

    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof LadCostDictionary)) {
            return false;
        }
        final LadCostDictionary other = (LadCostDictionary)o;
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
        final Object this$ladName = this.getLadName();
        final Object other$ladName = other.getLadName();
        Label_0102: {
            if (this$ladName == null) {
                if (other$ladName == null) {
                    break Label_0102;
                }
            }
            else if (this$ladName.equals(other$ladName)) {
                break Label_0102;
            }
            return false;
        }
        final Object this$unitAmount = this.getUnitAmount();
        final Object other$unitAmount = other.getUnitAmount();
        Label_0139: {
            if (this$unitAmount == null) {
                if (other$unitAmount == null) {
                    break Label_0139;
                }
            }
            else if (this$unitAmount.equals(other$unitAmount)) {
                break Label_0139;
            }
            return false;
        }
        final Object this$unitPricePerUnit = this.getUnitPricePerUnit();
        final Object other$unitPricePerUnit = other.getUnitPricePerUnit();
        Label_0176: {
            if (this$unitPricePerUnit == null) {
                if (other$unitPricePerUnit == null) {
                    break Label_0176;
                }
            }
            else if (this$unitPricePerUnit.equals(other$unitPricePerUnit)) {
                break Label_0176;
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
        return other instanceof LadCostDictionary;
    }

    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * 59 + (($id == null) ? 43 : $id.hashCode());
        final Object $ladName = this.getLadName();
        result = result * 59 + (($ladName == null) ? 43 : $ladName.hashCode());
        final Object $unitAmount = this.getUnitAmount();
        result = result * 59 + (($unitAmount == null) ? 43 : $unitAmount.hashCode());
        final Object $unitPricePerUnit = this.getUnitPricePerUnit();
        result = result * 59 + (($unitPricePerUnit == null) ? 43 : $unitPricePerUnit.hashCode());
        final Object $remark = this.getRemark();
        result = result * 59 + (($remark == null) ? 43 : $remark.hashCode());
        return result;
    }
}
