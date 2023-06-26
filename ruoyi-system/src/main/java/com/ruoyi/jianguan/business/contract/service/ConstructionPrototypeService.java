package com.ruoyi.jianguan.business.contract.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.ruoyi.common.core.domain.object.ResponseBase;
import com.ruoyi.jianguan.business.contract.domain.dto.ConstructionPrototypePageDTO;
import com.ruoyi.jianguan.business.contract.domain.dto.ConstructionPrototypeSaveDTO;
import com.ruoyi.jianguan.business.contract.domain.entity.ConstructionPrototype;
import com.ruoyi.jianguan.business.contract.domain.vo.ConstructionPrototypePageVo;
import com.ruoyi.jianguan.business.contract.domain.vo.ConstructionPrototypeDetailVo;


public interface ConstructionPrototypeService extends IService<ConstructionPrototype> {



    ResponseBase addOrUpdate(ConstructionPrototypeSaveDTO constructionPrototypeSaveDTO);


    ConstructionPrototypeDetailVo getInfoById(Long id);


    PageInfo<ConstructionPrototypePageVo> getPageInfo(ConstructionPrototypePageDTO pageDto);



}