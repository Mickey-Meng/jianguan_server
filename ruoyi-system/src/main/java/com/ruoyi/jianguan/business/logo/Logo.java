package com.ruoyi.jianguan.business.logo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author bx 2023/5/30
 */
@Data
@TableName("logo")
public class Logo {
    private Long id;
    private String logoUrl;
    private String title;
}
