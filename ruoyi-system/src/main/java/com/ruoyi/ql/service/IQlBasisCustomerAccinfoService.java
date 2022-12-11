package com.ruoyi.ql.service;

import com.ruoyi.ql.domain.QlBasisCustomerAccinfo;
import com.ruoyi.ql.domain.vo.QlBasisCustomerAccinfoVo;
import com.ruoyi.ql.domain.bo.QlBasisCustomerAccinfoBo;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 客户账户信息Service接口
 *
 * @author ruoyi
 * @date 2022-12-11
 */
public interface IQlBasisCustomerAccinfoService {

    /**
     * 查询客户账户信息
     */
    QlBasisCustomerAccinfoVo queryById(Long id);

    /**
     * 查询客户账户信息列表
     */
    TableDataInfo<QlBasisCustomerAccinfoVo> queryPageList(QlBasisCustomerAccinfoBo bo, PageQuery pageQuery);

    /**
     * 查询客户账户信息列表
     */
    List<QlBasisCustomerAccinfoVo> queryList(QlBasisCustomerAccinfoBo bo);

    /**
     * 新增客户账户信息
     */
    Boolean insertByBo(QlBasisCustomerAccinfoBo bo);

    /**
     * 修改客户账户信息
     */
    Boolean updateByBo(QlBasisCustomerAccinfoBo bo);

    /**
     * 校验并批量删除客户账户信息信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
