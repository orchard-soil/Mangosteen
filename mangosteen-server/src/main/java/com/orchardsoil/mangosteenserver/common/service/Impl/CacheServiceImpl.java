package com.orchardsoil.mangosteenserver.common.service.Impl;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.orchardsoil.mangosteenserver.common.domain.SystemConstant;
import com.orchardsoil.mangosteenserver.common.service.CacheService;
import com.orchardsoil.mangosteenserver.common.service.RedisService;
import com.orchardsoil.mangosteenserver.core.mapper.UserMapper;
import com.orchardsoil.mangosteenserver.core.model.Menu;
import com.orchardsoil.mangosteenserver.core.model.Role;
import com.orchardsoil.mangosteenserver.core.model.User;
import com.orchardsoil.mangosteenserver.core.service.MenuService;
import com.orchardsoil.mangosteenserver.core.service.RoleService;
import com.orchardsoil.mangosteenserver.core.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("cacheService")
public class CacheServiceImpl implements CacheService {

  @Autowired
  private RedisService redisService;

  @Autowired
  private RoleService roleService;

  @Autowired
  private MenuService menuService;

  @Autowired
  private UserService userService;


  @Autowired
  private UserMapper userMapper;

  @Autowired
  private ObjectMapper mapper;

  @Override
  public void testConnect() throws Exception {
    this.redisService.exists("test");
  }

  @Override
  public User getUser(String username) throws Exception {
    String userString = this.redisService.get(SystemConstant.USER_CACHE_PREFIX + username);
    if (StringUtils.isBlank(userString))
      throw new Exception();
    else
      return this.mapper.readValue(userString, User.class);
  }

  @Override
  public List<Role> getRoles(String username) throws Exception {
    String roleListString = this.redisService.get(SystemConstant.USER_ROLE_CACHE_PREFIX + username);
    if (StringUtils.isBlank(roleListString)) {
      throw new Exception();
    } else {
      JavaType type = mapper.getTypeFactory().constructParametricType(List.class, Role.class);
      return this.mapper.readValue(roleListString, type);
    }
  }

  @Override
  public List<Menu> getPermissions(String username) throws Exception {
//    先从缓存中获取权限列表
    String permissionListString = this.redisService.get(SystemConstant.USER_PERMISSION_CACHE_PREFIX + username);
    if (StringUtils.isBlank(permissionListString)) {
      throw new Exception();
    } else {
      JavaType type = mapper.getTypeFactory().constructParametricType(List.class, Menu.class);
      return this.mapper.readValue(permissionListString, type);
    }
  }

  @Override
  public void saveUser(User user) throws Exception {
    String username = user.getUsername();
    this.deleteUser(username);
    redisService.set(SystemConstant.USER_CACHE_PREFIX + username, mapper.writeValueAsString(user));
  }

  @Override
  public void saveUser(String username) throws Exception {
    User user = userMapper.findDetail(username);
    this.deleteUser(username);
    redisService.set(SystemConstant.USER_CACHE_PREFIX + username, mapper.writeValueAsString(user));
  }

  @Override
  public void saveRoles(String username) throws Exception {
    List<Role> roleList = this.roleService.findUserRole(username);
    if (!roleList.isEmpty()) {
      this.deleteRoles(username);
      redisService.set(SystemConstant.USER_ROLE_CACHE_PREFIX + username, mapper.writeValueAsString(roleList));
    }

  }

  @Override
  public void savePermissions(String username) throws Exception {
    List<Menu> permissionList = this.menuService.findUserPermissions(username);
    if (!permissionList.isEmpty()) {
      this.deletePermissions(username);
      redisService.set(SystemConstant.USER_PERMISSION_CACHE_PREFIX + username, mapper.writeValueAsString(permissionList));
    }
  }

  @Override
  public void deleteUser(String username) throws Exception {
    username = username.toLowerCase();
    redisService.del(SystemConstant.USER_CACHE_PREFIX + username);
  }

  @Override
  public void deleteRoles(String username) throws Exception {
    username = username.toLowerCase();
    redisService.del(SystemConstant.USER_ROLE_CACHE_PREFIX + username);
  }

  @Override
  public void deletePermissions(String username) throws Exception {
    username = username.toLowerCase();
    redisService.del(SystemConstant.USER_PERMISSION_CACHE_PREFIX + username);
  }

}
