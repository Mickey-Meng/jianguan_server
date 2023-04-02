package com.ruoyi.common.core.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 公共基类
 * </p>
 *
 * @description:
 * @author: zhengqing
 * @date: 2019/8/18 0018 1:30
 */
@Getter
@Setter
public abstract class MyBaseEntity extends NewBaseEntity {

    @TableLogic
    @TableField(value = "is_valid")
    private Integer isValid;

}
