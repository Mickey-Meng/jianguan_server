package com.ruoyi.project.flowidatenfo.service;


import com.ruoyi.common.core.domain.PageQuery;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.project.flowidatenfo.domain.bo.MeaFlowDataInfoBo;
import com.ruoyi.project.flowidatenfo.domain.vo.MeaFlowDataInfoVo;

import java.util.Collection;
import java.util.List;

/**
 * 工作流单数据Service接口
 *
 * @author ruoyi
 * @date 2022-12-10
 */
public interface IMeaFlowDataInfoService {

    /**
     * 查询工作流单数据
     */
    MeaFlowDataInfoVo queryById(Long id);

    /**
     * 查询工作流单数据列表
     */
    TableDataInfo<MeaFlowDataInfoVo> queryPageList(MeaFlowDataInfoBo bo, PageQuery pageQuery);

    /**
     * 查询工作流单数据列表
     */
    List<MeaFlowDataInfoVo> queryList(MeaFlowDataInfoBo bo);

    /**
     * 新增工作流单数据
     */
    Boolean insertByBo(MeaFlowDataInfoBo bo);

    /**
     * 修改工作流单数据
     */
    Boolean updateByBo(MeaFlowDataInfoBo bo);

    /**
     * 校验并批量删除工作流单数据信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
