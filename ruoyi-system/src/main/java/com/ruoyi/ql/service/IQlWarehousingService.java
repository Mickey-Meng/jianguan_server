package com.ruoyi.ql.service;

import com.ruoyi.ql.domain.QlWarehousing;
import com.ruoyi.ql.domain.bo.QlOutboundBo;
import com.ruoyi.ql.domain.vo.QlWarehousingVo;
import com.ruoyi.ql.domain.bo.QlWarehousingBo;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 入库管理Service接口
 *
 * @author ruoyi
 * @date 2022-12-11
 */
public interface IQlWarehousingService {

    /**
     * 查询入库管理
     */
    QlWarehousingVo queryById(Long id);

    /**
     * 查询入库管理列表
     */
    TableDataInfo<QlWarehousingVo> queryPageList(QlWarehousingBo bo, PageQuery pageQuery);

    /**
     * 查询入库管理列表
     */
    List<QlWarehousingVo> queryList(QlWarehousingBo bo);

    /**
     * 新增入库管理
     */
    Boolean insertByBo(QlWarehousingBo bo);
    void batchInsertBo(List<QlWarehousingBo> bos);

    /**
     * 修改入库管理
     */
    Boolean updateByBo(QlWarehousingBo bo);

    /**
     * 校验并批量删除入库管理信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

    String getInventoryId(String type);
}
