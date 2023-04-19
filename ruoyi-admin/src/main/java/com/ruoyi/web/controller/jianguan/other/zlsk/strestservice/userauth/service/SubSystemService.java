package com.ruoyi.web.controller.jianguan.other.zlsk.strestservice.userauth.service;

import com.ruoyi.web.controller.jianguan.other.zlsk.stsupporter.stctk.model.EntityList;
import com.ruoyi.web.controller.jianguan.other.zlsk.stsupporter.stctk.model.STData;
import com.ruoyi.web.controller.jianguan.other.zlsk.strestservice.userauth.mapper.SubSystemMapper;
import com.ruoyi.web.controller.jianguan.other.zlsk.strestservice.userauth.model.SubSystem;
import com.ruoyi.web.controller.jianguan.other.zlsk.strestservice.userauth.util.DateUtil;
import org.springframework.stereotype.*;
import org.springframework.beans.factory.annotation.*;
import java.util.*;

@Service
public class SubSystemService
{
    @Autowired
    private SubSystemMapper sMapper;

    public STData<EntityList<Map<String, Object>>> select(final SubSystem sys) {
        final STData s = new STData();
        final EntityList<Map<String, Object>> r = (EntityList<Map<String, Object>>)new EntityList();
        final List<Map<String, Object>> slist = this.sMapper.select(sys);
        final int total = this.sMapper.selectTotal(sys);
        if (slist == null || slist.size() == 0) {
            s.setMeow(0);
            s.setMsg("\u6ca1\u6709\u67e5\u8be2\u5230\u7ed3\u679c");
            s.setData(r);
        }
        else {
            s.setMsg("\u67e5\u8be2\u6210\u529f");
            r.setTotal(total);
            r.setGetMe((List)slist);
            s.setData(r);
            s.setMeow(0);
        }
        return (STData<EntityList<Map<String, Object>>>)s;
    }

    public STData<EntityList<Map<String, Object>>> selectSubsystemType(final SubSystem sys) {
        final STData s = new STData();
        final EntityList<Map<String, Object>> r = (EntityList<Map<String, Object>>)new EntityList();
        final List<Map<String, Object>> slist = this.sMapper.selectSubsystemType(sys);
        final int total = this.sMapper.selectTotal(sys);
        if (slist == null || slist.size() == 0) {
            s.setMeow(0);
            s.setMsg("\u6ca1\u6709\u67e5\u8be2\u5230\u7ed3\u679c");
            s.setData(r);
        }
        else {
            s.setMsg("\u67e5\u8be2\u6210\u529f");
            r.setTotal(total);
            r.setGetMe((List)slist);
            s.setData(r);
            s.setMeow(0);
        }
        return (STData<EntityList<Map<String, Object>>>)s;
    }

    public STData<Integer> insert(final SubSystem sys) throws Exception {
        final STData<Integer> r = (STData<Integer>)new STData();
        final SubSystem s = new SubSystem();
        s.setSysCode(sys.getSysCode());
        s.setSysName(sys.getSysName());
        final List<Map<String, Object>> g = this.sMapper.select(s);
        int n = 0;
        if (g == null || g.size() == 0) {
            sys.setCreatetime(DateUtil.getCurrentDate());
            sys.setStstate(1);
            n = this.sMapper.insert(sys);
            if (n != 1) {
                r.setMeow(0);
                r.setData((Integer) n);
                r.setMsg("\u6dfb\u52a0\u5931\u8d25");
                throw new Exception("\u6dfb\u52a0\u5931\u8d25");
            }
            r.setData(n);
            r.setMeow(0);
            r.setMsg("\u6dfb\u52a0\u6210\u529f");
        }
        else {
            r.setMeow(-5001);
            r.setData(n);
            r.setMsg("\u8be5\u7cfb\u7edf\u5df2\u7ecf\u5b58\u5728");
        }
        return r;
    }

    public STData<Integer> update(final SubSystem sys) throws Exception {
        final STData<Integer> r = (STData<Integer>)new STData();
        if (sys.getID() == null) {
            r.setMsg("ID\u4e0d\u80fd\u4e3a\u7a7a");
            r.setMeow(0);
            return r;
        }
        final int n = this.sMapper.updateSys(sys);
        if (n == 1) {
            r.setData(n);
            r.setMeow(0);
            r.setMsg("\u4fee\u6539\u6210\u529f");
            return r;
        }
        r.setMeow(0);
        r.setData(n);
        r.setMsg("\u4fee\u6539\u5931\u8d25");
        throw new Exception("\u4fee\u6539\u5931\u8d25");
    }

    public STData<Integer> delete(final String id) throws Exception {
        final STData<Integer> r = (STData<Integer>)new STData();
        if (id == null || id.isEmpty()) {
            r.setMsg("ID\u4e0d\u80fd\u4e3a\u7a7a");
            r.setMeow(0);
            return r;
        }
        final int n = this.sMapper.delete(Integer.parseInt(id));
        if (n == 1) {
            r.setData(n);
            r.setMeow(0);
            r.setMsg("\u5220\u9664\u6210\u529f");
            return r;
        }
        r.setMeow(0);
        r.setData(n);
        r.setMsg("\u5220\u9664\u5931\u8d25");
        throw new Exception("\u5220\u9664\u5931\u8d25");
    }
}
