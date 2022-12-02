package com.ruoyi.system.service;


import com.ruoyi.common.core.domain.PageQuery;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.system.domain.bo.ContractInfoBo;
import com.ruoyi.system.domain.vo.ContractInfoVo;

import java.util.Collection;
import java.util.List;

/**
 * 合同条款Service接口
 *
 * @author ruoyi
 * @date 2022-12-02
 */
public interface IContractInfoService {

    /**
     * 查询合同条款
     */
    ContractInfoVo queryById(String HTBH);

    /**
     * 查询合同条款列表
     */
    TableDataInfo<ContractInfoVo> queryPageList(ContractInfoBo bo, PageQuery pageQuery);

    /**
     * 查询合同条款列表
     */
    List<ContractInfoVo> queryList(ContractInfoBo bo);

    /**
     * 新增合同条款
     */
    Boolean insertByBo(ContractInfoBo bo);

    /**
     * 修改合同条款
     */
    Boolean updateByBo(ContractInfoBo bo);

    /**
     * 校验并批量删除合同条款信息
     */
    Boolean deleteWithValidByIds(Collection<String> ids, Boolean isValid);
}
