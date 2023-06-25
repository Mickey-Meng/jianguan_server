package com.ruoyi.jianguan.business.constructionDesign.service.impl;

import cn.hutool.core.util.ObjUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ruoyi.common.core.domain.object.ResponseBase;
import com.ruoyi.common.core.sequence.util.IdUtil;
import com.ruoyi.common.enums.BimFlowKey;
import com.ruoyi.flowable.domain.dto.FlowTaskCommentDto;
import com.ruoyi.flowable.service.FlowStaticPageService;
import com.ruoyi.jianguan.business.constructionDesign.domain.dto.ConstructionDesignPageDTO;
import com.ruoyi.jianguan.business.constructionDesign.domain.dto.PlanConstructionDesignSaveDTO;
import com.ruoyi.jianguan.business.constructionDesign.domain.dto.ProgressConstructionDesignSaveDTO;
import com.ruoyi.jianguan.business.constructionDesign.domain.entity.ConstructionDesign;
import com.ruoyi.jianguan.business.constructionDesign.domain.vo.PlanConstructionDesignVo;
import com.ruoyi.jianguan.business.constructionDesign.domain.vo.ProgressConstructionDesignVo;
import com.ruoyi.jianguan.business.constructionDesign.mapper.ConstructionDesignMapper;
import com.ruoyi.jianguan.business.constructionDesign.service.ConstructionDesignService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;

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
        ConstructionDesign ConstructionDesign = new ConstructionDesign();
        BeanUtils.copyProperties(saveDto, ConstructionDesign);
        boolean isStartFlow = false;
        if (Objects.isNull(saveDto.getId())) {
            isStartFlow = true;
            ConstructionDesign.setId(IdUtil.nextLongId());
        }
        // 编辑操作不修改审批状态
        if(ObjUtil.isNull(saveDto.getId())) {
            ConstructionDesign.setPlanStatus(0);
            ConstructionDesign.setProgressStatus(-1);
        }
        boolean saveOrUpdate = this.saveOrUpdate(ConstructionDesign);
        if (saveOrUpdate && isStartFlow) {
            String processDefinitionKey = BimFlowKey.planConstructionDesign.getName();
            String businessKey = ConstructionDesign.getId().toString();
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
        ConstructionDesign ConstructionDesign = constructionDesignMapper.selectById(saveDto.getId());
        //附件
        ConstructionDesign.setAttachment(JSON.toJSONString(saveDto.getAttachment()));
        ConstructionDesign.setProgressStatus(0);
        boolean saveOrUpdate = this.saveOrUpdate(ConstructionDesign);
        if (saveOrUpdate) {
            String processDefinitionKey = BimFlowKey.progressConstructionDesign.getName();
            String businessKey = ConstructionDesign.getId().toString();
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
        ConstructionDesign ConstructionDesign = this.getById(id);
        if (Objects.isNull(ConstructionDesign)) {
            return null;
        }
        //属性转换
        PlanConstructionDesignVo vo = new PlanConstructionDesignVo();
        BeanUtils.copyProperties(ConstructionDesign, vo);
        return vo;
    }
    @Override
    public ProgressConstructionDesignVo getProgressInfoById(Long id) {
        //查询
        ConstructionDesign ConstructionDesign = this.getById(id);
        if (Objects.isNull(ConstructionDesign)) {
            return null;
        }
        //属性转换
        ProgressConstructionDesignVo vo = new ProgressConstructionDesignVo();
        BeanUtils.copyProperties(ConstructionDesign, vo);
        // TODO: 2023/3/29 附件待实现
       // vo.setAttachment(JSONArray.parseArray(progressConstructionDesign.getAttachment(), FileModel.class));
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
        return new PageInfo<>(progressConstructionDesignPageVos);
    }
}