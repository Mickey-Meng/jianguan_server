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
import com.ruoyi.common.core.domain.entity.FileModel;
import com.ruoyi.common.core.sequence.util.IdUtil;
import com.ruoyi.common.enums.BimFlowKey;
import com.ruoyi.flowable.domain.dto.FlowTaskCommentDto;
import com.ruoyi.flowable.service.FlowStaticPageService;
import com.ruoyi.flowable.service.ZjFGroupsProjectsService;
import com.ruoyi.jianguan.business.quality.domain.dto.QualityActivityPageDTO;
import com.ruoyi.jianguan.business.quality.domain.dto.QualityActivitySaveDTO;
import com.ruoyi.jianguan.business.quality.domain.entity.QualityActivity;
import com.ruoyi.jianguan.business.quality.domain.vo.QualityActivityDetailVo;
import com.ruoyi.jianguan.business.quality.domain.vo.QualityActivityPageVo;
import com.ruoyi.jianguan.business.quality.mapper.QualityActivityMapper;
import com.ruoyi.jianguan.business.quality.service.QualityActivityService;
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
 * 质量活动 服务实现类
 *
 * @author qiaoxulin
 * @date 2022-06-11
 */
@Service
public class QualityActivityServiceImpl extends ServiceImpl<QualityActivityMapper, QualityActivity> implements QualityActivityService {

    @Autowired
    private FlowStaticPageService flowStaticPageService;

    @Autowired
    private QualityActivityMapper qualityActivityMapper;

    @Autowired
    private ZjFGroupsProjectsService projectsService;
    @Autowired
    private IJgProjectItemService jgProjectItemService;
    /**
     * 新增或者更新质量活动数据
     *
     * @param saveDto
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseBase addOrUpdate(QualityActivitySaveDTO saveDto) {
        //属性copy
        QualityActivity qualityActivity = new QualityActivity();
        BeanUtils.copyProperties(saveDto, qualityActivity);
        //附件
        qualityActivity.setAttachment(JSON.toJSONString(saveDto.getAttachment()));
        //新增
        boolean isStartFlow = false;
        if (Objects.isNull(qualityActivity.getId())) {
            qualityActivity.setId(IdUtil.nextLongId());
            //判断是否是草稿
            if (saveDto.getDraftFlag() == 1) {
                isStartFlow = true;
            }
        } else {
            //判断是否是草稿转为正式数据
            QualityActivity qualityActivit = this.getById(saveDto.getId());
            if (qualityActivit.getDraftFlag() == 0 && saveDto.getDraftFlag() == 1) {
                isStartFlow = true;
            }
        }

        // 编辑操作不修改审批状态
        if(ObjUtil.isNull(saveDto.getId())) {
            qualityActivity.setStatus(0);
        }
        boolean saveOrUpdate = this.saveOrUpdate(qualityActivity);
        //保存成功发起流程
        if (saveOrUpdate && isStartFlow) {
            //发起质量检测申请 并完成第一步
            String processDefinitionKey = BimFlowKey.质量活动.getName();
            String businessKey = qualityActivity.getId().toString();
            //审批人
            Map<String, Object> auditUser = saveDto.getAuditUser();
            if (auditUser.isEmpty()) {
                throw new RuntimeException("流程的审批人不能为空！");
            }
            //发起流程
            try {
                FlowTaskCommentDto flowTaskComment = new FlowTaskCommentDto();
                flowTaskComment.setApprovalType("save");
                flowTaskComment.setComment("发起质量检测申请");
                JSONObject taskVariableData = new JSONObject(auditUser);
                flowStaticPageService.startAndTakeUserTask(processDefinitionKey, flowTaskComment, taskVariableData, null, null, saveDto.getCopyData(), businessKey);
                return ResponseBase.success("流程启动成功");
            } catch (Exception e) {
                log.error("流程启动失败！e=" + e.getMessage());
                throw new RuntimeException("流程启动失败！e=" + e.getMessage());
            }
        }
        return ResponseBase.success(true);
    }

    /**
     * 通过id获取一条质量活动数据
     *
     * @param id
     * @return
     */

    @Override
    public QualityActivityDetailVo getInfoById(Long id) {
        //查询
        QualityActivity qualityActivity = this.getById(id);
        if (Objects.isNull(qualityActivity)) {
            return null;
        }
        //属性转换
        QualityActivityDetailVo vo = new QualityActivityDetailVo();
        BeanUtils.copyProperties(qualityActivity, vo);
        //附件
        vo.setAttachment(JSONArray.parseArray(qualityActivity.getAttachment(), FileModel.class));
        return vo;
    }

    /**
     * 分页查询质量活动数据
     *
     * @param pageDto
     * @return
     */
    @Override
    public PageInfo<QualityActivityPageVo> getPageInfo(QualityActivityPageDTO pageDto) {
        //分页查询
        PageHelper.startPage(pageDto.getPageNum(), pageDto.getPageSize());
        List<QualityActivityPageVo> pageVoList = qualityActivityMapper.getPageInfo(pageDto);
        //非空
        if (Objects.nonNull(pageVoList) && !pageVoList.isEmpty()) {
//            CompanyInfo companyInfo = projectsService.getCompanyInfoByProjectId(pageDto.getProjectId());
            //施工单位
//            Set<String> sgdws = companyInfo.getSgdws();
            JgProjectItemVo jgProjectItemVo = jgProjectItemService.queryById(pageDto.getProjectId().longValue()) ;
            String constructDept = jgProjectItemVo.getConstructDept();
            pageVoList.forEach(pageVo -> {
                //施工单位
//                pageVo.setBuildUnits(sgdws);
                pageVo.setConstructdpts(constructDept);
                //状态
                if(pageVo.getStatus() == 0) {
                    pageVo.setStatusStr("审批中");
                } else if(pageVo.getStatus() == 1) {
                    pageVo.setStatusStr("已审批");
                }else {
                    pageVo.setStatusStr("已驳回");
                }
            });
        }
        return new PageInfo<>(pageVoList);
    }

    /**
     * 质量活动导出
     *
     * @param pageDto
     * @param response
     */
    @Override
    public void export(QualityActivityPageDTO pageDto, HttpServletResponse response) {
        //分页查询  页数暂定为5000
        pageDto.setPageSize(5000);
        PageInfo<QualityActivityPageVo> pageInfo = this.getPageInfo(pageDto);
        //导出
        ExcelWriter writer = ExcelUtil.getWriter();
        writer.addHeaderAlias("buildSectionName", "施工标段");
        writer.addHeaderAlias("buildUnits", "施工单位");
        writer.addHeaderAlias("activityInfo", "活动内容");
        writer.addHeaderAlias("createName", "登记人");
        writer.addHeaderAlias("createTime", "登记时间");
        writer.addHeaderAlias("statusStr", "状态");
        writer.merge(5, "质量活动");
        writer.write(pageInfo.getList(), true);
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        String name = ("质量活动");
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
