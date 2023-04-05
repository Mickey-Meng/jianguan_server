package com.ruoyi.web.controller.jianguan.contract;

import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageInfo;
import com.ruoyi.common.core.domain.object.ResponseBase;
import com.ruoyi.flowable.service.FlowApiService;
import com.ruoyi.jianguan.contract.domain.dto.EquipmentExit;
import com.ruoyi.jianguan.contract.domain.dto.EquipmentExitPageDTO;
import com.ruoyi.jianguan.contract.domain.dto.EquipmentExitSaveDTO;
import com.ruoyi.jianguan.contract.domain.entity.EquipmentInfo;
import com.ruoyi.jianguan.contract.domain.vo.EquipmentExitDetailVo;
import com.ruoyi.jianguan.contract.domain.vo.EquipmentExitPageVo;
import com.ruoyi.jianguan.contract.service.EquipmentExitService;
import com.ruoyi.jianguan.contract.service.EquipmentInfoService;
import io.netty.util.internal.StringUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Objects;

/**
* 退场设备报验单
* @author qiaoxulin
* @date 2022-05-27
*/
@Api(value = "退场设备报验单", tags = {"退场设备报验单"})
@RestController
@RequestMapping("/web/api/v1/equipmentExit")
public class EquipmentExitController {


    @Autowired
    private EquipmentExitService equipmentExitService;
    @Autowired
    private FlowApiService flowApiService;
    @Autowired
    private EquipmentInfoService equipmentInfoService;

    /**
    * 新增或者更新退场设备报验单数据
    * @param
    */
    @PostMapping(value = "/addOrUpdate", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "新增或者更新退场设备报验单数据")
    public ResponseBase addOrUpdate(@RequestBody @ApiParam(name = "saveDto") EquipmentExitSaveDTO saveDto) {
        return equipmentExitService.addOrUpdate(saveDto);
    }


    /**
    * 通过id删除一条退场设备报验单 数据
    * @param id
    */
    @GetMapping(value = "/id", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "通过id删除一条退场设备报验单数据")
    @Transactional(rollbackFor = Exception.class)
    public boolean removeById(@ApiParam(name = "id") Long id)  {
        //删除 恢复设备数量
        EquipmentExit byId = equipmentExitService.getById(id);
        String equipmentInfo = byId.getEquipmentInfo();
        if (!StringUtil.isNullOrEmpty(equipmentInfo)){
            List<EquipmentInfo> equipmentNums = JSONArray.parseArray(equipmentInfo, EquipmentInfo.class);
            //加上设备数量
            for (EquipmentInfo equipment : Objects.requireNonNull(equipmentNums)) {
                EquipmentInfo equipmentInfo1 = equipmentInfoService.getById(equipment.getId());
                if (Objects.isNull(equipmentInfo1)) {
                    continue;
                }
                EquipmentInfo equip = new EquipmentInfo();
                equip.setId(equipmentInfo1.getId());
                int num = equipmentInfo1.getNum() + equipmentInfo1.getNum();
                equip.setNum(num);
                equipmentInfoService.updateById(equip);
            }
        }
        flowApiService.deleteProcessInstanceByBusinessKey(String.valueOf(id));
        return equipmentExitService.removeById(id);
    }


    /**
    * 通过id获取一条退场设备报验单数据
    * @param id
    */
    @GetMapping(value = "/detail/id", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "通过id获取一条退场设备报验单数据")
    public EquipmentExitDetailVo getById(@ApiParam(name = "id") Long id)  {
        return equipmentExitService.getInfoById(id);
    }


    /**
    * 分页查询退场设备报验单 数据
    * @param pageDto
    */
    @PostMapping(value = "/page", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "分页查询退场设备报验单数据")
    public PageInfo<EquipmentExitPageVo> page(@RequestBody EquipmentExitPageDTO pageDto)  {
        return equipmentExitService.getPageInfo(pageDto);
    }

    /**
     * 设备退场报验导出
     *
     * @param pageDto
     */
    @PostMapping(value = "/export", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "设备退场报验导出")
    public void export(@RequestBody @ApiParam(name = "pageDto") EquipmentExitPageDTO pageDto, HttpServletResponse response) {
        equipmentExitService.export(pageDto, response);
    }
}
