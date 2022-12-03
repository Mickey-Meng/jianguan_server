package com.ruoyi.project.contract.service;


import com.ruoyi.common.core.domain.PageQuery;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.project.contract.domain.bo.MeaContractInfoBo;
import com.ruoyi.project.contract.domain.vo.MeaContractInfoVo;

import java.util.Collection;
import java.util.List;

/**
 * 合同条款Service接口
 *
 * @author ruoyi
 * @date 2022-12-03
 */
public interface IMeaContractInfoService {

    /**
     * 查询合同条款
     */
    MeaContractInfoVo queryById(String id);

    /**
     * 查询合同条款列表
     */
    TableDataInfo<MeaContractInfoVo> queryPageList(MeaContractInfoBo bo, PageQuery pageQuery);

    /**
     * 查询合同条款列表
     */
    List<MeaContractInfoVo> queryList(MeaContractInfoBo bo);

    /**
     * 新增合同条款
     */
    Boolean insertByBo(MeaContractInfoBo bo);

    /**
     * 修改合同条款
     */
    Boolean updateByBo(MeaContractInfoBo bo);

    /**
     * 校验并批量删除合同条款信息
     */
    Boolean deleteWithValidByIds(Collection<String> ids, Boolean isValid);
}
