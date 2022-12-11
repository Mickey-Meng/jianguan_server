package com.ruoyi.ql.service;

import com.ruoyi.ql.domain.QlFinWages;
import com.ruoyi.ql.domain.vo.QlFinWagesVo;
import com.ruoyi.ql.domain.bo.QlFinWagesBo;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 工资管理Service接口
 *
 * @author ruoyi
 * @date 2022-12-11
 */
public interface IQlFinWagesService {

    /**
     * 查询工资管理
     */
    QlFinWagesVo queryById(Long id);

    /**
     * 查询工资管理列表
     */
    TableDataInfo<QlFinWagesVo> queryPageList(QlFinWagesBo bo, PageQuery pageQuery);

    /**
     * 查询工资管理列表
     */
    List<QlFinWagesVo> queryList(QlFinWagesBo bo);

    /**
     * 新增工资管理
     */
    Boolean insertByBo(QlFinWagesBo bo);

    /**
     * 修改工资管理
     */
    Boolean updateByBo(QlFinWagesBo bo);

    /**
     * 校验并批量删除工资管理信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
