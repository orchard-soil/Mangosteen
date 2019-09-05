package com.orchardsoil.mangosteenserver.core.controller;

import com.orchardsoil.mangosteenserver.common.authentication.JWTToken;
import com.orchardsoil.mangosteenserver.common.authentication.JWTUtil;
import com.orchardsoil.mangosteenserver.common.controller.BaseController;
import com.orchardsoil.mangosteenserver.common.entity.MangosteenResponse;
import com.orchardsoil.mangosteenserver.common.properties.MangosteenProperties;
import com.orchardsoil.mangosteenserver.common.utils.BaseUtil;
import com.orchardsoil.mangosteenserver.common.utils.DateUtil;
import com.orchardsoil.mangosteenserver.common.utils.MD5Util;
import com.orchardsoil.mangosteenserver.core.model.User;
import com.orchardsoil.mangosteenserver.core.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Validated
@RestController
public class LoginController {
  @Autowired
  private UserService userService;

  @Autowired
  private MangosteenProperties properties;

  @PostMapping("/login")
//  @Limit(key = "login", period = 60, count = 20, name = "登录接口", prefix = "limit")
  public MangosteenResponse login(
      @NotBlank(message = "{required}") String username,
      @NotBlank(message = "{required}") String password, HttpServletRequest request) throws Exception {
    username = StringUtils.lowerCase(username);
    password = MD5Util.encrypt(username, password);

    final String errorMessage = "用户名或密码错误";
    User user = this.userService.findByName(username);

    if (user == null)
      throw new Exception(errorMessage);
    if (!StringUtils.equals(user.getPassword(), password))
      throw new Exception(errorMessage);
//    if (User.STATUS_LOCK.equals(user.getStatus()))
//      throw new FebsException("账号已被锁定,请联系管理员！");


    String token = BaseUtil.encryptToken(JWTUtil.sign(username, password));
    LocalDateTime expireTime = LocalDateTime.now().plusSeconds(properties.getShiro().getJwtTimeOut());
    String expireTimeStr = DateUtil.formatFullTime(expireTime);
    JWTToken jwtToken = new JWTToken(token, expireTimeStr);
//    String userId = this.saveTokenToRedis(user, jwtToken, request);
    user.setId("1");
    Map<String, Object> userInfo = this.generateUserInfo(jwtToken, user);
    return new MangosteenResponse().message("认证成功").data(userInfo);
  }
  /**
   * 验证码
   *
   * @param request
   * @param response
   * @throws Exception
   */
  @GetMapping("images/captcha")
  public void captcha(HttpServletRequest request, HttpServletResponse response) throws Exception {

  }

  @PostMapping("regist")
  public void regist(
      @NotBlank(message = "{required}") String username,
      @NotBlank(message = "{required}") String password) throws Exception {
    this.userService.regist(username, password);
  }

  /**
   * 生成前端需要的用户信息，包括：
   * 1. token
   * 2. Vue Router
   * 3. 用户角色
   * 4. 用户权限
   * 5. 前端系统个性化配置信息
   *
   * @param token token
   * @param user  用户信息
   * @return UserInfo
   */
  private Map<String, Object> generateUserInfo(JWTToken token, User user) {
    String username = user.getUsername();
    Map<String, Object> userInfo = new HashMap<>();
    userInfo.put("token", token.getToken());
    userInfo.put("exipreTime", token.getExipreAt());

    user.setPassword("it's a secret");
    userInfo.put("user", user);
    return userInfo;
  }
}
