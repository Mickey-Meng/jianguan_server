package com.ruoyi.jianguan.business.quality.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.jianguan.business.quality.domain.dto.QualityActivityPageDTO;
import com.ruoyi.jianguan.business.quality.domain.dto.QualityActivitySaveDTO;
import com.ruoyi.jianguan.business.quality.domain.entity.QualityActivity;
import com.ruoyi.jianguan.business.quality.domain.vo.QualityActivityDetailVo;
import com.ruoyi.jianguan.business.quality.domain.vo.QualityActivityPageVo;
import com.github.pagehelper.PageInfo;
import com.ruoyi.common.core.domain.object.ResponseBase;

import javax.servlet.http.HttpServletResponse;

/**
 * 质量活动 服务类
 *
 * @author qiaoxulin
 * @date 2022-06-11
 */
public interface QualityActivityService extends IService<QualityActivity> {
    /**
     * 新增或者更新质量活动数据
     *
     * @param saveDto
     * @return
     */
    ResponseBase addOrUpdate(QualityActivitySaveDTO saveDto);

    /**
     * 通过id获取一条质量活动数据
     *
     * @param id
     * @return
     */
    QualityActivityDetailVo getInfoById(Long id);

    /**
     * 分页查询质量活动数据
     *
     * @param pageDto
     * @return
     */
    PageInfo<QualityActivityPageVo> getPageInfo(QualityActivityPageDTO pageDto);

    /**
     * 质量活动导出
     *
     * @param pageDto
     * @param response
     */
    void export(QualityActivityPageDTO pageDto, HttpServletResponse response);
}
