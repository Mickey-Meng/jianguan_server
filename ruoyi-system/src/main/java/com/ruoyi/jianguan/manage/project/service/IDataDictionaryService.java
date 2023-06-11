package com.ruoyi.jianguan.manage.project.service;

import com.ruoyi.jianguan.manage.project.domain.bo.DataDictionaryBo;
import com.ruoyi.jianguan.manage.project.domain.vo.DataDictionaryVo;
import com.ruoyi.ql.domain.bo.QlShopGoodsTypeBo;

import java.util.Collection;
import java.util.List;

/**
 * 商品类别Service接口
 *
 * @author ruoyi
 * @date 2022-12-11
 */
public interface IDataDictionaryService {

    /**
     * 查询商品类别
     */
    DataDictionaryVo queryById(Long id);


    /**
     * 查询商品类别列表
     */
    List<DataDictionaryVo> queryList(DataDictionaryBo bo);
    List<DataDictionaryVo> getFileTypesByPCode(String pCode);


    /**
     * 新增商品类别
     */
    Boolean insertByBo(DataDictionaryBo bo);

    /**
     * 修改商品类别
     */
    Boolean updateByBo(DataDictionaryBo bo);

    /**
     * 校验并批量删除商品类别信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);



}
