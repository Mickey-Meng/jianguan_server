package com.ruoyi.jianguan.business.quality.service.impl;

import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.ObjUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.core.domain.entity.Conponent;
import com.ruoyi.common.enums.BimFlowKey;
import com.ruoyi.jianguan.business.quality.domain.dto.FirstAcceptPageDTO;
import com.ruoyi.jianguan.business.quality.domain.dto.FirstAcceptSaveDTO;
import com.ruoyi.jianguan.business.quality.domain.entity.FirstAccept;
import com.ruoyi.jianguan.business.quality.domain.vo.FirstAcceptDetailVo;
import com.ruoyi.jianguan.business.quality.domain.vo.FirstAcceptPageVo;
import com.ruoyi.common.core.domain.entity.FileModel;
import com.ruoyi.jianguan.business.quality.mapper.FirstAcceptMapper;
import com.ruoyi.jianguan.business.quality.service.FirstAcceptService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ruoyi.common.core.domain.object.ResponseBase;
import com.ruoyi.flowable.domain.dto.FlowTaskCommentDto;
import com.ruoyi.flowable.service.FlowStaticPageService;
import com.ruoyi.common.core.sequence.util.IdUtil;
import com.ruoyi.jianguan.common.dao.ConponentDAO;
import com.ruoyi.jianguan.common.service.ComponentSevice;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 首件认可 服务实现类
 *
 * @author qiaoxulin
 * @date 2022-06-01
 */
@Service
public class FirstAcceptServiceImpl extends ServiceImpl<FirstAcceptMapper, FirstAccept> implements FirstAcceptService {

    @Autowired
    private FirstAcceptMapper firstAcceptMapper;

    @Autowired
    ComponentSevice componentSevice;
    @Autowired
    @Qualifier("zjConponentDAO")
    ConponentDAO conponentDAO;
    @Autowired
    private FlowStaticPageService flowStaticPageService;

    /**
     * 新增或者更新首件认可数据
     *
     * @param saveDto
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseBase addOrUpdate(FirstAcceptSaveDTO saveDto) {
        //属性copy
        FirstAccept firstAccept = new FirstAccept();
        BeanUtils.copyProperties(saveDto, firstAccept);
        //施工技术、工艺方案说明和图表
        firstAccept.setBuildTechAttachment(JSON.toJSONString(saveDto.getBuildTechAttachment()));
        //测量放样资料
        firstAccept.setMeasureAttachment(JSON.toJSONString(saveDto.getMeasureAttachment()));
        //材料出厂保证书、材料检测试验报告
        firstAccept.setMaterialAttachment(JSON.toJSONString(saveDto.getMaterialAttachment()));
        //机械的主要技术标准及最大生产能力
        firstAccept.setMechanicalAttachment(JSON.toJSONString(saveDto.getMechanicalAttachment()));
        //批准的标准试验报告
        firstAccept.setTestAttachment(JSON.toJSONString(saveDto.getTestAttachment()));
        //开工申请
        firstAccept.setOpenAttachment(JSON.toJSONString(saveDto.getOpenAttachment()));
        //质量保证资料
        firstAccept.setQualityAttachment(JSON.toJSONString(saveDto.getQualityAttachment()));
        //影像资料
        firstAccept.setImageVideo(JSON.toJSONString(saveDto.getImageVideo()));
        //首件工程总结
        firstAccept.setFirstProjectVideo(JSON.toJSONString(saveDto.getFirstProjectVideo()));
        //新增
        boolean isStartFlow = false;
        if (Objects.isNull(saveDto.getId())) {
            firstAccept.setId(IdUtil.nextLongId());
            //判断是否是草稿
            if (saveDto.getDraftFlag() == 1) {
                isStartFlow = true;
            }
        } else {
            //判断是否是草稿转为正式数据
            FirstAccept firstAccep = this.getById(saveDto.getId());
            if (firstAccep.getDraftFlag() == 0 && saveDto.getDraftFlag() == 1) {
                isStartFlow = true;
            }
        }
        // 编辑操作不修改审批状态
        if(ObjUtil.isNull(saveDto.getId())) {
            firstAccept.setStatus(0);
        }
        //保存
        boolean saveOrUpdate = this.saveOrUpdate(firstAccept);
        //新增且保存成功
        if (saveOrUpdate && isStartFlow) {
            //发起流程
            String processDefinitionKey = BimFlowKey.首件认可.getName();
            String businessKey = firstAccept.getId().toString();
            //设置流程的审批人
            Map<String, Object> auditUser = saveDto.getAuditUser();
            if (auditUser.isEmpty()) {
                throw new RuntimeException("流程的审批人不能为空！");
            }
            //发起流程
            try {
                FlowTaskCommentDto flowTaskComment = new FlowTaskCommentDto();
                flowTaskComment.setApprovalType("save");
                flowTaskComment.setComment("发起首件认可申请");
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
     * 通过id获取一条首件认可数据
     *
     * @param id
     * @return
     */
    @Override
    public FirstAcceptDetailVo getInfoById(Long id) {
        //查询
        FirstAccept firstAccept = this.getById(id);
        if (Objects.isNull(firstAccept)) {
            return null;
        }


        //属性copy
        FirstAcceptDetailVo vo = new FirstAcceptDetailVo();
        BeanUtils.copyProperties(firstAccept, vo);
        // yangaogao 20230603   #245 返回构件详情
        Conponent conponent = conponentDAO.getDataById(firstAccept.getSubProject());
        vo.setConponent(conponent);
        //施工技术、工艺方案说明和图表
        vo.setBuildTechAttachment(JSONArray.parseArray(firstAccept.getBuildTechAttachment(), FileModel.class));
        //测量放样资料
        vo.setMeasureAttachment(JSONArray.parseArray(firstAccept.getMeasureAttachment(), FileModel.class));
        //材料出厂保证书、材料检测试验报告
        vo.setMaterialAttachment(JSONArray.parseArray(firstAccept.getMaterialAttachment(), FileModel.class));
        //机械的主要技术标准及最大生产能力
        vo.setMechanicalAttachment(JSONArray.parseArray(firstAccept.getMechanicalAttachment(), FileModel.class));
        //批准的标准试验报告
        vo.setTestAttachment(JSONArray.parseArray(firstAccept.getTestAttachment(), FileModel.class));
        //开工申请
        vo.setOpenAttachment(JSONArray.parseArray(firstAccept.getOpenAttachment(), FileModel.class));
        //质量保证资料
        vo.setQualityAttachment(JSONArray.parseArray(firstAccept.getQualityAttachment(), FileModel.class));
        //影像资料
        vo.setImageVideo(JSONArray.parseArray(firstAccept.getImageVideo(), FileModel.class));
        //首件工程总结
        vo.setFirstProjectVideo(JSONArray.parseArray(firstAccept.getFirstProjectVideo(), FileModel.class));
        return vo;
    }

    /**
     * 分页查询首件认可数据
     *
     * @param pageDto
     * @return
     */
    @Override
    public PageInfo<FirstAcceptPageVo> getPageInfo(FirstAcceptPageDTO pageDto) {
        //分页查询
        PageHelper.startPage(pageDto.getPageNum(), pageDto.getPageSize());
        List<FirstAcceptPageVo> pageVoList = firstAcceptMapper.getPageInfo(pageDto);
        //非空
        if (Objects.nonNull(pageVoList) && !pageVoList.isEmpty()) {
            pageVoList.forEach(pageVo -> {
                //状态
                pageVo.setStatusStr(pageVo.getStatus() == 0 ? "进行中" : "已完成");
            });
        }
        return new PageInfo<>(pageVoList);
    }

    /**
     * 首件认可导出
     *
     * @param pageDto
     * @param response
     */
    @Override
    public void export(FirstAcceptPageDTO pageDto, HttpServletResponse response) {
        //分页查询  页数暂定为5000
        pageDto.setPageSize(5000);
        PageInfo<FirstAcceptPageVo> pageInfo = this.getPageInfo(pageDto);
        //导出
        ExcelWriter writer = ExcelUtil.getWriter();
        writer.addHeaderAlias("buildSectionName", "施工标段");
        writer.addHeaderAlias("unitProjectName", "单位工程");
        writer.addHeaderAlias("firstProjectName", "首件工程名称");
        writer.addHeaderAlias("subProjectDetail", "具体分项");
        writer.addHeaderAlias("buildDate", "实施日期");
        writer.addHeaderAlias("firstPassExplain", "首件工程通过情况");
        writer.addHeaderAlias("createName", "创建人");
        writer.merge(6, "首件认可");
        writer.write(pageInfo.getList(), true);
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        String name = ("首件认可");
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
