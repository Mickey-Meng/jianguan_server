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
import com.ruoyi.jianguan.business.contract.domain.dto.ConstructionPrototypePageDTO;
import com.ruoyi.jianguan.business.contract.domain.dto.ConstructionPrototypeSaveDTO;
import com.ruoyi.jianguan.business.contract.domain.entity.ConstructionPrototype;
import com.ruoyi.jianguan.business.contract.domain.vo.ConstructionPrototypeDetailVo;
import com.ruoyi.jianguan.business.contract.domain.vo.ConstructionPrototypePageVo;
import com.ruoyi.jianguan.business.contract.mapper.ConstructionPrototypeMapper;
import com.ruoyi.jianguan.business.contract.service.ConstructionPrototypeService;
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
public class ConstructionPrototypeServiceImpl extends ServiceImpl<ConstructionPrototypeMapper, ConstructionPrototype>  implements ConstructionPrototypeService {

    @Autowired
    private ConstructionPrototypeMapper constructionPrototypeMapper;

    @Autowired
    private FlowStaticPageService flowStaticPageService;


    @Override
    public ResponseBase addOrUpdate(ConstructionPrototypeSaveDTO saveDto) {
        //属性copy
        ConstructionPrototype constructionPrototype = new ConstructionPrototype();
        BeanUtils.copyProperties(saveDto, constructionPrototype);
        boolean isStartFlow = false;
        if (Objects.isNull(saveDto.getId())) {
            isStartFlow = true;
            constructionPrototype.setId(IdUtil.nextLongId());
        }
        // 初始化审批状态：审批中
        // 编辑操作不修改审批状态
        if(ObjUtil.isNull(saveDto.getId())) {
            constructionPrototype.setStatus(0);
        }
        //附件
        constructionPrototype.setAttachment(JSON.toJSONString(saveDto.getAttachment()));
        boolean saveOrUpdate = this.saveOrUpdate(constructionPrototype);
        if (saveOrUpdate && isStartFlow) {
            String processDefinitionKey = BimFlowKey.constructionPrototype.getName();
            String businessKey = constructionPrototype.getId().toString();
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
    public ConstructionPrototypeDetailVo getInfoById(Long id) {
        //查询
        ConstructionPrototype constructionPrototype = this.getById(id);
        if (Objects.isNull(constructionPrototype)) {
            return null;
        }
        //属性转换
        ConstructionPrototypeDetailVo vo = new ConstructionPrototypeDetailVo();
        BeanUtils.copyProperties(constructionPrototype, vo);
        vo.setAttachment(JSONArray.parseArray(constructionPrototype.getAttachment(), FileModel.class));
        return vo;
    }

    @Override
    public PageInfo<ConstructionPrototypePageVo> getPageInfo(ConstructionPrototypePageDTO pageDto) {
        //分页查询
        PageHelper.startPage(pageDto.getPageNum(), pageDto.getPageSize());
        List<ConstructionPrototypePageVo> constructionPrototypePageVos = constructionPrototypeMapper.getPageInfo(pageDto);
        return new PageInfo<>(constructionPrototypePageVos);
    }
}