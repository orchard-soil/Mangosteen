package com.orchardsoil.mangosteenserver.common.exception;

public class RedisConnectException extends Exception {

  private static final long serialVersionUID = -3483733484513093751L;

  public RedisConnectException(String message) {
    super(message);
  }
}
