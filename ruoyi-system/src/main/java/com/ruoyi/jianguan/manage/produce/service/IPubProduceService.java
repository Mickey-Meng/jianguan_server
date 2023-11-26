package com.ruoyi.jianguan.manage.produce.service;

import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;
import com.ruoyi.jianguan.manage.produce.domain.bo.PubProduceBo;
import com.ruoyi.jianguan.manage.produce.domain.entity.PubProduce;
import com.ruoyi.jianguan.manage.produce.domain.vo.PubProduceVo;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * 工序信息Service接口
 *
 * @author ruoyi
 * @date 2023-06-03
 */
public interface IPubProduceService {

    /**
     * 查询工序信息
     */
    PubProduceVo queryById(Long id);

    /**
     * 查询工序信息列表
     */
    TableDataInfo<PubProduceVo> queryPageList(PubProduceBo bo, PageQuery pageQuery);

    /**
     * 查询工序信息列表
     */
    List<PubProduceVo> queryList(PubProduceBo bo);

    /**
     * 新增工序信息
     */
    Boolean insertByBo(PubProduceBo bo);

    /**
     * 修改工序信息
     */
    Boolean updateByBo(PubProduceBo bo);

    /**
     * 校验并批量删除工序信息信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

    /**
     * 校验主键是否唯一工序信息信息
     */
    Boolean checkUniqueByPrimaryKey(Long id);

    List<PubProduce> getProduceListByTypeId(Long typeId);

    Boolean doImportProduces(Long[] ids, PubProduceBo bo);

    Map<String, String> getFillDataTemplate(Long id, String templateUrl);

    Optional<List<String>> saveFillDataTemplate(Long id, String luckySheetJson) throws IOException;
}
