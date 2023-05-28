package com.ruoyi.jianguan.business.safetydisclosure.domain.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.core.sequence.util.IdUtil;
import com.ruoyi.jianguan.business.safetydisclosure.domain.dto.EducationTrainDto;
import com.ruoyi.jianguan.business.safetydisclosure.domain.entity.EducationTrain;
import com.ruoyi.jianguan.business.safetydisclosure.domain.mapper.EducationTrainMapper;
import com.ruoyi.jianguan.business.safetydisclosure.domain.service.EducationTrainService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author bx 2023/5/20
 */
@Service
public class EducationTrainImpl extends ServiceImpl<EducationTrainMapper, EducationTrain> implements EducationTrainService {

    @Autowired
    EducationTrainMapper educationTrainMapper;

    @Override
    public int addOrUpdate(EducationTrainDto saveDto) {
        Long id = saveDto.getId();
        EducationTrain educationTrain = new EducationTrain();
        BeanUtils.copyProperties(saveDto, educationTrain);
        if (null == id) {
            educationTrain.setId(IdUtil.nextLongId());
            return educationTrainMapper.insert(educationTrain);
        } else {
            return educationTrainMapper.updateById(educationTrain);
        }
    }
}

