package com.ruoyi.project.bill.mapper;


import com.ruoyi.common.core.mapper.BaseMapperPlus;
import com.ruoyi.project.bill.domain.MeaContractBill;
import com.ruoyi.project.bill.domain.pojo.ChapterCollectDto;
import com.ruoyi.project.bill.domain.vo.MeaContractBillVo;

import java.util.List;

/**
 * 工程量清单Mapper接口
 *
 * @author ruoyi
 * @date 2022-12-03
 */
public interface MeaContractBillMapper extends BaseMapperPlus<MeaContractBillMapper, MeaContractBill, MeaContractBillVo> {

    List<ChapterCollectDto> chapter_collect();

}
