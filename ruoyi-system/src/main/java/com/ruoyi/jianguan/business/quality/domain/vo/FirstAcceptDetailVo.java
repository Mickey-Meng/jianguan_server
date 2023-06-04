package com.ruoyi.jianguan.business.quality.domain.vo;

import com.ruoyi.common.core.domain.entity.Conponent;
import com.ruoyi.common.core.domain.entity.FileModel;
import com.ruoyi.common.core.domain.NewBaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.util.List;

/**
 * 首件认可详情返回VO
 *
 * @author qiaoxulin
 * @date : 2022/6/1 18:20
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "FirstAcceptDetailVo", description = "首件认可详情返回VO")
public class FirstAcceptDetailVo extends NewBaseEntity {

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
     * 首件工程名称
     */
    @ApiModelProperty(value = "首件工程名称")
    private String firstProjectName;


    /**
     * 分部分项id
     */
    @ApiModelProperty(value = "分部分项id")
    private Integer subProject;

    private Conponent conponent;


    /**
     * 具体分项
     */
    @ApiModelProperty(value = "具体分项")
    private String subProjectDetail;


    /**
     * 实施日期
     */
    @ApiModelProperty(value = "实施日期")
    private LocalDate buildDate;


    /**
     * 施工过程简述
     */
    @ApiModelProperty(value = "施工过程简述")
    private String buildProcessExplain;


    /**
     * 监理工作情况
     */
    @ApiModelProperty(value = "监理工作情况")
    private String supervisionWorkExplain;


    /**
     * 主要数据记录
     */
    @ApiModelProperty(value = "主要数据记录")
    private String mainDataExplain;


    /**
     * 发现的问题及处理情况
     */
    @ApiModelProperty(value = "发现的问题及处理情况")
    private String problemDealExplain;


    /**
     * 检验结果描述
     */
    @ApiModelProperty(value = "检验结果描述")
    private String checkResultExplain;


    /**
     * 外观质量描述
     */
    @ApiModelProperty(value = "外观质量描述")
    private String faceQuelityExplain;


    /**
     * 施工技术、工艺方案说明和图表
     */
    @ApiModelProperty(value = "施工技术、工艺方案说明和图表")
    private List<FileModel> buildTechAttachment;


    /**
     * 测量放样资料
     */
    @ApiModelProperty(value = "测量放样资料")
    private List<FileModel> measureAttachment;


    /**
     * 材料出厂保证书、材料检测试验报告
     */
    @ApiModelProperty(value = "材料出厂保证书、材料检测试验报告")
    private List<FileModel> materialAttachment;


    /**
     * 机械的主要技术标准及最大生产能力
     */
    @ApiModelProperty(value = "机械的主要技术标准及最大生产能力")
    private List<FileModel> mechanicalAttachment;


    /**
     * 批准的标准试验报告
     */
    @ApiModelProperty(value = "批准的标准试验报告")
    private List<FileModel> testAttachment;


    /**
     * 开工申请
     */
    @ApiModelProperty(value = "开工申请")
    private List<FileModel> openAttachment;


    /**
     * 质量保证资料
     */
    @ApiModelProperty(value = "质量保证资料")
    private List<FileModel> qualityAttachment;


    /**
     * 影像资料
     */
    @ApiModelProperty(value = "影像资料")
    private List<FileModel> imageVideo;


    /**
     * 首件工程总结
     */
    @ApiModelProperty(value = "首件工程总结")
    private List<FileModel> firstProjectVideo;


    /**
     * 首件工程通过情况
     */
    @ApiModelProperty(value = "首件工程通过情况")
    private String firstPassExplain;


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
}
