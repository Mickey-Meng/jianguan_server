package com.ruoyi.common.core.domain.object;

import com.ruoyi.common.utils.context.ContextUtil;
import lombok.Data;
import lombok.ToString;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @mm
 * 基于Jwt，用于前后端传递的令牌对象。
 *
 * @author Jerry
 * @date 2021-06-06
 */
@Data
@ToString
public class TokenData {

    /**
     * 在HTTP Request对象中的属性键。
     */
    public static final String REQUEST_ATTRIBUTE_NAME = "tokenData";
    /**
     * 用户Id。
     */
    private Long userId;
    /**
     * 用户所属角色。多个角色之间逗号分隔。
     */
    private String roleIds;
    /**
     * 用户所在部门Id。
     * 仅当系统支持uaa时可用，否则可以直接忽略该字段。保留该字段是为了保持单体和微服务通用代码部分的兼容性。
     */
    private Long deptId;
    /**
     * 用户所属岗位Id。多个岗位之间逗号分隔。仅当系统支持岗位时有值。
     */
    private String postIds;
    /**
     * 用户的部门岗位Id。多个岗位之间逗号分隔。仅当系统支持岗位时有值。
     */
    private String deptPostIds;
    /**
     * 租户Id。
     * 仅当系统支持uaa时可用，否则可以直接忽略该字段。保留该字段是为了保持单体和微服务通用代码部分的兼容性。
     */
    private Long tenantId;
    /**
     * 是否为超级管理员。
     */
    private Boolean isAdmin;
    /**
     * 用户登录名。
     */
    private String loginName;
    /**
     * 用户显示名称。
     */
    private String showName;
    /**
     * 设备类型。参考 AppDeviceType。
     */
    private Integer deviceType;
    /**
     * 标识不同登录的会话Id。
     */
    private String sessionId;
    /**
     * 访问uaa的授权token。
     * 仅当系统支持uaa时可用，否则可以直接忽略该字段。保留该字段是为了保持单体和微服务通用代码部分的兼容性。
     */
    private String uaaAccessToken;
    /**
     * 数据库路由键(仅当水平分库时使用)。
     */
    private Integer datasourceRouteKey;
    /**
     * 登录IP。
     */
    private String loginIp;
    /**
     * 登录时间。
     */
    private Date loginTime;
    /**
     * 登录头像地址。
     */
    private String headImageUrl;

    /**
     * 将令牌对象添加到Http请求对象。
     *
     * @param tokenData 令牌对象。
     */
    public static void addToRequest(TokenData tokenData) {
        HttpServletRequest request = ContextUtil.getHttpRequest();
        request.setAttribute(TokenData.REQUEST_ATTRIBUTE_NAME, tokenData);
    }

    /**
     * 从Http Request对象中获取令牌对象。
     *
     * @return 令牌对象。
     */
    public static TokenData takeFromRequest() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) {
            return null;
        }
        HttpServletRequest request = ContextUtil.getHttpRequest();
        return (TokenData) request.getAttribute(REQUEST_ATTRIBUTE_NAME);
    }
}