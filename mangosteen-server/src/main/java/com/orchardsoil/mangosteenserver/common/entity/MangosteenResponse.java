package com.orchardsoil.mangosteenserver.common.entity;

import org.springframework.http.HttpStatus;

import java.util.HashMap;

public class MangosteenResponse extends HashMap<String, Object> {

  private static final long serialVersionUID = -8713837118340960775L;

  public MangosteenResponse code(HttpStatus status) {
    this.put("code", status.value());
    return this;
  }

  public MangosteenResponse message(String message) {
    this.put("message", message);
    return this;
  }

  public MangosteenResponse data(Object data) {
    this.put("data", data);
    return this;
  }

  public MangosteenResponse success() {
    this.code(HttpStatus.OK);
    return this;
  }

  public MangosteenResponse fail() {
    this.code(HttpStatus.INTERNAL_SERVER_ERROR);
    return this;
  }

  /**
   * 参数错误
   * @return
   */
  public MangosteenResponse bad() {
    this.code(HttpStatus.BAD_REQUEST);
    return this;
  }

  @Override
  public MangosteenResponse put(String key, Object value) {
    super.put(key, value);
    return this;
  }
}