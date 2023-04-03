package com.ruoyi.czjg.zjrw.domain.dto;

import com.ruoyi.common.constant.Constant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author ：lin_zhixiong
 * @date ：Created in 2021/6/28 10:28 上午
 * @version: V1.0
 * @slogan: 天下风云出我辈，一入代码苦耕耘
 * @description:
 **/
@ApiModel
public class Response<T> {

    /** 返回码 */
    @ApiModelProperty(position = 1, value = "返回码")
    private int code;
    /** 返回文字内容 */
    @ApiModelProperty(position = 2, value = "返回信息")
    private String message;
    /** 返回实体对象 */
    @ApiModelProperty(position = 3, value = "返回体")
    private T obj;

    public Response(int code, String message, T obj) {
        this.code = code;
        this.message = message;
        this.obj = obj;
    }

    public Response() {}

    public Response(Exception ex) {
        this.code = Constant.EXCEPTION;
        this.message = ex.getMessage();
    }

    public Response(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public Response(ResponseCode responseCode) {
        this.code = responseCode.getCode();
        this.message = responseCode.getMsg();
    }

    public Response(ResponseCode responseCode, T obj) {
        this.code = responseCode.getCode();
        this.message = responseCode.getMsg();
        this.obj = obj;
    }
}

