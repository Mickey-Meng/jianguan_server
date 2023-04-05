package com.ruoyi.jianguan.contract.service.impl;

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
import com.ruoyi.common.core.domain.vo.EnumsVo;
import com.ruoyi.common.core.sequence.util.IdUtil;
import com.ruoyi.common.enums.BimFlowKey;
import com.ruoyi.common.enums.BuildProjectPartEnum;
import com.ruoyi.jianguan.contract.domain.dto.BuildContractPageDTO;
import com.ruoyi.jianguan.contract.domain.dto.BuildContractSaveDTO;
import com.ruoyi.jianguan.contract.domain.entity.BuildContract;
import com.ruoyi.jianguan.contract.domain.vo.BuildContractDetailVo;
import com.ruoyi.jianguan.contract.domain.vo.BuildContractPageVo;
import com.ruoyi.jianguan.contract.mapper.BuildContractMapper;
import com.ruoyi.jianguan.contract.service.BuildContractService;
import com.ruoyi.flowable.domain.dto.FlowTaskCommentDto;
import com.ruoyi.flowable.service.FlowAuditEntryService;
import com.ruoyi.flowable.service.FlowStaticPageService;
import com.ruoyi.flowable.service.UserService;
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

/**
 * 施工专业分包合同表 服务实现类
 *
 * @author qiaoxulin
 * @date 2022-05-18
 */
@Service
public class BuildContractServiceImpl extends ServiceImpl<BuildContractMapper, BuildContract> implements BuildContractService {


    @Autowired
    private UserService userService;

    @Autowired
    private FlowStaticPageService flowStaticPageService;

    @Autowired
    private BuildContractMapper buildContractMapper;

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
    public ResponseBase addOrUpdate(BuildContractSaveDTO saveDto) {
        //属性copy
        BuildContract buildContract = new BuildContract();
        BeanUtils.copyProperties(saveDto, buildContract);
        //合同信息
        buildContract.setContractInfo(JSON.toJSONString(saveDto.getContractInfo()));
        //附件
        buildContract.setAttachment(JSON.toJSONString(saveDto.getAttachment()));
        //新增
        boolean isStartFlow = false;
        if (Objects.isNull(saveDto.getId())) {
            buildContract.setId(IdUtil.nextLongId());
            //判断是否是草稿
            if (saveDto.getDraftFlag() == 1) {
                isStartFlow = true;
            }
        } else {
            //判断是否是草稿转为正式数据
            BuildContract buildContrac = this.getById(saveDto.getId());
            if (buildContrac.getDraftFlag() == 0 && saveDto.getDraftFlag() == 1) {
                isStartFlow = true;
            }
        }
        boolean saveOrUpdate = this.saveOrUpdate(buildContract);
        //保存成功且新增
        if (saveOrUpdate && isStartFlow) {
            String processDefinitionKey = BimFlowKey.施工专业分包合同.getName();
            String businessKey = buildContract.getId().toString();
            //设置流程的审批人
            Map<String, Object> auditUser = saveDto.getAuditUser();
            if (auditUser.isEmpty()) {
                throw new RuntimeException("流程的审批人不能为空！");
            }
            //发起流程
            try {
                FlowTaskCommentDto flowTaskComment = new FlowTaskCommentDto();
                flowTaskComment.setApprovalType("save");
                flowTaskComment.setComment("发起施工专业分包合同申请");
                JSONObject taskVariableData = new JSONObject(auditUser);
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
    public BuildContractDetailVo getInfoById(Long id) {
        //查询
        BuildContract buildContract = this.getById(id);
        if (Objects.isNull(buildContract)) {
            return null;
        }
        //属性转换
        BuildContractDetailVo vo = new BuildContractDetailVo();
        BeanUtils.copyProperties(buildContract, vo);
        //合同信息
        vo.setContractInfo(JSONArray.parseArray(buildContract.getContractInfo(), BuildContractSaveDTO.ContractInfo.class));
        //附件
        vo.setAttachment(JSONArray.parseArray(buildContract.getAttachment(), FileModel.class));
        return vo;
    }

    /**
     * 分页查询施工专业分包合同表数据
     *
     * @param pageDto
     * @return
     */
    @Override
    public PageInfo<BuildContractPageVo> getPageInfo(BuildContractPageDTO pageDto) {
        //分页查询
        PageHelper.startPage(pageDto.getPageNum(), pageDto.getPageSize());
        List<BuildContractPageVo> buildContracts = buildContractMapper.getPageInfo(pageDto);
        return new PageInfo<>(buildContracts);
    }

    /**
     * 获取拟分包工程部位枚举
     *
     * @return
     */
    @Override
    public List<EnumsVo> getBuildContractPartEnum() {
        //返回
        List<EnumsVo> result = Lists.newArrayList();
        List<BuildProjectPartEnum> buildProjectPartEnums = BuildProjectPartEnum.LIST;
        if (!buildProjectPartEnums.isEmpty()) {
            buildProjectPartEnums.forEach(buildProjectPart -> {
                EnumsVo vo = new EnumsVo();
                vo.setCode(buildProjectPart.getCode());
                vo.setDesc(buildProjectPart.getDes());
                result.add(vo);
            });
        }
        return result;
    }

    /**
     * 施工专业分包合同导出
     *
     * @param pageDto
     * @param response
     */
    @Override
    public void export(BuildContractPageDTO pageDto, HttpServletResponse response) {
        //分页查询  页数暂定为5000
        pageDto.setPageSize(5000);
        PageInfo<BuildContractPageVo> pageInfo = this.getPageInfo(pageDto);
        //导出
        ExcelWriter writer = ExcelUtil.getWriter();
        writer.addHeaderAlias("buildSectionName", "标段");
        writer.addHeaderAlias("contractCode", "合同编号");
        writer.addHeaderAlias("contractUser", "承包人");
        writer.addHeaderAlias("createTime", "备案时间");
        writer.merge(3, "施工专业分包合同");
        writer.write(pageInfo.getList(), true);
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        String name = ("施工专业分包合同");
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
