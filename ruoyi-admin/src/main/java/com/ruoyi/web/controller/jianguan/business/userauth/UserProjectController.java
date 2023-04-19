package com.ruoyi.web.controller.jianguan.business.userauth;

import com.ruoyi.jianguan.business.userauth.service.IUserProjectService;
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
