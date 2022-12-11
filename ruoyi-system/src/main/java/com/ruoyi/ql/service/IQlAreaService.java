package com.ruoyi.ql.service;

import com.ruoyi.ql.domain.QlArea;
import com.ruoyi.ql.domain.vo.QlAreaVo;
import com.ruoyi.ql.domain.bo.QlAreaBo;

import java.util.Collection;
import java.util.List;

/**
 * 省市区Service接口
 *
 * @author ruoyi
 * @date 2022-12-11
 */
public interface IQlAreaService {

    /**
     * 查询省市区
     */
    QlAreaVo queryById(Long Id);


    /**
     * 查询省市区列表
     */
    List<QlAreaVo> queryList(QlAreaBo bo);

    /**
     * 新增省市区
     */
    Boolean insertByBo(QlAreaBo bo);

    /**
     * 修改省市区
     */
    Boolean updateByBo(QlAreaBo bo);

    /**
     * 校验并批量删除省市区信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
