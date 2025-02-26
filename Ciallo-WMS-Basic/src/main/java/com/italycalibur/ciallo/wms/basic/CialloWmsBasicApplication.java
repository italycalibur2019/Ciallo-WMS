package com.italycalibur.ciallo.wms.basic;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.italycalibur.ciallo.wms")
@MapperScan(basePackages = "com.italycalibur.ciallo.wms.core.models.mapper")
public class CialloWmsBasicApplication {

    public static void main(String[] args) {
        SpringApplication.run(CialloWmsBasicApplication.class, args);
    }

}
