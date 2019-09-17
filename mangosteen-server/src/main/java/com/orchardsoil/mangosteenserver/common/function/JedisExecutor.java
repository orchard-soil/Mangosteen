package com.orchardsoil.mangosteenserver.common.function;


import com.orchardsoil.mangosteenserver.common.exception.RedisConnectException;

@FunctionalInterface
public interface JedisExecutor<T, R> {
    R excute(T t) throws RedisConnectException;
}
