package com.ruoyi.jianguan.business.quality.service.impl;

import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.ObjUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.enums.BimFlowKey;
import com.ruoyi.jianguan.business.quality.domain.dto.ProjectOpenPageDTO;
import com.ruoyi.jianguan.business.quality.domain.dto.ProjectOpenSaveDTO;
import com.ruoyi.jianguan.business.quality.domain.entity.ProjectOpen;
import com.ruoyi.jianguan.business.quality.domain.vo.ProjectOpenDetailVo;
import com.ruoyi.jianguan.business.quality.domain.vo.ProjectOpenPageVo;
import com.ruoyi.common.core.domain.entity.FileModel;
import com.ruoyi.jianguan.business.quality.mapper.ProjectOpenMapper;
import com.ruoyi.jianguan.business.quality.service.ProjectOpenService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ruoyi.flowable.service.ZjFGroupsProjectsService;
import com.ruoyi.common.core.domain.object.ResponseBase;
import com.ruoyi.flowable.domain.dto.FlowTaskCommentDto;
import com.ruoyi.flowable.service.FlowStaticPageService;
import com.ruoyi.common.core.sequence.util.IdUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * 项目开工申请 服务实现类
 *
 * @author qiaoxulin
 * @date 2022-06-01
 */
@Service
public class ProjectOpenServiceImpl extends ServiceImpl<ProjectOpenMapper, ProjectOpen> implements ProjectOpenService {


    @Autowired
    private ProjectOpenMapper projectOpenMapper;

    @Autowired
    private ZjFGroupsProjectsService projectsService;

    @Autowired
    private FlowStaticPageService flowStaticPageService;

    /**
     * 新增或者更新项目开工申请数据
     *
     * @param saveDto
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseBase addOrUpdate(ProjectOpenSaveDTO saveDto) {
        //属性copy
        ProjectOpen projectOpen = new ProjectOpen();
        BeanUtils.copyProperties(saveDto, projectOpen);
        //附件
        projectOpen.setAttachment(JSON.toJSONString(saveDto.getAttachment()));
        //新增
        boolean isStartFlow = false;
        if (Objects.isNull(saveDto.getId())) {
            projectOpen.setId(IdUtil.nextLongId());
            //判断是否是草稿
            if (saveDto.getDraftFlag() == 1) {
                isStartFlow = true;
            }
        } else {
            //判断是否是草稿转为正式数据
            ProjectOpen project = this.getById(saveDto.getId());
            if (project.getDraftFlag() == 0 && saveDto.getDraftFlag() == 1) {
                isStartFlow = true;
            }
        }
        // 编辑操作不修改审批状态
        if(ObjUtil.isNull(saveDto.getId())) {
            projectOpen.setStatus(0);
        }
        //保存
        boolean saveOrUpdate = this.saveOrUpdate(projectOpen);
        //保存成功且新增
        if (saveOrUpdate && isStartFlow) {
            //发起流程
            String processDefinitionKey = BimFlowKey.项目开工申请.getName();
            String businessKey = projectOpen.getId().toString();
            //设置流程的审批人
            Map<String, Object> auditUser = saveDto.getAuditUser();
            if (auditUser.isEmpty()) {
                throw new RuntimeException("流程的审批人不能为空！");
            }
            //发起流程
            try {
                FlowTaskCommentDto flowTaskComment = new FlowTaskCommentDto();
                flowTaskComment.setApprovalType("save");
                flowTaskComment.setComment("发起项目开工申请");
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
     * 通过id获取一条项目开工申请数据
     *
     * @param id
     * @return
     */
    @Override
    public ProjectOpenDetailVo getInfoById(Long id) {
        //查询
        ProjectOpen projectOpen = this.getById(id);
        if (Objects.isNull(projectOpen)) {
            return null;
        }
        //返回
        ProjectOpenDetailVo vo = new ProjectOpenDetailVo();
        BeanUtils.copyProperties(projectOpen, vo);
        //附件
        vo.setAttachment(JSONArray.parseArray(projectOpen.getAttachment(), FileModel.class));
        return vo;
    }

    /**
     * 分页查询项目开工申请数据
     *
     * @param pageDto
     * @return
     */
    @Override
    public PageInfo<ProjectOpenPageVo> getPageInfo(ProjectOpenPageDTO pageDto) {
        //分页查
        PageHelper.startPage(pageDto.getPageNum(), pageDto.getPageSize());
        List<ProjectOpenPageVo> pageVoList = projectOpenMapper.getPageInfo(pageDto);
        //非空
        if (Objects.nonNull(pageVoList) && !pageVoList.isEmpty()) {
            Set<String> buildSection = projectsService.getBuildSectionByProjectId(pageDto.getProjectId());
            if (Objects.nonNull(buildSection) && !buildSection.isEmpty()) {
                pageVoList.forEach(pageVo -> {
                    //添加施工单位
                    pageVo.setBuildSectionNames(buildSection);
                    //状态
                    pageVo.setStatusStr(pageVo.getStatus() == 0 ? "进行中" : "已完成");
                    //天数
                    long days = pageVo.getContractEndDate().until(pageVo.getContractOpenDate(), ChronoUnit.DAYS);
                    pageVo.setDays(Integer.parseInt(String.valueOf(days)));
                });
            }
        }
        return new PageInfo<>(pageVoList);
    }

    /**
     * 项目开工申请导出
     *
     * @param pageDto
     * @param response
     */
    @Override
    public void export(ProjectOpenPageDTO pageDto, HttpServletResponse response) {
        //分页查询  页数暂定为5000
        pageDto.setPageSize(5000);
        PageInfo<ProjectOpenPageVo> pageInfo = this.getPageInfo(pageDto);
        //导出
        ExcelWriter writer = ExcelUtil.getWriter();
        //TODO
        writer.addHeaderAlias("buildSectionNames", "监理标段");
        writer.addHeaderAlias("buildSectionNames", "施工标段");
        writer.addHeaderAlias("openDate", "申请开工日期");
        writer.addHeaderAlias("endDate", "计划完工日期");
        writer.addHeaderAlias("contractOpenDate", "合同规定工期起");
        writer.addHeaderAlias("days", "历时天数");
        writer.addHeaderAlias("contractEndDate", "合同规定工期止");
        writer.addHeaderAlias("statusStr", "状态");
        writer.write(pageInfo.getList(), true);
        writer.merge(7, "项目开工申请");
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        String name = ("项目开工申请");
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
