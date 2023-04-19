package com.ruoyi.web.controller.jianguan.other.zlsk.strestservice.dp.model.vo;

import java.io.*;
import io.swagger.annotations.*;

public class LadCostVO implements Serializable
{
    @ApiModelProperty(value = "\u8d39\u7528\u5217\u9879id\u503c", required = true)
    private Integer dictionaryId;
    @ApiModelProperty("\u8d39\u7528\u5217\u9879\u540d\u79f0")
    private String ladName;
    @ApiModelProperty("\u6570\u91cf")
    private Double amount;
    @ApiModelProperty("\u6570\u91cf\u5355\u4f4d")
    private String unitAmount;
    @ApiModelProperty("\u5355\u4ef7")
    private Double unitPrice;
    @ApiModelProperty("\u5355\u4ef7\u5355\u4f4d")
    private String unitPricePerUnit;
    @ApiModelProperty("\u8d39\u7528\u603b\u548c(\u4e07\u5143)")
    private Double totalCost;

    public Integer getDictionaryId() {
        return this.dictionaryId;
    }

    public String getLadName() {
        return this.ladName;
    }

    public Double getAmount() {
        return this.amount;
    }

    public String getUnitAmount() {
        return this.unitAmount;
    }

    public Double getUnitPrice() {
        return this.unitPrice;
    }

    public String getUnitPricePerUnit() {
        return this.unitPricePerUnit;
    }

    public Double getTotalCost() {
        return this.totalCost;
    }

    public LadCostVO setDictionaryId(final Integer dictionaryId) {
        this.dictionaryId = dictionaryId;
        return this;
    }

    public LadCostVO setLadName(final String ladName) {
        this.ladName = ladName;
        return this;
    }

    public LadCostVO setAmount(final Double amount) {
        this.amount = amount;
        return this;
    }

    public LadCostVO setUnitAmount(final String unitAmount) {
        this.unitAmount = unitAmount;
        return this;
    }

    public LadCostVO setUnitPrice(final Double unitPrice) {
        this.unitPrice = unitPrice;
        return this;
    }

    public LadCostVO setUnitPricePerUnit(final String unitPricePerUnit) {
        this.unitPricePerUnit = unitPricePerUnit;
        return this;
    }

    public LadCostVO setTotalCost(final Double totalCost) {
        this.totalCost = totalCost;
        return this;
    }

    @Override
    public String toString() {
        return "LadCostVO(dictionaryId=" + this.getDictionaryId() + ", ladName=" + this.getLadName() + ", amount=" + this.getAmount() + ", unitAmount=" + this.getUnitAmount() + ", unitPrice=" + this.getUnitPrice() + ", unitPricePerUnit=" + this.getUnitPricePerUnit() + ", totalCost=" + this.getTotalCost() + ")";
    }

    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof LadCostVO)) {
            return false;
        }
        final LadCostVO other = (LadCostVO)o;
        if (!other.canEqual(this)) {
            return false;
        }
        final Object this$dictionaryId = this.getDictionaryId();
        final Object other$dictionaryId = other.getDictionaryId();
        Label_0065: {
            if (this$dictionaryId == null) {
                if (other$dictionaryId == null) {
                    break Label_0065;
                }
            }
            else if (this$dictionaryId.equals(other$dictionaryId)) {
                break Label_0065;
            }
            return false;
        }
        final Object this$amount = this.getAmount();
        final Object other$amount = other.getAmount();
        Label_0102: {
            if (this$amount == null) {
                if (other$amount == null) {
                    break Label_0102;
                }
            }
            else if (this$amount.equals(other$amount)) {
                break Label_0102;
            }
            return false;
        }
        final Object this$unitPrice = this.getUnitPrice();
        final Object other$unitPrice = other.getUnitPrice();
        Label_0139: {
            if (this$unitPrice == null) {
                if (other$unitPrice == null) {
                    break Label_0139;
                }
            }
            else if (this$unitPrice.equals(other$unitPrice)) {
                break Label_0139;
            }
            return false;
        }
        final Object this$totalCost = this.getTotalCost();
        final Object other$totalCost = other.getTotalCost();
        Label_0176: {
            if (this$totalCost == null) {
                if (other$totalCost == null) {
                    break Label_0176;
                }
            }
            else if (this$totalCost.equals(other$totalCost)) {
                break Label_0176;
            }
            return false;
        }
        final Object this$ladName = this.getLadName();
        final Object other$ladName = other.getLadName();
        Label_0213: {
            if (this$ladName == null) {
                if (other$ladName == null) {
                    break Label_0213;
                }
            }
            else if (this$ladName.equals(other$ladName)) {
                break Label_0213;
            }
            return false;
        }
        final Object this$unitAmount = this.getUnitAmount();
        final Object other$unitAmount = other.getUnitAmount();
        Label_0250: {
            if (this$unitAmount == null) {
                if (other$unitAmount == null) {
                    break Label_0250;
                }
            }
            else if (this$unitAmount.equals(other$unitAmount)) {
                break Label_0250;
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
        return other instanceof LadCostVO;
    }

    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
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
