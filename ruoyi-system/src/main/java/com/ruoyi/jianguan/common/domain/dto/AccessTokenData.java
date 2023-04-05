package com.ruoyi.jianguan.common.domain.dto;

import lombok.Data;

@Data
public class AccessTokenData {
    private String accessToken;
    private Long expireTime;
}
