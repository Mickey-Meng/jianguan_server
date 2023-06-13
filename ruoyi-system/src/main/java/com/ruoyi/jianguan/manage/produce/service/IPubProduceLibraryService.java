package com.ruoyi.jianguan.manage.produce.service;

import cn.hutool.core.lang.tree.Tree;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;
import com.ruoyi.jianguan.manage.produce.domain.bo.PubProduceLibraryBo;
import com.ruoyi.jianguan.manage.produce.domain.vo.PubProduceLibraryVo;

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
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

    /**
     * 校验主键是否唯一工序库信息
     */
    Boolean checkUniqueByPrimaryKey(Integer id);

    List<Tree<Long>> getProduceLibraryTree(PubProduceLibraryBo bo);

    /**
     * 复制工序库
     * @param bo
     * @return
     */
    Boolean copyProduceLibrary(PubProduceLibraryBo bo);
}
