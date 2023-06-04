package com.ruoyi.web.controller.jianguan.manage.produce;

import java.util.List;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import cn.hutool.core.lang.tree.Tree;
import com.ruoyi.jianguan.manage.project.domain.bo.JgProjectInfoBo;
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
import com.ruoyi.system.domain.vo.PubProduceLibraryVo;
import com.ruoyi.system.domain.bo.PubProduceLibraryBo;
import com.ruoyi.system.service.IPubProduceLibraryService;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 工序库
 *
 * @author ruoyi
 * @date 2023-06-03
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/system/jg/produceLibrary")
public class PubProduceLibraryController extends BaseController {

    private final IPubProduceLibraryService iPubProduceLibraryService;

    /**
     * 查询工序库列表
     */
    @SaCheckPermission("jg:produceLibrary:list")
    @GetMapping("/list")
    public R<List<PubProduceLibraryVo>> list(PubProduceLibraryBo bo) {
        return R.ok(iPubProduceLibraryService.queryList(bo));
    }
    @SaCheckPermission("jg:produceLibrary:list")
    @GetMapping("/getProduceLibraryTree")
    public R<List<Tree<Long>>> getProduceLibraryTree(PubProduceLibraryBo bo) {
        return R.ok(iPubProduceLibraryService.getProduceLibraryTree(bo));
    }
    
    
    /**
     * 分页查询工序库列表
     */
    @SaCheckPermission("jg:produceLibrary:list")
    @GetMapping("/page")

    public TableDataInfo<PubProduceLibraryVo> page(PubProduceLibraryBo bo, PageQuery pageQuery) {
        return iPubProduceLibraryService.queryPageList(bo, pageQuery);
    }


    /**
     * 导出工序库列表
     */
    @SaCheckPermission("jg:produceLibrary:export")
    @Log(title = "工序库" , businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(PubProduceLibraryBo bo, HttpServletResponse response) {
        List<PubProduceLibraryVo> list = iPubProduceLibraryService.queryList(bo);
        ExcelUtil.exportExcel(list, "工序库" , PubProduceLibraryVo.class, response);
    }

    /**
     * 获取工序库详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("jg:produceLibrary:query")
    @GetMapping("/{id}")
    public R<PubProduceLibraryVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Integer id) {
        return R.ok(iPubProduceLibraryService.queryById(id));
    }

    /**
     * 新增工序库
     */
    @SaCheckPermission("jg:produceLibrary:add")
    @Log(title = "工序库" , businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody PubProduceLibraryBo bo) {
        return toAjax(iPubProduceLibraryService.insertByBo(bo) ? 1 : 0);
    }

    /**
     * 修改工序库
     */
    @SaCheckPermission("jg:produceLibrary:edit")
    @Log(title = "工序库" , businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody PubProduceLibraryBo bo) {
        return toAjax(iPubProduceLibraryService.updateByBo(bo) ? 1 : 0);
    }

    /**
     * 删除工序库
     *
     * @param ids 主键串
     */
    @SaCheckPermission("jg:produceLibrary:remove")
    @Log(title = "工序库" , businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Integer[] ids) {
        return toAjax(iPubProduceLibraryService.deleteWithValidByIds(Arrays.asList(ids), true) ? 1 : 0);
    }
}
