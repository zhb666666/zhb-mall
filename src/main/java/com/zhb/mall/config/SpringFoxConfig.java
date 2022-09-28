package com.zhb.mall.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
/*@EnableWebMvc:简单来说就是该类自己装配，spring不会自动装配.
 当使用@EnableWebMvc时，加载的是WebMvcConfigurationSupport中的配置项。
 当不使用@EnableWebMvc时，使用的是WebMvcAutoConfiguration引入的配置项*/
public class SpringFoxConfig {

    //访问http://localhost:8083/swagger-ui.html可以看到API文档
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("乡村生鲜网")
                .description("")
                .termsOfServiceUrl("")
                .build();
    }
}