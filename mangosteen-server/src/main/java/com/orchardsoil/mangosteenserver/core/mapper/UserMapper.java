package com.orchardsoil.mangosteenserver.core.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.orchardsoil.mangosteenserver.core.model.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper extends BaseMapper<User> {
  /**
   * 获取单个用户详情
   *
   * @param username 用户名
   * @return 用户信息
   */
  User findDetail(String username);

  IPage<User> findUserDetail(Page page, @Param("user") User user);
}