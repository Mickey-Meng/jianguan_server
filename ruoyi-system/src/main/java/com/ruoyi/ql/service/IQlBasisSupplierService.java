package com.ruoyi.ql.service;

import com.ruoyi.ql.domain.QlBasisSupplier;
import com.ruoyi.ql.domain.vo.QlBasisSupplierVo;
import com.ruoyi.ql.domain.bo.QlBasisSupplierBo;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 供应商管理Service接口
 *
 * @author ruoyi
 * @date 2022-12-11
 */
public interface IQlBasisSupplierService {

    /**
     * 查询供应商管理
     */
    QlBasisSupplierVo queryById(Long id);

    /**
     * 查询供应商管理列表
     */
    TableDataInfo<QlBasisSupplierVo> queryPageList(QlBasisSupplierBo bo, PageQuery pageQuery);

    /**
     * 查询供应商管理列表
     */
    List<QlBasisSupplierVo> queryList(QlBasisSupplierBo bo);

    /**
     * 新增供应商管理
     */
    Boolean insertByBo(QlBasisSupplierBo bo);

    /**
     * 修改供应商管理
     */
    Boolean updateByBo(QlBasisSupplierBo bo);

    /**
     * 校验并批量删除供应商管理信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
