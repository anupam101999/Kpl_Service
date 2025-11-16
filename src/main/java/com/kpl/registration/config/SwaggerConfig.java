package com.kpl.registration.config;

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

	/**
	 * primary interface into the swagger-spring mvc framework
	 * 
	 * @return Docket
	 */
	@Bean
	public Docket swagger2Configuration() {
		return new Docket(DocumentationType.SWAGGER_2).select().paths(PathSelectors.ant("/kpl/registration/api/**"))
				.apis(RequestHandlerSelectors.basePackage("com.kpl.registration")).build().apiInfo(apiDetails());
	}

	/**
	 * Generating API info
	 * 
	 * @return ApiInfo
	 */
	private ApiInfo apiDetails() {
		return new ApiInfoBuilder().title("Kpl-Service Api").version("1.0.0")
				.description("List of API's implemented for KPL Service").license("KPL Proprietary 1.0").build();
	}

}