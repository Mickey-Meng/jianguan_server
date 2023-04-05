package com.ruoyi.jianguan.quality.domain.vo;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.core.domain.entity.FileModel;
import com.ruoyi.common.core.domain.NewBaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

/**
 * 监理旁站详情返回VO
 *
 * @author qiaoxulin
 * @date : 2022/6/10 16:15
 */
@Data
@ApiModel(value = "ProjectOpenPageVo", description = "监理旁站详情返回VO")
public class SupervisionSideDetailVo extends NewBaseEntity {
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
     * 地址
     */
    @ApiModelProperty(value = "地址")
    private JSONObject address;


    /**
     * 旁站时间
     */
    @ApiModelProperty(value = "旁站时间")
    private LocalDate sideDate;

    /**
     * 旁站信息记录
     */
    @ApiModelProperty(value = "旁站信息记录")
    private String sideInfo;


    /**
     * 工程部位id
     */
    @ApiModelProperty(value = "工程部位id")
    private Integer projectPartId;

    /**
     * 工程部位名称
     */
    @ApiModelProperty(value = "工程部位名称")
    private String projectPartStr;

    /**
     * 旁站监理项目id
     */
    @ApiModelProperty(value = "旁站监理项目id")
    private Integer sideProjectId;


    /**
     * 工程部位描述
     */
    @ApiModelProperty(value = "工程部位描述")
    private String projectPartDesc;


    /**
     * 异常情况
     */
    @ApiModelProperty(value = "异常情况")
    private String exceptionCondition;


    /**
     * 旁站工作情况
     */
    @ApiModelProperty(value = "旁站工作情况")
    private String sideWorkCondition;


    /**
     * 发现的问题及处理结果
     */
    @ApiModelProperty(value = "发现的问题及处理结果")
    private String problemDealCondition;


    /**
     * 旁站现场照片
     */
    @ApiModelProperty(value = "旁站现场照片")
    private List<FileModel> scenePhotoAttachment;


    /**
     * 实测实量照片
     */
    @ApiModelProperty(value = "实测实量照片")
    private List<FileModel> actualCheckAttachment;


    /**
     * 视频上传
     */
    @ApiModelProperty(value = "视频上传")
    private List<FileModel> video;


    /**
     * 附件上传
     */
    @ApiModelProperty(value = "附件上传")
    private List<FileModel> attachment;


    /**
     * 状态 0 进行中 1已完成
     */
    @ApiModelProperty(value = "状态 0 进行中 1已完成")
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

}
