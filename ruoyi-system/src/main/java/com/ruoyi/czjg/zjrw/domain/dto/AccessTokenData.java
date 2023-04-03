package com.ruoyi.czjg.zjrw.domain.dto;

import lombok.Data;

@Data
public class AccessTokenData {
    private String accessToken;
    private Long expireTime;
}
