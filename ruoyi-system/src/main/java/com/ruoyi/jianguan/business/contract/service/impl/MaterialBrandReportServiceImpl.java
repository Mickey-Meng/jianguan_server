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
    public ResponseBase addOrUpdate(MaterialBrandReportSaveDTO saveDto) {
        //属性copy
        MaterialBrandReport materialBrandReport = new MaterialBrandReport();
        BeanUtils.copyProperties(saveDto, materialBrandReport);
        boolean isStartFlow = false;
        if (Objects.isNull(saveDto.getId())) {
            isStartFlow = true;
            materialBrandReport.setId(IdUtil.nextLongId());
        }
//        if (Objects.isNull(saveDto.getReportStatus())) {
//            isStartFlow = true;
//        }
        // 初始化审批状态：审批中
        // 编辑操作不修改审批状态
        if (ObjUtil.isNull(saveDto.getId())) {
            materialBrandReport.setStatus(0);
        }
//        if(ObjUtil.isNull(saveDto.getReportStatus())) {
//            materialBrandReport.setReportStatus(0);
//        }
        //附件
        materialBrandReport.setAttachment(JSON.toJSONString(saveDto.getAttachment()));
        boolean saveOrUpdate = this.saveOrUpdate(materialBrandReport);
        if (saveOrUpdate && isStartFlow) {
            String processDefinitionKey = BimFlowKey.materialBrandReport.getName();
//            if (null!=saveDto.getStatus()&&1 == saveDto.getStatus()) {
//                processDefinitionKey = BimFlowKey.materialBrandReportReport.getName();
//            }
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