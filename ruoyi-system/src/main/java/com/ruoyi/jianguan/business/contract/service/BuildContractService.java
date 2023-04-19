package com.ruoyi.jianguan.business.contract.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.ruoyi.common.core.domain.object.ResponseBase;
import com.ruoyi.common.core.domain.vo.EnumsVo;
import com.ruoyi.jianguan.business.contract.domain.dto.BuildContractPageDTO;
import com.ruoyi.jianguan.business.contract.domain.dto.BuildContractSaveDTO;
import com.ruoyi.jianguan.business.contract.domain.entity.BuildContract;
import com.ruoyi.jianguan.business.contract.domain.vo.BuildContractDetailVo;
import com.ruoyi.jianguan.business.contract.domain.vo.BuildContractPageVo;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 施工专业分包合同表 服务类
 *
 * @author qiaoxulin
 * @date 2022-05-18
 */
public interface BuildContractService extends IService<BuildContract> {

    /**
     * 新增或者更新施工专业分包合同表数据
     *
     * @param saveDto
     * @return
     */
    ResponseBase addOrUpdate(BuildContractSaveDTO saveDto);

    /**
     * 通过id获取一条施工专业分包合同表数据
     *
     * @param id
     * @return
     */
    BuildContractDetailVo getInfoById(Long id);

    /**
     * 分页查询施工专业分包合同表数据
     *
     * @param pageDto
     * @return
     */
    PageInfo<BuildContractPageVo> getPageInfo(BuildContractPageDTO pageDto);

    /**
     * 获取拟分包工程部位枚举
     *
     * @return
     */
    List<EnumsVo> getBuildContractPartEnum();

    /**
     * 施工专业分包合同导出
     *
     * @param pageDto
     * @param response
     */
    void export(BuildContractPageDTO pageDto, HttpServletResponse response);
}
