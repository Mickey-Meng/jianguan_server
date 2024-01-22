package com.ruoyi.web.controller.jianguan.business.onlineForms;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.annotation.RepeatSubmit;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.PageQuery;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.domain.object.ResponseBase;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.file.FileUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.jianguan.business.onlineForms.domain.bo.PubProduceDocumentBo;
import com.ruoyi.jianguan.business.onlineForms.domain.vo.PubProduceDocumentVo;
import com.ruoyi.jianguan.business.onlineForms.service.IOnlineFormsService;
import com.ruoyi.jianguan.business.onlineForms.service.IPubProduceDocumentService;
import com.ruoyi.jianguan.common.domain.dto.RecodeUploadData;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.apache.poi.util.IOUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 工序附件信息
 *
 * @author mickey
 * @date 2024-01-02
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/onlineForms/produceOnline")
public class OnlineFormsController extends BaseController {

    private final IOnlineFormsService onlineFormsService;

    @RepeatSubmit()
    @PostMapping("/saveFillDataTemplate/{id}")
    @Log(title = "保存编辑后的模板数据" , businessType = BusinessType.INSERT)
    public R<?> saveFillDataTemplate(@NotNull(message = "主键不能为空") @PathVariable Long id,
                                     @Validated(AddGroup.class) @RequestBody String LuckySheetJson) throws IOException {
        return R.ok("模板数据保存成功", onlineFormsService.saveFillDataTemplate(id, LuckySheetJson));
    }

    @PostMapping("/submitReport")
    @ApiOperation("上传填报记录")
    public ResponseBase submitReport(@RequestBody RecodeUploadData recodeData){
        return onlineFormsService.submitReport(recodeData);
    }

    @PostMapping("/submitCheck")
    @ApiOperation("上传填报记录")
    public ResponseBase submitCheck(@RequestBody RecodeUploadData recodeData){
        return onlineFormsService.submitCheck(recodeData);
    }

    @GetMapping("/getProduceReportInfoById")
    @ApiOperation("根据构件id查询每到工序的记录")
    public ResponseBase getProduceReportInfoById(@RequestParam(value ="produceAndRecodeId",required = false) Integer id,
                                                 @RequestParam(value ="documentType",required = false) Integer documentType){
        if(ObjectUtils.isEmpty(id)){
            return new ResponseBase(500,"id为null");
        }
        // 查询所有的数据
        return onlineFormsService.getProduceReportInfoById(id, documentType);
    }

    @GetMapping("/getFillDataTemplate/{id}")
    public void getFillDataTemplate(@NotNull(message = "主键不能为空") @PathVariable Long id, HttpServletResponse response) throws IOException {
        Map<String, String> templateDataMap = onlineFormsService.getFillDataTemplate(id);
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

    @GetMapping("/getOnlineReportTemplate")
    @ApiOperation("根据构件id查询每到工序的记录")
    public ResponseBase getOnlineReportTemplate(@RequestParam(value ="componentCode",required = false) String componentCode,
                                                 @RequestParam(value ="projectId",required = false) String projectId){
        if(ObjectUtils.isEmpty(componentCode)){
            return new ResponseBase(500,"componentCode为null");
        }
        // 查询所有的数据
        return onlineFormsService.getOnlineReportTemplate(componentCode, projectId);
    }

    @GetMapping("/getReportRecord")
    public ResponseBase getReportRecord(@RequestParam(value ="componentId",required = false) Integer componentId){
        return onlineFormsService.getReportRecord(componentId);
    }

}
