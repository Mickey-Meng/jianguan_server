package com.ruoyi.project.measurementNo.service;


import com.ruoyi.common.core.domain.PageQuery;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.project.measurementNo.domain.bo.MeaMeasurementNoBo;
import com.ruoyi.project.measurementNo.domain.vo.MeaMeasurementNoVo;

import java.util.Collection;
import java.util.List;

/**
 * 中间计量期数管理Service接口
 *
 * @author ruoyi
 * @date 2022-12-03
 */
public interface IMeaMeasurementNoService {

    /**
     * 查询中间计量期数管理
     */
    MeaMeasurementNoVo queryById(String id);

    MeaMeasurementNoVo queryMax();

    /**
     * 查询中间计量期数管理列表
     */
    TableDataInfo<MeaMeasurementNoVo> queryPageList(MeaMeasurementNoBo bo, PageQuery pageQuery);

    /**
     * 查询中间计量期数管理列表
     */
    List<MeaMeasurementNoVo> queryList(MeaMeasurementNoBo bo);

    /**
     * 新增中间计量期数管理
     */
    Boolean insertByBo(MeaMeasurementNoBo bo);

    /**
     * 修改中间计量期数管理
     */
    Boolean updateByBo(MeaMeasurementNoBo bo);
    /**
     * 修改中间计量期数管理
     */
    String lockingByJlqcbh(String jlqcbh) ;
    /**
     * 校验并批量删除中间计量期数管理信息
     */
    Boolean deleteWithValidByIds(Collection<String> ids, Boolean isValid);

    R sortList();

}
