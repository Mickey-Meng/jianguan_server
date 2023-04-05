package com.ruoyi.jianguan.contract.service.impl;

import cn.hutool.core.io.IoUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ruoyi.common.core.domain.entity.CompanyInfo;
import com.ruoyi.common.core.domain.object.ResponseBase;
import com.ruoyi.common.core.sequence.util.IdUtil;
import com.ruoyi.common.enums.BimFlowKey;
import com.ruoyi.flowable.domain.dto.FlowTaskCommentDto;
import com.ruoyi.flowable.service.FlowStaticPageService;
import com.ruoyi.flowable.service.ZjFGroupsProjectsService;
import com.ruoyi.jianguan.contract.domain.dto.EquipmentExitPageDTO;
import com.ruoyi.jianguan.contract.domain.dto.EquipmentExitSaveDTO;
import com.ruoyi.jianguan.contract.domain.entity.EquipmentExit;
import com.ruoyi.jianguan.contract.domain.entity.EquipmentInfo;
import com.ruoyi.jianguan.contract.domain.vo.EquipmentExitDetailVo;
import com.ruoyi.jianguan.contract.domain.vo.EquipmentExitPageVo;
import com.ruoyi.jianguan.contract.mapper.EquipmentExitMapper;
import com.ruoyi.jianguan.contract.service.EquipmentExitService;
import com.ruoyi.jianguan.contract.service.EquipmentInfoService;
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
 * 退场设备报验单 服务实现类
 *
 * @author qiaoxulin
 * @date 2022-05-27
 */
@Service
public class EquipmentExitServiceImpl extends ServiceImpl<EquipmentExitMapper, EquipmentExit> implements EquipmentExitService {

    @Autowired
    private EquipmentExitMapper equipmentExitMapper;

    @Autowired
    private ZjFGroupsProjectsService projectsService;

    @Autowired
    private FlowStaticPageService flowStaticPageService;

    @Autowired
    private EquipmentInfoService equipmentInfoService;

    /**
     * 新增或者更新退场设备报验单数据
     *
     * @param saveDto
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseBase addOrUpdate(EquipmentExitSaveDTO saveDto) {
        //属性copy
        EquipmentExit equipmentExit = new EquipmentExit();
        BeanUtils.copyProperties(saveDto, equipmentExit);
        //设备信息
        equipmentExit.setEquipmentInfo(JSON.toJSONString(saveDto.getEquipmentInfo()));
        List<EquipmentInfo> equipmentInfos = saveDto.getEquipmentInfo();
        //新增
        boolean isStartFlow = false;
        if (Objects.isNull(saveDto.getId())) {
            equipmentExit.setId(IdUtil.nextLongId());
            //判断是否是草稿
            if (saveDto.getDraftFlag() == 1) {
                isStartFlow = true;
            }
            //减去设备数量
            for (EquipmentInfo equipment : Objects.requireNonNull(equipmentInfos)) {
                EquipmentInfo byId = equipmentInfoService.getById(equipment.getId());
                if (Objects.isNull(byId)) {
                    continue;
                }
                EquipmentInfo equipmentInfo = new EquipmentInfo();
                equipmentInfo.setId(equipment.getId());
                int num = byId.getNum()- equipment.getNum();
                if (num <= 0){
                    continue;
                }
                equipmentInfo.setNum(num);
                equipmentInfoService.updateById(equipmentInfo);
            }
        } else {
            //判断是否是草稿转为正式数据
            EquipmentExit equipmentExi = this.getById(saveDto.getId());
            if (equipmentExi.getDraftFlag() == 0 && saveDto.getDraftFlag() == 1) {
                isStartFlow = true;
            }
        }
        //保存
        boolean saveOrUpdate = this.saveOrUpdate(equipmentExit);
        //新增成功
        if (saveOrUpdate && isStartFlow) {
            //发起流程
            String processDefinitionKey = BimFlowKey.设备退场报验.getName();
            String businessKey = equipmentExit.getId().toString();
            //设置流程的审批人
            Map<String, Object> auditUser = saveDto.getAuditUser();
            if (auditUser.isEmpty()) {
                throw new RuntimeException("流程的审批人不能为空！");
            }
            //发起流程
            try {
                FlowTaskCommentDto flowTaskComment = new FlowTaskCommentDto();
                flowTaskComment.setApprovalType("save");
                flowTaskComment.setComment("发起设备退场报验申请");
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
     * 通过id获取一条退场设备报验单数据
     *
     * @param id
     * @return
     */
    @Override
    public EquipmentExitDetailVo getInfoById(Long id) {
        //查询
        EquipmentExit equipmentExit = this.getById(id);
        if (Objects.isNull(equipmentExit)) {
            return null;
        }
        //属性转换
        EquipmentExitDetailVo vo = new EquipmentExitDetailVo();
        BeanUtils.copyProperties(equipmentExit, vo);
        //设备信息
        vo.setEquipmentInfo(JSONArray.parseArray(equipmentExit.getEquipmentInfo(), EquipmentInfo.class));
        //状态信息
        vo.setStatusStr(equipmentExit.getStatus() == 0 ? "进行中" : "已完成");
        return vo;
    }

    /**
     * 分页查询退场设备报验单数据
     *
     * @param pageDto
     * @return
     */
    @Override
    public PageInfo<EquipmentExitPageVo> getPageInfo(EquipmentExitPageDTO pageDto) {
        //分页查询
        PageHelper.startPage(pageDto.getPageNum(), pageDto.getPageSize());
        List<EquipmentExitPageVo> pageVoList = equipmentExitMapper.getPageInfo(pageDto);
        //非空判断
        if (Objects.nonNull(pageVoList) && !pageVoList.isEmpty()) {
            //通过项目id获取施工单位 监理单位等
            CompanyInfo companyInfo = projectsService.getCompanyInfoByProjectId(pageDto.getProjectId());
            //施工单位
            Set<String> sgdws = companyInfo.getSgdws();
            //监理单位
            Set<String> jldws = companyInfo.getJldws();
            if (Objects.nonNull(sgdws) && !sgdws.isEmpty()) {
                pageVoList.forEach(pageVo -> {
                    //施工单位
                    pageVo.setBuildUnits(sgdws);
                    //监理单位
                    pageVo.setSupervisorUnits(jldws);
                    //状态
                    if (pageVo.getStatus() == 0) {
                        pageVo.setStatusStr("进行中");
                    } else {
                        pageVo.setStatusStr("已完成");
                    }
                });
            }
        }
        return new PageInfo<>(pageVoList);
    }

    /**
     * 设备退场报验导出
     *
     * @param pageDto
     * @param response
     */
    @Override
    public void export(EquipmentExitPageDTO pageDto, HttpServletResponse response) {
        //分页查询  页数暂定为5000
        pageDto.setPageSize(5000);
        PageInfo<EquipmentExitPageVo> pageInfo = this.getPageInfo(pageDto);
        //导出
        ExcelWriter writer = ExcelUtil.getWriter();
        writer.addHeaderAlias("projectName", "项目名称");
        writer.addHeaderAlias("projectCode", "工程编号");
        writer.addHeaderAlias("buildUnits", "施工单位");
        writer.addHeaderAlias("contractCode", "合同段");
        writer.addHeaderAlias("supervisionBan", "监理标段");
        writer.addHeaderAlias("supervisorUnits", "监理单位");
        writer.addHeaderAlias("contractCode", "合同号");
        writer.addHeaderAlias("statusStr", "状态");
        writer.merge(7, "设备退场报验");
        writer.write(pageInfo.getList(), true);
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        String name = ("设备退场报验");
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
