package com.ruoyi.jianguan.business.quality.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.jianguan.business.quality.domain.dto.FirstAcceptPageDTO;
import com.ruoyi.jianguan.business.quality.domain.dto.FirstAcceptSaveDTO;
import com.ruoyi.jianguan.business.quality.domain.entity.FirstAccept;
import com.ruoyi.jianguan.business.quality.domain.vo.FirstAcceptDetailVo;
import com.ruoyi.jianguan.business.quality.domain.vo.FirstAcceptPageVo;
import com.github.pagehelper.PageInfo;
import com.ruoyi.common.core.domain.object.ResponseBase;

import javax.servlet.http.HttpServletResponse;

/**
 * 首件认可 服务类
 *
 * @author qiaoxulin
 * @date 2022-06-01
 */
public interface FirstAcceptService extends IService<FirstAccept> {
    /**
     * 新增或者更新首件认可数据
     *
     * @param saveDto
     * @return
     */
    ResponseBase addOrUpdate(FirstAcceptSaveDTO saveDto);

    /**
     * 通过id获取一条首件认可数据
     *
     * @param id
     * @return
     */
    FirstAcceptDetailVo getInfoById(Long id);

    /**
     * 分页查询首件认可数据
     *
     * @param pageDto
     * @return
     */
    PageInfo<FirstAcceptPageVo> getPageInfo(FirstAcceptPageDTO pageDto);

    /**
     * 首件认可导出
     *
     * @param pageDto
     * @param response
     */
    void export(FirstAcceptPageDTO pageDto, HttpServletResponse response);
}
