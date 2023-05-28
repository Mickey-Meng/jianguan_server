package com.ruoyi.jianguan.manage.project.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.ruoyi.common.annotation.ExcelDictFormat;
import com.ruoyi.common.convert.ExcelDictConvert;
import lombok.Data;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;




/**
 * 设备监控视图对象 pub_monitor
 *
 * @author ruoyi
 * @date 2023-05-28
 */
@Data
@ExcelIgnoreUnannotated
@ApiModel(value = "设备监控", description = "设备监控VO")
public class PubMonitorVo {

    private static final long serialVersionUID = 1L;

    /**
     * 项目编号
     */
    @ExcelProperty(value = "项目编号")
    @ApiModelProperty(value = "项目编号", required = true)
    private Integer id;

    /**
     * 项目id
     */
    @ExcelProperty(value = "项目id")
    @ApiModelProperty(value = "项目id", required = true)
    private String projectId;

    /**
     * 名称
     */
    @ExcelProperty(value = "名称")
    @ApiModelProperty(value = "名称", required = true)
    private String name;

    /**
     * 类型
     */
    @ExcelProperty(value = "类型")
    @ApiModelProperty(value = "类型", required = true)
    private String type;

    /**
     * 设备编号
     */
    @ExcelProperty(value = "设备编号")
    @ApiModelProperty(value = "设备编号", required = true)
    private String deviceNo;

    /**
     * 通道编号
     */
    @ExcelProperty(value = "通道编号")
    @ApiModelProperty(value = "通道编号", required = true)
    private String channelNo;

    /**
     * 监控url
     */
    @ExcelProperty(value = "监控url")
    @ApiModelProperty(value = "监控url", required = true)
    private String url;

    /**
     * 坐标
     */
    @ExcelProperty(value = "坐标")
    @ApiModelProperty(value = "坐标", required = true)
    private String geom;


}
