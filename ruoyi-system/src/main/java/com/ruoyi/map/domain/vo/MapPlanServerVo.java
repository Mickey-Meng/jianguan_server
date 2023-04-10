package com.ruoyi.map.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.ruoyi.common.annotation.ExcelDictFormat;
import com.ruoyi.common.convert.ExcelDictConvert;
import lombok.Data;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;




/**
 * 地图方案服务关联视图对象 map_plan_server
 *
 * @author ruoyi
 * @date 2023-04-10
 */
@Data
@ExcelIgnoreUnannotated
@ApiModel(value = "地图方案服务关联", description = "地图方案服务关联VO")
public class MapPlanServerVo {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ExcelProperty(value = "主键")
    @ApiModelProperty(value = "主键", required = true)
    private Long id;

    /**
     * 地图方案ID
     */
    @ExcelProperty(value = "地图方案ID")
    @ApiModelProperty(value = "地图方案ID", required = true)
    private Long planId;

    /**
     * 地图服务信息ID
     */
    @ExcelProperty(value = "地图服务信息ID")
    @ApiModelProperty(value = "地图服务信息ID", required = true)
    private Long serverId;


    /**
     * 服务名称
     */
    @ExcelProperty(value = "服务名称")
    @ApiModelProperty(value = "服务名称", required = true)
    private String serverName;

    /**
     * 自定义服务id
     */
    @ExcelProperty(value = "自定义服务id")
    @ApiModelProperty(value = "自定义服务id", required = true)
    private String serverCode;

    /**
     * 地图服务地址
     */
    @ExcelProperty(value = "地图服务地址")
    @ApiModelProperty(value = "地图服务地址", required = true)
    private String serverUrl;

    /**
     * 服务类型
     */
    @ExcelProperty(value = "服务类型")
    @ApiModelProperty(value = "服务类型", required = true)
    private String serverType;

    /**
     * 是否可见
     */
    @ExcelProperty(value = "是否可见")
    @ApiModelProperty(value = "是否可见", required = true)
    private Long visiable;

    /**
     * 状态（0=正常,1=删除）
     */
    @ExcelProperty(value = "状态", converter = ExcelDictConvert.class)
    @ExcelDictFormat(readConverterExp = "0==正常,1=删除")
    @ApiModelProperty(value = "状态", required = true)
    private String status;

    /**
     * 属性信息
     */
    @ExcelProperty(value = "属性信息")
    @ApiModelProperty(value = "属性信息", required = true)
    private String attrbuites;

    /**
     * 服务缩率图
     */
    @ExcelProperty(value = "服务缩率图")
    @ApiModelProperty(value = "服务缩率图", required = true)
    private String thumbnail;

    /**
     *
     */
    @ExcelProperty(value = "描述")
    @ApiModelProperty(value = "", required = true)
    private String description;

    /**
     * 创建者
     */
    @ExcelProperty(value = "创建者")
    @ApiModelProperty(value = "创建者", required = true)
    private String createBy;

    /**
     * 创建时间(加入时间)
     */
    @ExcelProperty(value = "加入时间")
    @ApiModelProperty(value = "加入时间", required = true)
    private Date createTime;

    /**
     * 更新者
     */
    @ExcelProperty(value = "更新者")
    @ApiModelProperty(value = "更新者", required = true)
    private String updateBy;

    /**
     * 更新时间
     */
    @ExcelProperty(value = "更新时间")
    @ApiModelProperty(value = "更新时间", required = true)
    private Date updateTime;

}
