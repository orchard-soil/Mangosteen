package com.orchardsoil.mangosteenserver.core.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.orchardsoil.mangosteenserver.core.model.Role;

public interface RoleMapper extends BaseMapper<Role> {

  IPage<Role> findUserRole(String userName);

}
