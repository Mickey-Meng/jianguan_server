package com.ruoyi.jianguan.business.quality.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.common.core.domain.vo.EnumsVo;
import com.ruoyi.jianguan.business.quality.domain.dto.SupervisionSidePageDTO;
import com.ruoyi.jianguan.business.quality.domain.dto.SupervisionSideSaveDTO;
import com.ruoyi.jianguan.business.quality.domain.entity.SupervisionSide;
import com.ruoyi.jianguan.business.quality.domain.vo.SupervisionSideDetailVo;
import com.ruoyi.jianguan.business.quality.domain.vo.SupervisionSidePageVo;
import com.github.pagehelper.PageInfo;
import com.ruoyi.common.core.domain.object.ResponseBase;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 监理旁站 服务类
 *
 * @author qiaoxulin
 * @date 2022-06-10
 */
public interface SupervisionSideService extends IService<SupervisionSide> {
    /**
     * 新增或者更新监理旁站数据
     *
     * @param saveDto
     * @return
     */
    ResponseBase addOrUpdate(SupervisionSideSaveDTO saveDto);

    /**
     * 通过id获取一条监理旁站数据
     *
     * @param id
     * @return
     */
    SupervisionSideDetailVo getInfoById(Long id);

    /**
     * 分页查询监理旁站数据
     *
     * @param pageDto
     * @return
     */
    PageInfo<SupervisionSidePageVo> getPageInfo(SupervisionSidePageDTO pageDto);

    /**
     * 获取旁站监理项目枚举
     *
     * @return
     */
    List<EnumsVo> getSideProject();

    /**
     * 监理旁站导出
     *
     * @param pageDto
     * @param response
     */
    void export(SupervisionSidePageDTO pageDto, HttpServletResponse response);
}
