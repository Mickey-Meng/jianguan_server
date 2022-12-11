package com.ruoyi.ql.service;

import com.ruoyi.ql.domain.QlWhReservoir;
import com.ruoyi.ql.domain.vo.QlWhReservoirVo;
import com.ruoyi.ql.domain.bo.QlWhReservoirBo;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 库区设置Service接口
 *
 * @author ruoyi
 * @date 2022-12-11
 */
public interface IQlWhReservoirService {

    /**
     * 查询库区设置
     */
    QlWhReservoirVo queryById(Long id);

    /**
     * 查询库区设置列表
     */
    TableDataInfo<QlWhReservoirVo> queryPageList(QlWhReservoirBo bo, PageQuery pageQuery);

    /**
     * 查询库区设置列表
     */
    List<QlWhReservoirVo> queryList(QlWhReservoirBo bo);

    /**
     * 新增库区设置
     */
    Boolean insertByBo(QlWhReservoirBo bo);

    /**
     * 修改库区设置
     */
    Boolean updateByBo(QlWhReservoirBo bo);

    /**
     * 校验并批量删除库区设置信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
