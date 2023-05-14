package com.ruoyi.ql.service;

import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;
import com.ruoyi.ql.domain.bo.QlPaymentWarehousingRelBo;
import com.ruoyi.ql.domain.vo.QlPaymentWarehousingRelVo;

import java.util.Collection;
import java.util.List;

/**
 * 付款与入库关系Service接口
 *
 * @author ruoyi
 * @date 2023-05-10
 */
public interface IQlPaymentWarehousingRelService {

    /**
     * 查询付款与入库关系
     */
    QlPaymentWarehousingRelVo queryById(Long id);

    /**
     * 查询付款与入库关系列表
     */
    TableDataInfo<QlPaymentWarehousingRelVo> queryPageList(QlPaymentWarehousingRelBo bo, PageQuery pageQuery);

    /**
     * 查询付款与入库关系列表
     */
    List<QlPaymentWarehousingRelVo> queryList(QlPaymentWarehousingRelBo bo);

    /**
     * 新增付款与入库关系
     */
    Boolean insertByBo(QlPaymentWarehousingRelBo bo);

    /**
     * 修改付款与入库关系
     */
    Boolean updateByBo(QlPaymentWarehousingRelBo bo);

    /**
     * 校验并批量删除付款与入库关系信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}