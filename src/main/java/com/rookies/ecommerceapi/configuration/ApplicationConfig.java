package com.rookies.ecommerceapi.configuration;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.Arrays;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class ApplicationConfig {

        @Bean
        public ModelMapper modelMapper() {
                ModelMapper modelMapper = new ModelMapper();
                // modelMapper.getConfiguration().setAmbiguityIgnored(true);
                modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
                return modelMapper;
        }

        @Bean
        public WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> webServerFactoryCustomizer() {
                return factory -> factory.setContextPath("/my-shop/api/v1");
        }

        @Bean
        public OpenAPI customOpenAPI() {
                return new OpenAPI()
                                // config auth
                                .components(new Components()
                                                .addSecuritySchemes("bearer-key-customer",
                                                                new SecurityScheme().type(SecurityScheme.Type.HTTP)
                                                                                .scheme("bearer").bearerFormat("JWT"))
                                                .addSecuritySchemes("bearer-key-employee",
                                                                new SecurityScheme().type(SecurityScheme.Type.HTTP)
                                                                                .scheme("bearer").bearerFormat("JWT"))
                                                .addSecuritySchemes("bearer-key-manager",
                                                                new SecurityScheme().type(SecurityScheme.Type.HTTP)
                                                                                .scheme("bearer").bearerFormat("JWT"))
                                                .addSecuritySchemes("bearer-key-admin",
                                                                new SecurityScheme().type(SecurityScheme.Type.HTTP)
                                                                                .scheme("bearer").bearerFormat("JWT")))
                                // config list of server to test
                                .servers(Arrays.asList(new Server().url("http://localhost:9995")))
                                // info
                                .info(new Info().title("My shop Application API").description("Sample OpenAPI 3.0")
                                                .contact(new Contact().email("nccuong281299@gmail.com").name("NCC")
                                                                .url(""))
                                                .license(new License().name("Apache 2.0")
                                                                .url("http://www.apache.org/licenses/LICENSE-2.0.html"))
                                                .version("1.0.0"));
        }
}