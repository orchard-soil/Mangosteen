package com.orchardsoil.mangosteenserver.core.controller;

import com.orchardsoil.mangosteenserver.common.controller.BaseController;
import com.orchardsoil.mangosteenserver.common.entity.MangosteenResponse;
import com.orchardsoil.mangosteenserver.common.exception.MangosteenException;
import com.orchardsoil.mangosteenserver.core.model.Menu;
import com.orchardsoil.mangosteenserver.core.service.MenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.DynamicParameter;
import io.swagger.annotations.DynamicParameters;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
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
@Api("菜单管理")
@RequestMapping("menu")
public class MenuController extends BaseController {
  @Autowired
  private MenuService menuService;
  private String message;

  @PostMapping("/create")
  @ApiOperation(value = "创建菜单", notes = " 创建菜单 ")
  @DynamicParameters(name = "Menu", properties = {
      @DynamicParameter(name = "menuName", value = "菜单名称", required = true, dataTypeClass = String.class),
      @DynamicParameter(name = "component", value = "Vue组件名称", dataTypeClass = String.class),
      @DynamicParameter(name = "path", value = "菜单url", dataTypeClass = String.class),
      @DynamicParameter(name = "perms", value = "权限", dataTypeClass = String.class),
      @DynamicParameter(name = "icon", value = "图标", dataTypeClass = String.class),
      @DynamicParameter(name = "parentId", value = "父节点", required = true, dataTypeClass = String.class),
      @DynamicParameter(name = "type", value = "类型（0：0=按钮,1=菜单）",
          required = true, dataTypeClass = String.class),
      @DynamicParameter(name = "orderNum", value = "菜单排序", dataTypeClass = Double.class)
  })
  public MangosteenResponse addMenu(@Valid Menu menu) {
    // 如果是菜单
    if (menu.getType().equals("1")) {
      log.info("添加了菜单");
      if (StringUtils.isEmpty(menu.getPath()))
        return new MangosteenResponse().message("菜单url不能为空").bad();

      if (StringUtils.isEmpty(menu.getComponent()))
        return new MangosteenResponse().message("组件地址不能为空").bad();
    }
    try {
      this.menuService.creatMenu(menu);
    } catch (Exception e) {
      message = "新增角色失败";
      log.error(message, e);
      return new MangosteenResponse().fail().message(e.getMessage());
    }
    return new MangosteenResponse().success().message("创建成功");
  }

}
