package com.ruoyi.ql.service;

import com.ruoyi.ql.domain.QlProjectInfo;
import com.ruoyi.ql.domain.vo.QlProjectInfoVo;
import com.ruoyi.ql.domain.bo.QlProjectInfoBo;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 项目信息Service接口
 *
 * @author ruoyi
 * @date 2022-12-11
 */
public interface IQlProjectInfoService {

    /**
     * 查询项目信息
     */
    QlProjectInfoVo queryById(Long id);

    /**
     * 查询项目信息列表
     */
    TableDataInfo<QlProjectInfoVo> queryPageList(QlProjectInfoBo bo, PageQuery pageQuery);

    /**
     * 查询项目信息列表
     */
    List<QlProjectInfoVo> queryList(QlProjectInfoBo bo);

    /**
     * 新增项目信息
     */
    Boolean insertByBo(QlProjectInfoBo bo);

    /**
     * 修改项目信息
     */
    Boolean updateByBo(QlProjectInfoBo bo);

    /**
     * 校验并批量删除项目信息信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
