package com.ruoyi.web.controller.jianguan.other.zlsk.strestservice.map.service;

import com.ruoyi.web.controller.jianguan.other.zlsk.stsupporter.stctk.model.EntityList;
import com.ruoyi.web.controller.jianguan.other.zlsk.strestservice.map.mapper.MapConfigRecordMapper;
import com.ruoyi.web.controller.jianguan.other.zlsk.strestservice.map.model.MapConfigRecord;
import org.springframework.stereotype.*;
import org.springframework.beans.factory.annotation.*;
import java.text.*;
import java.util.*;

@Service
public class MapConfigService
{
    @Autowired
    private MapConfigRecordMapper mcrMapper;

    public EntityList<Map<String, Object>> selectRecordByPlan(final String mpid) {
        List<Map<String, Object>> rs = null;
        final EntityList<Map<String, Object>> el = (EntityList<Map<String, Object>>)new EntityList();
        int mid = 0;
        if (mpid != null && !mpid.isEmpty()) {
            mid = Integer.parseInt(mpid);
        }
        rs = this.mcrMapper.selectRecordByPlan(mid);
        el.setGetMe((List)rs);
        el.setMeow(0);
        return el;
    }

    public EntityList<Map<String, Object>> selectRecordByPlanParent(final String mpids) {
        List<Map<String, Object>> rs = null;
        final EntityList<Map<String, Object>> el = (EntityList<Map<String, Object>>)new EntityList();
        if (mpids != null && !mpids.isEmpty()) {
            final String[] mp = mpids.split(",");
            String ids = "(";
            for (int i = 0; i < mp.length; ++i) {
                ids = ids + mp[i] + ",";
            }
            ids = ids.substring(0, ids.length() - 1);
            ids += ")";
            rs = this.mcrMapper.selectRecordByPlanParent(ids);
            el.setGetMe((List)rs);
        }
        el.setMeow(0);
        return el;
    }

    public EntityList<Map<String, Object>> selectMapConfigs(final MapConfigRecord mcr) {
        List<Map<String, Object>> rs = null;
        final EntityList<Map<String, Object>> el = (EntityList<Map<String, Object>>)new EntityList();
        rs = this.mcrMapper.selectMapConfigs(mcr);
        el.setGetMe((List)rs);
        el.setPageNum((int)mcr.getPageNumber());
        el.setPageSize((int)mcr.getPageSize());
        el.setMeow(0);
        final int total = this.mcrMapper.getTotal(mcr);
        el.setTotal(total);
        return el;
    }

    public EntityList<Map<String, Object>> selectNoPlans(final MapConfigRecord mr) {
        List<Map<String, Object>> rs = null;
        final EntityList<Map<String, Object>> el = (EntityList<Map<String, Object>>)new EntityList();
        rs = this.mcrMapper.selectNoPlans(mr);
        el.setGetMe((List)rs);
        el.setMeow(0);
        return el;
    }

    public EntityList<Map<String, Object>> insertMapConfig(final MapConfigRecord mcr) {
        final EntityList<Map<String, Object>> el = (EntityList<Map<String, Object>>)new EntityList();
        final SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        final String sttime = df.format(new Date());
        mcr.setCreatetime(sttime);
        final int end = this.mcrMapper.insertMapConfig(mcr);
        if (end >= 0) {
            el.setMeow(0);
        }
        else {
            el.setMeow(-1004);
            el.setMsg("\u63d2\u5165\u5931\u8d25");
        }
        return el;
    }

    public EntityList<Map<String, Object>> updateMapConfig(final MapConfigRecord mcr) {
        final EntityList<Map<String, Object>> el = (EntityList<Map<String, Object>>)new EntityList();
        final int end = this.mcrMapper.updateMapConfig(mcr);
        if (end >= 0) {
            el.setMeow(0);
        }
        else {
            el.setMeow(-1004);
            el.setMsg("\u66f4\u65b0\u5931\u8d25");
        }
        return el;
    }

    public EntityList<Map<String, Object>> delMapMapConfig(final String id) {
        final EntityList<Map<String, Object>> el = (EntityList<Map<String, Object>>)new EntityList();
        final int end = this.mcrMapper.delMapMapConfig(Integer.parseInt(id));
        if (end >= 0) {
            el.setMeow(0);
        }
        else {
            el.setMeow(-1004);
            el.setMsg("\u66f4\u65b0\u5931\u8d25");
        }
        return el;
    }
}
