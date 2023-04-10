package com.ruoyi.map.domain.bo;

import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.*;

import java.util.Collection;
import java.util.Date;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 地图方案服务关联业务对象 map_plan_server
 *
 * @author ruoyi
 * @date 2023-04-10
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class MapPlanServerBo extends BaseEntity {

    /**
     * 主键
     */
    private Long id;

    /**
     * 地图方案ID
     */
    private Long planId;

    /**
     * 地图服务信息ID
     */
    private Long serverId;

    private String serverIds;


}
