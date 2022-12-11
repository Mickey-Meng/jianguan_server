package com.ruoyi.ql.service;

import com.ruoyi.ql.domain.QlFinEmp;
import com.ruoyi.ql.domain.vo.QlFinEmpVo;
import com.ruoyi.ql.domain.bo.QlFinEmpBo;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 员工信息管理Service接口
 *
 * @author ruoyi
 * @date 2022-12-11
 */
public interface IQlFinEmpService {

    /**
     * 查询员工信息管理
     */
    QlFinEmpVo queryById(Long id);

    /**
     * 查询员工信息管理列表
     */
    TableDataInfo<QlFinEmpVo> queryPageList(QlFinEmpBo bo, PageQuery pageQuery);

    /**
     * 查询员工信息管理列表
     */
    List<QlFinEmpVo> queryList(QlFinEmpBo bo);

    /**
     * 新增员工信息管理
     */
    Boolean insertByBo(QlFinEmpBo bo);

    /**
     * 修改员工信息管理
     */
    Boolean updateByBo(QlFinEmpBo bo);

    /**
     * 校验并批量删除员工信息管理信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
