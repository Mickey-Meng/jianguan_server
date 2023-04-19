package com.ruoyi.web.controller.jianguan.other.zlsk.strestservice.userauth.service;

import com.ruoyi.web.controller.jianguan.other.zlsk.stsupporter.stctk.model.EntityList;
import com.ruoyi.web.controller.jianguan.other.zlsk.stsupporter.stctk.model.STData;
import com.ruoyi.web.controller.jianguan.other.zlsk.strestservice.userauth.mapper.LogMapper;
import com.ruoyi.web.controller.jianguan.other.zlsk.strestservice.userauth.model.Log;
import org.springframework.stereotype.*;
import org.springframework.beans.factory.annotation.*;
import java.text.*;
import java.util.*;
import org.springframework.transaction.annotation.*;

@Service
public class LogService
{
    @Autowired
    private LogMapper lmapper;

    public STData<EntityList<Map<String, Object>>> select(final Log l) {
        final STData s = new STData();
        final EntityList<Map<String, Object>> r = (EntityList<Map<String, Object>>)new EntityList();
        final List<Map<String, Object>> list = this.lmapper.select(l);
        final int total = this.lmapper.selectTotal(l);
        if (list == null || list.size() == 0) {
            s.setMeow(0);
            s.setMsg("\u6ca1\u6709\u67e5\u8be2\u5230\u7ed3\u679c");
            s.setData((Object)r);
        }
        else {
            s.setMsg("\u67e5\u8be2\u6210\u529f");
            r.setTotal(total);
            r.setGetMe((List)list);
            s.setData((Object)r);
            s.setMeow(0);
        }
        return (STData<EntityList<Map<String, Object>>>)s;
    }

    @Transactional(rollbackFor = { Exception.class })
    public STData<Integer> insert(final Log l) {
        final STData<Integer> r = (STData<Integer>)new STData();
        if (l.getOperation() == null || l.getOperation().isEmpty()) {
            r.setMsg("\u64cd\u4f5c\u5185\u5bb9\u4e0d\u80fd\u4e3a\u7a7a");
            r.setMeow(-3001);
            return r;
        }
        if (l.getOperUserId() == null || l.getOperUserId() == 0) {
            r.setMsg("\u64cd\u4f5c\u4eba\u4e0d\u80fd\u4e3a\u7a7a");
            r.setMeow(-3001);
            return r;
        }
        final SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        final String sttime = df.format(new Date());
        l.setCreatetime(sttime);
        l.setStstate(1);
        this.lmapper.insert(l);
        r.setMeow(0);
        r.setMsg("\u6dfb\u52a0\u6210\u529f");
        return r;
    }

    @Transactional(rollbackFor = { Exception.class })
    public STData<Integer> update(final Log l) {
        final STData<Integer> r = (STData<Integer>)new STData();
        if (l.getId() == null) {
            r.setMsg("\u975e\u6cd5\u53c2\u6570");
            r.setMeow(-3001);
            return r;
        }
        this.lmapper.update(l);
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
        this.lmapper.delete(Integer.parseInt(id));
        r.setMeow(0);
        r.setMsg("\u5220\u9664\u6210\u529f");
        return r;
    }
}
