package com.ruoyi.web.controller.jianguan.business.userauth;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.http.HttpStatus;
import com.google.common.collect.Lists;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.PageQuery;
import com.ruoyi.common.core.domain.entity.SysDept;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.domain.object.ResponseBase;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.helper.LoginHelper;
import com.ruoyi.jianguan.business.userauth.domain.dto.UserProjectDto;
import com.ruoyi.jianguan.business.userauth.domain.entity.UserProject;
import com.ruoyi.jianguan.business.userauth.service.IUserProjectService;
import com.ruoyi.system.service.ISysDeptService;
import com.ruoyi.system.service.ISysMenuService;
import com.ruoyi.system.service.ISysUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户PC端登录首页控制器
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/sysManage/permission")
public class PermissionController extends BaseController {

    private final ISysMenuService sysMenuService;
    private final ISysDeptService sysDeptService;
    private final ISysUserService userService;
    private final IUserProjectService userProjectService;

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

    @GetMapping("/deptTree")
    public ResponseBase getDeptTree(SysDept sysDept) {
        List<Tree<Long>> deptTreeList = sysDeptService.selectDeptTreeList(new SysDept());
        return ResponseBase.success(deptTreeList);
    }

    /**
     * 获取用户列表
     */
    @GetMapping("/getUserListByDeptId")
    public TableDataInfo<SysUser> getUserListByDeptId(SysUser user, PageQuery pageQuery) {
        return userService.selectPageUserList(user, pageQuery);
    }

    /**
     * 添加工区权限
     * @param userProjectDto
     * @return
     */
    @ResponseBody
    @PostMapping("/addWorkArea")
    public ResponseBase addWorkArea(@RequestBody UserProjectDto userProjectDto) {
        //当该用户的roleid不是2-管理员,不允许添加
        //TODO , #8 ,yangaogao 20230425当前判断逻辑为判定拥有的角色中是否有admin，但实际上真正可以做添加工区的可能不止admin，还有其他角色。因此需要完善其他角色。。
        if (!LoginHelper.getLoginUser().getRolePermission().contains("admin")  && !LoginHelper.getLoginUser().getRolePermission().contains("gly") ) {
            return new ResponseBase(HttpStatus.HTTP_INTERNAL_ERROR, "用户添加组织机构失败,该用户没有权限配置组织机构!");
        }
        boolean isSuccess = true;
        if (CollectionUtil.isNotEmpty(userProjectDto.getUserIds())) {
            List<UserProject> userProjectList = Lists.newArrayList();
            userProjectDto.getUserIds().stream().forEach(userId -> {
                userProjectDto.getWorkAreaIds().stream().forEach(workAreaId -> {
                    userProjectList.add(
                        UserProject.builder()
                            .userId(userId)
                            .workAreaId(workAreaId)
                            .build()
                    );
                });
            });
            // 先删除用户对应的项目下所有权限
            userProjectService.deleteWorkAreaByUserIds(userProjectDto.getUserIds(), userProjectDto.getProjectId());
            //再添加相应权限
            isSuccess = userProjectService.batchAddWorkArea(userProjectList);
        }
        return new ResponseBase(isSuccess ? HttpStatus.HTTP_OK : HttpStatus.HTTP_INTERNAL_ERROR,
            "添加用户工区权限" + (isSuccess ? "成功!" : "失败!"));
    }

    /**
     * 根据用户ID查询其关联的工区信息
     * @param userId
     * @return
     */
    @GetMapping("/getWorkAreaByUserId/{userId}")
    public ResponseBase getWorkAreaByUserId(@PathVariable("userId") Integer userId) {
        return ResponseBase.success(userProjectService.getWorkAreaByUserId(userId));
    }
}
