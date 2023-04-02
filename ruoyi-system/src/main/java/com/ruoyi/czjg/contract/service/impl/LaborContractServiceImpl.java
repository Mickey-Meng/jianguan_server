package com.ruoyi.czjg.contract.service.impl;

import cn.hutool.core.io.IoUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Sets;
import com.ruoyi.common.core.domain.entity.FileModel;
import com.ruoyi.common.core.domain.object.ResponseBase;
import com.ruoyi.common.core.sequence.util.IdUtil;
import com.ruoyi.common.enums.BimFlowKey;
import com.ruoyi.common.utils.zjbim.StringUtils;
import com.ruoyi.czjg.contract.domain.dto.LaborContractPageDTO;
import com.ruoyi.czjg.contract.domain.dto.LaborContractSaveDTO;
import com.ruoyi.czjg.contract.domain.entity.LaborContract;
import com.ruoyi.czjg.contract.domain.vo.LaborContractDetailVo;
import com.ruoyi.czjg.contract.domain.vo.LaborContractPageVo;
import com.ruoyi.czjg.contract.mapper.LaborContractMapper;
import com.ruoyi.czjg.contract.service.LaborContractService;
import com.ruoyi.flowable.domain.dto.FlowTaskCommentDto;
import com.ruoyi.flowable.service.FlowAuditEntryService;
import com.ruoyi.flowable.service.FlowStaticPageService;
import com.ruoyi.flowable.service.UserService;
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
 * 劳务分包合同 服务实现类
 *
 * @author qiaoxulin
 * @date 2022-05-20
 */
@Service
public class LaborContractServiceImpl extends ServiceImpl<LaborContractMapper, LaborContract> implements LaborContractService {


    @Autowired
    private LaborContractMapper laborContractMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private FlowStaticPageService flowStaticPageService;

    @Autowired
    private FlowAuditEntryService flowAuditEntryService;

    /**
     * 新增或者更新劳务分包合同数据
     *
     * @param saveDto
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseBase addOrUpdate(LaborContractSaveDTO saveDto) {
        //属性转换
        LaborContract laborContract = new LaborContract();
        BeanUtils.copyProperties(saveDto, laborContract);
        //合同附件
        laborContract.setAttachment(JSON.toJSONString(saveDto.getAttachment()));
        //信息填报
        laborContract.setInformation(JSON.toJSONString(saveDto.getInformation()));
        //新增
        boolean isStartFlow = false;
        if (Objects.isNull(saveDto.getId())) {
            laborContract.setId(IdUtil.nextLongId());
            //判断是否是草稿
            if (saveDto.getDraftFlag() == 1) {
                isStartFlow = true;
            }
        } else {
            //判断是否是草稿转为正式数据
            LaborContract laborContrac = this.getById(saveDto.getId());
            if (laborContrac.getDraftFlag() == 0 && saveDto.getDraftFlag() == 1) {
                isStartFlow = true;
            }
        }
        boolean saveOrUpdate = this.saveOrUpdate(laborContract);
        //保存成功且新增
        if (saveOrUpdate && isStartFlow) {
            String processDefinitionKey = BimFlowKey.劳务分包合同.getName();
            String businessKey = laborContract.getId().toString();
            //设置流程的审批人
            Map<String, Object> auditUser = saveDto.getAuditUser();
            if (auditUser.isEmpty()) {
                throw new RuntimeException("流程的审批人不能为空！");
            }
            //发起流程
            try {
                FlowTaskCommentDto flowTaskComment = new FlowTaskCommentDto();
                flowTaskComment.setApprovalType("save");
                flowTaskComment.setComment("发起劳务分包合同申请");
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
     * 通过id获取一条劳务分包合同数据
     *
     * @param id
     * @return
     */
    @Override
    public LaborContractDetailVo getInfoById(Long id) {
        LaborContract laborContract = this.getById(id);
        if (Objects.isNull(laborContract)) {
            return null;
        }
        //属性转换
        LaborContractDetailVo vo = new LaborContractDetailVo();
        BeanUtils.copyProperties(laborContract, vo);
        //附件
        vo.setAttachment(JSONArray.parseArray(laborContract.getAttachment(), FileModel.class));
        //信息填报
        vo.setInformation(JSONArray.parseArray(laborContract.getInformation(), LaborContractSaveDTO.Information.class));
        return vo;
    }

    /**
     * 分页查询劳务分包合同数据
     *
     * @param pageDto
     * @return
     */
    @Override
    public PageInfo<LaborContractPageVo> getPageInfo(LaborContractPageDTO pageDto) {
        //分页查询
        PageHelper.startPage(pageDto.getPageNum(), pageDto.getPageSize());
        List<LaborContractPageVo> pageVos = laborContractMapper.getPageInfo(pageDto);
        //拟分包工程名称 处理
        if (Objects.nonNull(pageVos) && !pageVos.isEmpty()) {
            pageVos.forEach(pageVo -> {
                String information = pageVo.getInformation();
                if (StringUtils.isNotEmptyAndNull(information)) {
                    List<LaborContractSaveDTO.Information> infoList = JSONArray.parseArray(information, LaborContractSaveDTO.Information.class);
                    Set<String> buildProjectNameSet = Sets.newHashSet();
                    if (!infoList.isEmpty()) {
                        infoList.forEach(info -> {
                            buildProjectNameSet.add(info.getBuildProjectName());
                        });
                    }
                    pageVo.setLaborContractProjectName(buildProjectNameSet);
                }
            });
        }
        return new PageInfo<>(pageVos);
    }

    /**
     * 劳务分包合同列表（不分页）
     *
     * @return
     */
    @Override
    public List<LaborContract> getList() {
        LambdaQueryWrapper<LaborContract> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(LaborContract::getDraftFlag, 1);
        return this.list(queryWrapper);
    }

    /**
     * 劳务分包合同导出
     *
     * @param pageDto
     * @param response
     */
    @Override
    public void export(LaborContractPageDTO pageDto, HttpServletResponse response) {
        //分页查询  页数暂定为5000
        pageDto.setPageSize(5000);
        PageInfo<LaborContractPageVo> pageInfo = this.getPageInfo(pageDto);
        //导出
        ExcelWriter writer = ExcelUtil.getWriter();
        writer.addHeaderAlias("buildSectionName", "施工标段");
        writer.addHeaderAlias("laborContractProjectName", "拟劳务合作工程名称");
        writer.addHeaderAlias("startDate", "备案日期");
        writer.addHeaderAlias("contractUser", "承包人");
        writer.write(pageInfo.getList(), true);
        writer.merge(3, "劳务分包合同");
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        String name = ("劳务分包合同");
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
