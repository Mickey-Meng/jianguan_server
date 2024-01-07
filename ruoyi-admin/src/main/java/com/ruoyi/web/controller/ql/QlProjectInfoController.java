package com.ruoyi.web.controller.ql;

import java.util.List;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import com.ruoyi.common.constant.Constants;
import com.ruoyi.ql.domain.convert.QlProjectInfoConvert;
import com.ruoyi.ql.domain.export.query.QlProjectInfoReportQuery;
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
import com.ruoyi.ql.domain.vo.QlProjectInfoVo;
import com.ruoyi.ql.domain.bo.QlProjectInfoBo;
import com.ruoyi.ql.service.IQlProjectInfoService;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 项目信息
 *
 * @author ruoyi
 * @date 2022-12-11
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/projectInfo/projectInfo")
public class QlProjectInfoController extends BaseController {

    private final IQlProjectInfoService iQlProjectInfoService;

    /**
     * 查询项目信息列表
     */
    @SaCheckPermission("projectInfo:projectInfo:list")
    @GetMapping("/list")
    public TableDataInfo<QlProjectInfoVo> list(QlProjectInfoBo bo, PageQuery pageQuery) {
        return iQlProjectInfoService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出项目信息列表
     */
    @SaCheckPermission("projectInfo:projectInfo:export")
    @Log(title = "项目信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(QlProjectInfoReportQuery bo, HttpServletResponse response) {
        List<QlProjectInfoVo> list = null;
        if(Constants.EXPORT_ALL.equals(bo.getExportAll())) {
            list = iQlProjectInfoService.queryList(QlProjectInfoConvert.INSTANCE.conver(bo));
        } else {
            PageQuery pageQuery = new PageQuery();
            pageQuery.setPageNum(bo.getPageNum());
            pageQuery.setPageSize(bo.getPageSize());
            TableDataInfo<QlProjectInfoVo> qlProjectInfoVoTableDataInfo = iQlProjectInfoService.queryPageList(QlProjectInfoConvert.INSTANCE.conver(bo), pageQuery);
            list = qlProjectInfoVoTableDataInfo.getRows();
        }
        ExcelUtil.exportExcel(list, "项目信息", QlProjectInfoVo.class, response);
    }

    /**
     * 获取项目信息详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("projectInfo:projectInfo:query")
    @GetMapping("/{id}")
    public R<QlProjectInfoVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(iQlProjectInfoService.queryById(id));
    }

    /**
     * 新增项目信息
     */
    @SaCheckPermission("projectInfo:projectInfo:add")
    @Log(title = "项目信息", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody QlProjectInfoBo bo) {
        return toAjax(iQlProjectInfoService.insertByBo(bo) ? 1 : 0);
    }

    /**
     * 修改项目信息
     */
    @SaCheckPermission("projectInfo:projectInfo:edit")
    @Log(title = "项目信息", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody QlProjectInfoBo bo) {
        return toAjax(iQlProjectInfoService.updateByBo(bo) ? 1 : 0);
    }

    /**
     * 删除项目信息
     *
     * @param ids 主键串
     */
    @SaCheckPermission("projectInfo:projectInfo:remove")
    @Log(title = "项目信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(iQlProjectInfoService.deleteWithValidByIds(Arrays.asList(ids), true) ? 1 : 0);
    }
}
