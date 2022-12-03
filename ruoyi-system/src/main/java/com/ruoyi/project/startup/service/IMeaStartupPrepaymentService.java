package com.ruoyi.project.startup.service;


import com.ruoyi.common.core.domain.PageQuery;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.project.startup.domain.bo.MeaStartupPrepaymentBo;
import com.ruoyi.project.startup.domain.vo.MeaStartupPrepaymentVo;

import java.util.Collection;
import java.util.List;

/**
 * 开工预付款Service接口
 *
 * @author ruoyi
 * @date 2022-12-03
 */
public interface IMeaStartupPrepaymentService {

    /**
     * 查询开工预付款
     */
    MeaStartupPrepaymentVo queryById(String id);

    /**
     * 查询开工预付款列表
     */
    TableDataInfo<MeaStartupPrepaymentVo> queryPageList(MeaStartupPrepaymentBo bo, PageQuery pageQuery);

    /**
     * 查询开工预付款列表
     */
    List<MeaStartupPrepaymentVo> queryList(MeaStartupPrepaymentBo bo);

    /**
     * 新增开工预付款
     */
    Boolean insertByBo(MeaStartupPrepaymentBo bo);

    /**
     * 修改开工预付款
     */
    Boolean updateByBo(MeaStartupPrepaymentBo bo);

    /**
     * 校验并批量删除开工预付款信息
     */
    Boolean deleteWithValidByIds(Collection<String> ids, Boolean isValid);
}
