package com.orchardsoil.mangosteenserver.common.controller;

import com.orchardsoil.mangosteenserver.core.model.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;


public class BaseController {
  protected Subject getSubject() {
    return SecurityUtils.getSubject();
  }

  protected User getCurrentUser() {
    return (User) getSubject().getPrincipal();
  }

  protected Session getSession() {
    return getSubject().getSession();
  }

  protected Session getSession(Boolean flag) {
    return getSubject().getSession(flag);
  }

  protected void login(AuthenticationToken token) {
    getSubject().login(token);
  }
}
