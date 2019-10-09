package com.orchardsoil.mangosteenserver.common.authentication;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.apache.shiro.mgt.SecurityManager;

import javax.servlet.Filter;
import java.util.LinkedHashMap;

@Slf4j
@Configuration
public class ShiroConfig {
  @Bean
  public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
    ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
    // 设置 securityManager
    shiroFilterFactoryBean.setSecurityManager(securityManager);

    // 在 Shiro过滤器链上加入 JWTFilter
    LinkedHashMap<String, Filter> filters = new LinkedHashMap<>();
    filters.put("jwt", new JWTFilter());
    shiroFilterFactoryBean.setFilters(filters);

    LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
    // 所有请求都要经过 jwt过滤器
    filterChainDefinitionMap.put("/**", "jwt");

    shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
    return shiroFilterFactoryBean;
  }

  @Bean
  public SecurityManager securityManager() {
    DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
    log.info("配置 SecurityManager，并注入 shiroRealm");
    // 配置 SecurityManager，并注入 shiroRealm
    securityManager.setRealm(shiroRealm());
    return securityManager;
  }

  @Bean
  public ShiroRealm shiroRealm() {
    // 配置 Realm
    return new ShiroRealm();
  }

  @Bean
  public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
    AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
    authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
    return authorizationAttributeSourceAdvisor;
  }
}
