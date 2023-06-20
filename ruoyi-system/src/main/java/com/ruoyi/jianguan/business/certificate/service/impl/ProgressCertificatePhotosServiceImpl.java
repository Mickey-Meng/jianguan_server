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
import com.ruoyi.jianguan.business.certificate.domain.dto.ProgressCertificatePhotosPageDTO;
import com.ruoyi.jianguan.business.certificate.domain.dto.ProgressCertificatePhotosSaveDTO;
import com.ruoyi.jianguan.business.certificate.domain.entity.ProgressCertificatePhotos;
import com.ruoyi.jianguan.business.certificate.domain.vo.ProgressCertificatePhotosVo;
import com.ruoyi.jianguan.business.certificate.mapper.ProgressCertificatePhotosMapper;
import com.ruoyi.jianguan.business.certificate.service.ProgressCertificatePhotosService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 合同付款Service业务层处理
 *
 * @author mickey
 * @date 2023-06-07
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ProgressCertificatePhotosServiceImpl extends ServiceImpl<ProgressCertificatePhotosMapper, ProgressCertificatePhotos>  implements ProgressCertificatePhotosService {

    @Autowired
    private ProgressCertificatePhotosMapper progressCertificatePhotosMapper;

    @Autowired
    private FlowStaticPageService flowStaticPageService;


    @Override
    public ResponseBase addOrUpdate(ProgressCertificatePhotosSaveDTO saveDto) {
        //属性copy
        ProgressCertificatePhotos progressCertificatePhotos = new ProgressCertificatePhotos();
        BeanUtils.copyProperties(saveDto, progressCertificatePhotos);
        boolean isStartFlow = false;
        if (Objects.isNull(saveDto.getId())) {
            isStartFlow = true;
            progressCertificatePhotos.setId(IdUtil.nextLongId());
        }
        //附件
        progressCertificatePhotos.setAttachment(JSON.toJSONString(saveDto.getAttachment()));
        // 编辑操作不修改审批状态
        if(ObjUtil.isNull(saveDto.getId())) {
            progressCertificatePhotos.setStatus(0);
        }
        boolean saveOrUpdate = this.saveOrUpdate(progressCertificatePhotos);
        if (saveOrUpdate && isStartFlow) {
            String processDefinitionKey = BimFlowKey.progressCertificate.name();
            String businessKey = progressCertificatePhotos.getId().toString();
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
    public ProgressCertificatePhotosVo getInfoById(Long id) {
        //查询
        ProgressCertificatePhotos progressCertificatePhotos = this.getById(id);
        if (Objects.isNull(progressCertificatePhotos)) {
            return null;
        }
        //属性转换
        ProgressCertificatePhotosVo vo = new ProgressCertificatePhotosVo();
        BeanUtils.copyProperties(progressCertificatePhotos, vo);
        // TODO: 2023/3/29 附件待实现
       // vo.setAttachment(JSONArray.parseArray(progressCertificatePhotos.getAttachment(), FileModel.class));
        return vo;
    }

    @Override
    public PageInfo<ProgressCertificatePhotosVo> getPageInfo(ProgressCertificatePhotosPageDTO pageDto) {
        //分页查询
        PageHelper.startPage(pageDto.getPageNum(), pageDto.getPageSize());
        List<ProgressCertificatePhotosVo> progressCertificatePhotosPageVos = progressCertificatePhotosMapper.getPageInfo(pageDto);
        return new PageInfo<>(progressCertificatePhotosPageVos);
    }
}