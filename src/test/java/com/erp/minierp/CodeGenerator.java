package com.erp.minierp;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine; // 引入了

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
                // 3. 策略配置
                .strategyConfig(builder -> {
                    builder.addInclude("depot") // 对应数据库里的真实表名
                            .entityBuilder()
                            .enableLombok()
                            .idType(IdType.AUTO)
                            .enableTableFieldAnnotation();
                })
                // 执行生成
                .execute();

        System.out.println("====== 数据库表代码生成完毕！======");
    }
}