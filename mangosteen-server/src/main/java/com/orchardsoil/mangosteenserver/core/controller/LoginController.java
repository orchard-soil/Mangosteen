package com.orchardsoil.mangosteenserver.core.controller;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.orchardsoil.mangosteenserver.common.authentication.JWTToken;
import com.orchardsoil.mangosteenserver.common.authentication.JWTUtil;
import com.orchardsoil.mangosteenserver.common.entity.MangosteenResponse;
import com.orchardsoil.mangosteenserver.common.exception.MangosteenException;
import com.orchardsoil.mangosteenserver.common.properties.MangosteenProperties;
import com.orchardsoil.mangosteenserver.common.utils.BaseUtil;
import com.orchardsoil.mangosteenserver.common.utils.DateUtil;
import com.orchardsoil.mangosteenserver.common.utils.MD5Util;
import com.orchardsoil.mangosteenserver.core.model.User;
import com.orchardsoil.mangosteenserver.core.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotBlank;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Validated
@Api(description = "用户管理")
@RestController
public class LoginController {
  @Autowired
  private UserService userService;

  @Autowired
  private MangosteenProperties properties;

  @Autowired
  DefaultKaptcha defaultKaptcha;


  @PostMapping("/login")
  @ApiOperation(value = "用户登录", notes = " 用户登录 ")
  @ApiImplicitParams(
      {
          @ApiImplicitParam(name = "username", value = "用户名", required = true, dataType = "String"),
          @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String"),
          @ApiImplicitParam(name = "vrifyCode", value = "验证码", required = true, dataType = "String")
      }
  )
//  @Limit(key = "login", period = 60, count = 20, name = "登录接口", prefix = "limit")
  public MangosteenResponse login(
      @RequestParam(value = "username") String username,
      @RequestParam(value = "password") String password,
      @RequestParam(value = "vrifyCode") String vrifyCode,
      HttpServletRequest request) throws Exception {
    // 从session 中获取验证码
    String verificationCodeIn = (String) request.getSession().getAttribute("vrifyCode");
    // 删除
    request.getSession().removeAttribute("vrifyCode");
    // 判断验证码是否正确
    if (StringUtils.isEmpty(vrifyCode) || StringUtils.isEmpty(verificationCodeIn) || !verificationCodeIn.equals(vrifyCode)) {
      throw new MangosteenException("验证码错误，或已失效");
    }
    username = StringUtils.lowerCase(username);
    password = MD5Util.encrypt(username, password);

    final String errorMessage = "用户名或密码错误";
    User user = this.userService.findByName(username);

    if (user == null)
      throw new MangosteenException(errorMessage);
    if (!StringUtils.equals(user.getPassword(), password))
      throw new MangosteenException(errorMessage);
//    if (User.STATUS_LOCK.equals(user.getStatus()))
//      throw new FebsException("账号已被锁定,请联系管理员！");

    // 设置Token 到 JWT
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
   * @param httpServletRequest
   * @param httpServletResponse
   * @throws Exception
   */
  @ApiOperation(value = "获取验证码", notes = " 获取登录验证码 ")
  @GetMapping(value = "images/captcha", produces = "image/jpg")
  public void captcha(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
      throws Exception {
    byte[] captchaChallengeAsJpeg = null;
    ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
    try {
      // 生产验证码字符串并保存到session中
      String createText = defaultKaptcha.createText();
      httpServletRequest.getSession().setAttribute("vrifyCode", createText);
      // 使用生成的验证码字符串返回一个BufferedImage对象并转为byte写入到byte数组中
      BufferedImage challenge = defaultKaptcha.createImage(createText);
      ImageIO.write(challenge, "jpg", jpegOutputStream);
    } catch (IllegalArgumentException e) {
      httpServletResponse.sendError(HttpServletResponse.SC_NOT_FOUND);
      return;
    }

    // 定义response输出类型为image/jpeg类型，使用response输出流输出图片的byte数组
    captchaChallengeAsJpeg = jpegOutputStream.toByteArray();
    httpServletResponse.setHeader("Cache-Control", "no-store");
    httpServletResponse.setHeader("Pragma", "no-cache");
    httpServletResponse.setDateHeader("Expires", 0);
    httpServletResponse.setContentType("image/jpeg");
    ServletOutputStream responseOutputStream = httpServletResponse.getOutputStream();
    responseOutputStream.write(captchaChallengeAsJpeg);
    responseOutputStream.flush();
    responseOutputStream.close();
  }

  /**
   * 用户注册
   *
   * @param username
   * @param password
   * @throws Exception
   */
  @ApiOperation(value = "用户注册", notes = " 用户注册 ")
  @ApiImplicitParams({
      @ApiImplicitParam(name = "username", value = "用户名", required = true, dataType = "String"),
      @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String")
  })
  @PostMapping("regist")
  public MangosteenResponse regist(
      @RequestParam(value = "username", required = true) String username,
      @RequestParam(value = "password", required = true) String password) throws Exception {
    this.userService.regist(username, password);
    return new MangosteenResponse().message("用户注册成功").success();
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
