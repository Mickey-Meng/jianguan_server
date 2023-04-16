package com.ruoyi.web.controller.jianguan.zlsk.strestservice.userauth.service;

import com.ruoyi.web.controller.jianguan.zlsk.strestservice.userauth.mapper.GroupsMapper;
import com.ruoyi.web.controller.jianguan.zlsk.strestservice.userauth.mapper.UserGroupMapper;
import com.ruoyi.web.controller.jianguan.zlsk.strestservice.userauth.model.Groups;
import com.ruoyi.web.controller.jianguan.zlsk.strestservice.userauth.model.UserGroup;
import com.ruoyi.web.controller.jianguan.zlsk.stsupporter.stctk.util.DateUtil;
import org.springframework.stereotype.*;
import org.springframework.beans.factory.annotation.*;
import java.util.*;
import com.ruoyi.web.controller.jianguan.zlsk.stsupporter.stctk.model.*;
import org.springframework.transaction.annotation.*;

@Service
public class GroupService
{
    @Autowired
    private GroupsMapper groupsMapper;
    @Autowired
    private UserGroupMapper userGroupMapper;

    public STData<Map<String, Object>> getCode(final Integer id) {
        final STData<Map<String, Object>> r = (STData<Map<String, Object>>)new STData();
        if (id == null) {
            r.setMeow(-3001);
            r.setMsg("\u90e8\u95e8ID\u4e0d\u80fd\u4e3a\u7a7a");
            return r;
        }
        final Groups groups = new Groups();
        groups.setId(id);
        final List<Map<String, Object>> group1 = this.groupsMapper.getGroupCode(groups);
        final Groups groups2 = new Groups();
        groups2.setParentid(id);
        final List<Map<String, Object>> group2 = this.groupsMapper.getGroupCode(groups2);
        final Map map1 = new HashMap();
        final String str = group1.get(0).get("CODE") + "-";
        if (group2 == null || group2.size() == 0) {
            map1.put("code", str + "1");
        }
        else {
            int a = -1;
            for (final Map map2 : group2) {
                final String code = (String) map2.get("CODE");
                final int b = Integer.parseInt(code.substring(str.length(), code.length()), 10);
                if (b > a) {
                    a = b;
                }
            }
            ++a;
            map1.put("code", str + a);
        }
        r.setData((Map<String, Object>) map1);
        r.setMeow(0);
        return r;
    }

    public STData<EntityList<Map<String, Object>>> select(final Groups groups) {
        final STData s = new STData();
        final EntityList<Map<String, Object>> r = (EntityList<Map<String, Object>>)new EntityList();
        final List<Map<String, Object>> group = this.groupsMapper.select(groups);
        if (group == null || group.size() == 0) {
            s.setMeow(-3002);
            s.setMsg("\u6ca1\u6709\u67e5\u8be2\u5230\u7ed3\u679c");
            s.setData((Object)r);
        }
        else {
            s.setMsg("\u67e5\u8be2\u6210\u529f");
            r.setTotal(group.size());
            r.setGetMe((List)group);
            s.setData((Object)r);
            s.setMeow(0);
        }
        return (STData<EntityList<Map<String, Object>>>)s;
    }

    @Transactional(rollbackFor = { Exception.class })
    public STData<Integer> insert(final Groups group) throws Exception {
        final STData<Integer> r = (STData<Integer>)new STData();
        if (group.getName() == null) {
            r.setMsg("\u90e8\u95e8\u540d\u79f0\u4e0d\u80fd\u4e3a\u7a7a");
            r.setMeow(-3001);
            return r;
        }
        if (group.getCode() == null) {
            r.setMsg("\u90e8\u95e8\u7f16\u53f7\u4e0d\u80fd\u4e3a\u7a7a");
            r.setMeow(-3001);
            return r;
        }
        if (group.getParentid() == null) {
            r.setMsg("\u90e8\u95e8\u7236\u7ea7ID\u4e0d\u80fd\u4e3a\u7a7a");
            r.setMeow(-3001);
            return r;
        }
        if (group.getStorder() == null) {
            r.setMsg("\u90e8\u95e8\u987a\u5e8f\u7f16\u53f7\u4e0d\u80fd\u4e3a\u7a7a");
            r.setMeow(-3001);
            return r;
        }
        if (group.getGrouplevel() == null) {
            r.setMsg("\u90e8\u95e8\u7b49\u7ea7\u7f16\u53f7\u4e0d\u80fd\u4e3a\u7a7a");
            r.setMeow(-3001);
            return r;
        }
        if (group.getType() == null || group.getType().isEmpty()) {
            r.setMsg("\u8bf7\u9009\u62e9\u90e8\u95e8\u7c7b\u578b");
            r.setMeow(-3001);
            return r;
        }
        final Groups groups = new Groups();
        groups.setName(group.getName());
        final List<Map<String, Object>> g = this.groupsMapper.select(groups);
        int n = 0;
        if (g == null || g.size() == 0) {
            group.setSttime(DateUtil.formatNow());
            group.setStstate(1);
            n = this.groupsMapper.insert(group);
            if (n != 1) {
                r.setMeow(-1001);
                r.setData((Integer) n);
                r.setMsg("\u90e8\u95e8\u6dfb\u52a0\u5931\u8d25");
                throw new Exception("\u90e8\u95e8\u6dfb\u52a0\u5931\u8d25");
            }
            r.setData((Integer) n);
            r.setMeow(0);
            r.setMsg("\u90e8\u95e8\u6dfb\u52a0\u6210\u529f");
        }
        else {
            r.setMeow(-5001);
            r.setData((Integer) n);
            r.setMsg("\u8be5\u90e8\u95e8\u5df2\u7ecf\u5b58\u5728");
        }
        return r;
    }

    @Transactional(rollbackFor = { Exception.class })
    public STData<Integer> addGroup(final Groups group) throws Exception {
        final STData<Integer> r = (STData<Integer>)new STData();
        if (group.getName() == null) {
            r.setMsg("\u90e8\u95e8\u540d\u79f0\u4e0d\u80fd\u4e3a\u7a7a");
            r.setMeow(-3001);
            return r;
        }
        if (group.getCode() == null) {
            r.setMsg("\u90e8\u95e8\u7f16\u53f7\u4e0d\u80fd\u4e3a\u7a7a");
            r.setMeow(-3001);
            return r;
        }
        if (group.getParentid() == null) {
            r.setMsg("\u90e8\u95e8\u7236\u7ea7ID\u4e0d\u80fd\u4e3a\u7a7a");
            r.setMeow(-3001);
            return r;
        }
        if (group.getStorder() == null) {
            r.setMsg("\u90e8\u95e8\u987a\u5e8f\u7f16\u53f7\u4e0d\u80fd\u4e3a\u7a7a");
            r.setMeow(-3001);
            return r;
        }
        if (group.getGrouplevel() == null) {
            r.setMsg("\u90e8\u95e8\u7b49\u7ea7\u7f16\u53f7\u4e0d\u80fd\u4e3a\u7a7a");
            r.setMeow(-3001);
            return r;
        }
        if (group.getType() == null || group.getType().isEmpty()) {
            r.setMsg("\u8bf7\u9009\u62e9\u90e8\u95e8\u7c7b\u578b");
            r.setMeow(-3001);
            return r;
        }
        final Groups groups = new Groups();
        groups.setName(group.getName());
        final List<Map<String, Object>> g = this.groupsMapper.select(groups);
        int n = 0;
        group.setSttime(DateUtil.formatNow());
        group.setStstate(1);
        n = this.groupsMapper.insert(group);
        if (n == 1) {
            r.setData((Integer) n);
            r.setMeow(0);
            r.setMsg("\u90e8\u95e8\u6dfb\u52a0\u6210\u529f");
            return r;
        }
        r.setMeow(-1001);
        r.setData((Integer) n);
        r.setMsg("\u90e8\u95e8\u6dfb\u52a0\u5931\u8d25");
        throw new Exception("\u90e8\u95e8\u6dfb\u52a0\u5931\u8d25");
    }

    @Transactional(rollbackFor = { Exception.class })
    public STData<Integer> update(final Groups group) throws Exception {
        final STData<Integer> r = (STData<Integer>)new STData();
        if (group.getId() == null) {
            r.setMsg("\u90e8\u95e8ID\u4e0d\u80fd\u4e3a\u7a7a");
            r.setMeow(-3001);
            return r;
        }
        final int n = this.groupsMapper.update(group);
        if (n == 1) {
            r.setData((Integer) n);
            r.setMeow(0);
            r.setMsg("\u90e8\u95e8\u4fee\u6539\u6210\u529f");
            return r;
        }
        r.setMeow(-1003);
        r.setData((Integer) n);
        r.setMsg("\u90e8\u95e8\u4fee\u6539\u5931\u8d25");
        throw new Exception("\u90e8\u95e8\u4fee\u6539\u5931\u8d25");
    }

    @Transactional(rollbackFor = { Exception.class })
    public STData<Integer> delete(final int[] groupId) throws Exception {
        final STData<Integer> r = (STData<Integer>)new STData();
        if (groupId == null) {
            r.setMsg("\u90e8\u95e8ID\u4e0d\u80fd\u4e3a\u7a7a");
            r.setMeow(-3001);
            return r;
        }
        int n = 0;
        for (int i = 0; i < groupId.length; ++i) {
            final UserGroup userGroup = new UserGroup();
            userGroup.setGroupid(groupId[i]);
            final List<Map<String, Object>> list = this.userGroupMapper.select(userGroup);
            if (list != null && list.size() != 0) {
                r.setData(-1002);
                r.setData((Integer) n);
                r.setMsg("\u90e8\u95e8\u4e2d\u8fd8\u5b58\u5728\u7528\u6237");
                return r;
            }
        }
        for (int i = 0; i < groupId.length; ++i) {
            n += this.groupsMapper.delete(groupId[i]);
        }
        if (n == groupId.length) {
            r.setData((Integer) n);
            r.setMeow(0);
            r.setMsg("\u90e8\u95e8\u5220\u9664\u6210\u529f");
            return r;
        }
        r.setData((Integer) n);
        r.setMeow(0);
        r.setMsg("\u90e8\u95e8\u5220\u9664\u5931\u8d25");
        throw new Exception("\u90e8\u95e8\u5220\u9664\u5931\u8d25");
    }

    @Transactional(rollbackFor = { Exception.class })
    public STData<Integer> delete1(final int[] groupId) throws Exception {
        final STData<Integer> r = (STData<Integer>)new STData();
        if (groupId == null) {
            r.setMsg("\u90e8\u95e8ID\u4e0d\u80fd\u4e3a\u7a7a");
            r.setMeow(-3001);
            return r;
        }
        int n = 0;
        for (int i = 0; i < groupId.length; ++i) {
            final UserGroup userGroup = new UserGroup();
            userGroup.setGroupid(groupId[i]);
            final List<Map<String, Object>> list = this.userGroupMapper.select(userGroup);
            if (list != null && list.size() != 0) {
                r.setData(-1002);
                r.setData((Integer) 0);
                r.setMsg("\u90e8\u95e8\u4e2d\u8fd8\u5b58\u5728\u7528\u6237");
                return r;
            }
        }
        for (int i = 0; i < groupId.length; ++i) {
            final Groups groups = new Groups();
            groups.setStstate(-1);
            groups.setId(groupId[i]);
            n += this.groupsMapper.update(groups);
        }
        if (n == groupId.length) {
            r.setData((Integer) n);
            r.setMeow(0);
            r.setMsg("\u90e8\u95e8\u7ec4\u7ec7\u5220\u9664\u6210\u529f");
            return r;
        }
        r.setData(-1002);
        r.setData((Integer) n);
        r.setMsg("\u90e8\u95e8\u7ec4\u7ec7\u5220\u9664\u5931\u8d25");
        throw new Exception("\u90e8\u95e8\u7ec4\u7ec7\u5220\u9664\u5931\u8d25");
    }
}
