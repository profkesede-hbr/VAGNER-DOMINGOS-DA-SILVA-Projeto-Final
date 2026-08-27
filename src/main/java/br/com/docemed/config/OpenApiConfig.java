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
                        .title("Doc-eMed — API de Gestão Clínica e Anamnese Digital")
                        .description("API RESTful do sistema Doc-eMed para gestão de prontuários, pacientes e digitalização completa da Ficha de Avaliação Capilar (144 perguntas clínicas).\n\n"
                                + "**Desenvolvido por:**\n"
                                + "- Vagner Domingos da Silva\n"
                                + "- Jorge Wilker Mamede de Andrade\n"
                                + "- Luis Javier Leon Cardenas\n"
                                + "- Bianca Bruna Batista da Silva\n\n"
                                + "**Orientador:** Prof. Kesede R. Julio (IFSP 2025.2)")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Equipe Doc-eMed")
                                .url("https://github.com/profkesede-hbr/VAGNER-DOMINGOS-DA-SILVA-Projeto-Final")
                                .email("goldengoblinsentertainment@gmail.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://springdoc.org")));
    }
}
