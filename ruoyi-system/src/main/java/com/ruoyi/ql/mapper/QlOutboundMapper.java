package com.ruoyi.ql.mapper;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.ruoyi.ql.domain.QlOutbound;
import com.ruoyi.ql.domain.QlWarehousing;
import com.ruoyi.ql.domain.vo.QlOutboundVo;
import com.ruoyi.common.core.mapper.BaseMapperPlus;

import java.util.List;

/**
 * 出库管理Mapper接口
 *
 * @author ruoyi
 * @date 2023-05-05
 */
public interface QlOutboundMapper extends BaseMapperPlus<QlOutboundMapper, QlOutbound, QlOutboundVo> {
    String getInventoryId(String formattedDateTime);
    @InterceptorIgnore(tenantLine = "true")
    List<QlOutbound> getQlOutboundExpireScheduled();
}
