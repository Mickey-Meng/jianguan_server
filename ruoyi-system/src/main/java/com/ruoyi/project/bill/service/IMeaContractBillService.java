package com.ruoyi.project.bill.service;


import com.ruoyi.common.core.domain.PageQuery;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.project.bill.domain.bo.MeaContractBillBo;
import com.ruoyi.project.bill.domain.vo.MeaContractBillVo;

import java.util.Collection;
import java.util.List;

/**
 * 工程量清单Service接口
 *
 * @author ruoyi
 * @date 2022-12-03
 */
public interface IMeaContractBillService {

    /**
     * 查询工程量清单
     */
    MeaContractBillVo queryById(String id);


    /**
     * 查询工程量清单列表
     */
    List<MeaContractBillVo> queryList(MeaContractBillBo bo);

    /**
     * 新增工程量清单
     */
    Boolean insertByBo(MeaContractBillBo bo);

    /**
     * 修改工程量清单
     */
    Boolean updateByBo(MeaContractBillBo bo);

    /**
     * 校验并批量删除工程量清单信息
     */
    Boolean deleteWithValidByIds(Collection<String> ids, Boolean isValid);

    TableDataInfo<MeaContractBillVo> queryPageList(MeaContractBillBo bo, PageQuery pageQuery);

}
