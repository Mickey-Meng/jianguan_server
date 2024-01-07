package com.ruoyi.web.controller.jianguan.business.contract;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageInfo;
import com.ruoyi.common.core.domain.object.ResponseBase;
import com.ruoyi.jianguan.business.contract.domain.dto.MaterialBrandReportPageDTO;
import com.ruoyi.jianguan.business.contract.domain.dto.MaterialBrandReportSaveDTO;
import com.ruoyi.jianguan.business.contract.domain.entity.MaterialBrandReport;
import com.ruoyi.jianguan.business.contract.domain.vo.MaterialBrandReportDetailVo;
import com.ruoyi.jianguan.business.contract.domain.vo.MaterialBrandReportPageVo;
import com.ruoyi.jianguan.business.contract.service.MaterialBrandReportService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController
@RequestMapping("/web/api/v1/material_brand_report")
public class MaterialBrandReportController {

    @Autowired
    private MaterialBrandReportService materialBrandReportService;


    @PostMapping(value = "/addOrUpdate", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "新增或者更新")
    public ResponseBase addOrUpdate(@RequestBody @ApiParam(name = "saveDto") MaterialBrandReportSaveDTO saveDto) {
        String materialCategory = saveDto.getMaterialCategory();
        if(StrUtil.isNotBlank(materialCategory)) {
            String[] split = materialCategory.split(",");
            if(split.length > 1) {
                saveDto.setMaterialCategoryCode(split[0]);
                saveDto.setMaterialCategory(split[1]);
            }
        }
        return materialBrandReportService.addOrUpdateMaterialBrandReport(saveDto);
    }

    @GetMapping(value = "/id", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "通过id删除一条数据")
    @Transactional(rollbackFor = Exception.class)
    public boolean removeById(@ApiParam(name = "id") Long id) {
        return materialBrandReportService.removeById(id);
    }

    @GetMapping(value = "/detail/id", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "通过id获取一条数据")
    public MaterialBrandReportDetailVo getById(@ApiParam(name = "id") Long id) {
        return materialBrandReportService.getInfoById(id);
    }

    @PostMapping(value = "/page", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "分页查询数据")
    public PageInfo<MaterialBrandReportPageVo> page(@RequestBody MaterialBrandReportPageDTO pageDto) {
        PageInfo<MaterialBrandReportPageVo> pageInfo = materialBrandReportService.getPageInfo(pageDto);
        log.info("ContractPaymentPageVo: {}", pageInfo);
        return pageInfo;
    }

    @GetMapping(value = "/list", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "查询所有数据")
    public List<MaterialBrandReport> list() {
        return materialBrandReportService.list();
    }

}