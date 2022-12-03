package com.ruoyi.project.other.service;


import com.ruoyi.common.core.domain.PageQuery;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.project.other.domain.bo.MeaOtherPaymentBo;
import com.ruoyi.project.other.domain.vo.MeaOtherPaymentVo;

import java.util.Collection;
import java.util.List;

/**
 * 其他款项Service接口
 *
 * @author ruoyi
 * @date 2022-12-03
 */
public interface IMeaOtherPaymentService {

    /**
     * 查询其他款项
     */
    MeaOtherPaymentVo queryById(String id);

    /**
     * 查询其他款项列表
     */
    TableDataInfo<MeaOtherPaymentVo> queryPageList(MeaOtherPaymentBo bo, PageQuery pageQuery);

    /**
     * 查询其他款项列表
     */
    List<MeaOtherPaymentVo> queryList(MeaOtherPaymentBo bo);

    /**
     * 新增其他款项
     */
    Boolean insertByBo(MeaOtherPaymentBo bo);

    /**
     * 修改其他款项
     */
    Boolean updateByBo(MeaOtherPaymentBo bo);

    /**
     * 校验并批量删除其他款项信息
     */
    Boolean deleteWithValidByIds(Collection<String> ids, Boolean isValid);
}
