package com.ruoyi.jianguan.manage.map.service;

import cn.hutool.core.lang.tree.Tree;
import com.ruoyi.jianguan.manage.map.domain.bo.MapPlanBo;
import com.ruoyi.jianguan.manage.map.domain.vo.MapPlanVo;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 地图方案管理Service接口
 *
 * @author ruoyi
 * @date 2023-04-08
 */
public interface IMapPlanService {

    /**
     * 查询地图方案管理
     */
    MapPlanVo queryById(Long id);

    /**
     * 查询地图方案管理列表
     */
    TableDataInfo<MapPlanVo> queryPageList(MapPlanBo bo, PageQuery pageQuery);

    /**
     * 查询地图方案管理列表
     */
    List<MapPlanVo> queryList(MapPlanBo bo);

    /**
     * 新增地图方案管理
     */
    Boolean insertByBo(MapPlanBo bo);

    /**
     * 修改地图方案管理
     */
    Boolean updateByBo(MapPlanBo bo);

    /**
     * 校验并批量删除地图方案管理信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

    /**
     * 查询地图方案管理树列表
     * @param bo
     * @return
     */
    List<Tree<Long>> getMapPlanTree(MapPlanBo bo);
}