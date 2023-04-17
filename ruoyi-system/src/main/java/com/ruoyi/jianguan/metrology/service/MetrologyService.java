package com.ruoyi.jianguan.metrology.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.ruoyi.common.core.domain.object.ResponseBase;
import com.ruoyi.jianguan.metrology.domain.dto.MetrologyPageDTO;
import com.ruoyi.jianguan.metrology.domain.dto.MetrologySaveDTO;
import com.ruoyi.jianguan.metrology.domain.entity.Metrology;
import com.ruoyi.jianguan.metrology.domain.vo.MetrologyDetailVo;
import com.ruoyi.jianguan.metrology.domain.vo.MetrologyPageVo;

import javax.servlet.http.HttpServletResponse;

/**
 * 计量 服务类
 *
 * @author G.X.L
 * @date 2023-03-29
 */
public interface MetrologyService extends IService<Metrology> {

    /**
     * 新增或者更新施工专业分包合同表数据
     *
     * @param saveDto
     * @return
     */
    ResponseBase addOrUpdate(MetrologySaveDTO saveDto);

    /**
     * 通过id获取一条施工专业分包合同表数据
     *
     * @param id
     * @return
     */
    MetrologyDetailVo getInfoById(Long id);

    /**
     * 分页查询施工专业分包合同表数据
     *
     * @param pageDto
     * @return
     */
    PageInfo<MetrologyPageVo> getPageInfo(MetrologyPageDTO pageDto);


    /**
     * 施工专业分包合同导出
     *
     * @param pageDto
     * @param response
     */
    void export(MetrologyPageDTO pageDto, HttpServletResponse response);
}
