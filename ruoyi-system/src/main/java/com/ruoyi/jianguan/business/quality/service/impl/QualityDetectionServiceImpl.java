package com.ruoyi.jianguan.business.quality.service.impl;

import cn.hutool.core.io.IoUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.core.domain.vo.EnumsVo;
import com.ruoyi.common.enums.BimFlowKey;
import com.ruoyi.common.enums.MaterialsEnum;
import com.ruoyi.flowable.service.FlowAuditEntryService;
import com.ruoyi.jianguan.business.quality.domain.dto.QualityDetectionPageDTO;
import com.ruoyi.jianguan.business.quality.domain.dto.QualityDetectionSaveDTO;
import com.ruoyi.jianguan.business.quality.domain.entity.QualityDetection;
import com.ruoyi.jianguan.business.quality.domain.vo.QualityDetectionDetailVo;
import com.ruoyi.jianguan.business.quality.domain.vo.QualityDetectionPageVo;
import com.ruoyi.jianguan.common.service.UserService;
import com.ruoyi.common.core.domain.entity.FileModel;
import com.ruoyi.jianguan.business.quality.mapper.QualityDetectionMapper;
import com.ruoyi.jianguan.business.quality.service.QualityDetectionService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Sets;
import com.ruoyi.common.core.domain.object.ResponseBase;
import com.ruoyi.flowable.domain.dto.FlowTaskCommentDto;
import com.ruoyi.flowable.service.FlowStaticPageService;
import com.ruoyi.common.core.sequence.util.IdUtil;
import io.netty.util.internal.StringUtil;
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
 * 质量检测 服务实现类
 *
 * @author qiaoxulin
 * @date 2022-05-11
 */
@Service
public class QualityDetectionServiceImpl extends ServiceImpl<QualityDetectionMapper, QualityDetection> implements QualityDetectionService {

    @Autowired
    private QualityDetectionMapper qualityDetectionMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private FlowStaticPageService flowStaticPageService;

    @Autowired
    private FlowAuditEntryService flowAuditEntryService;

    /**
     * 新增或者更新质量检测数据
     *
     * @param qualityDetectionDto
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseBase addOrUpdate(QualityDetectionSaveDTO qualityDetectionDto) {
        if (Objects.isNull(qualityDetectionDto)) {
            return ResponseBase.error("参数不能为空！");
        }
        //属性转换
        QualityDetection qualityDetection = new QualityDetection();
        BeanUtils.copyProperties(qualityDetectionDto, qualityDetection);
        //检测信息
        qualityDetection.setDetectionInfo(JSON.toJSONString(qualityDetectionDto.getDetectionInfo()));
        //检测报告
        qualityDetection.setDetectionReport(JSON.toJSONString(qualityDetectionDto.getDetectionReport()));
        //出厂信息
        qualityDetection.setFactoryInfo(JSON.toJSONString(qualityDetectionDto.getFactoryInfo()));
        //其他附件
        qualityDetection.setOtherAttachment(JSON.toJSONString(qualityDetectionDto.getOtherAttachment()));
        //新增
        boolean isStartFlow = false;
        if (Objects.isNull(qualityDetectionDto.getId())) {
            qualityDetection.setId(IdUtil.nextLongId());
            //判断是否是草稿
            if (qualityDetectionDto.getDraftFlag() == 1) {
                isStartFlow = true;
            }
        } else {
            //判断是否是草稿转为正式数据
            QualityDetection detection = this.getById(qualityDetectionDto.getId());
            if (detection.getDraftFlag() == 0 && qualityDetectionDto.getDraftFlag() == 1) {
                isStartFlow = true;
            }
        }
        boolean saveOrUpdate = this.saveOrUpdate(qualityDetection);
        //保存成功发起流程
        if (saveOrUpdate && isStartFlow) {
            //发起质量检测申请 并完成第一步
            String processDefinitionKey = BimFlowKey.质量检测.getName();
            String businessKey = qualityDetection.getId().toString();
            //审批人
            Map<String, Object> auditUser = qualityDetectionDto.getAuditUser();
            if (auditUser.isEmpty()) {
                throw new RuntimeException("流程的审批人不能为空！");
            }
            //发起流程
            try {
                FlowTaskCommentDto flowTaskComment = new FlowTaskCommentDto();
                flowTaskComment.setApprovalType("save");
                flowTaskComment.setComment("发起质量检测申请");
                JSONObject taskVariableData = new JSONObject(auditUser);
                flowStaticPageService.startAndTakeUserTask(processDefinitionKey, flowTaskComment, taskVariableData, null, null, qualityDetectionDto.getCopyData(), businessKey);
                return ResponseBase.success("流程启动成功");
            } catch (Exception e) {
                log.error("流程启动失败！e=" + e.getMessage());
                throw new RuntimeException("流程启动失败！e=" + e.getMessage());
            }
        }
        return ResponseBase.success(true);
    }

    /**
     * 通过id获取一条质量检测数据
     *
     * @param id
     * @return
     */
    @Override
    public QualityDetectionDetailVo getInfoById(Long id) {
        //查询数据
        QualityDetection qualityDetection = this.getById(id);
        if (Objects.isNull(qualityDetection)) {
            return null;
        }
        //属性转换
        QualityDetectionDetailVo detailVo = new QualityDetectionDetailVo();
        BeanUtils.copyProperties(qualityDetection, detailVo);
        //检测信息
        detailVo.setDetectionInfo(JSONArray.parseArray(qualityDetection.getDetectionInfo(), QualityDetectionSaveDTO.DetectionInfo.class));
        //检测报告
        detailVo.setDetectionReport(JSONArray.parseArray(qualityDetection.getDetectionReport(), FileModel.class));
        //出厂信息
        detailVo.setFactoryInfo(JSONArray.parseArray(qualityDetection.getFactoryInfo(), FileModel.class));
        //其他附件
        detailVo.setOtherAttachment(JSONArray.parseArray(qualityDetection.getOtherAttachment(), FileModel.class));
        return detailVo;
    }

    /**
     * 质量检测分页查询
     *
     * @param pageDto
     * @return
     */
    @Override
    public PageInfo<QualityDetectionPageVo> getPageInfo(QualityDetectionPageDTO pageDto) {
        //分页
        PageHelper.startPage(pageDto.getPageNum(), pageDto.getPageSize());
        List<QualityDetectionPageVo> qualityDetectionList = qualityDetectionMapper.getPageInfo(pageDto);
        //判断
        if (Objects.nonNull(qualityDetectionList) && !qualityDetectionList.isEmpty()) {
            //属性处理
            qualityDetectionList.forEach(qualityDetection -> {
                //检测信息
                String detectionInfo = qualityDetection.getDetectionInfo();
                if (!StringUtil.isNullOrEmpty(detectionInfo)) {
                    List<QualityDetectionSaveDTO.DetectionInfo> detectionInfos = JSONArray.parseArray(detectionInfo,
                            QualityDetectionSaveDTO.DetectionInfo.class);
                    if (!detectionInfos.isEmpty()) {
                        //材料名称
                        Set<String> materialsName = Sets.newHashSet();
                        //工程部位
                        Set<String> projectParts = Sets.newHashSet();
                        //材料规格
                        Set<String> materialSpecification = Sets.newHashSet();
                        //检测结果
                        Set<String> testResult = Sets.newHashSet();
                        //循环
                        detectionInfos.forEach(detection -> {
                            materialsName.add(detection.getName());
                            projectParts.add(detection.getProjectPart());
                            materialSpecification.add(detection.getSpecification());
                            //转换检测结果
                            Integer detectionResult = detection.getDetectionResult();
                            if (Objects.nonNull(detectionResult)) {
                                if (detectionResult == 0) {
                                    testResult.add("合格");
                                } else {
                                    testResult.add("不合格");
                                }
                            }
                        });
                        qualityDetection.setMaterialsName(materialsName);
                        qualityDetection.setProjectParts(projectParts);
                        qualityDetection.setMaterialSpecification(materialSpecification);
                        qualityDetection.setTestResult(testResult);
                    }
                }
            });
        }
        return new PageInfo<>(qualityDetectionList);
    }

    /**
     * 获取材料枚举
     *
     * @return
     */
    @Override
    public List<EnumsVo> getMaterialEnum() {
        //返回
        List<EnumsVo> result = Lists.newArrayList();
        List<MaterialsEnum> materialsEnums = MaterialsEnum.LIST;
        if (!materialsEnums.isEmpty()) {
            materialsEnums.forEach(materials -> {
                EnumsVo vo = new EnumsVo();
                vo.setCode(materials.getCode());
                vo.setDesc(materials.getDes());
                result.add(vo);
            });
        }
        return result;
    }

    /**
     * 质量检测导出
     *
     * @param pageDto
     */
    @Override
    public void export(QualityDetectionPageDTO pageDto, HttpServletResponse response) {
        //分页查询  页数暂定为5000
        pageDto.setPageSize(5000);
        PageInfo<QualityDetectionPageVo> pageInfo = this.getPageInfo(pageDto);
        //导出
        ExcelWriter writer = ExcelUtil.getWriter();
        writer.addHeaderAlias("buildSectionName", "标段");
        writer.addHeaderAlias("materialsName", "材料名称");
        writer.addHeaderAlias("fillDate", "填报日期");
        writer.addHeaderAlias("materialSpecification", "材料规格");
        writer.addHeaderAlias("projectParts", "工程部位");
        writer.addHeaderAlias("testResult", "检测结果");
        writer.merge(5, "质量检测");
        writer.write(pageInfo.getList(), true);
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        String name = ("质量检测");
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
