package per.jxnflzc.practice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api() {
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select() // 选择哪些路径和api会生成document
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())// 对根下所有路径进行监控
                .build();
        return docket;
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("Practice接口文档")
                .description("这是用Swagger动态生成的接口文档")
                .termsOfServiceUrl("NO terms of service")
                .contact(
                        new Contact("jxnflzc", "http://120.78.141.62", "245186727@qq.com")
                )
                .license("The Apache License, Version 2.0")
                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
                .version("0.0.1")
                .build();
    }
}
