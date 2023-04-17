package com.ruoyi.web.controller.jianguan.zlsk.strestservice.userauth.service;

import com.ruoyi.web.controller.jianguan.zlsk.strestservice.userauth.mapper.GyyMapper;
import com.ruoyi.web.controller.jianguan.zlsk.strestservice.userauth.model.Gyy;
import org.springframework.stereotype.*;
import org.springframework.beans.factory.annotation.*;
import com.ruoyi.web.controller.jianguan.zlsk.stsupporter.stctk.model.*;
import java.text.*;
import java.util.*;
import org.springframework.transaction.annotation.*;

@Service
public class GyyService
{
    @Autowired
    private GyyMapper gmapper;

    public STData<EntityList<Map<String, Object>>> select(final Gyy g) {
        final STData s = new STData();
        final EntityList<Map<String, Object>> r = (EntityList<Map<String, Object>>)new EntityList();
        final List<Map<String, Object>> glist = this.gmapper.select(g);
        final int total = this.gmapper.selectTotal(g);
        if (glist == null || glist.size() == 0) {
            s.setMeow(0);
            s.setMsg("\u6ca1\u6709\u67e5\u8be2\u5230\u7ed3\u679c");
            s.setData((Object)r);
        }
        else {
            s.setMsg("\u67e5\u8be2\u6210\u529f");
            r.setTotal(total);
            r.setGetMe((List)glist);
            s.setData((Object)r);
            s.setMeow(0);
        }
        return (STData<EntityList<Map<String, Object>>>)s;
    }

    @Transactional(rollbackFor = { Exception.class })
    public STData<Integer> insert(final Gyy g) {
        final STData<Integer> r = (STData<Integer>)new STData();
        if (g.getFunName() == null || g.getFunName().isEmpty()) {
            r.setMsg("\u60ef\u7528\u8bed\u540d\u79f0\u4e0d\u80fd\u4e3a\u7a7a");
            r.setMeow(-3001);
            return r;
        }
        final Gyy g2 = new Gyy();
        g2.setFunName(g.getFunName());
        final List<Map<String, Object>> ulist = this.gmapper.select(g2);
        if (ulist.size() > 0) {
            r.setMsg("\u60ef\u7528\u8bed\u5df2\u5b58\u5728");
            r.setMeow(-3001);
            return r;
        }
        final Gyy g3 = new Gyy();
        g3.setFunKey(g.getFunKey());
        final List<Map<String, Object>> ulist2 = this.gmapper.select(g3);
        if (ulist2.size() > 0) {
            r.setMsg("\u7f16\u7801\u5df2\u5b58\u5728");
            r.setMeow(-3001);
            return r;
        }
        final SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        final String sttime = df.format(new Date());
        g.setCreatetime(sttime);
        g.setStstate(1);
        this.gmapper.insert(g);
        r.setMeow(0);
        r.setMsg("\u6dfb\u52a0\u6210\u529f");
        return r;
    }

    @Transactional(rollbackFor = { Exception.class })
    public STData<Integer> update(final Gyy g) {
        final STData<Integer> r = (STData<Integer>)new STData();
        if (g.getId() == null) {
            r.setMsg("\u975e\u6cd5\u53c2\u6570");
            r.setMeow(-3001);
            return r;
        }
        final Gyy g2 = new Gyy();
        g2.setFunName(g.getFunName());
        final List<Map<String, Object>> glist = this.gmapper.select(g2);
        if (glist.size() > 0) {
            r.setMsg("\u60ef\u7528\u8bed\u540d\u79f0\u5df2\u5b58\u5728");
            r.setMeow(-3001);
            return r;
        }
        this.gmapper.update(g);
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
        this.gmapper.delete(Integer.parseInt(id));
        this.gmapper.deleteChildren(Integer.parseInt(id));
        r.setMeow(0);
        r.setMsg("\u5220\u9664\u6210\u529f");
        return r;
    }
}
