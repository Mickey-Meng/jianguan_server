package com.ruoyi.web.controller.jianguan.userauth;

import com.ruoyi.common.core.domain.object.ResponseBase;
import com.ruoyi.jianguan.userauth.domain.dto.UserProjectDto;
import com.ruoyi.jianguan.userauth.service.IUserProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping()
public class UserProjectController {

    private final IUserProjectService userProjectService;


}
