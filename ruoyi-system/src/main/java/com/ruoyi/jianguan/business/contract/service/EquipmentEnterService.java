package com.ruoyi.jianguan.business.contract.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.ruoyi.common.core.domain.object.ResponseBase;
import com.ruoyi.common.core.domain.vo.EnumsVo;
import com.ruoyi.jianguan.business.contract.domain.dto.EquipmentEnterPageDTO;
import com.ruoyi.jianguan.business.contract.domain.dto.EquipmentEnterSaveDTO;
import com.ruoyi.jianguan.business.contract.domain.entity.EquipmentEnter;
import com.ruoyi.jianguan.business.contract.domain.vo.EquipmentEnterDetailVo;
import com.ruoyi.jianguan.business.contract.domain.vo.EquipmentEnterPageVo;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 设备进场报验 服务类
 *
 * @author qiaoxulin
 * @date 2022-05-26
 */
public interface EquipmentEnterService extends IService<EquipmentEnter> {
    /**
     * 新增或者更新设备进场报验数据
     *
     * @param saveDto
     * @return
     */
    ResponseBase addOrUpdate(EquipmentEnterSaveDTO saveDto);

    /**
     * 通过id获取一条设备进场报验数据
     *
     * @param id
     * @return
     */
    EquipmentEnterDetailVo getInfoById(Long id);

    /**
     * 分页查询设备进场报验数据
     *
     * @param pageDto
     * @return
     */
    PageInfo<EquipmentEnterPageVo> getPageInfo(EquipmentEnterPageDTO pageDto);

    /**
     * 获取设备类型枚举
     *
     * @return
     */
    List<EnumsVo> getEquipmentEnum();

    /**
     * 设备进场报验导出
     *
     * @param pageDto
     * @param response
     */
    void export(EquipmentEnterPageDTO pageDto, HttpServletResponse response);
}
