# Relatório de Compatibilidade e Unificação do Projeto Doc-eMed

**Data:** 27 de Agosto de 2026  
**Projeto:** Doc-eMed (Sistema Inteligente de Gestão Clínica e Avaliação Tricológica)  
**Instituição:** Instituto Federal de São Paulo (IFSP) — 2025.2  
**Orientador:** Prof. Kesede R. Julio  

**Equipe de Desenvolvimento:**
- **Vagner Domingos da Silva** (Desenvolvedor & Arquiteto)
- **Jorge Wilker Mamede de Andrade** (Desenvolvedor)
- **Luis Javier Leon Cardenas** (Desenvolvedor)
- **Bianca Bruna Batista da Silva** (Desenvolvedora)

---

## 1. Sumário Executivo

Este documento relata a análise comparativa, resolução de divergências e unificação integral do código-fonte e da documentação do projeto **Doc-eMed**, consolidando o trabalho inicial de estruturação desenvolvido por **Vagner Domingos da Silva** com o Pull Request #1 submetido por **Jorge Wilker Mamede de Andrade**, incorporando a equipe completa e garantindo conformidade absoluta com os requisitos do IFSP.

---

## 2. Histórico de Versões e Evolução

| Etapa | Autor Principal | Descrição | Status |
|---|---|---|:---:|
| **v0.1 — Inicial (FilaMed)** | Vagner Domingos da Silva | Criação da base Spring Boot 3.4.x, configuração do Maven Wrapper, repositório Git e modelagem preliminar de filas/consultas. | Concluído |
| **v0.2 — PR #1 (Doc-eMed Backend API)** | Jorge Wilker Mamede de Andrade | Migração e especialização do domínio para **Tricologia/Anamnese (Ficha SPA Brasil Cursos)**, 144 perguntas dinâmicas, Tricoscopia, Bristol, Exames Laboratoriais e documentação IFSP. | Integrado (PR #1) |
| **v1.0 — Unificação e Compatibilização** | Equipe Unificada | Harmonização do `README.md` raiz, metadados OpenAPI/Swagger, padronização do `pom.xml`, validação de build e consolidação da equipe de autores. | **100% Funcional** |

---

## 3. Matriz Comparativa de Componentes

### 3.1. Estrutura de Pacotes e Módulos

| Componente | Versão Inicial (`FilaMed`) | Versão Consolidada (`Doc-eMed`) | Ação / Compatibilidade |
|---|---|---|---|
| **Pacote Base** | `br.com.filamed` | `br.com.docemed` | Unificado em `br.com.docemed` para aderência ao domínio clínico |
| **Main Application** | `FilamedBackendApplication.java` | `DoceMedApplication.java` | Atualizado com `@SpringBootApplication` e execução direta |
| **Módulo Pacientes** | Cadastro básico | CRUD completo com 17 atributos (CPF único, contato, endereço, convênio) + busca case-insensitive + soft delete | Integrado e validado |
| **Módulo Anamnese** | Não existia | Ficha de 13 seções clínicas com 144 perguntas, Escala de Bristol (`TipoIntestinalBristol`), Tricoscopia (`TricoscopiaInfo`), Alopecias (`AlopeciaInfo`) e 21 Exames (`ExameLaboratorial`) | Integrado via `@Embeddable` (alta performance sem JOINs complexos) |
| **Catálogo de Perguntas** | Não existia | `PerguntaAnamnese` + `DataLoader` com as 144 perguntas do questionário oficial pré-carregadas | Integrado com carga automática na inicialização |
| **Tratamento de Exceções** | Básico | `GlobalExceptionHandler` + `RecursoNaoEncontradoException` com JSON padronizado e HTTP status semânticos | Padronizado |
| **Documentação da API** | Básica | Swagger UI / OpenAPI 3 com metadados detalhados, contatos da equipe e agrupamento de tags | Totalmente compatível em `/swagger-ui.html` |

---

## 4. Análise de Compatibilidade do Banco de Dados

O banco de dados relacional MySQL foi configurado em `application.properties` com:
* `spring.jpa.hibernate.ddl-auto=update` para geração e atualização autônoma do schema.
* Mapeamento otimizado de objetos complexos:
  * `anamneses`: Tabela principal com campos embutidos (`@Embedded`) para `TricoscopiaInfo`, `AlopeciaInfo` e `ExameLaboratorial`.
  * `pacientes`: Tabela de clientes com chave primária autoincremento, validação única de CPF e e-mail.
  * `perguntas_anamnese`: Tabela do catálogo gerenciável de perguntas.
  * `respostas_anamnese`: Tabela de respostas dinâmicas extras vinculadas à anamnese.

---

## 5. Endpoints REST Consolidados

| Grupo | Método | Rota | Descrição |
|---|:---:|---|---|
| **Pacientes** | `POST` | `/pacientes` | Cadastrar novo paciente |
| | `GET` | `/pacientes` | Listar todos os pacientes ativos |
| | `GET` | `/pacientes/{id}` | Buscar paciente por ID |
| | `GET` | `/pacientes/buscar?nome=` | Buscar paciente por nome |
| | `PUT` | `/pacientes/{id}` | Atualizar dados do paciente |
| | `DELETE` | `/pacientes/{id}` | Inativar paciente (soft delete) |
| **Anamnese** | `POST` | `/anamnese` | Criar nova ficha de avaliação clínica |
| | `GET` | `/anamnese/{id}` | Buscar ficha de anamnese por ID |
| | `GET` | `/anamnese/paciente/{id}` | Histórico completo de avaliações do paciente |
| | `GET` | `/anamnese/paciente/{id}/recente` | Avaliação mais recente do paciente |
| | `DELETE` | `/anamnese/{id}` | Excluir ficha de anamnese |
| **Perguntas** | `GET` | `/anamnese/perguntas` | Listar 144 perguntas ativas |
| | `GET` | `/anamnese/perguntas/todas` | Listar todas as perguntas (ativas/inativas) |
| | `POST` | `/anamnese/perguntas` | Cadastrar nova pergunta personalizada |
| | `PUT` | `/anamnese/perguntas/{id}` | Editar texto, tipo ou ordem da pergunta |
| | `PATCH` | `/anamnese/perguntas/{id}/ativar` | Ativar pergunta no formulário |
| | `PATCH` | `/anamnese/perguntas/{id}/desativar` | Desativar pergunta do formulário |
| | `DELETE` | `/anamnese/perguntas/{id}` | Excluir pergunta do catálogo |

---

## 6. Validação do Build e Testes

* **Maven Wrapper:** Versão 3.9.16
* **Java Virtual Machine:** OpenJDK 21 LTS
* **Resultado da Compilação:**
  ```
  [INFO] Compiling 28 source files with javac [debug parameters release 21] to target\classes
  [INFO] Compiling 1 source file with javac [debug parameters release 21] to target\test-classes
  [INFO] ------------------------------------------------------------------------
  [INFO] BUILD SUCCESS
  [INFO] ------------------------------------------------------------------------
  ```

---

## 7. Conclusão

O projeto encontra-se **100% unificado**, sem conflitos residuais, com dependências alinhadas no `pom.xml`, documentação enriquecida no `README.md` principal,Swagger UI configurado e a equipe completa de desenvolvedores devidamente creditada.
