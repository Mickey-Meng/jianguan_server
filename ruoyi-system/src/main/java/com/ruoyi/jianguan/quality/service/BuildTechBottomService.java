package com.ruoyi.jianguan.quality.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.jianguan.quality.domain.dto.BuildTechBottomPageDTO;
import com.ruoyi.jianguan.quality.domain.dto.BuildTechBottomSaveDTO;
import com.ruoyi.jianguan.quality.domain.entity.BuildTechBottom;
import com.ruoyi.jianguan.quality.domain.vo.BuildTechBottomDetailVo;
import com.ruoyi.jianguan.quality.domain.vo.BuildTechBottomPageVo;
import com.github.pagehelper.PageInfo;
import com.ruoyi.common.core.domain.object.ResponseBase;

import javax.servlet.http.HttpServletResponse;

/**
 * 施工技术交底 服务类
 *
 * @author qiaoxulin
 * @date 2022-05-26
 */
public interface BuildTechBottomService extends IService<BuildTechBottom> {
    /**
     * 新增或者更新施工技术交底数据
     *
     * @param saveDto
     * @return
     */
    ResponseBase addOrUpdate(BuildTechBottomSaveDTO saveDto);

    /**
     * 通过id获取一条施工技术交底数据s
     *
     * @param id
     * @return
     */
    BuildTechBottomDetailVo getInfoById(Long id);

    /**
     * 分页查询施工技术交底数据
     *
     * @param pageDto
     * @return
     */
    PageInfo<BuildTechBottomPageVo> getPageInfo(BuildTechBottomPageDTO pageDto);

    /**
     * 施工技术交底导出
     *
     * @param pageDto
     * @param response
     */
    void export(BuildTechBottomPageDTO pageDto, HttpServletResponse response);
}
