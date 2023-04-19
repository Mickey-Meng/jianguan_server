package com.ruoyi.web.controller.jianguan.other.zlsk.strestservice.map.controller;

import com.alibaba.fastjson.JSONException;
import com.ruoyi.web.controller.jianguan.other.zlsk.stframebase.controller.StControllerBase;
import com.ruoyi.web.controller.jianguan.other.zlsk.stsupporter.stctk.model.STData;
import com.ruoyi.web.controller.jianguan.other.zlsk.strestservice.map.model.MapConfigRecord;
import com.ruoyi.web.controller.jianguan.other.zlsk.strestservice.map.model.MapPermission;
import com.ruoyi.web.controller.jianguan.other.zlsk.strestservice.map.model.MapPlan;
import com.ruoyi.web.controller.jianguan.other.zlsk.strestservice.map.service.MapConfigService;
import com.ruoyi.web.controller.jianguan.other.zlsk.strestservice.map.service.MapPermissionService;
import com.ruoyi.web.controller.jianguan.other.zlsk.strestservice.map.service.MapPlanService;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;

@RequestMapping({ "/mapConfig" })
@RestController
public class STMapConfigController extends StControllerBase
{
    @Autowired
    private MapPlanService mpService;
    @Autowired
    private MapConfigService mcrService;
    @Autowired
    private MapPermissionService mperService;
    // @Autowired
   // private MapPsService mapPsService;

    @RequestMapping(value = { "/queryMapPlans" }, method = { RequestMethod.GET })
    public STData queryMapPlans(final MapPlan mp) {
        return new STData((Object)this.mpService.selectMapPlans(mp));
    }

    @RequestMapping(value = { "/addMapPlans" }, method = { RequestMethod.POST })
    public STData addMapPlans(final MapPlan mp, final String plans) throws JSONException {
        return new STData((Object)this.mpService.insertMapPlan(mp, plans));
    }

    @RequestMapping(value = { "/insertMapRecordPlan" }, method = { RequestMethod.POST })
    public STData insertMapRecordPlan(final String plans, final String visibles) throws JSONException {
        return new STData((Object)this.mpService.insertMapRecordPlan(plans, visibles));
    }

    @RequestMapping(value = { "/editMapPlans" }, method = { RequestMethod.POST })
    public STData editMapPlans(final MapPlan mp) {
        return new STData((Object)this.mpService.updateMapPlan(mp));
    }

    @RequestMapping(value = { "/delMapPlans" }, method = { RequestMethod.GET })
    public STData delMapPlans(final String id, final String parentId, final String lev) {
        return new STData((Object)this.mpService.delMapPlan(id, parentId, lev));
    }

    @RequestMapping(value = { "/queryNoPer" }, method = { RequestMethod.GET })
    public STData queryNoPer(final String mId, final String pname, final String ptype) {
        return new STData((Object)this.mpService.selectNoPer(mId, pname, ptype));
    }

    @RequestMapping(value = { "/delMap_Record_Plan" }, method = { RequestMethod.GET })
    public STData delMap_Record_Plan(final String mcrId) {
        return new STData((Object)this.mpService.delMap_Record_Plan(mcrId));
    }

    @RequestMapping(value = { "/selectPlanByPer" }, method = { RequestMethod.GET })
    public STData selectPlanByPer(final String ptype, final String aid) {
        return new STData((Object)this.mpService.selectPlanByPer(ptype, aid));
    }

    @RequestMapping(value = { "/queryMapConfigs" }, method = { RequestMethod.GET })
    public STData queryMapConfigs(final MapConfigRecord mcr) {
        return new STData((Object)this.mcrService.selectMapConfigs(mcr));
    }

    @RequestMapping(value = { "/selectNoPlans" }, method = { RequestMethod.GET })
    public STData selectNoPlans(final MapConfigRecord mr) {
        return new STData((Object)this.mcrService.selectNoPlans(mr));
    }

    @RequestMapping(value = { "/selectRecordByPlan" }, method = { RequestMethod.GET })
    public STData selectRecordByPlan(final String mpid) {
        return new STData((Object)this.mcrService.selectRecordByPlan(mpid));
    }

    @RequestMapping(value = { "/selectRecordByPlanParent" }, method = { RequestMethod.GET })
    public STData selectRecordByPlanParent(final String mpids) {
        return new STData((Object)this.mcrService.selectRecordByPlanParent(mpids));
    }

    @RequestMapping(value = { "/addMapConfig" }, method = { RequestMethod.POST })
    public STData addMapConfig(final MapConfigRecord mcr) {
        return new STData((Object)this.mcrService.insertMapConfig(mcr));
    }

    @RequestMapping(value = { "/editMapConfig" }, method = { RequestMethod.POST })
    public STData editMapConfig(final MapConfigRecord mcr) {
        return new STData((Object)this.mcrService.updateMapConfig(mcr));
    }

    @RequestMapping(value = { "/delMapConfig" }, method = { RequestMethod.GET })
    public STData delMapConfig(final String id) {
        return new STData((Object)this.mcrService.delMapMapConfig(id));
    }

    @RequestMapping(value = { "/queryMapPermission" }, method = { RequestMethod.GET })
    public STData queryMapPermission(final MapPermission mp) {
        return new STData((Object)this.mperService.selectMapPermission(mp));
    }

    @RequestMapping(value = { "/addMapPermission" }, method = { RequestMethod.POST })
    public STData addMapPermission(final String mps) throws JSONException {
        return this.mperService.insertMapPermission(mps);
    }

    @RequestMapping(value = { "/editMapPermission" }, method = { RequestMethod.POST })
    public STData editMapPermission(final String mid, final String mps) throws JSONException {
        return this.mperService.editMapPermission(mid, mps);
    }

    @RequestMapping(value = { "/delMapPermission" }, method = { RequestMethod.GET })
    public STData delMapPermission(final String id, final String type, final String ptype, final String aid) {
        return new STData((Object)this.mperService.delMapPermission(id, type, ptype, aid));
    }

    @RequestMapping(value = { "/query" }, method = { RequestMethod.GET })
    public STData query(final String type) {
        return new STData((Object)this.mperService.query(type));
    }

    @RequestMapping(value = { "/getMyMap" }, method = { RequestMethod.GET })
    public STData getMyMap(final String type, final String userid, final String groupId, final String rolesId) {
        return new STData((Object)this.mperService.getMyMap(type, userid, groupId, rolesId));
    }

    @RequestMapping(value = { "/getDataPsCg" }, method = { RequestMethod.GET })
    public STData getDataPsCg(final String gid) {
       // return new STData((Object)this.mapPsService.getDataPsCg(gid));
        return new STData();
    }

    @RequestMapping(value = { "/insertDataPsCg" }, method = { RequestMethod.GET })
    public STData insertDataPsCg(final String key, final String fdef) {
        //return new STData((Object)this.mapPsService.insertDataPsCg(key, fdef));
        return new STData();
    }

    @RequestMapping(value = { "/deleteDataPsCg" }, method = { RequestMethod.GET })
    public STData deleteDataPsCg(final String gid) {
       // return new STData((Object)this.mapPsService.deleteDataPsCg(gid));
        return new STData();
    }

    @RequestMapping(value = { "/updateDataPsCg" }, method = { RequestMethod.GET })
    public STData updateDataPsCg(final String gid, final String key, final String fdef) {
        //return new STData((Object)this.mapPsService.updateDataPsCg(gid, key, fdef));
        return new STData();
    }

    @RequestMapping(value = { "/getMapPsTc" }, method = { RequestMethod.GET })
    public STData getMapPsTc(final String gid) {
        //return new STData((Object)this.mapPsService.getMapPsTc(gid));
        return new STData();
    }

    @RequestMapping(value = { "/getDataAllTable" }, method = { RequestMethod.GET })
    public STData getDataAllTable(final String ck) {
       // return new STData((Object)this.mapPsService.getDataAllTable(ck));
        return new STData();
    }

    @RequestMapping(value = { "/uploadDataAllTable" }, method = { RequestMethod.POST })
    public STData uploadDataAllTable(final String dataInfo) {
      //  return new STData((Object)this.mapPsService.uploadDataAllTable(dataInfo));
        return new STData();
    }

    @RequestMapping(value = { "/selectDataPsfc" }, method = { RequestMethod.GET })
    public STData selectDataPsfc(final String ck) {
      //  return new STData((Object)this.mapPsService.selectDataPsfc(ck));
        return new STData();
    }

    @RequestMapping(value = { "/uploadDataPsfc" }, method = { RequestMethod.POST })
    public STData uploadDataPsfc(final String dataInfo, final String ck) {
       // return new STData((Object)this.mapPsService.uploadDataPsfc(dataInfo, ck));
        return new STData();
    }

    @RequestMapping(value = { "/selectDataFmc" }, method = { RequestMethod.GET })
    public STData selectDataFmc() {
        // return new STData((Object)this.mapPsService.selectDataFmc());
        return new STData();
    }

    @RequestMapping(value = { "/deleteDataFmc" }, method = { RequestMethod.GET })
    public STData deleteDataFmc(final String gid) {
        // return new STData((Object)this.mapPsService.deleteDataFmc(gid));
        return new STData();
    }

    @RequestMapping(value = { "/getDataBurst" }, method = { RequestMethod.GET })
    public STData getDataBurst(final String ck) {
        // return new STData((Object)this.mapPsService.getDataBurst(ck));
        return new STData();
    }

    @RequestMapping(value = { "/uploadDataBurst" }, method = { RequestMethod.POST })
    public STData uploadDataBurst(final String dataInfo, final String ck) {
        // return new STData((Object)this.mapPsService.uploadDataBurst(dataInfo, ck));
        return new STData();
    }

    @RequestMapping({ "/easySelect" })
    public STData easySelect(final String mapStr, final String table) {
        // return this.mapPsService.easySelect(mapStr, table);
        return new STData();
    }

    @RequestMapping({ "/easyDelete" })
    public STData easyDelete(final String gid, final String table) {
        // return this.mapPsService.easyDelete(gid, table);
        return new STData();
    }

    @RequestMapping(value = { "/easyUpload" }, method = { RequestMethod.POST })
    public STData easyUpload(final String mapStr, final String table) {
        // return this.mapPsService.easyUpload(mapStr, table);
        return new STData();
    }
}
