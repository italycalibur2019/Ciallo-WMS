package com.italycalibur.ciallo.wms.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.italycalibur.ciallo.wms.core")
@MapperScan(basePackages = "com.italycalibur.ciallo.wms.core.models.mapper")
public class CialloWmsAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(CialloWmsAdminApplication.class, args);
    }

}
