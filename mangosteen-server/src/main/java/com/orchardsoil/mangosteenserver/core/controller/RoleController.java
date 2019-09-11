package com.orchardsoil.mangosteenserver.core.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.orchardsoil.mangosteenserver.common.controller.BaseController;
import com.orchardsoil.mangosteenserver.common.domain.QueryRequest;
import com.orchardsoil.mangosteenserver.common.entity.MangosteenResponse;
import com.orchardsoil.mangosteenserver.core.model.Role;
import com.orchardsoil.mangosteenserver.core.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@Validated
@RestController
/**
 * 角色管理
 */
public class RoleController extends BaseController {

  @Autowired
  private RoleService roleService;

  /**
   * 获取角色列表
   *
   * @return
   */
  @GetMapping("/roles")
  @ApiOperation(value = "角色列表", notes = " 角色列表 ")
  public MangosteenResponse getRoleList(QueryRequest queryRequest, Role role) {
    IPage<Role> roles = roleService.findRoles(role, queryRequest);
    return new MangosteenResponse().data(getDataTable(roles)).message("获取成功").success();
  }
}
