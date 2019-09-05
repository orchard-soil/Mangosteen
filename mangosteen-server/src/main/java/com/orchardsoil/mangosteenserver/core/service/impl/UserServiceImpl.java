package com.orchardsoil.mangosteenserver.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.orchardsoil.mangosteenserver.common.utils.MD5Util;
import com.orchardsoil.mangosteenserver.core.mapper.UserMapper;
import com.orchardsoil.mangosteenserver.core.model.User;
import com.orchardsoil.mangosteenserver.core.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Slf4j
@Service("userService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
  @Override
  public User findByName(String username) {
    return this.baseMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getUsername, username));
  }

  @Override
  @Transactional
  public void regist(String username, String password) throws Exception {
    User user = new User();
    user.setPassword(MD5Util.encrypt(username, password));
    user.setUsername(username);
    user.setCreateTime(new Date());
    user.setStatus(User.STATUS_VALID);
    user.setSsex(User.SEX_UNKNOW);
    user.setAvatar(User.DEFAULT_AVATAR);
    user.setDescription("注册用户");
    this.save(user);
  }

}
