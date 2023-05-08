package com.ruoyi.jianguan.manage.project.service;

import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;
import com.ruoyi.jianguan.manage.project.domain.bo.JgProjectItemBo;
import com.ruoyi.jianguan.manage.project.domain.vo.JgProjectItemVo;

import java.util.Collection;
import java.util.List;

/**
 * 项目详情Service接口
 *
 * @author ruoyi
 * @date 2023-05-08
 */
public interface IJgProjectItemService {

    /**
     * 查询项目详情
     */
    JgProjectItemVo queryById(Long id);

    /**
     * 查询项目详情列表
     */
    TableDataInfo<JgProjectItemVo> queryPageList(JgProjectItemBo bo, PageQuery pageQuery);

    /**
     * 查询项目详情列表
     */
    List<JgProjectItemVo> queryList(JgProjectItemBo bo);

    /**
     * 新增项目详情
     */
    Boolean insertByBo(JgProjectItemBo bo);

    /**
     * 修改项目详情
     */
    Boolean updateByBo(JgProjectItemBo bo);

    /**
     * 校验并批量删除项目详情信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

    boolean saveProjectItem(JgProjectItemBo bo);
}
