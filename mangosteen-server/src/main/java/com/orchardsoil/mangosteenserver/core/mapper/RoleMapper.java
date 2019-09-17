package com.orchardsoil.mangosteenserver.core.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.orchardsoil.mangosteenserver.core.model.Role;

import java.util.List;

public interface RoleMapper extends BaseMapper<Role> {

  List<Role> findUserRole(String userName);

}
