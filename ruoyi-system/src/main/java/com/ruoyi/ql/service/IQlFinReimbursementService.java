package com.ruoyi.ql.service;

import com.ruoyi.ql.domain.QlFinReimbursement;
import com.ruoyi.ql.domain.vo.QlFinReimbursementVo;
import com.ruoyi.ql.domain.bo.QlFinReimbursementBo;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 费用报销Service接口
 *
 * @author ruoyi
 * @date 2022-12-11
 */
public interface IQlFinReimbursementService {

    /**
     * 查询费用报销
     */
    QlFinReimbursementVo queryById(Long id);

    /**
     * 查询费用报销列表
     */
    TableDataInfo<QlFinReimbursementVo> queryPageList(QlFinReimbursementBo bo, PageQuery pageQuery);

    /**
     * 查询费用报销列表
     */
    List<QlFinReimbursementVo> queryList(QlFinReimbursementBo bo);

    /**
     * 新增费用报销
     */
    Boolean insertByBo(QlFinReimbursementBo bo);

    /**
     * 修改费用报销
     */
    Boolean updateByBo(QlFinReimbursementBo bo);

    /**
     * 校验并批量删除费用报销信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
