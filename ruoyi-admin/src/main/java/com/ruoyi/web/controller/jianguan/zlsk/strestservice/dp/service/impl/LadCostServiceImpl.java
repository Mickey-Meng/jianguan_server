package com.ruoyi.web.controller.jianguan.zlsk.strestservice.dp.service.impl;

import com.ruoyi.web.controller.jianguan.zlsk.strestservice.dp.enums.LadAcreageEnum;
import com.ruoyi.web.controller.jianguan.zlsk.strestservice.dp.mapper.*;
import com.ruoyi.web.controller.jianguan.zlsk.strestservice.dp.model.*;
import com.ruoyi.web.controller.jianguan.zlsk.strestservice.dp.model.dto.LadCostInfoDTO;
import com.ruoyi.web.controller.jianguan.zlsk.strestservice.dp.service.IDpEngineeringService;
import com.ruoyi.web.controller.jianguan.zlsk.strestservice.dp.service.ILadCostService;
import com.ruoyi.web.controller.jianguan.zlsk.stsupporter.stctk.stbase.*;
import org.springframework.stereotype.*;
import org.springframework.beans.factory.annotation.*;
import java.util.*;
import com.alibaba.fastjson.*;
import org.springframework.transaction.annotation.*;

@Service
public class LadCostServiceImpl extends StServiceBase implements ILadCostService
{
    @Autowired
    private LadCostMapper ladCostMapper;
    @Autowired
    private LadCostDictionaryMapper ladCostDictionaryMapper;
    @Autowired
    private LadAcreageMapper ladAcreageMapper;
    @Autowired
    private DpEngineeringMapper dpEngineeringMapper;
    @Autowired
    private DpEngineeringCostMapper dpEngineeringCostMapper;
    @Autowired
    private IDpEngineeringService dpEngineeringService;

    @Transactional
    public LadCostInfoDTO getList(final Integer id) {
        final List<LadCost> ladCostList = this.ladCostMapper.selectById(id);
        if (ladCostList.size() < 1) {
            final List<LadCostDictionary> dictionaryList = this.ladCostDictionaryMapper.selectIdList();
            final LadCost ladCost = new LadCost();
            for (final LadCostDictionary ladCostDictionary : dictionaryList) {
                ladCost.setEngineeringId(id).setDictionaryId(ladCostDictionary.getId()).setLadName(ladCostDictionary.getLadName()).setUnitAmount(ladCostDictionary.getUnitAmount()).setUnitPricePerUnit(ladCostDictionary.getUnitPricePerUnit());
                this.ladCostMapper.insert(ladCost);
            }
            this.ladAcreageMapper.insertDefaultValue(id, LadAcreageEnum.CAPITE_COMPENSATED_AREA.getValue(), LadAcreageEnum.PLOT_RATIO.getValue());
        }
        final List<LadCost> lad = this.ladCostMapper.selectById(id);
        final JSONObject json = this.dpEngineeringService.getTotalCost(id);
        final DpEngineeringCost total = (DpEngineeringCost)json.get((Object)"TOTAL");
        final Double ladFee = total.getLadFee();
        final Double ladUnforeseenRate = total.getLadUnforeseenRate();
        final LadAcreage ladAcreage = this.ladAcreageMapper.selectByEngineeringId(id);
        final String site = this.dpEngineeringMapper.selectSite(id);
        return new LadCostInfoDTO().setLadCostList(lad).setLadAcreage(ladAcreage).setSite(site).setTotalCost(ladFee).setLadUnforeseenRate(ladUnforeseenRate);
    }

    @Transactional
    public String keepLadInfo(final LadCostInfoDTO dto) {
        if (dto.getLadAcreage() != null) {
            this.ladAcreageMapper.updateInfo(dto.getLadAcreage());
        }
        if (dto.getLadCostList() != null) {
            for (final LadCost ladCost : dto.getLadCostList()) {
                this.ladCostMapper.updateInfo(ladCost);
            }
        }
        if (dto.getLadUnforeseenRate() != null && dto.getEngineeringId() != null) {
            this.dpEngineeringCostMapper.insertLadRate(dto.getEngineeringId(), dto.getLadUnforeseenRate());
        }
        if (dto.getTotalCost() != null && dto.getEngineeringId() != null) {
            this.dpEngineeringCostMapper.insertLadTotal(dto.getEngineeringId(), dto.getTotalCost());
        }
        return "success";
    }

    @Transactional
    public List<LadCost> addLad(final List<LadCost> ladCost) {
        for (final LadCost cost : ladCost) {
            this.ladCostMapper.insert(cost);
        }
        return ladCost;
    }
}
