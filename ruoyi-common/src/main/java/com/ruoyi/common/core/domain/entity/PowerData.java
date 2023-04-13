package com.ruoyi.common.core.domain.entity;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author ：lin_zhixiong
 * @date ：Created in 2021/7/8 3:19 下午
 * @version: V1.0
 * @slogan: 天下风云出我辈，一入代码苦耕耘
 * @description: 权限对象，
 **/

@Data
@Builder
@Accessors(chain = true)
public class PowerData {

    private Integer id; //用户id
    private List<String> projects; // 关联的项目id
    private List<Integer> gongqus; //关联的工区id
    private Integer role; //关联的角色id
    private String roleIds; //关联的角色id

}
