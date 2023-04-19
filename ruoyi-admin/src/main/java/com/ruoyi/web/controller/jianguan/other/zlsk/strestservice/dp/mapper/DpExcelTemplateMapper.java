package com.ruoyi.web.controller.jianguan.other.zlsk.strestservice.dp.mapper;

import com.ruoyi.web.controller.jianguan.other.zlsk.strestservice.dp.model.DpExcelTemplate;
import org.springframework.stereotype.*;
import org.apache.ibatis.annotations.*;
import java.util.*;

@Repository
public interface DpExcelTemplateMapper
{
    List<DpExcelTemplate> selectInfo(@Param("typeId") final Integer p0, @Param("parentId") final Integer p1);
}
