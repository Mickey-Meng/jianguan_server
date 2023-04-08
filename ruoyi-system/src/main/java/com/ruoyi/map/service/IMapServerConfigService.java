package com.ruoyi.map.service;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;
import com.ruoyi.map.domain.bo.MapServerConfigBo;
import com.ruoyi.map.domain.vo.MapServerConfigVo;

import java.util.Collection;
import java.util.List;

/**
 * 地图服务注册Service接口
 *
 * @author ruoyi
 * @date 2023-04-08
 */
public interface IMapServerConfigService {

    /**
     * 查询地图服务注册
     */
    MapServerConfigVo queryById(Long id);

    /**
     * 查询地图服务注册列表
     */
    TableDataInfo<MapServerConfigVo> queryPageList(MapServerConfigBo bo, PageQuery pageQuery);

    /**
     * 查询地图服务注册列表
     */
    List<MapServerConfigVo> queryList(MapServerConfigBo bo);

    /**
     * 新增地图服务注册
     */
    Boolean insertByBo(MapServerConfigBo bo);

    /**
     * 修改地图服务注册
     */
    Boolean updateByBo(MapServerConfigBo bo);

    /**
     * 校验并批量删除地图服务注册信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

    int updateServerStatus(MapServerConfigBo bo);
}
