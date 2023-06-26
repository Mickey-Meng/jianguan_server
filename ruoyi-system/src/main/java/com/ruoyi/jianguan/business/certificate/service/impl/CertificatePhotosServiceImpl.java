package com.ruoyi.jianguan.business.certificate.service.impl;

import cn.hutool.core.util.ObjUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ruoyi.common.core.domain.entity.FileModel;
import com.ruoyi.common.core.domain.object.ResponseBase;
import com.ruoyi.common.core.sequence.util.IdUtil;
import com.ruoyi.common.enums.BimFlowKey;
import com.ruoyi.flowable.domain.dto.FlowTaskCommentDto;
import com.ruoyi.flowable.service.FlowStaticPageService;
import com.ruoyi.jianguan.business.certificate.domain.dto.CertificatePhotosPageDTO;
import com.ruoyi.jianguan.business.certificate.domain.dto.PlanCertificatePhotosSaveDTO;
import com.ruoyi.jianguan.business.certificate.domain.dto.ProgressCertificatePhotosSaveDTO;
import com.ruoyi.jianguan.business.certificate.domain.entity.CertificatePhotos;
import com.ruoyi.jianguan.business.certificate.domain.vo.PlanCertificatePhotosVo;
import com.ruoyi.jianguan.business.certificate.domain.vo.ProgressCertificatePhotosVo;
import com.ruoyi.jianguan.business.certificate.mapper.CertificatePhotosMapper;
import com.ruoyi.jianguan.business.certificate.service.CertificatePhotosService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 计划&进度管理-证照管理Service业务层处理
 * @author mickey
 * @date 2023-06-07
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class CertificatePhotosServiceImpl extends ServiceImpl<CertificatePhotosMapper, CertificatePhotos>  implements CertificatePhotosService {

    @Autowired
    private CertificatePhotosMapper certificatePhotosMapper;

    @Autowired
    private FlowStaticPageService flowStaticPageService;

    @Override
    public ResponseBase addOrUpdate(PlanCertificatePhotosSaveDTO saveDto) {
        //属性copy
        CertificatePhotos certificatePhotos = new CertificatePhotos();
        BeanUtils.copyProperties(saveDto, certificatePhotos);
        certificatePhotos.setPlanOwner(saveDto.getOwner());
        boolean isStartFlow = false;
        if (Objects.isNull(saveDto.getId())) {
            isStartFlow = true;
            certificatePhotos.setId(IdUtil.nextLongId());
        }
        // 编辑操作不修改审批状态
        if(ObjUtil.isNull(saveDto.getId())) {
            certificatePhotos.setPlanStatus(0);
            certificatePhotos.setProgressStatus(-1);
        }
        boolean saveOrUpdate = this.saveOrUpdate(certificatePhotos);
        if (saveOrUpdate && isStartFlow) {
            String processDefinitionKey = BimFlowKey.planCertificatePhotos.getName();
            String businessKey = certificatePhotos.getId().toString();
            //设置流程的审批人
            Map<String, Object> auditUser = saveDto.getAuditUser();
            if (auditUser.isEmpty()) {
                throw new RuntimeException("流程的审批人不能为空！");
            }
            //发起流程
            try {
                FlowTaskCommentDto flowTaskComment = new FlowTaskCommentDto();
                flowTaskComment.setApprovalType("save");
                flowTaskComment.setComment("计划管理-证照管理单创建");
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
    public ResponseBase updateUploadFile(ProgressCertificatePhotosSaveDTO saveDto) {
        CertificatePhotos certificatePhotos = certificatePhotosMapper.selectById(saveDto.getId());
        //附件
        certificatePhotos.setAttachment(JSON.toJSONString(saveDto.getAttachment()));
        certificatePhotos.setProgressStatus(0);
        certificatePhotos.setProgressOwner(saveDto.getOwner());
        boolean saveOrUpdate = this.saveOrUpdate(certificatePhotos);
        if (saveOrUpdate) {
            String processDefinitionKey = BimFlowKey.progressCertificatePhotos.getName();
            String businessKey = certificatePhotos.getId().toString();
            //设置流程的审批人
            Map<String, Object> auditUser = saveDto.getAuditUser();
            if (auditUser.isEmpty()) {
                throw new RuntimeException("流程的审批人不能为空！");
            }
            //发起流程
            try {
                FlowTaskCommentDto flowTaskComment = new FlowTaskCommentDto();
                flowTaskComment.setApprovalType("save");
                flowTaskComment.setComment("进度管理-证照管理单创建");
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
    public PlanCertificatePhotosVo getPlanInfoById(Long id) {
        //查询
        CertificatePhotos certificatePhotos = this.getById(id);
        if (Objects.isNull(certificatePhotos)) {
            return null;
        }
        //属性转换
        PlanCertificatePhotosVo vo = new PlanCertificatePhotosVo();
        BeanUtils.copyProperties(certificatePhotos, vo);
        vo.setOwner(certificatePhotos.getPlanOwner());
        vo.setStatus(certificatePhotos.getPlanStatus());
        return vo;
    }
    @Override
    public ProgressCertificatePhotosVo getProgressInfoById(Long id) {
        //查询
        CertificatePhotos certificatePhotos = this.getById(id);
        if (Objects.isNull(certificatePhotos)) {
            return null;
        }
        //属性转换
        ProgressCertificatePhotosVo vo = new ProgressCertificatePhotosVo();
        BeanUtils.copyProperties(certificatePhotos, vo);
        vo.setOwner(certificatePhotos.getProgressOwner());
        vo.setStatus(certificatePhotos.getProgressStatus());
        vo.setAttachment(JSONArray.parseArray(certificatePhotos.getAttachment(), FileModel.class));
        return vo;
    }

    /**
     * 查询[计划管理-证照管理]分页数据
     * @param pageDto
     * @return
     */
    @Override
    public PageInfo<PlanCertificatePhotosVo> getPlanPageInfo(CertificatePhotosPageDTO pageDto) {
        //分页查询
        PageHelper.startPage(pageDto.getPageNum(), pageDto.getPageSize());
        List<PlanCertificatePhotosVo> planCertificatePhotosPageVos = certificatePhotosMapper.getPlanPageInfo(pageDto);
        return new PageInfo<>(planCertificatePhotosPageVos);
    }

    /**
     * 查询[进度管理-证照管理]分页数据
     * @param pageDto
     * @return
     */
    @Override
    public PageInfo<ProgressCertificatePhotosVo> getProgressPageInfo(CertificatePhotosPageDTO pageDto) {
        //分页查询
        PageHelper.startPage(pageDto.getPageNum(), pageDto.getPageSize());
        List<ProgressCertificatePhotosVo> progressCertificatePhotosPageVos = certificatePhotosMapper.getProgressPageInfo(pageDto);
        return new PageInfo<>(progressCertificatePhotosPageVos);
    }
}