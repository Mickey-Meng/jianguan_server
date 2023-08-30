package com.ruoyi.jianguan.business.quality.service.impl;

import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.ObjUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.enums.BimFlowKey;
import com.ruoyi.jianguan.business.quality.domain.dto.SupervisionNoticePageDTO;
import com.ruoyi.jianguan.business.quality.domain.dto.SupervisionNoticeSaveDTO;
import com.ruoyi.jianguan.business.quality.domain.entity.SupervisionNotice;
import com.ruoyi.jianguan.business.quality.domain.entity.SupervisionOrder;
import com.ruoyi.jianguan.business.quality.domain.vo.SupervisionNoticeDetailVo;
import com.ruoyi.jianguan.business.quality.domain.vo.SupervisionNoticePageVo;
import com.ruoyi.jianguan.business.quality.domain.vo.SupervisionOrderDetailVo;
import com.ruoyi.jianguan.business.quality.mapper.SupervisionNoticeMapper;
import com.ruoyi.jianguan.business.quality.service.SupervisionNoticeService;
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
 * 监理通知 服务实现类
 *
 * @author qiaoxulin
 * @date 2022-06-11
 */
@Service
public class SupervisionNoticeServiceImpl extends ServiceImpl<SupervisionNoticeMapper, SupervisionNotice> implements SupervisionNoticeService {

    @Autowired
    private FlowStaticPageService flowStaticPageService;

    @Autowired
    private SupervisionNoticeMapper supervisionNoticeMapper;

    /**
     * 新增或者更新监理通知数据
     *
     * @param saveDto
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseBase addOrUpdate(SupervisionNoticeSaveDTO saveDto) {
        //属性copy
        SupervisionNotice notice = new SupervisionNotice();
        BeanUtils.copyProperties(saveDto, notice);
        //新增
        boolean isStartFlow = false;
        if (Objects.isNull(saveDto.getId())) {
            notice.setId(IdUtil.nextLongId());
            //判断是否是草稿
            if (saveDto.getDraftFlag() == 1) {
                isStartFlow = true;
            }
        } else {
            //判断是否是草稿转为正式数据
            SupervisionNotice notic = this.getById(saveDto.getId());
            if (notic.getDraftFlag() == 0 && saveDto.getDraftFlag() == 1) {
                isStartFlow = true;
            }
        }
        // 编辑操作不修改审批状态
        if(ObjUtil.isNull(saveDto.getId())) {
            notice.setStatus(0);
        }
        //保存
        boolean saveOrUpdate = this.saveOrUpdate(notice);
        //新增且保存成功
        if (saveOrUpdate && isStartFlow) {
            //发起流程
            String processDefinitionKey = BimFlowKey.监理通知.getName();
            String businessKey = notice.getId().toString();
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
     * 分页查询监理通知数据
     *
     * @param pageDto
     * @return
     */
    @Override
    public PageInfo<SupervisionNoticePageVo> getPageInfo(SupervisionNoticePageDTO pageDto) {
        //分页查询
        PageHelper.startPage(pageDto.getPageNum(), pageDto.getPageSize());
        List<SupervisionNoticePageVo> supervisionNotices = supervisionNoticeMapper.getPageInfo(pageDto);
        //非空
        if (Objects.nonNull(supervisionNotices) && !supervisionNotices.isEmpty()) {
            supervisionNotices.forEach(supervisionNotice -> {
                //状态
                if(supervisionNotice.getStatus() == 0) {
                    supervisionNotice.setStatusStr("审批中");
                } else if(supervisionNotice.getStatus() == 1) {
                    supervisionNotice.setStatusStr("已审批");
                }else {
                    supervisionNotice.setStatusStr("已驳回");
                }
            });
        }
        return new PageInfo<>(supervisionNotices);
    }

    /**
     * 监理通知导出
     *
     * @param pageDto
     * @param response
     */
    @Override
    public void export(SupervisionNoticePageDTO pageDto, HttpServletResponse response) {
        //分页查询  页数暂定为5000
        pageDto.setPageSize(5000);
        PageInfo<SupervisionNoticePageVo> pageInfo = this.getPageInfo(pageDto);
        //导出
        ExcelWriter writer = ExcelUtil.getWriter();
        writer.addHeaderAlias("code", "编号");
        writer.addHeaderAlias("title", "标题");
        writer.addHeaderAlias("createTime", "创建时间");
        writer.addHeaderAlias("statusStr", "状态");
        writer.merge(3, "监理通知");
        writer.write(pageInfo.getList(), true);
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        String name = ("监理通知");
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

    @Override
    public SupervisionNoticeDetailVo getInfoById(Long id) {
        //查询
        SupervisionNotice supervisionOrder = this.getById(id);
        if (Objects.isNull(supervisionOrder)) {
            return null;
        }
        //属性转换
        SupervisionNoticeDetailVo vo = new SupervisionNoticeDetailVo();
        BeanUtils.copyProperties(supervisionOrder, vo);
        return vo;
    }
}
