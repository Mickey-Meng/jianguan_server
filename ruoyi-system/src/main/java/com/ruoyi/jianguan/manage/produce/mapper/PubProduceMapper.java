package com.ruoyi.jianguan.manage.produce.mapper;

import com.ruoyi.common.core.mapper.BaseMapperPlus;
import com.ruoyi.jianguan.manage.produce.domain.entity.PubProduce;
import com.ruoyi.jianguan.manage.produce.domain.vo.PubProduceVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 工序信息Mapper接口
 *
 * @author ruoyi
 * @date 2023-06-03
 */
public interface PubProduceMapper extends BaseMapperPlus<PubProduceMapper, PubProduce, PubProduceVo> {

    Integer selectCountByPrimaryKey(@Param("id") Long id);

    List<PubProduce> selectProduceListByTypeId(@Param("typeId") Long typeId);
}
