package com.ruoyi.czjg.contract.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.ruoyi.common.core.domain.object.ResponseBase;
import com.ruoyi.czjg.contract.domain.dto.ComeGoMoneyPageDTO;
import com.ruoyi.czjg.contract.domain.dto.ComeGoMoneySaveDTO;
import com.ruoyi.czjg.contract.domain.entity.ComeGoMoney;
import com.ruoyi.czjg.contract.domain.vo.ComeGoMoneyDetailVo;
import com.ruoyi.project.contract.domain.vo.ComeGoMoneyPageVo;

import javax.servlet.http.HttpServletResponse;

/**
 * 往来款 服务类
 *
 * @author qiaoxulin
 * @date 2022-05-27
 */
public interface ComeGoMoneyService extends IService<ComeGoMoney> {
    /**
     * 新增或者更新往来款数据
     *
     * @param saveDto
     * @return
     */
    ResponseBase addOrUpdate(ComeGoMoneySaveDTO saveDto);

    /**
     * 通过id获取一条往来款数据
     *
     * @param id
     * @return
     */
    ComeGoMoneyDetailVo getInfoById(Long id);

    /**
     * 分页查询往来款数据
     *
     * @param pageDto
     * @return
     */
    PageInfo<ComeGoMoneyPageVo> getPageInfo(ComeGoMoneyPageDTO pageDto);

    /**
     * 往来款导出
     *
     * @param pageDto
     * @param response
     */
    void export(ComeGoMoneyPageDTO pageDto, HttpServletResponse response);
}
