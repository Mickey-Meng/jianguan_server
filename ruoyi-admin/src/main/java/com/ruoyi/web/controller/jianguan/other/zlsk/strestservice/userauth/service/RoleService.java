package com.ruoyi.web.controller.jianguan.other.zlsk.strestservice.userauth.service;

import com.ruoyi.web.controller.jianguan.other.zlsk.strestservice.userauth.mapper.*;
import com.ruoyi.web.controller.jianguan.other.zlsk.strestservice.userauth.model.*;
import com.ruoyi.web.controller.jianguan.other.zlsk.stsupporter.stctk.model.EntityList;
import com.ruoyi.web.controller.jianguan.other.zlsk.stsupporter.stctk.model.STData;
import com.ruoyi.web.controller.jianguan.other.zlsk.stsupporter.stctk.util.DateUtil;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.*;
import java.util.*;

@Service
public class RoleService
{
    @Autowired
    private FunMapper funMapper;
    @Autowired
    private RolesMapper rolesMapper;
    @Autowired
    private MenuMapper menuMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;
    @Autowired
    private UserauthMapper userauthMapper;
    @Autowired
    private UsersMapper usersMapper;
    @Autowired
    private UserGroupMapper userGroupMapper;

    public STData<Map<String, Object>> getCode(final Integer id) {
        final STData<Map<String, Object>> r = (STData<Map<String, Object>>)new STData();
        if (id == null) {
            r.setMeow(-3001);
            r.setMsg("\u89d2\u8272ID\u4e0d\u80fd\u4e3a\u7a7a");
            return r;
        }
        final Roles role1 = new Roles();
        role1.setParentid(id);
        final List<Map<String, Object>> roles1 = this.rolesMapper.select(role1);
        String str;
        if (id == -1) {
            str = "1000";
        }
        else {
            final Roles role2 = new Roles();
            role2.setId(id);
            final List<Map<String, Object>> roles2 = this.rolesMapper.select(role2);
            str = roles2.get(0).get("CODE") + "";
        }
        final Map map1 = new HashMap();
        if (roles1.size() == 0 || roles1 == null) {
            map1.put("code", str + 1);
        }
        else {
            int a = -1;
            for (final Map map2 : roles1) {
                final String code = (String) map2.get("CODE");
                final int b = Integer.parseInt(code.substring(str.length(), code.length()));
                if (b > a) {
                    a = b;
                }
            }
            ++a;
            map1.put("code", str + a);
        }
        r.setData(map1);
        r.setMeow(0);
        return r;
    }

    public EntityList<Map<String, Object>> select(final Roles roles) {
        final EntityList<Map<String, Object>> r = (EntityList<Map<String, Object>>)new EntityList();
        final List<Map<String, Object>> role = this.rolesMapper.select(roles);
        if (role == null || role.size() == 0) {
            r.setMsg("\u6ca1\u6709\u67e5\u8be2\u5230\u7ed3\u679c");
        }
        else {
            r.setGetMe((List)role);
            r.setTotal(role.size());
            r.setMeow(0);
            r.setMsg("\u67e5\u8be2\u6210\u529f");
        }
        return r;
    }

    @Transactional(rollbackFor = { Exception.class })
    public STData<Integer> insertRole(final Roles role) throws Exception {
        final STData<Integer> r = (STData<Integer>)new STData();
        if (role.getName() == null) {
            r.setMeow(-3001);
            r.setMsg("\u89d2\u8272\u540d\u79f0\u4e0d\u80fd\u4e3a\u7a7a");
            return r;
        }
        if (role.getParentid() == null) {
            r.setMeow(-3001);
            r.setMsg("\u89d2\u8272\u7236\u7ea7ID\u4e0d\u80fd\u4e3a\u7a7a");
            return r;
        }
        if (role.getRolelevel() == null) {
            r.setMeow(-3001);
            r.setMsg("\u89d2\u8272\u7b49\u7ea7\u4e0d\u80fd\u4e3a\u7a7a");
            return r;
        }
        if (role.getCode() == null) {
            r.setMeow(-3001);
            r.setMsg("\u89d2\u8272\u7f16\u7801\u4e0d\u80fd\u4e3a\u7a7a");
            return r;
        }
        int n = 0;
        final Roles roles = new Roles();
        roles.setName1(role.getName());
        roles.setParentid(role.getParentid());
        final List<Map<String, Object>> role2 = this.rolesMapper.select(roles);
        if (role2 == null || role2.size() == 0) {
            role.setSttime(DateUtil.formatNow());
            role.setStstate(1);
            role.setStorder(0);
            role.setType(1);
            n = this.rolesMapper.insert(role);
            if (n != 1) {
                r.setData(n);
                r.setData((-1001));
                r.setMsg("\u89d2\u8272\u6dfb\u52a0\u5931\u8d25");
                throw new Exception("\u89d2\u8272\u6dfb\u52a0\u5931\u8d25");
            }
            r.setData(n);
            r.setMeow(0);
            r.setMsg("\u89d2\u8272\u6dfb\u52a0\u6210\u529f");
        }
        else {
            r.setData(n);
            r.setMeow(-5001);
            r.setMsg("\u5df2\u5b58\u5728\u8be5\u89d2\u8272\u540d");
        }
        return r;
    }

    @Transactional(rollbackFor = { Exception.class })
    public STData<Integer> updateRole(final Roles role) throws Exception {
        final STData<Integer> r = (STData<Integer>)new STData();
        if (role.getId() == null) {
            r.setMeow(-3001);
            r.setMsg("\u89d2\u8272ID\u4e0d\u80fd\u4e3a\u7a7a");
            return r;
        }
        if (role.getName() == null) {
            r.setMeow(-3001);
            r.setMsg("\u89d2\u8272\u540d\u79f0\u4e0d\u80fd\u4e3a\u7a7a");
            return r;
        }
        final Roles getParentId = new Roles();
        getParentId.setId(role.getId());
        final List<Map<String, Object>> result = this.rolesMapper.select(getParentId);
        if (result == null || result.size() == 0) {
            r.setMeow(-3001);
            r.setMsg("\u66f4\u65b0\u5931\u8d25\uff0c\u5e76\u6ca1\u6709\u627e\u5230\u6307\u5b9aid = " + role.getId() + "\u89d2\u8272");
            return r;
        }
        final Integer parentid = Integer.parseInt(result.get(0).get("PARENTID").toString());
        final int n = this.rolesMapper.update(role);
        if (n != 1) {
            r.setMeow(-1003);
            r.setData(n);
            r.setMsg("\u89d2\u8272\u66f4\u65b0\u5931\u8d25");
            throw new Exception("\u89d2\u8272\u66f4\u65b0\u5931\u8d25");
        }
        r.setData(n);
        r.setMeow(0);
        r.setMsg("\u89d2\u8272\u66f4\u65b0\u6210\u529f");
        final Roles checkCode = new Roles();
        checkCode.setCode(role.getCode());
        final List<Map<String, Object>> select = this.rolesMapper.select(checkCode);
        if (select != null && select.size() > 1) {
            r.setMeow(-1003);
            r.setData(n);
            r.setMsg("\u5df2\u7ecf\u6709\u76f8\u540c\u7684\u89d2\u8272\u7f16\u7801");
            throw new Exception("\u89d2\u8272\u66f4\u65b0\u5931\u8d25,\u5df2\u7ecf\u6709\u76f8\u540c\u7684\u89d2\u8272\u7f16\u7801");
        }
        final Roles checkName = new Roles();
        checkName.setParentid(parentid);
        checkName.setName1(role.getName());
        final List<Map<String, Object>> select2 = this.rolesMapper.select(checkName);
        if (select2 != null && select2.size() > 1) {
            r.setMeow(-1003);
            r.setData(n);
            r.setMsg("\u8be5\u7cfb\u7edf\u4e0b\uff0c\u5df2\u7ecf\u6709\u76f8\u540c\u7684\u89d2\u8272\u540d\u79f0");
            throw new Exception("\u8be5\u7cfb\u7edf\u4e0b\uff0c\u5df2\u7ecf\u6709\u76f8\u540c\u7684\u89d2\u8272\u540d\u79f0");
        }
        return r;
    }

    @Transactional(rollbackFor = { Exception.class })
    public STData<Integer> deleteRole(final int[] roleId) throws Exception {
        final STData<Integer> r = (STData<Integer>)new STData();
        if (roleId == null) {
            r.setMsg("\u89d2\u8272ID\u4e0d\u80fd\u4e3a\u7a7a");
            r.setMeow(-3001);
            return r;
        }
        for (int i = 0; i < roleId.length; ++i) {
            final UserRole userRole = new UserRole();
            userRole.setRoleid(roleId[i]);
            final List<Map<String, Object>> users = this.userRoleMapper.select(userRole);
            if (users != null && users.size() != 0) {
                r.setMeow(-9001);
                r.setMsg("\u6709\u89d2\u8272\u6b63\u5728\u88ab\u5360\u7528");
                return r;
            }
        }
        int n = 0;
        for (int j = 0; j < roleId.length; ++j) {
            final int k = this.rolesMapper.delete(roleId[j]);
            n += k;
            if (k > 0) {
                final Userauth userauth = new Userauth();
                userauth.setUtype(2);
                userauth.setUid(roleId[j]);
                this.userauthMapper.delete(userauth);
            }
        }
        if (n == roleId.length) {
            r.setMsg("\u5220\u9664\u89d2\u8272\u6210\u529f");
            r.setMeow(0);
            r.setData(n);
            return r;
        }
        r.setMsg("\u5220\u9664\u89d2\u8272\u5931\u8d25");
        r.setMeow(-1002);
        r.setData(n);
        throw new Exception("\u5220\u9664\u89d2\u8272\u5931\u8d25");
    }

    @Transactional(rollbackFor = { Exception.class })
    public STData<Integer> insertRoleMenu(final Integer roleId, final int[] menuId) throws Exception {
        final STData<Integer> r = (STData<Integer>)new STData();
        if (roleId == null) {
            r.setMeow(-3001);
            r.setMsg("\u89d2\u8272ID\u4e0d\u80fd\u4e3a\u7a7a");
            return r;
        }
        if (menuId == null || menuId.length == 0) {
            r.setMeow(-3001);
            r.setMsg("\u83dc\u5355ID\u4e0d\u80fd\u4e3a\u7a7a");
            return r;
        }
        int n = 0;
        for (int i = 0; i < menuId.length; ++i) {
            final Userauth userauth = new Userauth();
            userauth.setRid(menuId[i]);
            userauth.setRtype(2);
            userauth.setUid(roleId);
            userauth.setUtype(2);
            userauth.setSttime(DateUtil.formatNow());
            userauth.setStstate(1);
            userauth.setStorder(0);
            n += this.userauthMapper.insert(userauth);
        }
        if (n == menuId.length) {
            r.setData(n);
            r.setMeow(0);
            r.setMsg("\u89d2\u8272\u4fee\u6539\u83dc\u5355\u6210\u529f");
            return r;
        }
        r.setData(n);
        r.setMeow(-1003);
        r.setMsg("\u89d2\u8272\u4fee\u6539\u83dc\u5355\u5931\u8d25");
        throw new Exception("\u89d2\u8272\u4fee\u6539\u83dc\u5355\u5931\u8d25");
    }

    @Transactional(rollbackFor = { Exception.class })
    public STData<Integer> deleteRoleMenu(final Userauth userauth) throws Exception {
        final STData<Integer> r = (STData<Integer>)new STData();
        final Integer roleId = userauth.getUid();
        if (roleId == null) {
            r.setMeow(-3001);
            r.setMsg("\u89d2\u8272ID\u4e0d\u80fd\u4e3a\u7a7a");
            return r;
        }
        userauth.setRtype(2);
        try {
            final int n = this.userauthMapper.delete(userauth);
            r.setMeow(0);
            r.setMsg("\u89d2\u8272\u4fee\u6539\u83dc\u5355\u6210\u529f");
            r.setData(n);
        }
        catch (Exception e) {
            throw new Exception("\u89d2\u8272\u4fee\u6539\u83dc\u5355\u5931\u8d25");
        }
        return r;
    }

    public EntityList<Map<String, Object>> selectRoleMenu(final Userauth userauth) {
        final EntityList<Map<String, Object>> r = (EntityList<Map<String, Object>>)new EntityList();
        final Integer roleId = userauth.getUid();
        if (roleId == null) {
            r.setMeow(-3001);
            r.setMsg("\u89d2\u8272ID\u4e0d\u80fd\u4e3a\u7a7a");
            return r;
        }
        userauth.setRtype(2);
        userauth.setUtype(2);
        final List<Map<String, Object>> menus = this.menuMapper.selectByUserauth(userauth);
        if (menus == null || menus.size() == 0) {
            r.setMsg("\u89d2\u8272\u6ca1\u6709\u4efb\u4f55\u83dc\u5355");
            r.setMeow(-3002);
        }
        else {
            final List result = new ArrayList();
            for (final Map<String, Object> menu : menus) {
                final LinkedList<Object> list = new LinkedList<Object>();
                list.add(menu);
                result.add(list);
            }
            r.setMsg("\u67e5\u8be2\u6210\u529f\uff01");
            r.setGetMe(result);
            r.setMeow(0);
        }
        r.setTotal(menus.size());
        return r;
    }

    @Transactional(rollbackFor = { Exception.class })
    public STData<Integer> insertFunRole(final Integer funId, final int[] roleId) throws Exception {
        final STData<Integer> r = (STData<Integer>)new STData();
        if (funId == null) {
            r.setMeow(-3001);
            r.setMsg("\u529f\u80fdID\u4e0d\u80fd\u4e3a\u7a7a");
            return r;
        }
        if (roleId == null || roleId.length == 0) {
            r.setMeow(-3001);
            r.setMsg("\u89d2\u8272ID\u4e0d\u80fd\u4e3a\u7a7a");
            return r;
        }
        int n = 0;
        for (int i = 0; i < roleId.length; ++i) {
            final Userauth userauth = new Userauth();
            userauth.setRid(funId);
            userauth.setRtype(4);
            userauth.setUid(roleId[i]);
            userauth.setUtype(2);
            userauth.setSttime(DateUtil.formatNow());
            userauth.setStstate(1);
            userauth.setStorder(0);
            n += this.userauthMapper.insert(userauth);
        }
        if (n == roleId.length) {
            r.setData(n);
            r.setMeow(0);
            r.setMsg("\u4fee\u6539\u89d2\u8272\u529f\u80fd\u6210\u529f");
            return r;
        }
        r.setData(n);
        r.setMeow(-1003);
        r.setMsg("\u4fee\u6539\u89d2\u8272\u529f\u80fd\u5931\u8d25");
        throw new Exception("\u4fee\u6539\u89d2\u8272\u529f\u80fd\u5931\u8d25");
    }

    @Transactional(rollbackFor = { Exception.class })
    public STData<Integer> deleteFunRole(final Userauth userauth) throws Exception {
        final STData<Integer> r = (STData<Integer>)new STData();
        if (userauth.getRid() == null) {
            r.setMsg("\u529f\u80fd\u6309\u94aeID\u4e0d\u80fd\u4e3a\u7a7a");
            r.setMeow(-3001);
            return r;
        }
        userauth.setRtype(4);
        try {
            final int n = this.userauthMapper.delete(userauth);
            r.setMsg("\u4fee\u6539\u89d2\u8272\u529f\u80fd\u6210\u529f");
            r.setMeow(0);
            r.setData(n);
        }
        catch (Exception e) {
            throw new Exception("\u4fee\u6539\u89d2\u8272\u529f\u80fd\u5931\u8d25");
        }
        return r;
    }

    public EntityList<Map<String, Object>> selectFunRole(final Fun fun) {
        final EntityList<Map<String, Object>> r = (EntityList<Map<String, Object>>)new EntityList();
        if (fun.getCode() == null || fun.getCode() == "") {
            r.setMeow(-3001);
            r.setMsg("\u529f\u80fd\u6309\u94ae\u7f16\u53f7\u4e0d\u80fd\u4e3a\u7a7a");
            return r;
        }
        final List<Map<String, Object>> funs = this.funMapper.select(fun);
        if (funs == null || funs.size() == 0) {
            r.setMeow(-5001);
            r.setMsg("\u529f\u80fd\u6309\u94ae\u4e0d\u5b58\u5728");
            return r;
        }
        final Userauth userauth = new Userauth();
        userauth.setRid((int)funs.get(0).get("ID"));
        userauth.setRtype(4);
        final List<Map<String, Object>> funRole = this.userauthMapper.select(userauth);
        if (funRole == null || funRole.size() == 0) {
            r.setMsg("\u8be5\u529f\u80fd\u4e0b\u6ca1\u6709\u89d2\u8272");
            r.setMeow(-3002);
        }
        else {
            final List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
            for (final Map map : funRole) {
                if ((int)map.get("UTYPE") == 2) {
                    final int roleid = (int) map.get("UID");
                    final Roles role = new Roles();
                    role.setId(roleid);
                    list.addAll(this.rolesMapper.select(role));
                }
            }
            r.setTotal(funRole.size());
            r.setGetMe((List)list);
            r.setMeow(0);
        }
        return r;
    }

    public STData getUserByFun(final Fun fun) {
        final STData s = new STData();
        final EntityList<Map<String, Object>> roles = this.selectFunRole(fun);
        if (roles.getMeow() != 0) {
            s.setData(roles);
            return s;
        }
        final List<Map<String, Object>> list = (List<Map<String, Object>>)roles.getGetMe();
        for (final Map map : list) {
            final int roleId = (int) map.get("ID");
            final UserRole userRole = new UserRole();
            userRole.setRoleid(roleId);
            final List<Map<String, Object>> userRoles = this.userRoleMapper.select(userRole);
            final List list2 = new ArrayList();
            for (final Map map2 : userRoles) {
                final int userid = (int) map2.get("USERID");
                final Users users = new Users();
                users.setId(userid);
                final List<Map<String, Object>> user = this.usersMapper.select(users);
                list2.addAll(user);
            }
            map.put("children", list2);
        }
        s.setData(list);
        s.setMsg("\u67e5\u8be2\u6210\u529f");
        s.setMeow(0);
        return s;
    }

    public STData<List<Map<String, Object>>> selectUserByRole(final String code, final int groupId) {
        final STData s = new STData();
        final List list = new ArrayList();
        if (code == null) {
            s.setMeow(-3001);
            s.setMsg("\u89d2\u8272\u7f16\u7801\u4e0d\u80fd\u4e3a\u7a7a");
            return (STData<List<Map<String, Object>>>)s;
        }
        final Roles roles = new Roles();
        roles.setCode(code);
        final List<Map<String, Object>> role = this.rolesMapper.select(roles);
        if (role != null && role.size() != 0) {
            final int roleid = (int) role.get(0).get("ID");
            final UserRole userRole = new UserRole();
            userRole.setRoleid(roleid);
            final List<Map<String, Object>> userRoles = this.userRoleMapper.select(userRole);
            final UserGroup userGroup = new UserGroup();
            userGroup.setGroupid(groupId);
            final List<Map<String, Object>> userGroups = this.userGroupMapper.select(userGroup);
            if (userRoles.size() != 0 && userRoles != null && userGroups.size() != 0 && userGroups != null) {
                for (final Map m : userRoles) {
                    final int userid = (int) m.get("USERID");
                    for (final Map map : userGroups) {
                        final int userid2 = (int) map.get("USERID");
                        if (userid == userid2) {
                            final Users users = new Users();
                            users.setId(userid);
                            final List<Map<String, Object>> user = this.usersMapper.select(users);
                            if (user == null || user.size() == 0) {
                                continue;
                            }
                            list.addAll(user);
                        }
                    }
                }
                s.setData(list);
                s.setMsg("\u67e5\u8be2\u6210\u529f");
                s.setMeow(0);
            }
            else {
                s.setData(list);
                s.setMsg("\u6ca1\u6709\u4eba\u5458");
                s.setMeow(-3002);
            }
        }
        else {
            s.setData((-1));
            s.setMsg("\u89d2\u8272\u540d\u79f0\u6709\u8bef");
            s.setMeow(-5001);
        }
        return (STData<List<Map<String, Object>>>)s;
    }
}
