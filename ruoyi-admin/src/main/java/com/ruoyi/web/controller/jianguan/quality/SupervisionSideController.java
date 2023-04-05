package com.ruoyi.web.controller.jianguan.quality;

import com.ruoyi.common.core.domain.vo.EnumsVo;
import com.ruoyi.jianguan.quality.domain.dto.SupervisionSidePageDTO;
import com.ruoyi.jianguan.quality.domain.dto.SupervisionSideSaveDTO;
import com.ruoyi.jianguan.quality.service.SupervisionSideService;
import com.ruoyi.jianguan.quality.domain.vo.SupervisionSideDetailVo;
import com.ruoyi.jianguan.quality.domain.vo.SupervisionSidePageVo;
import com.github.pagehelper.PageInfo;
import com.ruoyi.common.core.domain.object.ResponseBase;
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
* 监理旁站
* @author qiaoxulin
* @date 2022-06-10
*/
@Api(value = "监理旁站", tags = {"监理旁站"})
@RestController
@RequestMapping("/web/api/v1/supervisionSide")
public class SupervisionSideController {


    @Autowired
    private SupervisionSideService  supervisionSideService;
    @Autowired
    private FlowApiService flowApiService;

    /**
    * 新增或者更新监理旁站数据
    * @param
    */
    @PostMapping(value = "/addOrUpdate", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "新增或者更新监理旁站数据")
    public ResponseBase addOrUpdate(@RequestBody @ApiParam(name = "saveDto") SupervisionSideSaveDTO saveDto) {
        return supervisionSideService.addOrUpdate(saveDto);
    }


    /**
    * 通过id删除一条监理旁站 数据
    * @param id
    */
    @GetMapping(value = "/id", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "通过id删除一条监理旁站数据")
    @Transactional(rollbackFor = Exception.class)
    public boolean removeById(@ApiParam(name = "id") Long id)  {
        flowApiService.deleteProcessInstanceByBusinessKey(String.valueOf(id));
        return supervisionSideService.removeById(id);
    }


    /**
    * 通过id获取一条监理旁站数据
    * @param id
    */
    @GetMapping(value = "/detail/id", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "通过id获取一条监理旁站数据")
    public SupervisionSideDetailVo getById(@ApiParam(name = "id") Long id)  {
        return supervisionSideService.getInfoById(id);
    }


    /**
    * 分页查询监理旁站 数据
    * @param pageDto
    */
    @PostMapping(value = "/page", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "分页查询监理旁站数据")
    public PageInfo<SupervisionSidePageVo> page(@RequestBody SupervisionSidePageDTO pageDto)  {
        return supervisionSideService.getPageInfo(pageDto);
    }

    /**
     * 获取旁站监理项目枚举
     */
    @GetMapping(value = "/sideProject/enums", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "获取旁站监理项目枚举")
    public List<EnumsVo> getSideProject() {
        return supervisionSideService.getSideProject();
    }

    /**
     * 监理旁站导出
     *
     * @param pageDto
     */
    @PostMapping(value = "/export", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "监理旁站导出")
    public void export(@RequestBody @ApiParam(name = "pageDto") SupervisionSidePageDTO pageDto, HttpServletResponse response) {
        supervisionSideService.export(pageDto, response);
    }
}
