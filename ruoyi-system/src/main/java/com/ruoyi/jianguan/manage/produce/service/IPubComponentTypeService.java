package com.ruoyi.jianguan.manage.produce.service;

import com.ruoyi.common.core.domain.PageQuery;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.jianguan.manage.produce.domain.bo.PubComponentTypeBo;
import com.ruoyi.jianguan.manage.produce.domain.vo.PubComponentTypeVo;

import java.util.Collection;
import java.util.List;

/**
 * 构建类型Service接口
 *
 * @author ruoyi
 * @date 2023-06-03
 */
public interface IPubComponentTypeService {

    /**
     * 查询构建类型
     */
    PubComponentTypeVo queryById(Integer id);

    /**
     * 查询构建类型列表
     */
    TableDataInfo<PubComponentTypeVo> queryPageList(PubComponentTypeBo bo, PageQuery pageQuery);

    /**
     * 查询构建类型列表
     */
    List<PubComponentTypeVo> queryList(PubComponentTypeBo bo);

    /**
     * 新增构建类型
     */
    Boolean insertByBo(PubComponentTypeBo bo);

    /**
     * 修改构建类型
     */
    Boolean updateByBo(PubComponentTypeBo bo);

    /**
     * 校验并批量删除构建类型信息
     */
    Boolean deleteWithValidByIds(Collection<Integer> ids, Boolean isValid);

    /**
     * 校验主键是否唯一构建类型信息
     */
    Boolean checkUniqueByPrimaryKey(Integer id);

}
