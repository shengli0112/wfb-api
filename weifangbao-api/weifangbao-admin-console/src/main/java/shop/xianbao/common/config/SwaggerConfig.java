/**
 * Copyright (c) 2018 仙宝框架 All rights reserved.
 *
 * https://www.ruitukeji.com
 *
 * 版权所有，侵权必究！
 */

package shop.xianbao.common.config;

import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import shop.xianbao.common.constant.Constant;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

/**
 * Swagger配置
 *
 * @author Tim tim@ruitukeji.com
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig{

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
            .apiInfo(apiInfo())
            .select()
            //加了ApiOperation注解的类，生成接口文档
            .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
            //包下的类，生成接口文档
            //.apis(RequestHandlerSelectors.basePackage("top.ruitukeji.modules.job.controller"))
            .paths(PathSelectors.any())
            .build()
            .directModelSubstitute(java.util.Date.class, String.class)
            .securitySchemes(security());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
            .title("仙宝框架")
            .description("xidi-admin文档")
            .termsOfServiceUrl("https://www.ruitukeji.com")
            .version("2.0.0")
            .build();
    }

    private List<ApiKey> security() {
        return newArrayList(
            new ApiKey(Constant.TOKEN_HEADER, Constant.TOKEN_HEADER, "header")
        );
    }
}