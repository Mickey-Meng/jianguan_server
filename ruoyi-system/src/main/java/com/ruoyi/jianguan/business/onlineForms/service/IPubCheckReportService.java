package com.ruoyi.jianguan.business.onlineForms.service;

import com.ruoyi.jianguan.business.onlineForms.domain.PubCheckReport;
import com.ruoyi.jianguan.business.onlineForms.domain.vo.PubCheckReportVo;
import com.ruoyi.jianguan.business.onlineForms.domain.bo.PubCheckReportBo;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

/**
 * 评定填报关联信息Service接口
 *
 * @author mickey
 * @date 2024-01-16
 */
public interface IPubCheckReportService {

    /**
     * 查询评定填报关联信息
     */
    PubCheckReportVo queryById(Long id);

    /**
     * 查询评定填报关联信息列表
     */
    TableDataInfo<PubCheckReportVo> queryPageList(PubCheckReportBo bo, PageQuery pageQuery);

    /**
     * 查询评定填报关联信息列表
     */
    List<PubCheckReportVo> queryList(PubCheckReportBo bo);

    /**
     * 新增评定填报关联信息
     */
    Boolean insertByBo(PubCheckReportBo bo);

    /**
     * 修改评定填报关联信息
     */
    Boolean updateByBo(PubCheckReportBo bo);

    /**
     * 校验并批量删除评定填报关联信息信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

    /**
     * 校验主键是否唯一评定填报关联信息信息
     */
    Boolean checkUniqueByPrimaryKey(Long id);

    PubCheckReportVo loadOnlineCheckReport(Integer componentId) throws IOException;
}
