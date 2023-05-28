package com.ruoyi.ql.service;


import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;
import com.ruoyi.ql.domain.bo.QlWarehousingDetailBo;
import com.ruoyi.ql.domain.vo.QlWarehousingDetailVo;

import java.util.Collection;
import java.util.List;

/**
 * 入库单明细Service接口
 *
 * @author ruoyi
 * @date 2023-05-18
 */
public interface IQlWarehousingDetailService {

    /**
     * 查询入库单明细
     */
    QlWarehousingDetailVo queryById(Long id);

    /**
     * 查询入库单明细列表
     */
    TableDataInfo<QlWarehousingDetailVo> queryPageList(QlWarehousingDetailBo bo, PageQuery pageQuery);

    /**
     * 查询入库单明细列表
     */
    List<QlWarehousingDetailVo> queryList(QlWarehousingDetailBo bo);

    /**
     * 新增入库单明细
     */
    Boolean insertByBo(QlWarehousingDetailBo bo);

    /**
     * 修改入库单明细
     */
    Boolean updateByBo(QlWarehousingDetailBo bo);

    /**
     * 校验并批量删除入库单明细信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
