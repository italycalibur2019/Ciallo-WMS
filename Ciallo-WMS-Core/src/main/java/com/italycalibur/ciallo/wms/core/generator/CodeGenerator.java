package com.italycalibur.ciallo.wms.core.generator;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.converts.PostgreSqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.querys.PostgreSqlQuery;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.query.SQLQuery;
import com.italycalibur.ciallo.wms.core.models.BaseEntity;

import java.nio.file.Paths;
import java.util.Collections;

/**
 * @author dhr
 * @version 1.0
 * @date 2025-02-16 08:32:55
 * @description: Mybatis Plus 代码生成器
 */
public class CodeGenerator {
    public static final String schema = "sys";

    public static final String url = "jdbc:postgresql://localhost:5432/wms?currentSchema=" + schema;

    public static final String username = "postgres";

    public static final String password = "123456";

    public static final String outPutPath = Paths.get(System.getProperty("user.dir")) + "/Ciallo-WMS-Core";

    public static void main(String[] args) {
        FastAutoGenerator.create(url,username,password)
                // 数据源配置
                .dataSourceConfig(builder -> builder
                        .databaseQueryClass(SQLQuery.class)
                        .typeConvert(new PostgreSqlTypeConvert())
                        .dbQuery(new PostgreSqlQuery())
                        .schema(schema)
                )
                // 全局配置
                .globalConfig(builder -> builder
                        .author("italycalibur")
                        .outputDir(outPutPath + "/src/main/java")
                        .commentDate("yyyy-MM-dd HH:mm:ss")
                        .dateType(DateType.ONLY_DATE)
                        .enableSpringdoc()
                )
                // 包配置
                .packageConfig(builder -> builder
                        .parent("com.italycalibur.ciallo.wms.core")
                        .entity("models.entity")
                        .mapper("models.mapper")
                        .service("service")
                        .serviceImpl("service.impl")
                        .xml("mapper")
                        .pathInfo(Collections.singletonMap(OutputFile.xml, outPutPath + "/src/main/resources"))
                )
                // 策略配置
                .strategyConfig(builder -> builder
                        .enableSchema()
                        .addInclude("sys_permission")
                        .addTablePrefix("sys_")
                        .entityBuilder()
                        .enableLombok()
                        .superClass(BaseEntity.class)
                        .enableTableFieldAnnotation()
                        .enableFileOverride()
                        .controllerBuilder()
                        .disable()
                        .serviceBuilder()
                        .disableServiceImpl()
                        .disable()
                        .mapperBuilder()
                        .disableMapperXml()
                        .disable()
                )
                // 模板引擎配置
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();
    }
}
