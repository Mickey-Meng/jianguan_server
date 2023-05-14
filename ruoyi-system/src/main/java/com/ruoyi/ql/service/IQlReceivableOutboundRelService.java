package com.ruoyi.ql.service;


import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;
import com.ruoyi.ql.domain.bo.QlReceivableOutboundRelBo;
import com.ruoyi.ql.domain.vo.QlReceivableOutboundRelVo;

import java.util.Collection;
import java.util.List;

/**
 * 付款与入库关系Service接口
 *
 * @author ruoyi
 * @date 2023-05-11
 */
public interface IQlReceivableOutboundRelService {

    /**
     * 查询付款与入库关系
     */
    QlReceivableOutboundRelVo queryById(Long id);

    /**
     * 查询付款与入库关系列表
     */
    TableDataInfo<QlReceivableOutboundRelVo> queryPageList(QlReceivableOutboundRelBo bo, PageQuery pageQuery);

    /**
     * 查询付款与入库关系列表
     */
    List<QlReceivableOutboundRelVo> queryList(QlReceivableOutboundRelBo bo);

    /**
     * 新增付款与入库关系
     */
    Boolean insertByBo(QlReceivableOutboundRelBo bo);

    /**
     * 修改付款与入库关系
     */
    Boolean updateByBo(QlReceivableOutboundRelBo bo);

    /**
     * 校验并批量删除付款与入库关系信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}