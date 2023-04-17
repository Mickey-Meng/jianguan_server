package com.ruoyi.web.controller.jianguan.zlsk.strestservice.userauth.service;

import com.ruoyi.web.controller.jianguan.zlsk.strestservice.userauth.mapper.PositionMapper;
import com.ruoyi.web.controller.jianguan.zlsk.strestservice.userauth.model.Position;
import org.springframework.stereotype.*;
import org.springframework.beans.factory.annotation.*;
import com.ruoyi.web.controller.jianguan.zlsk.stsupporter.stctk.model.*;
import java.text.*;
import java.util.*;
import org.springframework.transaction.annotation.*;

@Service
public class PositionService
{
    @Autowired
    private PositionMapper pmapper;

    public STData<EntityList<Map<String, Object>>> select(final Position p) {
        final STData s = new STData();
        final EntityList<Map<String, Object>> r = (EntityList<Map<String, Object>>)new EntityList();
        final List<Map<String, Object>> group = this.pmapper.select(p);
        if (group == null || group.size() == 0) {
            s.setMeow(0);
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
    public STData<Integer> insert(final Position p) {
        final STData<Integer> r = (STData<Integer>)new STData();
        if (p.getPositionName() == null || p.getPositionName().isEmpty()) {
            r.setMsg("\u5c97\u4f4d\u540d\u79f0\u4e0d\u80fd\u4e3a\u7a7a");
            r.setMeow(-3001);
            return r;
        }
        if (p.getPositionCode() == null || p.getPositionCode().isEmpty()) {
            r.setMsg("\u5c97\u4f4d\u7f16\u7801\u4e0d\u80fd\u4e3a\u7a7a");
            r.setMeow(-3001);
            return r;
        }
        if (p.getParentId() == null) {
            r.setMsg("\u7236\u7ea7\u4e0d\u80fd\u4e3a\u7a7a");
            r.setMeow(-3001);
            return r;
        }
        final SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        final String sttime = df.format(new Date());
        p.setCreatetime(sttime);
        p.setStstate(1);
        this.pmapper.insert(p);
        r.setMeow(0);
        r.setMsg("\u6dfb\u52a0\u6210\u529f");
        return r;
    }

    @Transactional(rollbackFor = { Exception.class })
    public STData<Integer> update(final Position p) {
        final STData<Integer> r = (STData<Integer>)new STData();
        if (p.getId() == null) {
            r.setMsg("\u975e\u6cd5\u53c2\u6570");
            r.setMeow(-3001);
            return r;
        }
        this.pmapper.update(p);
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
        this.pmapper.delete(Integer.parseInt(id));
        this.pmapper.deleteChildren(Integer.parseInt(id));
        r.setMeow(0);
        r.setMsg("\u5220\u9664\u6210\u529f");
        return r;
    }
}
