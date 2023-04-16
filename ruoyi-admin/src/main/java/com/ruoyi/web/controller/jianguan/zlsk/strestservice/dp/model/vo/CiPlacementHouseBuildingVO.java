package com.ruoyi.web.controller.jianguan.zlsk.strestservice.dp.model.vo;

import com.ruoyi.web.controller.jianguan.zlsk.strestservice.dp.model.CiPlacementHouseBuilding;
import io.swagger.annotations.*;
import java.util.*;

public class CiPlacementHouseBuildingVO
{
    @ApiModelProperty("\u82e5\u8bbe\u7f6e\u5730\u5757\u70b9\u9009,\u5bb9\u79ef\u7387")
    private Map<Double, String> volume;
    @ApiModelProperty("\u82e5\u672a\u8bbe\u7f6e\u5b89\u7f6e\u623f\u5730\u5757,\u4eba\u5747\u8865\u507f\u5efa\u7b51\u9762\u79ef")
    private Map<Double, String> CompensatedAreaPerCapita;
    private List<CiPlacementHouseBuilding> ciPlacementHouseBuilding;

    public Map<Double, String> getVolume() {
        return this.volume;
    }

    public Map<Double, String> getCompensatedAreaPerCapita() {
        return this.CompensatedAreaPerCapita;
    }

    public List<CiPlacementHouseBuilding> getCiPlacementHouseBuilding() {
        return this.ciPlacementHouseBuilding;
    }

    public CiPlacementHouseBuildingVO setVolume(final Map<Double, String> volume) {
        this.volume = volume;
        return this;
    }

    public CiPlacementHouseBuildingVO setCompensatedAreaPerCapita(final Map<Double, String> CompensatedAreaPerCapita) {
        this.CompensatedAreaPerCapita = CompensatedAreaPerCapita;
        return this;
    }

    public CiPlacementHouseBuildingVO setCiPlacementHouseBuilding(final List<CiPlacementHouseBuilding> ciPlacementHouseBuilding) {
        this.ciPlacementHouseBuilding = ciPlacementHouseBuilding;
        return this;
    }

    @Override
    public String toString() {
        return "CiPlacementHouseBuildingVO(volume=" + this.getVolume() + ", CompensatedAreaPerCapita=" + this.getCompensatedAreaPerCapita() + ", ciPlacementHouseBuilding=" + this.getCiPlacementHouseBuilding() + ")";
    }

    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof CiPlacementHouseBuildingVO)) {
            return false;
        }
        final CiPlacementHouseBuildingVO other = (CiPlacementHouseBuildingVO)o;
        if (!other.canEqual(this)) {
            return false;
        }
        final Object this$volume = this.getVolume();
        final Object other$volume = other.getVolume();
        Label_0065: {
            if (this$volume == null) {
                if (other$volume == null) {
                    break Label_0065;
                }
            }
            else if (this$volume.equals(other$volume)) {
                break Label_0065;
            }
            return false;
        }
        final Object this$CompensatedAreaPerCapita = this.getCompensatedAreaPerCapita();
        final Object other$CompensatedAreaPerCapita = other.getCompensatedAreaPerCapita();
        Label_0102: {
            if (this$CompensatedAreaPerCapita == null) {
                if (other$CompensatedAreaPerCapita == null) {
                    break Label_0102;
                }
            }
            else if (this$CompensatedAreaPerCapita.equals(other$CompensatedAreaPerCapita)) {
                break Label_0102;
            }
            return false;
        }
        final Object this$ciPlacementHouseBuilding = this.getCiPlacementHouseBuilding();
        final Object other$ciPlacementHouseBuilding = other.getCiPlacementHouseBuilding();
        if (this$ciPlacementHouseBuilding == null) {
            if (other$ciPlacementHouseBuilding == null) {
                return true;
            }
        }
        else if (this$ciPlacementHouseBuilding.equals(other$ciPlacementHouseBuilding)) {
            return true;
        }
        return false;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof CiPlacementHouseBuildingVO;
    }

    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $volume = this.getVolume();
        result = result * 59 + (($volume == null) ? 43 : $volume.hashCode());
        final Object $CompensatedAreaPerCapita = this.getCompensatedAreaPerCapita();
        result = result * 59 + (($CompensatedAreaPerCapita == null) ? 43 : $CompensatedAreaPerCapita.hashCode());
        final Object $ciPlacementHouseBuilding = this.getCiPlacementHouseBuilding();
        result = result * 59 + (($ciPlacementHouseBuilding == null) ? 43 : $ciPlacementHouseBuilding.hashCode());
        return result;
    }
}
