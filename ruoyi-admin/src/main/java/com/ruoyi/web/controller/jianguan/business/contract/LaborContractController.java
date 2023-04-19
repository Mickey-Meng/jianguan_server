package com.ruoyi.web.controller.jianguan.business.contract;

import com.github.pagehelper.PageInfo;
import com.ruoyi.common.core.domain.object.ResponseBase;
import com.ruoyi.jianguan.business.contract.domain.dto.LaborContractPageDTO;
import com.ruoyi.jianguan.business.contract.domain.dto.LaborContractSaveDTO;
import com.ruoyi.jianguan.business.contract.domain.entity.LaborContract;
import com.ruoyi.jianguan.business.contract.domain.vo.LaborContractDetailVo;
import com.ruoyi.jianguan.business.contract.domain.vo.LaborContractPageVo;
import com.ruoyi.jianguan.business.contract.service.LaborContractService;
import com.ruoyi.flowable.service.FlowApiService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 劳务分包合同
 *
 * @author qiaoxulin
 * @date 2022-05-20
 */
@Api(value = "劳务分包合同", tags = {"劳务分包合同"})
@RestController
@RequestMapping("/web/api/v1/laborContract")
public class LaborContractController {


    @Autowired
    private LaborContractService laborContractService;
    @Autowired
    private FlowApiService flowApiService;

    /**
     * 新增或者更新劳务分包合同数据
     *
     * @param
     */
    @PostMapping(value = "/addOrUpdate", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "新增或者更新劳务分包合同数据")
    public ResponseBase addOrUpdate(@RequestBody LaborContractSaveDTO saveDto) {
        return laborContractService.addOrUpdate(saveDto);
    }


    /**
     * 通过id删除一条劳务分包合同 数据
     *
     * @param id
     */
    @GetMapping(value = "/id", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "通过id删除一条劳务分包合同数据")
    @Transactional(rollbackFor = Exception.class)
    public boolean removeById(@ApiParam(name = "id") Long id) {
        flowApiService.deleteProcessInstanceByBusinessKey(String.valueOf(id));
        return laborContractService.removeById(id);
    }


    /**
     * 通过id获取一条劳务分包合同数据
     *
     * @param id
     */
    @GetMapping(value = "/detail/id", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "通过id获取一条劳务分包合同数据")
    public LaborContractDetailVo getById(@ApiParam(name = "id") Long id) {
        return laborContractService.getInfoById(id);
    }


    /**
     * 分页查询劳务分包合同数据
     *
     * @param pageDto
     */
    @PostMapping(value = "/page", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "分页查询劳务分包合同数据")
    public PageInfo<LaborContractPageVo> page(@RequestBody LaborContractPageDTO pageDto) {
        return laborContractService.getPageInfo(pageDto);
    }

    /**
     * 查询所有劳务分包合同数据
     */
    @GetMapping(value = "/list", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "查询所有劳务分包合同数据")
    public List<LaborContract> list() {
        return laborContractService.list();
    }

    /**
     * 劳务分包合同列表（不分页）
     */
    @PostMapping(value = "/list", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "劳务分包合同列表（不分页）")
    public List<LaborContract> getList() {
        return laborContractService.getList();
    }

    /**
     * 劳务分包合同导出
     *
     * @param pageDto
     */
    @PostMapping(value = "/export", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "劳务分包合同导出")
    public void export(@RequestBody @ApiParam(name = "pageDto") LaborContractPageDTO pageDto, HttpServletResponse response) {
        laborContractService.export(pageDto, response);
    }

}
