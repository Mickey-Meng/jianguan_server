package com.ruoyi.web.controller.jianguan.other.zlsk.strestservice.userauth.service;

import com.amazonaws.util.StringUtils;
import com.ruoyi.web.controller.jianguan.other.zlsk.strestservice.userauth.mapper.*;
import com.ruoyi.web.controller.jianguan.other.zlsk.strestservice.userauth.model.*;
import com.ruoyi.web.controller.jianguan.other.zlsk.stsupporter.stctk.model.EntityList;
import com.ruoyi.web.controller.jianguan.other.zlsk.stsupporter.stctk.model.STData;
import com.ruoyi.web.controller.jianguan.other.zlsk.stsupporter.stctk.util.DateUtil;
import com.ruoyi.web.controller.jianguan.other.zlsk.stsupporter.stctk.util.encryption.Md5Encryption;
import com.ruoyi.web.controller.jianguan.other.zlsk.strestservice.userauth.util.ZListUtil;
import org.springframework.beans.factory.annotation.*;
import java.util.concurrent.*;

import javax.servlet.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.*;
import java.util.stream.*;
import java.net.*;
import java.util.*;

@Service
public class UserService extends RoleService
{
    @Autowired
    private UsersMapper usersMapper;
    @Autowired
    private UserinfoMapper userinfoMapper;
    @Autowired
    private GroupsMapper groupsMapper;
    @Autowired
    private RolesMapper rolesMapper;
    @Autowired
    private MenuMapper menuMapper;
    @Autowired
    private FunMapper funMapper;
    @Autowired
    private UserGroupMapper userGroupMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;
    @Autowired
    private UserauthMapper userauthMapper;
    @Autowired
    private UserloghistoryMapper userloghistoryMapper;
    @Autowired
    private AuthService authservice;
    @Autowired
    private OperationLogMapper operationlogMapper;
    private static final ConcurrentHashMap<String, Long> loginMap;
    private static final long LOGIN_AGAIN_INTERVAL = 86400000L;

    public STData login(final HttpServletResponse httpServletResponse, Users users) throws Exception {
        final STData stData = new STData();
        final String un = users.getUsername();
        final String lck = un + "_c";
        final String ltk = un + "_t";
        final Long t = UserService.loginMap.get(ltk);
        if (t != null && System.currentTimeMillis() - t < 86400000L) {
            stData.setMeow(-5001);
            stData.setMsg("\u7528\u6237\u5df2\u9501\u5b9a\uff0c\u8bf724\u5c0f\u65f6\u540e\u518d\u8bd5\uff01\u9501\u5b9a\u65f6\u95f4: " + loginMap.get(ltk));
            return stData;
        }
        if (StringUtils.isNullOrEmpty(users.getUsername()) && StringUtils.isNullOrEmpty(users.getPhone())) {
            stData.setMeow(-5001);
            stData.setMsg("\u8bf7\u8f93\u5165\u7528\u6237\u540d\u6216\u624b\u673a\u53f7\uff01");
            return stData;
        }
        users.setPwd(Md5Encryption.getMD5(users.getPwd()));
        List<Map<String, Object>> user = this.usersMapper.login(users);
        if (user == null || user.size() == 0) {
            users = new Users();
            users.setPhone(users.getUsername());
            users.setPwd(users.getPwd());
            user = this.usersMapper.login(users);
            if (user == null || user.size() == 0) {
                Long lc = UserService.loginMap.get(lck);
                if (lc == null || lc < 1L) {
                    lc = 1L;
                }
                else {
                    ++lc;
                }
                UserService.loginMap.put(lck, lc);
                if (lc > 4L) {
                    final long ct = System.currentTimeMillis();
                    UserService.loginMap.put(ltk, ct);
                    stData.setMsg("\u7528\u6237\u5df2\u9501\u5b9a\uff0c\u8bf724\u5c0f\u65f6\u540e\u518d\u8bd5\uff01\u9501\u5b9a\u65f6\u95f4: " + ct);
                }
                else {
                    stData.setMsg("\u7528\u6237\u540d\u6216\u5bc6\u7801\u4e0d\u6b63\u786e\uff0c\u518d\u8f93\u5165" + (5L - lc) + "\u6b21\u9519\u8bef\u540e\u8d26\u53f7\u4f1a\u88ab\u9501\u5b9a\uff01");
                }
                stData.setMeow(-5001);
                return stData;
            }
        }
        UserService.loginMap.remove(lck);
        UserService.loginMap.remove(ltk);
        final Map<String, Object> ui = user.get(0);
        final Map<String, String> info = new HashMap<String, String>(2);
        info.put("ID", String.valueOf(ui.get("ID")));
        info.put("USERNAME", String.valueOf(ui.get("USERNAME")));
        stData.setData(info);
        stData.setMeow(0);
        stData.setMsg("\u767b\u9646\u6210\u529f");
        return stData;
    }

    public STData getUserInfo(final HttpServletRequest request, final int userId, final String type, final int mobile, final String systemName) {
        final Map<String, Object> result = new HashMap<String, Object>();
        final STData stData = new STData();
        if (systemName == null || systemName.isEmpty()) {
            stData.setMeow(-3001);
            stData.setMsg("\u7f3a\u5c11\u7cfb\u7edf\u540d\u79f0");
            stData.setData(result);
            return stData;
        }
        final String token = request.getHeader("token");
        int id = 0;
        if (token == null || token.isEmpty()) {
            id = userId;
        }
        else {
            id = Integer.parseInt("");
        }
        final Users users = new Users();
        users.setId(id);
        final Map<String, Object> userInfo = this.usersMapper.select(users).get(0);
        final UserRole userRole = new UserRole();
        if(null != userInfo){
            userRole.setUserid(Integer.parseInt(userInfo.get("user_id").toString()));
            if (mobile != -1) {
                this.authservice.updateMobile(Integer.parseInt(userInfo.get("user_id").toString()), mobile);
            }
        }
        final Map<String, Object> userAuth = this.selectUserAuth(userRole, type, systemName);
        final List<Map<String, Object>> roles = (List<Map<String, Object>>)((EntityList)this.selectUserRole(userRole).getData()).getGetMe();
        result.put("userInfo", userInfo);
        result.put("roles", roles);
        result.put("userAuth", userAuth);
        stData.setMeow(0);
        stData.setMsg("\u7528\u6237\u6570\u636e\u83b7\u53d6\u6210\u529f");
        stData.setData(result);
        return stData;
    }

    public STData<Integer> changePWD(final Users users) {
        final STData s = new STData();
        if (users.getId() == null) {
            s.setMsg("\u7528\u6237ID\u4e0d\u80fd\u4e3a\u7a7a");
            s.setMeow(-3001);
            return (STData<Integer>)s;
        }
        if (users.getPwd() == null) {
            s.setMeow(-3001);
            s.setMsg("\u7528\u6237\u5bc6\u7801\u4e0d\u80fd\u4e3a\u7a7a");
            return (STData<Integer>)s;
        }
        if (users.getPwdOld() != null) {
            final String pwd = this.usersMapper.selectPWDById(users.getId());
            if (!Md5Encryption.getMD5(users.getPwdOld()).equals(pwd)) {
                s.setMeow(-3001);
                s.setMsg("\u539f\u5bc6\u7801\u8f93\u5165\u9519\u8bef");
                return (STData<Integer>)s;
            }
        }
        users.setPwd(Md5Encryption.getMD5(users.getPwd()));
        final int n = this.usersMapper.update(users);
        if (n == 1) {
            s.setMsg("\u5bc6\u7801\u66f4\u6539\u6210\u529f");
            s.setMeow(0);
            s.setData(n);
        }
        else {
            s.setMsg("\u5bc6\u7801\u66f4\u6539\u5931\u8d25");
            s.setMeow(-1001);
            s.setData(n);
        }
        return (STData<Integer>)s;
    }

    public STData<EntityList<Map<String, Object>>> select(final Users users, final Groups groups) {
        final STData s = new STData();
        final EntityList<Map<String, Object>> r = (EntityList<Map<String, Object>>)new EntityList();
        if (users.getName1() == null) {
            s.setMeow(-3001);
            s.setMsg("\u7528\u6237\u540d\u79f0\u4e0d\u80fd\u4e3a\u7a7a");
            return (STData<EntityList<Map<String, Object>>>)s;
        }
        if (groups.getCode() == null) {
            s.setMeow(-3001);
            s.setMsg("\u7ec4\u7ec7\u7f16\u7801\u4e0d\u80fd\u4e3a\u7a7a");
            return (STData<EntityList<Map<String, Object>>>)s;
        }
        final List<Map<String, Object>> user = this.usersMapper.select(users);
        final List<Map<String, Object>> group = this.groupsMapper.select(groups);
        final Iterator<Map<String, Object>> it = user.iterator();
        while (it.hasNext()) {
            int n = 0;
            final Map<String, Object> map = it.next();
            final int groupId = (int) map.get("GROUPID");
            for (final Map map2 : group) {
                final int id = (int) map2.get("ID");
                if (groupId == id) {
                    n = 1;
                }
            }
            if (n == 0) {
                it.remove();
            }
        }
        if (user.size() == 0 || user == null) {
            r.setMsg("\u6ca1\u6709\u67e5\u8be2\u5230\u76f8\u5173\u4eba\u5458");
            s.setMeow(-3002);
        }
        else {
            r.setMsg("\u67e5\u8be2\u6210\u529f");
            s.setMeow(0);
        }
        r.setTotal(user.size());
        r.setGetMe((List)user);
        s.setData(r);
        return (STData<EntityList<Map<String, Object>>>)s;
    }

    @Transactional(rollbackFor = { Exception.class })
    public STData<Integer> insert(final Users user, final Userinfo userinfo, final UserGroup userGroup) throws Exception {
        final STData<Integer> r = (STData<Integer>)new STData();
        if (user.getUsername() == null) {
            r.setMsg("\u7528\u6237\u540d\u79f0\u4e0d\u80fd\u4e3a\u7a7a");
            r.setMeow(-3001);
            return r;
        }
        if (user.getName1() == null) {
            r.setMsg("\u7528\u6237\u771f\u5b9e\u540d\u79f0\u4e0d\u80fd\u4e3a\u7a7a");
            r.setMeow(-3001);
            return r;
        }
        if (user.getPwd() == null) {
            r.setMsg("\u7528\u6237\u5bc6\u7801\u4e0d\u80fd\u4e3a\u7a7a");
            r.setMeow(-3001);
            return r;
        }
        if (user.getUsercode() == null) {
            r.setMsg("\u7528\u6237\u7f16\u53f7\u4e0d\u80fd\u4e3a\u7a7a");
            r.setMeow(-3001);
            return r;
        }
        if (userGroup.getGroupid() == null) {
            r.setMsg("\u90e8\u95e8ID\u4e0d\u80fd\u4e3a\u7a7a");
            r.setMeow(-3001);
            return r;
        }
        final Users users = new Users();
        users.setUsername(user.getUsername());
        final List<Map<String, Object>> list = this.usersMapper.select(users);
        int n = 0;
        if (list == null || list.size() == 0) {
            user.setPwd(Md5Encryption.getMD5(user.getPwd()));
            user.setSttime(DateUtil.formatNow());
            user.setStstate(1);
            user.setStorder(0);
            n = this.usersMapper.insert(user);
            if (n != 1) {
                r.setData((-1001));
                r.setData(n);
                r.setMsg("\u7528\u6237\u6dfb\u52a0\u5931\u8d25");
                throw new Exception("\u7528\u6237\u6dfb\u52a0\u5931\u8d25");
            }
            final int userid = this.usersMapper.getUserid();
            if (userid != 0) {
                userinfo.setUserid(userid);
                userinfo.setSttime(DateUtil.format(new Date()));
                userinfo.setStstate(1);
                userinfo.setStorder(0);
                n += this.userinfoMapper.insertUserinfo(userinfo);
                userGroup.setUserid(userid);
                userGroup.setSttime(DateUtil.format(new Date()));
                userGroup.setStorder(0);
                userGroup.setStstate(1);
                n += this.userGroupMapper.insert(userGroup);
                r.setMsg("\u7528\u6237\u6dfb\u52a0\u6210\u529f");
                r.setData(n);
                r.setMeow(0);
            }
            else {
                r.setMeow(-1);
            }
        }
        else {
            r.setMeow(-5001);
            r.setData(n);
            r.setMsg("\u7528\u6237\u540d\u5df2\u88ab\u5360\u7528");
        }
        return r;
    }

    @Transactional(rollbackFor = { Exception.class })
    public STData<Integer> update(final Users user) throws Exception {
        final STData<Integer> r = (STData<Integer>)new STData();
        if (user.getId() == null) {
            r.setMeow(-3001);
            r.setMsg("\u7528\u6237ID\u4e0d\u80fd\u4e3a\u7a7a");
            return r;
        }
        if (user.getPwd() == null) {
            user.setPwd(null);
        }
        else {
            user.setPwd(Md5Encryption.getMD5(user.getPwd()));
        }
        final Users users = new Users();
        users.setUsername(user.getUsername());
        final List<Map<String, Object>> user2 = this.usersMapper.select(users);
        if (user2 == null || user2.size() == 0) {
            final int n = this.usersMapper.update(user);
            if (n != 2) {
                r.setMeow(-1003);
                r.setData(n);
                r.setMsg("\u7528\u6237\u4fe1\u606f\u66f4\u65b0\u5931\u8d25");
                throw new Exception("\u7528\u6237\u4fe1\u606f\u66f4\u65b0\u5931\u8d25");
            }
            r.setData(n);
            r.setMsg("\u7528\u6237\u4fe1\u606f\u66f4\u65b0\u6210\u529f");
            r.setMeow(0);
        }
        else if (user2.get(0).get("ID").equals(user.getId())) {
            if (user2.get(0).get("STSTATE").equals(user.getStstate())) {
                user.setUpdatetime(DateUtil.formatNow());
            }
            final int n = this.usersMapper.update(user);
            if (n != 2) {
                r.setMeow(-1003);
                r.setData(n);
                r.setMsg("\u7528\u6237\u4fe1\u606f\u66f4\u65b0\u5931\u8d25");
                throw new Exception("\u7528\u6237\u4fe1\u606f\u66f4\u65b0\u5931\u8d25");
            }
            r.setData(n);
            r.setMsg("\u7528\u6237\u4fe1\u606f\u66f4\u65b0\u6210\u529f");
            r.setMeow(0);
        }
        else {
            r.setMeow(-9001);
            r.setMsg("\u7528\u6237\u540d\u5df2\u7ecf\u88ab\u5360\u7528");
            r.setData(0);
        }
        return r;
    }

    @Transactional(rollbackFor = { Exception.class })
    public STData<Integer> updateState(final Users user) throws Exception {
        final STData<Integer> r = (STData<Integer>)new STData();
        if (user.getId() == null || user.getStstate() == null) {
            r.setMsg("\u7528\u6237ID\u4e0d\u80fd\u4e3a\u7a7a");
            r.setMeow(-3001);
            return r;
        }
        try {
            final int n = this.usersMapper.update(user);
            if (n > 0) {
                this.userGroupMapper.delete(user.getId());
                this.userRoleMapper.delete(user.getId());
                r.setMsg("\u7528\u6237\u5220\u9664\u6210\u529f");
                r.setData(n);
                r.setMeow(0);
            }
            else {
                r.setMeow(-1002);
                r.setData(n);
                r.setMsg("\u7528\u6237\u5220\u9664\u5931\u8d25");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new Exception("\u7528\u6237\u5220\u9664\u5931\u8d25");
        }
        return r;
    }

    public STData<EntityList<Map<String, Object>>> selectByGroup(final Users users) {
        final STData s = new STData();
        final EntityList<Map<String, Object>> entityListResult = (EntityList<Map<String, Object>>)new EntityList();
        final List<Map<String, Object>> list = this.usersMapper.select(users);
        users.setPageNumber(null);
        users.setPageSize(null);
        final List<Map<String, Object>> totalList = this.usersMapper.select(users);
        if (list == null || list.size() == 0) {
            entityListResult.setMsg("\u8be5\u90e8\u95e8\u4e0b\u6ca1\u6709\u7528\u6237");
            s.setMeow(-1);
            s.setData(entityListResult);
        }
        else {
            entityListResult.setTotal(totalList.size());
            entityListResult.setGetMe((List)list);
            entityListResult.setMsg("\u67e5\u8be2\u6210\u529f");
            s.setMeow(0);
            s.setData(entityListResult);
        }
        return (STData<EntityList<Map<String, Object>>>)s;
    }

    @Transactional(rollbackFor = { Exception.class })
    public STData<Integer> updateUserGroup(final UserGroup userGroup) throws Exception {
        final STData<Integer> r = (STData<Integer>)new STData();
        if (userGroup.getGroupid() == null || userGroup.getUserid() == null) {
            r.setMeow(-3001);
            r.setMsg("\u7528\u6237ID\u6216\u90e8\u95e8ID\u4e0d\u80fd\u4e3a\u7a7a");
            return r;
        }
        userGroup.setSttime(DateUtil.formatNow());
        final int n = this.userGroupMapper.update(userGroup);
        if (n == 1) {
            r.setMeow(0);
            r.setData(n);
            r.setMsg("\u7528\u6237\u4fee\u6539\u90e8\u95e8\u6210\u529f");
            return r;
        }
        r.setMeow(-1003);
        r.setData(n);
        r.setMsg("\u7528\u6237\u4fee\u6539\u90e8\u95e8\u5931\u8d25");
        throw new Exception("\u7528\u6237\u4fee\u6539\u90e8\u95e8\u5931\u8d25");
    }

    @Transactional(rollbackFor = { Exception.class })
    public STData<Integer> addUserRole(final Integer userId, final int[] roleId) throws Exception {
        final STData<Integer> r = (STData<Integer>)new STData();
        if (userId == null) {
            r.setMeow(-3001);
            r.setMsg("\u7528\u6237ID\u4e0d\u80fd\u4e3a\u7a7a");
            return r;
        }
        if (roleId == null) {
            r.setMeow(-3001);
            r.setMsg("\u89d2\u8272ID\u4e0d\u80fd\u4e3a\u7a7a");
            return r;
        }
        int n = 0;
        for (int i = 0; i < roleId.length; ++i) {
            final UserRole userRole = new UserRole();
            userRole.setUserid(userId);
            userRole.setRoleid(roleId[i]);
            userRole.setSttime(DateUtil.formatNow());
            userRole.setStstate(1);
            userRole.setStorder(0);
            n += this.userRoleMapper.insert(userRole);
        }
        if (n == roleId.length) {
            r.setData(n);
            r.setMeow(0);
            r.setMsg("\u7528\u6237\u4fee\u6539\u89d2\u8272\u6210\u529f");
            return r;
        }
        r.setMeow(-1003);
        r.setMeow(0);
        r.setMsg("\u7528\u6237\u4fee\u6539\u89d2\u8272\u5931\u8d25");
        throw new Exception("\u7528\u6237\u4fee\u6539\u89d2\u8272\u5931\u8d25");
    }

    @Transactional(rollbackFor = { Exception.class })
    public STData<Integer> deleteUserRole(final Integer userId) throws Exception {
        final STData<Integer> r = (STData<Integer>)new STData();
        if (userId == null) {
            r.setMeow(-3001);
            r.setMsg("\u7528\u6237ID\u4e0d\u80fd\u4e3a\u7a7a");
            return r;
        }
        try {
            final int n = this.userRoleMapper.delete(userId);
            r.setData(n);
            r.setMeow(0);
            r.setMsg("\u7528\u6237\u89d2\u8272\u4fee\u6539\u6210\u529f");
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new Exception("\u7528\u6237\u89d2\u8272\u4fee\u6539\u5931\u8d25");
        }
        return r;
    }

    public STData<EntityList<Map<String, Object>>> selectUserRole(final UserRole userRole) {
        final STData s = new STData();
        final EntityList<Map<String, Object>> r = (EntityList<Map<String, Object>>)new EntityList();
        if (userRole.getUserid() == null) {
            s.setMeow(-3001);
            s.setMsg("\u7528\u6237ID\u4e0d\u80fd\u4e3a\u7a7a");
            return (STData<EntityList<Map<String, Object>>>)s;
        }
        final List<Map<String, Object>> roleIdList = this.userRoleMapper.selectRoleByUser(userRole);
        if (roleIdList == null && roleIdList.size() == 0) {
            r.setMsg("\u8be5\u7528\u6237\u6ca1\u6709\u88ab\u8d4b\u4e88\u89d2\u8272\u6743\u9650!");
            s.setMeow(-3002);
            s.setData(r);
        }
        else {
            r.setMsg("\u67e5\u8be2\u6210\u529f!");
            r.setGetMe((List)roleIdList);
            s.setMeow(0);
            s.setData(r);
        }
        return (STData<EntityList<Map<String, Object>>>)s;
    }

    public Map<String, Object> selectUserAuth(final UserRole userRole, final String type, final String systemName) {
        final List<Map<String, Object>> roleList = (List<Map<String, Object>>)((EntityList)this.selectUserRole(userRole).getData()).getGetMe();
        final List<Integer> uids = new ArrayList<Integer>();
        roleList.forEach(item -> uids.add((int)item.get("role_id")));
        final Map<String, Object> params = new HashMap<String, Object>();
        params.put("uids", uids); // 角色集合。
        params.put("sysCode", systemName); //
        final List<Map<String, Object>> menuList = this.menuMapper.selectMenu(params);
        final List<Map<String, Object>> menuCookie = ZListUtil.getTreeByList("ID", "PARENTID", "children", menuList, -1);
        final List<Map<String, Object>> funList = this.funMapper.selectFun(params);
        final List<String> funCookie = new ArrayList<String>();
        funList.forEach(item -> funCookie.add((String) item.get("CODE")));
        final Map<String, Object> map = new HashMap<String, Object>();
        map.put("menuCookie", menuCookie);
        map.put("funCookie", funCookie);
        return map;
    }

    public STData<EntityList<Map<String, Object>>> selectByGroupId(final Integer groupId) {
        final List<Integer> users = this.getUsersByGroup(groupId);
        final List<Integer> list = users.stream().distinct().collect(Collectors.toList());
        final List<Map<String, Object>> userList = this.usersMapper.selectByUserids(list);
        final STData stData = new STData();
        stData.setData(userList);
        stData.setMsg("\u67e5\u8be2\u6210\u529f\uff01");
        stData.setMeow(0);
        return (STData<EntityList<Map<String, Object>>>)stData;
    }

    private List<Integer> getUsersByGroup(final Integer groupId) {
        final UserGroup userGroup = new UserGroup();
        userGroup.setGroupid(groupId);
        final List<Map<String, Object>> usergroups = this.userGroupMapper.select(userGroup);
        final List<Integer> users = new LinkedList<Integer>();
        for (final Map<String, Object> usergroup : usergroups) {
            users.add(Integer.valueOf(usergroup.get("USERID").toString()));
        }
        final Groups groups = new Groups();
        groups.setParentid(groupId);
        final List<Map<String, Object>> select = this.groupsMapper.select(groups);
        for (final Map<String, Object> map : select) {
            final Integer id = Integer.valueOf(map.get("ID").toString());
            final List<Integer> list = this.getUsersByGroup(id);
            users.addAll(list);
        }
        return users;
    }

    @Transactional(rollbackFor = { Exception.class })
    public STData<Integer> addLog(final Userloghistory userloghistory) throws Exception {
        final STData s = new STData();
        if (userloghistory.getUserid() == null || userloghistory.getIp() == null || userloghistory.getLogtype() == null) {
            s.setMsg("\u65e5\u5fd7\u4fe1\u606f\u4e0d\u5b8c\u6574");
            s.setMeow(-3001);
            return (STData<Integer>)s;
        }
        userloghistory.setSttime(DateUtil.formatNow());
        final int n = this.userloghistoryMapper.insert(userloghistory);
        if (n > 0) {
            s.setData(n);
            s.setMeow(0);
            s.setMsg("\u65e5\u5fd7\u6dfb\u52a0\u6210\u529f");
            return (STData<Integer>)s;
        }
        s.setMeow(-1001);
        s.setMeow(0);
        s.setMsg("\u65e5\u5fd7\u6dfb\u52a0\u5931\u8d25");
        throw new Exception("\u65e5\u5fd7\u6dfb\u52a0\u5931\u8d25");
    }

    @Transactional(rollbackFor = { Exception.class })
    public STData<Integer> deleteLog(final String ids) throws Exception {
        final STData s = new STData();
        if (ids == null) {
            s.setMsg("\u65e5\u5fd7ID\u4e0d\u80fd\u4e3a\u7a7a");
            s.setMeow(-3001);
            return (STData<Integer>)s;
        }
        final Map map = new HashMap();
        map.put("ids", ids);
        final int n = this.userloghistoryMapper.delete(map);
        if (n > 0) {
            s.setData(n);
            s.setMeow(0);
            s.setMsg("\u65e5\u5fd7\u5220\u9664\u6210\u529f");
            return (STData<Integer>)s;
        }
        s.setMeow(-1001);
        s.setMeow(0);
        s.setMsg("\u65e5\u5fd7\u5220\u9664\u5931\u8d25");
        throw new Exception("\u65e5\u5fd7\u5220\u9664\u5931\u8d25");
    }

    public STData selectLog(final Userloghistory userloghistory) {
        final STData s = new STData();
        final List<Map<String, Object>> list = this.userloghistoryMapper.select(userloghistory);
        final EntityList entityList = new EntityList();
        final Userloghistory userloghistory2 = new Userloghistory();
        if (list == null || list.size() == 0) {
            s.setMeow(-3002);
            s.setMsg("\u65e5\u5fd7\u4fe1\u606f\u6682\u65f6\u4e3a\u7a7a");
        }
        else {
            final List<Map<String, Object>> list2 = this.userloghistoryMapper.select(userloghistory2);
            entityList.setTotal(list2.size());
            entityList.setGetMe((List)list);
            entityList.setMsg("\u67e5\u8be2\u6210\u529f");
            entityList.setMeow(0);
            s.setData(entityList);
            s.setMeow(0);
        }
        return s;
    }

    @Transactional(rollbackFor = { Exception.class })
    public STData<Integer> addOperationLog(final OperationLog operationLog) throws Exception {
        final STData s = new STData();
        final InetAddress inet = InetAddress.getLocalHost();
        final String ipStrs = inet.getHostAddress();
        operationLog.setIp(ipStrs);
        if (operationLog.getUserid() == null || operationLog.getOperation() == null) {
            s.setMsg("\u65e5\u5fd7\u4fe1\u606f\u4e0d\u5b8c\u6574");
            s.setMeow(-3001);
            return (STData<Integer>)s;
        }
        final int n = this.operationlogMapper.insert(operationLog);
        if (n > 0) {
            s.setData(n);
            s.setMeow(0);
            s.setMsg("\u65e5\u5fd7\u6dfb\u52a0\u6210\u529f");
            return (STData<Integer>)s;
        }
        s.setMeow(-1001);
        s.setMeow(0);
        s.setMsg("\u65e5\u5fd7\u6dfb\u52a0\u5931\u8d25");
        throw new Exception("\u65e5\u5fd7\u6dfb\u52a0\u5931\u8d25");
    }

    public STData selectOperationLog(final OperationLog operationLog) {
        final STData s = new STData();
        final List<Map<String, Object>> list = this.operationlogMapper.select(operationLog);
        final EntityList entityList = new EntityList();
        final OperationLog operationLog2 = new OperationLog();
        if (list == null || list.size() == 0) {
            s.setMeow(0);
            s.setData(entityList);
            s.setMsg("\u65e5\u5fd7\u4fe1\u606f\u6682\u65f6\u4e3a\u7a7a");
        }
        else {
            final List<Map<String, Object>> list2 = this.operationlogMapper.select(operationLog2);
            entityList.setTotal(list2.size());
            entityList.setGetMe((List)list);
            entityList.setMsg("\u67e5\u8be2\u6210\u529f");
            entityList.setMeow(0);
            s.setData(entityList);
            s.setMeow(0);
        }
        return s;
    }

    public STData<List<Map<String, Object>>> getUsersByRole(final Integer groupId, final String roleCode) {
        final STData s = new STData();
        final Roles roles = new Roles();
        roles.setCode(roleCode);
        final List<Map<String, Object>> role = this.rolesMapper.select(roles);
        if (role == null || role.size() == 0) {
            s.setData((-1));
            s.setMsg("\u89d2\u8272\u7f16\u7801\u6709\u8bef");
            s.setMeow(-5001);
            return (STData<List<Map<String, Object>>>)s;
        }
        final int roleId = (int) role.get(0).get("ID");
        final UserRole userRole = new UserRole();
        userRole.setRoleid(roleId);
        final List<Map<String, Object>> users = this.userRoleMapper.select(userRole);
        if (users == null || users.size() == 0) {
            s.setData((-1));
            s.setMsg("\u8be5\u89d2\u8272\u4e0b\u6ca1\u6709\u7528\u6237");
            s.setMeow(-5001);
            return (STData<List<Map<String, Object>>>)s;
        }
        final Groups group = new Groups();
        group.setId(groupId);
        final List<Map<String, Object>> groups = this.groupsMapper.select(group);
        final List<Map<String, Object>> g = new ArrayList<Map<String, Object>>();
        for (final Map map : groups) {
            List<Integer> list = new ArrayList<Integer>();
            final Set<Map<String, Object>> users2 = new HashSet<Map<String, Object>>();
            list = this.getUserId(list, (Integer) map.get("ID"));
            for (final Map map2 : users) {
                for (final Integer userid : list) {
                    if ((int)map2.get("USERID") == (int)userid) {
                        final Users user = new Users();
                        user.setId(userid);
                        final List<Map<String, Object>> u = this.usersMapper.select(user);
                        if (u == null || u.size() == 0) {
                            continue;
                        }
                        users2.add(u.get(0));
                    }
                }
            }
            if (users2 != null && users2.size() != 0) {
                final List<Map<String, Object>> role2 = this.rolesMapper.select(roles);
                role2.get(0).put("children", users2);
                map.put("children", role2);
                g.add(map);
            }
        }
        s.setMeow(0);
        s.setMsg("\u67e5\u8be2\u6210\u529f");
        s.setData(g);
        return (STData<List<Map<String, Object>>>)s;
    }

    public STData<List<Map<String, Object>>> getUsersByRoleAndGroup(final String visible, final String roleCode) {
        final STData s = new STData();
        final Roles roles = new Roles();
        roles.setCode(roleCode);
        final List<Map<String, Object>> role = this.rolesMapper.select(roles);
        if (role == null || role.size() == 0) {
            s.setData((-1));
            s.setMsg("\u89d2\u8272\u7f16\u7801\u6709\u8bef");
            s.setMeow(-5001);
            return (STData<List<Map<String, Object>>>)s;
        }
        final int roleId = (int) role.get(0).get("ID");
        final UserRole userRole = new UserRole();
        userRole.setRoleid(roleId);
        final List<Map<String, Object>> users = this.userRoleMapper.select(userRole);
        if (users == null || users.size() == 0) {
            s.setData((-1));
            s.setMsg("\u8be5\u89d2\u8272\u4e0b\u6ca1\u6709\u7528\u6237");
            s.setMeow(-5001);
            return (STData<List<Map<String, Object>>>)s;
        }
        final Groups group = new Groups();
        group.setVisible(visible);
        final List<Map<String, Object>> groups = this.groupsMapper.select(group);
        final List<Map<String, Object>> g = new ArrayList<Map<String, Object>>();
        for (final Map map : groups) {
            List<Integer> list = new ArrayList<Integer>();
            final Set<Map<String, Object>> users2 = new HashSet<Map<String, Object>>();
            list = this.getUserId(list, (int)map.get("ID"));
            for (final Map map2 : users) {
                for (final Integer userid : list) {
                    if ((int)map2.get("USERID") == (int)userid) {
                        final Users user = new Users();
                        user.setId(userid);
                        final List<Map<String, Object>> u = this.usersMapper.select(user);
                        if (u == null || u.size() == 0) {
                            continue;
                        }
                        users2.add(u.get(0));
                    }
                }
            }
            if (users2 != null && users2.size() != 0) {
                final List<Map<String, Object>> role2 = this.rolesMapper.select(roles);
                role2.get(0).put("children", users2);
                map.put("children", role2);
                g.add(map);
            }
        }
        s.setMeow(0);
        s.setMsg("\u67e5\u8be2\u6210\u529f");
        s.setData(g);
        return (STData<List<Map<String, Object>>>)s;
    }

    public List<Integer> getUserId(final List<Integer> list, final int groupId) {
        final UserGroup userGroup = new UserGroup();
        userGroup.setGroupid(groupId);
        final List<Map<String, Object>> users = this.userGroupMapper.select(userGroup);
        if (users != null && users.size() != 0) {
            for (final Map map1 : users) {
                list.add((int)map1.get("USERID"));
            }
        }
        final Groups group = new Groups();
        group.setParentid(groupId);
        final List<Map<String, Object>> groups = this.groupsMapper.select(group);
        if (groups == null || groups.size() == 0) {
            return list;
        }
        for (final Map map2 : groups) {
            final UserGroup userGroup2 = new UserGroup();
            userGroup2.setGroupid((int)map2.get("ID"));
            final List<Map<String, Object>> users2 = this.userGroupMapper.select(userGroup2);
            if (users2 != null && users2.size() != 0) {
                for (final Map map3 : users2) {
                    list.add((int)map3.get("USERID"));
                }
            }
            this.getUserId(list, (Integer) map2.get("ID"));
        }
        return list;
    }

    static {
        loginMap = new ConcurrentHashMap<String, Long>(100);
    }

    class MenuSort implements Comparator<Map<String, Object>>
    {
        private String key;

        public MenuSort(final String key) {
            this.key = key;
        }

        @Override
        public int compare(final Map<String, Object> o1, final Map<String, Object> o2) {
            final Integer sortIndex1 = Integer.parseInt(String.valueOf(o1.get(this.key)));
            final Integer sortIndex2 = Integer.parseInt(String.valueOf(o2.get(this.key)));
            return sortIndex1.compareTo(sortIndex2);
        }

        @Override
        public boolean equals(final Object obj) {
            return false;
        }
    }
}
