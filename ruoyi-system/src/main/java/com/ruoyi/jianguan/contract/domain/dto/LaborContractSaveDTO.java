package com.ruoyi.jianguan.contract.domain.dto;

import com.ruoyi.common.core.domain.dto.SaveDTO;
import com.ruoyi.common.core.domain.entity.FileModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * @author qiaoxulin
 * @date : 2022/5/20 14:31
 */
@Data
@ApiModel(value = "LaborContractSaveDTO", description = "劳务分包合同保存dto")
public class LaborContractSaveDTO extends SaveDTO {

    /**
     * id
     */
    @ApiModelProperty(value = "id")
    private Long id;


    /**
     * 项目id
     */
    @ApiModelProperty(value = "项目id")
    private Integer projectId;


    /**
     * 发起时间
     */
    @ApiModelProperty(value = "发起时间")
    private LocalDate startDate;


    /**
     * 施工标段id
     */
    @ApiModelProperty(value = "施工标段id")
    private Integer buildSection;


    /**
     * 合同号
     */
    @ApiModelProperty(value = "合同号")
    private String contractCode;


    /**
     * 承包人
     */
    @ApiModelProperty(value = "承包人")
    private String contractUser;


    /**
     * 合同附件
     */
    @ApiModelProperty(value = "合同附件")
    private List<FileModel> attachment;


    /**
     * 信息
     */
    @ApiModelProperty(value = "信息")
    private List<Information> information;


    /**
     * 项目经理
     */
    @ApiModelProperty(value = "项目经理")
    private Integer projectManageUser;


    /**
     * 专监
     */
    @ApiModelProperty(value = "专监")
    private Integer specialUser;


    /**
     * 总监
     */
    @ApiModelProperty(value = "总监")
    private Integer directorUser;


    /**
     * 是否为草稿 0是 1不是
     */
    @ApiModelProperty(value = "是否为草稿 0是 1不是")
    private Integer draftFlag;


    /**
     * 删除标记(1: 正常 -1: 已删除)
     */
    @ApiModelProperty(value = "删除标记(1: 正常 -1: 已删除)")
    private Integer deletedFlag;

    /**
     * 信息填报内容
     */
    @Data
    public static class Information {

        /**
         * 拟分包工程名称
         */
        @ApiModelProperty(value = "拟分包工程名称")
        private String buildProjectName;

        /**
         * 拟分包工程部位
         */
        @ApiModelProperty(value = "拟分包工程部位")
        private String buildProjectPartName;

        /**
         * 承包人名称
         */
        @ApiModelProperty(value = "承包人名称")
        private String contractUserName;

        /**
         * 承包负责人
         */
        @ApiModelProperty(value = "承包负责人")
        private String contractChargeUserName;

        /**
         * 身份证号
         */
        @ApiModelProperty(value = "身份证号")
        private String cardNum;

        /**
         * 拟分包工程合同金额(元)
         */
        @ApiModelProperty(value = "拟分包工程合同金额(元)")
        private BigDecimal contractNum;

        /**
         * 拟分包施工日期
         */
        @ApiModelProperty(value = "拟分包施工工期开始月份")
        private String buildStartMonth;

        /**
         * 拟分包施工日期
         */
        @ApiModelProperty(value = "拟分包施工工期结束月份")
        private String buildEndMonth;

        /**
         * 承包人专业分包资质
         */
        @ApiModelProperty(value = "承包人专业分包资质")
        private String contractUserQualification;

        /**
         * 备注
         */
        @ApiModelProperty(value = "备注")
        private String remark;
    }

}
