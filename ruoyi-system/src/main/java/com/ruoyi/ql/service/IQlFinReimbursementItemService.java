package com.ruoyi.ql.service;

import com.ruoyi.ql.domain.QlFinReimbursementItem;
import com.ruoyi.ql.domain.vo.QlFinReimbursementItemVo;
import com.ruoyi.ql.domain.bo.QlFinReimbursementItemBo;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 费用报销明细Service接口
 *
 * @author ruoyi
 * @date 2022-12-11
 */
public interface IQlFinReimbursementItemService {

    /**
     * 查询费用报销明细
     */
    QlFinReimbursementItemVo queryById(Long id);

    /**
     * 查询费用报销明细列表
     */
    TableDataInfo<QlFinReimbursementItemVo> queryPageList(QlFinReimbursementItemBo bo, PageQuery pageQuery);

    /**
     * 查询费用报销明细列表
     */
    List<QlFinReimbursementItemVo> queryList(QlFinReimbursementItemBo bo);

    /**
     * 新增费用报销明细
     */
    Boolean insertByBo(QlFinReimbursementItemBo bo);

    /**
     * 修改费用报销明细
     */
    Boolean updateByBo(QlFinReimbursementItemBo bo);

    /**
     * 校验并批量删除费用报销明细信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
