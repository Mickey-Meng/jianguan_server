package com.ruoyi.jianguan.quality.service.impl;

import cn.hutool.core.io.IoUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.enums.BimFlowKey;
import com.ruoyi.jianguan.quality.domain.dto.SupervisionOrderPageDTO;
import com.ruoyi.jianguan.quality.domain.dto.SupervisionOrderSaveDTO;
import com.ruoyi.common.core.domain.entity.FileModel;
import com.ruoyi.jianguan.quality.domain.entity.SupervisionOrder;
import com.ruoyi.jianguan.quality.mapper.SupervisionOrderMapper;
import com.ruoyi.jianguan.quality.service.SupervisionOrderService;
import com.ruoyi.jianguan.quality.domain.vo.SupervisionOrderDetailVo;
import com.ruoyi.jianguan.quality.domain.vo.SupervisionOrderPageVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 监理指令 服务实现类
 *
 * @author qiaoxulin
 * @date 2022-06-14
 */
@Service
public class SupervisionOrderServiceImpl extends ServiceImpl<SupervisionOrderMapper, SupervisionOrder> implements SupervisionOrderService {

    @Autowired
    private FlowStaticPageService flowStaticPageService;

    @Autowired
    private SupervisionOrderMapper supervisionOrderMapper;

    /**
     * 新增或者更新监理指令数据
     *
     * @param saveDto
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseBase addOrUpdate(SupervisionOrderSaveDTO saveDto) {
        //属性copy
        SupervisionOrder supervisionOrder = new SupervisionOrder();
        BeanUtils.copyProperties(saveDto, supervisionOrder);
        //问题照片
        supervisionOrder.setProblemPhotoAttachment(JSON.toJSONString(saveDto.getProblemPhotoAttachment()));
        //其他附件
        supervisionOrder.setOtherAttachment(JSON.toJSONString(saveDto.getOtherAttachment()));
        //整改照片
        supervisionOrder.setReplyPhotoAttachment(JSON.toJSONString(saveDto.getReplyPhotoAttachment()));
        //其他附件
        supervisionOrder.setReplyOtherAttachment(JSON.toJSONString(saveDto.getReplyOtherAttachment()));
        //新增
        boolean isStartFlow = false;
        if (Objects.isNull(saveDto.getId())) {
            supervisionOrder.setId(IdUtil.nextLongId());
            //判断是否是草稿
            if (saveDto.getDraftFlag() == 1) {
                isStartFlow = true;
            }
        } else {
            //判断是否是草稿转为正式数据
            SupervisionOrder supervisionOrde = this.getById(saveDto.getId());
            if (supervisionOrde.getDraftFlag() == 0 && saveDto.getDraftFlag() == 1) {
                isStartFlow = true;
            }
        }
        //保存
        boolean saveOrUpdate = this.saveOrUpdate(supervisionOrder);
        //新增且保存成功
        if (saveOrUpdate && isStartFlow) {
            //发起流程
            String processDefinitionKey = BimFlowKey.监理指令.getName();
            String businessKey = supervisionOrder.getId().toString();
            //设置流程的审批人
            Map<String, Object> auditUser = saveDto.getAuditUser();
            if (auditUser.isEmpty()) {
                throw new RuntimeException("流程的审批人不能为空！");
            }
            //发起流程
            try {
                FlowTaskCommentDto flowTaskComment = new FlowTaskCommentDto();
                flowTaskComment.setApprovalType("save");
                flowTaskComment.setComment("发起监理指令申请");
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
     * 通过id获取一条监理指令数据
     *
     * @param id
     * @return
     */
    @Override
    public SupervisionOrderDetailVo getInfoById(Long id) {
        //查询
        SupervisionOrder supervisionOrder = this.getById(id);
        if (Objects.isNull(supervisionOrder)) {
            return null;
        }
        //属性转换
        SupervisionOrderDetailVo vo = new SupervisionOrderDetailVo();
        BeanUtils.copyProperties(supervisionOrder, vo);
        //问题照片
        vo.setProblemPhotoAttachment(JSONArray.parseArray(supervisionOrder.getProblemPhotoAttachment(), FileModel.class));
        //其他附件
        vo.setOtherAttachment(JSONArray.parseArray(supervisionOrder.getOtherAttachment(), FileModel.class));
        //整改照片
        vo.setReplyPhotoAttachment(JSONArray.parseArray(supervisionOrder.getReplyPhotoAttachment(), FileModel.class));
        //其他附件
        vo.setReplyOtherAttachment(JSONArray.parseArray(supervisionOrder.getReplyOtherAttachment(), FileModel.class));
        return vo;
    }

    /**
     * 分页查询监理指令数据
     *
     * @param pageDto
     * @return
     */
    @Override
    public PageInfo<SupervisionOrderPageVo> getPageInfo(SupervisionOrderPageDTO pageDto) {
        //分页查询
        PageHelper.startPage(pageDto.getPageNum(), pageDto.getPageSize());
        List<SupervisionOrderPageVo> pageVoList = supervisionOrderMapper.getPageInfo(pageDto);
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
     * 监理指令导出
     *
     * @param pageDto
     * @param response
     */
    @Override
    public void export(SupervisionOrderPageDTO pageDto, HttpServletResponse response) {
        //分页查询  页数暂定为5000
        pageDto.setPageSize(5000);
        PageInfo<SupervisionOrderPageVo> pageInfo = this.getPageInfo(pageDto);
        //导出
        ExcelWriter writer = ExcelUtil.getWriter();
        writer.addHeaderAlias("orderCode", "指令编号");
        writer.addHeaderAlias("projectName", "项目名称");
        writer.addHeaderAlias("createUserName", "发起人");
        writer.addHeaderAlias("orderTitle", "指令标题");
        writer.addHeaderAlias("buildSectionName", "施工标段");
        writer.addHeaderAlias("createTime", "发起时间");
        writer.addHeaderAlias("statusStr", "状态");
        writer.merge(6, "监理指令");
        writer.write(pageInfo.getList(), true);
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        String name = ("监理指令");
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
