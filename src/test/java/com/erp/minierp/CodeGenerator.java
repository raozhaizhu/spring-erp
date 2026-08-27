package com.erp.minierp;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.TemplateType;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

public class CodeGenerator {
    public static void main(String[] args) {
        FastAutoGenerator.create(
                        "jdbc:mysql://localhost:3306/spring_erp?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=false&serverTimezone=GMT%2B8",
                        "root",
                        "123456"
                )
                // 显式绑定 Freemarker 引擎！
                .templateEngine(new FreemarkerTemplateEngine())

                // 1. 全局配置
                .globalConfig(builder -> {
                    builder.author("raozhaizhu")
                            .outputDir(System.getProperty("user.dir") + "/src/main/java");
                })
                // 2. 包名配置
                .packageConfig(builder -> {
                    builder.parent("com.erp.minierp")
                            .entity("datasource.entity")
                            .mapper("mapper")
                            .service("service")
                            .controller("controller");
                })
                // 3. 模板配置：禁用 Controller 和 Service/ServiceImpl 模板
                .templateConfig(builder -> {
                    builder.disable(TemplateType.CONTROLLER);
                    builder.disable(TemplateType.SERVICE);
                    builder.disable(TemplateType.SERVICE_IMPL);
                })
                // 4. 策略配置
                .strategyConfig(builder -> {
                    builder.addInclude("material", "material_category", "depot", "partner") // 对应数据库里的真实表名
                            .entityBuilder()
                            .enableLombok()
                            .idType(IdType.AUTO)
                            .enableTableFieldAnnotation()
                            .enableFileOverride()
                            .mapperBuilder()
                            .enableFileOverride();
                })
                // 执行生成
                .execute();

        System.out.println("====== 数据库表代码生成完毕！======");
    }
}