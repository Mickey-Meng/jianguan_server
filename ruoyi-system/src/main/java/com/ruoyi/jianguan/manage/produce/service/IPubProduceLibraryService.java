package com.ruoyi.system.service;

import cn.hutool.core.lang.tree.Tree;
import com.ruoyi.system.domain.vo.PubProduceLibraryVo;
import com.ruoyi.system.domain.bo.PubProduceLibraryBo;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 工序库Service接口
 *
 * @author ruoyi
 * @date 2023-06-03
 */
public interface IPubProduceLibraryService {

    /**
     * 查询工序库
     */
    PubProduceLibraryVo queryById(Integer id);

    /**
     * 查询工序库列表
     */
    TableDataInfo<PubProduceLibraryVo> queryPageList(PubProduceLibraryBo bo, PageQuery pageQuery);

    /**
     * 查询工序库列表
     */
    List<PubProduceLibraryVo> queryList(PubProduceLibraryBo bo);

    /**
     * 新增工序库
     */
    Boolean insertByBo(PubProduceLibraryBo bo);

    /**
     * 修改工序库
     */
    Boolean updateByBo(PubProduceLibraryBo bo);

    /**
     * 校验并批量删除工序库信息
     */
    Boolean deleteWithValidByIds(Collection<Integer> ids, Boolean isValid);

    /**
     * 校验主键是否唯一工序库信息
     */
    Boolean checkUniqueByPrimaryKey(Integer id);

    List<Tree<Long>> getProduceLibraryTree(PubProduceLibraryBo bo);
}
