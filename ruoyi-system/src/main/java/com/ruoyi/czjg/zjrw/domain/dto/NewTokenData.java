package com.ruoyi.czjg.zjrw.domain.dto;

import lombok.Data;

@Data
public class NewTokenData {
    private AccessTokenData data;
    private String code;
    private String msg;
}
