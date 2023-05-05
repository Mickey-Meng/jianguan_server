package com.ruoyi.ql.service;

import com.ruoyi.ql.domain.QlFinReceivable;
import com.ruoyi.ql.domain.vo.QlFinReceivableVo;
import com.ruoyi.ql.domain.bo.QlFinReceivableBo;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 收款记录Service接口
 *
 * @author ruoyi
 * @date 2023-05-05
 */
public interface IQlFinReceivableService {

    /**
     * 查询收款记录
     */
    QlFinReceivableVo queryById(Long id);

    /**
     * 查询收款记录列表
     */
    TableDataInfo<QlFinReceivableVo> queryPageList(QlFinReceivableBo bo, PageQuery pageQuery);

    /**
     * 查询收款记录列表
     */
    List<QlFinReceivableVo> queryList(QlFinReceivableBo bo);

    /**
     * 新增收款记录
     */
    Boolean insertByBo(QlFinReceivableBo bo);

    /**
     * 修改收款记录
     */
    Boolean updateByBo(QlFinReceivableBo bo);

    /**
     * 校验并批量删除收款记录信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
