package com.ruoyi.web.controller.jianguan.zlsk.strestservice.userauth.service;

import com.ruoyi.web.controller.jianguan.zlsk.strestservice.userauth.mapper.*;
import com.ruoyi.web.controller.jianguan.zlsk.strestservice.userauth.model.*;
import com.ruoyi.web.controller.jianguan.zlsk.strestservice.userauth.util.DateUtil;
import org.springframework.stereotype.*;
import org.springframework.beans.factory.annotation.*;
import com.ruoyi.web.controller.jianguan.zlsk.stsupporter.stctk.model.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.*;
import java.text.*;
import java.util.*;

@Service
public class AuthService
{
    @Autowired
    private RolesMapper rolesMapper;
    @Autowired
    private UserauthMapper userauthMapper;
    @Autowired
    private FunMapper funMapper;
    @Autowired
    private MenuMapper menuMapper;
    @Autowired
    private ServiceMapper serviceMapper;
    @Autowired
    private UsersMapper usersMapper;
    @Autowired
    private DicMapper dicMapper;

    public EntityList<Map<String, Object>> selectFun(final Fun fun) {
        final EntityList<Map<String, Object>> r = (EntityList<Map<String, Object>>)new EntityList();
        final List<Map<String, Object>> funs = this.funMapper.select(fun);
        if (funs == null || funs.size() == 0) {
            r.setMeow(-3002);
            r.setMsg("\u6ca1\u6709\u529f\u80fd\u6309\u94ae");
            return r;
        }
        for (final Map map : funs) {
            final Userauth userauth = new Userauth();
            userauth.setRtype(4);
            userauth.setRid((int)map.get("ID"));
            final List<Map<String, Object>> funRole = this.userauthMapper.select(userauth);
            if (funRole == null || funRole.size() == 0) {
                map.put("person", "");
            }
            else {
                final StringBuffer sb = new StringBuffer();
                for (final Map m : funRole) {
                    final int uid = (int) m.get("UID");
                    final int utype = (int) m.get("UTYPE");
                    final Roles roles = new Roles();
                    roles.setId(uid);
                    if (utype == 2) {
                        final List<Map<String, Object>> role = this.rolesMapper.select(roles);
                        if (role.isEmpty() || role == null) {
                            continue;
                        }
                        sb.append(role.get(0).get("NAME") + ",");
                    }
                }
                final String s = sb.toString().substring(0, sb.length() - 1);
                map.put("person", s);
            }
        }
        r.setTotal(funs.size());
        r.setGetMe((List)funs);
        r.setMeow(0);
        return r;
    }

    public EntityList<Map<String, Object>> selectMenu(final Menu menu) {
        final EntityList<Map<String, Object>> r = (EntityList<Map<String, Object>>)new EntityList();
        final List<Map<String, Object>> menus = this.menuMapper.select(menu);
        if (menus == null || menus.size() == 0) {
            r.setMeow(-3002);
            r.setMsg("\u6ca1\u6709\u67e5\u8be2\u5230\u7ed3\u679c");
        }
        else {
            r.setTotal(menus.size());
            r.setMsg("\u67e5\u8be2\u6210\u529f");
            r.setGetMe((List)menus);
            r.setMeow(0);
        }
        return r;
    }

    public EntityList<Map<String, Object>> selectService(final com.ruoyi.web.controller.jianguan.zlsk.strestservice.userauth.model.Service service) {
        final EntityList<Map<String, Object>> r = (EntityList<Map<String, Object>>)new EntityList();
        final List<Map<String, Object>> services = this.serviceMapper.select(service);
        if (services == null || services.size() == 0) {
            r.setMsg("\u6ca1\u6709\u67e5\u8be2\u5230\u7ed3\u679c");
            r.setMeow(-3002);
        }
        else {
            r.setMsg("\u67e5\u8be2\u6210\u529f");
            r.setTotal(services.size());
            r.setGetMe((List)services);
            r.setMeow(0);
        }
        return r;
    }

    @Transactional(rollbackFor = { Exception.class })
    public STData<Integer> insertService(final com.ruoyi.web.controller.jianguan.zlsk.strestservice.userauth.model.Service  service) throws Exception {
        final STData<Integer> s = (STData<Integer>)new STData();
        if (service.getName() == null) {
            s.setMsg("\u670d\u52a1\u540d\u79f0\u4e0d\u80fd\u4e3a\u7a7a");
            s.setMeow(-3001);
            return s;
        }
        service.setSttime(DateUtil.getCurrentDate());
        service.setStstate(1);
        service.setStorder(0);
        final int n = this.serviceMapper.insert(service);
        if (n == 1) {
            s.setData(n);
            s.setMeow(0);
            s.setMsg("\u670d\u52a1\u6dfb\u52a0\u6210\u529f");
            return s;
        }
        s.setMeow(-1001);
        s.setData(n);
        s.setMsg("\u670d\u52a1\u6dfb\u52a0\u5931\u8d25");
        throw new Exception("\u670d\u52a1\u6dfb\u52a0\u5931\u8d25");
    }

    @Transactional(rollbackFor = { Exception.class })
    public STData<Integer> deleteService(final Integer serviceId) throws Exception {
        final STData<Integer> r = (STData<Integer>)new STData();
        if (serviceId == null) {
            r.setMsg("\u670d\u52a1ID\u4e0d\u80fd\u4e3a\u7a7a");
            r.setMeow(-3001);
            return r;
        }
        final int n = this.serviceMapper.delete(serviceId);
        if (n == 1) {
            r.setData(n);
            r.setMeow(0);
            r.setMsg("\u670d\u52a1\u6743\u9650\u5220\u9664\u6210\u529f");
            return r;
        }
        r.setData((-1002));
        r.setData(n);
        r.setMsg("\u670d\u52a1\u6743\u9650\u5220\u9664\u5931\u8d25");
        throw new Exception("\u670d\u52a1\u6743\u9650\u5220\u9664\u5931\u8d25");
    }

    @Transactional(rollbackFor = { Exception.class })
    public STData<Integer> insertFun(final Fun fun) throws Exception {
        final STData<Integer> r = (STData<Integer>)new STData();
        if (fun.getParentid() == null) {
            r.setMsg("\u529f\u80fd\u6309\u94ae\u7236\u7ea7ID\u4e0d\u80fd\u4e3a\u7a7a");
            r.setMeow(-3001);
            return r;
        }
        if (fun.getName() == null) {
            r.setMsg("\u529f\u80fd\u6309\u94aeID\u4e0d\u80fd\u4e3a\u7a7a");
            r.setMeow(-3001);
            return r;
        }
        fun.setSttime(DateUtil.getCurrentDate());
        fun.setStstate(1);
        fun.setStorder(0);
        final int n = this.funMapper.insert(fun);
        if (n == 1) {
            r.setData(n);
            r.setMeow(0);
            r.setMsg("\u529f\u80fd\u6309\u94ae\u6dfb\u52a0\u6210\u529f");
            return r;
        }
        r.setData((-1001));
        r.setData(n);
        r.setMsg("\u529f\u80fd\u6309\u94ae\u6dfb\u52a0\u5931\u8d25");
        throw new Exception("\u529f\u80fd\u6309\u94ae\u6dfb\u52a0\u5931\u8d25");
    }

    @Transactional(rollbackFor = { Exception.class })
    public STData<Integer> deleteFun(final Integer funId) throws Exception {
        final STData<Integer> r = (STData<Integer>)new STData();
        if (funId == null) {
            r.setMsg("\u529f\u80fdID\u4e0d\u80fd\u4e3a\u7a7a");
            r.setMeow(-3001);
            return r;
        }
        final int n = this.funMapper.delete(funId);
        if (n == 1) {
            r.setData(n);
            r.setMeow(0);
            r.setMsg("\u529f\u80fd\u6309\u94ae\u5220\u9664\u6210\u529f");
            return r;
        }
        r.setData((-1002));
        r.setData(n);
        r.setMsg("\u529f\u80fd\u6309\u94ae\u5220\u9664\u5931\u8d25");
        throw new Exception("\u529f\u80fd\u6309\u94ae\u5220\u9664\u5931\u8d25");
    }

    @Transactional(rollbackFor = { Exception.class })
    public STData<Integer> insertMenu(final Menu menu, final String sms) throws Exception {
        final STData<Integer> r = (STData<Integer>)new STData();
        if (menu.getName() == null) {
            r.setMsg("\u83dc\u5355\u540d\u79f0\u4e0d\u80fd\u4e3a\u7a7a");
            r.setMeow(-3001);
            return r;
        }
        if (menu.getStorder() == null) {
            r.setMsg("\u83dc\u5355\u987a\u5e8f\u7f16\u53f7\u4e0d\u80fd\u4e3a\u7a7a");
            r.setMeow(-3001);
            return r;
        }
        if (menu.getParentid() == null) {
            r.setMsg("\u83dc\u5355\u7236\u7ea7ID\u4e0d\u80fd\u4e3a\u7a7a");
            r.setMeow(-3001);
            return r;
        }
        final Menu menu2 = new Menu();
        menu2.setParentid(menu.getParentid());
        menu2.setName(menu.getName());
        final List<Map<String, Object>> list = this.menuMapper.select(menu2);
        if (list.size() == 0 || list == null) {
            menu.setSttime(DateUtil.getCurrentDate());
            menu.setStstate(1);
            final int n = this.menuMapper.insert(menu);
            if (sms != null && !sms.isEmpty()) {
                final String[] sm = sms.split(",");
                for (int i = 0; i < sm.length; ++i) {
                    final SubsystemMenu sm2 = new SubsystemMenu();
                    sm2.setMid(menu.getId());
                    sm2.setSid(Integer.parseInt(sm[i]));
                    this.menuMapper.addSubMenu(sm2);
                }
            }
            if (n <= 0) {
                r.setData((-1001));
                r.setData(n);
                r.setMsg("\u83dc\u5355\u6dfb\u52a0\u5931\u8d25");
                throw new Exception("\u83dc\u5355\u6dfb\u52a0\u5931\u8d25");
            }
            r.setData(n);
            r.setMeow(0);
            r.setMsg("\u83dc\u5355\u6dfb\u52a0\u6210\u529f");
        }
        else {
            r.setMsg("\u83dc\u5355\u540d\u6216\u8005\u83dc\u5355\u7f16\u7801\u91cd\u590d");
            r.setData(0);
            r.setMeow(-1001);
        }
        return r;
    }

    @Transactional(rollbackFor = { Exception.class })
    public STData<Integer> updateMenu(final Menu menu, final String sms) throws Exception {
        final STData<Integer> r = (STData<Integer>)new STData();
        if (menu.getId() == null) {
            r.setMsg("\u83dc\u5355ID\u4e0d\u80fd\u4e3a\u7a7a");
            r.setMeow(-3001);
            return r;
        }
        final int n = this.menuMapper.update(menu);
        if (sms != null && !sms.isEmpty()) {
            this.menuMapper.delSubMenu(menu.getId());
            final String[] sm = sms.split(",");
            for (int i = 0; i < sm.length; ++i) {
                final SubsystemMenu sm2 = new SubsystemMenu();
                sm2.setMid(menu.getId());
                sm2.setSid(Integer.parseInt(sm[i]));
                this.menuMapper.addSubMenu(sm2);
            }
        }
        if (n == 1) {
            r.setData(n);
            r.setMeow(0);
            r.setMsg("\u83dc\u5355\u4fee\u6539\u6210\u529f");
            return r;
        }
        r.setData((-1003));
        r.setData(n);
        r.setMsg("\u83dc\u5355\u4fee\u6539\u5931\u8d25");
        throw new Exception("\u83dc\u5355\u4fee\u6539\u5931\u8d25");
    }

    @Transactional(rollbackFor = { Exception.class })
    public STData<Integer> deleteMenu(final int[] menuId) throws Exception {
        final STData<Integer> r = (STData<Integer>)new STData();
        int n = 0;
        for (int i = 0; i < menuId.length; ++i) {
            final Userauth userauth = new Userauth();
            userauth.setRtype(2);
            userauth.setRid(menuId[i]);
            final List<Map<String, Object>> list = this.userauthMapper.select(userauth);
            if (list != null && list.size() != 0) {
                r.setMeow(-9001);
                r.setMsg("\u8be5\u83dc\u5355\u6709\u89d2\u8272\u6b63\u5728\u5360\u7528");
                r.setData(n);
                return r;
            }
        }
        for (int i = 0; i < menuId.length; ++i) {
            n += this.menuMapper.delete(menuId[i]);
        }
        if (n == menuId.length) {
            r.setData(n);
            r.setMeow(0);
            r.setMsg("\u83dc\u5355\u5220\u9664\u6210\u529f");
            return r;
        }
        r.setData((-1002));
        r.setData(n);
        r.setMsg("\u83dc\u5355\u5220\u9664\u5931\u8d25");
        throw new Exception("\u83dc\u5355\u5220\u9664\u5931\u8d25");
    }

    public STData<Integer> updateLonlat(final int userid, final String lonlat) {
        final STData<Integer> r = (STData<Integer>)new STData();
        if (userid == 0) {
            r.setData((-3001));
            r.setMsg("\u6ca1\u6709\u76ee\u6807\u5bf9\u8c61");
            return r;
        }
        int n = 0;
        final SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        final String sbsj = df.format(new Date());
        final List<Map<String, Object>> list = this.usersMapper.selectUserPosition(userid);
        final Map map = new HashMap();
        map.put("userid", userid);
        map.put("position", lonlat);
        map.put("sbsj", sbsj);
        if (Integer.parseInt(list.get(0).get("total").toString()) > 0) {
            n = this.usersMapper.updateLonlat(map);
        }
        else {
            map.put("storder", 0);
            map.put("ststate", 1);
            n = this.usersMapper.addPosition(map);
        }
        r.setData(n);
        r.setMeow(0);
        r.setMsg("\u66f4\u65b0\u6210\u529f");
        return r;
    }

    public STData<List<Map<String, Object>>> getLonlat(final String ids) {
        final STData s = new STData();
        final Map map = new HashMap();
        if (ids != null) {
            map.put("ids", ids);
        }
        final List<Map<String, Object>> list = this.usersMapper.getLonlat(map);
        if (list == null || list.size() == 0) {
            s.setMeow(-5001);
            s.setMsg("\u6ca1\u6709\u5750\u6807\u4f4d\u7f6e");
        }
        else {
            s.setMsg("\u67e5\u8be2\u6210\u529f");
            s.setMeow(0);
            s.setData(list);
        }
        return (STData<List<Map<String, Object>>>)s;
    }

    public STData<Integer> updateMobile(final int userid, final int mobile) {
        final STData<Integer> r = (STData<Integer>)new STData();
        if (userid == 0) {
            r.setData((-3001));
            r.setMsg("\u6ca1\u6709\u76ee\u6807\u5bf9\u8c61");
            return r;
        }
        int n = 0;
        final List<Map<String, Object>> list = this.usersMapper.selectMobileType(userid);
        if (Integer.parseInt(list.get(0).get("total").toString()) > 0) {
            n = this.usersMapper.updateMobile(userid, mobile);
        }
        else {
            n = this.usersMapper.addMobileType(userid, mobile);
        }
        r.setData(n);
        r.setMeow(0);
        r.setMsg("\u66f4\u65b0\u6210\u529f");
        return r;
    }

    public STData<List<Map<String, Object>>> selectDic(final Dic dic) {
        final STData s = new STData();
        final List<Map<String, Object>> list = this.dicMapper.select(dic);
        if (list == null || list.size() == 0) {
            s.setMeow(-3002);
            s.setMsg("\u4e0d\u5b58\u5728\u5b57\u5178\u4fe1\u606f");
        }
        else {
            s.setData(list);
            s.setMeow(0);
            s.setMsg("\u67e5\u8be2\u6210\u529f");
        }
        return (STData<List<Map<String, Object>>>)s;
    }

    public STData<List<Map<String, Object>>> selectByKey(final String parentkey, final String key, final String value) {
        final STData s = new STData();
        final List<Map<String, Object>> list = this.dicMapper.selectByKey(parentkey, key, value);
        if (list == null || list.size() == 0) {
            s.setMeow(-3002);
            s.setMsg("\u6ca1\u6709\u627e\u5230\u5b57\u5178\u4fe1\u606f");
        }
        else {
            s.setData(list);
            s.setMeow(0);
            s.setMsg("\u67e5\u8be2\u6210\u529f");
        }
        return (STData<List<Map<String, Object>>>)s;
    }

    public STData selectDicByParentKey(final String keyStr) {
        final STData stData = new STData();
        if (keyStr == null || keyStr.equals("") || keyStr.equals("null")) {
            stData.setMeow(-1);
            stData.setMsg("key \u4e0d\u80fd\u4e3a\u7a7a");
            return stData;
        }
        final String[] keys = keyStr.split(",");
        final Map<String, List<Map<String, Object>>> result = new HashMap<String, List<Map<String, Object>>>();
        for (final String key : keys) {
            Dic dic = new Dic();
            dic.setKey(key);
            final List<Map<String, Object>> list = this.dicMapper.select(dic);
            if (list.size() != 0) {
                dic = new Dic();
                dic.setParentid((int)list.get(0).get("id"));
                result.put(key, this.dicMapper.select(dic));
            }
        }
        stData.setData(result);
        stData.setMeow(0);
        return stData;
    }

    public STData<Integer> insertDic(final Dic dic) {
        final STData s = new STData();
        if (dic.getName() == null || dic.getKey() == null || dic.getParentid() == null) {
            s.setMsg("\u57fa\u672c\u4fe1\u606f\u4e0d\u80fd\u4e3a\u7a7a");
            s.setMeow(-3001);
            return (STData<Integer>)s;
        }
        dic.setStorder(0);
        dic.setStstate(1);
        dic.setSttime(new Date());
        dic.setValue(dic.getName());
        final int n = this.dicMapper.insert(dic);
        if (n == 1) {
            s.setData(n);
            s.setMeow(0);
            s.setMsg("\u6dfb\u52a0\u6210\u529f");
        }
        else {
            s.setData(n);
            s.setMeow(-1001);
            s.setMsg("\u6dfb\u52a0\u5931\u8d25");
        }
        return (STData<Integer>)s;
    }

    public STData<Integer> updateDic(final Dic dic) {
        final STData s = new STData();
        if (dic.getId() == null) {
            s.setMsg("\u5b57\u5178id\u4e0d\u80fd\u4e3a\u7a7a");
            s.setMeow(-3001);
            return (STData<Integer>)s;
        }
        if (dic.getName() == null && dic.getKey() == null) {
            s.setMeow(-3001);
            s.setMsg("\u57fa\u672c\u4fe1\u606f\u4e0d\u5168");
            return (STData<Integer>)s;
        }
        final int n = this.dicMapper.update(dic);
        if (n == 1) {
            s.setMsg("\u66f4\u65b0\u6210\u529f");
            s.setMeow(0);
            s.setData(n);
        }
        else {
            s.setData(n);
            s.setMsg("\u66f4\u65b0\u5931\u8d25");
            s.setMeow(-1003);
        }
        return (STData<Integer>)s;
    }

    @Transactional(rollbackFor = { Exception.class })
    public STData<Integer> deleteDic(final int[] ids) throws Exception {
        final STData s = new STData();
        if (ids == null) {
            s.setMeow(-3001);
            s.setMsg("id\u4e0d\u80fd\u4e3a\u7a7a");
            return (STData<Integer>)s;
        }
        int n = 0;
        for (int i = 0; i < ids.length; ++i) {
            n = this.dicMapper.delete(ids[i]);
        }
        if (n == ids.length) {
            s.setData(n);
            s.setMeow(0);
            s.setMsg("\u5220\u9664\u6210\u529f");
            return (STData<Integer>)s;
        }
        s.setData(n);
        s.setMeow(-1002);
        s.setMsg("\u5220\u9664\u5931\u8d25");
        throw new Exception("\u5220\u9664\u5931\u8d25");
    }
}
