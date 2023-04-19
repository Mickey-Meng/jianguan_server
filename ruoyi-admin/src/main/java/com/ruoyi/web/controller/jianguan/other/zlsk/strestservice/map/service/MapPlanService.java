package com.ruoyi.web.controller.jianguan.other.zlsk.strestservice.map.service;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONException;
import cn.hutool.json.JSONObject;
import com.ruoyi.web.controller.jianguan.other.zlsk.strestservice.map.mapper.MapPlanMapper1;
import com.ruoyi.web.controller.jianguan.other.zlsk.stsupporter.stctk.model.EntityList;
import com.ruoyi.web.controller.jianguan.other.zlsk.strestservice.map.model.MapPlan;
import com.ruoyi.web.controller.jianguan.other.zlsk.strestservice.map.model.Map_Record_Plan;
import org.springframework.stereotype.*;
import org.springframework.beans.factory.annotation.*;
import java.text.*;
import java.util.*;

@Service
public class MapPlanService
{
    @Autowired
    private MapPlanMapper1 mpmapper;

    public EntityList<Map<String, Object>> selectMapPlans(final MapPlan mp) {
        List<Map<String, Object>> rs = null;
        final EntityList<Map<String, Object>> el = (EntityList<Map<String, Object>>)new EntityList();
        rs = this.mpmapper.selectMapPlans(mp);
        el.setGetMe((List)rs);
        el.setMeow(0);
        return el;
    }

    public EntityList<Map<String, Object>> selectNoPer(final String mId, final String pname, final String ptype) {
        List<Map<String, Object>> rs = null;
        final EntityList<Map<String, Object>> el = (EntityList<Map<String, Object>>)new EntityList();
        int mid = 0;
        if (mId != null && !mId.isEmpty()) {
            mid = Integer.parseInt(mId);
        }
        int pid = 1;
        if (ptype != null && !ptype.isEmpty()) {
            pid = Integer.parseInt(ptype);
        }
        rs = this.mpmapper.selectNoPer(mid, pname, pid);
        el.setGetMe((List)rs);
        el.setMeow(0);
        return el;
    }

    public EntityList<Map<String, Object>> selectPlanByPer(final String ptype, final String aid) {
        List<Map<String, Object>> rs = null;
        final EntityList<Map<String, Object>> el = (EntityList<Map<String, Object>>)new EntityList();
        String sql = "";
        if (ptype != null && !ptype.isEmpty()) {
            sql = "select t2.*,t1.id mpid ,t1.associateId from  map_permission t1 left join map_plan t2 on t1.mId = t2.ID where t1.ptype = " + ptype + "";
            if (aid != null && !aid.isEmpty()) {
                final String[] a = aid.split(",");
                String id = "(";
                for (int i = 0; i < a.length; ++i) {
                    id = id + a[i] + ",";
                }
                id = id.substring(0, id.length() - 1);
                id += ")";
                sql = sql + " and t1.associateId in " + id;
            }
            sql += " ORDER BY t1.createtime  desc";
            rs = this.mpmapper.selectPlanByPer(sql);
            el.setGetMe((List)rs);
            el.setMeow(0);
        }
        else {
            el.setMeow(-1004);
            el.setMsg("\u83b7\u53d6\u6570\u636e\u5931\u8d25");
        }
        return el;
    }

    public EntityList<Map<String, Object>> insertMapPlan(final MapPlan mp, final String plans) throws JSONException {
        final EntityList<Map<String, Object>> el = (EntityList<Map<String, Object>>)new EntityList();
        final SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        final String sttime = df.format(new Date());
        if (plans != null && !plans.isEmpty()) {
            final JSONArray ja = new JSONArray(plans);
            for (int i = 0; i < ja.size(); ++i) {
                final JSONObject jo = ja.getJSONObject(i);
                final MapPlan m = new MapPlan();
                m.setCreatetime(sttime);
                m.setLev(jo.getInt("lev"));
                m.setParentId(jo.getInt("parentid"));
                m.setPname(jo.getStr("pname"));
                m.setStstate(jo.getInt("ststate"));
                m.setStgroup(jo.getStr("stgroup"));
                m.setStorder(jo.getInt("storder"));
                this.mpmapper.insertMapPlan(m);
            }
            el.setMeow(0);
        }
        else {
            mp.setCreatetime(sttime);
            final int end = this.mpmapper.insertMapPlan(mp);
            if (end >= 0) {
                el.setMeow(0);
            }
            else {
                el.setMeow(-1004);
                el.setMsg("\u63d2\u5165\u5931\u8d25");
            }
        }
        return el;
    }

    public EntityList<Map<String, Object>> insertMapRecordPlan(final String plans, final String visibles) throws JSONException {
        final EntityList<Map<String, Object>> el = (EntityList<Map<String, Object>>)new EntityList();
        if (plans != null && !plans.isEmpty()) {
            final JSONArray ja = new JSONArray(plans);
            for (int i = 0; i < ja.size(); ++i) {
                final JSONObject jo = ja.getJSONObject(i);
                final Map_Record_Plan mrp = new Map_Record_Plan();
                mrp.setMpId(jo.getInt("mpId"));
                mrp.setMcrId(jo.getInt("mcrId"));
                this.mpmapper.insertMapRecordPlan(mrp);
            }
            el.setMeow(0);
        }
        return el;
    }

    public EntityList<Map<String, Object>> updateMapPlan(final MapPlan mp) {
        final EntityList<Map<String, Object>> el = (EntityList<Map<String, Object>>)new EntityList();
        final int end = this.mpmapper.updateMapPlan(mp);
        if (end >= 0) {
            el.setMeow(0);
        }
        else {
            el.setMeow(-1004);
            el.setMsg("\u66f4\u65b0\u5931\u8d25");
        }
        return el;
    }

    public EntityList<Map<String, Object>> delMapPlan(final String id, final String parentId, final String lev) {
        final EntityList<Map<String, Object>> el = (EntityList<Map<String, Object>>)new EntityList();
        if (lev != null && !lev.isEmpty()) {
            final int end = this.mpmapper.delAllMapPlan();
            if (end >= 0) {
                el.setMeow(0);
            }
            else {
                el.setMeow(-1004);
                el.setMsg("\u66f4\u65b0\u5931\u8d25");
            }
        }
        else {
            final int pid = Integer.parseInt(parentId);
            if (pid == 0) {
                this.mpmapper.delChildrenMapPlan(Integer.parseInt(id));
            }
            final int end2 = this.mpmapper.delMapPlan(Integer.parseInt(id));
            if (end2 >= 0) {
                el.setMeow(0);
            }
            else {
                el.setMeow(-1004);
                el.setMsg("\u66f4\u65b0\u5931\u8d25");
            }
        }
        return el;
    }

    public EntityList<Map<String, Object>> delMap_Record_Plan(final String mcrId) {
        final EntityList<Map<String, Object>> el = (EntityList<Map<String, Object>>)new EntityList();
        if (mcrId == null || mcrId.isEmpty()) {
            el.setMeow(-1004);
            el.setMsg("\u5220\u9664\u5931\u8d25");
        }
        else {
            final int end = this.mpmapper.delMap_Record_Plan(Integer.parseInt(mcrId));
            if (end >= 0) {
                el.setMeow(0);
            }
            else {
                el.setMeow(-1004);
                el.setMsg("\u5220\u9664\u5931\u8d25");
            }
        }
        return el;
    }
}
