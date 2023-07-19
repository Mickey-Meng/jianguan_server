package com.ruoyi.jianguan.business.quality.service.impl;

import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.ObjUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.enums.BimFlowKey;
import com.ruoyi.jianguan.business.quality.domain.dto.SubitemOpenPageDTO;
import com.ruoyi.jianguan.business.quality.domain.dto.SubitemOpenSaveDTO;
import com.ruoyi.jianguan.business.quality.domain.entity.SubitemOpen;
import com.ruoyi.jianguan.business.quality.domain.vo.SubitemOpenDetailVo;
import com.ruoyi.jianguan.business.quality.domain.vo.SubitemOpenPageVo;
import com.ruoyi.jianguan.business.quality.mapper.SubitemOpenMapper;
import com.ruoyi.common.core.domain.entity.FileModel;
import com.ruoyi.jianguan.business.quality.service.SubitemOpenService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ruoyi.flowable.service.ZjFGroupsProjectsService;
import com.ruoyi.common.core.domain.object.ResponseBase;
import com.ruoyi.flowable.domain.dto.FlowTaskCommentDto;
import com.ruoyi.flowable.service.FlowStaticPageService;
import com.ruoyi.common.core.sequence.util.IdUtil;
import com.ruoyi.jianguan.manage.project.domain.vo.JgProjectItemVo;
import com.ruoyi.jianguan.manage.project.service.IJgProjectItemService;
import com.ruoyi.system.service.ISysUserService;
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
 * 分项开工申请 服务实现类
 *
 * @author qiaoxulin
 * @date 2022-06-01
 */
@Service
public class SubitemOpenServiceImpl extends ServiceImpl<SubitemOpenMapper, SubitemOpen> implements SubitemOpenService {

    @Autowired
    private SubitemOpenMapper subitemOpenMapper;

    @Autowired
    private ZjFGroupsProjectsService projectsService;
    @Autowired
    private FlowStaticPageService flowStaticPageService;
    @Autowired
    private IJgProjectItemService jgProjectItemService;
    @Autowired
    private  ISysUserService userService;

    /**
     * 新增或者更新分项开工申请数据
     *
     * @param saveDto
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseBase addOrUpdate(SubitemOpenSaveDTO saveDto) {
        //属性copy
        SubitemOpen subitemOpen = new SubitemOpen();
        BeanUtils.copyProperties(saveDto, subitemOpen);
        //标准试验审批表附件
        subitemOpen.setExperimentAttachment(JSON.toJSONString(saveDto.getExperimentAttachment()));
        //专项施工方案审批表附件
        subitemOpen.setBuildAttachment(JSON.toJSONString(saveDto.getBuildAttachment()));
        //工艺试验审批表附件
        subitemOpen.setProcessAttachment(JSON.toJSONString(saveDto.getProcessAttachment()));
        //到场材料审批表附件
        subitemOpen.setMaterialAttachment(JSON.toJSONString(saveDto.getMaterialAttachment()));
        //到场设备审批表附件
        subitemOpen.setEquipmentAttachment(JSON.toJSONString(saveDto.getEquipmentAttachment()));
        //到场技术附件
        subitemOpen.setTechAttachment(JSON.toJSONString(saveDto.getTechAttachment()));
        //施工方案附件
        subitemOpen.setBuildPlanAttachment(JSON.toJSONString(saveDto.getBuildPlanAttachment()));
        //安全技术措施附件
        subitemOpen.setSecurityAttachment(JSON.toJSONString(saveDto.getSecurityAttachment()));
        //危险性较大项目附件
        subitemOpen.setRiskAttachment(JSON.toJSONString(saveDto.getRiskAttachment()));
        //环境保护措施附件
        subitemOpen.setEnvironmentAttachment(JSON.toJSONString(saveDto.getEnvironmentAttachment()));
        //环保、安全、质量、技术交底材料附件
        subitemOpen.setBottomAttachment(JSON.toJSONString(saveDto.getBottomAttachment()));
        //新增
        boolean isStartFlow = false;
        if (Objects.isNull(saveDto.getId())) {
            subitemOpen.setId(IdUtil.nextLongId());
            //判断是否是草稿
            if (saveDto.getDraftFlag() == 1) {
                isStartFlow = true;
            }
        } else {
            //判断是否是草稿转为正式数据
            SubitemOpen subitemOpe = this.getById(saveDto.getId());
            if (subitemOpe.getDraftFlag() == 0 && saveDto.getDraftFlag() == 1) {
                isStartFlow = true;
            }
        }
        // 编辑操作不修改审批状态
        if(ObjUtil.isNull(saveDto.getId())) {
            subitemOpen.setStatus(0);
        }
        boolean saveOrUpdate = this.saveOrUpdate(subitemOpen);
        //新增且保存成功
        if (saveOrUpdate && isStartFlow) {
            //发起流程
            String processDefinitionKey = BimFlowKey.分项开工申请.getName();
            String businessKey = subitemOpen.getId().toString();
            //设置流程的审批人
            Map<String, Object> auditUser = saveDto.getAuditUser();
            if (auditUser.isEmpty()) {
                throw new RuntimeException("流程的审批人不能为空！");
            }
            //发起流程
            try {
                FlowTaskCommentDto flowTaskComment = new FlowTaskCommentDto();
                flowTaskComment.setApprovalType("save");
                flowTaskComment.setComment("发起分项开工申请");
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
     * 通过id获取一条分项开工申请数据
     *
     * @param id
     * @return
     */
    @Override
    public SubitemOpenDetailVo getInfoById(Long id) {
        //查询
        SubitemOpen subitemOpen = this.getById(id);
        if (Objects.isNull(subitemOpen)) {
            return null;
        }
        SysUser liveUser = userService.selectUserById(subitemOpen.getLiveUser().longValue());
        SysUser buildUser = userService.selectUserById(subitemOpen.getBuildUser().longValue());
        SysUser checkUser = userService.selectUserById(subitemOpen.getCheckUser().longValue());

        //属性copy
        SubitemOpenDetailVo vo = new SubitemOpenDetailVo();
        BeanUtils.copyProperties(subitemOpen, vo);
        vo.setLiveUserName(liveUser.getNickName());
        vo.setBuildUserName(buildUser.getNickName());
        vo.setCheckUserName(checkUser.getNickName());

        //标准试验审批表附件
        vo.setExperimentAttachment(JSONArray.parseArray(subitemOpen.getExperimentAttachment(), FileModel.class));
        //专项施工方案审批表附件
        vo.setBuildAttachment(JSONArray.parseArray(subitemOpen.getBuildAttachment(), FileModel.class));
        //工艺试验审批表附件
        vo.setProcessAttachment(JSONArray.parseArray(subitemOpen.getProcessAttachment(), FileModel.class));
        //到场材料审批表附件
        vo.setMaterialAttachment(JSONArray.parseArray(subitemOpen.getMaterialAttachment(), FileModel.class));
        //到场设备审批表附件
        vo.setEquipmentAttachment(JSONArray.parseArray(subitemOpen.getEquipmentAttachment(), FileModel.class));
        //到场技术附件
        vo.setTechAttachment(JSONArray.parseArray(subitemOpen.getTechAttachment(), FileModel.class));
        //施工方案附件
        vo.setBuildPlanAttachment(JSONArray.parseArray(subitemOpen.getBuildPlanAttachment(), FileModel.class));
        //安全技术措施附件
        vo.setSecurityAttachment(JSONArray.parseArray(subitemOpen.getSecurityAttachment(), FileModel.class));
        //危险性较大项目附件
        vo.setRiskAttachment(JSONArray.parseArray(subitemOpen.getRiskAttachment(), FileModel.class));
        //环境保护措施附件
        vo.setEnvironmentAttachment(JSONArray.parseArray(subitemOpen.getEnvironmentAttachment(), FileModel.class));
        //环保、安全、质量、技术交底材料附件
        vo.setBottomAttachment(JSONArray.parseArray(subitemOpen.getBottomAttachment(), FileModel.class));
        return vo;
    }

    /**
     * 分页查询分项开工申请数据
     *
     * @param pageDto
     * @return
     */
    @Override
    public PageInfo<SubitemOpenPageVo> getPageInfo(SubitemOpenPageDTO pageDto) {
        //分页查询
        PageHelper.startPage(pageDto.getPageNum(), pageDto.getPageSize());
        List<SubitemOpenPageVo> pageVoList = subitemOpenMapper.getPageInfo(pageDto);
        //非空
        JgProjectItemVo jgProjectItemVo = jgProjectItemService.queryById(pageDto.getBuildSection().longValue()) ;
        String constructDept = jgProjectItemVo.getConstructDept();
        if (Objects.nonNull(pageVoList) && !pageVoList.isEmpty()) {
//            CompanyInfo companyInfo = projectsService.getCompanyInfoByProjectId(pageDto.getProjectId());
            //施工单位
//            Set<String> sgdws = companyInfo.getSgdws();
            pageVoList.forEach(pageVo -> {

                //施工单位
//                pageVo.setBuildUnits(constructDept);
                pageVo.setConstructdpts(constructDept);
                //状态
                pageVo.setStatusStr(pageVo.getStatus() == 0 ? "审批中" : "已审批");
            });
        }
        return new PageInfo<>(pageVoList);
    }

    /**
     * 分项开工申请导出
     *
     * @param pageDto
     * @param response
     */
    @Override
    public void export(SubitemOpenPageDTO pageDto, HttpServletResponse response) {
        //分页查询  页数暂定为5000
        pageDto.setPageSize(5000);
        PageInfo<SubitemOpenPageVo> pageInfo = this.getPageInfo(pageDto);
        //导出
        ExcelWriter writer = ExcelUtil.getWriter();
        writer.addHeaderAlias("projectName", "项目名称");
        writer.addHeaderAlias("buildUnits", "施工单位");
        writer.addHeaderAlias("openDate", "建议开工日期");
        writer.addHeaderAlias("endDate", "计划完工日期");
        writer.addHeaderAlias("place", "地点或桩号");
        writer.addHeaderAlias("liveUserName", "现场负责人");
        writer.addHeaderAlias("buildUserName", "施工员");
        writer.addHeaderAlias("checkUserName", "质检员");
        writer.addHeaderAlias("statusStr", "状态");
        writer.merge(8, "分项开工申请");
        writer.write(pageInfo.getList(), true);
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        String name = ("分项开工申请");
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
