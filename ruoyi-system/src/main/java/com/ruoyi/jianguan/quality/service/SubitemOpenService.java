package com.ruoyi.jianguan.quality.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.jianguan.quality.domain.dto.SubitemOpenPageDTO;
import com.ruoyi.jianguan.quality.domain.dto.SubitemOpenSaveDTO;
import com.ruoyi.jianguan.quality.domain.entity.SubitemOpen;
import com.ruoyi.jianguan.quality.domain.vo.SubitemOpenDetailVo;
import com.ruoyi.jianguan.quality.domain.vo.SubitemOpenPageVo;
import com.github.pagehelper.PageInfo;
import com.ruoyi.common.core.domain.object.ResponseBase;

import javax.servlet.http.HttpServletResponse;

/**
 * 分项开工申请 服务类
 *
 * @author qiaoxulin
 * @date 2022-06-01
 */
public interface SubitemOpenService extends IService<SubitemOpen> {

    /**
     * 新增或者更新分项开工申请数据
     *
     * @param saveDto
     * @return
     */
    ResponseBase addOrUpdate(SubitemOpenSaveDTO saveDto);

    /**
     * 通过id获取一条分项开工申请数据
     *
     * @param id
     * @return
     */
    SubitemOpenDetailVo getInfoById(Long id);

    /**
     * 分页查询分项开工申请数据
     *
     * @param pageDto
     * @return
     */
    PageInfo<SubitemOpenPageVo> getPageInfo(SubitemOpenPageDTO pageDto);

    /**
     * 分项开工申请导出
     *
     * @param pageDto
     * @param response
     */
    void export(SubitemOpenPageDTO pageDto, HttpServletResponse response);
}
