package com.ruoyi.jianguan.business.quality.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 省市区
 *
 * @author qiaoxulin
 * @since 2022-05-15
 */
@Data
@Accessors(chain = true)
@TableName("zz_provice_city_area")
@ApiModel(value = "ProviceCityArea对象", description = "省市区")
public class ProviceCityArea {

    private static final long serialVersionUID = 1L;


    /**
     * id
     */
    @ApiModelProperty(value = "id")
    private Integer id;


    /**
     * 名称
     */
    @ApiModelProperty(value = "名称")
    private String name;


    /**
     * 父id
     */
    @ApiModelProperty(value = "父id")
    private Integer pid;


}
