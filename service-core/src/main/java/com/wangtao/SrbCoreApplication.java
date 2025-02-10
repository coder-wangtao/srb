package com.wangtao;

import io.swagger.annotations.Api;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@MapperScan(basePackages = "com.wangtao.mapper")
@EnableSwagger2  //启用Swagger
public class SrbCoreApplication {
    public static void main(String[] args) {
        SpringApplication.run(SrbCoreApplication.class, args);
    }
}