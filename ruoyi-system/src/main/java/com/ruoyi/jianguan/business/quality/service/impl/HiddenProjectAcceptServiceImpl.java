package com.ruoyi.jianguan.business.quality.service.impl;

import cn.hutool.core.io.IoUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.core.dao.jianguan.ZjFGroupsProjectsDAO;
import com.ruoyi.common.enums.BimFlowKey;
import com.ruoyi.flowable.service.FlowAuditEntryService;
import com.ruoyi.jianguan.business.quality.domain.dto.HiddenProjectAcceptPageDTO;
import com.ruoyi.jianguan.business.quality.domain.dto.HiddenProjectAcceptSaveDTO;
import com.ruoyi.jianguan.business.quality.domain.entity.HiddenProjectAccept;
import com.ruoyi.jianguan.business.quality.domain.vo.HiddenProjectAccepDetailtVo;
import com.ruoyi.jianguan.business.quality.domain.vo.HiddenProjectAcceptPageVo;
import com.ruoyi.jianguan.common.service.UserService;
import com.ruoyi.common.core.domain.entity.FileModel;
import com.ruoyi.jianguan.business.quality.mapper.HiddenProjectAcceptMapper;
import com.ruoyi.jianguan.business.quality.service.HiddenProjectAcceptService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Sets;
import com.ruoyi.common.core.domain.object.ResponseBase;
import com.ruoyi.flowable.domain.dto.FlowTaskCommentDto;
import com.ruoyi.flowable.service.FlowStaticPageService;
import com.ruoyi.common.core.sequence.util.IdUtil;
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
 * 隐蔽工程验收记录 服务实现类
 *
 * @author qiaoxulin
 * @since 2022-05-12
 */
@Service
public class HiddenProjectAcceptServiceImpl extends ServiceImpl<HiddenProjectAcceptMapper, HiddenProjectAccept> implements HiddenProjectAcceptService {

    @Autowired
    private HiddenProjectAcceptMapper projectAcceptMapper;

    @Autowired
    private FlowStaticPageService flowStaticPageService;

    @Autowired
    private UserService userService;

    @Autowired
    private ZjFGroupsProjectsDAO projectsDAO;

    @Autowired
    private FlowAuditEntryService flowAuditEntryService;
    @Autowired
    private IJgProjectItemService jgProjectItemService;
    /**
     * 新增或者更新隐蔽工程验收记录数据
     *
     * @param saveDto
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseBase addOrUpdate(HiddenProjectAcceptSaveDTO saveDto) {
        HiddenProjectAccept projectAccept = new HiddenProjectAccept();
        //属性copy
        BeanUtils.copyProperties(saveDto, projectAccept);
        //附件
        projectAccept.setAttachment(JSON.toJSONString(saveDto.getAttachment()));
        //新增
        boolean isStartFlow = false;
        if (Objects.isNull(saveDto.getId())) {
            projectAccept.setId(IdUtil.nextLongId());
            //判断是否是草稿
            if (saveDto.getDraftFlag() == 1) {
                isStartFlow = true;
            }
        } else {
            //判断是否是草稿转为正式数据
            HiddenProjectAccept accept = this.getById(saveDto.getId());
            if (accept.getDraftFlag() == 0 && saveDto.getDraftFlag() == 1) {
                isStartFlow = true;
            }
        }
        boolean saveOrUpdate = this.saveOrUpdate(projectAccept);
        //保存成功发起流程
        if (saveOrUpdate && isStartFlow) {
            //发起质量检测申请
            String processDefinitionKey = BimFlowKey.隐蔽工程验收记录.getName();
            String businessKey = projectAccept.getId().toString();
            //设置流程的审批人
            Map<String, Object> auditUser = saveDto.getAuditUser();
            if (auditUser.isEmpty()) {
                throw new RuntimeException("流程的审批人不能为空！");
            }
            //发起流程
            try {
                FlowTaskCommentDto flowTaskComment = new FlowTaskCommentDto();
                flowTaskComment.setApprovalType("save");
                flowTaskComment.setComment("发起申请");
                JSONObject taskVariableData = new JSONObject(auditUser);
                flowStaticPageService.startAndTakeUserTask(processDefinitionKey, flowTaskComment, taskVariableData, null, null, saveDto.getCopyData(), businessKey);
            } catch (Exception e) {
                log.error("流程启动失败！e=" + e.getMessage());
                throw new RuntimeException("流程启动失败！e=" + e.getMessage());
            }
        }
        return ResponseBase.success("流程启动成功");
    }

    /**
     * 通过id获取一条隐蔽工程验收记录数据
     *
     * @param id
     * @return
     */
    @Override
    public HiddenProjectAccepDetailtVo getInfoById(Long id) {
        //查询
        HiddenProjectAccept projectAccept = this.getById(id);
        if (Objects.isNull(projectAccept)) {
            return null;
        }
        //转换对象
        HiddenProjectAccepDetailtVo detailtVo = new HiddenProjectAccepDetailtVo();
        BeanUtils.copyProperties(projectAccept, detailtVo);
        //附件
        detailtVo.setAttachment(JSONArray.parseArray(projectAccept.getAttachment(), FileModel.class));
        return detailtVo;
    }

    /**
     * 分页查询隐蔽工程验收记录数据
     *
     * @param pageDto
     * @return
     */
    @Override
    public PageInfo<HiddenProjectAcceptPageVo> getPageInfo(HiddenProjectAcceptPageDTO pageDto) {
        //分页查询
        PageHelper.startPage(pageDto.getPageNum(), pageDto.getPageSize());
        List<HiddenProjectAcceptPageVo> acceptList = projectAcceptMapper.getPageInfo(pageDto);
        //判断
        if (Objects.nonNull(acceptList) && !acceptList.isEmpty()) {
            //通过项目id查询项目详细信息（项目名、施工单位、监理单位、合同号等）
//            List<Map<String, Object>> companys = projectsDAO.getCompanyInfoByProjectId(pageDto.getProjectId());
            JgProjectItemVo jgProjectItemVo = jgProjectItemService.queryById(pageDto.getProjectId().longValue()) ;
            String constructDept = jgProjectItemVo.getConstructDept();
            if (Objects.nonNull(constructDept) && !constructDept.isEmpty()) {
                //循环属性
                acceptList.forEach(accept -> {
                    //状态
                    accept.setStatusStr(accept.getStatus() == 0 ? "进行中" : "已完成");
                    accept.setConstructdpts(jgProjectItemVo.getConstructDept());
                    accept.setSupervisorDepts(jgProjectItemVo.getSupervisorDept());

                });
            }
        }
        //分页对象返回
        return new PageInfo(acceptList);
    }

    /**
     * 隐蔽工程验收记录导出
     *
     * @param pageDto
     * @param response
     */
    @Override
    public void export(HiddenProjectAcceptPageDTO pageDto, HttpServletResponse response) {
        //分页查询  页数暂定为5000
        pageDto.setPageSize(5000);
        PageInfo<HiddenProjectAcceptPageVo> pageInfo = this.getPageInfo(pageDto);
        //导出
        ExcelWriter writer = ExcelUtil.getWriter();
        writer.addHeaderAlias("projectName", "项目名称");
        writer.addHeaderAlias("buildUnit", "施工单位");
        writer.addHeaderAlias("contractCode", "合同号");
        writer.addHeaderAlias("supervisorUnit", "监理单位");
        writer.addHeaderAlias("projectCode", "工程编号");
        writer.addHeaderAlias("buildSectionName", "施工标段");
        writer.addHeaderAlias("statusStr", "状态");
        writer.merge(6, "隐蔽工程验收记录");
        writer.write(pageInfo.getList(), true);
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        String name = ("隐蔽工程验收记录");
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
