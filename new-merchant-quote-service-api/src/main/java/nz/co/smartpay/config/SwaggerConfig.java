package nz.co.smartpay.config;

import com.google.common.base.Predicate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket postsApi() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("smartpay")
                .apiInfo(apiInfo()).select().paths(postPaths()).build();
    }

    private Predicate<String> postPaths() {
        return regex("/.*");
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("New Merchant Quote Service API")
                .description("New Merchant Quote Service API reference for developers")
                .contact( new Contact("Jacob Holden","", "yacoob.holden@gmail.com"))
                .version("1.0")
                .build();
    }

}
