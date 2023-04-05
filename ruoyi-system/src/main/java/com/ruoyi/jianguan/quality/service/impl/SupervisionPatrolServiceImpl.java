package com.ruoyi.jianguan.quality.service.impl;

import cn.hutool.core.io.IoUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.jianguan.quality.domain.dto.SupervisionPatrolPageDTO;
import com.ruoyi.jianguan.quality.domain.dto.SupervisionPatrolSaveDTO;
import com.ruoyi.common.core.domain.entity.FileModel;
import com.ruoyi.jianguan.quality.domain.entity.SupervisionPatrol;
import com.ruoyi.jianguan.quality.mapper.SupervisionPatrolMapper;
import com.ruoyi.jianguan.quality.service.SupervisionPatrolService;
import com.ruoyi.jianguan.quality.domain.vo.SupervisionPatrolDetailVo;
import com.ruoyi.jianguan.quality.domain.vo.SupervisionPatrolPageVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ruoyi.common.core.domain.object.ResponseBase;
import com.ruoyi.flowable.service.FlowStaticPageService;
import com.ruoyi.common.core.sequence.util.IdUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

/**
 * 监理巡视 服务实现类
 *
 * @author qiaoxulin
 * @date 2022-06-10
 */
@Service
public class SupervisionPatrolServiceImpl extends ServiceImpl<SupervisionPatrolMapper, SupervisionPatrol> implements SupervisionPatrolService {


    @Autowired
    private FlowStaticPageService flowStaticPageService;

    @Autowired
    private SupervisionPatrolMapper supervisionPatrolMapper;


    /**
     * 新增或者更新监理巡视数据
     *
     * @param saveDto
     * @return
     */
    @Override
    public ResponseBase addOrUpdate(SupervisionPatrolSaveDTO saveDto) {
        //属性copy
        SupervisionPatrol supervisionPatrol = new SupervisionPatrol();
        BeanUtils.copyProperties(saveDto, supervisionPatrol);
        //巡视现场照片
        supervisionPatrol.setPatrolPhotoAttachment(JSON.toJSONString(saveDto.getPatrolPhotoAttachment()));
        //视频
        supervisionPatrol.setVideo(JSON.toJSONString(saveDto.getVideo()));
        //新增
        boolean isStartFlow = false;
        if (Objects.isNull(saveDto.getId())) {
            supervisionPatrol.setId(IdUtil.nextLongId());
            //判断是否是草稿
            if (saveDto.getDraftFlag() == 1) {
                isStartFlow = true;
            }
        } else {
            //判断是否是草稿转为正式数据
            SupervisionPatrol supervisionPatro = this.getById(saveDto.getId());
            if (supervisionPatro.getDraftFlag() == 0 && saveDto.getDraftFlag() == 1) {
                isStartFlow = true;
            }
        }
        //保存
        boolean saveOrUpdate = this.saveOrUpdate(supervisionPatrol);
        //新增且保存成功
        if (saveOrUpdate && isStartFlow) {
//            //发起流程
//            String processDefinitionKey = BimFlowKey.监理巡视.getName();
//            String businessKey = supervisionPatrol.getId().toString();
//            //设置流程的审批人
//            Map<String, Object> auditUser = saveDto.getAuditUser();
//            if (auditUser.isEmpty()) {
//                throw new RuntimeException("流程的审批人不能为空！");
//            }
//            //发起流程
//            try {
//                FlowTaskCommentDto flowTaskComment = new FlowTaskCommentDto();
//                flowTaskComment.setApprovalType("save");
//                flowTaskComment.setComment("发起监理巡视申请");
//                JSONObject taskVariableData = new JSONObject(auditUser);
//                flowStaticPageService.startAndTakeUserTask(processDefinitionKey, flowTaskComment, taskVariableData, null, null, null, businessKey);
//            } catch (Exception e) {
//                log.error("流程启动失败！e=" + e.getMessage());
//                throw new RuntimeException("流程启动失败！e=" + e.getMessage());
//            }
        }
        return ResponseBase.success(saveOrUpdate);
    }

    /**
     * 通过id获取一条监理巡视数据
     *
     * @param id
     * @return
     */
    @Override
    public SupervisionPatrolDetailVo getInfoById(Long id) {
        //查询
        SupervisionPatrol supervisionPatrol = this.getById(id);
        if (Objects.isNull(supervisionPatrol)) {
            return null;
        }
        //属性转换
        SupervisionPatrolDetailVo vo = new SupervisionPatrolDetailVo();
        BeanUtils.copyProperties(supervisionPatrol, vo);
        //巡视现场照片
        vo.setPatrolPhotoAttachment(JSONArray.parseArray(supervisionPatrol.getPatrolPhotoAttachment(), FileModel.class));
        //视频
        vo.setVideo(JSONArray.parseArray(supervisionPatrol.getVideo(), FileModel.class));
        return vo;
    }

    /**
     * 分页查询监理巡视数据
     *
     * @param pageDto
     * @return
     */
    @Override
    public PageInfo<SupervisionPatrolPageVo> getPageInfo(SupervisionPatrolPageDTO pageDto) {
        //分页 查询
        PageHelper.startPage(pageDto.getPageNum(), pageDto.getPageSize());
        List<SupervisionPatrolPageVo> pageVoList = supervisionPatrolMapper.getPageInfo(pageDto);
        return new PageInfo<>(pageVoList);
    }

    /**
     * 监理巡视导出
     *
     * @param pageDto
     * @param response
     */
    @Override
    public void export(SupervisionPatrolPageDTO pageDto, HttpServletResponse response) {
        //分页查询  页数暂定为5000
        pageDto.setPageSize(5000);
        PageInfo<SupervisionPatrolPageVo> pageInfo = this.getPageInfo(pageDto);
        //导出
        ExcelWriter writer = ExcelUtil.getWriter();
        writer.addHeaderAlias("buildSectionName", "标段");
        writer.addHeaderAlias("patrolPlace", "巡视地点");
        writer.addHeaderAlias("createName", "创建人");
        writer.addHeaderAlias("createTime", "创建时间");
        writer.merge(3, "监理巡视");
        writer.write(pageInfo.getList(), true);
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        String name = ("监理巡视");
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
