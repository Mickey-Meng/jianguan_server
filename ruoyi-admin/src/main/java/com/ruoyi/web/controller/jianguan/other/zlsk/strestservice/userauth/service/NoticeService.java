package com.ruoyi.web.controller.jianguan.other.zlsk.strestservice.userauth.service;

import com.ruoyi.web.controller.jianguan.other.zlsk.stsupporter.stctk.model.EntityList;
import com.ruoyi.web.controller.jianguan.other.zlsk.stsupporter.stctk.model.STData;
import com.ruoyi.web.controller.jianguan.other.zlsk.strestservice.userauth.mapper.NoticeMapper;
import com.ruoyi.web.controller.jianguan.other.zlsk.strestservice.userauth.model.Notice;
import org.springframework.stereotype.*;
import org.springframework.beans.factory.annotation.*;
import java.text.*;
import java.util.*;
import org.springframework.transaction.annotation.*;

@Service
public class NoticeService
{
    @Autowired
    private NoticeMapper nmapper;

    public STData<EntityList<Map<String, Object>>> select(final Notice n) {
        final STData s = new STData();
        final EntityList<Map<String, Object>> r = (EntityList<Map<String, Object>>)new EntityList();
        final List<Map<String, Object>> nlist = this.nmapper.select(n);
        final int total = this.nmapper.selectTotal(n);
        if (nlist == null || nlist.size() == 0) {
            s.setMeow(0);
            s.setMsg("\u6ca1\u6709\u67e5\u8be2\u5230\u7ed3\u679c");
            s.setData((Object)r);
        }
        else {
            s.setMsg("\u67e5\u8be2\u6210\u529f");
            r.setTotal(total);
            r.setGetMe((List)nlist);
            s.setData((Object)r);
            s.setMeow(0);
        }
        return (STData<EntityList<Map<String, Object>>>)s;
    }

    @Transactional(rollbackFor = { Exception.class })
    public STData<Integer> insert(final Notice n) {
        final STData<Integer> r = (STData<Integer>)new STData();
        if (n.getTitle() == null || n.getTitle().isEmpty()) {
            r.setMsg("\u6807\u9898\u4e0d\u80fd\u4e3a\u7a7a");
            r.setMeow(-3001);
            return r;
        }
        if (n.getContent() == null || n.getContent().isEmpty()) {
            r.setMsg("\u5185\u5bb9\u4e0d\u80fd\u4e3a\u7a7a");
            r.setMeow(-3001);
            return r;
        }
        final Notice n2 = new Notice();
        n2.setTitle(n.getTitle());
        final List<Map<String, Object>> nlist = this.nmapper.select(n2);
        if (nlist.size() > 0) {
            r.setMsg("\u6807\u9898\u5df2\u5b58\u5728");
            r.setMeow(-3001);
            return r;
        }
        final SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        final String sttime = df.format(new Date());
        n.setCreatetime(sttime);
        n.setStstate(1);
        this.nmapper.insert(n);
        r.setMeow(0);
        r.setMsg("\u6dfb\u52a0\u6210\u529f");
        return r;
    }

    @Transactional(rollbackFor = { Exception.class })
    public STData<Integer> update(final Notice n) {
        final STData<Integer> r = (STData<Integer>)new STData();
        if (n.getId() == null) {
            r.setMsg("\u975e\u6cd5\u53c2\u6570");
            r.setMeow(-3001);
            return r;
        }
        final Notice n2 = new Notice();
        n2.setTitle(n.getTitle());
        n2.setId(n.getId());
        final List<Map<String, Object>> nlist = this.nmapper.selectExist(n2);
        if (nlist.size() > 0) {
            r.setMsg("\u6807\u9898\u5df2\u5b58\u5728");
            r.setMeow(-3001);
            return r;
        }
        this.nmapper.update(n);
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
        this.nmapper.delete(Integer.parseInt(id));
        r.setMeow(0);
        r.setMsg("\u5220\u9664\u6210\u529f");
        return r;
    }
}
