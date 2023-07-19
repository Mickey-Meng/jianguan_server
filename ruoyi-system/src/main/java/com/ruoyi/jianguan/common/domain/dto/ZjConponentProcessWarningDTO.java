package com.ruoyi.jianguan.common.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * @author
 *
 */
@Data
public class ZjConponentProcessWarningDTO implements Serializable {

    private Integer id;

    /**
     *  4  达到预警天数
     *  1  正常完工
     *  2  逾期完工
     *  3  逾期未完工
     */
    private Integer status;
    /**
     * 构件id
     */
    private Integer conponentid;

    /**
     * 构件编码
     */
    private String conponentcode;

    /**
     * 构件计划开始时间
     */
    @JsonFormat(timezone="GMT+8",pattern="yyyy-MM-dd HH:mm:ss")
    private Date plansttime;

    /**
     * 构件计划完成时间
     */
    @JsonFormat(timezone="GMT+8",pattern="yyyy-MM-dd HH:mm:ss")
    private Date planendtime;

    /**
     * 构件实际开始时间
     */
    @JsonFormat(timezone="GMT+8",pattern="yyyy-MM-dd HH:mm:ss")
    private Date actulsttime;

    /**
     * 构件实际完成时间
     */
    @JsonFormat(timezone="GMT+8",pattern="yyyy-MM-dd HH:mm:ss")
    private Date actulendtime;


    /**
     * 记录时间
     */
    @JsonFormat(timezone="GMT+8",pattern="yyyy-MM-dd HH:mm:ss")
    private Date recordtime;

    /**
     * 单位工程编号
     */
    private String projectcode;


    /**
     * 构件类型
     */
    private String conponenttype;

    /**
     * 项目名称
     */
    private String projectname;

    /**
     * 构件类型名称
     */
    private String conponenttypename;

    /**
     * 逾期天数
     */
    private Integer overdueDays;
    /**
     * 距离计划开始日期天数
     */
    private Integer startDays;
    /**
     * 工区id
     */
    private String gongquid;

    /**
     * 工区name
     */
    private String gongqu;
    /**
     * 预警级别
     */
    private String conponentlevel;
    /**
     * 单位工程
     */
    private String danweigongcheng;
    /**
     * 分部工程
     */
    private String fenbugongcheng;
    /**
     * 子分部工程
     */
    private String zifenbugongcheng;
    /**
     * 分项工程
     */
    private String fenxianggongcheng;
    /**
     * 构件名称
     */
    private String goujian;
    /**
     * 进度预警节点级别
     */
    private String jiedianjibie;

}
