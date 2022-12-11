package com.ruoyi.ql.service;

import com.ruoyi.ql.domain.QlOutbound;
import com.ruoyi.ql.domain.vo.QlOutboundVo;
import com.ruoyi.ql.domain.bo.QlOutboundBo;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 出库管理Service接口
 *
 * @author ruoyi
 * @date 2022-12-11
 */
public interface IQlOutboundService {

    /**
     * 查询出库管理
     */
    QlOutboundVo queryById(Long id);

    /**
     * 查询出库管理列表
     */
    TableDataInfo<QlOutboundVo> queryPageList(QlOutboundBo bo, PageQuery pageQuery);

    /**
     * 查询出库管理列表
     */
    List<QlOutboundVo> queryList(QlOutboundBo bo);

    /**
     * 新增出库管理
     */
    Boolean insertByBo(QlOutboundBo bo);

    /**
     * 修改出库管理
     */
    Boolean updateByBo(QlOutboundBo bo);

    /**
     * 校验并批量删除出库管理信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
