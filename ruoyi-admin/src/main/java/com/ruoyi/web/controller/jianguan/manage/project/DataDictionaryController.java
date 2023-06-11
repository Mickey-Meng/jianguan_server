package com.ruoyi.web.controller.jianguan.manage.project;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.annotation.RepeatSubmit;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.domain.object.ResponseBase;
import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.jianguan.manage.project.domain.bo.DataDictionaryBo;
import com.ruoyi.jianguan.manage.project.domain.vo.DataDictionaryVo;
import com.ruoyi.jianguan.manage.project.service.IDataDictionaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.List;

/**
 * 商品类别
 *
 * @author ruoyi
 * @date 2022-12-11
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/dataDictionary/dataDictionary")
public class DataDictionaryController extends BaseController {

    private final IDataDictionaryService dataDictionaryService;

    /**
     * 查询商品类别列表
     */
    @SaCheckPermission("dataDictionary:dataDictionary:list")
    @GetMapping("/list")
    public R<List<DataDictionaryVo>> list(DataDictionaryBo bo) {
        List<DataDictionaryVo> list = dataDictionaryService.queryList(bo);
        return R.ok(list);
    }
    @GetMapping("/getFileType")
    public ResponseBase getFileType(String pId) {
        DataDictionaryBo bo = new DataDictionaryBo();
        bo.setParentId(Long.parseLong(pId));
        List<DataDictionaryVo> list = dataDictionaryService.queryList(bo);
        return new ResponseBase(200, "查询查询成功!", list);
    }
    @GetMapping("/getFileTypesByPCode")
    public ResponseBase getFileTypesByPCode(String pCode) {
        List<DataDictionaryVo> list = dataDictionaryService.getFileTypesByPCode(pCode);
        return new ResponseBase(200, "查询查询成功!", list);
    }

    /**
     * 导出商品类别列表
     */
    @SaCheckPermission("dataDictionary:dataDictionary:export")
    @Log(title = "商品类别", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(DataDictionaryBo bo, HttpServletResponse response) {
        List<DataDictionaryVo> list = dataDictionaryService.queryList(bo);
        ExcelUtil.exportExcel(list, "商品类别", DataDictionaryVo.class, response);
    }

    /**
     * 获取商品类别详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("dataDictionary:dataDictionary:query")
    @GetMapping("/{id}")
    public R<DataDictionaryVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(dataDictionaryService.queryById(id));
    }

    /**
     * 新增商品类别
     */
    @SaCheckPermission("dataDictionary:dataDictionary:add")
    @Log(title = "商品类别", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody DataDictionaryBo bo) {
        return toAjax(dataDictionaryService.insertByBo(bo) ? 1 : 0);
    }

    /**
     * 修改商品类别
     */
    @SaCheckPermission("dataDictionary:dataDictionary:edit")
    @Log(title = "商品类别", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody DataDictionaryBo bo) {
        return toAjax(dataDictionaryService.updateByBo(bo) ? 1 : 0);
    }

    /**
     * 删除商品类别
     *
     * @param ids 主键串
     */
    @SaCheckPermission("dataDictionary:dataDictionary:remove")
    @Log(title = "商品类别", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(dataDictionaryService.deleteWithValidByIds(Arrays.asList(ids), true) ? 1 : 0);
    }
}
