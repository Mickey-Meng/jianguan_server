package com.ruoyi.jianguan.business.contract.service.impl;

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
import com.ruoyi.common.core.domain.entity.FileModel;
import com.ruoyi.common.core.domain.object.ResponseBase;
import com.ruoyi.common.core.domain.vo.EnumsVo;
import com.ruoyi.common.core.sequence.util.IdUtil;
import com.ruoyi.common.enums.BimFlowKey;
import com.ruoyi.common.enums.EquipmentTypeEnum;
import com.ruoyi.jianguan.business.contract.domain.dto.EquipmentEnterPageDTO;
import com.ruoyi.jianguan.business.contract.domain.dto.EquipmentEnterSaveDTO;
import com.ruoyi.jianguan.business.contract.domain.entity.EquipmentEnter;
import com.ruoyi.jianguan.business.contract.domain.entity.EquipmentInfo;
import com.ruoyi.jianguan.business.contract.domain.vo.EquipmentEnterDetailVo;
import com.ruoyi.jianguan.business.contract.domain.vo.EquipmentEnterPageVo;
import com.ruoyi.jianguan.business.contract.mapper.EquipmentEnterMapper;
import com.ruoyi.jianguan.business.contract.service.EquipmentEnterService;
import com.ruoyi.jianguan.business.contract.service.EquipmentInfoService;
import com.ruoyi.flowable.domain.dto.FlowTaskCommentDto;
import com.ruoyi.flowable.service.FlowStaticPageService;
import com.ruoyi.flowable.service.ZjFGroupsProjectsService;
import com.ruoyi.jianguan.manage.project.domain.vo.JgProjectItemVo;
import com.ruoyi.jianguan.manage.project.service.IJgProjectItemService;
import org.apache.commons.compress.utils.Lists;
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
 * 设备进场报验 服务实现类
 *
 * @author qiaoxulin
 * @date 2022-05-26
 */
@Service
public class EquipmentEnterServiceImpl extends ServiceImpl<EquipmentEnterMapper, EquipmentEnter> implements EquipmentEnterService {

    @Autowired
    private EquipmentEnterMapper equipmentEnterMapper;

    @Autowired
    private ZjFGroupsProjectsService projectsService;

    @Autowired
    private FlowStaticPageService flowStaticPageService;

    @Autowired
    private EquipmentInfoService equipmentInfoService;
    @Autowired
    private IJgProjectItemService jgProjectItemService;


    /**
     * 新增或者更新设备进场报验数据
     *
     * @param saveDto
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseBase addOrUpdate(EquipmentEnterSaveDTO saveDto) {
        //属性copy
        EquipmentEnter equipmentEnter = new EquipmentEnter();
        BeanUtils.copyProperties(saveDto, equipmentEnter);
        //装备信息
        equipmentEnter.setEquipmentInfo(JSON.toJSONString(saveDto.getEquipmentInfo()));
        //附件
        equipmentEnter.setAttachment(JSON.toJSONString(saveDto.getAttachment()));
        //装备信息
        List<EquipmentInfo> equipmentInfo = saveDto.getEquipmentInfo();
        //新增
        boolean isStartFlow = false;
        if (Objects.isNull(saveDto.getId())) {
            long longId = IdUtil.nextLongId();
            equipmentEnter.setId(longId);
            //判断是否是草稿
            if (saveDto.getDraftFlag() == 1) {
                isStartFlow = true;
            }
            //新增设备信息
            if (Objects.nonNull(equipmentInfo) && equipmentInfo.size() > 0){
                equipmentInfo.forEach(equipment -> {
                    equipment.setId(IdUtil.nextLongId());
                    equipment.setEnterId(longId);
                });
                equipmentInfoService.saveBatch(equipmentInfo);
            }
        } else {
            //判断是否是草稿转为正式数据
            EquipmentEnter equipmentEnte = this.getById(saveDto.getId());
            if (equipmentEnte.getDraftFlag() == 0 && saveDto.getDraftFlag() == 1) {
                isStartFlow = true;
            }
            //编辑 1.先删除 再新增
            equipmentInfoService.removeByEnterId(saveDto.getId());
            equipmentInfoService.saveBatch(equipmentInfo);
        }
        //保存
        boolean saveOrUpdate = this.saveOrUpdate(equipmentEnter);

        //新增成功
        if (saveOrUpdate && isStartFlow) {
            //发起流程
            String processDefinitionKey = BimFlowKey.设备进场报验.getName();
            String businessKey = equipmentEnter.getId().toString();
            //设置流程的审批人
            Map<String, Object> auditUser = saveDto.getAuditUser();
            if (auditUser.isEmpty()) {
                throw new RuntimeException("流程的审批人不能为空！");
            }
            //发起流程
            try {
                FlowTaskCommentDto flowTaskComment = new FlowTaskCommentDto();
                flowTaskComment.setApprovalType("save");
                flowTaskComment.setComment("发起设备进场报验申请");
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
     * 通过id获取一条设备进场报验数据
     *
     * @param id
     * @return
     */
    @Override
    public EquipmentEnterDetailVo getInfoById(Long id) {
        //查询
        EquipmentEnter equipmentEnter = this.getById(id);
        if (Objects.isNull(equipmentEnter)) {
            return null;
        }
        //属性转换
        EquipmentEnterDetailVo vo = new EquipmentEnterDetailVo();
        BeanUtils.copyProperties(equipmentEnter, vo);
        //设备信息
        vo.setEquipmentInfo(JSONArray.parseArray(equipmentEnter.getEquipmentInfo(), EquipmentInfo.class));
        //附件
        vo.setAttachment(JSONArray.parseArray(equipmentEnter.getAttachment(), FileModel.class));
        return vo;
    }

    /**
     * 分页查询设备进场报验数据
     *
     * @param pageDto
     * @return
     */
    @Override
    public PageInfo<EquipmentEnterPageVo> getPageInfo(EquipmentEnterPageDTO pageDto) {
        //分页查询
        PageHelper.startPage(pageDto.getPageNum(), pageDto.getPageSize());
        List<EquipmentEnterPageVo> pageVoList = equipmentEnterMapper.getPageInfo(pageDto);
        //非空
        if (Objects.nonNull(pageVoList) && !pageVoList.isEmpty()) {
            //通过项目id获取施工单位 监理单位等
//            CompanyInfo companyInfo = projectsService.getCompanyInfoByProjectId(pageDto.getProjectId());

            JgProjectItemVo jgProjectItemVo = jgProjectItemService.queryById(pageDto.getProjectId().longValue()) ;
            String constructDept = jgProjectItemVo.getConstructDept();
            //施工单位
//            Set<String> sgdws = companyInfo.getSgdws();
            //监理单位
//            Set<String> jldws = companyInfo.getJldws();

            if (Objects.nonNull(constructDept) && !constructDept.isEmpty()) {
                pageVoList.forEach(pageVo -> {
                    //施工单位
//                    pageVo.setBuildUnits(sgdws);
                    pageVo.setConstructdpts(constructDept);
                    //监理单位
//                    pageVo.setSupervisorUnits(jldws);
                    pageVo.setSupervisordpts(jgProjectItemVo.getSupervisorDept());
                    //状态
                    if (pageVo.getStatus() == 0) {
                        pageVo.setStatusStr("进行中");
                    } else {
                        pageVo.setStatusStr("已完成");
                    }
                    //设备信息
                    List<EquipmentInfo> equipmentInfos = equipmentInfoService.getByEnterId(pageVo.getId());
                    pageVo.setEquipmentInfos(equipmentInfos);
                });
            }
        }
        return new PageInfo<>(pageVoList);
    }

    /**
     * 获取设备类型枚举
     *
     * @return
     */
    @Override
    public List<EnumsVo> getEquipmentEnum() {
        //返回
        List<EnumsVo> result = Lists.newArrayList();
        List<EquipmentTypeEnum> equipmentTypeEnums = EquipmentTypeEnum.LIST;
        if (!equipmentTypeEnums.isEmpty()) {
            equipmentTypeEnums.forEach(equipment -> {
                EnumsVo vo = new EnumsVo();
                vo.setCode(equipment.getCode());
                vo.setDesc(equipment.getDes());
                result.add(vo);
            });
        }
        return result;
    }

    /**
     * 设备进场报验导出
     *
     * @param pageDto
     * @param response
     */
    @Override
    public void export(EquipmentEnterPageDTO pageDto, HttpServletResponse response) {
        //分页查询  页数暂定为5000
        pageDto.setPageSize(5000);
        PageInfo<EquipmentEnterPageVo> pageInfo = this.getPageInfo(pageDto);
        //导出
        ExcelWriter writer = ExcelUtil.getWriter();
        writer.addHeaderAlias("projectName", "项目名称");
        writer.addHeaderAlias("projectCode", "工程编号");
        writer.addHeaderAlias("buildUnits", "施工单位");
        writer.addHeaderAlias("contractCode", "合同段");
        writer.addHeaderAlias("supervisionBan", "监理办");
        writer.addHeaderAlias("supervisorUnits", "监理单位");
        writer.addHeaderAlias("contractCode", "合同号");
        writer.addHeaderAlias("statusStr", "状态");
        writer.merge(7, "设备进场报验");
        writer.write(pageInfo.getList(), true);
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        String name = ("设备进场报验");
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
