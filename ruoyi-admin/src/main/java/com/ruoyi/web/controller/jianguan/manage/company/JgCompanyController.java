package com.ruoyi.web.controller.jianguan.manage.company;

import java.util.List;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import com.ruoyi.jianguan.manage.company.domain.bo.JgCompanyBo;
import com.ruoyi.jianguan.manage.company.domain.vo.JgCompanyVo;
import com.ruoyi.jianguan.manage.company.service.IJgCompanyService;
import lombok.RequiredArgsConstructor;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.*;

import cn.dev33.satoken.annotation.SaCheckPermission;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;
import com.ruoyi.common.annotation.RepeatSubmit;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.PageQuery;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import com.ruoyi.common.core.validate.QueryGroup;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 公司信息
 *
 * @author ruoyi
 * @date 2023-05-16
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/system/jg/company")
public class JgCompanyController extends BaseController {

    private final IJgCompanyService iJgCompanyService;

/**
 * 查询公司信息列表
 */
@SaCheckPermission("jg:company:list")
@GetMapping("/list")
    public TableDataInfo<JgCompanyVo> list(JgCompanyBo bo, PageQuery pageQuery) {
        return iJgCompanyService.queryPageList(bo, pageQuery);
    }

    /**
     * 分页查询公司信息列表
     */
    @SaCheckPermission("jg:company:list")
    @GetMapping("/page")

    public TableDataInfo<JgCompanyVo> page(JgCompanyBo bo, PageQuery pageQuery) {
        return iJgCompanyService.queryPageList(bo, pageQuery);
    }


    /**
     * 导出公司信息列表
     */
    @SaCheckPermission("jg:company:export")
    @Log(title = "公司信息" , businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(JgCompanyBo bo, HttpServletResponse response) {
        List<JgCompanyVo> list = iJgCompanyService.queryList(bo);
        ExcelUtil.exportExcel(list, "公司信息" , JgCompanyVo.class, response);
    }

    /**
     * 获取公司信息详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("jg:company:query")
    @GetMapping("/{id}")
    public R<JgCompanyVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(iJgCompanyService.queryById(id));
    }

    /**
     * 新增公司信息
     */
    @SaCheckPermission("jg:company:add")
    @Log(title = "公司信息" , businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody JgCompanyBo bo) {
        return toAjax(iJgCompanyService.insertByBo(bo) ? 1 : 0);
    }

    /**
     * 修改公司信息
     */
    @SaCheckPermission("jg:company:edit")
    @Log(title = "公司信息" , businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody JgCompanyBo bo) {
        return toAjax(iJgCompanyService.updateByBo(bo) ? 1 : 0);
    }

    /**
     * 删除公司信息
     *
     * @param ids 主键串
     */
    @SaCheckPermission("jg:company:remove")
    @Log(title = "公司信息" , businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(iJgCompanyService.deleteWithValidByIds(Arrays.asList(ids), true) ? 1 : 0);
    }
}
