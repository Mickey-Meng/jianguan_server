package com.ruoyi.web.controller.jianguan.business.contract;

import com.github.pagehelper.PageInfo;
import com.ruoyi.common.core.domain.object.ResponseBase;
import com.ruoyi.jianguan.business.contract.domain.dto.ConstructionPrototypePageDTO;
import com.ruoyi.jianguan.business.contract.domain.dto.ConstructionPrototypeSaveDTO;
import com.ruoyi.jianguan.business.contract.domain.entity.ConstructionPrototype;
import com.ruoyi.jianguan.business.contract.domain.vo.ConstructionPrototypeDetailVo;
import com.ruoyi.jianguan.business.contract.domain.vo.ConstructionPrototypePageVo;
import com.ruoyi.jianguan.business.contract.service.ConstructionPrototypeService;
import com.ruoyi.jianguan.business.contract.service.ConstructionPrototypeService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController
@RequestMapping("/web/api/v1/construction_prototype")
public class ConstructionPrototypeController {

    @Autowired
    private ConstructionPrototypeService constructionPrototypeService;


    @PostMapping(value = "/addOrUpdate", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "新增或者更新")
    public ResponseBase addOrUpdate(@RequestBody @ApiParam(name = "saveDto") ConstructionPrototypeSaveDTO saveDto) {
        return constructionPrototypeService.addOrUpdate(saveDto);
    }

    @GetMapping(value = "/id", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "通过id删除一条数据")
    @Transactional(rollbackFor = Exception.class)
    public boolean removeById(@ApiParam(name = "id") Long id) {
        return constructionPrototypeService.removeById(id);
    }

    @GetMapping(value = "/detail/id", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "通过id获取一条数据")
    public ConstructionPrototypeDetailVo getById(@ApiParam(name = "id") Long id) {
        return constructionPrototypeService.getInfoById(id);
    }

    @PostMapping(value = "/page", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "分页查询数据")
    public PageInfo<ConstructionPrototypePageVo> page(@RequestBody ConstructionPrototypePageDTO pageDto) {
        PageInfo<ConstructionPrototypePageVo> pageInfo = constructionPrototypeService.getPageInfo(pageDto);
        log.info("ContractPaymentPageVo: {}", pageInfo);
        return pageInfo;
    }

    @GetMapping(value = "/list", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "查询所有数据")
    public List<ConstructionPrototype> list() {
        return constructionPrototypeService.list();
    }

}