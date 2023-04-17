package com.ruoyi.web.controller.jianguan.zlsk.strestservice.dp.model;

import java.io.*;
import io.swagger.annotations.*;

public class DpEngineeringCost implements Serializable
{
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "\u5de5\u7a0bid", required = true)
    private Integer engineeringId;
    @ApiModelProperty("\u5efa\u5b89\u5de5\u7a0b\u8d39(\u4e07\u5143)")
    private Double constructionInstallationCost;
    @ApiModelProperty("\u5de5\u7a0b\u5176\u4ed6\u8d39(\u4e07\u5143)")
    private Double otherFee;
    @ApiModelProperty("\u9884\u5907\u8d39(\u4e07\u5143)")
    private Double budgetReserve;
    @ApiModelProperty("\u5f81\u5730\u62c6\u8fc1\u603b\u8d39\u7528(\u4e07\u5143)")
    private Double ladFee;
    @ApiModelProperty("\u7ba1\u7406\u8d39(\u4e07\u5143)")
    private Double administrativeExpense;
    @ApiModelProperty("\u9879\u76ee\u9759\u6001\u6295\u8d44(\u4e07\u5143)")
    private Double staticInvestment;
    @ApiModelProperty("\u8d22\u52a1\u8d39(\u4e07\u5143)")
    private Double financialExpense;
    @ApiModelProperty("\u9879\u76ee\u52a8\u6001\u603b\u6295\u8d44(\u4e07\u5143)")
    private Double totalInvestment;
    @ApiModelProperty("\u5176\u4ed6\u8d39\u8d39\u7387")
    private Double otherFeeRate;
    @ApiModelProperty("\u9884\u5907\u8d39\u8d39\u7387")
    private Double budgetReserveRate;
    @ApiModelProperty("\u7ba1\u7406\u8d39\u8d39\u7387")
    private Double administrativeExpenseRate;
    @ApiModelProperty("\u8d22\u52a1\u8d39\u8d39\u7387")
    private Double financialExpenseRate;
    @ApiModelProperty("\u62c6\u8fc1\u4e0d\u53ef\u9884\u89c1\u8d39\u8d39\u7387")
    private Double ladUnforeseenRate;

    public Integer getEngineeringId() {
        return this.engineeringId;
    }

    public Double getConstructionInstallationCost() {
        return this.constructionInstallationCost;
    }

    public Double getOtherFee() {
        return this.otherFee;
    }

    public Double getBudgetReserve() {
        return this.budgetReserve;
    }

    public Double getLadFee() {
        return this.ladFee;
    }

    public Double getAdministrativeExpense() {
        return this.administrativeExpense;
    }

    public Double getStaticInvestment() {
        return this.staticInvestment;
    }

    public Double getFinancialExpense() {
        return this.financialExpense;
    }

    public Double getTotalInvestment() {
        return this.totalInvestment;
    }

    public Double getOtherFeeRate() {
        return this.otherFeeRate;
    }

    public Double getBudgetReserveRate() {
        return this.budgetReserveRate;
    }

    public Double getAdministrativeExpenseRate() {
        return this.administrativeExpenseRate;
    }

    public Double getFinancialExpenseRate() {
        return this.financialExpenseRate;
    }

    public Double getLadUnforeseenRate() {
        return this.ladUnforeseenRate;
    }

    public DpEngineeringCost setEngineeringId(final Integer engineeringId) {
        this.engineeringId = engineeringId;
        return this;
    }

    public DpEngineeringCost setConstructionInstallationCost(final Double constructionInstallationCost) {
        this.constructionInstallationCost = constructionInstallationCost;
        return this;
    }

    public DpEngineeringCost setOtherFee(final Double otherFee) {
        this.otherFee = otherFee;
        return this;
    }

    public DpEngineeringCost setBudgetReserve(final Double budgetReserve) {
        this.budgetReserve = budgetReserve;
        return this;
    }

    public DpEngineeringCost setLadFee(final Double ladFee) {
        this.ladFee = ladFee;
        return this;
    }

    public DpEngineeringCost setAdministrativeExpense(final Double administrativeExpense) {
        this.administrativeExpense = administrativeExpense;
        return this;
    }

    public DpEngineeringCost setStaticInvestment(final Double staticInvestment) {
        this.staticInvestment = staticInvestment;
        return this;
    }

    public DpEngineeringCost setFinancialExpense(final Double financialExpense) {
        this.financialExpense = financialExpense;
        return this;
    }

    public DpEngineeringCost setTotalInvestment(final Double totalInvestment) {
        this.totalInvestment = totalInvestment;
        return this;
    }

    public DpEngineeringCost setOtherFeeRate(final Double otherFeeRate) {
        this.otherFeeRate = otherFeeRate;
        return this;
    }

    public DpEngineeringCost setBudgetReserveRate(final Double budgetReserveRate) {
        this.budgetReserveRate = budgetReserveRate;
        return this;
    }

    public DpEngineeringCost setAdministrativeExpenseRate(final Double administrativeExpenseRate) {
        this.administrativeExpenseRate = administrativeExpenseRate;
        return this;
    }

    public DpEngineeringCost setFinancialExpenseRate(final Double financialExpenseRate) {
        this.financialExpenseRate = financialExpenseRate;
        return this;
    }

    public DpEngineeringCost setLadUnforeseenRate(final Double ladUnforeseenRate) {
        this.ladUnforeseenRate = ladUnforeseenRate;
        return this;
    }

    @Override
    public String toString() {
        return "DpEngineeringCost(engineeringId=" + this.getEngineeringId() + ", constructionInstallationCost=" + this.getConstructionInstallationCost() + ", otherFee=" + this.getOtherFee() + ", budgetReserve=" + this.getBudgetReserve() + ", ladFee=" + this.getLadFee() + ", administrativeExpense=" + this.getAdministrativeExpense() + ", staticInvestment=" + this.getStaticInvestment() + ", financialExpense=" + this.getFinancialExpense() + ", totalInvestment=" + this.getTotalInvestment() + ", otherFeeRate=" + this.getOtherFeeRate() + ", budgetReserveRate=" + this.getBudgetReserveRate() + ", administrativeExpenseRate=" + this.getAdministrativeExpenseRate() + ", financialExpenseRate=" + this.getFinancialExpenseRate() + ", ladUnforeseenRate=" + this.getLadUnforeseenRate() + ")";
    }

    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof DpEngineeringCost)) {
            return false;
        }
        final DpEngineeringCost other = (DpEngineeringCost)o;
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
        final Object this$constructionInstallationCost = this.getConstructionInstallationCost();
        final Object other$constructionInstallationCost = other.getConstructionInstallationCost();
        Label_0102: {
            if (this$constructionInstallationCost == null) {
                if (other$constructionInstallationCost == null) {
                    break Label_0102;
                }
            }
            else if (this$constructionInstallationCost.equals(other$constructionInstallationCost)) {
                break Label_0102;
            }
            return false;
        }
        final Object this$otherFee = this.getOtherFee();
        final Object other$otherFee = other.getOtherFee();
        Label_0139: {
            if (this$otherFee == null) {
                if (other$otherFee == null) {
                    break Label_0139;
                }
            }
            else if (this$otherFee.equals(other$otherFee)) {
                break Label_0139;
            }
            return false;
        }
        final Object this$budgetReserve = this.getBudgetReserve();
        final Object other$budgetReserve = other.getBudgetReserve();
        Label_0176: {
            if (this$budgetReserve == null) {
                if (other$budgetReserve == null) {
                    break Label_0176;
                }
            }
            else if (this$budgetReserve.equals(other$budgetReserve)) {
                break Label_0176;
            }
            return false;
        }
        final Object this$ladFee = this.getLadFee();
        final Object other$ladFee = other.getLadFee();
        Label_0213: {
            if (this$ladFee == null) {
                if (other$ladFee == null) {
                    break Label_0213;
                }
            }
            else if (this$ladFee.equals(other$ladFee)) {
                break Label_0213;
            }
            return false;
        }
        final Object this$administrativeExpense = this.getAdministrativeExpense();
        final Object other$administrativeExpense = other.getAdministrativeExpense();
        Label_0250: {
            if (this$administrativeExpense == null) {
                if (other$administrativeExpense == null) {
                    break Label_0250;
                }
            }
            else if (this$administrativeExpense.equals(other$administrativeExpense)) {
                break Label_0250;
            }
            return false;
        }
        final Object this$staticInvestment = this.getStaticInvestment();
        final Object other$staticInvestment = other.getStaticInvestment();
        Label_0287: {
            if (this$staticInvestment == null) {
                if (other$staticInvestment == null) {
                    break Label_0287;
                }
            }
            else if (this$staticInvestment.equals(other$staticInvestment)) {
                break Label_0287;
            }
            return false;
        }
        final Object this$financialExpense = this.getFinancialExpense();
        final Object other$financialExpense = other.getFinancialExpense();
        Label_0324: {
            if (this$financialExpense == null) {
                if (other$financialExpense == null) {
                    break Label_0324;
                }
            }
            else if (this$financialExpense.equals(other$financialExpense)) {
                break Label_0324;
            }
            return false;
        }
        final Object this$totalInvestment = this.getTotalInvestment();
        final Object other$totalInvestment = other.getTotalInvestment();
        Label_0361: {
            if (this$totalInvestment == null) {
                if (other$totalInvestment == null) {
                    break Label_0361;
                }
            }
            else if (this$totalInvestment.equals(other$totalInvestment)) {
                break Label_0361;
            }
            return false;
        }
        final Object this$otherFeeRate = this.getOtherFeeRate();
        final Object other$otherFeeRate = other.getOtherFeeRate();
        Label_0398: {
            if (this$otherFeeRate == null) {
                if (other$otherFeeRate == null) {
                    break Label_0398;
                }
            }
            else if (this$otherFeeRate.equals(other$otherFeeRate)) {
                break Label_0398;
            }
            return false;
        }
        final Object this$budgetReserveRate = this.getBudgetReserveRate();
        final Object other$budgetReserveRate = other.getBudgetReserveRate();
        Label_0435: {
            if (this$budgetReserveRate == null) {
                if (other$budgetReserveRate == null) {
                    break Label_0435;
                }
            }
            else if (this$budgetReserveRate.equals(other$budgetReserveRate)) {
                break Label_0435;
            }
            return false;
        }
        final Object this$administrativeExpenseRate = this.getAdministrativeExpenseRate();
        final Object other$administrativeExpenseRate = other.getAdministrativeExpenseRate();
        Label_0472: {
            if (this$administrativeExpenseRate == null) {
                if (other$administrativeExpenseRate == null) {
                    break Label_0472;
                }
            }
            else if (this$administrativeExpenseRate.equals(other$administrativeExpenseRate)) {
                break Label_0472;
            }
            return false;
        }
        final Object this$financialExpenseRate = this.getFinancialExpenseRate();
        final Object other$financialExpenseRate = other.getFinancialExpenseRate();
        Label_0509: {
            if (this$financialExpenseRate == null) {
                if (other$financialExpenseRate == null) {
                    break Label_0509;
                }
            }
            else if (this$financialExpenseRate.equals(other$financialExpenseRate)) {
                break Label_0509;
            }
            return false;
        }
        final Object this$ladUnforeseenRate = this.getLadUnforeseenRate();
        final Object other$ladUnforeseenRate = other.getLadUnforeseenRate();
        if (this$ladUnforeseenRate == null) {
            if (other$ladUnforeseenRate == null) {
                return true;
            }
        }
        else if (this$ladUnforeseenRate.equals(other$ladUnforeseenRate)) {
            return true;
        }
        return false;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof DpEngineeringCost;
    }

    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $engineeringId = this.getEngineeringId();
        result = result * 59 + (($engineeringId == null) ? 43 : $engineeringId.hashCode());
        final Object $constructionInstallationCost = this.getConstructionInstallationCost();
        result = result * 59 + (($constructionInstallationCost == null) ? 43 : $constructionInstallationCost.hashCode());
        final Object $otherFee = this.getOtherFee();
        result = result * 59 + (($otherFee == null) ? 43 : $otherFee.hashCode());
        final Object $budgetReserve = this.getBudgetReserve();
        result = result * 59 + (($budgetReserve == null) ? 43 : $budgetReserve.hashCode());
        final Object $ladFee = this.getLadFee();
        result = result * 59 + (($ladFee == null) ? 43 : $ladFee.hashCode());
        final Object $administrativeExpense = this.getAdministrativeExpense();
        result = result * 59 + (($administrativeExpense == null) ? 43 : $administrativeExpense.hashCode());
        final Object $staticInvestment = this.getStaticInvestment();
        result = result * 59 + (($staticInvestment == null) ? 43 : $staticInvestment.hashCode());
        final Object $financialExpense = this.getFinancialExpense();
        result = result * 59 + (($financialExpense == null) ? 43 : $financialExpense.hashCode());
        final Object $totalInvestment = this.getTotalInvestment();
        result = result * 59 + (($totalInvestment == null) ? 43 : $totalInvestment.hashCode());
        final Object $otherFeeRate = this.getOtherFeeRate();
        result = result * 59 + (($otherFeeRate == null) ? 43 : $otherFeeRate.hashCode());
        final Object $budgetReserveRate = this.getBudgetReserveRate();
        result = result * 59 + (($budgetReserveRate == null) ? 43 : $budgetReserveRate.hashCode());
        final Object $administrativeExpenseRate = this.getAdministrativeExpenseRate();
        result = result * 59 + (($administrativeExpenseRate == null) ? 43 : $administrativeExpenseRate.hashCode());
        final Object $financialExpenseRate = this.getFinancialExpenseRate();
        result = result * 59 + (($financialExpenseRate == null) ? 43 : $financialExpenseRate.hashCode());
        final Object $ladUnforeseenRate = this.getLadUnforeseenRate();
        result = result * 59 + (($ladUnforeseenRate == null) ? 43 : $ladUnforeseenRate.hashCode());
        return result;
    }
}
