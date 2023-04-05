package com.ruoyi.jianguan.common.domain.entity;

/**
 * @Author : Chen_ZhiWei
 * @Date : Create file in 2022/5/23 11:21
 * @Version : 1.0
 * @Description :
 **/
public class PersonReturnModel {

    private String code;

    private String data;

    private String errorCode;

    private String errorMessage;

    private boolean success;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    @Override
    public String toString() {
        return "PersonReturnModel{" +
                "code='" + code + '\'' +
                ", data=" + data +
                ", errorCode='" + errorCode + '\'' +
                ", errorMessage='" + errorMessage + '\'' +
                ", success=" + success +
                '}';
    }
}
