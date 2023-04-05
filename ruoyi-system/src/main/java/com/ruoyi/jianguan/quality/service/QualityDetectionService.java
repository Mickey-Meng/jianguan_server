package com.ruoyi.jianguan.quality.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.common.core.domain.vo.EnumsVo;
import com.ruoyi.jianguan.quality.domain.dto.QualityDetectionPageDTO;
import com.ruoyi.jianguan.quality.domain.dto.QualityDetectionSaveDTO;
import com.ruoyi.jianguan.quality.domain.entity.QualityDetection;
import com.ruoyi.jianguan.quality.domain.vo.QualityDetectionDetailVo;
import com.ruoyi.jianguan.quality.domain.vo.QualityDetectionPageVo;
import com.github.pagehelper.PageInfo;
import com.ruoyi.common.core.domain.object.ResponseBase;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 质量检测 服务类
 *
 * @author qiaoxulin
 * @date 2022-05-11
 */
public interface QualityDetectionService extends IService<QualityDetection> {

    /**
     * 新增或者更新质量检测数据
     *
     * @param qualityDetectionDto
     * @return
     */
    ResponseBase addOrUpdate(QualityDetectionSaveDTO qualityDetectionDto);

    /**
     * 通过id获取一条质量检测数据
     *
     * @param id
     * @return
     */
    QualityDetectionDetailVo getInfoById(Long id);

    /**
     * 质量检测分页查询
     *
     * @param pageDto
     * @return
     */
    PageInfo<QualityDetectionPageVo> getPageInfo(QualityDetectionPageDTO pageDto);

    /**
     * 获取材料枚举
     *
     * @return
     */
    List<EnumsVo> getMaterialEnum();

    /**
     * 质量检测导出
     *
     * @param pageDto
     */
    void export(QualityDetectionPageDTO pageDto, HttpServletResponse response);
}
