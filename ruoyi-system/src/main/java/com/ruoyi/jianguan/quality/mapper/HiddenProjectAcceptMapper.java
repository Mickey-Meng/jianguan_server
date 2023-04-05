package com.ruoyi.jianguan.quality.mapper;

import com.ruoyi.jianguan.quality.domain.dto.HiddenProjectAcceptPageDTO;
import com.ruoyi.jianguan.quality.domain.entity.HiddenProjectAccept;
import com.ruoyi.jianguan.quality.domain.vo.HiddenProjectAcceptPageVo;
import com.ruoyi.common.core.mapper.BaseDaoMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 隐蔽工程验收记录 Mapper 接口
 *
 * @author qiaoxulin
 * @date 2022-05-12
 */
@Mapper
@Repository
public interface HiddenProjectAcceptMapper extends BaseDaoMapper<HiddenProjectAccept> {

    /**
     * 分页查询隐蔽工程验收记录数据
     *
     * @param pageDto
     * @return
     */
    List<HiddenProjectAcceptPageVo> getPageInfo(@Param("pageDto") HiddenProjectAcceptPageDTO pageDto);
}
