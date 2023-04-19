package com.ruoyi.jianguan.business.quality.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.jianguan.business.quality.domain.dto.ProjectOpenPageDTO;
import com.ruoyi.jianguan.business.quality.domain.dto.ProjectOpenSaveDTO;
import com.ruoyi.jianguan.business.quality.domain.entity.ProjectOpen;
import com.ruoyi.jianguan.business.quality.domain.vo.ProjectOpenDetailVo;
import com.ruoyi.jianguan.business.quality.domain.vo.ProjectOpenPageVo;
import com.github.pagehelper.PageInfo;
import com.ruoyi.common.core.domain.object.ResponseBase;

import javax.servlet.http.HttpServletResponse;

/**
 * 项目开工申请 服务类
 *
 * @author qiaoxulin
 * @date 2022-06-01
 */
public interface ProjectOpenService extends IService<ProjectOpen> {

    /**
     * 新增或者更新项目开工申请数据
     *
     * @param saveDto
     * @return
     */
    ResponseBase addOrUpdate(ProjectOpenSaveDTO saveDto);

    /**
     * 通过id获取一条项目开工申请数据
     *
     * @param id
     * @return
     */
    ProjectOpenDetailVo getInfoById(Long id);

    /**
     * 分页查询项目开工申请数据
     *
     * @param pageDto
     * @return
     */
    PageInfo<ProjectOpenPageVo> getPageInfo(ProjectOpenPageDTO pageDto);

    /**
     * 项目开工申请导出
     *
     * @param pageDto
     * @param response
     */
    void export(ProjectOpenPageDTO pageDto, HttpServletResponse response);
}
