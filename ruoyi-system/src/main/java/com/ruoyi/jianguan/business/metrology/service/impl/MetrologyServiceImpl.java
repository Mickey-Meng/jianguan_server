package com.ruoyi.jianguan.business.metrology.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ruoyi.common.constant.ApprovalStatusEnum;
import com.ruoyi.common.core.domain.entity.FileModel;
import com.ruoyi.common.core.domain.object.ResponseBase;
import com.ruoyi.common.core.sequence.util.IdUtil;
import com.ruoyi.common.enums.BimFlowKey;
import com.ruoyi.flowable.domain.dto.FlowTaskCommentDto;
import com.ruoyi.flowable.service.FlowAuditEntryService;
import com.ruoyi.flowable.service.FlowStaticPageService;
import com.ruoyi.jianguan.common.service.UserService;
import com.ruoyi.jianguan.business.metrology.domain.dto.MetrologyPageDTO;
import com.ruoyi.jianguan.business.metrology.domain.dto.MetrologySaveDTO;
import com.ruoyi.jianguan.business.metrology.domain.entity.Metrology;
import com.ruoyi.jianguan.business.metrology.domain.vo.MetrologyDetailVo;
import com.ruoyi.jianguan.business.metrology.domain.vo.MetrologyPageVo;
import com.ruoyi.jianguan.business.metrology.export.MetroLogyExport;
import com.ruoyi.jianguan.business.metrology.mapper.MetrologyMapper;
import com.ruoyi.jianguan.business.metrology.service.MetrologyService;
import lombok.extern.slf4j.Slf4j;
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
 * 施工专业分包合同表 服务实现类
 *
 * @author qiaoxulin
 * @date 2022-05-18
 */
@Slf4j
@Service
public class MetrologyServiceImpl extends ServiceImpl<MetrologyMapper, Metrology> implements MetrologyService {



    @Autowired
    private UserService userService;

    @Autowired
    private FlowStaticPageService flowStaticPageService;

    @Autowired
    private MetrologyMapper metrologyMapper;

    @Autowired
    private FlowAuditEntryService flowAuditEntryService;

    /**
     * 新增或者更新施工专业分包合同表数据
     *
     * @param saveDto
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseBase addOrUpdate(MetrologySaveDTO saveDto) {
        //属性copy
        Metrology metrology = new Metrology();
        BeanUtils.copyProperties(saveDto, metrology);
        metrology.setAttachment(JSON.toJSONString(saveDto.getAttachment()));
        boolean isStartFlow = false;
        if (Objects.isNull(saveDto.getId())) {
            isStartFlow = true;
            // 初始化审批状态：审批中
            metrology.setAuditStatus(ApprovalStatusEnum.APPROVING.name());
            metrology.setId(IdUtil.nextLongId());
        } else {
            metrology.setAuditStatus(ApprovalStatusEnum.REJECT.name());
        }
        //附件
        metrology.setAttachment(JSON.toJSONString(saveDto.getAttachment()));
        boolean saveOrUpdate = this.saveOrUpdate(metrology);
        if (saveOrUpdate && isStartFlow) {
            String processDefinitionKey = BimFlowKey.metrology.getName();
            String businessKey = metrology.getId().toString();
            //设置流程的审批人
            Map<String, Object> auditUser = saveDto.getAuditUser();
            if (auditUser.isEmpty()) {
                throw new RuntimeException("流程的审批人不能为空！");
            }
            //发起流程
            try {
                FlowTaskCommentDto flowTaskComment = new FlowTaskCommentDto();
                flowTaskComment.setApprovalType("save");
                flowTaskComment.setComment("计量审批");
                JSONObject taskVariableData = new JSONObject(auditUser);
                // processDefinitionKey 流程key
                // flowTaskComment 审批意见
                // taskVariableData 流程审批用户
                // masterData
                // slaveData
                // copyData 抄送用户
                // businessKey 业务主键
                // startAndTakeUserTask 自己封装的接口
                flowStaticPageService.startAndTakeUserTask(processDefinitionKey, flowTaskComment, taskVariableData, null, null, saveDto.getCopyData(), businessKey);
            } catch (Exception e) {
                log.error("流程启动失败！e=" + e.getMessage());
                throw new RuntimeException("流程启动失败！e=" + e.getMessage());
            }
        }
        return ResponseBase.success("流程启动成功");
    }

    /**
     * 通过id获取一条施工专业分包合同表数据
     *
     * @param id
     * @return
     */
    @Override
    public MetrologyDetailVo getInfoById(Long id) {
        //查询
        Metrology metrology = this.getById(id);
        if (Objects.isNull(metrology)) {
            return null;
        }
        //属性转换
        MetrologyDetailVo vo = new MetrologyDetailVo();
        BeanUtils.copyProperties(metrology, vo);
        // TODO: 2023/3/29 附件待实现
        vo.setAttachment(JSONArray.parseArray(metrology.getAttachment(), FileModel.class));
        return vo;
    }

    /**
     * 分页查询施工专业分包合同表数据
     *
     * @param pageDto
     * @return
     */
    @Override
    public PageInfo<MetrologyPageVo> getPageInfo(MetrologyPageDTO pageDto) {
        //分页查询
        PageHelper.startPage(pageDto.getPageNum(), pageDto.getPageSize());
        List<MetrologyPageVo> metrologys = metrologyMapper.getPageInfo(pageDto);
        return new PageInfo<>(metrologys);
    }

    // TODO: 2023/3/29 导出，待实现
    /**
     * 施工专业分包合同导出
     *
     * @param pageDto
     * @param response
     */
    @Override
    public void export(MetrologyPageDTO pageDto, HttpServletResponse response) {
        //分页查询  页数暂定为5000
        pageDto.setPageSize(5000);
        PageInfo<MetrologyPageVo> pageInfo = this.getPageInfo(pageDto);
        //导出
        ExcelWriter writer = ExcelUtil.getWriter();

        writer.addHeaderAlias("id", "id");
        writer.addHeaderAlias("metrologyName", "计量期数名称");
        writer.addHeaderAlias("startDate", "开始日期");
        writer.addHeaderAlias("endDate", "结束日期");
        writer.addHeaderAlias("metrologyNo", "计量编号");
        writer.addHeaderAlias("applyDate", "申请日期");
        writer.addHeaderAlias("applyUnit", "申请单位");
        writer.addHeaderAlias("amount", "计量金额");
        writer.addHeaderAlias("content", "计量内容");
        writer.addHeaderAlias("applyCertificate", "申请依据");
        writer.addHeaderAlias("auditStatus", "审批状态");

        List<MetroLogyExport> metroLogyExports = BeanUtil.copyToList(pageInfo.getList(), MetroLogyExport.class);
        convertAuditStatus(metroLogyExports);
        writer.merge(9, "计量台账");
        writer.write(metroLogyExports, true);
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        String name = ("计量台账");
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

    /**
     * 审批状态编码转中文描述
     *
     * @param metroLogyExports
     */
    private void convertAuditStatus(List<MetroLogyExport> metroLogyExports)  {
        for (MetroLogyExport metrology : metroLogyExports) {
            metrology.setAuditStatus(ApprovalStatusEnum.getDesc(metrology.getAuditStatus()));
        }
    }
}
