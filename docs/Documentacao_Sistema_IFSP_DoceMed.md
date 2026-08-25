# Documentação do Sistema

---

## SUMÁRIO

| Seção | Página |
|---|---|
| Dados do Projeto/Cliente | 2 |
| Equipe de Desenvolvimento | 3 |
| 1. Introdução | 4 |
| 2. Objetivo | 5 |
| 3. Escopo | 6 |
| 4. Backlogs do Produto | 7 |
| 5. Cronograma | 9 |
| 6. Materiais e Métodos | 10 |
| 7. Resultados | 11 |
| 8. Conclusão | 12 |
| 9. Divulgação | 13 |

---

## Dados do Projeto/Cliente

**Título do Projeto:** Doc-eMed — Sistema de Gestão Clínica e Digitalização da Ficha de Avaliação Capilar

**Cliente:** Terapeuta Capilar (SPA Brasil Cursos)

**Link de divulgação:** *(a preencher — LinkedIn com os posts de cada fase do projeto)*

---

## Equipe de Desenvolvimento

| Nome Completo | Curso | Disciplina | Professor Orientador |
|---|---|---|---|
| Jorge Wilker Mamede de Andrade | Back-End | Java | Kesede R. Julio |
| Bianca Bruna Batista da Silva | Back-End | Java | Kesede R. Julio |
| Vagner Domingos da Silva | Back-End | Java | Kesede R. Julio |
| Luis Javier Leon Cardenas | Back-End | Java | Kesede R. Julio |

---

## 1. Introdução

O projeto tem como foco a informatização do processo de preenchimento de dados de atendimento de pacientes em clínicas de estética e áreas correlatas — especificamente, clínicas de terapia capilar.

Motivado pela dificuldade de coletar esses dados manualmente através de fichas impressas, o **Doc-eMed** pretende coletar, armazenar e gerenciar informações clínicas dos pacientes com eficiência e segurança, especialmente por se tratar de dados de saúde classificados como sensíveis.

Como solução, foi desenvolvido um sistema de pré-consulta e gestão de prontuários capaz de:

- Cadastrar os dados pessoais e clínicos do paciente de forma digital
- Digitalizar a **Ficha de Avaliação Capilar** da SPA Brasil Cursos na íntegra, com suas **144 questões clínicas**
- Organizar as informações em prontuário eletrônico vinculado ao histórico do paciente
- Permitir que o terapeuta acesse o histórico completo de avaliações de cada cliente

A implementação envolve recursos de formulário eletrônico estruturado, armazenamento em banco de dados relacional e API REST documentada, observando cuidados compatíveis com o tratamento de dados sensíveis e boas práticas de segurança da informação.

O impacto esperado é a melhoria da organização, da agilidade e da confidencialidade no processo de atendimento clínico, eliminando o uso de fichas em papel e possibilitando o acompanhamento da evolução do paciente ao longo do tempo.

---

## 2. Objetivo

Criar um sistema de gestão de prontuários para clínicas de terapia capilar que:

1. **Digitalize a ficha de avaliação física** — eliminando o uso de papel e possibilitando consulta rápida ao histórico do paciente
2. **Automatize o recebimento das informações clínicas** — com 144 questões estruturadas cobrindo todas as seções da ficha original
3. **Garanta organização dos dados** — vinculando cada avaliação ao cadastro do paciente com controle de data e histórico completo
4. **Ofereça tratamento adequado das informações sensíveis** — em conformidade com boas práticas de proteção de dados

---

## 3. Escopo

O escopo do sistema contempla três módulos principais:

**(1) Módulo de Cadastro de Pacientes**
Permite o registro completo dos dados pessoais do cliente, incluindo: Nome, CPF, Data de Nascimento, WhatsApp, E-mail, Endereço, CEP, Bairro, Cidade, Profissão, Convênio/Plano de Saúde, Indicado por e Queixa Principal. A data de cadastro é gerada automaticamente pelo sistema.

**(2) Módulo de Anamnese Clínica**
Digitaliza integralmente a Ficha de Avaliação Capilar da SPA Brasil Cursos, organizada em 13 seções:
- Tricologia Inicial
- Alimentação e Hábitos
- Histórico de Saúde (25 perguntas)
- Medicamentos e Saúde Ginecológica (Escala de Bristol incluída)
- Histórico da Queda Capilar
- Aspecto do Cabelo
- Dados Clínicos do Couro Cabeludo
- Hábitos de Estilo de Vida
- Histórico Familiar
- Tricoscopia (Haste/Couro)
- Exames Laboratoriais (21 exames)
- Alopecias Não Cicatriciais e Cicatriciais
- Indicação do Terapeuta (Tratamento, Manutenção, Termo de Responsabilidade)

**(3) Módulo de Catálogo de Perguntas**
Permite ao administrador gerenciar dinamicamente as 144 perguntas da ficha, criando, editando, ativando ou desativando perguntas sem necessidade de alteração no código-fonte.

**Fora do escopo desta etapa:**
Diagnóstico clínico automatizado, prescrição eletrônica, aplicativo mobile, compartilhamento automático com terceiros, integrações com planos de saúde ou prontuários externos.

---

## 4. Backlogs do Produto

### Requisitos Funcionais

- **RF01** — Cadastrar dados iniciais do paciente: o sistema permite o preenchimento de nome, CPF, data de nascimento, contato, endereço, profissão, convênio, indicado por e queixa principal.

- **RF02** — Registrar ficha de anamnese completa: o sistema permite o preenchimento das 144 questões clínicas da Ficha de Avaliação Capilar, organizadas em 13 seções temáticas.

- **RF03** — Registrar queixa principal e sintomas iniciais: o sistema disponibiliza campo específico para queixa principal no cadastro do paciente e campo de observações clínicas na anamnese.

- **RF04** — Registrar uso de medicamentos: o sistema inclui perguntas sobre medicamentos em uso (Pergunta 26 da ficha), incluindo contraceptivos orais, anticoagulantes, anticonvulsivantes, estatinas e outros.

- **RF05** — Registrar exames laboratoriais: o sistema permite registrar o resultado de 21 exames, incluindo Hemograma, Ferritina, Vitamina D, TSH, T4 Livre, Anti-TPO, DHT, Testosterona, Zinco, Cobre e outros.

- **RF06** — Armazenar respostas do questionário: o sistema salva todas as informações preenchidas no banco de dados MySQL, vinculadas ao ID do paciente, com data e hora de preenchimento.

- **RF07** — Consultar histórico de avaliações: o sistema permite visualizar todas as anamneses registradas de um paciente em ordem cronológica, bem como acessar diretamente a avaliação mais recente.

- **RF08** — Gerenciar catálogo de perguntas: o administrador pode criar novas perguntas personalizadas, editar o texto de perguntas existentes, ativar ou desativar perguntas do formulário sem alterar o código-fonte.

- **RF09** — Buscar pacientes: o sistema permite busca de pacientes por nome ou ID.

- **RF10** — Inativar registros: o sistema permite inativar pacientes e remover fichas de anamnese sem exclusão definitiva do banco.

### Requisitos Não Funcionais

- **RNF01** — Segurança dos dados: os dados são armazenados em banco de dados MySQL local, sem exposição pública não autorizada.

- **RNF02** — Controle de acesso por perfil: *(previsto para fase 2)* o sistema terá autenticação JWT com perfis de acesso (Terapeuta e Administrador).

- **RNF03** — Confidencialidade das informações: os dados clínicos sensíveis são tratados como campos estruturados e não expostos publicamente.

- **RNF04** — Conformidade com a LGPD: os dados pessoais e de saúde coletados são necessários para a finalidade clínica declarada. O paciente assina o Termo de Responsabilidade registrado na ficha de anamnese.

- **RNF05** — Armazenamento em ambiente controlado: o sistema opera com banco de dados MySQL local, com opção de migração para nuvem privada na fase seguinte.

- **RNF06** — Usabilidade da API: todos os endpoints são documentados e testáveis via Swagger UI (`http://localhost:8080/swagger-ui.html`), com respostas JSON padronizadas e mensagens de erro claras.

- **RNF07** — Disponibilidade das informações ao profissional: o terapeuta pode acessar a última avaliação do paciente com uma única requisição (`GET /anamnese/paciente/{id}/recente`).

---

## 5. Cronograma

| Etapa | Descrição | Status |
|---|---|---|
| **1** | Coleta de dados do cliente e levantamento de necessidades. Análise da Ficha de Avaliação Capilar (SPA Brasil Cursos) | ✅ Concluído |
| **2** | Criação do protótipo inicial (backend) — API REST com Spring Boot, cadastro de pacientes, ficha de anamnese com 144 questões, catálogo de perguntas, Swagger UI | ✅ Concluído |
| **3** | Criação do frontend simplificado | 🔄 Em desenvolvimento |
| **4** | Fusão do backend e frontend em uma única solução | 🔄 Em desenvolvimento |
| **5** | Criação da documentação (manual do usuário e do software) | 🔄 Em desenvolvimento |
| **6** | Apresentação final do projeto ao cliente | 📅 29/08/2026 |

---

## 6. Materiais e Métodos

### a. Modelagem do Sistema

**Diagrama de Classes Principal**

```
Paciente (1) ──────────── (N) Anamnese
    │                          │
    │ id                       │ id
    │ nome                     │ pacienteId (FK)
    │ cpf (único)              │ dataPreenchimento
    │ dataNascimento           │
    │ whatsapp                 ├── TricoscopiaInfo (@Embeddable)
    │ email                    │     diversidadeDiametros, fiosVellus,
    │ endereco                 │     pontosPretosLocalizacao...
    │ profissao                │
    │ convenio                 ├── AlopeciaInfo (@Embeddable)
    │ queixaPrincipal          │     alopeciaAndrogenetica, tipoHamilton,
    │ dataCadastro (auto)      │     alopeciaAreata, efluvioTelogeno...
    │ ativo                    │
                               ├── ExameLaboratorial (@Embeddable)
                               │     hemograma, ferritina, vitaminaD,
                               │     tsh, t4Livre, antiTPO, dht...
                               │
                               └── (+ 100 campos das demais seções)

PerguntaAnamnese
    │ id
    │ texto
    │ tipo
    │ ordem
    │ ativo
```

**Fluxo de Requisições (Arquitetura em Camadas)**

```
[Swagger UI / Frontend]
         ↕ JSON / HTTP
    [Controller]         ← Valida entrada (DTO), chama Service
         ↕
    [Service]            ← Regras de negócio, mapeamento DTO ↔ Model
         ↕
   [Repository]          ← Spring Data JPA (consultas ao banco)
         ↕
     [MySQL]             ← Banco de dados local (docemed)
```

### b. Tecnologias Utilizadas

| Tecnologia | Versão | Uso no projeto |
|---|---|---|
| **Java** | 25 | Linguagem principal do backend |
| **Spring Boot** | 4.1.0 | Framework principal — auto-configuração, servidor embutido Tomcat |
| **Spring Data JPA** | (incluso no Boot) | Mapeamento objeto-relacional e acesso ao banco |
| **Hibernate** | 7.x | ORM para geração e atualização das tabelas MySQL |
| **MySQL** | 5.5.27 | Banco de dados relacional local |
| **SpringDoc OpenAPI** | (incluso) | Geração automática do Swagger UI |
| **Maven** | 3.x (Wrapper) | Gerenciamento de dependências e build |
| **IntelliJ IDEA** | — | IDE de desenvolvimento |
| **Git / GitHub** | — | Controle de versão e repositório do projeto |

### c. Arquitetura do Sistema

O sistema é uma **API REST** com arquitetura em camadas (Controller → Service → Repository → Banco de Dados), exposta via Swagger UI para testes e documentação interativa.

Os dados clínicos da ficha de avaliação são armazenados em uma única tabela `anamneses` com objetos embutidos (`@Embeddable`) para `TricoscopiaInfo`, `AlopeciaInfo` e `ExameLaboratorial`, evitando JOINs desnecessários e simplificando as consultas.

O catálogo de 144 perguntas é carregado automaticamente na inicialização via `DataLoader` e armazenado na tabela `perguntas_anamnese`.

**Endpoints ativos:**

| Grupo | Rotas |
|---|---|
| Pacientes | `POST /pacientes`, `GET /pacientes`, `GET /pacientes/{id}`, `GET /pacientes/buscar`, `PUT /pacientes/{id}`, `DELETE /pacientes/{id}` |
| Anamnese | `POST /anamnese`, `GET /anamnese/{id}`, `GET /anamnese/paciente/{id}`, `GET /anamnese/paciente/{id}/recente`, `DELETE /anamnese/{id}` |
| Perguntas | `GET /anamnese/perguntas`, `GET /anamnese/perguntas/todas`, `POST /anamnese/perguntas`, `PUT /anamnese/perguntas/{id}`, `PATCH .../ativar`, `PATCH .../desativar`, `DELETE /anamnese/perguntas/{id}` |

---

## 7. Resultados

### a. Protótipo — Telas e Funcionalidades

**Tela 1 — Swagger UI (Documentação Interativa)**

O Swagger UI (`http://localhost:8080/swagger-ui.html`) exibe todos os endpoints organizados em três grupos:
- **1. Pacientes** — Cadastro e gestão dos clientes
- **2. Perguntas da Anamnese** — Gerenciamento do catálogo de 144 perguntas
- **3. Anamnese Clínica** — Criação e consulta das fichas de avaliação

O profissional pode testar qualquer endpoint diretamente pelo navegador, sem necessidade de ferramentas externas.

**Tela 2 — POST /pacientes (Cadastro de Novo Paciente)**

O terapeuta preenche os dados do cliente (nome, CPF, data de nascimento, WhatsApp, endereço etc.) e recebe como resposta o `id` gerado e a `dataCadastro` registrada automaticamente pelo servidor.

**Tela 3 — GET /anamnese/perguntas (Catálogo de Perguntas)**

Retorna a lista completa das 144 perguntas ativas da ficha, ordenadas sequencialmente conforme o PDF original. Cada pergunta contém: `id`, `texto`, `tipo` de resposta e `ordem`.

**Tela 4 — POST /anamnese (Criação da Ficha de Avaliação)**

O terapeuta envia o JSON completo com as respostas das 13 seções da ficha. O sistema valida o `pacienteId`, armazena a avaliação e retorna o registro criado com data e hora de preenchimento.

**Tela 5 — GET /anamnese/paciente/{id}/recente (Última Avaliação)**

Com um único acesso, o terapeuta visualiza a ficha de avaliação mais recente do paciente, incluindo todas as seções preenchidas (tricoscopia, exames laboratoriais, alopecias, indicação do tratamento).

### b. Link do GitHub

*(a preencher — inserir aqui o link do repositório GitHub do projeto)*

---

## 8. Conclusão

### a. Impacto do Sistema

O Doc-eMed transformou positivamente o processo de atendimento da terapeuta capilar ao:

- **Eliminar o uso de fichas em papel** — os dados passam a ser armazenados digitalmente, com acesso imediato e histórico preservado indefinidamente
- **Reduzir o tempo de preenchimento em consultas de retorno** — o terapeuta acessa a última avaliação com uma única requisição, sem precisar localizar e reler a ficha física
- **Padronizar o registro clínico** — todas as 144 questões da ficha oficial são cobertas de forma estruturada, garantindo consistência entre os atendimentos
- **Facilitar o acompanhamento da evolução do paciente** — o histórico completo de todas as avaliações fica vinculado ao cadastro do paciente, permitindo comparação entre sessões
- **Flexibilizar o catálogo de perguntas** — o administrador pode adicionar, editar ou desativar perguntas da ficha sem necessidade de intervenção no código-fonte

### b. Melhorias Futuras

1. **Autenticação e controle de acesso (JWT)** — login com perfis de Terapeuta e Administrador, protegendo os dados sensíveis de saúde
2. **Upload de arquivo digitalizado** — anexar imagem do scan da ficha de alopecias diretamente na avaliação
3. **Geração de PDF da ficha preenchida** — exportar o prontuário completo em formato imprimível
4. **Dashboard com estatísticas** — total de pacientes, avaliações por período, tipos de alopecia mais frequentes
5. **Frontend web** — interface gráfica para preenchimento da ficha sem necessidade do Swagger
6. **Aplicativo mobile (Android/iOS)** — preenchimento pelo próprio paciente antes da consulta

---

## 9. Divulgação

### CONICT

*(Esta seção será preenchida com as fotos da apresentação no evento CONICT após a data da apresentação — 29/08/2026)*

| Foto | Legenda |
|---|---|
| *Foto 1: time com o primeiro slide de fundo* | Da esquerda para direita: *(descrever quem está na foto)* |
| *Foto 2: integrante apresentando o sistema* | *(nome de quem está apresentando)* |
| *Foto 3: plano geral da apresentação (frente → fundo)* | Participantes do evento assistindo à apresentação |
| *Foto 4: plano geral da apresentação (fundo → frente)* | Participantes do evento assistindo à apresentação |
