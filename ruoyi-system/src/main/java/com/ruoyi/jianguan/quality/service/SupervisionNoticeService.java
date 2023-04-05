package com.ruoyi.jianguan.quality.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.jianguan.quality.domain.dto.SupervisionNoticePageDTO;
import com.ruoyi.jianguan.quality.domain.dto.SupervisionNoticeSaveDTO;
import com.ruoyi.jianguan.quality.domain.entity.SupervisionNotice;
import com.ruoyi.jianguan.quality.domain.vo.SupervisionNoticePageVo;
import com.github.pagehelper.PageInfo;
import com.ruoyi.common.core.domain.object.ResponseBase;

import javax.servlet.http.HttpServletResponse;

/**
 * 监理通知 服务类
 *
 * @author qiaoxulin
 * @date 2022-06-11
 */
public interface SupervisionNoticeService extends IService<SupervisionNotice> {
    /**
     * 新增或者更新监理通知数据
     *
     * @param saveDto
     * @return
     */
    ResponseBase addOrUpdate(SupervisionNoticeSaveDTO saveDto);

    /**
     * 分页查询监理通知数据
     *
     * @param pageDto
     * @return
     */
    PageInfo<SupervisionNoticePageVo> getPageInfo(SupervisionNoticePageDTO pageDto);

    /**
     * 监理通知导出
     *
     * @param pageDto
     * @param response
     */
    void export(SupervisionNoticePageDTO pageDto, HttpServletResponse response);
}
