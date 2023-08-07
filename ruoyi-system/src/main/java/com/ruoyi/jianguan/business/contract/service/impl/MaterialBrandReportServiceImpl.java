package com.ruoyi.jianguan.business.contract.service.impl;

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
import com.ruoyi.jianguan.business.contract.domain.dto.MaterialBrandReportPageDTO;
import com.ruoyi.jianguan.business.contract.domain.dto.MaterialBrandReportSaveDTO;
import com.ruoyi.jianguan.business.contract.domain.entity.MaterialBrandReport;
import com.ruoyi.jianguan.business.contract.domain.vo.MaterialBrandReportDetailVo;
import com.ruoyi.jianguan.business.contract.domain.vo.MaterialBrandReportPageVo;
import com.ruoyi.jianguan.business.contract.mapper.MaterialBrandReportMapper;
import com.ruoyi.jianguan.business.contract.service.MaterialBrandReportService;
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
public class MaterialBrandReportServiceImpl extends ServiceImpl<MaterialBrandReportMapper, MaterialBrandReport> implements MaterialBrandReportService {

    @Autowired
    private MaterialBrandReportMapper materialBrandReportMapper;

    @Autowired
    private FlowStaticPageService flowStaticPageService;


    @Override
    public ResponseBase addOrUpdate(MaterialBrandReportSaveDTO saveDto,String type) {
        //属性copy
        MaterialBrandReport materialBrandReport = new MaterialBrandReport();
        BeanUtils.copyProperties(saveDto, materialBrandReport);
        boolean isStartFlow = false;
        if ("1".equals(type)&&Objects.isNull(saveDto.getId())) {
            isStartFlow = true;
            materialBrandReport.setId(IdUtil.nextLongId());
            materialBrandReport.setStatus(0);
            materialBrandReport.setStatus1(-1);
            materialBrandReport.setStatus2(-1);
            materialBrandReport.setAttachment(JSON.toJSONString(saveDto.getAttachment()));

        }
        if ("2".equals(type)&&-1==(saveDto.getStatus1())) {
            isStartFlow = true;
            materialBrandReport.setStatus1(0);
            materialBrandReport.setStatus2(-1);
            materialBrandReport.setAttachment1(JSON.toJSONString(saveDto.getAttachment()));
            materialBrandReport.setAttachment(null);

        }
        if ("3".equals(type)&&-1==(saveDto.getStatus2())) {
            isStartFlow = true;
            materialBrandReport.setStatus2(0);
            materialBrandReport.setAttachment2(JSON.toJSONString(saveDto.getAttachment()));
            materialBrandReport.setAttachment(null);
        }
        boolean saveOrUpdate = this.saveOrUpdate(materialBrandReport);
        if (saveOrUpdate && isStartFlow) {
            String processDefinitionKey = BimFlowKey.materialBrandReport.getName();
            if ("2".equals(type)) {
                processDefinitionKey = BimFlowKey.materialSampleConfirmation.getName();
            }
            if ("3".equals(type)) {
                processDefinitionKey = BimFlowKey.aterialAcceptance.getName();
            }
            String businessKey = materialBrandReport.getId().toString();
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
    public MaterialBrandReportDetailVo getInfoById(Long id) {
        //查询
        MaterialBrandReport materialBrandReport = this.getById(id);
        if (Objects.isNull(materialBrandReport)) {
            return null;
        }
        //属性转换
        MaterialBrandReportDetailVo vo = new MaterialBrandReportDetailVo();
        BeanUtils.copyProperties(materialBrandReport, vo);
        vo.setAttachment(JSONArray.parseArray(materialBrandReport.getAttachment(), FileModel.class));
        vo.setAttachment1(JSONArray.parseArray(materialBrandReport.getAttachment1(), FileModel.class));
        vo.setAttachment2(JSONArray.parseArray(materialBrandReport.getAttachment2(), FileModel.class));
        return vo;
    }

    @Override
    public PageInfo<MaterialBrandReportPageVo> getPageInfo(MaterialBrandReportPageDTO pageDto) {
        //分页查询
        PageHelper.startPage(pageDto.getPageNum(), pageDto.getPageSize());
        List<MaterialBrandReportPageVo> materialBrandReportPageVos = materialBrandReportMapper.getPageInfo(pageDto);
        return new PageInfo<>(materialBrandReportPageVos);
    }
}