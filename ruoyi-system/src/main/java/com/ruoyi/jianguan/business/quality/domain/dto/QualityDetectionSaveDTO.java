package com.ruoyi.jianguan.business.quality.domain.dto;

import com.ruoyi.common.core.domain.dto.SaveDTO;
import com.ruoyi.common.core.domain.entity.FileModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * 质量检测保存dto
 *
 * @author qiaoxulin
 * @since 2022-05-11
 */
@Data
@ApiModel(value = "QualityDetectionSaveDTO", description = "质量检测保存dto")
public class QualityDetectionSaveDTO extends SaveDTO {
    /**
     * id
     */
    @ApiModelProperty(value = "id")
    private Long id;


    /**
     * 施工标段id
     */
    @ApiModelProperty(value = "施工标段id")
    private Integer buildSection;

    /**
     * 项目id
     */
    @ApiModelProperty(value = "项目id")
    private Integer projectId;

    /**
     * 报验单编号
     */
    @ApiModelProperty(value = "报验单编号")
    private String inspectionCode;


    /**
     * 填报日期
     */
    @ApiModelProperty(value = "填报日期")
    private LocalDate fillDate;


    /**
     * 检测信息
     */
    @ApiModelProperty(value = "检测信息")
    private List<DetectionInfo> detectionInfo;


    /**
     * 试验检测报告
     */
    @ApiModelProperty(value = "试验检测报告")
    private List<FileModel> detectionReport;

    /**
     * 出厂信息
     */
    @ApiModelProperty(value = "出厂信息")
    private List<FileModel> factoryInfo;

    /**
     * 其他附件
     */
    @ApiModelProperty(value = "其他附件")
    private List<FileModel> otherAttachment;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;


    /**
     * 审核人
     */
    @ApiModelProperty(value = "审核人")
    private Integer detectionUser;


    /**
     * 删除标记(1: 正常 -1: 已删除)
     */
    @ApiModelProperty(value = "删除标记(1: 正常 -1: 已删除)")
    private Integer deletedFlag;

    /**
     * 是否为草稿 0是 1不是
     */
    @ApiModelProperty(value = "是否为草稿 0是 默认1不是")
    private Integer draftFlag;

    /**
     * 检测信息
     */
    @Data
    public static class DetectionInfo {
        /**
         * 材料名称
         */
        @ApiModelProperty(value = "材料名称")
        private String name;
        /**
         * 材料来源-省市区
         */
        @ApiModelProperty(value = "材料来源-省市区")
        private Address address;
        /**
         * 材料规格
         */
        @ApiModelProperty(value = "材料规格")
        private String specification;
        /**
         * 工程部位
         */
        @ApiModelProperty(value = "工程部位")
        private String projectPart;
        /**
         * 材料数量
         */
        @ApiModelProperty(value = "材料数量")
        private BigDecimal num;
        /**
         * 材料单位
         */
        @ApiModelProperty(value = "材料单位")
        private String unit;
        /**
         * 取样地点
         */
        @ApiModelProperty(value = "取样地点")
        private String takeAddress;
        /**
         * 实验日期
         */
        @ApiModelProperty(value = "实验日期")
        private LocalDate testDate;
        /**
         * 实验数量
         */
        @ApiModelProperty(value = "实验数量")
        private Integer testNum;
        /**
         * 合格数量
         */
        @ApiModelProperty(value = "合格数量")
        private Integer qualifiedNum;
        /**
         * 总合格率
         */
        @ApiModelProperty(value = "总合格率")
        private Double qualifiedRate;
        /**
         * 检测结果： 0合格 1不合格
         */
        @ApiModelProperty(value = "检测结果： 0合格 1不合格")
        private Integer detectionResult;
        /**
         * 报告编号
         */
        @ApiModelProperty(value = "报告编号")
        private String reportCode;
    }

    /**
     * 省市区
     */
    @Data
    public static class Address {
        /**
         * 省
         */
        @ApiModelProperty(value = "省")
        private String provice;
        /**
         * 省id
         */
        @ApiModelProperty(value = "省id")
        private Integer proviceId;
        /**
         * 市
         */
        @ApiModelProperty(value = "市")
        private String city;
        /**
         * 市id
         */
        @ApiModelProperty(value = "市id")
        private Integer cityId;
        /**
         * 区
         */
        @ApiModelProperty(value = "区")
        private String district;
        /**
         * 区id
         */
        @ApiModelProperty(value = "区id")
        private Integer districtId;
    }
}
