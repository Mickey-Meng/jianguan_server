package com.ruoyi.web.controller.jianguan.zlsk.strestservice.map.service;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONException;
import cn.hutool.json.JSONObject;
import com.ruoyi.web.controller.jianguan.zlsk.strestservice.map.mapper.MapPermissionMapper;
import com.ruoyi.web.controller.jianguan.zlsk.strestservice.map.model.MapPermission;
import org.springframework.stereotype.*;
import org.springframework.beans.factory.annotation.*;
import com.ruoyi.web.controller.jianguan.zlsk.stsupporter.stctk.model.*;
import java.text.*;
import java.util.*;

@Service
public class MapPermissionService
{
    @Autowired
    private MapPermissionMapper mpMapper;

    public EntityList<Map<String, Object>> selectMapPermission(final MapPermission mp) {
        List<Map<String, Object>> rs = null;
        final EntityList<Map<String, Object>> el = (EntityList<Map<String, Object>>)new EntityList();
        rs = this.mpMapper.selectMapPermission(mp);
        el.setGetMe((List)rs);
        el.setMeow(0);
        return el;
    }

    public EntityList<Map<String, Object>> query(final String type) {
        List<Map<String, Object>> rs = null;
        final EntityList<Map<String, Object>> el = (EntityList<Map<String, Object>>)new EntityList();
        String sql = "";
        final int t = Integer.parseInt(type);
        if (t == 0) {
            sql += " select  * from ss_f_groups where STSTATE = 1 and  CODE <> '1'";
        }
        else if (t == 1) {
            sql += " select  * from ss_f_roles where STSTATE = 1";
        }
        else if (t == 2) {
            sql += " SELECT t1.`NAME`,t3.`NAME` USERNAME ,t2.USERID,t1.ID FROM (select * from ss_f_groups where STSTATE = 1 and  CODE <> '1') t1 LEFT JOIN ss_f_user_group t2 ON t1.ID = t2.GROUPID LEFT join (select * from ss_f_users where STSTATE = 1 )t3 on t3.ID = t2.USERID";
        }
        rs = this.mpMapper.query(sql);
        el.setGetMe((List)rs);
        el.setMeow(0);
        return el;
    }

    public STData insertMapPermission(final String mps) throws JSONException {
        final JSONArray ja = new JSONArray(mps);
        for (int i = 0; i < ja.size(); ++i) {
            final MapPermission mp = new MapPermission();
            final JSONObject jo = ja.getJSONObject(i);
            mp.setAssociateId(jo.getInt("associateId"));
            mp.setmId(jo.getInt("mId"));
            mp.setPtype(jo.getInt("ptype"));
            final SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            final String sttime = df.format(new Date());
            mp.setCreatetime(sttime);
            this.mpMapper.insertMapPermission(mp);
        }
        final STData sd = new STData();
        final Map m = new HashMap();
        m.put("success", true);
        sd.setData((Object)m);
        return sd;
    }

    public STData editMapPermission(final String mid, final String mps) throws JSONException {
        this.mpMapper.delMapPermission(Integer.parseInt(mid));
        final JSONArray ja = new JSONArray(mps);
        for (int i = 0; i < ja.size(); ++i) {
            final MapPermission mp = new MapPermission();
            final JSONObject jo = ja.getJSONObject(i);
            mp.setAssociateId(jo.getInt("associateId"));
            mp.setID(jo.getInt("id"));
            mp.setmId(jo.getInt("mId"));
            mp.setPtype(jo.getInt("ptype"));
            final SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            final String sttime = df.format(new Date());
            mp.setCreatetime(sttime);
            this.mpMapper.insertMapPermission(mp);
        }
        final STData sd = new STData();
        final Map m = new HashMap();
        m.put("success", true);
        sd.setData((Object)m);
        return sd;
    }

    public EntityList<Map<String, Object>> delMapPermission(final String id, final String type, final String ptype, final String aid) {
        final EntityList<Map<String, Object>> el = (EntityList<Map<String, Object>>)new EntityList();
        int end = 0;
        if (type.equals("1")) {
            end = this.mpMapper.delMapPermissionByAid(Integer.parseInt(id));
        }
        else {
            end = this.mpMapper.delMapPermissionByMid(Integer.parseInt(id), Integer.parseInt(ptype), Integer.parseInt(aid));
        }
        if (end >= 0) {
            el.setMeow(0);
        }
        else {
            el.setMeow(-1004);
            el.setMsg("\u66f4\u65b0\u5931\u8d25");
        }
        return el;
    }

    public EntityList<Map<String, Object>> getMyMap(final String type, final String userid, final String groupId, final String rolesId) {
        List<Map<String, Object>> rs = null;
        final EntityList<Map<String, Object>> el = (EntityList<Map<String, Object>>)new EntityList();
        if (type == null || type.isEmpty()) {
            el.setMeow(-3001);
            el.setMsg("\u8bf7\u8bbe\u7f6e\u5173\u8054\u4f18\u5148\u7ea7\u53c2\u6570");
            return el;
        }
        final String[] ptype = type.split(",");
        String table = "";
        List<Map<String, Object>> t = this.mpMapper.selectTotal(Integer.parseInt(ptype[0]));
        int ty = 0;
        if (t.size() > 0) {
            ty = (int) t.get(0).get("ptype");
            if (ty == 1) {
                table = "ss_f_groups";
            }
            else if (ty == 2) {
                table = "ss_f_roles";
            }
            else if (ty == 3) {
                table = "ss_f_users";
            }
        }
        else {
            t = this.mpMapper.selectTotal(Integer.parseInt(ptype[1]));
            if (t.size() > 0) {
                ty = (int) t.get(0).get("ptype");
                if (ty == 1) {
                    table = "ss_f_groups";
                }
                else if (ty == 2) {
                    table = "ss_f_roles";
                }
                else if (ty == 3) {
                    table = "ss_f_users";
                }
            }
            else {
                t = this.mpMapper.selectTotal(Integer.parseInt(ptype[2]));
                if (t.size() > 0) {
                    ty = (int) t.get(0).get("ptype");
                    if (ty == 1) {
                        table = "ss_f_groups";
                    }
                    else if (ty == 2) {
                        table = "ss_f_roles";
                    }
                    else if (ty == 3) {
                        table = "ss_f_users";
                    }
                }
            }
        }
        if (table.equals("")) {
            el.setMeow(0);
            el.setMsg("\u8be5\u7528\u6237\u8fd8\u672a\u5173\u8054\u5730\u56fe\u6570\u636e");
            return el;
        }
        String sql = "SELECT t1.mId,t2.id aid, t2.`NAME`,t3.pname,t3.id pid,t4.pname cpname,t4.sttype,t4.ID,t4.parentId cparentId,t5.mpId,t5.mcrId,t6.* from  (select * from map_permission where  ptype = " + ty + " ) t1 LEFT JOIN (SELECT * from  " + table + " where STSTATE =1) t2 on t1.associateId = t2.ID LEFT JOIN (SELECT * from  map_plan where ststate =1) t3 on t1.mId = t3.ID LEFT join (SELECT * from  map_plan where ststate =1) t4 on t4.parentId = t3.ID LEFT JOIN map_record_plan t5 on t5.mpId = t4.ID LEFT JOIN map_config_record t6 on t5.mcrId = t6.ID where 1=1";
        if (ty == 1) {
            if (groupId == null || groupId.isEmpty()) {
                el.setMeow(-3001);
                el.setMsg("\u7f3a\u5c11\u7528\u6237\u4fe1\u606f");
                return el;
            }
            sql = sql + " and t2.ID in (" + groupId + ")";
        }
        else if (ty == 2) {
            if (rolesId == null || rolesId.isEmpty()) {
                el.setMeow(-3001);
                el.setMsg("\u7f3a\u5c11\u7528\u6237\u4fe1\u606f");
                return el;
            }
            final String[] r = rolesId.split(",");
            String rId = "(";
            for (int i = 0; i < r.length; ++i) {
                rId = rId + r[i] + ",";
            }
            rId = rId.substring(0, rId.length() - 1);
            rId += ")";
            sql = sql + " and t2.ID in" + rId;
        }
        else if (ty == 3) {
            if (userid == null || userid.isEmpty()) {
                el.setMeow(-3001);
                el.setMsg("\u7f3a\u5c11\u7528\u6237\u4fe1\u606f");
                return el;
            }
            sql = sql + " and t2.ID =" + userid;
        }
        sql += " order by  pid  asc,t4.storder asc";
        rs = this.mpMapper.query(sql);
        final String planSql = "SELECT * FROM map_plan WHERE ststate = 1 ORDER BY  lev,storder asc";
        List<Map<String, Object>> planrs1 = null;
        final List<Map<String, Object>> rs2 = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> rs3 = null;
        planrs1 = this.mpMapper.query(planSql);
        final String sql2 = "select  t1.mpId,t1.mcrId,t2.* from   map_record_plan t1 left join  (select * from map_config_record WHERE ststate = 1 )  t2 ON t1.mcrId = t2.ID  ORDER BY t1.ID ASC";
        rs3 = this.mpMapper.query(sql2);
        final Map<String, Object> m = new HashMap<String, Object>();
        for (int j = 0; j < planrs1.size(); ++j) {
            final Map<String, Object> list = new HashMap<String, Object>();
            if (planrs1.get(j).get("lev").toString().equals("0")) {
                final boolean e = this.isExist(rs2, "id", planrs1.get(j).get("ID").toString());
                if (!e) {
                    final Map<String, Object> mobj = this.getMy(rs, "mId", planrs1.get(j).get("ID").toString());
                    if (mobj != null) {
                        list.put("id", planrs1.get(j).get("ID").toString());
                        list.put("name", planrs1.get(j).get("pname").toString());
                        list.put("type", (planrs1.get(j).get("sttype") == null) ? "FOLDER" : planrs1.get(j).get("sttype").toString());
                        final List<Map<String, Object>> c = this.getChildren(planrs1, "parentId", planrs1.get(j).get("ID").toString(), rs3);
                        final List<Map<String, Object>> mlist = this.getMapList(rs3, "mpId", planrs1.get(j).get("ID").toString());
                        if (mlist.size() > 0) {
                            for (int k = 0; k < mlist.size(); ++k) {
                                c.add(mlist.get(k));
                            }
                        }
                        list.put("children", c);
                        rs2.add(list);
                    }
                }
            }
        }
        el.setGetMe((List)rs2);
        el.setMeow(0);
        return el;
    }

    public Map<String, Object> getMy(final List<Map<String, Object>> list, final String key, final String value) {
        if (list.size() == 0 || list == null) {
            return null;
        }
        for (int i = 0; i < list.size(); ++i) {
            if (list.get(i).get(key).toString().equals(value)) {
                return list.get(i);
            }
        }
        return null;
    }

    public List<Map<String, Object>> getChildren(final List<Map<String, Object>> list, final String key, final String value, final List<Map<String, Object>> rs1) {
        final List<Map<String, Object>> children = new ArrayList<Map<String, Object>>();
        if (list.size() == 0 || list == null) {
            return children;
        }
        for (int i = 0; i < list.size(); ++i) {
            final Map<String, Object> m = new HashMap<String, Object>();
            if (list.get(i).get(key).toString().equals(value)) {
                final boolean e = this.isExist(children, "id", list.get(i).get("ID").toString());
                if (!e) {
                    m.put("id", list.get(i).get("ID").toString());
                    m.put("name", list.get(i).get("pname").toString());
                    m.put("type", (list.get(i).get("sttype") == null) ? "FOLDER" : list.get(i).get("sttype").toString());
                    final List<Map<String, Object>> c = this.getChildren(list, "parentId", list.get(i).get("ID").toString(), rs1);
                    final List<Map<String, Object>> mlist = this.getMapList(rs1, "mpId", list.get(i).get("ID").toString());
                    if (mlist.size() > 0) {
                        for (int j = 0; j < mlist.size(); ++j) {
                            c.add(mlist.get(j));
                        }
                    }
                    m.put("children", c);
                    children.add(m);
                }
            }
        }
        return children;
    }

    public List<Map<String, Object>> getMapList(final List<Map<String, Object>> list, final String key, final String value) {
        final List<Map<String, Object>> map = new ArrayList<Map<String, Object>>();
        if (list.size() == 0 || list == null) {
            return map;
        }
        for (int i = 0; i < list.size(); ++i) {
            final Map<String, Object> m = new HashMap<String, Object>();
            if (list.get(i).get(key).toString() != null) {
                if (!list.get(i).get(key).toString().isEmpty()) {
                    if (list.get(i).get(key).toString().equals(value)) {
                        try {
                            if (list.get(i).containsKey("attrbuites")) {
                                String attrbuites = list.get(i).get("attrbuites").toString();
                                if (attrbuites != null && !attrbuites.isEmpty()) {
                                    attrbuites = attrbuites.replaceAll(" ", "");
                                    try {
                                        final JSONObject jo = new JSONObject(attrbuites);
                                        final Iterator<String> ki = (Iterator<String>)jo.entrySet();
                                        while (ki.hasNext()) {
                                            final String fn = ki.next();
                                            final Object ov = jo.get(fn);
                                            String fv = (ov == null) ? "${NULL}" : ov.toString();
                                            if (fv.equals("${NULL}")) {
                                                fv = "null";
                                            }
                                            else if (fv.startsWith("{") && fv.endsWith("}")) {
                                                m.put(fn, com.alibaba.fastjson.JSONObject.parse(fv));
                                            }
                                            else if (fv.startsWith("[") && fv.endsWith("]")) {
                                                final com.alibaba.fastjson.JSONArray ja = com.alibaba.fastjson.JSONArray.parseArray(fv);
                                                m.put(fn, ja);
                                            }
                                            else {
                                                m.put(fn, fv);
                                            }
                                        }
                                    }
                                    catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                            m.put("id", list.get(i).get("ID").toString());
                            m.put("name", list.get(i).get("serverName").toString());
                            m.put("url", list.get(i).get("url").toString());
                            m.put("type", list.get(i).get("type").toString());
                            if (list.get(i).containsKey("visiable")) {
                                if (list.get(i).get("visiable").toString() == null || list.get(i).get("visiable").toString().isEmpty()) {
                                    m.put("visible", false);
                                }
                                else {
                                    m.put("visible", Integer.parseInt(list.get(i).get("visiable").toString()) != 0);
                                }
                            }
                            else {
                                m.put("visible", false);
                            }
                            map.add(m);
                        }
                        catch (Exception ex) {}
                    }
                }
            }
        }
        return map;
    }

    public boolean isExist(final List<Map<String, Object>> list, final String key, final String value) {
        if (list.size() == 0 || list == null) {
            return false;
        }
        for (int i = 0; i < list.size(); ++i) {
            if (list.get(i).get(key).toString().equals(value)) {
                return true;
            }
        }
        return false;
    }
}
