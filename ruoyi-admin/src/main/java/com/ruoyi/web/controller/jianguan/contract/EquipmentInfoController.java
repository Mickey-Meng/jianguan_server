package com.ruoyi.web.controller.jianguan.contract;

import com.github.pagehelper.PageInfo;
import com.ruoyi.jianguan.contract.domain.dto.EquipmentInfoPageDTO;
import com.ruoyi.jianguan.contract.domain.entity.EquipmentInfo;
import com.ruoyi.jianguan.contract.service.EquipmentInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
* 设备信息
* @author qiaoxulin
* @date 2022-08-13
*/
@Api(value = "设备信息", tags = {"设备信息"})
@RestController
@RequestMapping("/web/api/v1/equipmentInfo")
public class EquipmentInfoController {


    @Autowired
    private EquipmentInfoService equipmentInfoService;


    /**
    * 新增设备信息数据
    * @param
    */
    @PostMapping(value = "/addOrUpdate", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "新增设备信息数据")
    public boolean add(@RequestBody List<EquipmentInfo> equipmentInfos) {
        return equipmentInfoService.saveBatch(equipmentInfos);
    }


    /**
    * 通过id删除一条设备信息 数据
    * @param id
    */
    @GetMapping(value = "/id", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "通过id删除一条设备信息数据")
    public boolean removeById(@ApiParam(name = "id") Long id)  {
        return equipmentInfoService.removeById(id);
    }

    /**
     * 通过进场设备id删除设备信息 数据
     * @param enterId
     */
    @GetMapping(value = "/removeByEnterId", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "通过进场设备id删除设备信息")
    public boolean removeByEnterId(@ApiParam(name = "id") Long enterId)  {
        return equipmentInfoService.removeByEnterId(enterId);
    }


    /**
    * 通过id获取一条设备信息数据
    * @param id
    */
    @GetMapping(value = "/detail/id", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "通过id获取一条设备信息数据")
    public EquipmentInfo getById(@ApiParam(name = "id") Long id)  {
        return equipmentInfoService.getById(id);
    }


    /**
    * 分页查询设备信息 数据
    * @param pageDto
    */
    @PostMapping(value = "/page", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "分页查询设备信息数据")
    public PageInfo<EquipmentInfo> page(@RequestBody EquipmentInfoPageDTO pageDto)  {
        return equipmentInfoService.getPageInfo(pageDto);
    }

}
