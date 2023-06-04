package com.ruoyi.jianguan.manage.produce.mapper;

import com.ruoyi.jianguan.manage.produce.domain.entity.PubProduceLibrary;
import com.ruoyi.system.domain.vo.PubProduceLibraryVo;
import com.ruoyi.common.core.mapper.BaseMapperPlus;
import org.apache.ibatis.annotations.Param;

/**
 * 工序库Mapper接口
 *
 * @author ruoyi
 * @date 2023-06-03
 */
public interface PubProduceLibraryMapper extends BaseMapperPlus<PubProduceLibraryMapper, PubProduceLibrary, PubProduceLibraryVo> {

    Integer selectCountByPrimaryKey(@Param("id") Integer id);
}
