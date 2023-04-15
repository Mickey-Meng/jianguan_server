package com.ruoyi.web.controller.jianguan;

import cn.hutool.core.lang.tree.Tree;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.object.ResponseBase;
import com.ruoyi.system.service.ISysMenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户PC端登录首页控制器
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/index")
public class IndexController extends BaseController {

    private final ISysMenuService sysMenuService;

    /**
     * 加载首页地图数据
     */
    @GetMapping("/loadMap")
    public ResponseBase loadMap() {
        // TODO 该函数逻辑待实现
        return ResponseBase.success(getLoginUser());
    }

    /**
     * 加载菜单和用户信息
     */
    @GetMapping("/loadMenuAndUserInfo")
    public ResponseBase getMenuAndUserInfo() {
        List<Tree<Long>> menuTree = sysMenuService.buildMenuTreeSelect(sysMenuService.selectMenuList(getUserId()));
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("menuTree", menuTree);
        dataMap.put("userInfo" ,getLoginUser());
        return ResponseBase.success(dataMap);
    }
}
