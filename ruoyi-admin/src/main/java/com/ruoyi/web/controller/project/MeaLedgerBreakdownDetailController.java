package com.ruoyi.web.controller.project;


import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.hutool.core.collection.CollUtil;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.annotation.RepeatSubmit;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.PageQuery;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.project.ledger.domain.bo.MeaLedgerBreakdownDetailBo;
import com.ruoyi.project.ledger.domain.vo.MeaLedgerBreakdownDetailInfoVo;
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
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

/**
 * 台账分解明细
 *
 * @author ruoyi
 * @date 2022-12-04
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/ledgerDetail/ledgerBreakdownDetail")
public class MeaLedgerBreakdownDetailController extends BaseController {
    private final IWfProcessService processService;

    @Value("${mea.flowable.ledgerBreakdown}")
    private String formKey;

    private final IMeaLedgerBreakdownDetailService iMeaLedgerBreakdownDetailService;

    /**
     * 查询台账分解明细列表
     */
    @SaCheckPermission("ledgerDetail:ledgerBreakdownDetail:list")
    @GetMapping("/list")
    public TableDataInfo<MeaLedgerBreakdownDetailVo> list(MeaLedgerBreakdownDetailBo bo,String queryType, PageQuery pageQuery) {
        return iMeaLedgerBreakdownDetailService.queryPageList(bo,queryType, pageQuery);
    }

    /**
     * 查询台账分解明细列表
     */
    @SaCheckPermission("ledgerDetail:ledgerBreakdownDetail:list")
    @ApiOperation("台账分解明细列表树节点")
    @GetMapping("/list-info")
    public TableDataInfo<MeaLedgerBreakdownDetailInfoVo> listInfo(MeaLedgerBreakdownDetailBo bo, PageQuery pageQuery) {
        return iMeaLedgerBreakdownDetailService.listInfo(bo, pageQuery);
    }

    /**
     * 查询台账分解叶子节点数据，分解数量>0
     * @return
     */
    @GetMapping("/getLeaflist")
    public R<List<MeaLedgerBreakdownDetailVo>> getLeaflist(String reviewCode) {
        List<MeaLedgerBreakdownDetailVo> list = iMeaLedgerBreakdownDetailService.getLeafList(reviewCode);
        return R.ok(list);
    }
    /**
     * 查询台账分解集合
     * @return
     */
    @GetMapping("/queryBreakdownDetails")
    public R<List<MeaLedgerBreakdownDetailVo>> queryMeaLedgerBreakdownDetails(MeaLedgerBreakdownDetailBo meaLedgerBreakdownDetailBo) {
        List<MeaLedgerBreakdownDetailVo> meaLedgerBreakdownDetailVos = iMeaLedgerBreakdownDetailService.queryList4ledgerApproval(meaLedgerBreakdownDetailBo);
        return R.ok(meaLedgerBreakdownDetailVos);
    }

    /**
     * 导出台账分解明细列表
     */
    @SaCheckPermission("ledgerDetail:ledgerBreakdownDetail:export")
    @Log(title = "台账分解明细", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(MeaLedgerBreakdownDetailBo bo, HttpServletResponse response) {
        List<MeaLedgerBreakdownDetailVo> list = iMeaLedgerBreakdownDetailService.queryList(bo);
        ExcelUtil.exportExcel(list, "台账分解明细", MeaLedgerBreakdownDetailVo.class, response);
    }

    /**
     * 获取台账分解明细详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("ledgerDetail:ledgerBreakdownDetail:query")
    @GetMapping("/{id}")
    public R<MeaLedgerBreakdownDetailVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable String id) {
        return R.ok(iMeaLedgerBreakdownDetailService.queryById(id));
    }

    /**
     * 新增台账分解明细
     */
    @SaCheckPermission("ledgerDetail:ledgerBreakdownDetail:add")
    @Log(title = "台账分解明细", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody MeaLedgerBreakdownDetailBo bo) {
        checkFjsl(bo);
        /*String process_1669973630070 = processService.getProcessByKey("Process_1670392865296");
        if(StrUtil.isBlank(process_1669973630070)){
            return R.fail("流程图未定义");
        }*/
        Boolean aBoolean = iMeaLedgerBreakdownDetailService.insertByBo(bo);
       /* if(aBoolean){
            processService.startMeaProcess(process_1669973630070,formKey,bo.getId(), bo);
        }*/
        return toAjax(aBoolean ? 1 : 0);
    }
    /**
     * 台账分解明细批量保存
     */
    @Log(title = "台账分解明细批量保存", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @ApiOperation("台账分解明细批量保存")
    @PostMapping("/addBatch")
    public R<Void> addBatch(@Validated(AddGroup.class) @RequestBody List<MeaLedgerBreakdownDetailBo> bo) {
        Boolean aBoolean = iMeaLedgerBreakdownDetailService.insertByListBo(bo);
        return toAjax(aBoolean ? 1 : 0);
    }

    private void checkFjsl(MeaLedgerBreakdownDetailBo bo) {
        MeaLedgerBreakdownDetailBo meaLedgerBreakdownDetailBo = new MeaLedgerBreakdownDetailBo();
        meaLedgerBreakdownDetailBo.setZmh(bo.getZmh());
        List<MeaLedgerBreakdownDetailVo> meaLedgerBreakdownDetailVos = iMeaLedgerBreakdownDetailService.queryList(meaLedgerBreakdownDetailBo);
        if(CollUtil.isNotEmpty(meaLedgerBreakdownDetailVos)) {
            BigDecimal fjsl = bo.getFjsl();
            BigDecimal sjsl = meaLedgerBreakdownDetailVos.get(0).getSjsl();
            BigDecimal totalFjsl = meaLedgerBreakdownDetailVos.stream().map(MeaLedgerBreakdownDetailVo::getFjsl).reduce(BigDecimal.ZERO, BigDecimal::add);
            BigDecimal remainder = sjsl.subtract(totalFjsl).subtract(fjsl);
            if(remainder.compareTo(BigDecimal.ZERO) < 0) {
                throw new ServiceException("分解数量超过设计数量，待分解数量: "+String.valueOf(sjsl.subtract(totalFjsl)));
            }
        }
    }

    /**
     * 修改台账分解明细
     */
    @SaCheckPermission("ledgerDetail:ledgerBreakdownDetail:edit")
    @Log(title = "台账分解明细", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody MeaLedgerBreakdownDetailBo bo) {
        bo.setReviewCode("0");
        checkFjsl(bo);
        Boolean b = iMeaLedgerBreakdownDetailService.updateByBo(bo);
       /*
       20230731 yangaogao  台账分解功能，取消流程审批
       String process_1670392865296 = processService.getProcessByKey("Process_1689909730690");
        if(StrUtil.isBlank(process_1670392865296)){
            return R.fail("流程图未定义");
        }
        processService.startMeaProcess(process_1670392865296,formKey,bo.getId().toString(), bo);*/
        return toAjax( b ? 1 : 0);
    }

    /**
     * 删除台账分解明细
     *
     * @param ids 主键串
     */
    @SaCheckPermission("ledgerDetail:ledgerBreakdownDetail:remove")
    @Log(title = "台账分解明细", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable String[] ids) {
        return toAjax(iMeaLedgerBreakdownDetailService.deleteWithValidByIds(Arrays.asList(ids), true) ? 1 : 0);
    }
}
