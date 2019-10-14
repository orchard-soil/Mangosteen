package com.orchardsoil.mangosteenserver;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@EnableTransactionManagement
@EnableScheduling
@EnableAsync
<<<<<<< HEAD
//排除 原生Druid的快速配置类
@SpringBootApplication(exclude = DruidDataSourceAutoConfigure.class)
=======
//@EnableSwagger2
//排除 原生Druid的快速配置类
@SpringBootApplication(exclude = DruidDataSourceAutoConfigure.class)
@MapperScan("com.orchardsoil.mangosteenserver.core.mapper")
>>>>>>> 1b02a976066a2fe4c0569ed163ee4f1e246391d7
public class MangosteenServerApplication {

  public static void main(String[] args) {
    SpringApplication.run(MangosteenServerApplication.class, args);
  }

}
