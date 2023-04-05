package com.ruoyi.jianguan.contract.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.ruoyi.common.core.domain.object.ResponseBase;
import com.ruoyi.jianguan.contract.domain.dto.LaborContractPageDTO;
import com.ruoyi.jianguan.contract.domain.dto.LaborContractSaveDTO;
import com.ruoyi.jianguan.contract.domain.entity.LaborContract;
import com.ruoyi.jianguan.contract.domain.vo.LaborContractDetailVo;
import com.ruoyi.jianguan.contract.domain.vo.LaborContractPageVo;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 劳务分包合同 服务类
 *
 * @author qiaoxulin
 * @date 2022-05-20
 */
public interface LaborContractService extends IService<LaborContract> {
    /**
     * 新增或者更新劳务分包合同数据
     *
     * @param saveDto
     * @return
     */
    ResponseBase addOrUpdate(LaborContractSaveDTO saveDto);

    /**
     * 通过id获取一条劳务分包合同数据
     *
     * @param id
     * @return
     */
    LaborContractDetailVo getInfoById(Long id);

    /**
     * 分页查询劳务分包合同数据
     *
     * @param pageDto
     * @return
     */
    PageInfo<LaborContractPageVo> getPageInfo(LaborContractPageDTO pageDto);

    /**
     * 劳务分包合同列表（不分页）
     *
     * @return
     */
    List<LaborContract> getList();

    /**
     * 劳务分包合同导出
     *
     * @param pageDto
     * @param response
     */
    void export(LaborContractPageDTO pageDto, HttpServletResponse response);
}
