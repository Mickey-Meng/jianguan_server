package com.ruoyi.jianguan.business.constructionDesign.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.ruoyi.common.core.domain.object.ResponseBase;
import com.ruoyi.jianguan.business.constructionDesign.domain.dto.ConstructionDesignPageDTO;
import com.ruoyi.jianguan.business.constructionDesign.domain.dto.PlanConstructionDesignSaveDTO;
import com.ruoyi.jianguan.business.constructionDesign.domain.dto.ProgressConstructionDesignSaveDTO;
import com.ruoyi.jianguan.business.constructionDesign.domain.entity.ConstructionDesign;
import com.ruoyi.jianguan.business.constructionDesign.domain.vo.PlanConstructionDesignVo;
import com.ruoyi.jianguan.business.constructionDesign.domain.vo.ProgressConstructionDesignVo;

import java.util.List;


/**
 * 计划管理-施工图管理Service接口
 *
 * @author mickey
 * @date 2023-06-19
 */
public interface ConstructionDesignService extends IService<ConstructionDesign> {


    /**
     * 新增或者更新计划管理-施工图管理表数据
     *
     * @param saveDto
     * @return
     */
    ResponseBase addOrUpdate(PlanConstructionDesignSaveDTO saveDto);


    /**
     * 查询[计划管理-施工图管理]分页数据
     * @param pageDto
     * @return
     */
    public PageInfo<PlanConstructionDesignVo> getPlanPageInfo(ConstructionDesignPageDTO pageDto);

    /**
     * 查询[进度管理-施工图管理]分页数据
     * @param pageDto
     * @return
     */
    public PageInfo<ProgressConstructionDesignVo> getProgressPageInfo(ConstructionDesignPageDTO pageDto);

    /**
     * 通过id获取一条计划管理-施工图管理表数据
     *
     * @param id
     * @return
     */
    PlanConstructionDesignVo getPlanInfoById(Long id);

    /**
     * 通过id获取一条进度管理-施工图管理表数据
     *
     * @param id
     * @return
     */
    ProgressConstructionDesignVo getProgressInfoById(Long id);

    ResponseBase updateUploadFile(ProgressConstructionDesignSaveDTO saveDto);

    /**
     * 查询到期的施工图设计数据
     * @param name
     * @return
     */
    List<ConstructionDesign> getExpiryRemindersList(String name);
}