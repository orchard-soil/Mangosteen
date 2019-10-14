package com.orchardsoil.mangosteenserver.common.domain;

public class SystemConstant {
  private static String str = "mangosteen";
  // user缓存前缀
  public static final String USER_CACHE_PREFIX = str + "cache.user.";
  // user角色缓存前缀
  public static final String USER_ROLE_CACHE_PREFIX = str + "cache.user.role.";
  // user权限缓存前缀
  public static final String USER_PERMISSION_CACHE_PREFIX = str + "cache.user.permission.";
  // user个性化配置前缀
  public static final String USER_CONFIG_CACHE_PREFIX = str + "cache.user.config.";
  // token缓存前缀
  public static final String TOKEN_CACHE_PREFIX = str + "cache.token.";

  // 存储在线用户的 zset前缀
  public static final String ACTIVE_USERS_ZSET_PREFIX = str + "user.active";


  // 排序规则： descend 降序
  public static final String ORDER_DESC = "descend";
  // 排序规则： ascend 升序
  public static final String ORDER_ASC = "ascend";
}
