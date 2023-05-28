package com.ruoyi.jianguan.manage.project.service;

import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;
import com.ruoyi.jianguan.manage.project.domain.bo.PubMonitorBo;
import com.ruoyi.jianguan.manage.project.domain.vo.PubMonitorVo;

import java.util.Collection;
import java.util.List;

/**
 * 设备监控Service接口
 *
 * @author ruoyi
 * @date 2023-05-28
 */
public interface IPubMonitorService {

    /**
     * 查询设备监控
     */
    PubMonitorVo queryById(Integer id);

    /**
     * 查询设备监控列表
     */
    TableDataInfo<PubMonitorVo> queryPageList(PubMonitorBo bo, PageQuery pageQuery);

    /**
     * 查询设备监控列表
     */
    List<PubMonitorVo> queryList(PubMonitorBo bo);

    /**
     * 新增设备监控
     */
    Boolean insertByBo(PubMonitorBo bo);

    /**
     * 修改设备监控
     */
    Boolean updateByBo(PubMonitorBo bo);

    /**
     * 校验并批量删除设备监控信息
     */
    Boolean deleteWithValidByIds(Collection<Integer> ids, Boolean isValid);


}
