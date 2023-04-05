package com.ruoyi.jianguan.quality.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.jianguan.quality.domain.dto.ManageTargetSaveDTO;
import com.ruoyi.common.core.domain.dto.PageDTO;
import com.ruoyi.jianguan.quality.domain.entity.ManageTarget;
import com.ruoyi.jianguan.quality.domain.vo.ManageTargetDetailVo;
import com.ruoyi.jianguan.quality.domain.vo.ManageTargetPageVo;
import com.github.pagehelper.PageInfo;

import javax.servlet.http.HttpServletResponse;

/**
 * 管理目标 服务类
 *
 * @author qiaoxulin
 * @date 2022-05-13
 */
public interface ManageTargetService extends IService<ManageTarget> {
    /**
     * 新增或者更新管理目标数据
     *
     * @param saveDto
     * @return
     */
    boolean addOrUpdate(ManageTargetSaveDTO saveDto);

    /**
     * 通过id获取一条管理目标数据
     *
     * @param id
     * @return
     */
    ManageTargetDetailVo getInfoById(Long id);

    /**
     * 分页查询管理目标数据
     *
     * @param pageDto
     * @return
     */
    PageInfo<ManageTargetPageVo> getPageInfo(PageDTO pageDto);

    /**
     * 管理目标导出
     *
     * @param pageDto
     * @param response
     */
    void export(PageDTO pageDto, HttpServletResponse response);
}
