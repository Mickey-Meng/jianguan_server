package com.ruoyi.web.controller.project;


import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.annotation.RepeatSubmit;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.PageQuery;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.project.approval.domain.bo.MeaLedgerApprovalBo;
import com.ruoyi.project.approval.domain.vo.MeaLedgerApprovalBreakDownVo;
import com.ruoyi.project.approval.domain.vo.MeaLedgerApprovalVo;
import com.ruoyi.project.approval.service.IMeaLedgerApprovalService;
import com.ruoyi.project.ledger.domain.bo.MeaLedgerBreakdownDetailBo;
import com.ruoyi.project.ledger.domain.vo.MeaLedgerBreakdownDetailVo;
import com.ruoyi.project.ledger.service.IMeaLedgerBreakdownDetailService;
import com.ruoyi.workflow.service.IWfProcessService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 台账报审
 *
 * @author ruoyi
 * @date 2022-12-03
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/ledgerapproval/ledgerApproval")
public class MeaLedgerApprovalController extends BaseController {

    private final IMeaLedgerApprovalService iMeaLedgerApprovalService;

    private final IWfProcessService processService;

    private final IMeaLedgerBreakdownDetailService iMeaLedgerBreakdownDetailService;

    @Value("${mea.flowable.ledgerApproval}")
    private String formKey;

    /**
     * 查询台账报审列表
     */
    @SaCheckPermission("ledgerapproval:ledgerApproval:list")
    @GetMapping("/list")
    public TableDataInfo<MeaLedgerApprovalVo> list(MeaLedgerApprovalBo bo, PageQuery pageQuery) {
        return iMeaLedgerApprovalService.queryPageList(bo, pageQuery);
    }


    /**
     * 查询台账报审列表
     */
    @GetMapping("/listMeaLedgerApprovalVo")
    public TableDataInfo<MeaLedgerApprovalBreakDownVo> queryMeaLedgerApprovalBreakDownList(MeaLedgerApprovalBo bo) {
        TableDataInfo t = new TableDataInfo();
        t.setRows(iMeaLedgerApprovalService.queryMeaLedgerApprovalBreakDownList(bo));
        return t;
    }

    /**
     * 导出台账报审列表
     */
    @SaCheckPermission("ledgerapproval:ledgerApproval:export")
    @Log(title = "台账报审", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(MeaLedgerApprovalBo bo, HttpServletResponse response) {
        List<MeaLedgerApprovalVo> list = iMeaLedgerApprovalService.queryList(bo);
        ExcelUtil.exportExcel(list, "台账报审", MeaLedgerApprovalVo.class, response);
    }

    /**
     * 获取台账报审详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("ledgerapproval:ledgerApproval:query")
    @GetMapping("/{id}")
    public R<MeaLedgerApprovalVo> getInfo(@NotNull(message = "主键不能为空")
                                          @PathVariable Long id) {
        return R.ok(iMeaLedgerApprovalService.queryById(id));
    }

    /**
     * 新增台账报审
     */
//    @SaCheckPermission("ledgerapproval:ledgerApproval:add")
//    @Log(title = "台账报审保存", businessType = BusinessType.INSERT)
//    @RepeatSubmit()
//    @ApiOperation("台账报审保存")
//    @PostMapping()
//    public R<Void> add(@Validated(AddGroup.class) @RequestBody List<MeaLedgerApprovalBo> bos) {
//        if(CollUtil.isEmpty(bos)){
//            return R.fail("数据不能为空");
//        }
//        String process_1669973630070 = processService.getProcessByKey("Process_1671867751905");
//        if(StrUtil.isBlank(process_1669973630070)){
//            return R.fail("流程图未定义");
//        }
//        for(MeaLedgerApprovalBo bo:bos){
//            Boolean aBoolean = iMeaLedgerApprovalService.insertByBo(bo);
//            if(aBoolean){
//                processService.startMeaProcess(process_1669973630070,formKey,bo.getId().toString(), bo);
//            }
//        }
//        return R.ok();
//    }

    /**
     * 新增台账报审
     */
    @SaCheckPermission("ledgerapproval:ledgerApproval:add")
    @Log(title = "台账报审上报", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @ApiOperation("台账报审上报")
    @PostMapping("/up")
    public R<Void> up(@Validated(AddGroup.class) @RequestBody List<MeaLedgerApprovalBo> bos) {
        if(CollUtil.isEmpty(bos)){
            return R.fail("数据不能为空");
        }
        String process_1669973630070 = processService.getProcessByKey("Process_1671867751905");
        if(StrUtil.isBlank(process_1669973630070)){
            return R.fail("流程图未定义");
        }
        for(MeaLedgerApprovalBo bo:bos){
            Boolean aBoolean = iMeaLedgerApprovalService.insertByBo(bo);
        }
        List<MeaLedgerApprovalBreakDownVo> boInfo=iMeaLedgerApprovalService.getInfoData(bos);
        MeaLedgerApprovalBo meaLedgerApprovalBo = bos.get(0);
        processService.startMeaProcess(process_1669973630070,formKey,meaLedgerApprovalBo.getSqqc(), boInfo);

        List<MeaLedgerBreakdownDetailBo> meaLedgerBreakdownDetails = new ArrayList<>();
        // 批量查询台账分解明细
        // TODO: 2023/3/5 后期修改为批量查询，不在循环内单条查询
        for (MeaLedgerApprovalBo bo : bos) {
            MeaLedgerBreakdownDetailVo meaLedgerBreakdownDetailVo = iMeaLedgerBreakdownDetailService.queryById(bo.getBreakdownId());
            MeaLedgerBreakdownDetailBo meaLedgerBreakdownDetailBo = new MeaLedgerBreakdownDetailBo();
            meaLedgerBreakdownDetailBo.setId(bo.getBreakdownId());
            meaLedgerBreakdownDetailBo.setStatus(meaLedgerBreakdownDetailVo.getStatus());
            meaLedgerBreakdownDetailBo.setTzfjbh(meaLedgerBreakdownDetailVo.getTzfjbh());
            // 台账上报，台账分解明细修改为已上报状态
            meaLedgerBreakdownDetailBo.setReviewCode("1");
            meaLedgerBreakdownDetails.add(meaLedgerBreakdownDetailBo);
        }
        iMeaLedgerBreakdownDetailService.updateBatchByBOList(meaLedgerBreakdownDetails);
        return R.ok();
    }

    /**
     * 修改台账报审
     */
    @SaCheckPermission("ledgerapproval:ledgerApproval:edit")
    @Log(title = "台账报审", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody MeaLedgerApprovalBo bo) {
        return toAjax(iMeaLedgerApprovalService.updateByBo(bo) ? 1 : 0);
    }

    /**
     * 删除台账报审
     *
     * @param ids 主键串
     */
    @SaCheckPermission("ledgerapproval:ledgerApproval:remove")
    @Log(title = "台账报审", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(iMeaLedgerApprovalService.deleteWithValidByIds(Arrays.asList(ids), true) ? 1 : 0);
    }
}
