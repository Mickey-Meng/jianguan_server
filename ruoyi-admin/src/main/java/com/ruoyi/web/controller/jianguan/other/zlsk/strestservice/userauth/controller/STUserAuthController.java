package com.ruoyi.web.controller.jianguan.other.zlsk.strestservice.userauth.controller;

import com.ruoyi.web.controller.jianguan.other.zlsk.stframebase.controller.StControllerBase;
import com.ruoyi.web.controller.jianguan.other.zlsk.stsupporter.stctk.model.EntityList;
import com.ruoyi.web.controller.jianguan.other.zlsk.stsupporter.stctk.model.STData;
import com.ruoyi.web.controller.jianguan.other.zlsk.strestservice.userauth.model.*;
import com.ruoyi.web.controller.jianguan.other.zlsk.strestservice.userauth.service.*;
import org.springframework.beans.factory.annotation.*;
import javax.servlet.http.*;
import java.util.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping({ "/userauth" })
public class STUserAuthController extends StControllerBase
{
    @Autowired
    private AuthService authService;
    @Autowired
    private GroupService groupService;
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private SubSystemService sService;
    @Autowired
    private PositionService pService;
    @Autowired
    private UserOrganizeService uService;
    @Autowired
    private GyyService gServices;
    @Autowired
    private NoticeService nService;
    @Autowired
    private LogService lService;

   // @StLogin
    @RequestMapping({ "/login" })
    public STData login(final HttpServletResponse httpServletResponse, final Users users) throws Exception {
        return this.userService.login(httpServletResponse, users);
    }

    // @StLogin
    @RequestMapping({ "/login2" })
    public STData login2(final HttpServletResponse httpServletResponse, final Users users) throws Exception {
        final String n = users.getN();
        final String d = users.getD();
        users.setUsername(n);
        users.setPwd(d);
        return this.userService.login(httpServletResponse, users);
    }

    @RequestMapping({ "/getUserInfo" })
    public STData getUserInfo(final HttpServletRequest request, @RequestParam(value = "id", required = false, defaultValue = "0") final int userId, @RequestParam(value = "type", defaultValue = "web") final String type, @RequestParam(value = "mobile", defaultValue = "-1") final int mobile, final String systemName) throws Exception {
        return this.userService.getUserInfo(request, userId, type, mobile, systemName);
    }

    @RequestMapping({ "/changePWD" })
    public STData<Integer> changePWD(final Users users) {
        return this.userService.changePWD(users);
    }

    @RequestMapping({ "/selectDic" })
    public STData selectDic(final Dic dic) {
        return this.authService.selectDic(dic);
    }

    @RequestMapping({ "/selectDicByKey" })
    public STData selectDicByKey(final String parentKey, final String key, final String value) {
        return this.authService.selectByKey(parentKey, key, value);
    }

    @RequestMapping({ "/selectDicByParentKey" })
    public STData selectDicByParentKey(final String keyStr) {
        return this.authService.selectDicByParentKey(keyStr);
    }

    @RequestMapping({ "/insertDic" })
    public STData insertDic(final Dic dic) {
        return this.authService.insertDic(dic);
    }

    @RequestMapping({ "/updateDic" })
    public STData updateDic(final Dic dic) {
        return this.authService.updateDic(dic);
    }

    @RequestMapping({ "/deleteDic" })
    public STData deleteDic(final int[] ids) throws Exception {
        return this.authService.deleteDic(ids);
    }

    @RequestMapping({ "/addLog" })
    public STData<Integer> addLog(final Userloghistory userloghistory) throws Exception {
        return this.userService.addLog(userloghistory);
    }

    @RequestMapping({ "/selectLog" })
    public STData selectLog(final Userloghistory userloghistory) {
        return this.userService.selectLog(userloghistory);
    }

    @RequestMapping({ "/deleteLog" })
    public STData<Integer> deleteLog(final String ids) throws Exception {
        return this.userService.deleteLog(ids);
    }

    @RequestMapping({ "/selectUser" })
    public STData<EntityList<Map<String, Object>>> selectUser(final Users users, final Groups groups) {
        return this.userService.select(users, groups);
    }

    @RequestMapping({ "/insertUser" })
    public STData<Integer> insertUser(final Users user, final Userinfo userinfo, final UserGroup userGroup) throws Exception {
        return this.userService.insert(user, userinfo, userGroup);
    }

    @RequestMapping({ "/updateUser" })
    public STData<Integer> updateUser(final Users user) throws Exception {
        return this.userService.update(user);
    }

    @RequestMapping({ "/updateUserState" })
    public STData<Integer> updateState(final Users users) throws Exception {
        return this.userService.updateState(users);
    }

    @RequestMapping({ "/selectByGroup" })
    public STData<EntityList<Map<String, Object>>> selectByGroup(final Users users) {
        return this.userService.selectByGroup(users);
    }

    @RequestMapping({ "/selectAllByGroupId" })
    public STData<EntityList<Map<String, Object>>> selectByGroupId(final Integer groupId) {
        return this.userService.selectByGroupId(groupId);
    }

    @RequestMapping({ "/addUserRole" })
    public STData<Integer> addUserRole(@RequestParam(required = false) final int userId, @RequestParam(required = false) final int[] roleId) throws Exception {
        return this.userService.addUserRole(userId, roleId);
    }

    @RequestMapping({ "/deleteUserRole" })
    public STData<Integer> deleteUserRole(@RequestParam(required = false) final int userId) throws Exception {
        return this.userService.deleteUserRole(userId);
    }

    @RequestMapping({ "/updateUserGroup" })
    public STData<Integer> updateUserGroup(final UserGroup userGroup) throws Exception {
        return this.userService.updateUserGroup(userGroup);
    }

    @RequestMapping({ "/selectUserRole" })
    public STData<EntityList<Map<String, Object>>> selectUserRole(final UserRole userRole) {
        return this.userService.selectUserRole(userRole);
    }

    @RequestMapping({ "/getRoleCode" })
    public STData<Map<String, Object>> getRoleCode(@RequestParam(required = false) final Integer id) {
        return this.roleService.getCode(id);
    }

    @RequestMapping({ "/selectRole" })
    public STData<EntityList<Map<String, Object>>> selectRole(final Roles role) {
        return (STData<EntityList<Map<String, Object>>>)new STData((Object)this.roleService.select(role));
    }

    @RequestMapping({ "/insertRole" })
    public STData<Integer> insertRole(final Roles role) throws Exception {
        return this.roleService.insertRole(role);
    }

    @RequestMapping({ "/updateRole" })
    public STData<Integer> updateRole(final Roles role) throws Exception {
        return this.roleService.updateRole(role);
    }

    @RequestMapping({ "/deleteRole" })
    public STData<Integer> deleteRole(@RequestParam(required = false) final int[] roleId) throws Exception {
        return this.roleService.deleteRole(roleId);
    }

    @RequestMapping({ "/insertRoleMenu" })
    public STData<Integer> insertRoleMenu(@RequestParam(required = false) final int roleId, @RequestParam(required = false) final int[] menuId) throws Exception {
        return this.roleService.insertRoleMenu(roleId, menuId);
    }

    @RequestMapping({ "/deleteRoleMenu" })
    public STData<Integer> deleteRoleMenu(final Userauth userauth) throws Exception {
        return this.roleService.deleteRoleMenu(userauth);
    }

    @RequestMapping({ "/selectRoleMenu" })
    public STData<EntityList<Map<String, Object>>> selectRoleMenu(final Userauth userauth) {
        return (STData<EntityList<Map<String, Object>>>)new STData((Object)this.roleService.selectRoleMenu(userauth));
    }

    @RequestMapping({ "/insertFunRole" })
    public STData<Integer> insertFunRole(@RequestParam(required = false) final int funId, @RequestParam(required = false) final int[] roleId) throws Exception {
        return this.roleService.insertFunRole(funId, roleId);
    }

    @RequestMapping({ "/deleteFunRole" })
    public STData<Integer> deleteFunRole(final Userauth userauth) throws Exception {
        return this.roleService.deleteFunRole(userauth);
    }

    @RequestMapping({ "/selectFunRole" })
    public STData<EntityList<Map<String, Object>>> selectFunRole(final Fun fun) {
        return (STData<EntityList<Map<String, Object>>>)new STData((Object)this.roleService.selectFunRole(fun));
    }

    @RequestMapping({ "/selectUserByRole" })
    public STData<List<Map<String, Object>>> selectUserByRole(final String code, final int groupId) {
        return this.roleService.selectUserByRole(code, groupId);
    }

    @RequestMapping({ "/getGroupCode" })
    public STData getGroupCode(@RequestParam(required = false) final Integer id) {
        return this.groupService.getCode(id);
    }

    @RequestMapping({ "/selectGroup" })
    public STData<EntityList<Map<String, Object>>> selectGroup(final Groups group) {
        return this.groupService.select(group);
    }

    @RequestMapping({ "/insertGroup" })
    public STData<Integer> insertGroup(final Groups group) throws Exception {
        return this.groupService.insert(group);
    }

    @RequestMapping({ "/addGroup" })
    public STData<Integer> addGroup(final Groups group) throws Exception {
        return this.groupService.addGroup(group);
    }

    @RequestMapping({ "updateGroup" })
    public STData<Integer> updateGroup(final Groups groups) throws Exception {
        return this.groupService.update(groups);
    }

    @RequestMapping({ "/deleteGroup" })
    public STData<Integer> deleteGroup(@RequestParam(required = false) final int[] groupId) throws Exception {
        return this.groupService.delete(groupId);
    }

    @RequestMapping({ "/deleteGroup1" })
    public STData<Integer> deleteGroup1(@RequestParam(required = false) final int[] groupId) throws Exception {
        return this.groupService.delete1(groupId);
    }

    @RequestMapping({ "/selectFun" })
    public STData<EntityList<Map<String, Object>>> selectFuns(final Fun fun) {
        return (STData<EntityList<Map<String, Object>>>)new STData((Object)this.authService.selectFun(fun));
    }

    @RequestMapping({ "/selectMenu" })
    public STData<EntityList<Map<String, Object>>> selectMenus(final Menu menu) {
        return (STData<EntityList<Map<String, Object>>>)new STData((Object)this.authService.selectMenu(menu));
    }

    @RequestMapping({ "/selectService" })
    public STData<EntityList<Map<String, Object>>> selectServices(final Service service) {
        return (STData<EntityList<Map<String, Object>>>)new STData((Object)this.authService.selectService(service));
    }

    @RequestMapping({ "/insertService" })
    public STData<Integer> insertService(final Service service) throws Exception {
        return this.authService.insertService(service);
    }

    @RequestMapping({ "/deleteService" })
    public STData<Integer> deleteService(@RequestParam(required = false) final int serviceId) throws Exception {
        return this.authService.deleteService(serviceId);
    }

    @RequestMapping({ "/insertFun" })
    public STData<Integer> insertFun(final Fun fun) throws Exception {
        return this.authService.insertFun(fun);
    }

    @RequestMapping({ "/deleteFun" })
    public STData<Integer> deleteFun(@RequestParam(required = false) final int funId) throws Exception {
        return this.authService.deleteFun(funId);
    }

    @RequestMapping({ "/insertMenu" })
    public STData<Integer> insertMenu(final Menu menu, final String sms) throws Exception {
        return this.authService.insertMenu(menu, sms);
    }

    @RequestMapping({ "/updateMenu" })
    public STData<Integer> updateMenu(final Menu menu, final String sms) throws Exception {
        return this.authService.updateMenu(menu, sms);
    }

    @RequestMapping({ "/deleteMenu" })
    public STData<Integer> deleteMenu(@RequestParam(required = false) final int[] menuId) throws Exception {
        return this.authService.deleteMenu(menuId);
    }

    @RequestMapping({ "/updateLonlat" })
    public STData<Integer> updateLonlat(@RequestParam(required = false) final int userid, final String lonlat) {
        return this.authService.updateLonlat(userid, lonlat);
    }

    @RequestMapping({ "/getLonlat" })
    public STData<List<Map<String, Object>>> getLonlat(final String ids) {
        return this.authService.getLonlat(ids);
    }

    @RequestMapping({ "/getUserByFun" })
    public STData getUserByFun(final Fun fun) {
        return this.roleService.getUserByFun(fun);
    }

    @RequestMapping({ "/getUsersByRole" })
    public STData getUsersByRole(@RequestParam(required = false) final Integer groupId, final String roleCode) {
        return this.userService.getUsersByRole(groupId, roleCode);
    }

    @RequestMapping({ "/addOperationLog" })
    public STData<Integer> addOperationLog(final OperationLog operationLog) throws Exception {
        return this.userService.addOperationLog(operationLog);
    }

    @RequestMapping({ "/selectOperationLog" })
    public STData selectOperationLog(final OperationLog operationLog) {
        return this.userService.selectOperationLog(operationLog);
    }

    @RequestMapping({ "/getUsersByRoleAndGroup" })
    public STData getUsersByRoleAndGroup(final String visible, final String roleCode) {
        return this.userService.getUsersByRoleAndGroup(visible, roleCode);
    }

    @RequestMapping(value = { "/selectSubSystem" }, method = { RequestMethod.GET })
    public STData<EntityList<Map<String, Object>>> selectSubSystem(final SubSystem sys) {
        return this.sService.select(sys);
    }

    @RequestMapping(value = { "/selectSubsystemType" }, method = { RequestMethod.GET })
    public STData<EntityList<Map<String, Object>>> selectSubsystemType(final SubSystem sys) {
        return this.sService.selectSubsystemType(sys);
    }

    @RequestMapping(value = { "/addSubSystem" }, method = { RequestMethod.POST })
    public STData<Integer> addSubSystem(final SubSystem sys) throws Exception {
        return this.sService.insert(sys);
    }

    @RequestMapping(value = { "updateSubSystem" }, method = { RequestMethod.POST })
    public STData<Integer> updateSubSystem(final SubSystem sys) throws Exception {
        return this.sService.update(sys);
    }

    @RequestMapping(value = { "/delSubSystem" }, method = { RequestMethod.GET })
    public STData<Integer> delSubSystem(final String id) throws Exception {
        return this.sService.delete(id);
    }

    @RequestMapping(value = { "/selectUserOrganize" }, method = { RequestMethod.GET })
    public STData<EntityList<Map<String, Object>>> selectUserOrganize(final UserOrganize u) {
        return this.uService.select(u);
    }

    @RequestMapping(value = { "/addUserOrganize" }, method = { RequestMethod.POST })
    public STData<Integer> addUserOrganize(final UserOrganize u) throws Exception {
        return this.uService.insert(u);
    }

    @RequestMapping(value = { "updateUserOrganize" }, method = { RequestMethod.POST })
    public STData<Integer> updateUserOrganize(final UserOrganize u) throws Exception {
        return this.uService.update(u);
    }

    @RequestMapping(value = { "/delUserOrganize" }, method = { RequestMethod.GET })
    public STData<Integer> delUserOrganize(final String id) throws Exception {
        return this.uService.delete(id);
    }

    @RequestMapping(value = { "/selectPosition" }, method = { RequestMethod.GET })
    public STData<EntityList<Map<String, Object>>> selectPosition(final Position p) {
        return this.pService.select(p);
    }

    @RequestMapping(value = { "/addPosition" }, method = { RequestMethod.POST })
    public STData<Integer> addPosition(final Position p) throws Exception {
        return this.pService.insert(p);
    }

    @RequestMapping(value = { "updatePosition" }, method = { RequestMethod.POST })
    public STData<Integer> updatePosition(final Position p) throws Exception {
        return this.pService.update(p);
    }

    @RequestMapping(value = { "/delPosition" }, method = { RequestMethod.GET })
    public STData<Integer> delPosition(final String id) throws Exception {
        return this.pService.delete(id);
    }

    @RequestMapping({ "/selectGyy" })
    public STData<EntityList<Map<String, Object>>> selectGyy(final Gyy g) {
        return this.gServices.select(g);
    }

    @RequestMapping(value = { "/addGyy" }, method = { RequestMethod.POST })
    public STData<Integer> addGyy(final Gyy g) throws Exception {
        return this.gServices.insert(g);
    }

    @RequestMapping(value = { "updateGyy" }, method = { RequestMethod.POST })
    public STData<Integer> updateGyy(final Gyy g) throws Exception {
        return this.gServices.update(g);
    }

    @RequestMapping(value = { "/delGyy" }, method = { RequestMethod.GET })
    public STData<Integer> delGyy(final String id) throws Exception {
        return this.gServices.delete(id);
    }

    @RequestMapping(value = { "/selectNotice" }, method = { RequestMethod.GET })
    public STData<EntityList<Map<String, Object>>> selectNotice(final Notice n) {
        return this.nService.select(n);
    }

    @RequestMapping(value = { "/addNotice" }, method = { RequestMethod.POST })
    public STData<Integer> addNotice(final Notice n) throws Exception {
        return this.nService.insert(n);
    }

    @RequestMapping(value = { "updateNotice" }, method = { RequestMethod.POST })
    public STData<Integer> updateNotice(final Notice n) throws Exception {
        return this.nService.update(n);
    }

    @RequestMapping(value = { "/delNotice" }, method = { RequestMethod.GET })
    public STData<Integer> delNotice(final String id) throws Exception {
        return this.nService.delete(id);
    }

    @RequestMapping(value = { "/selectLog" }, method = { RequestMethod.GET })
    public STData<EntityList<Map<String, Object>>> selectLog(final Log l) {
        return this.lService.select(l);
    }

    @RequestMapping(value = { "/addLog" }, method = { RequestMethod.POST })
    public STData<Integer> addLog(final Log l) throws Exception {
        return this.lService.insert(l);
    }

    @RequestMapping(value = { "updateLog" }, method = { RequestMethod.POST })
    public STData<Integer> updateLog(final Log l) throws Exception {
        return this.lService.update(l);
    }

    @RequestMapping(value = { "/delLog" }, method = { RequestMethod.GET })
    public STData<Integer> delLog(final String id) throws Exception {
        return this.lService.delete(id);
    }
}
