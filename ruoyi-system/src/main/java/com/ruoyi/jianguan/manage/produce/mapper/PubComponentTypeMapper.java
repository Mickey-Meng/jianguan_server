package com.ruoyi.jianguan.manage.produce.mapper;

import com.ruoyi.common.core.mapper.BaseMapperPlus;
import com.ruoyi.jianguan.manage.produce.domain.entity.PubComponentType;
import com.ruoyi.jianguan.manage.produce.domain.vo.PubComponentTypeVo;
import org.apache.ibatis.annotations.Param;

/**
 * 构建类型Mapper接口
 *
 * @author ruoyi
 * @date 2023-06-03
 */
public interface PubComponentTypeMapper extends BaseMapperPlus<PubComponentTypeMapper, PubComponentType, PubComponentTypeVo> {

    Integer selectCountByPrimaryKey(@Param("id") Integer id);
}
