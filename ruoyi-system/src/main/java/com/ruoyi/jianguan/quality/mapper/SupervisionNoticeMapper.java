package com.ruoyi.jianguan.quality.mapper;

import com.ruoyi.jianguan.quality.domain.dto.SupervisionNoticePageDTO;
import com.ruoyi.jianguan.quality.domain.entity.SupervisionNotice;
import com.ruoyi.jianguan.quality.domain.vo.SupervisionNoticePageVo;
import com.ruoyi.common.core.mapper.BaseDaoMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 监理通知 Mapper 接口
 *
 * @author qiaoxulin
 * @date 2022-06-11
 */
@Mapper
@Repository
public interface SupervisionNoticeMapper extends BaseDaoMapper<SupervisionNotice> {
    /**
     * 分页查询监理通知数据
     *
     * @param pageDto
     * @return
     */
    List<SupervisionNoticePageVo> getPageInfo(@Param("pageDto") SupervisionNoticePageDTO pageDto);
}
