package com.stackroute.NewsApp.swagger;

import com.google.common.base.Predicate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import static springfox.documentation.builders.PathSelectors.regex;
import static com.google.common.base.Predicates.or;
//Indicates this as a configuration class
@Configuration
//annotation to enable swagger
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket postApi(){
        return new Docket(DocumentationType.SWAGGER_2).groupName("swagger-api-UST").
                apiInfo(apiInfo()).select().paths(postPaths()).build();
    }

    private Predicate<String> postPaths(){
        return or(regex("/api/v1/.*"),regex("/api/v1/.*"));
    }
    private ApiInfo apiInfo(){
        return  new ApiInfoBuilder().title("News Application JWT Authentication module Documentation")
                .description("This is the authentication module of NewsAPI")
                .contact("Lakshmi.Rajan@ust.com")
                .license("stackroute")
                .licenseUrl("www.stackroute.com")
                .termsOfServiceUrl("abc@gmail.com")
                .version("1.0").build();
    }
}

