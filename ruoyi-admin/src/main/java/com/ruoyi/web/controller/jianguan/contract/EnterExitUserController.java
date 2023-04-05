package com.ruoyi.web.controller.jianguan.contract;

import com.github.pagehelper.PageInfo;
import com.ruoyi.common.core.domain.object.ResponseBase;
import com.ruoyi.jianguan.contract.domain.dto.EnterExitUserPageDTO;
import com.ruoyi.jianguan.contract.domain.entity.EnterExitUser;
import com.ruoyi.jianguan.contract.service.EnterExitUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 进退场人员
 *
 * @author qiaoxulin
 * @date 2022-05-28
 */
@Api(value = "进退场人员", tags = {"进退场人员"})
@RestController
@RequestMapping("/web/api/v1/enterExitUser")
public class EnterExitUserController {


    @Autowired
    private EnterExitUserService enterExitUserService;


    /**
     * 分页查询人员一览
     *
     * @param pageDto
     */
    @PostMapping(value = "/page", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "分页查询人员一览")
    public PageInfo<EnterExitUser> getPage(@RequestBody EnterExitUserPageDTO pageDto) {
        return enterExitUserService.getPageInfo(pageDto);
    }

    /**
     *人员批量退场
     *
     * @param userIds
     */
    @PostMapping(value = "/exitUsers", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "人员批量退场")
    public ResponseBase exitUsers(@RequestBody List<Long> userIds) {
        return enterExitUserService.exitUsers(userIds);
    }


}
