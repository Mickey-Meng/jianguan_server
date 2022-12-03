package com.ruoyi.project.materialprepayment.service;


import com.ruoyi.common.core.domain.PageQuery;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.project.materialprepayment.domain.bo.MeaMaterialPrepaymentBo;
import com.ruoyi.project.materialprepayment.domain.vo.MeaMaterialPrepaymentVo;

import java.util.Collection;
import java.util.List;

/**
 * 材料预付款Service接口
 *
 * @author ruoyi
 * @date 2022-12-03
 */
public interface IMeaMaterialPrepaymentService {

    /**
     * 查询材料预付款
     */
    MeaMaterialPrepaymentVo queryById(String id);

    /**
     * 查询材料预付款列表
     */
    TableDataInfo<MeaMaterialPrepaymentVo> queryPageList(MeaMaterialPrepaymentBo bo, PageQuery pageQuery);

    /**
     * 查询材料预付款列表
     */
    List<MeaMaterialPrepaymentVo> queryList(MeaMaterialPrepaymentBo bo);

    /**
     * 新增材料预付款
     */
    Boolean insertByBo(MeaMaterialPrepaymentBo bo);

    /**
     * 修改材料预付款
     */
    Boolean updateByBo(MeaMaterialPrepaymentBo bo);

    /**
     * 校验并批量删除材料预付款信息
     */
    Boolean deleteWithValidByIds(Collection<String> ids, Boolean isValid);
}
