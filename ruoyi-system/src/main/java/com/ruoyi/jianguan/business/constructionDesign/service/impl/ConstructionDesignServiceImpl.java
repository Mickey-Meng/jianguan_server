package com.ruoyi.jianguan.business.constructionDesign.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ruoyi.common.core.domain.entity.FileModel;
import com.ruoyi.common.core.domain.object.ResponseBase;
import com.ruoyi.common.core.sequence.util.IdUtil;
import com.ruoyi.common.enums.BimFlowKey;
import com.ruoyi.common.helper.LoginHelper;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.JwtUtil;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.flowable.domain.dto.FlowTaskCommentDto;
import com.ruoyi.flowable.service.FlowStaticPageService;
import com.ruoyi.jianguan.business.certificate.domain.entity.CertificatePhotos;
import com.ruoyi.jianguan.business.constructionDesign.domain.dto.ConstructionDesignPageDTO;
import com.ruoyi.jianguan.business.constructionDesign.domain.dto.PlanConstructionDesignSaveDTO;
import com.ruoyi.jianguan.business.constructionDesign.domain.dto.ProgressConstructionDesignSaveDTO;
import com.ruoyi.jianguan.business.constructionDesign.domain.entity.ConstructionDesign;
import com.ruoyi.jianguan.business.constructionDesign.domain.vo.PlanConstructionDesignVo;
import com.ruoyi.jianguan.business.constructionDesign.domain.vo.ProgressConstructionDesignVo;
import com.ruoyi.jianguan.business.constructionDesign.mapper.ConstructionDesignMapper;
import com.ruoyi.jianguan.business.constructionDesign.service.ConstructionDesignService;
import com.ruoyi.jianguan.manage.project.domain.entity.JgProjectInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 计划&进度管理-施工图管理Service业务层处理
 * @author mickey
 * @date 2023-06-25
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ConstructionDesignServiceImpl extends ServiceImpl<ConstructionDesignMapper, ConstructionDesign>  implements ConstructionDesignService {

    @Autowired
    private ConstructionDesignMapper constructionDesignMapper;

    @Autowired
    private FlowStaticPageService flowStaticPageService;

    @Override
    public ResponseBase addOrUpdate(PlanConstructionDesignSaveDTO saveDto) {
        //属性copy
        ConstructionDesign constructionDesign = new ConstructionDesign();
        BeanUtils.copyProperties(saveDto, constructionDesign);
        String[] ownerInfo = Objects.isNull(saveDto.getOwner()) ? null : saveDto.getOwner().split("&");
        if (ownerInfo != null && ownerInfo.length == 2) {
            constructionDesign.setOwnerId(Long.valueOf(ownerInfo[0]));
            constructionDesign.setOwnerName(ownerInfo[1]);
        }
        boolean isStartFlow = false;
        if (Objects.isNull(saveDto.getId())) {
            isStartFlow = true;
            constructionDesign.setId(IdUtil.nextLongId());
        }
        // 编辑操作不修改审批状态
        if(ObjUtil.isNull(saveDto.getId())) {
            constructionDesign.setPlanStatus(0);
            constructionDesign.setProgressStatus(-1);
        }
        boolean saveOrUpdate = this.saveOrUpdate(constructionDesign);
        if (saveOrUpdate && isStartFlow) {
            String processDefinitionKey = BimFlowKey.planConstructionDesign.getName();
            String businessKey = constructionDesign.getId() + "_" + processDefinitionKey;
            //设置流程的审批人
            Map<String, Object> auditUser = saveDto.getAuditUser();
            if (auditUser.isEmpty()) {
                throw new RuntimeException("流程的审批人不能为空！");
            }
            //发起流程
            try {
                FlowTaskCommentDto flowTaskComment = new FlowTaskCommentDto();
                flowTaskComment.setApprovalType("save");
                flowTaskComment.setComment("计划报审-施工图管理单创建");
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
    public ResponseBase updateUploadFile(ProgressConstructionDesignSaveDTO saveDto) {
        ConstructionDesign constructionDesign = constructionDesignMapper.selectById(saveDto.getId());
        //附件
        constructionDesign.setAttachment(JSON.toJSONString(saveDto.getAttachment()));
        constructionDesign.setProgressStatus(0);
        boolean saveOrUpdate = this.saveOrUpdate(constructionDesign);
        if (saveOrUpdate) {
            String processDefinitionKey = BimFlowKey.progressConstructionDesign.getName();
            String businessKey = constructionDesign.getId() + "_" + processDefinitionKey;
            //设置流程的审批人
            Map<String, Object> auditUser = saveDto.getAuditUser();
            if (auditUser.isEmpty()) {
                throw new RuntimeException("流程的审批人不能为空！");
            }
            //发起流程
            try {
                FlowTaskCommentDto flowTaskComment = new FlowTaskCommentDto();
                flowTaskComment.setApprovalType("save");
                flowTaskComment.setComment("进度管理-施工图管理单创建");
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
    public PlanConstructionDesignVo getPlanInfoById(Long id) {
        //查询
        ConstructionDesign constructionDesign = this.getById(id);
        if (Objects.isNull(constructionDesign)) {
            return null;
        }
        //属性转换
        PlanConstructionDesignVo vo = new PlanConstructionDesignVo();
        BeanUtils.copyProperties(constructionDesign, vo);
        vo.setStatus(constructionDesign.getPlanStatus());
        vo.setStatus(constructionDesign.getPlanStatus());
        vo.setOwner(constructionDesign.getOwnerId() + "&" + constructionDesign.getOwnerName());
        return vo;
    }
    @Override
    public ProgressConstructionDesignVo getProgressInfoById(Long id) {
        //查询
        ConstructionDesign constructionDesign = this.getById(id);
        if (Objects.isNull(constructionDesign)) {
            return null;
        }
        //属性转换
        ProgressConstructionDesignVo vo = new ProgressConstructionDesignVo();
        BeanUtils.copyProperties(constructionDesign, vo);
        vo.setStatus(constructionDesign.getProgressStatus());
        vo.setAttachment(JSONArray.parseArray(constructionDesign.getAttachment(), FileModel.class));
        return vo;
    }

    /**
     * 查询[计划报审-施工图管理]分页数据
     * @param pageDto
     * @return
     */
    @Override
    public PageInfo<PlanConstructionDesignVo> getPlanPageInfo(ConstructionDesignPageDTO pageDto) {
        //分页查询
        PageHelper.startPage(pageDto.getPageNum(), pageDto.getPageSize());
        List<PlanConstructionDesignVo> planConstructionDesignPageVos = constructionDesignMapper.getPlanPageInfo(pageDto);
        return new PageInfo<>(planConstructionDesignPageVos);
    }

    /**
     * 查询[进度管理-施工图管理]分页数据
     * @param pageDto
     * @return
     */
    @Override
    public PageInfo<ProgressConstructionDesignVo> getProgressPageInfo(ConstructionDesignPageDTO pageDto) {
        //分页查询
        PageHelper.startPage(pageDto.getPageNum(), pageDto.getPageSize());
        List<ProgressConstructionDesignVo> progressConstructionDesignPageVos = constructionDesignMapper.getProgressPageInfo(pageDto);
        progressConstructionDesignPageVos.forEach(progressConstructionDesignVo -> {
            List<FileModel> fileModelList = JSONArray.parseArray(progressConstructionDesignVo.getAttachmentNames(), FileModel.class);
            if (CollectionUtil.isNotEmpty(fileModelList)) {
                String attachmentNames = fileModelList.stream().map(FileModel::getFileName).collect(Collectors.joining(","));
                progressConstructionDesignVo.setAttachmentNames(attachmentNames);
            }
        });
        return new PageInfo<>(progressConstructionDesignPageVos);
    }

    @Override
    public List<ConstructionDesign> getExpiryRemindersList(String name) {
        LambdaQueryWrapper<ConstructionDesign> lqw = Wrappers.lambdaQuery();
        // 结束日期小于等于当前日期、结束日期大于等于当前日期-5、未审批完成的数据、当前登录用户等于责任人
        lqw.le(ConstructionDesign::getEndTime, DateUtils.getNowDate());
        lqw.ge(ConstructionDesign::getEndTime, DateUtils.addDays(new Date(), -5));
        lqw.eq(ConstructionDesign::getOwnerId, LoginHelper.getUserId());
        lqw.ne(ConstructionDesign::getPlanStatus, 1).or().ne(ConstructionDesign::getProgressStatus, 1);
        List<ConstructionDesign> expiryRemindersList = constructionDesignMapper.selectList(lqw);
        if (Objects.equals(name, BimFlowKey.progressConstructionDesign.getName())) {
            expiryRemindersList = expiryRemindersList.stream().filter(constructionDesign -> (constructionDesign.getProgressStatus() != 0) && Objects.isNull(constructionDesign.getAttachment()))
                    .collect(Collectors.toList());
        }
        return expiryRemindersList;
    }
}