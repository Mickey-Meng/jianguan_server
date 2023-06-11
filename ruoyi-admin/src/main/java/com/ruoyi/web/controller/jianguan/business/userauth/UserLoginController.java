package com.ruoyi.web.controller.jianguan.business.userauth;

import cn.hutool.http.HttpStatus;
import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.common.annotation.AuthIgnore;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.core.domain.entity.PowerData;
import com.ruoyi.common.core.domain.entity.SysMenu;
import com.ruoyi.common.core.domain.model.LoginBody;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.core.domain.object.ResponseBase;
import com.ruoyi.common.utils.JwtUtil;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.vo.RouterVo;
import com.ruoyi.system.service.ISysMenuService;
import com.ruoyi.system.service.SysLoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ：mengzhengbin
 * @date ：Created in 2023/4/12 18:52
 * @version: V1.0
 * @description: 前端用户登录
 **/
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Api(value="前端用户登录")
public class UserLoginController {

    private final SysLoginService loginService;
    private final ISysMenuService menuService;
    /**
     * 定稿版本：前端用户登录
     * @param loginBody
     * @return
     */
    @PostMapping("/doLogin")
    @ResponseBody
    @AuthIgnore
    @Anonymous
    @ApiOperation(value = "登录，支持单个用户对应多个组织机构（2022-03-08）")
    public ResponseBase doLogin(@Validated @RequestBody LoginBody loginBody){
        LoginUser loginUser = loginService.loginForUser(loginBody);
        if (StringUtils.isEmpty(loginUser.getRoleIds())) {
            return new ResponseBase(HttpStatus.HTTP_UNAUTHORIZED, "该角色没有配置角色权限,请到运维系统进行配置!");
        }
        // TODO 待完善: 该角色没有配置工区, 请到管理员账号下进行配置!
        Map<String, Object> ajaxDataMap = new HashMap<>();
        ajaxDataMap.put(Constants.LOGIN_USER_INFO, loginUser);

        PowerData powerData = PowerData.builder()
            .id(loginUser.getUserId().intValue())
            .roleIds(loginUser.getRoleIds())
            .build();
        //转token
        String jwtToken = JwtUtil.sign(powerData, JwtUtil.SSO_TIME);
        ajaxDataMap.put("jwtToken", jwtToken);

        List<SysMenu> menus = menuService.selectWebMenuTreeByUserId(loginUser.getUserId(), loginBody.getSourceType());
        List<RouterVo>  routerVos =  menuService.buildMenus(menus);
        ajaxDataMap.put("menus", routerVos);

        return new ResponseBase(HttpStatus.HTTP_OK, "登录成功", ajaxDataMap);
    }
}
