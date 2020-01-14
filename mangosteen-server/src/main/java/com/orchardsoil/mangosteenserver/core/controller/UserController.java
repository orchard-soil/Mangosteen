package com.orchardsoil.mangosteenserver.core.controller;

import com.orchardsoil.mangosteenserver.common.annotation.SysLog;
import com.orchardsoil.mangosteenserver.core.model.User;
import com.orchardsoil.mangosteenserver.core.service.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@Validated
@RestController
@RequestMapping("user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation(value = "获取用户列表", notes = " 获取用户列表 ")
    @SysLog("获取用户列表")
    @GetMapping("/list")
//  @RequiresPermissions("user:list")
    public List<User> getUserList() {
        return userService.getUserLst();
    }
}
