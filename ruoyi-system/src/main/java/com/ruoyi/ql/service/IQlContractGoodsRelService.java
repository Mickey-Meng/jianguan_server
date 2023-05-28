package com.ruoyi.ql.service;


import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;
import com.ruoyi.ql.domain.bo.QlContractGoodsRelBo;
import com.ruoyi.ql.domain.vo.QlContractGoodsRelVo;

import java.util.Collection;
import java.util.List;

/**
 * 合同与商品关系Service接口
 *
 * @author ruoyi
 * @date 2023-05-16
 */
public interface IQlContractGoodsRelService {

    /**
     * 查询合同与商品关系
     */
    QlContractGoodsRelVo queryById(Long id);

    /**
     * 查询合同与商品关系列表
     */
    TableDataInfo<QlContractGoodsRelVo> queryPageList(QlContractGoodsRelBo bo, PageQuery pageQuery);

    /**
     * 查询合同与商品关系列表
     */
    List<QlContractGoodsRelVo> queryList(QlContractGoodsRelBo bo);

    /**
     * 新增合同与商品关系
     */
    Boolean insertByBo(QlContractGoodsRelBo bo);

    /**
     * 修改合同与商品关系
     */
    Boolean updateByBo(QlContractGoodsRelBo bo);

    /**
     * 校验并批量删除合同与商品关系信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}