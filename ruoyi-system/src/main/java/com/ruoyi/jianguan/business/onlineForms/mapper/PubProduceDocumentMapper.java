package com.ruoyi.jianguan.business.onlineForms.mapper;

import com.ruoyi.jianguan.business.onlineForms.domain.PubProduceDocument;
import com.ruoyi.jianguan.business.onlineForms.domain.vo.PubProduceDocumentVo;
import com.ruoyi.common.core.mapper.BaseMapperPlus;
import org.apache.ibatis.annotations.Param;

/**
 * 工序附件信息Mapper接口
 *
 * @author mickey
 * @date 2024-01-02
 */
public interface PubProduceDocumentMapper extends BaseMapperPlus<PubProduceDocumentMapper, PubProduceDocument, PubProduceDocumentVo> {

    Integer selectCountByPrimaryKey(@Param("id") Long id);
}
