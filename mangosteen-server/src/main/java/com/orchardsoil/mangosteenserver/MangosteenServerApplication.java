package com.orchardsoil.mangosteenserver;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableTransactionManagement
@EnableScheduling
@EnableAsync
@EnableSwagger2
@MapperScan(" com.orchardsoil.mangosteenserver.core.mapper")
public class MangosteenServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MangosteenServerApplication.class, args);
    }

}
