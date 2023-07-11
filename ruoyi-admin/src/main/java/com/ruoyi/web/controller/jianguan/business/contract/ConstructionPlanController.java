package com.ruoyi.web.controller.jianguan.business.contract;

import com.github.pagehelper.PageInfo;
import com.ruoyi.common.core.domain.object.ResponseBase;
import com.ruoyi.jianguan.business.contract.domain.dto.ConstructionPlanPageDTO;
import com.ruoyi.jianguan.business.contract.domain.dto.ConstructionPlanSaveDTO;
import com.ruoyi.jianguan.business.contract.domain.entity.ConstructionPlan;
import com.ruoyi.jianguan.business.contract.domain.vo.ConstructionPlanDetailVo;
import com.ruoyi.jianguan.business.contract.domain.vo.ConstructionPlanPageVo;
import com.ruoyi.jianguan.business.contract.service.ConstructionPlanService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController
@RequestMapping("/web/api/v1/construction_plan")
public class ConstructionPlanController {

    @Autowired
    private ConstructionPlanService constructionPlanService;


    @PostMapping(value = "/addOrUpdate", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "新增或者更新")
    public ResponseBase addOrUpdate(@RequestBody @ApiParam(name = "saveDto") ConstructionPlanSaveDTO saveDto) {
        saveDto.setReportStatus(-1);
        String responsiblePerson = saveDto.getResponsiblePerson();
        String[] split = responsiblePerson.split("&");
        saveDto.setResponsiblePerson(split[1]);
        saveDto.setResponsiblePersonId(split[0]);
        return constructionPlanService.addOrUpdate(saveDto);
    }

    @GetMapping(value = "/id", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "通过id删除一条数据")
    @Transactional(rollbackFor = Exception.class)
    public boolean removeById(@ApiParam(name = "id") Long id) {
        return constructionPlanService.removeById(id);
    }

    @GetMapping(value = "/detail/id", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "通过id获取一条数据")
    public ConstructionPlanDetailVo getById(@ApiParam(name = "id") Long id) {
        return constructionPlanService.getInfoById(id);
    }

    @PostMapping(value = "/page", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "分页查询数据")
    public PageInfo<ConstructionPlanPageVo> page(@RequestBody ConstructionPlanPageDTO pageDto) {
        PageInfo<ConstructionPlanPageVo> pageInfo = constructionPlanService.getPageInfo(pageDto);
        log.info("ContractPaymentPageVo: {}", pageInfo);
        return pageInfo;
    }

    @GetMapping(value = "/list", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "查询所有数据")
    public List<ConstructionPlan> list() {
        return constructionPlanService.list();
    }

}