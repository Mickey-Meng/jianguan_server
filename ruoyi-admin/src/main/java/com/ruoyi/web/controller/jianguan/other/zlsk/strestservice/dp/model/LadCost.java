package com.ruoyi.web.controller.jianguan.other.zlsk.strestservice.dp.model;

import java.io.*;
import io.swagger.annotations.*;

public class LadCost implements Serializable
{
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "\u4e3b\u952eid", required = true)
    private Integer id;
    @ApiModelProperty("\u540d\u79f0")
    private String ladName;
    @ApiModelProperty(value = "\u5de5\u7a0bid", required = true)
    private Integer engineeringId;
    @ApiModelProperty("\u8d39\u7528\u5217\u9879id\u503c")
    private Integer dictionaryId;
    @ApiModelProperty("\u6570\u91cf")
    private Double amount;
    @ApiModelProperty("\u5355\u4ef7")
    private Double unitPrice;
    @ApiModelProperty("\u8d39\u7528\u603b\u548c(\u4e07\u5143)")
    private Double totalCost;
    @ApiModelProperty("\u5355\u4ef7\u5355\u4f4d")
    private String unitAmount;
    @ApiModelProperty("\u6570\u91cf\u5355\u4f4d")
    private String unitPricePerUnit;

    public Integer getId() {
        return this.id;
    }

    public String getLadName() {
        return this.ladName;
    }

    public Integer getEngineeringId() {
        return this.engineeringId;
    }

    public Integer getDictionaryId() {
        return this.dictionaryId;
    }

    public Double getAmount() {
        return this.amount;
    }

    public Double getUnitPrice() {
        return this.unitPrice;
    }

    public Double getTotalCost() {
        return this.totalCost;
    }

    public String getUnitAmount() {
        return this.unitAmount;
    }

    public String getUnitPricePerUnit() {
        return this.unitPricePerUnit;
    }

    public LadCost setId(final Integer id) {
        this.id = id;
        return this;
    }

    public LadCost setLadName(final String ladName) {
        this.ladName = ladName;
        return this;
    }

    public LadCost setEngineeringId(final Integer engineeringId) {
        this.engineeringId = engineeringId;
        return this;
    }

    public LadCost setDictionaryId(final Integer dictionaryId) {
        this.dictionaryId = dictionaryId;
        return this;
    }

    public LadCost setAmount(final Double amount) {
        this.amount = amount;
        return this;
    }

    public LadCost setUnitPrice(final Double unitPrice) {
        this.unitPrice = unitPrice;
        return this;
    }

    public LadCost setTotalCost(final Double totalCost) {
        this.totalCost = totalCost;
        return this;
    }

    public LadCost setUnitAmount(final String unitAmount) {
        this.unitAmount = unitAmount;
        return this;
    }

    public LadCost setUnitPricePerUnit(final String unitPricePerUnit) {
        this.unitPricePerUnit = unitPricePerUnit;
        return this;
    }

    @Override
    public String toString() {
        return "LadCost(id=" + this.getId() + ", ladName=" + this.getLadName() + ", engineeringId=" + this.getEngineeringId() + ", dictionaryId=" + this.getDictionaryId() + ", amount=" + this.getAmount() + ", unitPrice=" + this.getUnitPrice() + ", totalCost=" + this.getTotalCost() + ", unitAmount=" + this.getUnitAmount() + ", unitPricePerUnit=" + this.getUnitPricePerUnit() + ")";
    }

    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof LadCost)) {
            return false;
        }
        final LadCost other = (LadCost)o;
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
        final Object this$engineeringId = this.getEngineeringId();
        final Object other$engineeringId = other.getEngineeringId();
        Label_0102: {
            if (this$engineeringId == null) {
                if (other$engineeringId == null) {
                    break Label_0102;
                }
            }
            else if (this$engineeringId.equals(other$engineeringId)) {
                break Label_0102;
            }
            return false;
        }
        final Object this$dictionaryId = this.getDictionaryId();
        final Object other$dictionaryId = other.getDictionaryId();
        Label_0139: {
            if (this$dictionaryId == null) {
                if (other$dictionaryId == null) {
                    break Label_0139;
                }
            }
            else if (this$dictionaryId.equals(other$dictionaryId)) {
                break Label_0139;
            }
            return false;
        }
        final Object this$amount = this.getAmount();
        final Object other$amount = other.getAmount();
        Label_0176: {
            if (this$amount == null) {
                if (other$amount == null) {
                    break Label_0176;
                }
            }
            else if (this$amount.equals(other$amount)) {
                break Label_0176;
            }
            return false;
        }
        final Object this$unitPrice = this.getUnitPrice();
        final Object other$unitPrice = other.getUnitPrice();
        Label_0213: {
            if (this$unitPrice == null) {
                if (other$unitPrice == null) {
                    break Label_0213;
                }
            }
            else if (this$unitPrice.equals(other$unitPrice)) {
                break Label_0213;
            }
            return false;
        }
        final Object this$totalCost = this.getTotalCost();
        final Object other$totalCost = other.getTotalCost();
        Label_0250: {
            if (this$totalCost == null) {
                if (other$totalCost == null) {
                    break Label_0250;
                }
            }
            else if (this$totalCost.equals(other$totalCost)) {
                break Label_0250;
            }
            return false;
        }
        final Object this$ladName = this.getLadName();
        final Object other$ladName = other.getLadName();
        Label_0287: {
            if (this$ladName == null) {
                if (other$ladName == null) {
                    break Label_0287;
                }
            }
            else if (this$ladName.equals(other$ladName)) {
                break Label_0287;
            }
            return false;
        }
        final Object this$unitAmount = this.getUnitAmount();
        final Object other$unitAmount = other.getUnitAmount();
        Label_0324: {
            if (this$unitAmount == null) {
                if (other$unitAmount == null) {
                    break Label_0324;
                }
            }
            else if (this$unitAmount.equals(other$unitAmount)) {
                break Label_0324;
            }
            return false;
        }
        final Object this$unitPricePerUnit = this.getUnitPricePerUnit();
        final Object other$unitPricePerUnit = other.getUnitPricePerUnit();
        if (this$unitPricePerUnit == null) {
            if (other$unitPricePerUnit == null) {
                return true;
            }
        }
        else if (this$unitPricePerUnit.equals(other$unitPricePerUnit)) {
            return true;
        }
        return false;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof LadCost;
    }

    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * 59 + (($id == null) ? 43 : $id.hashCode());
        final Object $engineeringId = this.getEngineeringId();
        result = result * 59 + (($engineeringId == null) ? 43 : $engineeringId.hashCode());
        final Object $dictionaryId = this.getDictionaryId();
        result = result * 59 + (($dictionaryId == null) ? 43 : $dictionaryId.hashCode());
        final Object $amount = this.getAmount();
        result = result * 59 + (($amount == null) ? 43 : $amount.hashCode());
        final Object $unitPrice = this.getUnitPrice();
        result = result * 59 + (($unitPrice == null) ? 43 : $unitPrice.hashCode());
        final Object $totalCost = this.getTotalCost();
        result = result * 59 + (($totalCost == null) ? 43 : $totalCost.hashCode());
        final Object $ladName = this.getLadName();
        result = result * 59 + (($ladName == null) ? 43 : $ladName.hashCode());
        final Object $unitAmount = this.getUnitAmount();
        result = result * 59 + (($unitAmount == null) ? 43 : $unitAmount.hashCode());
        final Object $unitPricePerUnit = this.getUnitPricePerUnit();
        result = result * 59 + (($unitPricePerUnit == null) ? 43 : $unitPricePerUnit.hashCode());
        return result;
    }
}
