package com.ruoyi.web.controller.jianguan.business.contract;

import com.github.pagehelper.PageInfo;
import com.ruoyi.common.core.domain.object.ResponseBase;
import com.ruoyi.jianguan.business.contract.domain.dto.ComeGoMoneyPageDTO;
import com.ruoyi.jianguan.business.contract.domain.dto.ComeGoMoneySaveDTO;
import com.ruoyi.jianguan.business.contract.domain.vo.ComeGoMoneyDetailVo;
import com.ruoyi.jianguan.business.contract.service.ComeGoMoneyService;
import com.ruoyi.flowable.service.FlowApiService;
import com.ruoyi.jianguan.business.contract.domain.vo.ComeGoMoneyPageVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
* 往来款
* @author qiaoxulin
* @date 2022-05-27
*/
@Api(value = "往来款", tags = {"往来款"})
@RestController
@RequestMapping("/web/api/v1/comeGoMoney")
public class ComeGoMoneyController {


    @Autowired
    private ComeGoMoneyService comeGoMoneyService;
    @Autowired
    private FlowApiService flowApiService;

    /**
    * 新增或者更新往来款数据
    * @param
    */
    @PostMapping(value = "/addOrUpdate", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "新增或者更新往来款数据")
    public ResponseBase addOrUpdate(@RequestBody @ApiParam(name = "saveDto") ComeGoMoneySaveDTO saveDto) {
        return comeGoMoneyService.addOrUpdate(saveDto);
    }


    /**
    * 通过id删除一条往来款 数据
    * @param id
    */
    @GetMapping(value = "/id", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "通过id删除一条往来款数据")
    @Transactional(rollbackFor = Exception.class)
    public boolean removeById(@ApiParam(name = "id") Long id)  {
        flowApiService.deleteProcessInstanceByBusinessKey(String.valueOf(id));
        return comeGoMoneyService.removeById(id);
    }


    /**
    * 通过id获取一条往来款数据
    * @param id
    */
    @GetMapping(value = "/detail/id", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "通过id获取一条往来款数据")
    public ComeGoMoneyDetailVo getById(@ApiParam(name = "id") Long id)  {
        return comeGoMoneyService.getInfoById(id);
    }


    /**
    * 分页查询往来款 数据
    * @param pageDto
    */
    @PostMapping(value = "/page", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "分页查询往来款数据")
    public PageInfo<ComeGoMoneyPageVo> page(@RequestBody ComeGoMoneyPageDTO pageDto)  {
        return comeGoMoneyService.getPageInfo(pageDto);
    }

    /**
     * 往来款导出
     *
     * @param pageDto
     */
    @PostMapping(value = "/export", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "往来款导出")
    public void export(@RequestBody @ApiParam(name = "pageDto") ComeGoMoneyPageDTO pageDto, HttpServletResponse response) {
        comeGoMoneyService.export(pageDto, response);
    }

}
