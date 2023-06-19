package com.ruoyi.jianguan.business.contract.service.impl;

import cn.hutool.core.util.ObjUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ruoyi.common.core.domain.entity.FileModel;
import com.ruoyi.common.core.domain.object.ResponseBase;
import com.ruoyi.common.core.sequence.util.IdUtil;
import com.ruoyi.common.enums.BimFlowKey;
import com.ruoyi.flowable.domain.dto.FlowTaskCommentDto;
import com.ruoyi.flowable.service.FlowStaticPageService;
import com.ruoyi.jianguan.business.contract.domain.dto.DailyReportPageDTO;
import com.ruoyi.jianguan.business.contract.domain.dto.DailyReportSaveDTO;
import com.ruoyi.jianguan.business.contract.domain.entity.DailyReport;
import com.ruoyi.jianguan.business.contract.domain.vo.DailyReportDetailVo;
import com.ruoyi.jianguan.business.contract.domain.vo.DailyReportPageVo;
import com.ruoyi.jianguan.business.contract.mapper.DailyReportMapper;
import com.ruoyi.jianguan.business.contract.service.DailyReportService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;


@Slf4j
@Service
@RequiredArgsConstructor
public class DailyReportServiceImpl extends ServiceImpl<DailyReportMapper, DailyReport>  implements DailyReportService {

    @Autowired
    private DailyReportMapper dailyReportMapper;

    @Autowired
    private FlowStaticPageService flowStaticPageService;


    @Override
    public ResponseBase addOrUpdate(DailyReportSaveDTO saveDto) {
        //属性copy
        DailyReport dailyReport = new DailyReport();
        BeanUtils.copyProperties(saveDto, dailyReport);
        boolean isStartFlow = false;
        if (Objects.isNull(saveDto.getId())) {
            isStartFlow = true;
            dailyReport.setId(IdUtil.nextLongId());
        }
        // 初始化审批状态：审批中
        // 编辑操作不修改审批状态
        if(ObjUtil.isNull(saveDto.getId())) {
            dailyReport.setStatus(0);
        }
        //附件
        dailyReport.setAttachment(JSON.toJSONString(saveDto.getAttachment()));
        boolean saveOrUpdate = this.saveOrUpdate(dailyReport);
        if (saveOrUpdate && isStartFlow) {
            String processDefinitionKey = BimFlowKey.dailyReport.getName();
            String businessKey = dailyReport.getId().toString();
            //设置流程的审批人
            Map<String, Object> auditUser = saveDto.getAuditUser();
            if (auditUser.isEmpty()) {
                throw new RuntimeException("流程的审批人不能为空！");
            }
            //发起流程
            try {
                FlowTaskCommentDto flowTaskComment = new FlowTaskCommentDto();
                flowTaskComment.setApprovalType("save");
                flowTaskComment.setComment("合同付款单创建");
                JSONObject taskVariableData = new JSONObject(auditUser);
                flowStaticPageService.startAndTakeUserTask(processDefinitionKey, flowTaskComment, taskVariableData, null, null, saveDto.getCopyData(), businessKey);
            } catch (Exception e) {
                log.error("流程启动失败！e=" + e.getMessage());
                throw new RuntimeException("流程启动失败！e=" + e.getMessage());
            }
        }
        return ResponseBase.success("流程启动成功");
    }

    @Override
    public DailyReportDetailVo getInfoById(Long id) {
        //查询
        DailyReport dailyReport = this.getById(id);
        if (Objects.isNull(dailyReport)) {
            return null;
        }
        //属性转换
        DailyReportDetailVo vo = new DailyReportDetailVo();
        BeanUtils.copyProperties(dailyReport, vo);
        // TODO: 2023/3/29 附件待实现
        vo.setAttachment(JSONArray.parseArray(dailyReport.getAttachment(), FileModel.class));
        return vo;
    }

    @Override
    public PageInfo<DailyReportPageVo> getPageInfo(DailyReportPageDTO pageDto) {
        //分页查询
        PageHelper.startPage(pageDto.getPageNum(), pageDto.getPageSize());
        List<DailyReportPageVo> dailyReportPageVos = dailyReportMapper.getPageInfo(pageDto);
        return new PageInfo<>(dailyReportPageVos);
    }
}