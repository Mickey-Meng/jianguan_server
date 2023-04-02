package com.ruoyi.common.constant;

/**
 * 错误code和message枚举类
 *
 * @author qiaoxulin 20220325
 */
public enum ExceptionCodeEnum {

    /**
     * 没有错误
     */
    NO_ERROR(200, "没有错误"),
    /**
     * 未处理的异常！
     */
    UNHANDLED_EXCEPTION(201, "未处理的异常！"),

    ARGUMENT_NULL_EXIST(301, "数据验证失败，接口调用参数存在空值，请核对！"),
    ARGUMENT_PK_ID_NULL(302, "数据验证失败，接口调用主键Id参数为空，请核对！"),
    INVALID_ARGUMENT_FORMAT(303, "数据验证失败，不合法的参数格式，请核对！"),
    INVALID_STATUS_ARGUMENT(304, "数据验证失败，无效的状态参数值，请核对！"),
    UPLOAD_FAILED(305, "数据验证失败，数据上传失败！"),
    INVALID_UPLOAD_FIELD(306, "数据验证失败，该字段不支持数据上传！"),
    INVALID_UPLOAD_STORE_TYPE(307, "数据验证失败，并不支持上传存储类型！"),
    INVALID_UPLOAD_FILE_ARGUMENT(308, "数据验证失败，上传文件参数错误，请核对！"),
    INVALID_UPLOAD_FILE_IOERROR(350, "上传文件写入失败，请联系管理员！"),
    UNAUTHORIZED_LOGIN(555, "当前用户尚未登录或登录已超时，请重新登录！"),
    UNAUTHORIZED_USER_PERMISSION(501, "权限验证失败，当前用户不能访问该接口，请核对！"),
    NO_ACCESS_PERMISSION(502, "当前用户没有访问权限，请核对！"),
    NO_OPERATION_PERMISSION(503, "当前用户没有操作权限，请核对！"),

    PASSWORD_ERR(333, "密码错误，请重试！"),
    INVALID_USERNAME_PASSWORD(334, "用户名或密码错误，请重试！"),
    INVALID_ACCESS_TOKEN(335, "无效的用户访问令牌！"),
    INVALID_USER_STATUS(336, "用户状态错误，请刷新后重试！"),
    INVALID_TENANT_CODE(337, "指定的租户编码并不存在，请刷新后重试！"),
    INVALID_TENANT_STATUS(338, "当前租户为不可用状态，请刷新后重试！"),
    INVALID_USER_TENANT(339, "当前用户并不属于当前租户，请刷新后重试！"),

    HAS_CHILDREN_DATA(450, "数据验证失败，子数据存在，请刷新后重试！"),
    DATA_VALIDATED_FAILED(451, "数据验证失败，请核对！"),
    UPLOAD_FILE_FAILED(452, "文件上传失败，请联系管理员！"),
    DATA_SAVE_FAILED(453, "数据保存失败，请联系管理员！"),
    DATA_ACCESS_FAILED(454, "数据访问失败，请联系管理员！"),
    DATA_PERM_ACCESS_FAILED(455, "数据访问失败，您没有该页面的数据访问权限！"),
    DUPLICATED_UNIQUE_KEY(456, "数据保存失败，存在重复数据，请核对！"),
    DATA_NOT_EXIST(457, "数据不存在，请刷新后重试！"),
    DATA_PARENT_LEVEL_ID_NOT_EXIST(458, "数据验证失败，父级别关联Id不存在，请刷新后重试！"),
    DATA_PARENT_ID_NOT_EXIST(459, "数据验证失败，ParentId不存在，请核对！"),
    INVALID_RELATED_RECORD_ID(460, "数据验证失败，关联数据并不存在，请刷新后重试！"),
    INVALID_DATA_MODEL(461, "数据验证失败，无效的数据实体对象！"),
    INVALID_DATA_FIELD(462, "数据验证失败，无效的数据实体对象字段！"),
    INVALID_CLASS_FIELD(463, "数据验证失败，无效的类对象字段！"),
    SERVER_INTERNAL_ERROR(464, "服务器内部错误，请联系管理员！"),
    REDIS_CACHE_ACCESS_TIMEOUT(465, "Redis缓存数据访问超时，请刷新后重试！"),
    REDIS_CACHE_ACCESS_STATE_ERROR(466, "Redis缓存数据访问状态错误，请刷新后重试！");

    // 下面的枚举值为特定枚举值，即开发者可以根据自己的项目需求定义更多的非通用枚举值

    /**
     * 构造函数。
     *
     * @param errorMessage 错误消息。
     */
    ExceptionCodeEnum(int code, String errorMessage) {
        this.errorMessage = errorMessage;
        this.code = code;
    }

    /**
     * 错误信息。
     */
    private final String errorMessage;
    /**
     * 状态。
     */
    private final int code;

}
