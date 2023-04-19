package com.ruoyi.web.controller.jianguan.business.contract;

import com.github.pagehelper.PageInfo;
import com.ruoyi.common.core.domain.object.ResponseBase;
import com.ruoyi.common.core.domain.vo.EnumsVo;
import com.ruoyi.jianguan.business.contract.domain.dto.BuildContractPageDTO;
import com.ruoyi.jianguan.business.contract.domain.dto.BuildContractSaveDTO;
import com.ruoyi.jianguan.business.contract.domain.entity.BuildContract;
import com.ruoyi.jianguan.business.contract.domain.vo.BuildContractDetailVo;
import com.ruoyi.jianguan.business.contract.domain.vo.BuildContractPageVo;
import com.ruoyi.jianguan.business.contract.service.BuildContractService;
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
 * 施工专业分包合同表
 *
 * @author qiaoxulin
 * @date 2022-05-18
 */
@Api(value = "施工专业分包合同表", tags = {"施工专业分包合同表"})
@RestController
@RequestMapping("/web/api/v1/buildContract")
public class BuildContractController {


    @Autowired
    private BuildContractService buildContractService;

    @Autowired
    private FlowApiService flowApiService;

    /**
     * 新增或者更新施工专业分包合同表数据
     *
     * @param
     */
    @PostMapping(value = "/addOrUpdate", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "新增或者更新施工专业分包合同表数据")
    public ResponseBase addOrUpdate(@RequestBody @ApiParam(name = "saveDto") BuildContractSaveDTO saveDto) {
        return buildContractService.addOrUpdate(saveDto);
    }


    /**
     * 通过id删除一条施工专业分包合同表 数据
     *
     * @param id
     */
    @GetMapping(value = "/id", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "通过id删除一条施工专业分包合同表数据")
    @Transactional(rollbackFor = Exception.class)
    public boolean removeById(@ApiParam(name = "id") Long id) {
        flowApiService.deleteProcessInstanceByBusinessKey(String.valueOf(id));
        return buildContractService.removeById(id);
    }


    /**
     * 通过id获取一条施工专业分包合同表数据
     *
     * @param id
     */
    @GetMapping(value = "/detail/id", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "通过id获取一条施工专业分包合同表数据")
    public BuildContractDetailVo getById(@ApiParam(name = "id") Long id) {
        return buildContractService.getInfoById(id);
    }


    /**
     * 分页查询施工专业分包合同表 数据
     *
     * @param pageDto
     */
    @PostMapping(value = "/page", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "分页查询施工专业分包合同表数据")
    public PageInfo<BuildContractPageVo> page(@RequestBody BuildContractPageDTO pageDto) {
        return buildContractService.getPageInfo(pageDto);
    }


    /**
     * 查询所有施工专业分包合同数据
     */
    @GetMapping(value = "/list", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "查询所有施工专业分包合同数据")
    public List<BuildContract> list() {
        return buildContractService.list();
    }

    /**
     * 获取拟分包工程部位枚举
     */
    @GetMapping(value = "/buildContractPart/enums", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "获取拟分包工程部位枚举")
    public List<EnumsVo> getBuildContractPartEnum() {
        return buildContractService.getBuildContractPartEnum();
    }


    /**
     * 施工专业分包合同导出
     *
     * @param pageDto
     */
    @PostMapping(value = "/export", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "施工专业分包合同导出")
    public void export(@RequestBody @ApiParam(name = "pageDto") BuildContractPageDTO pageDto, HttpServletResponse response) {
        buildContractService.export(pageDto, response);
    }
}
