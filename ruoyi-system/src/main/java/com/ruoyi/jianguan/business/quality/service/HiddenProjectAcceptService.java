package com.ruoyi.jianguan.business.quality.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.jianguan.business.quality.domain.dto.HiddenProjectAcceptPageDTO;
import com.ruoyi.jianguan.business.quality.domain.dto.HiddenProjectAcceptSaveDTO;
import com.ruoyi.jianguan.business.quality.domain.entity.HiddenProjectAccept;
import com.ruoyi.jianguan.business.quality.domain.vo.HiddenProjectAccepDetailtVo;
import com.ruoyi.jianguan.business.quality.domain.vo.HiddenProjectAcceptPageVo;
import com.github.pagehelper.PageInfo;
import com.ruoyi.common.core.domain.object.ResponseBase;

import javax.servlet.http.HttpServletResponse;

/**
 * 隐蔽工程验收记录 服务类
 *
 * @author qiaoxulin
 * @date 2022-05-12
 */
public interface HiddenProjectAcceptService extends IService<HiddenProjectAccept> {

    /**
     * 新增或者更新隐蔽工程验收记录数据
     *
     * @param saveDto
     * @return
     */
    ResponseBase addOrUpdate(HiddenProjectAcceptSaveDTO saveDto);

    /**
     * 通过id获取一条隐蔽工程验收记录数据
     *
     * @param id
     * @return
     */
    HiddenProjectAccepDetailtVo getInfoById(Long id);

    /**
     * 分页查询隐蔽工程验收记录数据
     *
     * @param pageDto
     * @return
     */
    PageInfo<HiddenProjectAcceptPageVo> getPageInfo(HiddenProjectAcceptPageDTO pageDto);

    /**
     * 隐蔽工程验收记录导出
     *
     * @param pageDto
     * @param response
     */
    void export(HiddenProjectAcceptPageDTO pageDto, HttpServletResponse response);
}
