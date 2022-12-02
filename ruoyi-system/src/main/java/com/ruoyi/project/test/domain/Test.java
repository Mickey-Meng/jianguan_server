package com.ruoyi.project.test.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 测试单对象 test
 *
 * @author ruoyi
 * @date 2022-12-02
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("test")
public class Test extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * id
     */
    @TableId(value = "id")
    private Long id;
    /**
     * 姓名
     */
    private String name;
    /**
     * 状态（0正常 1停用）
     */
    private String status;
    /**
     * 备注
     */
    private String remark;

}
