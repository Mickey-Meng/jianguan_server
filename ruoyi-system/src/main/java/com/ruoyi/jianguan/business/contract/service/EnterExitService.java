package com.ruoyi.jianguan.business.contract.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.ruoyi.common.core.domain.object.ResponseBase;
import com.ruoyi.jianguan.business.contract.domain.dto.EnterExitPageDTO;
import com.ruoyi.jianguan.business.contract.domain.dto.EnterExitSaveDTO;
import com.ruoyi.jianguan.business.contract.domain.entity.EnterExit;
import com.ruoyi.jianguan.business.contract.domain.vo.EnterExitDetailVo;
import com.ruoyi.jianguan.business.contract.domain.vo.EnterExitPageVo;

import javax.servlet.http.HttpServletResponse;

/**
 * 进退场 服务类
 *
 * @author qiaoxulin
 * @date 2022-05-27
 */
public interface EnterExitService extends IService<EnterExit> {
    /**
     * 新增或者更新进退场数据
     *
     * @param saveDto
     * @return
     */
    ResponseBase addOrUpdate(EnterExitSaveDTO saveDto);

    /**
     * 通过id获取一条进退场数据
     *
     * @param id
     * @return
     */
    EnterExitDetailVo getInfoById(Long id);

    /**
     * 分页查询进退场数据
     *
     * @param pageDto
     * @return
     */
    PageInfo<EnterExitPageVo> getPageInfo(EnterExitPageDTO pageDto);

    /**
     * 通过id删除一条进退场数据
     *
     * @param id
     * @return
     */
    boolean delById(Long id);

    /**
     * 进退场导出
     *
     * @param pageDto
     * @param response
     */
    void export(EnterExitPageDTO pageDto, HttpServletResponse response);
}
