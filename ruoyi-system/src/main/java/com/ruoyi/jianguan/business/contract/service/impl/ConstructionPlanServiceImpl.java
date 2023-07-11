package com.ruoyi.jianguan.business.contract.service.impl;

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
import com.ruoyi.jianguan.business.contract.domain.dto.ConstructionPlanPageDTO;
import com.ruoyi.jianguan.business.contract.domain.dto.ConstructionPlanSaveDTO;
import com.ruoyi.jianguan.business.contract.domain.entity.ConstructionPlan;
import com.ruoyi.jianguan.business.contract.domain.vo.ConstructionPlanDetailVo;
import com.ruoyi.jianguan.business.contract.domain.vo.ConstructionPlanPageVo;
import com.ruoyi.jianguan.business.contract.mapper.ConstructionPlanMapper;
import com.ruoyi.jianguan.business.contract.service.ConstructionPlanService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;


@Slf4j
@Service
@RequiredArgsConstructor
public class ConstructionPlanServiceImpl extends ServiceImpl<ConstructionPlanMapper, ConstructionPlan>  implements ConstructionPlanService {

    @Autowired
    private ConstructionPlanMapper constructionPlanMapper;

    @Autowired
    private FlowStaticPageService flowStaticPageService;


    @Override
    public ResponseBase addOrUpdate(ConstructionPlanSaveDTO saveDto) {
        //属性copy
        ConstructionPlan constructionPlan = new ConstructionPlan();
        BeanUtils.copyProperties(saveDto, constructionPlan);
        boolean isStartFlow = false;
        if (Objects.isNull(saveDto.getId())) {
            isStartFlow = true;
            constructionPlan.setId(IdUtil.nextLongId());
        }
        if (Objects.isNull(saveDto.getReportStatus())) {
            isStartFlow = true;
        }
        // 初始化审批状态：审批中
        // 编辑操作不修改审批状态
        if(ObjUtil.isNull(saveDto.getId())) {
            constructionPlan.setStatus(0);
        }
        if(ObjUtil.isNull(saveDto.getReportStatus())) {
            constructionPlan.setReportStatus(0);
        }
        //附件
        constructionPlan.setAttachment(JSON.toJSONString(saveDto.getAttachment()));
        boolean saveOrUpdate = this.saveOrUpdate(constructionPlan);
        if (saveOrUpdate && isStartFlow) {
            String processDefinitionKey = BimFlowKey.constructionPlan.getName();
            if (null!=saveDto.getStatus()&&1 == saveDto.getStatus()) {
                processDefinitionKey = BimFlowKey.constructionPlanReport.getName();
            }
            String businessKey = constructionPlan.getId().toString();
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
    public ConstructionPlanDetailVo getInfoById(Long id) {
        //查询
        ConstructionPlan constructionPlan = this.getById(id);
        if (Objects.isNull(constructionPlan)) {
            return null;
        }
        //属性转换
        ConstructionPlanDetailVo vo = new ConstructionPlanDetailVo();
        BeanUtils.copyProperties(constructionPlan, vo);
        vo.setAttachment(JSONArray.parseArray(constructionPlan.getAttachment(), FileModel.class));
        return vo;
    }

    @Override
    public PageInfo<ConstructionPlanPageVo> getPageInfo(ConstructionPlanPageDTO pageDto) {
        //分页查询
        PageHelper.startPage(pageDto.getPageNum(), pageDto.getPageSize());
        List<ConstructionPlanPageVo> constructionPlanPageVos = constructionPlanMapper.getPageInfo(pageDto);
        return new PageInfo<>(constructionPlanPageVos);
    }
}