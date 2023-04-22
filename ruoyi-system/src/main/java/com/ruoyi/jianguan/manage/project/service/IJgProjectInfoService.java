package com.ruoyi.jianguan.manage.project.service;

import cn.hutool.core.lang.tree.Tree;
import com.ruoyi.jianguan.manage.project.domain.bo.JgProjectInfoBo;
import com.ruoyi.jianguan.manage.project.domain.vo.JgProjectInfoVo;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 项目信息Service接口
 *
 * @author ruoyi
 * @date 2023-04-19
 */
public interface IJgProjectInfoService {

    /**
     * 查询项目信息
     */
    JgProjectInfoVo queryById(Long id);

    /**
     * 查询项目信息列表
     */
    TableDataInfo<JgProjectInfoVo> queryPageList(JgProjectInfoBo bo, PageQuery pageQuery);

    /**
     * 查询项目信息列表
     */
    List<JgProjectInfoVo> queryList(JgProjectInfoBo bo);

    /**
     * 新增项目信息
     */
    Boolean insertByBo(JgProjectInfoBo bo);

    /**
     * 修改项目信息
     */
    Boolean updateByBo(JgProjectInfoBo bo);

    /**
     * 校验并批量删除项目信息信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

    /**
     * 项目机构树
     * @param bo
     * @return
     */
    List<Tree<Long>> getProjectTree(JgProjectInfoBo bo);
}
