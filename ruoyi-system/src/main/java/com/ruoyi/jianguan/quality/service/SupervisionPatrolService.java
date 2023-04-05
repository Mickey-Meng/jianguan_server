package com.ruoyi.jianguan.quality.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.jianguan.quality.domain.dto.SupervisionPatrolPageDTO;
import com.ruoyi.jianguan.quality.domain.dto.SupervisionPatrolSaveDTO;
import com.ruoyi.jianguan.quality.domain.entity.SupervisionPatrol;
import com.ruoyi.jianguan.quality.domain.vo.SupervisionPatrolDetailVo;
import com.ruoyi.jianguan.quality.domain.vo.SupervisionPatrolPageVo;
import com.github.pagehelper.PageInfo;
import com.ruoyi.common.core.domain.object.ResponseBase;

import javax.servlet.http.HttpServletResponse;

/**
 * 监理巡视 服务类
 *
 * @author qiaoxulin
 * @date 2022-06-10
 */
public interface SupervisionPatrolService extends IService<SupervisionPatrol> {

    /**
     * 新增或者更新监理巡视数据
     *
     * @param saveDto
     * @return
     */
    ResponseBase addOrUpdate(SupervisionPatrolSaveDTO saveDto);

    /**
     * 通过id获取一条监理巡视数据
     *
     * @param id
     * @return
     */
    SupervisionPatrolDetailVo getInfoById(Long id);

    /**
     * 分页查询监理巡视数据
     *
     * @param pageDto
     * @return
     */
    PageInfo<SupervisionPatrolPageVo> getPageInfo(SupervisionPatrolPageDTO pageDto);

    /**
     * 监理巡视导出
     *
     * @param pageDto
     * @param response
     */
    void export(SupervisionPatrolPageDTO pageDto, HttpServletResponse response);
}
