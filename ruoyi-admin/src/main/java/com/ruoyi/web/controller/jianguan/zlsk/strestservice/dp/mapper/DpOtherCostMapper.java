package com.ruoyi.web.controller.jianguan.zlsk.strestservice.dp.mapper;

import com.ruoyi.web.controller.jianguan.zlsk.strestservice.dp.model.DpOtherCost;
import org.springframework.stereotype.*;
import org.apache.ibatis.annotations.*;
import java.util.*;

@Repository("dpOtherCostMapper")
public interface DpOtherCostMapper extends BaseMapper<DpOtherCost>
{
    List<DpOtherCost> selectList(@Param("engineeringId") final Integer p0, @Param("costType") final Integer p1);

    Double selectTypeTotalCost(@Param("engineeringId") final Integer p0, @Param("costType") final Integer p1);
}
