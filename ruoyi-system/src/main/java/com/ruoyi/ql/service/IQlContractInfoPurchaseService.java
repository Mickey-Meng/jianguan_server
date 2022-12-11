package com.ruoyi.ql.service;

import com.ruoyi.ql.domain.QlContractInfoPurchase;
import com.ruoyi.ql.domain.vo.QlContractInfoPurchaseVo;
import com.ruoyi.ql.domain.bo.QlContractInfoPurchaseBo;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 采购合同 Service接口
 *
 * @author ruoyi
 * @date 2022-12-11
 */
public interface IQlContractInfoPurchaseService {

    /**
     * 查询采购合同
     */
    QlContractInfoPurchaseVo queryById(Long id);

    /**
     * 查询采购合同 列表
     */
    TableDataInfo<QlContractInfoPurchaseVo> queryPageList(QlContractInfoPurchaseBo bo, PageQuery pageQuery);

    /**
     * 查询采购合同 列表
     */
    List<QlContractInfoPurchaseVo> queryList(QlContractInfoPurchaseBo bo);

    /**
     * 新增采购合同
     */
    Boolean insertByBo(QlContractInfoPurchaseBo bo);

    /**
     * 修改采购合同
     */
    Boolean updateByBo(QlContractInfoPurchaseBo bo);

    /**
     * 校验并批量删除采购合同 信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
