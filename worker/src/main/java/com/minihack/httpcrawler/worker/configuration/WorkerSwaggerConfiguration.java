package com.minihack.httpcrawler.worker.configuration;

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
public class WorkerSwaggerConfiguration {

    @Bean
    public Docket api() throws Exception {
        return new Docket(DocumentationType.SWAGGER_2)
               .apiInfo(apiInfo())
               .select()
               .apis(RequestHandlerSelectors.basePackage("com.pandora.rights.worker"))
               .paths(PathSelectors.any())
               .build();
    }

    private ApiInfo apiInfo() throws Exception {
        return new ApiInfoBuilder()
               .title("Rights Worker")
               .description("RIGHTS API TESTER")
               .contact(new Contact("Content Operations", "https://wiki.savagebeast.com/display/eng/Content+Operations+-+External+Data", ""))
               .build();
    }

}
