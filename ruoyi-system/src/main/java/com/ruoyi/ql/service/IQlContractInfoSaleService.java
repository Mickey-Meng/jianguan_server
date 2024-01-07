package com.ruoyi.ql.service;

import com.ruoyi.ql.domain.QlContractInfoSale;
import com.ruoyi.ql.domain.vo.QlContractInfoSaleVo;
import com.ruoyi.ql.domain.bo.QlContractInfoSaleBo;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 合同管理Service接口
 *
 * @author ruoyi
 * @date 2022-12-11
 */
public interface IQlContractInfoSaleService {

    /**
     * 查询合同管理
     */
    QlContractInfoSaleVo queryById(Long id);

    /**
     * 查询合同管理列表
     */
    TableDataInfo<QlContractInfoSaleVo> queryPageList(QlContractInfoSaleBo bo, PageQuery pageQuery);

    /**
     * 查询合同管理列表
     */
    List<QlContractInfoSaleVo> queryList(QlContractInfoSaleBo bo);

    /**
     * 新增合同管理
     */
    Boolean insertByBo(QlContractInfoSaleBo bo);

    /**
     * 修改合同管理
     */
    Boolean updateByBo(QlContractInfoSaleBo bo);

    /**
     * 校验并批量删除合同管理信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

    void batchInsertBo(List<QlContractInfoSaleBo> qlContractInfoSaleBos);
}
