package com.ruoyi.web.controller.jianguan.business.userauth;

import com.ruoyi.common.core.domain.object.ResponseBase;
import com.ruoyi.jianguan.business.userauth.service.IUserProjectService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/userProject")
public class UserProjectController {

    private final IUserProjectService userProjectService;

    /**
     * 根据项目ID查询其下属工区对应的所有用户信息
     * @param projectId
     * @return
     */
    @ApiOperation(value = "通过id删除一条数据")
    @GetMapping(value = "/getUsersByProjectId/{projectId}", produces = "application/json;charset=UTF-8")
    public ResponseBase getUsersByProjectId(@PathVariable("projectId") Long projectId) {
        return ResponseBase.success(userProjectService.getUsersByProjectId(projectId));
    }
}
