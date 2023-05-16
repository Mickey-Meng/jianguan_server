package com.ruoyi.jianguan.manage.company.service;

import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;
import com.ruoyi.jianguan.manage.company.domain.bo.JgCompanyBo;
import com.ruoyi.jianguan.manage.company.domain.vo.JgCompanyVo;

import java.util.Collection;
import java.util.List;

/**
 * 公司信息Service接口
 *
 * @author ruoyi
 * @date 2023-05-16
 */
public interface IJgCompanyService {

    /**
     * 查询公司信息
     */
    JgCompanyVo queryById(Long id);

    /**
     * 查询公司信息列表
     */
    TableDataInfo<JgCompanyVo> queryPageList(JgCompanyBo bo, PageQuery pageQuery);

    /**
     * 查询公司信息列表
     */
    List<JgCompanyVo> queryList(JgCompanyBo bo);

    /**
     * 新增公司信息
     */
    Boolean insertByBo(JgCompanyBo bo);

    /**
     * 修改公司信息
     */
    Boolean updateByBo(JgCompanyBo bo);

    /**
     * 校验并批量删除公司信息信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
