package com.zhb.mall;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 * @Mapper不需要配置扫描地址，可以单独使用，如果有多个mapper文件的话，可以在项目启动类中加入@MapperScan(“mapper文件所在包”)
 * @Repository不可以单独使用，否则会报错误，要想用，必须配置扫描地址（@MapperScannerConfigurer）
 * @ServletComponentScan注解在SpringBootApplication上使用后，Servlet、Filter、Listener可以直接通过@WebServlet、@WebFilter、@WebListener注解自动注册，无需其他代码。
 * @MapperScan(“com.winter.dao”)注解添加以后，com.winter.dao包下面的接口类，在编译之后都会生成相应的实现类
 */
@EnableCaching//启用缓存
@EnableSwagger2
@ServletComponentScan("com.zhb.mall.filter")
@MapperScan(basePackages = {"com.zhb.mall.model.dao"})//扫描包
@SpringBootApplication


public class MallApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallApplication.class, args);
    }

}
