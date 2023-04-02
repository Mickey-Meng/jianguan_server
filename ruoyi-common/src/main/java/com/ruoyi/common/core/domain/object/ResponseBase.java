package com.ruoyi.common.core.domain.object;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @mm
 * @author ：lin_zhixiong
 * @date ：Created in 2021/6/18 8:59 上午
 * @version: V1.0
 * @slogan: 天下风云出我辈，一入代码苦耕耘
 * @description:
 **/
@Slf4j
@Data
public class ResponseBase {
    public static final Integer SUCCESS = 200;
    public static final Integer ERROR = 500;

    private Integer status = SUCCESS;
    private String message = "success";
    private Object data = null;

    /**
     * 为了优化性能，所有没有携带数据的正确结果，均可用该对象表示。
     */
    private static final ResponseBase OK = new ResponseBase();

    public ResponseBase() {
    }

    public ResponseBase(Object data) {
        this.data = data;
    }

    public ResponseBase(Integer status, String message) {
        this.status = status;
        this.message = message;
    }

    public ResponseBase(Integer status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    /**
     * 创建成功对象。
     * 如果需要绑定返回数据，可以在实例化后调用setDataObject方法。
     *
     * @return 返回创建的ResponseBase实例对象
     */
    public static ResponseBase success() {
        return new ResponseBase(200, "success");
    }

    /**
     * 创建带有返回数据的成功对象。
     *
     * @param data 返回的数据对象
     * @return 返回创建的ResponseBase实例对象
     */
    public static ResponseBase success(Object data) {
        ResponseBase responseBase = new ResponseBase(data);
        responseBase.setData(data);
        return responseBase;
    }


    /**
     * 创建错误对象。
     * 如果返回错误对象，errorCode 和 errorMessage 分别取自于参数 errorCodeEnum 的 name() 和 getErrorMessage()。
     *
     * @param message 错误码枚举
     * @return 返回创建的ResponseBase实例对象
     */
    public static ResponseBase error(Integer status, String message) {
        return new ResponseBase(status, message);
    }

    /**
     * 创建错误对象。
     * 如果返回错误对象，errorCode 和 errorMessage 分别取自于参数 errorCode 和参数 errorMessage。
     *
     * @param errorMessage 自定义的错误信息
     * @return 返回创建的ResponseBase实例对象
     */
    public static ResponseBase error(String errorMessage) {
        return new ResponseBase(500, errorMessage);
    }


    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResponseBase{" +
                "status=" + status +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
