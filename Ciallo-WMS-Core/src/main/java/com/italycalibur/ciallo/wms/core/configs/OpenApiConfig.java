package com.italycalibur.ciallo.wms.core.configs;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
//import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description: knife4j 配置类
 * @author dhr
 * @date 2025-02-24 13:26:37
 * @version 1.0
 */
@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                // 接口文档标题
                .info(new Info().title("Ciallo-WMS API接口文档")
                        // 接口文档简介
                        .description("这是基于Knife4j OpenApi3的接口文档")
                        // 接口文档版本
                        .version("v1.0")
                        // 开发者联系方式
                        .contact(new Contact().name("italycalibur").email("920893925@qq.com")))
                .externalDocs(new ExternalDocumentation().description("SpringBoot基础框架").url("http://127.0.0.1:9080")
                );
    }

    /* 以下分组和资源映射都可省略
    @Bean
    public GroupedOpenApi systemApi() {
        return GroupedOpenApi.builder().group("管理模块")
                .pathsToMatch("/system/**")
                .packagesToScan("com.italycalibur.ciallo.wms.admin")
                .build();
    }
    */
}
