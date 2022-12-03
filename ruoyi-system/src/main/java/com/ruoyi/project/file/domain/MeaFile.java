package com.ruoyi.project.file.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 附件记录对象 mea_file
 *
 * @author ruoyi
 * @date 2022-12-03
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("mea_file")
public class MeaFile extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * ID
     */
    @TableId(value = "file_id")
    private String fileId;
    /**
     * ID
     */
    private String fileName;
    /**
     * ID
     */
    private String flowId;
    /**
     * ID
     */
    private String url;
    /**
     * 地址
     */
    private String path;
    /**
     * 状态（0正常 1停用）
     */
    private String status;
    /**
     * 备注
     */
    private String remark;

}
