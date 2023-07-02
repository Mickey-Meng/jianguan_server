package com.ruoyi.flowable.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ruoyi.common.core.domain.dto.SsFUsersDTO;
import com.ruoyi.common.core.domain.entity.SsFUsers;
import com.ruoyi.common.core.sequence.util.IdUtil;
import com.ruoyi.flowable.domain.dto.FlowTypePageDTO;
import com.ruoyi.flowable.domain.entity.FlowAuditEntry;
import com.ruoyi.flowable.domain.vo.FlowAuditEntryDetailVo;
import com.ruoyi.flowable.domain.vo.FlowTypeDetailVo;
import com.ruoyi.flowable.mapper.FlowTypeMapper;
import com.ruoyi.flowable.model.FlowType;
import com.ruoyi.flowable.service.FlowAuditEntryService;
import com.ruoyi.flowable.service.FlowTypeService;
import com.ruoyi.flowable.service.UserService;
import org.apache.commons.compress.utils.Lists;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 流程类型 服务实现类
 *
 * @author qiaoxulin
 * @date 2022-06-11
 */
@Service
public class FlowTypeServiceImpl extends ServiceImpl<FlowTypeMapper, FlowType> implements FlowTypeService {

    @Autowired
    private FlowTypeMapper flowTypeMapper;

    @Autowired
    private FlowAuditEntryService flowAuditEntryService;

    @Autowired
    private UserService userService;

    /**
     * 新增或者更新流程类型数据
     *
     * @param flowType
     * @return
     */
    @Override
    public boolean addOrUpdate(FlowType flowType) {
        //新增
        if (Objects.isNull(flowType.getId())) {
            flowType.setId(IdUtil.nextLongId());
        }
        return this.saveOrUpdate(flowType);
    }

    /**
     * 分页查询流程类型数据
     *
     * @param pageDto
     * @return
     */
    @Override
    public PageInfo<FlowTypeDetailVo> getPageInfo(FlowTypePageDTO pageDto) {
        //分页查询
        PageHelper.startPage(pageDto.getPageNum(), pageDto.getPageSize());
        List<FlowTypeDetailVo> flowTypes = flowTypeMapper.getPageInfo(pageDto);
        //非空
        if (Objects.nonNull(flowTypes) && !flowTypes.isEmpty()) {
            flowTypes.forEach(flowType -> {
                //是否配置节点
                flowType.setCount(flowAuditEntryService.getCount(flowType.getId(), pageDto.getProjectId(), pageDto.getBuildSection()));
            });
        }
        return new PageInfo<>(flowTypes);
    }

    /**
     * 通过流程分类id查询流程审核人数据
     *
     * @param projectId
     * @param id
     * @return
     */
    @Override
    public List<FlowAuditEntryDetailVo> getAuditInfoByTypeId(Integer projectId, Long id, Integer buildSection) {
        //返回
        List<FlowAuditEntryDetailVo> detailVos = Lists.newArrayList();
        //查询
        List<FlowAuditEntry> flowAuditEntries = flowTypeMapper.getAuditInfoByTypeId(projectId, id, buildSection);
        //非空
        if (Objects.nonNull(flowAuditEntries) && !flowAuditEntries.isEmpty()) {
            flowAuditEntries.forEach(pageVo -> {
                FlowAuditEntryDetailVo vo = new FlowAuditEntryDetailVo();
                BeanUtils.copyProperties(pageVo, vo);
                //抄送人员id
                List<Integer> copyUserIds = JSONArray.parseArray(pageVo.getCopyUser(), Integer.class);
                vo.setCopyUserId(copyUserIds);
                if (Objects.nonNull(copyUserIds) && !copyUserIds.isEmpty()) {
                    List<SsFUsersDTO> ssFUsersDTOList  = new ArrayList<>();
                    ssFUsersDTOList = com.ruoyi.common.utils.BeanCopyUtils.copyList(userService.getUsersByIds(copyUserIds),SsFUsersDTO.class);
                    vo.setCopyUserInfo(ssFUsersDTOList);
                }
                //审核人员名称
                vo.setUserNames(JSONArray.parseArray(pageVo.getUserName(), String.class));
                //审核人员id
                List<Integer> userIds = JSONArray.parseArray(pageVo.getUserId(), Integer.class);
                vo.setUserIds(userIds);
                if (Objects.nonNull(userIds) && !userIds.isEmpty()) {
                    List<SsFUsersDTO> ssFUsersDTOList  = new ArrayList<>();
                    ssFUsersDTOList = com.ruoyi.common.utils.BeanCopyUtils.copyList(userService.getUsersByIds(userIds),SsFUsersDTO.class);
                    vo.setUserInfo(ssFUsersDTOList);
                }
//                //是否配置节点
//                vo.setCount(flowAuditEntryService.getCount(id, projectId, buildSection));
                detailVos.add(vo);
            });
        }
        return detailVos;
    }

    /**
     * 通过id删除一条流程类型数据
     *
     * @param id
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean delById(Long id) {
        //先删除审核人
        flowAuditEntryService.remove(new LambdaQueryWrapper<FlowAuditEntry>().eq(FlowAuditEntry::getTypeId, id));
        //再删除分类
        return this.removeById(id);
    }
}
