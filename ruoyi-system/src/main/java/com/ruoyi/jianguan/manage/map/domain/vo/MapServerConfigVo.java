package com.ruoyi.jianguan.manage.map.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.ruoyi.common.annotation.ExcelDictFormat;
import com.ruoyi.common.convert.ExcelDictConvert;
import lombok.Data;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;




/**
 * 地图服务注册视图对象 sys_map_server_config
 *
 * @author ruoyi
 * @date 2023-04-08
 */
@Data
@ExcelIgnoreUnannotated
@ApiModel(value = "地图服务注册", description = "地图服务注册VO")
public class MapServerConfigVo {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ExcelProperty(value = "主键")
    @ApiModelProperty(value = "主键", required = true)
    private Long id;

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
    private String visiable;

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
    @ExcelProperty(value = "")
    @ApiModelProperty(value = "", required = true)
    private String description;

    /**
     * 备注
     */
    @ExcelProperty(value = "备注")
    @ApiModelProperty(value = "备注", required = true)
    private String remark;


}
