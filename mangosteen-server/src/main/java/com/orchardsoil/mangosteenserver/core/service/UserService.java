package com.orchardsoil.mangosteenserver.core.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.orchardsoil.mangosteenserver.core.model.User;

public interface UserService extends IService<User> {
  /**
   * 通过用户名查找用户
   *
   * @param username username
   * @return user
   */
  User findByName(String username);


  /**
   * 注册用户
   *
   * @param username 用户名
   * @param password 密码
   */
  void regist(String username, String password) throws Exception;

}
