package com.ruoyi.ql.service;

import com.ruoyi.ql.domain.QlWhStorage;
import com.ruoyi.ql.domain.vo.QlWhStorageVo;
import com.ruoyi.ql.domain.bo.QlWhStorageBo;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 库位(储位)设置Service接口
 *
 * @author ruoyi
 * @date 2022-12-11
 */
public interface IQlWhStorageService {

    /**
     * 查询库位(储位)设置
     */
    QlWhStorageVo queryById(Long id);

    /**
     * 查询库位(储位)设置列表
     */
    TableDataInfo<QlWhStorageVo> queryPageList(QlWhStorageBo bo, PageQuery pageQuery);

    /**
     * 查询库位(储位)设置列表
     */
    List<QlWhStorageVo> queryList(QlWhStorageBo bo);

    /**
     * 新增库位(储位)设置
     */
    Boolean insertByBo(QlWhStorageBo bo);

    /**
     * 修改库位(储位)设置
     */
    Boolean updateByBo(QlWhStorageBo bo);

    /**
     * 校验并批量删除库位(储位)设置信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
