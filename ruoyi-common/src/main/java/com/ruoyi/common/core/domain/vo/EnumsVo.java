package com.ruoyi.common.core.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 下拉列表项
 *
 * @author: qiaoxulin
 * @date: 20220513
 */
@ApiModel(value = "下拉列表项", description = "下拉列表项")
@Data
@Accessors(chain = true)
public class EnumsVo implements Serializable {

    /**
     * 编码
     */
    @ApiModelProperty(value = "编码code")
    private int code;

    /**
     * 描述
     */
    @ApiModelProperty(value = "描述")
    private String desc;
}
