package com.ruoyi.jianguan.business.quality.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.common.core.domain.object.ResponseBase;
import com.ruoyi.jianguan.business.quality.domain.dto.BuildPlanPageDTO;
import com.ruoyi.jianguan.business.quality.domain.dto.BuildPlanSaveDTO;
import com.ruoyi.jianguan.business.quality.domain.entity.BuildPlan;
import com.ruoyi.jianguan.business.quality.domain.vo.BuildPlanDetailVo;
import com.ruoyi.jianguan.business.quality.domain.vo.BuildPlanPageVo;
import com.github.pagehelper.PageInfo;

import javax.servlet.http.HttpServletResponse;

/**
 * 施工方案 服务类
 *
 * @author qiaoxulin
 * @date 2022-05-25
 */
public interface BuildPlanService extends IService<BuildPlan> {
    /**
     * 新增或者更新施工方案数据
     *
     * @param saveDto
     * @return
     */
    ResponseBase addOrUpdate(BuildPlanSaveDTO saveDto);

    /**
     * 通过id获取一条施工方案数据
     *
     * @param id
     * @return
     */
    BuildPlanDetailVo getInfoById(Long id);

    /**
     * 分页查询施工方案数据
     *
     * @param pageDto
     * @return
     */
    PageInfo<BuildPlanPageVo> getPageInfo(BuildPlanPageDTO pageDto);

    /**
     * 施工方案导出
     *
     * @param pageDto
     * @param response
     */
    void export(BuildPlanPageDTO pageDto, HttpServletResponse response);
}
