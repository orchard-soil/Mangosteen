package com.orchardsoil.mangosteenserver.core.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.orchardsoil.mangosteenserver.common.domain.QueryRequest;
import com.orchardsoil.mangosteenserver.core.model.Role;

import java.util.List;

public interface RoleService extends IService<Role> {
  IPage<Role> findRoles(Role role, QueryRequest request);

  void creatRole(Role role);

  List<Role> findUserRole(String userName);
}
