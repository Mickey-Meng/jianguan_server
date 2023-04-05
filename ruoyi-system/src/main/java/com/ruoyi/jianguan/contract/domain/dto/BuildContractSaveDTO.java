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
 * 施工专业分包合同保存dto
 *
 * @author qiaoxulin
 * @date : 2022/5/18 11:27
 */
@Data
@ApiModel(value = "BuildContractSaveDTO", description = "施工专业分包合同保存dto")
public class BuildContractSaveDTO extends SaveDTO {

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
     * 项目名称
     */
    @ApiModelProperty(value = "项目名称")
    private String projectName;


    /**
     * 施工标段id
     */
    @ApiModelProperty(value = "施工标段id")
    private Integer buildSection;


    /**
     * 合同编号
     */
    @ApiModelProperty(value = "合同编号")
    private String contractCode;


    /**
     * 承包人
     */
    @ApiModelProperty(value = "承包人")
    private String contractUser;


    /**
     * 附件
     */
    @ApiModelProperty(value = "附件")
    private List<FileModel> attachment;


    /**
     * 合同信息
     */
    @ApiModelProperty(value = "合同信息")
    private List<ContractInfo> contractInfo;


    /**
     * 项目经理
     */
    @ApiModelProperty(value = "项目经理")
    private Integer projectManageUser;


    /**
     * 监理办
     */
    @ApiModelProperty(value = "监理办")
    private Integer supervisionUser;


    /**
     * 指挥部
     */
    @ApiModelProperty(value = "指挥部")
    private Integer commandUser;


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
     * 合同信息
     */
    @Data
    public static class ContractInfo {

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
        @ApiModelProperty(value = "拟分包施工日期")
        private LocalDate buildDate;

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
