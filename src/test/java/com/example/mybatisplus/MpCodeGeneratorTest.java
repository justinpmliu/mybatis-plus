package com.example.mybatisplus;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;

@RunWith(SpringRunner.class)
@SpringBootTest
class MpCodeGeneratorTest {
    @Test
    void generateCode() {
         generate("com.example.mybatisplus", "potluck","potluck");
    }

    private void generate(String parentPackage, String moduleName, String... tableNames) {
        FastAutoGenerator
                .create(
                        "jdbc:mysql://127.0.0.1:3306/test?useUnicode=true&useSSL=false&characterEncoding=utf8&serverTimezone=GMT%2B8",
                        "root",
                        "Test!6266")
                .globalConfig(builder -> {
                    builder.author("Justin Liu") // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            .disableOpenDir() // 默认生成后会打开文件所在位置，不想打开可以设置为false，设计这个属性的初衷应该是怕用户找不到生成的文件
                            .outputDir("/home/justin/Sandbox/codegen/java"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent(parentPackage) // 设置父包名
                            .moduleName(moduleName) // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml, "/home/justin/Sandbox/codegen/resources/mapper")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude(tableNames) // 设置需要生成的表名
                            .entityBuilder()
                                .enableLombok() // 使用 lombok
                                .naming(NamingStrategy.underline_to_camel) // 表名 -> 实体类名：使用驼峰命名
                                .columnNaming(NamingStrategy.underline_to_camel) // 数据库字段 -> 属性名：驼峰命名
                            .controllerBuilder()
                                .enableRestStyle() // 使用 @RestController 注解
                                .enableHyphenStyle(); // @RequestMapping 默认驼峰命名法 -> 改为用-分隔，更好看
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}
