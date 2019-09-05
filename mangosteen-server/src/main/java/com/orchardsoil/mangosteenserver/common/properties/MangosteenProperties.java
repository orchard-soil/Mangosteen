package com.orchardsoil.mangosteenserver.common.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "mangosteen")
public class MangosteenProperties {
  private ShiroProperties shiro = new ShiroProperties();

  private boolean openAopLog = true;

}
