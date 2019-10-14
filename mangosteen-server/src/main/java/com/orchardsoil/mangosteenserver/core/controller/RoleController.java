package com.orchardsoil.mangosteenserver.core.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.orchardsoil.mangosteenserver.common.controller.BaseController;
import com.orchardsoil.mangosteenserver.common.domain.QueryRequest;
import com.orchardsoil.mangosteenserver.common.entity.MangosteenResponse;
import com.orchardsoil.mangosteenserver.common.exception.MangosteenException;
import com.orchardsoil.mangosteenserver.core.model.Role;
import com.orchardsoil.mangosteenserver.core.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@Validated
@RestController
/**
 * 角色管理
 */
@RequestMapping("role")
@Api("角色管理")
public class RoleController extends BaseController {

  @Autowired
  private RoleService roleService;

  private  String message;
  @PostMapping("/create")
//  @RequiresPermissions("role:add")
  @ApiOperation(value = "创建角色", notes = " 创建角色 ")
  public void addRole(@Valid Role role) throws MangosteenException {
    try {
      this.roleService.creatRole(role);
    } catch (Exception e) {
      message = "新增角色失败";
      log.error(message, e);
      throw new MangosteenException(message);
    }
  }
  /**
   * 获取角色列表
   *
   * @return
   */
  @GetMapping("/list")
  @ApiOperation(value = "角色列表", notes = " 角色列表 ")
  public MangosteenResponse getRoleList(QueryRequest queryRequest, Role role) {
    IPage<Role> roles = roleService.findRoles(role, queryRequest);
    return new MangosteenResponse().data(getDataTable(roles)).message("获取成功").success();
  }
}
