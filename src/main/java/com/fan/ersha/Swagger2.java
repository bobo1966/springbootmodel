package com.fan.ersha;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author: 范
 * @description: Swagger在线文档
 * @date: Created in 11:12 2018/1/26
 * @modifiedBy: 修改人
 */
@Configuration
@EnableSwagger2
public class Swagger2 {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.fan.ersha.controller"))
                .paths(PathSelectors.any())
                .build();
    }
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("二傻商场在线API文档")//标题
                .description("如有问题请加客服")//描述
                .termsOfServiceUrl("http://localhost:8080/swagger-ui.html")//服务条款URL
                .contact("二傻商城")
                .version("1.0")
                .build();
    }
}
