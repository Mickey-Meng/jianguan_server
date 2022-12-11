package com.ruoyi.ql.service;

import com.ruoyi.ql.domain.QlWhWarehouse;
import com.ruoyi.ql.domain.vo.QlWhWarehouseVo;
import com.ruoyi.ql.domain.bo.QlWhWarehouseBo;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 仓库设置Service接口
 *
 * @author ruoyi
 * @date 2022-12-11
 */
public interface IQlWhWarehouseService {

    /**
     * 查询仓库设置
     */
    QlWhWarehouseVo queryById(Long id);

    /**
     * 查询仓库设置列表
     */
    TableDataInfo<QlWhWarehouseVo> queryPageList(QlWhWarehouseBo bo, PageQuery pageQuery);

    /**
     * 查询仓库设置列表
     */
    List<QlWhWarehouseVo> queryList(QlWhWarehouseBo bo);

    /**
     * 新增仓库设置
     */
    Boolean insertByBo(QlWhWarehouseBo bo);

    /**
     * 修改仓库设置
     */
    Boolean updateByBo(QlWhWarehouseBo bo);

    /**
     * 校验并批量删除仓库设置信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
