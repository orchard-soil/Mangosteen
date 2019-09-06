package com.orchardsoil.mangosteenserver.common.config;

import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;
import com.google.common.collect.Lists;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.Parameter;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Configuration
@EnableSwagger2
@EnableSwaggerBootstrapUI
public class SwaggerConfig implements WebMvcConfigurer {
  /**
   * 显示swagger-ui.html文档展示页，还必须注入swagger资源：
   *
   * @param registry
   */
  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
//    registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
    registry.addResourceHandler("doc.html").addResourceLocations("classpath:/META-INF/resources/");
    registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
  }

  @Bean
  public Docket createRestApi() {

    ParameterBuilder parameterBuilder=new ParameterBuilder();
    List<Parameter> parameters= Lists.newArrayList();
    parameterBuilder.name("Authorization").description("token令牌").modelRef(new ModelRef("String"))
        .parameterType("header")
        .required(true).build();
    parameters.add(parameterBuilder.build());

    return new Docket(DocumentationType.SWAGGER_2)
        .apiInfo(apiInfo())
        .select()
        //此包路径下的类，才生成接口文档
        //.apis(RequestHandlerSelectors.basePackage("com.uqiauto.trace.business"))
        //加了ApiOperation注解的类，才生成接口文档
        .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
        .paths(PathSelectors.any())
        .build();
  }

  /***
   * oauth2配置
   * 需要增加swagger授权回调地址
   * http://localhost/webjars/springfox-swagger-ui/o2c.html
   * @return
   */
  @Bean
  SecurityScheme securityScheme() {
    return new ApiKey("Authorization", "Authorization", "header");
  }

  /**
   * JWT token
   *
   * @return
   */
  private List<Parameter> setHeaderToken() {
    ParameterBuilder tokenPar = new ParameterBuilder();
    List<Parameter> pars = new ArrayList<>();
    tokenPar.name("Authorization").description("token").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
    pars.add(tokenPar.build());
    return pars;
  }

  private ApiInfo apiInfo() {
    return new ApiInfoBuilder()
        .title("Mangosteen-山竹 后台接口文档")
        .description("")
        .termsOfServiceUrl("")
        .version("1.0")
        .build();
  }

}
