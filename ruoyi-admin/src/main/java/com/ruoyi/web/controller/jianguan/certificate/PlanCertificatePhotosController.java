package com.ruoyi.web.controller.jianguan.certificate;

import com.github.pagehelper.PageInfo;
import com.ruoyi.common.core.domain.object.ResponseBase;
import com.ruoyi.jianguan.business.certificate.domain.dto.PlanCertificatePhotosPageDTO;
import com.ruoyi.jianguan.business.certificate.domain.dto.PlanCertificatePhotosSaveDTO;
import com.ruoyi.jianguan.business.certificate.domain.entity.PlanCertificatePhotos;
import com.ruoyi.jianguan.business.certificate.domain.vo.PlanCertificatePhotosVo;
import com.ruoyi.jianguan.business.certificate.service.PlanCertificatePhotosService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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
    private PlanCertificatePhotosService planCertificatePhotosService;

    /**
     * 新增或者更新计划管理-证照管理表数据
     *
     * @param
     */
    @PostMapping(value = "/addOrUpdate", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "新增或者更新")
    public ResponseBase addOrUpdate(@RequestBody @ApiParam(name = "saveDto") PlanCertificatePhotosSaveDTO saveDto) {
        return planCertificatePhotosService.addOrUpdate(saveDto);
    }


    /**
     * 通过id删除一条计划管理-证照管理表 数据
     *
     * @param id
     */
    @GetMapping(value = "/id", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "通过id删除一条数据")
    @Transactional(rollbackFor = Exception.class)
    public boolean removeById(@ApiParam(name = "id") Long id) {
        boolean b = planCertificatePhotosService.removeById(id);
        return b;
    }


    /**
     * 通过id获取一条计划管理-证照管理表数据
     *
     * @param id
     */
    @GetMapping(value = "/detail/id", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "通过id获取一条数据")
    public PlanCertificatePhotosVo getById(@ApiParam(name = "id") Long id) {
        return planCertificatePhotosService.getInfoById(id);
    }


    /**
     * 分页查询计划管理-证照管理表 数据
     *
     * @param pageDto
     */
    @PostMapping(value = "/page", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "分页查询数据")
    public PageInfo<PlanCertificatePhotosVo> page(@RequestBody PlanCertificatePhotosPageDTO pageDto) {
        PageInfo<PlanCertificatePhotosVo> pageInfo = planCertificatePhotosService.getPageInfo(pageDto);
        log.info("PlanCertificatePhotosPageVo: {}", pageInfo);
        return pageInfo;
    }


    /**
     * 查询所有计划管理-证照管理数据
     */
    @GetMapping(value = "/list", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "查询所有数据")
    public List<PlanCertificatePhotos> list() {
        return planCertificatePhotosService.list();
    }

    /**
     *

     CREATE TABLE `zz_certificate_photos` (
     `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
     `name` varchar(255) DEFAULT NULL COMMENT '证照名称',
     `contents` varchar(255) DEFAULT NULL COMMENT '证照内容',
     `start_time` datetime DEFAULT NULL COMMENT '开始时间',
     `end_time` datetime DEFAULT NULL COMMENT '结束时间',
     `report_time` datetime DEFAULT NULL COMMENT '上报时间',
     `report_user` varchar(255) DEFAULT NULL COMMENT '上报人',
     `owner` varchar(255) DEFAULT NULL COMMENT '责任人',
     `attachment` json DEFAULT NULL COMMENT '附件',
     `plan_status` int(1) DEFAULT '0' COMMENT '计划管理状态 0 审批中 1 审批完成 2 驳回',
     `progress_status` int(1) DEFAULT '0' COMMENT '进度管理状态 0 审批中 1 审批完成 2 驳回',
     `project_id` int(11) DEFAULT NULL COMMENT '项目id',
     `del_flag` char(1) DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
     `remark` varchar(500) DEFAULT NULL COMMENT '备注',
     `create_user_id` int(11) DEFAULT NULL COMMENT '创建者Id',
     `create_time` datetime DEFAULT NULL COMMENT '创建时间',
     `update_user_id` int(11) DEFAULT NULL COMMENT '更新者Id',
     `update_time` datetime DEFAULT NULL COMMENT '更新时间',
     PRIMARY KEY (`id`) USING BTREE
     ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='证照管理';

     */
}