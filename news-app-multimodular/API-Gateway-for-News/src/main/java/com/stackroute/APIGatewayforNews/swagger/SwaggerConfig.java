package com.stackroute.APIGatewayforNews.swagger;

import com.google.common.base.Predicate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import static com.google.common.base.Predicates.or;

import static springfox.documentation.builders.PathSelectors.regex;

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
        return or(regex("auth-service/.*"),regex("news-api/api/v1/.*"),regex("favourite-api/api/v1/.*"));
    }
    private ApiInfo apiInfo(){
        return  new ApiInfoBuilder().title("API gate-way Documentation(NEWS Application)")
                .description("This is the Zuul API gateway module of NewsAPI")
                .contact("Lakshmi.Rajan@ust.com,poojapradeep@gmail.com,sam.pulimootil@gmail.com")
                .version("1.0").build();
    }
}

