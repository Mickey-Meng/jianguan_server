package com.ruoyi.web.controller.project.test.controller;


import cn.dev33.satoken.annotation.SaCheckPermission;
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
import com.ruoyi.project.test.domain.bo.TestBo;
import com.ruoyi.project.test.domain.vo.TestVo;
import com.ruoyi.project.test.service.ITestService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.List;

/**
 * 测试单
 *
 * @author ruoyi
 * @date 2022-12-02
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/test/test")
public class TestController extends BaseController {

    private final ITestService iTestService;

    /**
     * 查询测试单列表
     */
    @SaCheckPermission("test:test:list")
    @GetMapping("/list")
    public TableDataInfo<TestVo> list(TestBo bo, PageQuery pageQuery) {
        return iTestService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出测试单列表
     */
    @SaCheckPermission("test:test:export")
    @Log(title = "测试单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(TestBo bo, HttpServletResponse response) {
        List<TestVo> list = iTestService.queryList(bo);
        ExcelUtil.exportExcel(list, "测试单", TestVo.class, response);
    }

    /**
     * 获取测试单详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("test:test:query")
    @GetMapping("/{id}")
    public R<TestVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(iTestService.queryById(id));
    }

    /**
     * 新增测试单
     */
    @SaCheckPermission("test:test:add")
    @Log(title = "测试单", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody TestBo bo) {
        return toAjax(iTestService.insertByBo(bo) ? 1 : 0);
    }

    /**
     * 修改测试单
     */
    @SaCheckPermission("test:test:edit")
    @Log(title = "测试单", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody TestBo bo) {
        return toAjax(iTestService.updateByBo(bo) ? 1 : 0);
    }

    /**
     * 删除测试单
     *
     * @param ids 主键串
     */
    @SaCheckPermission("test:test:remove")
    @Log(title = "测试单", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(iTestService.deleteWithValidByIds(Arrays.asList(ids), true) ? 1 : 0);
    }
}
