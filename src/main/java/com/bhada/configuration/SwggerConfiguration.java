//package com.bhada.configuration;
//
//import java.util.Collections;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.PropertySource;
//
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.service.ApiInfo;
//import springfox.documentation.service.Contact;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;
//
//@Configuration
//@EnableSwagger2
//@PropertySource("classpath:swaggerInfo.properties")
//public class SwggerConfiguration {
//			
//	@Value("${swagger.title}")
//	private String swaggerTitle;
//	
//	@Value("${swagger.description}")
//	private String swaggerDescription;
//	
//	@Value("${swagger.version}")
//	private String swaggerversion;
//	
//	@Value("${swagger.termsOfService}")
//	private String swaggertermsOfService;
//	
//	@Value("${swagger.contact.url}")
//	private String swaggerUrl;
//	
//	@Value("${swagger.apilicense}")
//	private String apiLicense;
//	
//	@Value("${swagger.licenseurl}")
//	private String licenseurl;
//	
//	@Value("${swagger.contact.name}")
//	private String swaggerUsername;
//	
//	@Value("${swagger.contact.email}")
//	private String swaggerEmail;
//	
//	
//	public Docket swaggerConfiguration() {
//		return new Docket(DocumentationType.SWAGGER_2)
////				.securityContexts(Arrays.asList(securityContext()))
////				.securitySchemes(Arrays.asList(apiKey()))
//				.select()
//				.apis(RequestHandlerSelectors.basePackage("com.bhada.controller"))
//				.build()
//				.apiInfo(apiDetails());
//	}
//	
//	private ApiInfo apiDetails() {
//		return new ApiInfo(swaggerTitle,
//				swaggerDescription,
//				swaggerversion,
//				swaggertermsOfService,
//				new Contact(swaggerUsername, swaggerUrl, swaggerEmail),
//				apiLicense,
//				licenseurl,
//				Collections.emptyList()
//				);
//	}
//	
////	private ApiKey apiKey() {
////		return new ApiKey("JWT", "Authorization", "header");
////	}
////	
////	private SecurityContext securityContext() {
////		return SecurityContext.builder().securityReferences(defaultAuth()).build();
////	}
////	
////	private List<SecurityReference> defaultAuth(){
////		AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
////		AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
////		authorizationScopes[0] = authorizationScope;
////		List<Object> obj = Arrays.asList(new SecurityReference("JWT", authorizationScopes));
////		
////		return obj;
////	}
//	
//}
