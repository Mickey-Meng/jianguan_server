package com.ruoyi.jianguan.business.contract.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 进退场人员
 *
 * @author qiaoxulin
 * @since 2022-05-28
 */
@Data
@Accessors(chain = true)
@TableName("zz_enter_exit_user")
@ApiModel(value = "EnterExitUser对象", description = "进退场人员")
public class EnterExitUser {

    private static final long serialVersionUID = 1L;


    /**
     * id
     */
    @ApiModelProperty(value = "id")
    private Long id;


    /**
     * 进退场id
     */
    @ApiModelProperty(value = "进退场id")
    private Long enterExitId;


    /**
     * 人员类型 0 进场 1退场
     */
    @ApiModelProperty(value = "人员类型 0 进场 1退场")
    private Integer type;


    /**
     * 姓名
     */
    @ApiModelProperty(value = "姓名")
    private String name;


    /**
     * 手机号
     */
    @ApiModelProperty(value = "手机号")
    private String phone;


    /**
     * 身份证号
     */
    @ApiModelProperty(value = "身份证号")
    private String card;


    /**
     * 户籍
     */
    @ApiModelProperty(value = "户籍")
    private String household;


    /**
     * 身体状况
     */
    @ApiModelProperty(value = "身体状况")
    private String bodyStatus;


    /**
     * 是否到过中高风险地区
     */
    @ApiModelProperty(value = "是否到过中高风险地区")
    private String isRisk;


    /**
     * 中高风险地区
     */
    @ApiModelProperty(value = "中高风险地区")
    private String riskArea;


    /**
     * 出发地
     */
    @ApiModelProperty(value = "出发地")
    private String startArea;


    /**
     * 途径城市
     */
    @ApiModelProperty(value = "途径城市")
    private String wayCity;


    /**
     * 是否完成疫苗接种
     */
    @ApiModelProperty(value = "是否完成疫苗接种")
    private String isVaccine;


    /**
     * 是否为绿码
     */
    @ApiModelProperty(value = "是否为绿码")
    private String isHealth;


    /**
     * 工种
     */
    @ApiModelProperty(value = "工种")
    private String workType;


    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;


    /**
     * 队伍公司名称
     */
    @ApiModelProperty(value = "队伍公司名称")
    private String companyName;


    /**
     * 队伍公司联系电话
     */
    @ApiModelProperty(value = "队伍公司联系电话")
    private String companyPhone;


}
