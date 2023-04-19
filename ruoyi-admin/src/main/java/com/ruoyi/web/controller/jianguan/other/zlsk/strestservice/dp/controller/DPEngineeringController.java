package com.ruoyi.web.controller.jianguan.other.zlsk.strestservice.dp.controller;

import com.ruoyi.web.controller.jianguan.other.zlsk.strestservice.dp.enums.ProjectNameEnum;
import com.ruoyi.web.controller.jianguan.other.zlsk.strestservice.dp.model.dto.LadCostInfoDTO;
import com.ruoyi.web.controller.jianguan.other.zlsk.stsupporter.stctk.model.STData;
import com.ruoyi.web.controller.jianguan.other.zlsk.strestservice.dp.model.*;
import com.ruoyi.web.controller.jianguan.other.zlsk.strestservice.dp.service.*;
import org.springframework.beans.factory.annotation.*;
import java.util.*;
import org.springframework.web.bind.annotation.*;
import com.alibaba.fastjson.*;
import io.swagger.annotations.*;
import javax.servlet.http.*;

@Api("digital_platform-total_engineering")
@RestController
@RequestMapping({ "/dp/engineering/v1" })
public class DPEngineeringController
{
    @Autowired
    private IDpEngineeringService dpEngineeringService;
    @Autowired
    private ILadCostService ladCostService;
    @Autowired
    private ICiEarthStoneWorkService ciEarthStoneWorkService;
    @Autowired
    private ICiRoadWorkService ciRoadWorkService;
    @Autowired
    private ICiBridgeService iCiBridgeService;
    @Autowired
    private ICiLandScapeGreeningWorkService ciLandScapeGreeningWorkService;
    @Autowired
    private ICiPublicSupportingFacilitiesService ciPublicSupportingFacilitiesService;
    @Autowired
    private ICiRiverRegulationService ciRiverRegulationService;
    @Autowired
    private ICiPlacementHouseBuildingService ciPlacementHouseBuildingService;
    @Autowired
    private IDpOtherCostService dpOtherCostService;

    @ApiOperation("\u67e5\u8be2\u5de5\u7a0b\u5217\u8868")
    @RequestMapping(value = { "/list" }, method = { RequestMethod.GET })
    public STData<List<DpEngineering>> getList() {
        return (STData<List<DpEngineering>>)new STData((Object)this.dpEngineeringService.getEngineeringList());
    }

    @ApiOperation("\u521b\u5efa/\u4fee\u6539\u603b\u5de5\u7a0b")
    @RequestMapping(value = { "/create" }, method = { RequestMethod.POST })
    public STData<Integer> createEngineering(@RequestBody final DpEngineering engineering) {
        return (STData<Integer>)new STData(0, "success", (Object)this.dpEngineeringService.createEngineering(engineering));
    }

    @ApiOperation("\u5220\u9664\u5de5\u7a0b")
    @ApiImplicitParam(name = "id", value = "\u5de5\u7a0bid", dataType = "Integer", required = true)
    @RequestMapping(value = { "/delete/{id}" }, method = { RequestMethod.DELETE })
    public STData<String> deleteEngineering(@PathVariable("id") final Integer id) {
        return (STData<String>)new STData(0, this.dpEngineeringService.deleteEngineering(id), (Object)null);
    }

    @ApiOperation("\u83b7\u53d6\u6240\u6709\u6a21\u5757\u540d\u79f0")
    @RequestMapping(value = { "/model" }, method = { RequestMethod.GET })
    public STData<JSONObject> modelList() {
        return (STData<JSONObject>)new STData((Object)this.dpEngineeringService.modelList());
    }

    @ApiOperation("\u83b7\u53d6\u6a21\u5757\u4fe1\u606f")
    @ApiImplicitParams({ @ApiImplicitParam(name = "engineeringId", value = "\u5de5\u7a0bId", required = true), @ApiImplicitParam(name = "modelName", value = "\u6a21\u5757\u540d\u79f0", required = true) })
    @RequestMapping(value = { "/model/info/{engineeringId}/{modelName}" }, method = { RequestMethod.GET })
    public STData modelInfo(@PathVariable("engineeringId") final Integer engineeringId, @PathVariable("modelName") final String modelName) {
        final ProjectNameEnum nameEnum = ProjectNameEnum.valueOf(modelName);
        switch (nameEnum) {
            case LAD: {
                return new STData((Object)this.ladCostService.getList(engineeringId));
            }
            case EARTH_STONE: {
                return new STData((Object)this.ciEarthStoneWorkService.workList(engineeringId));
            }
            case ROAD: {
                return new STData((Object)this.ciRoadWorkService.workList(engineeringId));
            }
            case BRIDGE: {
                return new STData((Object)this.iCiBridgeService.workList(engineeringId));
            }
            case LANDSCAPE: {
                return new STData((Object)this.ciLandScapeGreeningWorkService.workList(engineeringId));
            }
            case FACILITIES: {
                return new STData((Object)this.ciPublicSupportingFacilitiesService.workList(engineeringId));
            }
            case RIVER: {
                return new STData((Object)this.ciRiverRegulationService.workList(engineeringId));
            }
            case PLACEMENT_HOUSE: {
                return new STData((Object)this.ciPlacementHouseBuildingService.houseWorkList(engineeringId));
            }
            default: {
                return new STData(0, "\u6682\u672a\u5f00\u53d1", (Object)null);
            }
        }
    }

    @ApiOperation("\u83b7\u53d6\u5176\u4ed6\u8d39\u7528\u5217\u8868")
    @ApiImplicitParams({ @ApiImplicitParam(name = "engineeringId", value = "\u5de5\u7a0bid", required = true, dataType = "Integer"), @ApiImplicitParam(name = "typeId", value = "\u7c7b\u578bid", required = true, dataType = "Integer") })
    @RequestMapping(value = { "/cost/{engineeringId}/{typeId}" }, method = { RequestMethod.GET })
    public STData<List<DpOtherCost>> otherCost(@PathVariable("engineeringId") final Integer engineeringId, @PathVariable("typeId") final Integer typeId) {
        return (STData<List<DpOtherCost>>)new STData(0, "success", (Object)this.dpOtherCostService.workList(engineeringId, typeId));
    }

    @ApiOperation("\u5220\u9664\u5176\u4ed6\u8d39\u7528")
    @ApiImplicitParams({ @ApiImplicitParam(name = "ids", value = "\u8d39\u7528\u4e3b\u952eid\u96c6\u5408", required = true, dataType = "array") })
    @RequestMapping(value = { "/cost" }, method = { RequestMethod.DELETE })
    public STData otherCost(@RequestBody final List<Integer> ids) {
        return new STData(0, this.dpOtherCostService.deleteWorkInfo(ids), (Object)null);
    }

    @ApiOperation("\u5220\u9664\u6a21\u5757\u4fe1\u606f")
    @ApiImplicitParams({ @ApiImplicitParam(name = "ids", value = "\u6a21\u5757id\u96c6\u5408", required = true, dataType = "array"), @ApiImplicitParam(name = "modelName", value = "\u6a21\u5757\u540d\u79f0", required = true) })
    @RequestMapping(value = { "/model/info/{modelName}" }, method = { RequestMethod.DELETE })
    public STData modelInfo(@RequestBody final List<Integer> ids, @PathVariable("modelName") final String modelName) {
        final ProjectNameEnum nameEnum = ProjectNameEnum.valueOf(modelName);
        switch (nameEnum) {
            case EARTH_STONE: {
                return new STData(0, this.ciEarthStoneWorkService.deleteWorkInfo(ids), (Object)null);
            }
            case ROAD: {
                return new STData(0, this.ciRoadWorkService.deleteWorkInfo(ids), (Object)null);
            }
            case BRIDGE: {
                return new STData(0, this.iCiBridgeService.deleteWorkInfo(ids), (Object)null);
            }
            case LANDSCAPE: {
                return new STData(0, this.ciLandScapeGreeningWorkService.deleteWorkInfo(ids), (Object)null);
            }
            case FACILITIES: {
                return new STData(0, this.ciPublicSupportingFacilitiesService.deleteWorkInfo(ids), (Object)null);
            }
            case RIVER: {
                return new STData(0, this.ciRiverRegulationService.deleteWorkInfo(ids), (Object)null);
            }
            case PLACEMENT_HOUSE: {
                return new STData(0, this.ciPlacementHouseBuildingService.deleteWorkInfo(ids), (Object)null);
            }
            default: {
                return new STData(0, "\u6682\u672a\u5f00\u53d1", (Object)null);
            }
        }
    }

    @ApiOperation("\u4fee\u6539\u5f81\u5730\u62c6\u8fc1\u8d39\u7528\u9879")
    @RequestMapping(value = { "/lad/keep" }, method = { RequestMethod.POST })
    public STData keepLadInfo(@RequestBody final LadCostInfoDTO dto) {
        return new STData(0, this.ladCostService.keepLadInfo(dto), null);
    }

    @ApiOperation("\u65b0\u589e\u5f81\u5730\u62c6\u8fc1\u8d39\u7528\u9879")
    @RequestMapping(value = { "/lad" }, method = { RequestMethod.POST })
    public STData addLadInfo(@RequestBody final List<LadCost> ladCost) {
        return new STData(0, "success", (Object)this.ladCostService.addLad(ladCost));
    }

    @ApiOperation("\u65b0\u589e/\u4fee\u6539\u573a\u5730\u5e73\u6574\u53ca\u571f\u77f3\u65b9\u4fe1\u606f")
    @RequestMapping(value = { "/ci/earthAndStone" }, method = { RequestMethod.POST })
    public STData<Integer> createEarthStoneWork(@RequestBody final CiEarthStoneWork ciEarthStoneWork) {
        return (STData<Integer>)new STData(0, "success", (Object)this.ciEarthStoneWorkService.createWork(ciEarthStoneWork));
    }

    @ApiOperation("\u65b0\u589e/\u4fee\u6539\u9053\u8def\u5de5\u7a0b\u4fe1\u606f")
    @RequestMapping(value = { "/ci/road" }, method = { RequestMethod.POST })
    public STData<Integer> createRoadWork(@RequestBody final CiRoadWork ciRoadWork) {
        return (STData<Integer>)new STData(0, "success", (Object)this.ciRoadWorkService.createWork(ciRoadWork));
    }

    @ApiOperation("\u65b0\u589e/\u4fee\u6539\u6865\u6881\u5de5\u7a0b\u4fe1\u606f")
    @RequestMapping(value = { "/ci/bridge" }, method = { RequestMethod.POST })
    public STData<Integer> createBridgeWork(@RequestBody final CiBridgeWork ciBridgeWork) {
        return (STData<Integer>)new STData(0, "success", (Object)this.iCiBridgeService.createWork(ciBridgeWork));
    }

    @ApiOperation("\u65b0\u589e/\u4fee\u6539\u666f\u89c2\u7eff\u5316\u5de5\u7a0b\u4fe1\u606f")
    @RequestMapping(value = { "/ci/landscape" }, method = { RequestMethod.POST })
    public STData<Integer> createLandScapeGreenWork(@RequestBody final CiLandscapeGreeningWork ciLandscapeGreeningWork) {
        return (STData<Integer>)new STData(0, "success", (Object)this.ciLandScapeGreeningWorkService.createWork(ciLandscapeGreeningWork));
    }

    @ApiOperation("\u65b0\u589e/\u4fee\u6539\u516c\u5efa\u914d\u5957\u5de5\u7a0b\u4fe1\u606f")
    @RequestMapping(value = { "/ci/facilities" }, method = { RequestMethod.POST })
    public STData<Integer> createFacilitiesWork(@RequestBody final CiPublicSupportingFacilities ciPublicSupportingFacilities) {
        return (STData<Integer>)new STData(0, "success", (Object)this.ciPublicSupportingFacilitiesService.createWork(ciPublicSupportingFacilities));
    }

    @ApiOperation("\u65b0\u589e/\u4fee\u6539\u6cb3\u9053\u6cbb\u7406\u5de5\u7a0b\u4fe1\u606f")
    @RequestMapping(value = { "/ci/river" }, method = { RequestMethod.POST })
    public STData<Integer> createRiverWork(@RequestBody final CiRiverRegulation ciRiverRegulation) {
        return (STData<Integer>)new STData(0, "success", (Object)this.ciRiverRegulationService.createWork(ciRiverRegulation));
    }

    @ApiOperation("\u65b0\u589e/\u4fee\u6539\u5b89\u7f6e\u623f\u5efa\u8bbe\u5de5\u7a0b\u4fe1\u606f")
    @RequestMapping(value = { "/ci/house" }, method = { RequestMethod.POST })
    public STData<Integer> createHouseWork(@RequestBody final CiPlacementHouseBuilding ciPlacementHouseBuilding) {
        return (STData<Integer>)new STData(0, "success", (Object)this.ciPlacementHouseBuildingService.createWork(ciPlacementHouseBuilding));
    }

    @ApiOperation("\u65b0\u589e/\u4fee\u6539\u5176\u4ed6\u8d39\u7528\u5217\u8868")
    @RequestMapping(value = { "/cost/other" }, method = { RequestMethod.POST })
    public STData<Integer> otherCost(@RequestBody final DpOtherCost dpOtherCost) {
        return (STData<Integer>)new STData(0, "success", (Object)this.dpOtherCostService.createWork(dpOtherCost));
    }

    @ApiOperation("\u603b\u8d39\u7528\u6e05\u5355")
    @ApiImplicitParam(name = "engineeringId", value = "\u5de5\u7a0bid", dataType = "Integer", required = true)
    @RequestMapping(value = { "/totalCost" }, method = { RequestMethod.GET })
    public STData<JSONObject> totalCost(final Integer engineeringId) {
        return (STData<JSONObject>)new STData((Object)this.dpEngineeringService.getTotalCost(engineeringId));
    }

    @ApiOperation("\u4fdd\u5b58\u4fee\u6539\u8d39\u7387/\u5176\u4ed6\u8d39\u7528\u91d1\u989d")
    @RequestMapping(value = { "/totalCost" }, method = { RequestMethod.POST })
    public STData<JSONObject> totalCost(@RequestBody final DpEngineeringCost dpEngineeringCost) {
        final Integer integer = this.dpEngineeringService.changeTotalCost(dpEngineeringCost);
        if (integer == 0) {
            return (STData<JSONObject>)new STData(0, "unknown error", (Object)null);
        }
        return (STData<JSONObject>)new STData(0, "success", (Object)null);
    }

    @ApiOperation("\u83b7\u53d6\u5de5\u7a0b\u8d39\u7387\u4fe1\u606f")
    @ApiImplicitParams({ @ApiImplicitParam(name = "engineeringId", value = "\u5de5\u7a0bid", required = true, dataType = "Integer") })
    @RequestMapping(value = { "/rate" }, method = { RequestMethod.GET })
    public STData<DpEngineeringCost> rate(final Integer engineeringId) {
        return (STData<DpEngineeringCost>)new STData((Object)this.dpEngineeringService.getRate(engineeringId));
    }

    @ApiOperation("\u603b\u8d39\u7528\u5bfc\u51faexcel")
    @ApiImplicitParam(name = "engineeringId", value = "\u5de5\u7a0bid", dataType = "Integer", required = true)
    @RequestMapping(value = { "/excel" }, method = { RequestMethod.GET })
    public void outExcel(final Integer engineeringId, final HttpServletResponse httpServletResponse) {
        this.dpEngineeringService.outExcel(engineeringId, httpServletResponse);
    }
}
