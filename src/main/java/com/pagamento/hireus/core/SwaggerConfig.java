package com.pagamento.hireus.core;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	private static final String BASE_PACKAGE = "com.pagamento.hireus.api.controller";
    private static final String API_TITLE = "API de Folha de Pagamento";
    private static final String API_DESCRIPTION = "REST API para controle da folha de pagamento";
    private static final String CONTACT_NAME = "EQUIPE HIRE US";
    private static final String CONTACT_GITHUB = "https://gtihub de algum dos integrantes da equipe";
    private static final String CONTACT_EMAIL = "hireus@gmail.com";

    //Classe Externa Docket
	@Bean
	public Docket folhaPagamentoApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage(BASE_PACKAGE))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(construirApiInfo());
	}
	
	private ApiInfo construirApiInfo() {
		return new ApiInfoBuilder()
				.title(API_TITLE)
				.description(API_DESCRIPTION)
				.version("1.0.0")
				.contact(new springfox.documentation.service.Contact(CONTACT_NAME, CONTACT_GITHUB, CONTACT_EMAIL))
				.build();
	}
}