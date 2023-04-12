package com.ruoyi.jianguan.common.domain.dto;

import lombok.Data;

/**
 * @version : 1.0
 * @Author : chenzhiwei
 * @date : Create file in 2022/3/3 14:06
 * @description :
 **/
@Data
public class LoginDataDTO {

    private LoginData loginData;
    private int groupid; //用户的role权限;

}
