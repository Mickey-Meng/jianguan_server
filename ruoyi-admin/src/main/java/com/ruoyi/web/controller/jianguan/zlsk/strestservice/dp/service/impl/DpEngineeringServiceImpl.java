package com.ruoyi.web.controller.jianguan.zlsk.strestservice.dp.service.impl;

import com.ruoyi.web.controller.jianguan.zlsk.strestservice.dp.enums.CostTypeEnum;
import com.ruoyi.web.controller.jianguan.zlsk.strestservice.dp.enums.FilePathEnum;
import com.ruoyi.web.controller.jianguan.zlsk.strestservice.dp.enums.ProjectNameEnum;
import com.ruoyi.web.controller.jianguan.zlsk.strestservice.dp.enums.RateEnum;
import com.ruoyi.web.controller.jianguan.zlsk.strestservice.dp.mapper.*;
import com.ruoyi.web.controller.jianguan.zlsk.strestservice.dp.model.DpEngineering;
import com.ruoyi.web.controller.jianguan.zlsk.strestservice.dp.model.DpEngineeringCost;
import com.ruoyi.web.controller.jianguan.zlsk.strestservice.dp.model.*;
import com.ruoyi.web.controller.jianguan.zlsk.strestservice.dp.service.IDpEngineeringService;
import com.ruoyi.web.controller.jianguan.zlsk.strestservice.dp.util.DecimalUtil;
import com.ruoyi.web.controller.jianguan.zlsk.strestservice.dp.util.NumToCapitalUtil;
import com.ruoyi.web.controller.jianguan.zlsk.stsupporter.stctk.stbase.*;
import org.springframework.stereotype.*;
import org.springframework.beans.factory.annotation.*;
import com.alibaba.fastjson.*;
import java.math.*;
import javax.servlet.http.*;
import java.util.*;
import java.io.*;

@Service
public class DpEngineeringServiceImpl extends StServiceBase implements IDpEngineeringService
{
    @Autowired
    private DpEngineeringMapper dpEngineeringMapper;
    @Autowired
    private LadCostMapper ladCostMapper;
    @Autowired
    private CiEarthStoneWorkMapper ciEarthStoneWorkMapper;
    @Autowired
    private CiRoadWorkMapper ciRoadWorkMapper;
    @Autowired
    private CiBridgeWorkMapper ciBridgeWorkMapper;
    @Autowired
    private CiLandscapeGreeningWorkMapper ciLandscapeGreeningWorkMapper;
    @Autowired
    private CiPublicSupportingFacilitiesMapper ciPublicSupportingFacilitiesMapper;
    @Autowired
    private CiRiverRegulationMapper ciRiverRegulationMapper;
    @Autowired
    private CiPlacementHouseBuildingMapper ciPlacementHouseBuildingMapper;
    @Autowired
    private DpEngineeringCostMapper dpEngineeringCostMapper;
    @Autowired
    private DpOtherCostMapper dpOtherCostMapper;
    @Autowired
    private DpExcelTemplateMapper dpExcelTemplateMapper;

    public JSONObject modelList() {
        final JSONObject json = new JSONObject();
        for (final ProjectNameEnum value : ProjectNameEnum.values()) {
            json.put(value.getProjectName(), (Object)value.name());
        }
        return json;
    }

    public Integer createEngineering(final DpEngineering engineering) {
        if (engineering.getId() == null) {
            this.dpEngineeringMapper.insert(engineering);
        }
        else {
            this.dpEngineeringMapper.updateById(engineering);
        }
        return engineering.getId();
    }

    public String deleteEngineering(final Integer id) {
        this.dpEngineeringMapper.deleteById(id);
        return "\u5220\u9664\u6210\u529f!";
    }

    public List<DpEngineering> getEngineeringList() {
        return this.dpEngineeringMapper.selectList();
    }

    public DpEngineeringCost getRate(final Integer engineeringId) {
        return this.dpEngineeringCostMapper.selectByEngineeringId(engineeringId);
    }

    public JSONObject getTotalCost(final Integer engineeringId) {
        final JSONObject json = new JSONObject();
        final List<LadCost> ladList = this.ladCostMapper.selectById(engineeringId);
        final List<CiEarthStoneWork> ciEarthStoneWorks = this.ciEarthStoneWorkMapper.selectByEngineeringId(engineeringId);
        final List<CiRoadWork> ciRoadWorks = this.ciRoadWorkMapper.selectByEngineeringId(engineeringId);
        final List<CiBridgeWork> ciBridgeWorks = this.ciBridgeWorkMapper.selectByEngineeringId(engineeringId);
        final List<CiLandscapeGreeningWork> ciLandscapeGreeningWorks = this.ciLandscapeGreeningWorkMapper.selectByEngineeringId(engineeringId);
        final List<CiPublicSupportingFacilities> ciPublicSupportingFacilities = this.ciPublicSupportingFacilitiesMapper.selectByEngineeringId(engineeringId);
        final List<CiRiverRegulation> ciRiverRegulations = this.ciRiverRegulationMapper.selectByEngineeringId(engineeringId);
        final List<CiPlacementHouseBuilding> ciPlacementHouseBuildings = this.ciPlacementHouseBuildingMapper.selectByEngineeringId(engineeringId);
        json.put("LAD", (Object)ladList);
        json.put("EARTH_STONE", (Object)ciEarthStoneWorks);
        json.put("ROAD", (Object)ciRoadWorks);
        json.put("BRIDGE", (Object)ciBridgeWorks);
        json.put("LANDSCAPE", (Object)ciLandscapeGreeningWorks);
        json.put("FACILITIES", (Object)ciPublicSupportingFacilities);
        json.put("RIVER", (Object)ciRiverRegulations);
        json.put("PLACEMENT_HOUSE", (Object)ciPlacementHouseBuildings);
        final DpEngineeringCost dpEngineeringCost = this.dpEngineeringCostMapper.selectByEngineeringId(engineeringId);
        Double administrativeExpenseRate = 0.0;
        Double financialExpenseRate = 0.0;
        Double otherFeeRate;
        Double budgetReserveRate;
        Double ladUnforeseenRate;
        if (dpEngineeringCost != null) {
            otherFeeRate = dpEngineeringCost.getOtherFeeRate();
            budgetReserveRate = dpEngineeringCost.getBudgetReserveRate();
            administrativeExpenseRate = dpEngineeringCost.getAdministrativeExpenseRate();
            financialExpenseRate = dpEngineeringCost.getFinancialExpenseRate();
            ladUnforeseenRate = dpEngineeringCost.getLadUnforeseenRate();
        }
        else {
            otherFeeRate = RateEnum.OTHER_FEE_RATE.getRate();
            budgetReserveRate = RateEnum.BUDGET_RESERVE_RATE.getRate();
            ladUnforeseenRate = RateEnum.LAD_UNFORESEEN_RATE.getRate();
        }
        final Double ciCost = this.dpEngineeringMapper.selectCiCost(engineeringId);
        Double otherFee = this.dpOtherCostMapper.selectTypeTotalCost(engineeringId, CostTypeEnum.OTHER_FEE.getTypeId());
        if (otherFee == null) {
            otherFee = 0.0;
        }
        final BigDecimal add1 = new BigDecimal(Double.toString(ciCost)).add(new BigDecimal(Double.toString(otherFee)));
        Double budgetReserve = this.dpOtherCostMapper.selectTypeTotalCost(engineeringId, CostTypeEnum.BUDGET_RESERVE.getTypeId());
        if (budgetReserve == null) {
            budgetReserve = 0.0;
        }
        final Double ladCost = DecimalUtil.ladCostDecimal(ladList, ladUnforeseenRate);
        final BigDecimal add2 = add1.add(new BigDecimal(Double.toString(budgetReserve))).add(new BigDecimal(Double.toString(ladCost)));
        Double administrativeExpense = this.dpOtherCostMapper.selectTypeTotalCost(engineeringId, CostTypeEnum.ADMINISTRATIVE_EXPENSE.getTypeId());
        if (administrativeExpense == null) {
            administrativeExpense = 0.0;
        }
        final BigDecimal add3 = add2.add(new BigDecimal(Double.toString(administrativeExpense)));
        final Double staticInvestment = add3.doubleValue();
        Double financialExpense = this.dpOtherCostMapper.selectTypeTotalCost(engineeringId, CostTypeEnum.FINANCIAL_EXPENSE.getTypeId());
        if (financialExpense == null) {
            financialExpense = 0.0;
        }
        final Double total = add3.add(new BigDecimal(Double.toString(financialExpense))).doubleValue();
        final DpEngineeringCost engineeringCost = new DpEngineeringCost();
        engineeringCost.setEngineeringId(engineeringId).setConstructionInstallationCost(ciCost).setOtherFee(otherFee).setBudgetReserve(budgetReserve).setLadFee(ladCost).setAdministrativeExpense(administrativeExpense).setStaticInvestment(staticInvestment).setFinancialExpense(financialExpense).setTotalInvestment(total).setOtherFeeRate(otherFeeRate).setBudgetReserveRate(budgetReserveRate).setAdministrativeExpenseRate(administrativeExpenseRate).setFinancialExpenseRate(financialExpenseRate).setLadUnforeseenRate(ladUnforeseenRate);
        if (dpEngineeringCost == null) {
            this.dpEngineeringCostMapper.insertInfo(engineeringCost);
        }
        else {
            this.dpEngineeringCostMapper.updateInfo(engineeringCost);
        }
        json.put("TOTAL", (Object)engineeringCost);
        final DpEngineering dpEngineering = this.dpEngineeringMapper.selectById(engineeringId);
        json.put("engineering", (Object)dpEngineering);
        return json;
    }

    public Integer changeTotalCost(final DpEngineeringCost dpEngineeringCost) {
        return this.dpEngineeringCostMapper.updateInfo(dpEngineeringCost);
    }

    public void outExcel(final Integer engineeringId, final HttpServletResponse httpServletResponse) {
        this.outPutExcel(this.getTotalCost(engineeringId), httpServletResponse);
    }

    public void outPutExcel(final JSONObject jsonObject, final HttpServletResponse httpServletResponse) {
        final DpEngineering engineering = (DpEngineering)jsonObject.get((Object)"engineering");
        final String fileName = engineering.getEngineeringName() + "\u9879\u76ee\u6295\u8d44\u4f30\u7b97\u8868";
        final String temporaryFile = System.currentTimeMillis() + "";
        try {
            final List<DpExcelTemplate> totalTitle = this.dpExcelTemplateMapper.selectInfo(0, 0);
            final List<String> totalName = new ArrayList<String>();
            for (final DpExcelTemplate dpExcelTemplate : totalTitle) {
                totalName.add(dpExcelTemplate.getName());
            }
            //ExcelXlsxPoiUtils.writeExcelTitle(FilePathEnum.EXCEL_PATH.getPath(), temporaryFile, "\u603b\u8868", totalName, fileName, false);
            final List<List<Object>> list = new ArrayList<List<Object>>();
            final List<Object> objectList = new ArrayList<Object>();
            final DpEngineeringCost totalCost = (DpEngineeringCost)jsonObject.get((Object)"TOTAL");
            objectList.add(NumToCapitalUtil.changeCN("1"));
            objectList.add("\u5efa\u5b89\u5de5\u7a0b\u8d39");
            objectList.add("\u4e07\u5143");
            objectList.add("");
            objectList.add("");
            objectList.add(totalCost.getConstructionInstallationCost());
            objectList.add("");
            list.add(objectList);
            this.writeExcel(list, true, temporaryFile);
            objectList.clear();
            list.clear();
            this.ciExcel(jsonObject, temporaryFile);
            objectList.add(NumToCapitalUtil.changeCN("2"));
            objectList.add("\u5de5\u7a0b\u5efa\u8bbe\u5176\u4ed6\u8d39");
            objectList.add("\u4e07\u5143");
            objectList.add("");
            objectList.add(totalCost.getOtherFeeRate() * 100.0 + "%");
            objectList.add(totalCost.getOtherFee());
            objectList.add("");
            list.add(objectList);
            this.writeExcel(list, true, temporaryFile);
            objectList.clear();
            list.clear();
            objectList.add(NumToCapitalUtil.changeCN("3"));
            objectList.add("\u9884\u5907\u8d39");
            objectList.add("\u4e07\u5143");
            objectList.add("");
            objectList.add(totalCost.getBudgetReserveRate() * 100.0 + "%");
            objectList.add(totalCost.getBudgetReserve());
            objectList.add("");
            list.add(objectList);
            this.writeExcel(list, true, temporaryFile);
            objectList.clear();
            list.clear();
            objectList.add(NumToCapitalUtil.changeCN("4"));
            objectList.add("\u5f81\u5730\u62c6\u8fc1\u8d39");
            objectList.add("\u4e07\u5143");
            objectList.add("");
            objectList.add("");
            objectList.add(totalCost.getLadFee());
            objectList.add("");
            list.add(objectList);
            this.writeExcel(list, true, temporaryFile);
            objectList.clear();
            list.clear();
            this.ladExcel(jsonObject, temporaryFile);
            objectList.add(NumToCapitalUtil.changeCN("5"));
            objectList.add("\u7ba1\u7406\u8d39\u7528");
            objectList.add("\u4e07\u5143");
            objectList.add("");
            objectList.add("");
            objectList.add(totalCost.getAdministrativeExpense());
            objectList.add("");
            list.add(objectList);
            this.writeExcel(list, true, temporaryFile);
            objectList.clear();
            list.clear();
            objectList.add(NumToCapitalUtil.changeCN("6"));
            objectList.add("\u9879\u76ee\u9759\u6001\u6295\u8d44");
            objectList.add("\u4e07\u5143");
            objectList.add("");
            objectList.add("");
            objectList.add(totalCost.getStaticInvestment());
            objectList.add("");
            list.add(objectList);
            this.writeExcel(list, true, temporaryFile);
            objectList.clear();
            list.clear();
            objectList.add(NumToCapitalUtil.changeCN("7"));
            objectList.add("\u8d22\u52a1\u8d39");
            objectList.add("\u4e07\u5143");
            objectList.add("");
            objectList.add("");
            objectList.add(totalCost.getFinancialExpense());
            objectList.add("");
            list.add(objectList);
            this.writeExcel(list, true, temporaryFile);
            objectList.clear();
            list.clear();
            objectList.add(NumToCapitalUtil.changeCN("8"));
            objectList.add("\u9879\u76ee\u52a8\u6001\u603b\u6295\u8d44");
            objectList.add("\u4e07\u5143");
            objectList.add("");
            objectList.add("");
            objectList.add(totalCost.getTotalInvestment());
            objectList.add("");
            list.add(objectList);
            final File file = this.writeExcel(list, true, temporaryFile);
            objectList.clear();
            list.clear();
            //ExcelXlsxPoiUtils.out(file, fileName, httpServletResponse);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public File writeExcel(final List<List<Object>> value, final boolean isTitle, final String temporaryFile) {
        try {
           // final File file = ExcelXlsxPoiUtils.writeExcelData(FilePathEnum.EXCEL_PATH.getPath(), temporaryFile, "\u603b\u8868", value, isTitle);
            return new File("");
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void ladExcel(final JSONObject jsonObject, final String temporaryFile) {
        final List<List<Object>> list = new ArrayList<List<Object>>();
        final List<LadCost> ladList = (List<LadCost>)jsonObject.get((Object)"LAD");
        final DpEngineeringCost total = (DpEngineeringCost)jsonObject.get((Object)"TOTAL");
        if (ladList != null) {
            for (int i = 0; i < ladList.size(); ++i) {
                final List<Object> objectList = new ArrayList<Object>();
                objectList.add(i + 1);
                objectList.add(ladList.get(i).getLadName());
                objectList.add(ladList.get(i).getUnitAmount());
                objectList.add(ladList.get(i).getAmount());
                objectList.add(ladList.get(i).getUnitPrice());
                objectList.add(ladList.get(i).getTotalCost());
                objectList.add("");
                list.add(objectList);
            }
            this.writeExcel(list, true, temporaryFile);
            final double ladUnforeseen = new BigDecimal(Double.toString(total.getLadFee())).divide(new BigDecimal(Double.toString(1.0)).add(new BigDecimal(Double.toString(total.getLadUnforeseenRate()))), 2, 4).multiply(new BigDecimal(Double.toString(total.getLadUnforeseenRate()))).doubleValue();
            final List<Object> objectList2 = new ArrayList<Object>();
            objectList2.add(ladList.size() + 1);
            objectList2.add("\u4e0d\u53ef\u9884\u89c1\u8d39");
            objectList2.add("\u4e07\u5143");
            objectList2.add("");
            objectList2.add(total.getLadUnforeseenRate() * 100.0 + "%");
            objectList2.add(ladUnforeseen);
            objectList2.add("");
            list.add(objectList2);
        }
    }

    public void ciExcel(final JSONObject jsonObject, final String temporaryFile) {
        final List<List<Object>> list = new ArrayList<List<Object>>();
        final List<Object> objectList = new ArrayList<Object>();
        objectList.add("(" + NumToCapitalUtil.changeCN("1") + ")");
        objectList.add("\u573a\u5730\u5e73\u6574\u53ca\u571f\u77f3\u65b9");
        objectList.add("m?");
        objectList.add("");
        objectList.add("");
        objectList.add("");
        objectList.add("");
        list.add(objectList);
        this.writeExcel(list, true, temporaryFile);
        objectList.clear();
        list.clear();
        final List<CiEarthStoneWork> earthStoneWork = (List<CiEarthStoneWork>)jsonObject.get((Object)ProjectNameEnum.EARTH_STONE.name());
        if (earthStoneWork != null) {
            for (int i = 0; i < earthStoneWork.size(); ++i) {
                final List<Object> objectList2 = new ArrayList<Object>();
                objectList2.add(i + 1);
                objectList2.add(earthStoneWork.get(i).getProjectName());
                objectList2.add("m?");
                objectList2.add(earthStoneWork.get(i).getWorkAmount());
                objectList2.add(earthStoneWork.get(i).getUnitPrice());
                objectList2.add(earthStoneWork.get(i).getProjectCost());
                objectList2.add("");
                list.add(objectList2);
            }
            this.writeExcel(list, false, temporaryFile);
            list.clear();
        }
        objectList.add("(" + NumToCapitalUtil.changeCN("2") + ")");
        objectList.add("\u9053\u8def\u5de5\u7a0b");
        objectList.add("m?");
        objectList.add("");
        objectList.add("");
        objectList.add("");
        objectList.add("");
        list.add(objectList);
        this.writeExcel(list, true, temporaryFile);
        objectList.clear();
        list.clear();
        final List<CiRoadWork> ciRoadWork = (List<CiRoadWork>)jsonObject.get((Object)ProjectNameEnum.ROAD.name());
        if (ciRoadWork != null) {
            for (int j = 0; j < ciRoadWork.size(); ++j) {
                final Integer roadGrade = ciRoadWork.get(j).getRoadGrade();
                final List<Object> objectList3 = new ArrayList<Object>();
                objectList3.add(j + 1);
                objectList3.add(ciRoadWork.get(j).getProjectName());
                objectList3.add("m?");
                objectList3.add(ciRoadWork.get(j).getRoadArea());
                objectList3.add(ciRoadWork.get(j).getUnitPrice());
                objectList3.add(ciRoadWork.get(j).getProjectCost());
                switch (roadGrade) {
                    case 1: {
                        objectList3.add("\u4e3b\u5e72\u8def");
                        break;
                    }
                    case 2: {
                        objectList3.add("\u6b21\u5e72\u8def");
                        break;
                    }
                    case 3: {
                        objectList3.add("\u652f\u8def");
                        break;
                    }
                }
                list.add(objectList3);
            }
            this.writeExcel(list, false, temporaryFile);
            list.clear();
        }
        objectList.add("(" + NumToCapitalUtil.changeCN("3") + ")");
        objectList.add("\u6865\u6881\u5de5\u7a0b");
        objectList.add("m?");
        objectList.add("");
        objectList.add("");
        objectList.add("");
        objectList.add("");
        list.add(objectList);
        this.writeExcel(list, true, temporaryFile);
        objectList.clear();
        list.clear();
        final List<CiBridgeWork> ciBridgeWorks = (List<CiBridgeWork>)jsonObject.get((Object)ProjectNameEnum.BRIDGE.name());
        if (ciBridgeWorks != null) {
            for (int k = 0; k < ciBridgeWorks.size(); ++k) {
                final List<Object> objectList3 = new ArrayList<Object>();
                objectList3.add(k + 1);
                objectList3.add(ciBridgeWorks.get(k).getProjectName());
                objectList3.add("m?");
                objectList3.add(ciBridgeWorks.get(k).getBridgeArea());
                objectList3.add(ciBridgeWorks.get(k).getUnitPrice());
                objectList3.add(ciBridgeWorks.get(k).getProjectCost());
                final Integer bridgeGrade = ciBridgeWorks.get(k).getBridgeGrade();
                switch (bridgeGrade) {
                    case 1: {
                        objectList3.add("\u5927\u6865");
                        break;
                    }
                    case 2: {
                        objectList3.add("\u4e2d\u6865");
                        break;
                    }
                    case 3: {
                        objectList3.add("\u5c0f\u6865");
                        break;
                    }
                }
            }
            this.writeExcel(list, false, temporaryFile);
            list.clear();
        }
        objectList.add("(" + NumToCapitalUtil.changeCN("4") + ")");
        objectList.add("\u516c\u5efa\u914d\u5957\u8bbe\u65bd");
        objectList.add("m?");
        objectList.add("");
        objectList.add("");
        objectList.add("");
        objectList.add("");
        list.add(objectList);
        this.writeExcel(list, true, temporaryFile);
        objectList.clear();
        list.clear();
        final List<CiPublicSupportingFacilities> facilities = (List<CiPublicSupportingFacilities>)jsonObject.get((Object)ProjectNameEnum.FACILITIES.name());
        if (facilities != null) {
            for (int l = 0; l < facilities.size(); ++l) {
                final List<Object> objectList4 = new ArrayList<Object>();
                objectList4.add(l + 1);
                objectList4.add(facilities.get(l).getProjectName());
                objectList4.add("m?");
                objectList4.add(facilities.get(l).getBuildingArea());
                objectList4.add(facilities.get(l).getUnitPrice());
                objectList4.add(facilities.get(l).getProjectCost());
                objectList4.add("");
                list.add(objectList4);
            }
            this.writeExcel(list, false, temporaryFile);
            list.clear();
        }
        objectList.add("(" + NumToCapitalUtil.changeCN("5") + ")");
        objectList.add("\u516c\u56ed\u53ca\u7eff\u5730");
        objectList.add("m?");
        objectList.add("");
        objectList.add("");
        objectList.add("");
        objectList.add("");
        list.add(objectList);
        this.writeExcel(list, true, temporaryFile);
        objectList.clear();
        list.clear();
        final List<CiLandscapeGreeningWork> landscape = (List<CiLandscapeGreeningWork>)jsonObject.get((Object)ProjectNameEnum.LANDSCAPE.name());
        if (landscape != null) {
            for (int m = 0; m < landscape.size(); ++m) {
                final List<Object> objectList5 = new ArrayList<Object>();
                objectList5.add(m + 1);
                objectList5.add(landscape.get(m).getProjectName());
                objectList5.add("m?");
                objectList5.add(landscape.get(m).getWorkAmount());
                objectList5.add(landscape.get(m).getUnitPrice());
                objectList5.add(landscape.get(m).getProjectCost());
                objectList5.add("");
                list.add(objectList5);
            }
            this.writeExcel(list, false, temporaryFile);
            list.clear();
        }
        objectList.add("(" + NumToCapitalUtil.changeCN("6") + ")");
        objectList.add("\u6cb3\u9053\u6cbb\u7406");
        objectList.add("km");
        objectList.add("");
        objectList.add("");
        objectList.add("");
        objectList.add("");
        list.add(objectList);
        this.writeExcel(list, true, temporaryFile);
        objectList.clear();
        list.clear();
        final List<CiRiverRegulation> riverRegulations = (List<CiRiverRegulation>)jsonObject.get((Object)ProjectNameEnum.RIVER.name());
        if (riverRegulations != null) {
            for (int i2 = 0; i2 < riverRegulations.size(); ++i2) {
                final List<Object> objectList6 = new ArrayList<Object>();
                objectList6.add(i2 + 1);
                objectList6.add(riverRegulations.get(i2).getProjectName());
                objectList6.add("km");
                objectList6.add(riverRegulations.get(i2).getRiverArea());
                objectList6.add(riverRegulations.get(i2).getUnitPrice());
                objectList6.add(riverRegulations.get(i2).getProjectCost());
                objectList6.add("");
                list.add(objectList6);
            }
            this.writeExcel(list, false, temporaryFile);
            list.clear();
        }
        objectList.add("(" + NumToCapitalUtil.changeCN("7") + ")");
        objectList.add("\u5b89\u7f6e\u623f\u5efa\u8bbe");
        objectList.add("m?");
        objectList.add("");
        objectList.add("");
        objectList.add("");
        objectList.add("");
        list.add(objectList);
        this.writeExcel(list, true, temporaryFile);
        objectList.clear();
        list.clear();
        final List<CiPlacementHouseBuilding> houseBuildings = (List<CiPlacementHouseBuilding>)jsonObject.get((Object)ProjectNameEnum.PLACEMENT_HOUSE.name());
        if (houseBuildings != null) {
            for (int i3 = 0; i3 < houseBuildings.size(); ++i3) {
                final List<Object> objectList7 = new ArrayList<Object>();
                objectList7.add(i3 + 1);
                objectList7.add(houseBuildings.get(i3).getProjectName());
                objectList7.add("m?");
                objectList7.add(houseBuildings.get(i3).getBuildingArea());
                objectList7.add(houseBuildings.get(i3).getUnitPrice());
                objectList7.add(houseBuildings.get(i3).getProjectCost());
                objectList7.add("");
                list.add(objectList7);
            }
            this.writeExcel(list, false, temporaryFile);
        }
    }
}
