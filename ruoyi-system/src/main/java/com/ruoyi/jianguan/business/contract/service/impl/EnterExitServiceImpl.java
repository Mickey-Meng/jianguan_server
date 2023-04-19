package com.ruoyi.jianguan.business.contract.service.impl;

import cn.hutool.core.io.IoUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ruoyi.common.core.domain.entity.CompanyInfo;
import com.ruoyi.common.core.domain.object.ResponseBase;
import com.ruoyi.common.core.sequence.util.IdUtil;
import com.ruoyi.common.enums.BimFlowKey;
import com.ruoyi.flowable.domain.dto.FlowTaskCommentDto;
import com.ruoyi.flowable.service.FlowAuditEntryService;
import com.ruoyi.flowable.service.FlowStaticPageService;
import com.ruoyi.flowable.service.ZjFGroupsProjectsService;
import com.ruoyi.jianguan.business.contract.domain.dto.EnterExitPageDTO;
import com.ruoyi.jianguan.business.contract.domain.dto.EnterExitSaveDTO;
import com.ruoyi.jianguan.business.contract.domain.entity.EnterExit;
import com.ruoyi.jianguan.business.contract.domain.entity.EnterExitUser;
import com.ruoyi.jianguan.business.contract.domain.entity.LaborContract;
import com.ruoyi.jianguan.business.contract.domain.vo.EnterExitDetailVo;
import com.ruoyi.jianguan.business.contract.domain.vo.EnterExitPageVo;
import com.ruoyi.jianguan.business.contract.mapper.EnterExitMapper;
import com.ruoyi.jianguan.business.contract.service.EnterExitService;
import com.ruoyi.jianguan.business.contract.service.EnterExitUserService;
import com.ruoyi.jianguan.business.contract.service.LaborContractService;
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
 * 进退场 服务实现类
 *
 * @author qiaoxulin
 * @date 2022-05-27
 */
@Service
public class EnterExitServiceImpl extends ServiceImpl<EnterExitMapper, EnterExit> implements EnterExitService {

    @Autowired
    private EnterExitMapper enterExitMapper;

    @Autowired
    private ZjFGroupsProjectsService projectsService;

    @Autowired
    private EnterExitUserService enterExitUserService;

    @Autowired
    private FlowStaticPageService flowStaticPageService;

    @Autowired
    private FlowAuditEntryService flowAuditEntryService;

    @Autowired
    private LaborContractService laborContractService;

    /**
     * 新增或者更新进退场数据
     *
     * @param saveDto
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseBase addOrUpdate(EnterExitSaveDTO saveDto) {
        //属性copy
        EnterExit enterExit = new EnterExit();
        BeanUtils.copyProperties(saveDto, enterExit);
        //新增
        boolean isStartFlow = false;
        boolean isSave = false;
        if (Objects.isNull(saveDto.getId())) {
            enterExit.setId(IdUtil.nextLongId());
            //判断是否是草稿
            if (saveDto.getDraftFlag() == 1) {
                isStartFlow = true;
            }
            isSave = true;
        } else {
            //判断是否是草稿转为正式数据
            EnterExit enterExi = this.getById(saveDto.getId());
            if (enterExi.getDraftFlag() == 0 && saveDto.getDraftFlag() == 1) {
                isStartFlow = true;
            }
        }
        //保存进退场记录
        boolean saveOrUpdate = this.saveOrUpdate(enterExit);
        //保存人员清单
        List<EnterExitUser> enterExitUsers = saveDto.getEnterExitUsers();
        if (Objects.nonNull(enterExitUsers) && !enterExitUsers.isEmpty()) {
            //新增赋值id
            if (isSave) {
                enterExitUsers.forEach(enterExitUser -> {
                    enterExitUser.setId(IdUtil.nextLongId());
                    enterExitUser.setEnterExitId(enterExit.getId());
                });
                enterExitUserService.saveBatch(enterExitUsers);
            }
            //更新人员信息
            enterExitUserService.updateBatchById(enterExitUsers);
        }
        //新增且成功
        if (saveOrUpdate && isStartFlow) {
            //发起流程
            String processDefinitionKey = BimFlowKey.进退场管理.getName();
            String businessKey = enterExit.getId().toString();
            //设置流程的审批人
            Map<String, Object> auditUser = saveDto.getAuditUser();
            if (auditUser.isEmpty()) {
                throw new RuntimeException("流程的审批人不能为空！");
            }
            //发起流程
            try {
                FlowTaskCommentDto flowTaskComment = new FlowTaskCommentDto();
                flowTaskComment.setApprovalType("save");
                flowTaskComment.setComment("发起进退场申请");
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
     * 通过id获取一条进退场数据
     *
     * @param id
     * @return
     */
    @Override
    public EnterExitDetailVo getInfoById(Long id) {
        //查询
        EnterExit enterExit = this.getById(id);
        if (Objects.isNull(enterExit)) {
            return null;
        }
        //属性转换
        EnterExitDetailVo vo = new EnterExitDetailVo();
        BeanUtils.copyProperties(enterExit, vo);
        //进出场人员清单
        vo.setEnterExitUsers(enterExitUserService.getUserByEnterId(id));
        //劳务合同编号
        Long laborContractId = enterExit.getLaborContractId();
        if (Objects.nonNull(laborContractId)){
            LaborContract laborContract = laborContractService.getById(enterExit.getLaborContractId());
            if (Objects.nonNull(laborContract)){
                vo.setLaborContractCode(laborContract.getContractCode());
            }
        }
        return vo;
    }

    /**
     * 分页查询进退场数据
     *
     * @param pageDto
     * @return
     */
    @Override
    public PageInfo<EnterExitPageVo> getPageInfo(EnterExitPageDTO pageDto) {
        //分页查询
        PageHelper.startPage(pageDto.getPageNum(), pageDto.getPageSize());
        List<EnterExitPageVo> pageVoList = enterExitMapper.getPageInfo(pageDto);
        //状态转换
        if (Objects.nonNull(pageVoList) && !pageVoList.isEmpty()) {
            //通过项目id获取施工单位 监理单位等
            CompanyInfo companyInfo = projectsService.getCompanyInfoByProjectId(pageDto.getProjectId());
            //施工单位
            Set<String> sgdws = companyInfo.getSgdws();
            if (Objects.nonNull(sgdws) && !sgdws.isEmpty()) {
                pageVoList.forEach(pageVo -> {
                    //施工单位
                    pageVo.setBuildUnits(sgdws);
                    //状态
                    if (pageVo.getStatus() == 0) {
                        pageVo.setStatusStr("进行中");
                    } else {
                        pageVo.setStatusStr("已完成");
                    }
                    //报审类型
                    if (pageVo.getType() == 0) {
                        pageVo.setTypeStr("进场");
                    } else {
                        pageVo.setTypeStr("退场");
                    }
                });
            }
        }
        return new PageInfo<>(pageVoList);
    }

    /**
     * 通过id删除一条进退场数据
     *
     * @param id
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean delById(Long id) {
        //删除人员清单数据
        LambdaQueryWrapper<EnterExitUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(EnterExitUser::getEnterExitId, id);
        enterExitUserService.remove(queryWrapper);
        //删除清单
        return this.removeById(id);
    }

    /**
     * 进退场导出
     *
     * @param pageDto
     * @param response
     */
    @Override
    public void export(EnterExitPageDTO pageDto, HttpServletResponse response) {
        //分页查询  页数暂定为5000
        pageDto.setPageSize(5000);
        PageInfo<EnterExitPageVo> pageInfo = this.getPageInfo(pageDto);
        //导出
        ExcelWriter writer = ExcelUtil.getWriter();
        writer.addHeaderAlias("buildSectionName", "施工标段");
        writer.addHeaderAlias("buildUnits", "施工单位");
        writer.addHeaderAlias("typeStr", "报审类型");
        writer.addHeaderAlias("num", "人数");
        writer.addHeaderAlias("explaination", "说明");
        writer.addHeaderAlias("statusStr", "状态");
        writer.merge(5, "进退场");
        writer.write(pageInfo.getList(), true);
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        String name = ("进退场");
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
