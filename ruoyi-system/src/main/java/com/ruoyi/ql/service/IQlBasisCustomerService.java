package com.ruoyi.ql.service;

import com.ruoyi.ql.domain.QlBasisCustomer;
import com.ruoyi.ql.domain.vo.QlBasisCustomerVo;
import com.ruoyi.ql.domain.bo.QlBasisCustomerBo;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 客户资料Service接口
 *
 * @author ruoyi
 * @date 2022-12-11
 */
public interface IQlBasisCustomerService {

    /**
     * 查询客户资料
     */
    QlBasisCustomerVo queryById(Long id);

    /**
     * 查询客户资料列表
     */
    TableDataInfo<QlBasisCustomerVo> queryPageList(QlBasisCustomerBo bo, PageQuery pageQuery);

    /**
     * 查询客户资料列表
     */
    List<QlBasisCustomerVo> queryList(QlBasisCustomerBo bo);

    /**
     * 新增客户资料
     */
    Boolean insertByBo(QlBasisCustomerBo bo);

    /**
     * 修改客户资料
     */
    Boolean updateByBo(QlBasisCustomerBo bo);

    /**
     * 校验并批量删除客户资料信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
