package com.ruoyi.web.controller.jianguan.manage.produce;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Maps;
import com.ruoyi.common.core.domain.entity.SysDept;
import com.ruoyi.common.utils.file.FileUtils;
import com.ruoyi.jianguan.manage.produce.domain.bo.PubProduceBo;
import com.ruoyi.jianguan.manage.produce.domain.entity.PubProduce;
import com.ruoyi.jianguan.manage.produce.domain.vo.PubProduceVo;
import com.ruoyi.jianguan.manage.produce.service.IPubProduceService;
import lombok.RequiredArgsConstructor;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.*;

import cn.dev33.satoken.annotation.SaCheckPermission;
import org.apache.ibatis.annotations.Param;
import org.apache.poi.util.IOUtils;
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
 * 工序信息
 *
 * @author ruoyi
 * @date 2023-06-03
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/system/jg/produce")
public class PubProduceController extends BaseController {

    private final IPubProduceService iPubProduceService;

    /**
     * 查询工序信息列表
     */
    @SaCheckPermission("jg:produce:list")
    @GetMapping("/list")
    public TableDataInfo<PubProduceVo> list(PubProduceBo bo, PageQuery pageQuery) {
        return iPubProduceService.queryPageList(bo, pageQuery);
    }

    /**
     * 分页查询工序信息列表
     */
    @SaCheckPermission("jg:produce:list")
    @GetMapping("/page")
    public TableDataInfo<PubProduceVo> page(PubProduceBo bo, PageQuery pageQuery) {
        return iPubProduceService.queryPageList(bo, pageQuery);
    }

    @SaCheckPermission("jg:produce:list")
    @GetMapping(value = "/allList/{typeId}")
    public R<Map<String, Object>> getProjectDept(@PathVariable Long typeId) {
        List<PubProduce> produceAllList = iPubProduceService.getProduceListByTypeId(typeId);
        Map<String, Object> dataMap = Maps.newHashMap();
        dataMap.put("produceAllList", produceAllList);
        return R.ok(dataMap);
    }

    /**
     * 导出工序信息列表
     */
    @SaCheckPermission("jg:produce:export")
    @Log(title = "工序信息" , businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(PubProduceBo bo, HttpServletResponse response) {
        List<PubProduceVo> list = iPubProduceService.queryList(bo);
        ExcelUtil.exportExcel(list, "工序信息" , PubProduceVo.class, response);
    }

    /**
     * 获取工序信息详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("jg:produce:query")
    @GetMapping("/{id}")
    public R<PubProduceVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(iPubProduceService.queryById(id));
    }

    /**
     * 新增工序信息
     */
    @SaCheckPermission("jg:produce:add")
    @Log(title = "工序信息" , businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody PubProduceBo bo) {
        return toAjax(iPubProduceService.insertByBo(bo) ? 1 : 0);
    }

    /**
     * 修改工序信息
     */
    @SaCheckPermission("jg:produce:edit")
    @Log(title = "工序信息" , businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody PubProduceBo bo) {
        return toAjax(iPubProduceService.updateByBo(bo) ? 1 : 0);
    }

    /**
     * 删除工序信息
     *
     * @param ids 主键串
     */
    @SaCheckPermission("jg:produce:remove")
    @Log(title = "工序信息" , businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(iPubProduceService.deleteWithValidByIds(Arrays.asList(ids), true) ? 1 : 0);
    }

    /**
     * 修改工序信息
     */
    @SaCheckPermission("jg:produce:edit")
    @Log(title = "工序信息" , businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PostMapping("/importProduces/{ids}")
    public R<Void> edit(@PathVariable Long[] ids, @RequestBody PubProduceBo bo) {
        return toAjax(iPubProduceService.doImportProduces(ids, bo));
    }

    /**
     * 获取填充数据后的模板
     * @param id
     * @return
     */
    //@SaCheckPermission("jg:produce:query")
    @GetMapping("/getFillDataTemplate/{id}")
    public void getFillDataTemplate(@NotNull(message = "主键不能为空") @PathVariable Long id,
                              @RequestParam("templateUrl") String templateUrl, HttpServletResponse response) throws IOException {
        Map<String, String> templateDataMap = iPubProduceService.getFillDataTemplate(id, templateUrl);
        ExcelUtil.resetResponse(templateDataMap.get("templateName"), response);
        InputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(templateDataMap.get("templatePath"));
            IOUtils.copy(fileInputStream, response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        } finally {
            if (fileInputStream != null) {
                fileInputStream.close();
                // 删除生成的待填写模板文件
                FileUtils.del(templateDataMap.get("templatePath"));
            }
        }
    }

    @SaCheckPermission("jg:produce:save")
    @Log(title = "保存编辑后的模板数据" , businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping("/saveFillDataTemplate/{id}")
    public R<?> saveFillDataTemplate(@NotNull(message = "主键不能为空") @PathVariable Long id,
                                       @Validated(AddGroup.class) @RequestBody String LuckySheetJson) throws IOException {
        return R.ok("模板数据保存成功", iPubProduceService.saveFillDataTemplate(id, LuckySheetJson));
    }
}
