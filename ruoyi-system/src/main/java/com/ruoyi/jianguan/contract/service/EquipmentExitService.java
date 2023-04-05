package com.ruoyi.jianguan.contract.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.ruoyi.common.core.domain.object.ResponseBase;
import com.ruoyi.jianguan.contract.domain.dto.EquipmentExit;
import com.ruoyi.jianguan.contract.domain.dto.EquipmentExitPageDTO;
import com.ruoyi.jianguan.contract.domain.dto.EquipmentExitSaveDTO;
import com.ruoyi.jianguan.contract.domain.vo.EquipmentExitDetailVo;
import com.ruoyi.jianguan.contract.domain.vo.EquipmentExitPageVo;

import javax.servlet.http.HttpServletResponse;

/**
 * 退场设备报验单 服务类
 *
 * @author qiaoxulin
 * @date 2022-05-27
 */
public interface EquipmentExitService extends IService<EquipmentExit> {
    /**
     * 新增或者更新退场设备报验单数据
     *
     * @param saveDto
     * @return
     */
    ResponseBase addOrUpdate(EquipmentExitSaveDTO saveDto);

    /**
     * 通过id获取一条退场设备报验单数据
     *
     * @param id
     * @return
     */
    EquipmentExitDetailVo getInfoById(Long id);

    /**
     * 分页查询退场设备报验单数据
     *
     * @param pageDto
     * @return
     */
    PageInfo<EquipmentExitPageVo> getPageInfo(EquipmentExitPageDTO pageDto);

    /**
     * 设备退场报验导出
     *
     * @param pageDto
     * @param response
     */
    void export(EquipmentExitPageDTO pageDto, HttpServletResponse response);
}
