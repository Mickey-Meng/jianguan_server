package com.ruoyi.web.controller.jianguan.zlsk.strestservice.dp.model.dto;

import java.io.*;

import com.ruoyi.web.controller.jianguan.zlsk.strestservice.dp.model.LadAcreage;
import com.ruoyi.web.controller.jianguan.zlsk.strestservice.dp.model.LadCost;
import io.swagger.annotations.*;
import java.util.*;

public class LadCostInfoDTO implements Serializable
{
    @ApiModelProperty("\u5de5\u7a0bid")
    private Integer engineeringId;
    private List<LadCost> ladCostList;
    private LadAcreage ladAcreage;
    @ApiModelProperty("\u5750\u6807\u4e32")
    private String site;
    @ApiModelProperty("\u603b\u8d39\u7528")
    private Double totalCost;
    @ApiModelProperty("\u5f81\u62c6\u4e0d\u53ef\u9884\u89c1\u8d39\u8d39\u7387")
    private Double ladUnforeseenRate;

    public Integer getEngineeringId() {
        return this.engineeringId;
    }

    public List<LadCost> getLadCostList() {
        return this.ladCostList;
    }

    public LadAcreage getLadAcreage() {
        return this.ladAcreage;
    }

    public String getSite() {
        return this.site;
    }

    public Double getTotalCost() {
        return this.totalCost;
    }

    public Double getLadUnforeseenRate() {
        return this.ladUnforeseenRate;
    }

    public LadCostInfoDTO setEngineeringId(final Integer engineeringId) {
        this.engineeringId = engineeringId;
        return this;
    }

    public LadCostInfoDTO setLadCostList(final List<LadCost> ladCostList) {
        this.ladCostList = ladCostList;
        return this;
    }

    public LadCostInfoDTO setLadAcreage(final LadAcreage ladAcreage) {
        this.ladAcreage = ladAcreage;
        return this;
    }

    public LadCostInfoDTO setSite(final String site) {
        this.site = site;
        return this;
    }

    public LadCostInfoDTO setTotalCost(final Double totalCost) {
        this.totalCost = totalCost;
        return this;
    }

    public LadCostInfoDTO setLadUnforeseenRate(final Double ladUnforeseenRate) {
        this.ladUnforeseenRate = ladUnforeseenRate;
        return this;
    }

    @Override
    public String toString() {
        return "LadCostInfoDTO(engineeringId=" + this.getEngineeringId() + ", ladCostList=" + this.getLadCostList() + ", ladAcreage=" + this.getLadAcreage() + ", site=" + this.getSite() + ", totalCost=" + this.getTotalCost() + ", ladUnforeseenRate=" + this.getLadUnforeseenRate() + ")";
    }

    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof LadCostInfoDTO)) {
            return false;
        }
        final LadCostInfoDTO other = (LadCostInfoDTO)o;
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
        final Object this$totalCost = this.getTotalCost();
        final Object other$totalCost = other.getTotalCost();
        Label_0102: {
            if (this$totalCost == null) {
                if (other$totalCost == null) {
                    break Label_0102;
                }
            }
            else if (this$totalCost.equals(other$totalCost)) {
                break Label_0102;
            }
            return false;
        }
        final Object this$ladUnforeseenRate = this.getLadUnforeseenRate();
        final Object other$ladUnforeseenRate = other.getLadUnforeseenRate();
        Label_0139: {
            if (this$ladUnforeseenRate == null) {
                if (other$ladUnforeseenRate == null) {
                    break Label_0139;
                }
            }
            else if (this$ladUnforeseenRate.equals(other$ladUnforeseenRate)) {
                break Label_0139;
            }
            return false;
        }
        final Object this$ladCostList = this.getLadCostList();
        final Object other$ladCostList = other.getLadCostList();
        Label_0176: {
            if (this$ladCostList == null) {
                if (other$ladCostList == null) {
                    break Label_0176;
                }
            }
            else if (this$ladCostList.equals(other$ladCostList)) {
                break Label_0176;
            }
            return false;
        }
        final Object this$ladAcreage = this.getLadAcreage();
        final Object other$ladAcreage = other.getLadAcreage();
        Label_0213: {
            if (this$ladAcreage == null) {
                if (other$ladAcreage == null) {
                    break Label_0213;
                }
            }
            else if (this$ladAcreage.equals(other$ladAcreage)) {
                break Label_0213;
            }
            return false;
        }
        final Object this$site = this.getSite();
        final Object other$site = other.getSite();
        if (this$site == null) {
            if (other$site == null) {
                return true;
            }
        }
        else if (this$site.equals(other$site)) {
            return true;
        }
        return false;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof LadCostInfoDTO;
    }

    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $engineeringId = this.getEngineeringId();
        result = result * 59 + (($engineeringId == null) ? 43 : $engineeringId.hashCode());
        final Object $totalCost = this.getTotalCost();
        result = result * 59 + (($totalCost == null) ? 43 : $totalCost.hashCode());
        final Object $ladUnforeseenRate = this.getLadUnforeseenRate();
        result = result * 59 + (($ladUnforeseenRate == null) ? 43 : $ladUnforeseenRate.hashCode());
        final Object $ladCostList = this.getLadCostList();
        result = result * 59 + (($ladCostList == null) ? 43 : $ladCostList.hashCode());
        final Object $ladAcreage = this.getLadAcreage();
        result = result * 59 + (($ladAcreage == null) ? 43 : $ladAcreage.hashCode());
        final Object $site = this.getSite();
        result = result * 59 + (($site == null) ? 43 : $site.hashCode());
        return result;
    }
}
