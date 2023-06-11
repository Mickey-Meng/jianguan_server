package com.ruoyi.jianguan.business.contract.service.impl;

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
import com.ruoyi.jianguan.business.contract.domain.dto.ContractPaymentSaveDTO;
import com.ruoyi.jianguan.business.contract.domain.dto.ContractPaymentPageDTO;
import com.ruoyi.jianguan.business.contract.domain.entity.ContractPayment;
import com.ruoyi.jianguan.business.contract.domain.vo.ContractPaymentPageVo;
import com.ruoyi.jianguan.business.contract.domain.vo.ContractPaymentDetailVo;
import com.ruoyi.jianguan.business.contract.mapper.ContractPaymentMapper;
import com.ruoyi.jianguan.business.contract.service.ContractPaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 合同付款Service业务层处理
 *
 * @author mickey
 * @date 2023-06-07
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ContractPaymentServiceImpl extends ServiceImpl<ContractPaymentMapper, ContractPayment>  implements ContractPaymentService {

    @Autowired
    private ContractPaymentMapper contractPaymentMapper;

    @Autowired
    private FlowStaticPageService flowStaticPageService;


    @Override
    public ResponseBase addOrUpdate(ContractPaymentSaveDTO saveDto) {
        //属性copy
        ContractPayment contractPayment = new ContractPayment();
        BeanUtils.copyProperties(saveDto, contractPayment);
        boolean isStartFlow = false;
        if (Objects.isNull(saveDto.getId())) {
            isStartFlow = true;
            contractPayment.setId(IdUtil.nextLongId());
        }
        // 初始化审批状态：审批中
        contractPayment.setStatus(0);
        //附件
        contractPayment.setAttachment(JSON.toJSONString(saveDto.getAttachment()));
        boolean saveOrUpdate = this.saveOrUpdate(contractPayment);
        if (saveOrUpdate && isStartFlow) {
            String processDefinitionKey = BimFlowKey.contractPayment.getName();
            String businessKey = contractPayment.getId().toString();
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
    public ContractPaymentDetailVo getInfoById(Long id) {
        //查询
        ContractPayment contractPayment = this.getById(id);
        if (Objects.isNull(contractPayment)) {
            return null;
        }
        //属性转换
        ContractPaymentDetailVo vo = new ContractPaymentDetailVo();
        BeanUtils.copyProperties(contractPayment, vo);
        // TODO: 2023/3/29 附件待实现
        vo.setAttachment(JSONArray.parseArray(contractPayment.getAttachment(), FileModel.class));
        return vo;
    }

    @Override
    public PageInfo<ContractPaymentPageVo> getPageInfo(ContractPaymentPageDTO pageDto) {
        //分页查询
        PageHelper.startPage(pageDto.getPageNum(), pageDto.getPageSize());
        List<ContractPaymentPageVo> contractPaymentPageVos = contractPaymentMapper.getPageInfo(pageDto);
        return new PageInfo<>(contractPaymentPageVos);
    }
}