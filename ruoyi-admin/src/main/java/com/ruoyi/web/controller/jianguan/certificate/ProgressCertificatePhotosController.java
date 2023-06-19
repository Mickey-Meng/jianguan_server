package com.ruoyi.web.controller.jianguan.certificate;

import com.github.pagehelper.PageInfo;
import com.ruoyi.common.core.domain.object.ResponseBase;
import com.ruoyi.jianguan.business.certificate.domain.dto.ProgressCertificatePhotosPageDTO;
import com.ruoyi.jianguan.business.certificate.domain.dto.ProgressCertificatePhotosSaveDTO;
import com.ruoyi.jianguan.business.certificate.domain.entity.ProgressCertificatePhotos;
import com.ruoyi.jianguan.business.certificate.domain.vo.ProgressCertificatePhotosVo;
import com.ruoyi.jianguan.business.certificate.service.ProgressCertificatePhotosService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 进度管理-证照管理
 *
 * @author mickey
 * @date 2023-06-19
 */
@Slf4j
@Api(value = "进度管理-证照管理", tags = {"进度管理-证照管理"})
@RestController
@RequestMapping("/web/api/v1/certificate/progress")
public class ProgressCertificatePhotosController {

    @Autowired
    private ProgressCertificatePhotosService progressCertificatePhotosService;

    /**
     * 新增或者更新进度管理-证照管理表数据
     *
     * @param
     */
    @PostMapping(value = "/addOrUpdate", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "新增或者更新")
    public ResponseBase addOrUpdate(@RequestBody @ApiParam(name = "saveDto") ProgressCertificatePhotosSaveDTO saveDto) {
        return progressCertificatePhotosService.addOrUpdate(saveDto);
    }


    /**
     * 通过id删除一条进度管理-证照管理表 数据
     *
     * @param id
     */
    @GetMapping(value = "/id", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "通过id删除一条数据")
    @Transactional(rollbackFor = Exception.class)
    public boolean removeById(@ApiParam(name = "id") Long id) {
        boolean b = progressCertificatePhotosService.removeById(id);
        return b;
    }


    /**
     * 通过id获取一条进度管理-证照管理表数据
     *
     * @param id
     */
    @GetMapping(value = "/detail/id", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "通过id获取一条数据")
    public ProgressCertificatePhotosVo getById(@ApiParam(name = "id") Long id) {
        return progressCertificatePhotosService.getInfoById(id);
    }


    /**
     * 分页查询进度管理-证照管理表 数据
     *
     * @param pageDto
     */
    @PostMapping(value = "/page", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "分页查询数据")
    public PageInfo<ProgressCertificatePhotosVo> page(@RequestBody ProgressCertificatePhotosPageDTO pageDto) {
        PageInfo<ProgressCertificatePhotosVo> pageInfo = progressCertificatePhotosService.getPageInfo(pageDto);
        log.info("ProgressCertificatePhotosPageVo: {}", pageInfo);
        return pageInfo;
    }


    /**
     * 查询所有进度管理-证照管理数据
     */
    @GetMapping(value = "/list", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "查询所有数据")
    public List<ProgressCertificatePhotos> list() {
        return progressCertificatePhotosService.list();
    }

}