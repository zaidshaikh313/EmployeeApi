package com.virtusa.employeeapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
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
	public Docket employeeApi() {
		Docket docket=new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo()).groupName("Library Api's").select().
				apis(RequestHandlerSelectors.basePackage("com.virtusa.employeeapi.controllers"))
				.build();
		return docket;
	}
	private ApiInfo apiInfo() {
		ApiInfo apiInfo=new ApiInfoBuilder().title("Employee Api").description("This is an api for Employee info")
				.contact(new Contact("suresh", "http://fakeurl.com", "fake@gmail.com")).
				license("General Licence").build();
		return apiInfo;
	}
}
