package com.ruoyi.jianguan.business.quality.service.impl;

import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.ObjUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.core.domain.entity.CompanyInfo;
import com.ruoyi.common.core.sequence.util.IdUtil;
import com.ruoyi.common.enums.BimFlowKey;
import com.ruoyi.flowable.domain.dto.FlowTaskCommentDto;
import com.ruoyi.flowable.service.FlowAuditEntryService;
import com.ruoyi.flowable.service.FlowStaticPageService;
import com.ruoyi.flowable.service.ZjFGroupsProjectsService;
import com.ruoyi.jianguan.business.quality.domain.dto.BuildTechBottomPageDTO;
import com.ruoyi.jianguan.business.quality.domain.dto.BuildTechBottomSaveDTO;
import com.ruoyi.jianguan.business.quality.domain.entity.BuildTechBottom;
import com.ruoyi.jianguan.business.quality.domain.vo.BuildTechBottomDetailVo;
import com.ruoyi.jianguan.business.quality.domain.vo.BuildTechBottomPageVo;
import com.ruoyi.common.core.domain.entity.FileModel;
import com.ruoyi.jianguan.business.quality.mapper.BuildTechBottomMapper;
import com.ruoyi.jianguan.business.quality.service.BuildTechBottomService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;


import com.ruoyi.common.core.domain.object.ResponseBase;

import com.ruoyi.jianguan.manage.project.domain.vo.JgProjectItemVo;
import com.ruoyi.jianguan.manage.project.service.IJgProjectItemService;
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
import java.util.Set;

/**
 * 施工技术交底 服务实现类
 *
 * @author qiaoxulin
 * @date 2022-05-26
 */
@Service
public class BuildTechBottomServiceImpl extends ServiceImpl<BuildTechBottomMapper, BuildTechBottom> implements BuildTechBottomService {


    @Autowired
    private BuildTechBottomMapper buildTechBottomMapper;

    @Autowired
    private ZjFGroupsProjectsService projectsService;

    @Autowired
    private FlowStaticPageService flowStaticPageService;

    @Autowired
    private FlowAuditEntryService flowAuditEntryService;

    @Autowired
    private IJgProjectItemService jgProjectItemService;
    /**
     * 新增或者更新施工技术交底数据
     *
     * @param saveDto
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseBase addOrUpdate(BuildTechBottomSaveDTO saveDto) {
        //属性copy
        BuildTechBottom buildTechBottom = new BuildTechBottom();
        BeanUtils.copyProperties(saveDto, buildTechBottom);
        //附件
        buildTechBottom.setAttachment(JSON.toJSONString(saveDto.getAttachment()));
        //新增
        boolean isStartFlow = false;
        if (Objects.isNull(saveDto.getId())) {
            buildTechBottom.setId(IdUtil.nextLongId());
            //判断是否是草稿
            if (saveDto.getDraftFlag() == 1) {
                isStartFlow = true;
            }
        } else {
            //判断是否是草稿转为正式数据
            BuildTechBottom buildTechBotto = this.getById(saveDto.getId());
            if (buildTechBotto.getDraftFlag() == 0 && saveDto.getDraftFlag() == 1) {
                isStartFlow = true;
            }
        }
        // 编辑操作不修改审批状态
        if(ObjUtil.isNull(saveDto.getId())) {
            buildTechBottom.setStatus(0);
        }
        //保存
        boolean saveOrUpdate = this.saveOrUpdate(buildTechBottom);
        //保存成功且新增，发起流程
        if (saveOrUpdate && isStartFlow) {
            //发起流程
            String processDefinitionKey = BimFlowKey.施工技术交底.getName();
            String businessKey = buildTechBottom.getId().toString();
            //设置流程的审批人
            Map<String, Object> auditUser = saveDto.getAuditUser();
            if (auditUser.isEmpty()) {
                throw new RuntimeException("流程的审批人不能为空！");
            }
            //发起流程
            try {
                FlowTaskCommentDto flowTaskComment = new FlowTaskCommentDto();
                flowTaskComment.setApprovalType("save");
                flowTaskComment.setComment("发起施工技术交底申请");
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
     * 通过id获取一条施工技术交底数据
     *
     * @param id
     * @return
     */
    @Override
    public BuildTechBottomDetailVo getInfoById(Long id) {
        //查询
        BuildTechBottom buildTechBottom = this.getById(id);
        if (Objects.isNull(buildTechBottom)) {
            return null;
        }
        //属性转换
        BuildTechBottomDetailVo vo = new BuildTechBottomDetailVo();
        BeanUtils.copyProperties(buildTechBottom, vo);
        //附件
        vo.setAttachment(JSONArray.parseArray(buildTechBottom.getAttachment(), FileModel.class));
        return vo;
    }

    /**
     * 分页查询施工技术交底数据
     *
     * @param pageDto
     * @return
     */
    @Override
    public PageInfo<BuildTechBottomPageVo> getPageInfo(BuildTechBottomPageDTO pageDto) {
        //分页查询
        PageHelper.startPage(pageDto.getPageNum(), pageDto.getPageSize());
        List<BuildTechBottomPageVo> pageVoList = buildTechBottomMapper.getPageInfo(pageDto);
        //状态转换
        if (Objects.nonNull(pageVoList) && !pageVoList.isEmpty()) {
            //通过项目id获取施工单位 监理单位等
//            CompanyInfo companyInfo = projectsService.getCompanyInfoByProjectId(pageDto.getProjectId());
            JgProjectItemVo jgProjectItemVo = jgProjectItemService.queryById(pageDto.getProjectId().longValue()) ;
            String constructDept = jgProjectItemVo.getConstructDept();
            //施工单位
//            Set<String> sgdws = companyInfo.getSgdws();

            if (Objects.nonNull(constructDept) && !constructDept.isEmpty()) {
                pageVoList.forEach(pageVo -> {
                    //施工单位
//                    pageVo.setBuildUnits(sgdws);
                    pageVo.setConstructdpts(constructDept);
                    //状态
                    switch (pageVo.getStatus()) {
                        case 0:
                            pageVo.setStatusStr("审批中");break;
                        case 1:
                            pageVo.setStatusStr("已审批");break;
                        default:
                            pageVo.setStatusStr("驳回");break;
                    };
                });
            }
        }
        return new PageInfo<>(pageVoList);
    }

    /**
     * 施工技术交底导出
     *
     * @param pageDto
     * @param response
     */
    @Override
    public void export(BuildTechBottomPageDTO pageDto, HttpServletResponse response) {
        //分页查询  页数暂定为5000
        pageDto.setPageSize(5000);
        PageInfo<BuildTechBottomPageVo> pageInfo = this.getPageInfo(pageDto);
        //导出
        ExcelWriter writer = ExcelUtil.getWriter();
        writer.addHeaderAlias("buildSectionName", "施工标段");
        writer.addHeaderAlias("buildUnits", "施工单位");
        writer.addHeaderAlias("buildTechBottom", "施工技术交底概述");
        writer.addHeaderAlias("createName", "登记人");
        writer.addHeaderAlias("checkDate", "登记时间");
        writer.addHeaderAlias("statusStr", "状态");
        writer.merge(5, "施工技术交底");
        writer.write(pageInfo.getList(), true);
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        String name = ("施工技术交底");
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
