package com.ruoyi.web.controller.jianguan.zlsk.strestservice.userauth.service;

import com.ruoyi.web.controller.jianguan.zlsk.strestservice.userauth.mapper.UserOrganizeMapper;
import com.ruoyi.web.controller.jianguan.zlsk.strestservice.userauth.model.UserOrganize;
import org.springframework.stereotype.*;
import org.springframework.beans.factory.annotation.*;
import com.ruoyi.web.controller.jianguan.zlsk.stsupporter.stctk.model.*;
import java.text.*;
import java.util.*;
import org.springframework.transaction.annotation.*;

@Service
public class UserOrganizeService
{
    @Autowired
    private UserOrganizeMapper umapper;

    public STData<EntityList<Map<String, Object>>> select(final UserOrganize u) {
        final STData s = new STData();
        final EntityList<Map<String, Object>> r = (EntityList<Map<String, Object>>)new EntityList();
        final List<Map<String, Object>> ulist = this.umapper.select(u);
        final int total = this.umapper.selectTotal(u);
        if (ulist == null || ulist.size() == 0) {
            s.setMeow(0);
            s.setMsg("\u6ca1\u6709\u67e5\u8be2\u5230\u7ed3\u679c");
            s.setData((Object)r);
        }
        else {
            s.setMsg("\u67e5\u8be2\u6210\u529f");
            r.setTotal(total);
            r.setGetMe((List)ulist);
            s.setData((Object)r);
            s.setMeow(0);
        }
        return (STData<EntityList<Map<String, Object>>>)s;
    }

    @Transactional(rollbackFor = { Exception.class })
    public STData<Integer> insert(final UserOrganize u) {
        final STData<Integer> r = (STData<Integer>)new STData();
        if (u.getUserGroupName() == null || u.getUserGroupName().isEmpty()) {
            r.setMsg("\u5c97\u4f4d\u540d\u79f0\u4e0d\u80fd\u4e3a\u7a7a");
            r.setMeow(-3001);
            return r;
        }
        if (u.getUserGroupCode() == null || u.getUserGroupCode().isEmpty()) {
            r.setMsg("\u5c97\u4f4d\u7f16\u7801\u4e0d\u80fd\u4e3a\u7a7a");
            r.setMeow(-3001);
            return r;
        }
        final UserOrganize u2 = new UserOrganize();
        u2.setUserGroupCode(u.getUserGroupCode());
        final List<Map<String, Object>> ulist = this.umapper.select(u2);
        if (ulist.size() > 0) {
            r.setMsg("\u7f16\u7801\u5df2\u5b58\u5728");
            r.setMeow(-3001);
            return r;
        }
        final SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        final String sttime = df.format(new Date());
        u.setCreatetime(sttime);
        u.setStstate(1);
        this.umapper.insert(u);
        r.setMeow(0);
        r.setMsg("\u6dfb\u52a0\u6210\u529f");
        return r;
    }

    @Transactional(rollbackFor = { Exception.class })
    public STData<Integer> update(final UserOrganize u) {
        final STData<Integer> r = (STData<Integer>)new STData();
        if (u.getId() == null) {
            r.setMsg("\u975e\u6cd5\u53c2\u6570");
            r.setMeow(-3001);
            return r;
        }
        final UserOrganize u2 = new UserOrganize();
        u2.setUserGroupCode(u.getUserGroupCode());
        final List<Map<String, Object>> ulist = this.umapper.select(u2);
        if (ulist.size() > 0) {
            r.setMsg("\u7f16\u7801\u5df2\u5b58\u5728");
            r.setMeow(-3001);
            return r;
        }
        this.umapper.update(u);
        r.setMeow(0);
        r.setMsg("\u66f4\u65b0\u6210\u529f");
        return r;
    }

    @Transactional(rollbackFor = { Exception.class })
    public STData<Integer> delete(final String id) {
        final STData<Integer> r = (STData<Integer>)new STData();
        if (id == null || id.isEmpty()) {
            r.setMsg("\u975e\u6cd5\u53c2\u6570");
            r.setMeow(-3001);
            return r;
        }
        this.umapper.delete(Integer.parseInt(id));
        r.setMeow(0);
        r.setMsg("\u5220\u9664\u6210\u529f");
        return r;
    }
}
