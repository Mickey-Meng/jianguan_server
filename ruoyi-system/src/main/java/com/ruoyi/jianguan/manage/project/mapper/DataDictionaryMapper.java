package com.ruoyi.jianguan.manage.project.mapper;

import com.ruoyi.common.core.mapper.BaseMapperPlus;
import com.ruoyi.jianguan.manage.project.domain.entity.DataDictionary;
import com.ruoyi.jianguan.manage.project.domain.vo.DataDictionaryVo;

import java.util.List;

/**
 * 商品类别Mapper接口
 *
 * @author ruoyi
 * @date 2022-12-11
 */
public interface DataDictionaryMapper extends BaseMapperPlus<DataDictionaryMapper, DataDictionary, DataDictionaryVo> {

    List<DataDictionaryVo> selectVoListByPCode(String pCode);

}
