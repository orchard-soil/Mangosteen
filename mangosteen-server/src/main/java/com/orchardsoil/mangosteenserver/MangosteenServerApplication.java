package com.orchardsoil.mangosteenserver;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@EnableScheduling
@EnableAsync
@MapperScan(" com.orchardsoil.mangosteenserver.core.mapper")
public class MangosteenServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MangosteenServerApplication.class, args);
    }

}
