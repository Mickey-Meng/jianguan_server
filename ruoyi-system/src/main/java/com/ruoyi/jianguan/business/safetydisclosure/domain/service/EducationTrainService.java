package com.ruoyi.jianguan.business.safetydisclosure.domain.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.jianguan.business.safetydisclosure.domain.dto.EducationTrainDto;
import com.ruoyi.jianguan.business.safetydisclosure.domain.entity.EducationTrain;

/**
 * @author bx 2023/5/20
 */
public interface EducationTrainService extends IService<EducationTrain> {

    int addOrUpdate(EducationTrainDto saveDto);
}
