package com.ruoyi.web.controller.jianguan.flowable;

import com.github.pagehelper.PageInfo;
import com.ruoyi.common.core.domain.object.ResponseBase;
import com.ruoyi.flowable.domain.dto.FlowAuditEntryPageDTO;
import com.ruoyi.flowable.domain.dto.FlowAuditEntrySaveDTO;
import com.ruoyi.flowable.domain.vo.FlowAuditEntryDetailVo;
import com.ruoyi.flowable.service.FlowAuditEntryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 流程节点审核人员
 *
 * @author qiaoxulin
 * @date 2022-05-29
 */
@Api(value = "流程节点审核人员", tags = {"流程节点审核人员"})
@RestController
@RequestMapping("/web/api/v1/flowAuditEntry")
public class FlowAuditEntryController {


    @Autowired
    private FlowAuditEntryService flowAuditEntryService;


    /**
     * 新增或者更新流程节点审核人员数据
     *
     * @param
     */
    @PostMapping(value = "/addOrUpdate", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "新增或者更新流程节点审核人员数据")
    public boolean addOrUpdate(@RequestBody @ApiParam(name = "saveDto") FlowAuditEntrySaveDTO saveDto) {
        return flowAuditEntryService.addOrUpdate(saveDto);
    }


    /**
     * 通过id删除一条流程节点审核人员 数据
     *
     * @param id
     */
    @GetMapping(value = "/id", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "通过id删除一条流程节点审核人员数据")
    public boolean removeById(@ApiParam(name = "id") Long id) {
        return flowAuditEntryService.removeById(id);
    }

    /**
     * 通过flowKey删除流程节点审核人员 数据
     *
     * @param flowKey
     */
    @GetMapping(value = "/removeByFlowKey", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "通过flowKey删除流程节点审核人员")
    public boolean removeByFlowKey(String flowKey) {
        return flowAuditEntryService.removeByFlowKey(flowKey);
    }


    /**
     * 通过id获取一条流程节点审核人员数据
     *
     * @param id
     */
    @GetMapping(value = "/detail/id", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "通过id获取一条流程节点审核人员数据")
    public FlowAuditEntryDetailVo getById(@ApiParam(name = "id") Long id) {
        return flowAuditEntryService.getInfoById(id);
    }


    /**
     * 分页查询流程节点审核人员 数据
     *
     * @param pageDto
     */
    @PostMapping(value = "/page", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "分页查询流程节点审核人员数据")
    public PageInfo<FlowAuditEntryDetailVo> page(@RequestBody FlowAuditEntryPageDTO pageDto) {
        return flowAuditEntryService.getPageInfo(pageDto);
    }


    /**
     * 通过流程key获取一条流程节点审核人员数据
     *
     * @param flowKey
     */
    @GetMapping(value = "getAuditInfoByFlowKey", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "通过流程key获取一条流程节点审核人员数据")
    public Map<String,List<FlowAuditEntryDetailVo>> getAuditInfoByFlowKey(String flowKey, Integer projectId, Integer buildSection) {
        return flowAuditEntryService.getAuditEntryByFlowKey(flowKey, projectId, buildSection);
    }

    /**
     * 通过流程key获取一条流程节点抄送人员数据
     *
     * @param flowKey
     */
    @GetMapping(value = "getCopyUserByFlowKey", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "通过流程key获取一条流程节点抄送人员数据")
    public FlowAuditEntryDetailVo getCopyUserByFlowKey(String flowKey, Integer projectId, String entryKey, Integer buildSection) {
        return flowAuditEntryService.getCopyUserByFlowKey(flowKey, projectId, entryKey, buildSection);
    }

//    /**
//     * 更新流程节点信息
//     *
//     * @param
//     */
//    @GetMapping(value = "updateFlowEntry", produces = "application/json;charset=UTF-8")
//    @ApiOperation(value = "更新流程节点信息")
//    public FlowAuditEntryDetailVo updateFlowEntry(String newEntryPublishId, String flowKey) {
//        return flowAuditEntryService.updateFlowEntry(newEntryPublishId);
//    }

    /**
     * 新增流程节点信息
     *
     * @param
     */
    @GetMapping(value = "addFlowEntryByFlowKey", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "新增流程节点信息")
    public ResponseBase addFlowEntryByFlowKey(String flowKey, Integer projectId, Integer buildSection, Long typeId) {
        return flowAuditEntryService.addFlowEntryByFlowKey(flowKey, projectId, buildSection, typeId);
    }
}
