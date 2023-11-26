package com.ruoyi.ql.mapper;

import com.ruoyi.common.core.mapper.BaseMapperPlus;
import com.ruoyi.ql.domain.QlInvoiceItem;
import com.ruoyi.ql.domain.vo.QlInvoiceItemVo;
import org.apache.ibatis.annotations.Param;

/**
 * 【请填写功能名称】Mapper接口
 *
 * @author mickey
 * @date 2023-11-07
 */
public interface QlInvoiceItemMapper extends BaseMapperPlus<QlInvoiceItemMapper, QlInvoiceItem, QlInvoiceItemVo> {

    public void deleteBySourceId();
}
