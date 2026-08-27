<div align="center">

# 🏥 Doc-eMed

### Sistema Inteligente de Gestão Clínica e Digitalização da Ficha de Avaliação Capilar

API RESTful desenvolvida em **Java 21** com **Spring Boot** para gestão de pacientes, catálogo dinâmico de perguntas e digitalização completa da Ficha de Avaliação Capilar com 144 questões clínicas.

[![Java](https://img.shields.io/badge/Java-21-orange?style=for-the-badge&logo=openjdk)](https://openjdk.org/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.4.x-6DB33F?style=for-the-badge&logo=springboot)](https://spring.io/projects/spring-boot)
[![MySQL](https://img.shields.io/badge/MySQL-8-4479A1?style=for-the-badge&logo=mysql)](https://www.mysql.com/)
[![Swagger](https://img.shields.io/badge/Swagger-OpenAPI-85EA2D?style=for-the-badge&logo=swagger)](https://swagger.io/)
[![Maven](https://img.shields.io/badge/Maven-3.9-C71A36?style=for-the-badge&logo=apachemaven)](https://maven.apache.org/)
[![IFSP](https://img.shields.io/badge/IFSP-2025.2-red?style=for-the-badge)](https://www.ifsp.edu.br/)

</div>

---

## 📖 Sobre o Projeto

O **Doc-eMed** é um sistema completo de prontuário eletrônico e triagem clínica especializado em **Terapia Capilar e Tricologia**, desenvolvido para o projeto de conclusão de curso do **IFSP (Instituto Federal de São Paulo)**.

O sistema elimina o uso de fichas de papel na coleta de dados de saúde, disponibilizando formulários estruturados, armazenamento relacional seguro e uma API REST documentada interativamente via **Swagger UI / OpenAPI**.

---

## ✨ Funcionalidades Principais

### 👤 1. Módulo de Pacientes (`/pacientes`)
- Cadastro com dados pessoais completos: Nome, CPF (validação única), Data de Nascimento, RG, Sexo, Profissão e Indicado por.
- Dados de Contato e Endereço: WhatsApp/Celular, E-mail, Logradouro, Bairro, Cidade e CEP.
- Informações de Convênio e Queixa Principal.
- Geração automática de `dataCadastro` via `@PrePersist`.
- Busca flexível por nome (case-insensitive) e busca por ID.
- Inativação lógica (*soft delete*).

### 📋 2. Módulo de Anamnese e Avaliação Capilar (`/anamnese`)
Digitalização integral da **Ficha de Avaliação Capilar (SPA Brasil Cursos)** estruturada em 13 seções clínicas:
1. **Tricologia Inicial:** Tipo de cabelo, pigmentação residual e característica do couro cabeludo.
2. **Alimentação & Hábitos:** Consumo de frutas, verduras, legumes, ingestão hídrica, glúten, lactose e gorduras.
3. **Histórico de Saúde (Perguntas 1 a 25):** Doenças cardíacas, diabetes, câncer, alergias, hipertensão, histórico neurológico, tireoide, COVID-19, problemas renais/hepáticos, cirurgias, marca-passo e doenças autoimunes.
4. **Medicamentos & Ginecológico (Perguntas 26 a 32):** Medicamentos de uso contínuo, anticoncepcionais, SOP, ciclo menstrual e **Escala de Bristol (Enum `TIPO_1` a `TIPO_7`)**.
5. **Histórico da Queda Capilar (Perguntas 33 a 37):** Tempo de início, relação com eventos marcantes, novos fios, perda de pelos corporais e perda de densidade.
6. **Aspecto do Cabelo & Química:** Histórico de químicas nos últimos 12 meses (alisamento, luzes, coloração) e condições da haste (tricoptilose, triconodose, quebra, porosidade).
7. **Dados Clínicos do Couro Cabeludo:** Teste de tração, implante, caspa, dermatite seborreica, psoríase, foliculite, lesões, verrugas e padrão de rarefação.
8. **Tricoscopia (`TricoscopiaInfo` @Embeddable):** Avaliação microscópica de 12 achados (pontos pretos, amarelos, brancos, diversidade de diâmetro, fios vellus, eritema perifolicular, etc.) com localização anatômica.
9. **Hábitos de Estilo de Vida:** Frequência de lavagem, uso de fontes térmicas (secador/prancha), protetor térmico, atividade física e dietas restritivas.
10. **Histórico Familiar:** Calvície familiar e alopecia areata na família.
11. **Classificação de Alopecias (`AlopeciaInfo` @Embeddable):**
    - Androgenética (Escalas Hamilton e Ludwig)
    - Areata (placas)
    - Eflúvio Telógeno (10 fatores etiológicos)
    - Eflúvio Anágeno (5 causas)
    - Alopecias Cicatriciais (5 classificações)
12. **Exames Laboratoriais (`ExameLaboratorial` @Embeddable):** Registro estruturado de 21 marcadores (Hemograma, Ferritina, Vitamina D, TSH, T4 Livre, Anti-TPO, DHT, Testosterona, Zinco, Cobre, etc.).
13. **Indicação do Terapeuta & Termo:** Aspecto observado, tratamento em cabine, manutenção *home care* e aceite digital do Termo de Responsabilidade.

### ❓ 3. Catálogo Dinâmico de Perguntas (`/anamnese/perguntas`)
- `DataLoader` que pré-carrega automaticamente todas as **144 perguntas oficiais** na inicialização da aplicação.
- Permite que o administrador crie novas perguntas, edite enunciados e ative/desative perguntas sem necessidade de alterar o código.

---

## 🏗 Arquitetura do Sistema

O projeto adota arquitetura em camadas seguindo as melhores práticas de Clean Architecture e DDD no ecossistema Spring:

```
┌─────────────────────────────────────────────────────────────┐
│                    Swagger UI / Frontend                   │
└──────────────────────────────┬──────────────────────────────┘
                               │ JSON / HTTP REST
┌──────────────────────────────▼──────────────────────────────┐
│                      REST Controllers                       │
│      (PacienteController, AnamneseController, Pergunta...)   │
└──────────────────────────────┬──────────────────────────────┘
                               │ DTOs & Bean Validation
┌──────────────────────────────▼──────────────────────────────┐
│                       Service Layer                         │
│         (PacienteService, AnamneseService, Pergunta...)      │
└──────────────────────────────┬──────────────────────────────┘
                               │ Entities & @Embeddables
┌──────────────────────────────▼──────────────────────────────┐
│                     Repository Layer                        │
│                   (Spring Data JPA)                         │
└──────────────────────────────┬──────────────────────────────┘
                               │ Hibernate Dialect
┌──────────────────────────────▼──────────────────────────────┐
│                    MySQL Database (docemed)                 │
└─────────────────────────────────────────────────────────────┘
```

---

## 📂 Estrutura de Pacotes

```
src/main/java/br/com/docemed/
├── DoceMedApplication.java         # Classe principal Spring Boot
├── config/
│   ├── DataLoader.java             # Carga inicial das 144 perguntas clínicas
│   └── OpenApiConfig.java          # Configuração do Swagger UI / OpenAPI 3
├── controller/
│   ├── PacienteController.java     # Endpoints de Pacientes
│   ├── AnamneseController.java     # Endpoints de Fichas de Avaliação
│   └── PerguntaAnamneseController.java # Endpoints do Catálogo de Perguntas
├── dto/                            # Records DTO de Request e Response
├── exception/
│   ├── GlobalExceptionHandler.java # Tratamento global de exceções e JSON padronizado
│   └── RecursoNaoEncontradoException.java
├── model/
│   ├── Paciente.java               # Entidade do Paciente
│   ├── Anamnese.java               # Entidade da Ficha de Anamnese
│   ├── PerguntaAnamnese.java       # Entidade do Catálogo de Perguntas
│   ├── RespostaAnamnese.java       # Respostas dinâmicas adicionais
│   ├── TipoIntestinalBristol.java  # Enum da Escala de Bristol (1 a 7)
│   ├── TricoscopiaInfo.java        # @Embeddable: Achados da Tricoscopia
│   ├── AlopeciaInfo.java           # @Embeddable: Classificação de Alopecias
│   └── ExameLaboratorial.java      # @Embeddable: 21 Marcadores Laboratoriais
├── repository/                     # Interfaces Spring Data JPA
└── service/                        # Regras de negócio e mapeamento DTO ↔ Model
```

---

## 📚 Endpoints da API REST

### 👤 Pacientes (`/pacientes`)
| Método | Endpoint | Descrição |
|:---:|:---|:---|
| `POST` | `/pacientes` | Cadastra um novo paciente (Passo 1 da Ficha) |
| `GET` | `/pacientes` | Lista todos os pacientes ativos |
| `GET` | `/pacientes/{id}` | Busca paciente por ID |
| `GET` | `/pacientes/buscar?nome={nome}` | Busca pacientes por nome (parcial/case-insensitive) |
| `PUT` | `/pacientes/{id}` | Atualiza dados cadastrais do paciente |
| `DELETE` | `/pacientes/{id}` | Inativação lógica (*soft delete*) do paciente |

### 🩺 Anamnese (`/anamnese`)
| Método | Endpoint | Descrição |
|:---:|:---|:---|
| `POST` | `/anamnese` | Registra uma nova ficha de avaliação clínica completa |
| `GET` | `/anamnese/{id}` | Busca ficha de anamnese por ID |
| `GET` | `/anamnese/paciente/{id}` | Retorna todo o histórico de avaliações do paciente |
| `GET` | `/anamnese/paciente/{id}/recente` | Retorna a avaliação clínica mais recente do paciente |
| `DELETE` | `/anamnese/{id}` | Remove uma ficha de anamnese |

### ❓ Catálogo de Perguntas (`/anamnese/perguntas`)
| Método | Endpoint | Descrição |
|:---:|:---|:---|
| `GET` | `/anamnese/perguntas` | Lista as 144 perguntas ativas do questionário |
| `GET` | `/anamnese/perguntas/todas` | Lista todas as perguntas (ativas e inativas) |
| `POST` | `/anamnese/perguntas` | Cadastra uma nova pergunta customizada |
| `PUT` | `/anamnese/perguntas/{id}` | Atualiza texto, tipo ou ordem da pergunta |
| `PATCH` | `/anamnese/perguntas/{id}/ativar` | Ativa pergunta no formulário |
| `PATCH` | `/anamnese/perguntas/{id}/desativar` | Desativa pergunta no formulário |
| `DELETE` | `/anamnese/perguntas/{id}` | Exclui pergunta do catálogo |

---

## 🚀 Como Executar Localmente

### 1. Pré-requisitos
* **Java 21** LTS instalado (`java -version`)
* **MySQL 8** (ou 5.7+) rodando localmente na porta padrão `3306`

### 2. Configurar o Banco de Dados
No arquivo [application.properties](file:///p:/01-PROJETOS/VAGNER-DOMINGOS-DA-SILVA-Projeto-Final/src/main/resources/application.properties), configure as credenciais do seu MySQL:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/docemed?createDatabaseIfNotExist=true&useSSL=false&serverTimezone=America/Sao_Paulo
spring.datasource.username=root
spring.datasource.password=sua_senha
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

### 3. Compilar e Executar
Execute utilizando o Maven Wrapper embutido:

```powershell
# Compilar e validar dependências
.\mvnw.cmd clean compile

# Iniciar o servidor Spring Boot
.\mvnw.cmd spring-boot:run
```

A aplicação estará disponível em `http://localhost:8080`.

---

## 📄 Documentação Interativa (Swagger UI)

Com o servidor em execução, acesse a interface interativa do Swagger para testar todos os endpoints:

👉 **[http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)** ou **[http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)**

---

## 👥 Equipe de Desenvolvimento

Projeto desenvolvido para a disciplina de Projeto Final / Backend Java no **IFSP (Instituto Federal de São Paulo)**:

* **Vagner Domingos da Silva** — *Desenvolvedor & Arquiteto*
* **Jorge Wilker Mamede de Andrade** — *Desenvolvedor*
* **Luis Javier Leon Cardenas** — *Desenvolvedor*
* **Bianca Bruna Batista da Silva** — *Desenvolvedora*

**Orientação Acadêmica:**
* **Prof. Kesede R. Julio** — *Professor Orientador (IFSP 2025.2)*

---

## 📈 Documentos de Apoio

A documentação detalhada do projeto encontra-se no diretório [`/docs`](./docs):
* [Documentação do Sistema IFSP Doc-eMed](./docs/Documentacao_Sistema_IFSP_DoceMed.md)
* [Arquitetura e Camadas](./docs/arquitetura.md)
* [Requisitos Funcionais e Não-Funcionais](./docs/requisitos.md)
* [Roadmap de Entregas](./docs/roadmap.md)
* [Diário de Bordo](./docs/diario.md)
* [Registro de Uso de Inteligência Artificial](./docs/uso-de-ia.md)
* [Relatório de Compatibilidade e Unificação](./docs/relatorio_compatibilidade_e_unificacao.md)

---

## ⚖️ Licença

Este projeto está sob a licença [Apache 2.0](https://www.apache.org/licenses/LICENSE-2.0).