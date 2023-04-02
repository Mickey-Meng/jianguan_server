package com.ruoyi.common.core.domain.dto;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Map;

/**
 * 保存参数dto
 *
 * @author qiaoxulin
 * @date : 2022/5/13 14:51
 */
@Data
@ApiModel(value = "SaveDTO", description = "保存参数dto")
public class SaveDTO {
    /**
     * 流程节点审核人员对象
     */
    @ApiModelProperty(name = "auditUser",value = "流程节点审核人员对象")
    Map<String, Object> auditUser;

    /**
     * 流程节点抄送人员对象
     */
    @ApiModelProperty(name = "copyData",value = "流程节点抄送人员对象")
    JSONObject copyData = null;
}
