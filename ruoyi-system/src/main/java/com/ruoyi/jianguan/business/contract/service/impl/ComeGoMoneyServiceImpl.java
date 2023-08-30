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
import com.ruoyi.common.core.domain.entity.FileModel;
import com.ruoyi.common.core.domain.object.ResponseBase;
import com.ruoyi.common.core.sequence.util.IdUtil;
import com.ruoyi.common.enums.BimFlowKey;
import com.ruoyi.common.helper.LoginHelper;
import com.ruoyi.common.utils.JwtUtil;
import com.ruoyi.jianguan.business.contract.domain.dto.ComeGoMoneyPageDTO;
import com.ruoyi.jianguan.business.contract.domain.dto.ComeGoMoneySaveDTO;
import com.ruoyi.jianguan.business.contract.domain.entity.ComeGoMoney;
import com.ruoyi.jianguan.business.contract.domain.vo.ComeGoMoneyDetailVo;
import com.ruoyi.jianguan.business.contract.mapper.ComeGoMoneyMapper;
import com.ruoyi.jianguan.business.contract.service.ComeGoMoneyService;
import com.ruoyi.flowable.domain.dto.FlowTaskCommentDto;
import com.ruoyi.flowable.service.FlowAuditEntryService;
import com.ruoyi.flowable.service.FlowStaticPageService;
import com.ruoyi.jianguan.business.contract.domain.vo.ComeGoMoneyPageVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 往来款 服务实现类
 *
 * @author qiaoxulin
 * @date 2022-05-27
 */
@Service
public class
ComeGoMoneyServiceImpl extends ServiceImpl<ComeGoMoneyMapper, ComeGoMoney> implements ComeGoMoneyService {

    @Autowired
    private ComeGoMoneyMapper comeGoMoneyMapper;

    @Autowired
    private FlowStaticPageService flowStaticPageService;

    @Autowired
    private FlowAuditEntryService flowAuditEntryService;

    /**
     * 新增或者更新往来款数据
     *
     * @param saveDto
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseBase addOrUpdate(ComeGoMoneySaveDTO saveDto) {
        //属性copy
        ComeGoMoney comeGoMoney = new ComeGoMoney();
        BeanUtils.copyProperties(saveDto, comeGoMoney);
        //附件
        comeGoMoney.setAttachment(JSON.toJSONString(saveDto.getAttachment()));
        //新增
        boolean isStartFlow = false;
        if (Objects.isNull(saveDto.getId())) {
            comeGoMoney.setId(IdUtil.nextLongId());
            comeGoMoney.setCreateUserId(LoginHelper.getUserId().intValue());
            comeGoMoney.setCreateTime(new Date());
            //判断是否是草稿
            if (saveDto.getDraftFlag() == 1) {
                isStartFlow = true;
            }
        } else {
            //判断是否是草稿转为正式数据
            ComeGoMoney comeGoMone = this.getById(saveDto.getId());
            if (comeGoMone.getDraftFlag() == 0 && saveDto.getDraftFlag() == 1) {
                isStartFlow = true;
            }
        }
        comeGoMoney.setUpdateUserId(LoginHelper.getUserId().intValue());
        comeGoMoney.setUpdateTime(new Date());
        comeGoMoney.setStatus(1);
        //保存
        boolean saveOrUpdate = this.saveOrUpdate(comeGoMoney);
        //保存成功且新增
        if (saveOrUpdate && isStartFlow) {
            //发起流程
            String processDefinitionKey = BimFlowKey.往来款管理.getName();
            String businessKey = comeGoMoney.getId().toString();
            //设置流程的审批人
            Map<String, Object> auditUser = saveDto.getAuditUser();
            if (auditUser.isEmpty()) {
                throw new RuntimeException("流程的审批人不能为空！");
            }
            //发起流程
            try {
                FlowTaskCommentDto flowTaskComment = new FlowTaskCommentDto();
                flowTaskComment.setApprovalType("save");
                flowTaskComment.setComment("发起往来款申请");
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
     * 通过id获取一条往来款数据
     *
     * @param id
     * @return
     */
    @Override
    public ComeGoMoneyDetailVo getInfoById(Long id) {
        //查询
        ComeGoMoney comeGoMoney = this.getById(id);
        if (Objects.isNull(comeGoMoney)) {
            return null;
        }
        //属性转换
        ComeGoMoneyDetailVo vo = new ComeGoMoneyDetailVo();
        BeanUtils.copyProperties(comeGoMoney, vo);
        //附件
        vo.setAttachment(JSONArray.parseArray(comeGoMoney.getAttachment(), FileModel.class));
        return vo;
    }

    /**
     * 分页查询往来款数据
     *
     * @param pageDto
     * @return
     */
    @Override
    public PageInfo<ComeGoMoneyPageVo> getPageInfo(ComeGoMoneyPageDTO pageDto) {
        //分页查询
        PageHelper.startPage(pageDto.getPageNum(), pageDto.getPageSize());
        List<ComeGoMoneyPageVo> pageVoList = comeGoMoneyMapper.getPageInfo(pageDto);
        pageVoList.forEach(pageVo -> {
            //状态
            switch (pageVo.getStatus()) {
                case 0:
                    pageVo.setStatusStr("审批中");break;
                case 1:
                    pageVo.setStatusStr("已审批");break;
                default:
                    pageVo.setStatusStr("驳回");break;
            };
        });
        return new PageInfo<>(pageVoList);
    }

    /**
     * 往来款导出
     *
     * @param pageDto
     * @param response
     */
    @Override
    public void export(ComeGoMoneyPageDTO pageDto, HttpServletResponse response) {
        //分页查询  页数暂定为5000
        pageDto.setPageSize(5000);
        PageInfo<ComeGoMoneyPageVo> pageInfo = this.getPageInfo(pageDto);
        //导出
        ExcelWriter writer = ExcelUtil.getWriter();
        writer.addHeaderAlias("buildSectionName", "施工标段");
        writer.addHeaderAlias("billCode", "账单编号");
        writer.addHeaderAlias("payUnit", "付款单位");
        writer.addHeaderAlias("gatherUnit", "收款单位");
        writer.addHeaderAlias("payAmount", "支付金额");
        writer.addHeaderAlias("statusStr", "状态");
        writer.merge(5, "往来款");
        writer.write(pageInfo.getList(), true);
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        String name = ("往来款");
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
