package com.ruoyi.web.controller.jianguan.contract;

import com.github.pagehelper.PageInfo;
import com.ruoyi.common.core.domain.object.ResponseBase;
import com.ruoyi.common.core.domain.vo.EnumsVo;
import com.ruoyi.jianguan.contract.domain.dto.EquipmentEnterPageDTO;
import com.ruoyi.jianguan.contract.domain.dto.EquipmentEnterSaveDTO;
import com.ruoyi.jianguan.contract.domain.vo.EquipmentEnterDetailVo;
import com.ruoyi.jianguan.contract.domain.vo.EquipmentEnterPageVo;
import com.ruoyi.jianguan.contract.service.EquipmentEnterService;
import com.ruoyi.jianguan.contract.service.EquipmentInfoService;
import com.ruoyi.flowable.service.FlowApiService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
* 设备进场报验
* @author qiaoxulin
* @date 2022-05-26
*/
@Api(value = "设备进场报验", tags = {"设备进场报验"})
@RestController
@RequestMapping("/web/api/v1/equipmentEnter")
public class EquipmentEnterController {


    @Autowired
    private EquipmentEnterService equipmentEnterService;
    @Autowired
    private FlowApiService flowApiService;
    @Autowired
    private EquipmentInfoService equipmentInfoService;

    /**
    * 新增或者更新设备进场报验数据
    * @param
    */
    @PostMapping(value = "/addOrUpdate", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "新增或者更新设备进场报验数据")
    public ResponseBase addOrUpdate(@RequestBody @ApiParam(name = "saveDto") EquipmentEnterSaveDTO saveDto) {
        return equipmentEnterService.addOrUpdate(saveDto);
    }


    /**
    * 通过id删除一条设备进场报验 数据
    * @param id
    */
    @GetMapping(value = "/id", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "通过id删除一条设备进场报验数据")
    @Transactional(rollbackFor = Exception.class)
    public boolean removeById(@ApiParam(name = "id") Long id)  {
        //删除设备信息
        equipmentInfoService.removeByEnterId(id);
        flowApiService.deleteProcessInstanceByBusinessKey(String.valueOf(id));
        return equipmentEnterService.removeById(id);
    }


    /**
    * 通过id获取一条设备进场报验数据
    * @param id
    */
    @GetMapping(value = "/detail/id", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "通过id获取一条设备进场报验数据")
    public EquipmentEnterDetailVo getById(@ApiParam(name = "id") Long id)  {
        return equipmentEnterService.getInfoById(id);
    }


    /**
    * 分页查询设备进场报验 数据
    * @param pageDto
    */
    @PostMapping(value = "/page", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "分页查询设备进场报验数据")
    public PageInfo<EquipmentEnterPageVo> page(@RequestBody EquipmentEnterPageDTO pageDto)  {
        return equipmentEnterService.getPageInfo(pageDto);
    }

    /**
     * 获取设备类型枚举
     */
    @GetMapping(value = "/equipment/enums", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "获取设备类型枚举")
    public List<EnumsVo> getEquipmentEnum() {
        return equipmentEnterService.getEquipmentEnum();
    }


    /**
     * 设备进场报验导出
     *
     * @param pageDto
     */
    @PostMapping(value = "/export", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "设备进场报验导出")
    public void export(@RequestBody @ApiParam(name = "pageDto") EquipmentEnterPageDTO pageDto, HttpServletResponse response) {
        equipmentEnterService.export(pageDto, response);
    }
}
