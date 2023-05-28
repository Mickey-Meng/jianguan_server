package com.ruoyi.web.controller.jianguan.business.safetydisclosure;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ruoyi.common.core.domain.object.ResponseBase;
import com.ruoyi.jianguan.business.safetydisclosure.domain.dto.EducationTrainDto;
import com.ruoyi.jianguan.business.safetydisclosure.domain.entity.EducationTrain;
import com.ruoyi.jianguan.business.safetydisclosure.domain.service.EducationTrainService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author bx 2023/5/20
 */
@RestController
@RequestMapping("/web/api/v1/safetyDisclosure/educationTrain")
public class EducationTrainController {

    @Resource
    EducationTrainService educationTrainService;

    @PostMapping(value = "/addOrUpdate")
    public ResponseBase addOrUpdate(@RequestBody EducationTrainDto saveDto) {
        int i = educationTrainService.addOrUpdate(saveDto);
        if (i == 1) {
            return ResponseBase.success();
        }
        return ResponseBase.error("error");
    }

    @GetMapping(value = "/fetchAll/{projectId}")
    public ResponseBase fetchAll(@PathVariable("projectId") Integer projectId) {
        QueryWrapper<EducationTrain> wrapper = new QueryWrapper<>();
        wrapper.eq("project_id", projectId);
        List<EducationTrain> list = educationTrainService.list(wrapper);
        return ResponseBase.success(list);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseBase delete(@PathVariable("id") Long id) {
        boolean b = educationTrainService.removeById(id);
        return ResponseBase.success(b);
    }
}
