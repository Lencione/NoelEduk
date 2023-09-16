package br.com.noeleduk.noelproject.config;

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

@EnableSwagger2
@Configuration
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("br.com.noeleduk.noelproject.Controllers"))
                .paths(PathSelectors.any())
                .build().apiInfo(apiInfoMetaData());
    }

    private ApiInfo apiInfoMetaData() {

        return new ApiInfoBuilder().title("NoelEduk API Docs")
                .description("O propósito desta documentação é evidenciar todas as funcionalidades do servidor.")
                .contact(new Contact("Wesley Lencione", "https://github.com/lencione", "wesley@lencione.com.br"))
                .contact(new Contact("Rafael Menegon", "https://github.com/devrafamenegon", "dev.rafaelmenegon@gmail.com"))
                .contact(new Contact("Enzo Sabino", "https://github.com/onnibas", "enzo.sabino199@al.unieduk.com.br"))
                .contact(new Contact("José Pagotto", "https://github.com/heitorpagotto", "jose.pagotto006@al.unieduk.com.br"))
                .license("Apache 2.0")
                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
                .version("1.0.0")
                .build();
    }
}