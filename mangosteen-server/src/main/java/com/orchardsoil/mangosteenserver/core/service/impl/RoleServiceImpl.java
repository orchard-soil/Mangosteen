package com.orchardsoil.mangosteenserver.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.orchardsoil.mangosteenserver.common.domain.QueryRequest;
import com.orchardsoil.mangosteenserver.common.utils.SortUtil;
import com.orchardsoil.mangosteenserver.core.mapper.RoleMapper;
import com.orchardsoil.mangosteenserver.core.mapper.RoleMenuMapper;
import com.orchardsoil.mangosteenserver.core.model.Role;
import com.orchardsoil.mangosteenserver.core.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Slf4j
@Service("RoleService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {
  @Autowired
  private RoleMenuMapper roleMenuMapper;

  @Override
  public IPage<Role> findRoles(Role role, QueryRequest request) {
    try {
      LambdaQueryWrapper<Role> queryWrapper = new LambdaQueryWrapper<>();

      if (StringUtils.isNotBlank(role.getRoleName())) {
        queryWrapper.eq(Role::getRoleName, role.getRoleName());
      }
      if (StringUtils.isNotBlank(role.getCreateTimeFrom()) && StringUtils.isNotBlank(role.getCreateTimeTo())) {
        queryWrapper
            .ge(Role::getCreateTime, role.getCreateTimeFrom())
            .le(Role::getCreateTime, role.getCreateTimeTo());
      }
      Page<Role> page = new Page<>();
      SortUtil.handlePageSort(request, page, true);
      return this.page(page, queryWrapper);
    } catch (Exception e) {
      log.error("获取角色信息失败", e);
      return null;
    }
  }

  @Override
  public void creatRole(Role role) {
    role.setCreateTime(new Date());
    this.save(role);
  }

  @Override
  public List<Role> findUserRole(String userName) {
    return this.baseMapper.findUserRole(userName);
  }
}
