package com.ruoyi.web.controller.project;


import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.hutool.core.bean.BeanUtil;
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
import com.ruoyi.common.utils.BeanCopyUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.project.bill.domain.MeaContractBill;
import com.ruoyi.project.bill.domain.bo.MeaContractBillBo;
import com.ruoyi.project.bill.domain.vo.ChapterCollectVo;
import com.ruoyi.project.bill.domain.vo.MeaContractBillVo;
import com.ruoyi.project.bill.service.IMeaContractBillService;
import com.ruoyi.system.domain.bo.ContractInfoBo;
import com.ruoyi.system.domain.vo.ContractInfoVo;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 工程量清单
 *
 * @author ruoyi
 * @date 2022-12-03
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/bill/contractBill")

public class MeaContractBillController extends BaseController {

    private final IMeaContractBillService iMeaContractBillService;

    /**
     * 查询工程量清单列表
     */
    @SaCheckPermission("bill:contractBill:list")
    @GetMapping("/list")
    public R<List<MeaContractBillVo>> list(MeaContractBillBo bo) {
        List<MeaContractBillVo> list = iMeaContractBillService.queryList(bo);
        // 合并 合同数量和变更数量
        if(CollUtil.isEmpty(list)) {
            return R.ok(list);
        }
        Map<String, List<MeaContractBillVo>> meaContractBillMap = list.stream().collect(Collectors.groupingBy(MeaContractBillVo::getZmh));
        List<MeaContractBillVo> meaContractBills = new ArrayList<>();
        meaContractBillMap.forEach((zmh, meaContractBillList) -> {
            if(zmh.equals("103-1")) {
                int i = 0;
            }
            MeaContractBillVo meaContractBillVo = BeanUtil.copyProperties(meaContractBillList.get(0), MeaContractBillVo.class);
            // 是否存在变更记录
            List<MeaContractBillVo> changMeaContractBillVo = meaContractBillList.stream().filter(meaContractBill -> "1".equals(meaContractBill.getIsChange())).collect(Collectors.toList());
            if(changMeaContractBillVo.size() == 1) {
                meaContractBillVo.setBgsl(changMeaContractBillVo.get(0).getBgsl());
                meaContractBillVo.setBgje(changMeaContractBillVo.get(0).getBgje());
            }
            meaContractBills.add(meaContractBillVo);
        });
        return R.ok(meaContractBills);
    }


    /**
     * 查询工程量清单叶子节点，合同数量>0的数据列表
     */
    @GetMapping("/getLeaflist")
    public R<List<MeaContractBillVo>> getLeaflist(MeaContractBillBo bo) {
        List<MeaContractBillVo> list = iMeaContractBillService.getLeafList(bo);
        return R.ok(list);
    }
    /**
     * 查询工程量清单树干节点
     */
    @SaCheckPermission("bill:contractBill:list")
    @GetMapping("/treeList")
    public R<List<MeaContractBillVo>> treeList(MeaContractBillBo bo) {
        List<MeaContractBillVo> list = iMeaContractBillService.treeList(bo);
        return R.ok(list);
    }

    /**
     * 查询工程量清单列表
     */
    @SaCheckPermission("bill:contractBill:list")
    @GetMapping("/leaves")
    public R<List<MeaContractBillVo>> leaves(MeaContractBillBo bo) {
        List<MeaContractBillVo> list = iMeaContractBillService.leaves(bo);
        return R.ok(list);
    }

    /**
     * 查询工程量清单列表
     */
    @SaCheckPermission("bill:collect_info:list")
    @GetMapping("/chapter_collect")
    public R<List<ChapterCollectVo>> chapter_collect() {
        List<ChapterCollectVo> list = iMeaContractBillService.chapter_collect();
        return R.ok(list);
    }



    /**
     * 查询合同条款列表
     */
    @SaCheckPermission("bill:contractBill:page")
    @GetMapping("/page")
    public TableDataInfo<MeaContractBillVo> list(MeaContractBillBo bo, PageQuery pageQuery) {
        return iMeaContractBillService.queryPageList(bo, pageQuery);
    }


    /**
     * 导出工程量清单列表
     */
    @SaCheckPermission("bill:contractBill:export")
    @Log(title = "工程量清单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(MeaContractBillBo bo, HttpServletResponse response) {
        List<MeaContractBillVo> list = iMeaContractBillService.queryList(bo);
        ExcelUtil.exportExcel(list, "工程量清单", MeaContractBillVo.class, response);
    }




    /**
     * 获取工程量清单详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("bill:contractBill:query")
    @GetMapping("/{id}")
    public R<MeaContractBillVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable String id) {
        return R.ok(iMeaContractBillService.queryById(id));
    }

    /**
     * 新增工程量清单
     */
    @SaCheckPermission("bill:contractBill:add")
    @Log(title = "工程量清单", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody MeaContractBillBo bo) {
        return toAjax(iMeaContractBillService.insertByBo(bo) ? 1 : 0);
    }

    /**
     * 修改工程量清单
     */
    @SaCheckPermission("bill:contractBill:edit")
    @Log(title = "工程量清单", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody MeaContractBillBo bo) {
        return toAjax(iMeaContractBillService.updateByBo(bo) ? 1 : 0);
    }

    /**
     * 删除工程量清单
     *
     * @param ids 主键串
     */
    @SaCheckPermission("bill:contractBill:remove")
    @Log(title = "工程量清单", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable String[] ids) {
        return toAjax(iMeaContractBillService.deleteWithValidByIds(Arrays.asList(ids), true) ? 1 : 0);
    }
}
