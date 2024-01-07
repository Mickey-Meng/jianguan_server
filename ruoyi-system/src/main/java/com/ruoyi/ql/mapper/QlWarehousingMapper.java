package com.ruoyi.ql.mapper;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.ruoyi.ql.domain.QlContractInfoSale;
import com.ruoyi.ql.domain.QlWarehousing;
import com.ruoyi.ql.domain.vo.QlWarehousingVo;
import com.ruoyi.common.core.mapper.BaseMapperPlus;

import java.util.List;

/**
 * 入库管理Mapper接口
 *
 * @author ruoyi
 * @date 2022-12-11
 */
public interface QlWarehousingMapper extends BaseMapperPlus<QlWarehousingMapper, QlWarehousing, QlWarehousingVo> {
    String getInventoryId(String formattedDateTime);
    @InterceptorIgnore(tenantLine = "true")
    List<QlWarehousing> getWarehousingExpireScheduled();
}
