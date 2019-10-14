package com.orchardsoil.mangosteenserver.common.function;

// @FunctionalInterface注解来标记你的接口是函数式接口
@FunctionalInterface
public interface CacheSelector<T> {
  T select() throws Exception;
}
