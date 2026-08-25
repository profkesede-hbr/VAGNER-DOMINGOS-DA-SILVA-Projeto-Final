# Arquitetura do Sistema — Doc-eMed

## Objetivo

O **Doc-eMed** é um sistema backend de gestão clínica para terapeutas capilares, desenvolvido como projeto final de Java.

O sistema digitaliza a **Ficha de Avaliação Capilar** da SPA Brasil Cursos, permitindo o cadastro de pacientes, preenchimento digital completo da anamnese (144 questões clínicas) e gerenciamento do catálogo de perguntas.

---

## Tecnologias Utilizadas

### Backend
- **Java 25** (Spring Boot 4.1.0)
- **Spring Data JPA** (Hibernate 7)
- **Spring Web MVC** (REST API)
- **SpringDoc OpenAPI** (Swagger UI)
- **Maven** (gerenciamento de dependências via Maven Wrapper)

### Banco de Dados
- **MySQL 5.5.27** (local)
- Configurado via `spring.jpa.hibernate.ddl-auto=update`
- Atenção: campos `DATETIME` devem usar `columnDefinition = "DATETIME"` por limitação do MySQL 5.5

### Documentação
- **Swagger UI**: `http://localhost:8080/swagger-ui.html`
- **Markdown** (pasta `docs/`)

---

## Estrutura de Pacotes

```
br.com.docemed
│
├── DoceMedApplication.java        ← Ponto de entrada da aplicação
│
├── config/
│   └── DataLoader.java            ← Seed das 144 perguntas da ficha PDF
│
├── controller/
│   ├── PacienteController.java    ← CRUD de pacientes (/pacientes)
│   ├── AnamneseController.java    ← Ficha clínica (/anamnese)
│   └── PerguntaController.java    ← Catálogo de perguntas (/anamnese/perguntas)
│
├── service/
│   ├── PacienteService.java
│   └── AnamneseService.java
│
├── repository/
│   ├── PacienteRepository.java
│   ├── AnamneseRepository.java
│   └── PerguntaAnamneseRepository.java
│
├── model/
│   ├── Paciente.java              ← Entidade: dados cadastrais do cliente
│   ├── Anamnese.java              ← Entidade: ficha clínica completa
│   ├── PerguntaAnamnese.java      ← Entidade: catálogo de perguntas
│   ├── TricoscopiaInfo.java       ← @Embeddable: exame tricoscópico
│   ├── AlopeciaInfo.java          ← @Embeddable: alopecias não cicatriciais e cicatriciais
│   ├── ExameLaboratorial.java     ← @Embeddable: 21 exames laboratoriais
│   └── TipoIntestinalBristol.java ← Enum: Escala de Bristol (Tipo 1 a 7)
│
├── dto/
│   ├── PacienteRequestDTO.java / PacienteResponseDTO.java
│   ├── AnamneseRequestDTO.java    ← inclui DTOs aninhados: TricoscopiaDTO, AlopeciaDTO, ExamesDTO
│   ├── AnamneseResponseDTO.java
│   ├── PerguntaAnamneseRequestDTO.java
│   └── PerguntaAnamneseResponseDTO.java
│
└── exception/
    ├── RecursoNaoEncontradoException.java  ← Exceção customizada (404)
    └── GlobalExceptionHandler.java         ← Tratamento global de erros
```

---

## Arquitetura em Camadas

```
[ Swagger UI / Frontend / App Mobile ]
           ↕  JSON (HTTP)
       [ Controller ]        ← Recebe requisições, valida entradas via DTO
           ↕
       [ Service ]           ← Contém regras de negócio
           ↕
      [ Repository ]         ← Acesso ao banco via Spring Data JPA
           ↕
        [ MySQL ]            ← Banco de dados relacional
```

---

## Módulos Implementados

| Módulo | Rota Base | Status |
|---|---|---|
| Cadastro de Pacientes | `/pacientes` | ✅ Implementado |
| Ficha de Anamnese Clínica | `/anamnese` | ✅ Implementado |
| Catálogo de Perguntas | `/anamnese/perguntas` | ✅ Implementado |
| Autenticação / Login | — | 🔜 Próxima fase |
| Relatórios / Dashboard | — | 🔜 Próxima fase |
