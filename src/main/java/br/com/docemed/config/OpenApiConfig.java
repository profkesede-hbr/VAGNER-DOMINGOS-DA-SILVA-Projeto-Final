package br.com.docemed.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuração do OpenAPI / Swagger UI para o Doc-eMed.
 */
@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Doc-eMed — API de Gestão Clínica e Anamnese")
                        .description("API REST do sistema Doc-eMed para gestão de filas de atendimento, consultas e anamnese digital.")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Equipe Doc-eMed")
                                .email("contato@docemed.com.br"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://springdoc.org")));
    }
}
