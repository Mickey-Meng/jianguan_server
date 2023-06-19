package com.ruoyi.web.controller.jianguan.business.contract;

import com.github.pagehelper.PageInfo;
import com.ruoyi.common.core.domain.object.ResponseBase;
import com.ruoyi.jianguan.business.contract.domain.dto.DailyReportPageDTO;
import com.ruoyi.jianguan.business.contract.domain.dto.DailyReportSaveDTO;
import com.ruoyi.jianguan.business.contract.domain.entity.DailyReport;
import com.ruoyi.jianguan.business.contract.domain.vo.DailyReportDetailVo;
import com.ruoyi.jianguan.business.contract.domain.vo.DailyReportPageVo;
import com.ruoyi.jianguan.business.contract.service.DailyReportService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController
@RequestMapping("/web/api/v1/daily_report")
public class DailyReportController {

    @Autowired
    private DailyReportService contractPaymentService;

    /**
     * 新增或者更新施工专业分包合同表数据
     *
     * @param
     */
    @PostMapping(value = "/addOrUpdate", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "新增或者更新")
    public ResponseBase addOrUpdate(@RequestBody @ApiParam(name = "saveDto") DailyReportSaveDTO saveDto) {
        return contractPaymentService.addOrUpdate(saveDto);
    }


    /**
     * 通过id删除一条施工专业分包合同表 数据
     *
     * @param id
     */
    @GetMapping(value = "/id", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "通过id删除一条数据")
    @Transactional(rollbackFor = Exception.class)
    public boolean removeById(@ApiParam(name = "id") Long id) {
        return contractPaymentService.removeById(id);
    }


    /**
     * 通过id获取一条施工专业分包合同表数据
     *
     * @param id
     */
    @GetMapping(value = "/detail/id", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "通过id获取一条数据")
    public DailyReportDetailVo getById(@ApiParam(name = "id") Long id) {
        return contractPaymentService.getInfoById(id);
    }


    /**
     * 分页查询施工专业分包合同表 数据
     *
     * @param pageDto
     */
    @PostMapping(value = "/page", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "分页查询数据")
    public PageInfo<DailyReportPageVo> page(@RequestBody DailyReportPageDTO pageDto) {
        PageInfo<DailyReportPageVo> pageInfo = contractPaymentService.getPageInfo(pageDto);
        log.info("ContractPaymentPageVo: {}", pageInfo);
        return pageInfo;
    }


    /**
     * 查询所有施工专业分包合同数据
     */
    @GetMapping(value = "/list", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "查询所有数据")
    public List<DailyReport> list() {
        return contractPaymentService.list();
    }

}