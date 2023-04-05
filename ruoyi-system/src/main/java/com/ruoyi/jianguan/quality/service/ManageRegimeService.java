package com.ruoyi.jianguan.quality.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.jianguan.quality.domain.dto.ManageRegimeSaveDTO;
import com.ruoyi.common.core.domain.dto.PageDTO;
import com.ruoyi.jianguan.quality.domain.entity.ManageRegime;
import com.ruoyi.jianguan.quality.domain.vo.ManageRegimeDetailVo;
import com.ruoyi.jianguan.quality.domain.vo.ManageRegimePageVo;
import com.github.pagehelper.PageInfo;

import javax.servlet.http.HttpServletResponse;

/**
 * 管理制度 服务类
 *
 * @author qiaoxulin
 * @date 2022-05-13
 */
public interface ManageRegimeService extends IService<ManageRegime> {

    /**
     * 新增或者更新管理制度数据
     *
     * @param saveDto
     * @return
     */
    boolean addOrUpdate(ManageRegimeSaveDTO saveDto);

    /**
     * 通过id获取一条管理制度数据
     *
     * @param id
     * @return
     */
    ManageRegimeDetailVo getInfoById(Long id);

    /**
     * 分页查询管理制度数据
     *
     * @param pageDto
     * @return
     */
    PageInfo<ManageRegimePageVo> getPageInfo(PageDTO pageDto);

    /**
     * 管理制度导出
     *
     * @param pageDto
     * @param response
     */
    void export(PageDTO pageDto, HttpServletResponse response);
}
