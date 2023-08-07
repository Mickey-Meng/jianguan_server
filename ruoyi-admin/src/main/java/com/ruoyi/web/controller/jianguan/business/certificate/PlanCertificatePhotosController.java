package com.ruoyi.web.controller.jianguan.business.certificate;

import com.github.pagehelper.PageInfo;
import com.ruoyi.common.core.domain.object.ResponseBase;
import com.ruoyi.common.enums.BimFlowKey;
import com.ruoyi.jianguan.business.certificate.domain.dto.CertificatePhotosPageDTO;
import com.ruoyi.jianguan.business.certificate.domain.dto.PlanCertificatePhotosSaveDTO;
import com.ruoyi.jianguan.business.certificate.domain.entity.CertificatePhotos;
import com.ruoyi.jianguan.business.certificate.domain.vo.PlanCertificatePhotosVo;
import com.ruoyi.jianguan.business.certificate.service.CertificatePhotosService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


/**
 * 计划管理-证照管理
 *
 * @author mickey
 * @date 2023-06-19
 */
@Slf4j
@Api(value = "计划管理-证照管理", tags = {"计划管理-证照管理"})
@RestController
@RequestMapping("/web/api/v1/certificate/plan")
public class PlanCertificatePhotosController {

    @Autowired
    private CertificatePhotosService certificatePhotosService;

    /**
     * 分页查询计划管理-证照管理表 数据
     * @param pageDto
     */
    @PostMapping(value = "/page", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "分页查询数据")
    public PageInfo<PlanCertificatePhotosVo> page(@RequestBody CertificatePhotosPageDTO pageDto) {
        PageInfo<PlanCertificatePhotosVo> pageInfo = certificatePhotosService.getPlanPageInfo(pageDto);
        log.info("PlanCertificatePhotosPageVo: {}", pageInfo);
        return pageInfo;
    }

    /**
     * 新增或者更新计划管理-证照管理表数据
     * @param
     */
    @PostMapping(value = "/addOrUpdate", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "新增或者更新")
    public ResponseBase addOrUpdate(@RequestBody @ApiParam(name = "saveDto") PlanCertificatePhotosSaveDTO saveDto) {
        return certificatePhotosService.addOrUpdate(saveDto);
    }

    /**
     * 通过id删除一条计划管理-证照管理表 数据
     * @param id
     */
    @GetMapping(value = "/id", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "通过id删除一条数据")
    @Transactional(rollbackFor = Exception.class)
    public boolean removeById(@ApiParam(name = "id") Long id) {
        return certificatePhotosService.removeById(id);
    }

    /**
     * 通过id获取一条计划管理-证照管理表数据
     * @param id
     */
    @GetMapping(value = "/detail/id", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "通过id获取一条数据")
    public PlanCertificatePhotosVo getById(@ApiParam(name = "id") Long id) {
        return certificatePhotosService.getPlanInfoById(id);
    }

    /**
     * 获取到期提醒数据列表
     * @return
     */
    @GetMapping("/getExpiryRemindersList")
    @ResponseBody
    @ApiOperation(value = "获取到期提醒数据")
    public ResponseBase getExpiryRemindersList(){
        List<CertificatePhotos> expiryRemindersList = certificatePhotosService.getExpiryRemindersList(BimFlowKey.planCertificatePhotos.getName());
        return ResponseBase.success(expiryRemindersList.stream().map(CertificatePhotos::getName).collect(Collectors.joining()));
    }
}