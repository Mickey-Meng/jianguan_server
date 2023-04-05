package com.ruoyi.jianguan.contract.domain.dto;

import com.ruoyi.common.core.domain.dto.SaveDTO;
import com.ruoyi.jianguan.contract.domain.entity.EnterExitUser;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 进退场保存dto
 *
 * @author qiaoxulin
 * @date : 2022/5/27 17:46
 */
@Data
@ApiModel(value = "EnterExitSaveDTO", description = "进退场保存dto")
public class EnterExitSaveDTO extends SaveDTO {

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
     * 施工标段id
     */
    @ApiModelProperty(value = "施工标段id")
    private Integer buildSection;

    /**
     * 劳务分包合同id
     */
    @ApiModelProperty(value = "劳务分包合同id")
    private Long laborContractId;

    /**
     * 报审类型 0 进场 1退场
     */
    @ApiModelProperty(value = "报审类型 0 进场 1退场")
    private Integer type;


    /**
     * 人数
     */
    @ApiModelProperty(value = "人数")
    private Integer num;


    /**
     * 说明
     */
    @ApiModelProperty(value = "说明")
    private String explaination;


    /**
     * 状态 0 进行中 1 已完成
     */
    @ApiModelProperty(value = "状态 0 进行中 1 已完成")
    private Integer status;


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
     * 进出场人员清单
     */
    @ApiModelProperty(value = "进出场人员清单")
    private List<EnterExitUser> enterExitUsers;

}
