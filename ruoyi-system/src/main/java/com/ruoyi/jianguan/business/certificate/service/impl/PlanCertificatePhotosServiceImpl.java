package com.ruoyi.jianguan.business.certificate.service.impl;

import cn.hutool.core.util.ObjUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ruoyi.common.core.domain.object.ResponseBase;
import com.ruoyi.common.core.sequence.util.IdUtil;
import com.ruoyi.common.enums.BimFlowKey;
import com.ruoyi.flowable.domain.dto.FlowTaskCommentDto;
import com.ruoyi.flowable.service.FlowStaticPageService;
import com.ruoyi.jianguan.business.certificate.domain.dto.PlanCertificatePhotosPageDTO;
import com.ruoyi.jianguan.business.certificate.domain.dto.PlanCertificatePhotosSaveDTO;
import com.ruoyi.jianguan.business.certificate.domain.entity.PlanCertificatePhotos;
import com.ruoyi.jianguan.business.certificate.domain.vo.PlanCertificatePhotosVo;
import com.ruoyi.jianguan.business.certificate.mapper.PlanCertificatePhotosMapper;
import com.ruoyi.jianguan.business.certificate.service.PlanCertificatePhotosService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 计划管理-证照管理Service业务层处理
 *
 * @author mickey
 * @date 2023-06-19
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class PlanCertificatePhotosServiceImpl extends ServiceImpl<PlanCertificatePhotosMapper, PlanCertificatePhotos>  implements PlanCertificatePhotosService {

    @Autowired
    private PlanCertificatePhotosMapper planCertificatePhotosMapper;

    @Autowired
    private FlowStaticPageService flowStaticPageService;


    @Override
    public ResponseBase addOrUpdate(PlanCertificatePhotosSaveDTO saveDto) {
        //属性copy
        PlanCertificatePhotos planCertificatePhotos = new PlanCertificatePhotos();
        BeanUtils.copyProperties(saveDto, planCertificatePhotos);
        boolean isStartFlow = false;
        if (Objects.isNull(saveDto.getId())) {
            isStartFlow = true;
            planCertificatePhotos.setId(IdUtil.nextLongId());
        }
        // 编辑操作不修改审批状态
        if(ObjUtil.isNull(saveDto.getId())) {
            planCertificatePhotos.setStatus(0);
        }
        boolean saveOrUpdate = this.saveOrUpdate(planCertificatePhotos);
        if (saveOrUpdate && isStartFlow) {
            String processDefinitionKey = BimFlowKey.planCertificate.name();
            String businessKey = planCertificatePhotos.getId().toString();
            //设置流程的审批人
            Map<String, Object> auditUser = saveDto.getAuditUser();
            if (auditUser.isEmpty()) {
                throw new RuntimeException("流程的审批人不能为空！");
            }
            //发起流程
            try {
                FlowTaskCommentDto flowTaskComment = new FlowTaskCommentDto();
                flowTaskComment.setApprovalType("save");
                flowTaskComment.setComment("合同付款单创建");
                JSONObject taskVariableData = new JSONObject(auditUser);
                flowStaticPageService.startAndTakeUserTask(processDefinitionKey, flowTaskComment, taskVariableData, null, null, saveDto.getCopyData(), businessKey);
            } catch (Exception e) {
                log.error("流程启动失败！e=" + e.getMessage());
                throw new RuntimeException("流程启动失败！e=" + e.getMessage());
            }
        }
        return ResponseBase.success("流程启动成功");
    }

    @Override
    public PlanCertificatePhotosVo getInfoById(Long id) {
        //查询
        PlanCertificatePhotos planCertificatePhotos = this.getById(id);
        if (Objects.isNull(planCertificatePhotos)) {
            return null;
        }
        //属性转换
        PlanCertificatePhotosVo vo = new PlanCertificatePhotosVo();
        BeanUtils.copyProperties(planCertificatePhotos, vo);
        return vo;
    }

    @Override
    public PageInfo<PlanCertificatePhotosVo> getPageInfo(PlanCertificatePhotosPageDTO pageDto) {
        //分页查询
        PageHelper.startPage(pageDto.getPageNum(), pageDto.getPageSize());
        List<PlanCertificatePhotosVo> planCertificatePhotosPageVos = planCertificatePhotosMapper.getPageInfo(pageDto);
        return new PageInfo<>(planCertificatePhotosPageVos);
    }
}