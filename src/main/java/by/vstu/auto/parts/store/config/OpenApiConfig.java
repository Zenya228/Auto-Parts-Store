package by.vstu.auto.parts.store.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI autoPartsStoreOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Auto Parts Store API")
                        .description("REST API для управления категориями, брендами и запчастями интернет-магазина автозапчастей")
                        .version("v1")
                        .contact(new Contact()
                                .name("Auto Parts Store")));
    }
}
