package com.ruoyi.jianguan.quality.service.impl;

import cn.hutool.core.io.IoUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.core.sequence.util.IdUtil;
import com.ruoyi.common.enums.BimFlowKey;
import com.ruoyi.flowable.domain.dto.FlowTaskCommentDto;
import com.ruoyi.flowable.service.FlowAuditEntryService;
import com.ruoyi.flowable.service.FlowStaticPageService;
import com.ruoyi.jianguan.quality.domain.dto.BuildPlanPageDTO;
import com.ruoyi.jianguan.quality.domain.dto.BuildPlanSaveDTO;
import com.ruoyi.jianguan.quality.domain.entity.BuildPlan;
import com.ruoyi.common.core.domain.entity.FileModel;
import com.ruoyi.jianguan.quality.mapper.BuildPlanMapper;
import com.ruoyi.jianguan.quality.service.BuildPlanService;
import com.ruoyi.jianguan.quality.domain.vo.BuildPlanDetailVo;
import com.ruoyi.jianguan.quality.domain.vo.BuildPlanPageVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ruoyi.common.core.domain.object.ResponseBase;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 施工方案 服务实现类
 *
 * @author qiaoxulin
 * @date 2022-05-25
 */
@Service
public class BuildPlanServiceImpl extends ServiceImpl<BuildPlanMapper, BuildPlan> implements BuildPlanService {


    @Autowired
    private BuildPlanMapper buildPlanMapper;

    @Autowired
    private FlowStaticPageService flowStaticPageService;

    @Autowired
    private FlowAuditEntryService flowAuditEntryService;

    /**
     * 新增或者更新施工方案数据
     *
     * @param saveDto
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseBase addOrUpdate(BuildPlanSaveDTO saveDto) {
        //属性转换
        BuildPlan buildPlan = new BuildPlan();
        BeanUtils.copyProperties(saveDto, buildPlan);
        //专项施工方案
        buildPlan.setBuildPlanAttachment(JSON.toJSONString(saveDto.getBuildPlanAttachment()));
        //专家论证会议纪要
        buildPlan.setExpertMeetingAttachment(JSON.toJSONString(saveDto.getExpertMeetingAttachment()));
        //整改回复上传
        buildPlan.setReplyAttachment(JSON.toJSONString(saveDto.getReplyAttachment()));
        //新增
        boolean isStartFlow = false;
        if (Objects.isNull(saveDto.getId())) {
            buildPlan.setId(IdUtil.nextLongId());
            //判断是否是草稿
            if (saveDto.getDraftFlag() == 1) {
                isStartFlow = true;
            }
        } else {
            //判断是否是草稿转为正式数据
            BuildPlan buildPla = this.getById(saveDto.getId());
            if (buildPla.getDraftFlag() == 0 && saveDto.getDraftFlag() == 1) {
                isStartFlow = true;
            }
        }
        boolean saveOrUpdate = this.saveOrUpdate(buildPlan);
        //保存成功且是新增
        if (saveOrUpdate && isStartFlow) {
            //发起流程
            String processDefinitionKey = BimFlowKey.施工方案.getName();
            String businessKey = buildPlan.getId().toString();
            //设置流程的审批人
            Map<String, Object> auditUser = saveDto.getAuditUser();
            if (auditUser.isEmpty()) {
                throw new RuntimeException("流程的审批人不能为空！");
            }
            //发起流程
            try {
                FlowTaskCommentDto flowTaskComment = new FlowTaskCommentDto();
                flowTaskComment.setApprovalType("save");
                flowTaskComment.setComment("发起施工方案申请");
                JSONObject taskVariableData = new JSONObject(auditUser);
                flowStaticPageService.startAndTakeUserTask(processDefinitionKey, flowTaskComment, taskVariableData, null, null, saveDto.getCopyData(), businessKey);
            } catch (Exception e) {
                log.error("流程启动失败！e=" + e.getMessage());
                throw new RuntimeException("流程启动失败！e=" + e.getMessage());
            }
        }
        return ResponseBase.success(saveOrUpdate);
    }

    /**
     * 通过id获取一条施工方案数据
     *
     * @param id
     * @return
     */
    @Override
    public BuildPlanDetailVo getInfoById(Long id) {
        //查询
        BuildPlan buildPlan = getById(id);
        //判断
        if (Objects.isNull(buildPlan)) {
            return null;
        }
        //属性转换
        BuildPlanDetailVo vo = new BuildPlanDetailVo();
        BeanUtils.copyProperties(buildPlan, vo);
        //专项施工方案
        vo.setBuildPlanAttachment(JSONArray.parseArray(buildPlan.getBuildPlanAttachment(), FileModel.class));
        //专家论证会议纪要
        vo.setExpertMeetingAttachment(JSONArray.parseArray(buildPlan.getExpertMeetingAttachment(), FileModel.class));
        //整改回复上传
        vo.setReplyAttachment(JSONArray.parseArray(buildPlan.getReplyAttachment(), FileModel.class));
        return vo;
    }

    /**
     * 分页查询施工方案数据
     *
     * @param pageDto
     * @return
     */
    @Override
    public PageInfo<BuildPlanPageVo> getPageInfo(BuildPlanPageDTO pageDto) {
        //分页查询
        PageHelper.startPage(pageDto.getPageNum(), pageDto.getPageSize());
        List<BuildPlanPageVo> pageVoList = buildPlanMapper.getPageInfo(pageDto);
        //属性转换
        if (Objects.nonNull(pageVoList) && !pageVoList.isEmpty()) {
            pageVoList.forEach(pageVo -> {
                Integer status = pageVo.getStatus();
                if (0 == status) {
                    pageVo.setStatusStr("审批中");
                } else {
                    pageVo.setStatusStr("已生效");
                }
            });
        }
        return new PageInfo<>(pageVoList);
    }

    /**
     * 施工方案导出
     *
     * @param pageDto
     * @param response
     */
    @Override
    public void export(BuildPlanPageDTO pageDto, HttpServletResponse response) {
        //分页查询  页数暂定为5000
        pageDto.setPageSize(5000);
        PageInfo<BuildPlanPageVo> pageInfo = this.getPageInfo(pageDto);
        //导出
        ExcelWriter writer = ExcelUtil.getWriter();
        writer.addHeaderAlias("projectCode", "工程编号");
        writer.addHeaderAlias("projectName", "项目名称");
        writer.addHeaderAlias("supervisionSectionName", "监理标段");
        writer.addHeaderAlias("buildSectionName", "施工标段");
        writer.addHeaderAlias("contractCode", "合同号");
        writer.addHeaderAlias("buildPlanName", "专项施工方案名称");
        writer.addHeaderAlias("contractCode", "合同段");
        writer.addHeaderAlias("statusStr", "状态");
        writer.merge(7, "施工方案");
        writer.write(pageInfo.getList(), true);
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        String name = ("施工方案");
        response.setHeader("Content-Disposition", "attachment;filename=" + name + ".xls");
        ServletOutputStream out = null;
        try {
            out = response.getOutputStream();
            writer.flush(out, true);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            writer.close();
        }
        IoUtil.close(out);
    }
}
