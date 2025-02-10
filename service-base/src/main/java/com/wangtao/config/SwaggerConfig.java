package com.wangtao.config;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {

    //一个Docket代表一个swagger分组: 一个分注重可以将多个匹配的Controller进行管理
    @Bean
    public Docket adminApiConfig() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("adminApi").apiInfo(adminApiInfo()).select()
                //只显示admin路径下面的页面
                .paths(Predicates.and(PathSelectors.regex("/admin/.*"))).build();
    }
    private ApiInfo adminApiInfo() {
        return new ApiInfoBuilder()
                .title("尚容宝后台管理系统-API文档")
                .description("本文档描述了尚容宝后台管理系统接口")
                .version("1.0")
                .build();
    }

    @Bean
    public Docket webApiConfig() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("webApi").apiInfo(webApiInfo()).select()
                //只显示admin路径下面的页面
                .paths(Predicates.and(PathSelectors.regex("/api/.*"))).build();
    }
    private ApiInfo webApiInfo() {
        return new ApiInfoBuilder()
                .title("尚容宝用户前台系统-API文档")
                .description("本文档描述了尚容宝用户前台系统接口")
                .version("1.0")
                .build();
    }
}
