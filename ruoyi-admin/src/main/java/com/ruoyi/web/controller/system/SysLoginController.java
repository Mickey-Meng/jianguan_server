package com.ruoyi.web.controller.system;

import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.domain.entity.SsFUsers;
import com.ruoyi.common.core.domain.entity.SysMenu;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.domain.model.LoginBody;
import com.ruoyi.common.core.domain.model.SmsLoginBody;
import com.ruoyi.common.helper.LoginHelper;
import com.ruoyi.common.utils.RsaEncrypt;
import com.ruoyi.common.utils.sm4.Sm4Util;
import com.ruoyi.system.domain.vo.RouterVo;
import com.ruoyi.system.service.ISysMenuService;
import com.ruoyi.system.service.ISysUserService;
import com.ruoyi.system.service.SysLoginService;
import com.ruoyi.system.service.SysPermissionService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.bouncycastle.util.encoders.Hex;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import java.nio.charset.StandardCharsets;
import java.util.*;

import static com.ruoyi.common.constant.RoleConstants.SYS_ROLE_SYSTEM;

/**
 * 登录验证
 *
 * @author Lion Li
 */
@Validated
@RequiredArgsConstructor
@RestController
public class SysLoginController {

    private final SysLoginService loginService;
    private final ISysMenuService menuService;
    private final ISysUserService userService;
    private final SysPermissionService permissionService;
    private static String defaultKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAl3xudmrPJr5r/aM6RlKO0UifXuJCzdzn\n" +
        "ZZ6AW3kR8iAZQ4pBSgyNQSX9OUainzEhlPHSovnAcFxLF+2gJubrwajjWo3v82GNVKIFUM15M6zs\n" +
        "N/j2s8l3EVPMJ/i2zRrypztzKtHoGRoKH1rG0SsHCBY94wqK2HPM4tKj7pJKi0QgR6l/ahBNP2c1\n" +
        "hJNjE2clyJzRjuXqzgDNaA+IeuLFmX7DmVHZjWxxD7KlQhympl7JQgt5L58SAhR7S6g2ConWwLFu\n" +
        "2oPTTUGak+zG5VqJmPRYbbgK8xwQ7mCZejqkC2tllmK/RRm4YQIUuBWW6Q26fHc6tFdNMx4LWP/v\n" +
        "LlMlkwIDAQAB";


    /**
     * 登录方法
     *
     * @param loginBody 登录信息
     * @return 结果
     */
    @Anonymous
    @PostMapping("/login")
    public R<Map<String, Object>> login(@Validated @RequestBody LoginBody loginBody) {
        Map<String, Object> ajax = new HashMap<>();
        // 生成令牌
        String token = loginService.login(loginBody.getUsername(), loginBody.getPassword(), loginBody.getCode(),
            loginBody.getUuid());
        ajax.put(Constants.TOKEN, token);

        String ssoToken = RsaEncrypt.encrypt("Alice", defaultKey);
        ajax.put("ssoToken",ssoToken);
        return R.ok(ajax);
    }


    @ResponseBody
    @GetMapping("/validateSsoToken/{tokenStr}")
    @ApiOperation(value = "校验token")
    public R<Map<String, Object>> checkToken(@PathVariable("tokenStr") String tokenStr) throws Exception {
//        tokenStr = "b69b650e5839ba8293fce8fcd0a89a4d8931a59ae51b682a054685f6e5babdad";
        byte[] key = Sm4Util.hexTobytes("51d95b1dc43a9faaad0570f81c755fcc");
        byte[] input = Hex.decode(tokenStr);
        byte[] output = Sm4Util.decryptEcbPkcs5Padding(input, key);
        String s = new String(output, StandardCharsets.UTF_8);

        String[] split = s.split("&");

        String token = loginService.login(split[0], split[1], null);

        Map<String, Object> ajax = new HashMap<>();

        ajax.put(Constants.TOKEN, token);

        String ssoToken = RsaEncrypt.encrypt("Alice", defaultKey);
        ajax.put("ssoToken",ssoToken);
        return R.ok(ajax);
    }

    /**
     * 短信登录(示例)
     *
     * @param smsLoginBody 登录信息
     * @return 结果
     */
    @Anonymous
    @PostMapping("/smsLogin")
    public R<Map<String, Object>> smsLogin(@Validated @RequestBody SmsLoginBody smsLoginBody) {
        Map<String, Object> ajax = new HashMap<>();
        // 生成令牌
        String token = loginService.smsLogin(smsLoginBody.getPhonenumber(), smsLoginBody.getSmsCode());
        ajax.put(Constants.TOKEN, token);
        return R.ok(ajax);
    }

    /**
     * 小程序登录(示例)
     *
     * @param xcxCode 小程序code
     * @return 结果
     */
    @Anonymous
    @PostMapping("/xcxLogin")
    public R<Map<String, Object>> xcxLogin(@NotBlank(message = "{xcx.code.not.blank}") String xcxCode) {
        Map<String, Object> ajax = new HashMap<>();
        // 生成令牌
        String token = loginService.xcxLogin(xcxCode);
        ajax.put(Constants.TOKEN, token);
        return R.ok(ajax);
    }

    /**
     * 退出登录
     */
    @Anonymous
    @PostMapping("/logout")
    public R<Void> logout() {
        loginService.logout();
        return R.ok("退出成功");
    }

    /**
     * 获取用户信息
     *
     * @return 用户信息
     */
    @GetMapping("getInfo")
    public R<Map<String, Object>> getInfo() {
        SysUser user = userService.selectUserById(LoginHelper.getUserId());
        // 角色集合
        Set<String> roles = permissionService.getRolePermission(user);
        // 权限集合
        Set<String> permissions = permissionService.getMenuPermission(user);
        Map<String, Object> ajax = new HashMap<>();
        ajax.put("user", user);
        ajax.put("roles", roles);
        ajax.put("permissions", permissions);
        return R.ok(ajax);
    }

    /**
     * 获取路由信息
     *
     * @return 路由信息
     */
    @GetMapping("getRouters")
    public R<List<RouterVo>> getRouters() {
        Long userId = LoginHelper.getUserId();
        List<SysMenu> menus = menuService.selectMenuTreeByUserId(userId, "0");
        // 如果是超级管理员，则不过滤数据
        if (!LoginHelper.isAdmin()) {
            Iterator<SysMenu> iterator = menus.iterator();
            while (iterator.hasNext()) {
                SysMenu next = iterator.next();
                String name = next.getMenuCode();
                if (SYS_ROLE_SYSTEM.equalsIgnoreCase(name)) {
                    iterator.remove();
                }
            }
        }
        return R.ok(menuService.buildMenus(menus));
    }
}
