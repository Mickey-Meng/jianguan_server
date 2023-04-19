package com.ruoyi.web.controller.jianguan.manage.project;

import java.util.List;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import com.ruoyi.jianguan.manage.project.domain.bo.JgProjectInfoBo;
import com.ruoyi.jianguan.manage.project.domain.vo.JgProjectInfoVo;
import com.ruoyi.jianguan.manage.project.service.IJgProjectInfoService;
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
 * 项目信息
 *
 * @author ruoyi
 * @date 2023-04-19
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/system/project")
public class JgProjectInfoController extends BaseController {

    private final IJgProjectInfoService iJgProjectInfoService;

/**
 * 查询项目信息列表
 */
@SaCheckPermission("system:projectInfo:list")
@GetMapping("/list")
    public TableDataInfo<JgProjectInfoVo> list(JgProjectInfoBo bo, PageQuery pageQuery) {
        return iJgProjectInfoService.queryPageList(bo, pageQuery);
    }

    /**
     * 分页查询项目信息列表
     */
    @SaCheckPermission("system:projectInfo:list")
    @GetMapping("/page")

    public TableDataInfo<JgProjectInfoVo> page(JgProjectInfoBo bo, PageQuery pageQuery) {
        return iJgProjectInfoService.queryPageList(bo, pageQuery);
    }


    /**
     * 导出项目信息列表
     */
    @SaCheckPermission("system:projectInfo:export")
    @Log(title = "项目信息" , businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(JgProjectInfoBo bo, HttpServletResponse response) {
        List<JgProjectInfoVo> list = iJgProjectInfoService.queryList(bo);
        ExcelUtil.exportExcel(list, "项目信息" , JgProjectInfoVo.class, response);
    }

    /**
     * 获取项目信息详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("system:projectInfo:query")
    @GetMapping("/{id}")
    public R<JgProjectInfoVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(iJgProjectInfoService.queryById(id));
    }

    /**
     * 新增项目信息
     */
    @SaCheckPermission("system:projectInfo:add")
    @Log(title = "项目信息" , businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody JgProjectInfoBo bo) {
        return toAjax(iJgProjectInfoService.insertByBo(bo) ? 1 : 0);
    }

    /**
     * 修改项目信息
     */
    @SaCheckPermission("system:projectInfo:edit")
    @Log(title = "项目信息" , businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody JgProjectInfoBo bo) {
        return toAjax(iJgProjectInfoService.updateByBo(bo) ? 1 : 0);
    }

    /**
     * 删除项目信息
     *
     * @param ids 主键串
     */
    @SaCheckPermission("system:projectInfo:remove")
    @Log(title = "项目信息" , businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(iJgProjectInfoService.deleteWithValidByIds(Arrays.asList(ids), true) ? 1 : 0);
    }
}
