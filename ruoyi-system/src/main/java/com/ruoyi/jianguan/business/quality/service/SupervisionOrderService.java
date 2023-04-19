package com.ruoyi.jianguan.business.quality.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.jianguan.business.quality.domain.dto.SupervisionOrderPageDTO;
import com.ruoyi.jianguan.business.quality.domain.dto.SupervisionOrderSaveDTO;
import com.ruoyi.jianguan.business.quality.domain.entity.SupervisionOrder;
import com.ruoyi.jianguan.business.quality.domain.vo.SupervisionOrderDetailVo;
import com.ruoyi.jianguan.business.quality.domain.vo.SupervisionOrderPageVo;
import com.github.pagehelper.PageInfo;
import com.ruoyi.common.core.domain.object.ResponseBase;

import javax.servlet.http.HttpServletResponse;

/**
 * 监理指令 服务类
 *
 * @author qiaoxulin
 * @date 2022-06-14
 */
public interface SupervisionOrderService extends IService<SupervisionOrder> {
    /**
     * 新增或者更新监理指令数据
     *
     * @param saveDto
     * @return
     */
    ResponseBase addOrUpdate(SupervisionOrderSaveDTO saveDto);

    /**
     * 通过id获取一条监理指令数据
     *
     * @param id
     * @return
     */
    SupervisionOrderDetailVo getInfoById(Long id);

    /**
     * 分页查询监理指令数据
     *
     * @param pageDto
     * @return
     */
    PageInfo<SupervisionOrderPageVo> getPageInfo(SupervisionOrderPageDTO pageDto);

    /**
     * 监理指令导出
     *
     * @param pageDto
     * @param response
     */
    void export(SupervisionOrderPageDTO pageDto, HttpServletResponse response);
}
