package com.ruoyi.jianguan.quality.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.jianguan.quality.domain.dto.QualityReportPageDTO;
import com.ruoyi.jianguan.quality.domain.dto.QualityReportSaveDTO;
import com.ruoyi.jianguan.quality.domain.entity.QualityReport;
import com.ruoyi.jianguan.quality.domain.vo.QualityReportDetailVo;
import com.ruoyi.jianguan.quality.domain.vo.QualityReportPageVo;
import com.github.pagehelper.PageInfo;
import com.ruoyi.common.core.domain.object.ResponseBase;

import javax.servlet.http.HttpServletResponse;

/**
 * 质量简报 服务类
 *
 * @author qiaoxulin
 * @date 2022-06-02
 */
public interface QualityReportService extends IService<QualityReport> {
    /**
     * 新增或者更新质量简报数据
     *
     * @param saveDto
     * @return
     */
    ResponseBase addOrUpdate(QualityReportSaveDTO saveDto);

    /**
     * 通过id获取一条质量简报数据
     *
     * @param id
     * @return
     */
    QualityReportDetailVo getInfoById(Long id);

    /**
     * 分页查询质量简报数据
     *
     * @param pageDto
     * @return
     */
    PageInfo<QualityReportPageVo> getPageInfo(QualityReportPageDTO pageDto);

    /**
     * 质量简报导出
     *
     * @param pageDto
     * @param response
     */
    void export(QualityReportPageDTO pageDto, HttpServletResponse response);
}
