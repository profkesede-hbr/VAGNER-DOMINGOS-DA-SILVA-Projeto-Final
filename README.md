<div align="center">

<img src="./docs/images/logo-hardware-br.png" alt="Instituto Hardware BR" width="300px" style="margin-bottom: 20px;">

# 🏥 Doc-eMed — Plataforma de Gestão Clínica & Prontuário Tricológico

### Sistema Inteligente de Triagem, Fila Virtual em Tempo Real e Digitalização da Ficha de Avaliação Capilar (144 Questões)

Plataforma Web Full Stack desenvolvida em **Java 21 LTS** com **Spring Boot 4.1.0**, **Thymeleaf**, **Server-Sent Events (SSE)** em tempo real e banco de dados **MariaDB 11.4** para gestão de pacientes, catálogo dinâmico de perguntas, agendamento de consultas, triagem médica, receituário digital e sistema de filas com suporte a telão na recepção.

[![Java](https://img.shields.io/badge/Java-21%20LTS-orange?style=for-the-badge&logo=openjdk)](https://openjdk.org/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.1.0-6DB33F?style=for-the-badge&logo=springboot)](https://spring.io/projects/spring-boot)
[![MariaDB](https://img.shields.io/badge/MariaDB-11.4-003545?style=for-the-badge&logo=mariadb)](https://mariadb.org/)
[![Swagger](https://img.shields.io/badge/Swagger-OpenAPI%203.0-85EA2D?style=for-the-badge&logo=swagger)](https://swagger.io/)
[![Version](https://img.shields.io/badge/Release-v1.0.1-blue?style=for-the-badge)](https://github.com/profkesede-hbr/VAGNER-DOMINGOS-DA-SILVA-Projeto-Final)
[![Hardware BR](https://img.shields.io/badge/Instituto-Hardware%20BR-purple?style=for-the-badge)](https://hardwarebr.org)
[![IFSP Campinas](https://img.shields.io/badge/IFSP-Campinas-red?style=for-the-badge)](https://cmp.ifsp.edu.br/)

---

### 🌐 Link Público Oficial de Apresentação (Online 24/7 na VM)
👉 **[https://publicly-chem-nursery-chapter.trycloudflare.com](https://publicly-chem-nursery-chapter.trycloudflare.com)**

</div>

---

## 📑 Sumário

- [📖 Sobre o Projeto](#-sobre-o-projeto)
- [🧭 Módulos e Rotas da Aplicação](#-módulos-e-rotas-da-aplicação)
- [📸 Demonstração Visual das Interfaces (Screenshots Detalhados)](#-demonstração-visual-das-interfaces-screenshots-detalhados)
  - [1. Landing Page Oficial](#1--landing-page-oficial)
  - [2. Dashboard Clínico do Médico](#2--dashboard-clínico-do-médico)
  - [3. Portal do Paciente com Telão & Fila Integrada](#3--portal-do-paciente-com-telão--fila-integrada)
  - [4. Área Médica — Autenticação Segura & Acesso Restrito](#4--área-médica--autenticação-segura--acesso-restrito)
  - [5. Portal do Paciente — Cadastro Rápido (Modo de Teste)](#5--portal-do-paciente--cadastro-rápido-modo-de-teste)
  - [6. Portal do Paciente — Login (Modo de Teste)](#6--portal-do-paciente--login-modo-de-teste)
  - [7. Cadastro de Paciente Real — Prontuário Oficial Completo](#7--cadastro-de-paciente-real--prontuário-oficial-completo)
- [🔒 Controle de Acesso & Segurança](#-controle-de-acesso--segurança)
- [📋 Estrutura da Ficha de Avaliação Capilar (144 Perguntas)](#-estrutura-da-ficha-de-avaliação-capilar-144-perguntas)
- [🏗️ Arquitetura Técnica & Tecnologias](#️-arquitetura-técnica--tecnologias)
- [⚡ Comunicação em Tempo Real (Server-Sent Events)](#-comunicação-em-tempo-real-server-sent-events)
- [📚 Endpoints da API REST (Swagger OpenAPI)](#-endpoints-da-api-rest-swagger-openapi)
- [🚀 Como Executar Localmente](#-como-executar-localmente)
- [🖥️ Implantação 24/7 na Máquina Virtual](#️-implantação-247-na-máquina-virtual)
- [👥 Equipe de Desenvolvimento & Orientação](#-equipe-de-desenvolvimento--orientação)
- [⚖️ Licença](#️-licença)

---

## 📖 Sobre o Projeto

O **Doc-eMed** é um ecossistema completo de prontuário eletrônico, triagem clínica e gestão de fluxo de atendimento especializado em **Terapia Capilar e Tricologia Integrada**, desenvolvido como projeto de conclusão de curso realizado pelo **Instituto Hardware BR** em conjunto com o **Instituto Federal de São Paulo (IFSP — 2025.2)**.

A plataforma substitui o uso de fichas manuais em papel por uma solução digital auditável, ágil e interativa:
* **Frontend Nativo Java**: Renderização server-side de alto desempenho com **Thymeleaf**, aliado a um Design System moderno em **Glassmorphism**, responsivo e com microinterações fluidas.
* **Digitalização Fiel de 144 Perguntas**: Mapeamento integral das 8 páginas da ficha clássica do **SPA Brasil Cursos**, permitindo ao terapeuta capilar e médico registrar diagnósticos tricoscópicos precisos.
* **Triagem Inteligente**: O agendamento de consultas é vinculado à anamnese prévia, garantindo que o profissional receba o histórico do paciente antes da consulta.
* **Fila Virtual em Tempo Real (SSE)**: Sistema reativo com chamadas sonoras hospitalares e sincronização entre painéis de recepção e dispositivos dos pacientes.
* **Prontuário Médico & Prescrição Digital**: Emissão de planos de tratamento em cabine, receituários formatados com orientações *home care* e módulo de impressão direta.

---

## 🧭 Módulos e Rotas da Aplicação

| Módulo | Rota | Público-Alvo | Descrição |
| :--- | :--- | :--- | :--- |
| **Página Inicial** | `/` | Geral | Apresentação institucional com navegação direta para os 4 módulos |
| **Login do Paciente** | `/paciente/login` | Pacientes Cadastrados | Acesso unificado com redirecionamento inteligente (Real vs Teste) |
| **Cadastro Paciente Real** | `/paciente/real-cadastro` | Novos Pacientes | Formulário de 4 etapas para abertura oficial de prontuário eletrônico |
| **Portal do Paciente Real** | `/paciente/real-portal` | Paciente Real | Anamnese completa (144 perguntas) e histórico de prescrições |
| **Modo de Testes Express** | `/paciente/acesso` | Demonstração | Cadastro rápido e login de teste com telão de recepção embutido na tela |
| **Portal do Paciente Teste** | `/paciente/portal` | Paciente Teste | Ficha rápida, agendamento interativo e telão integrado com alertas sonoros |
| **Área Médica (Login)** | `/medico/login` | Médicos / Gestores | Autenticação restrita e segura para o corpo clínico autorizado |
| **Painel do Médico** | `/medico/portal` | Médicos / Gestores | Dashboard com gráficos, triagem de agendamentos, prontuário e catálogo |
| **Telão da Recepção (TV)** | `/painel-chamada` | Recepção / Sala de Espera | Telão em tela cheia com sintetizador de áudio para televisores |
| **Swagger UI** | `/swagger-ui.html` | Desenvolvedores / Auditores | Documentação interativa e console de testes de todos os endpoints REST |

---

## 📸 Demonstração Visual das Interfaces (Screenshots Detalhados)

Abaixo são demonstradas as principais telas da aplicação, acompanhadas de suas respectivas explicações técnicas e funcionais:

---

### 1. 🌐 Landing Page Oficial
> **Arquivo:** `docs/images/00-landing-page-oficial.png`

<div align="center">
  <img src="./docs/images/00-landing-page-oficial.png" alt="Landing Page Oficial Doc-eMed" width="95%">
</div>

* **Barra de Navegação Superior:** Logotipo estilizado com identidade visual tricologia, botão de acesso direto **"Login do Paciente"** para clientes cadastrados, atalho para o **"Telão TV"** e badge de status do sistema online em tempo real.
* **Seção de Boas-Vindas (Hero):** Badge superior com identificação institucional oficial (**`INSTITUTO HARDWARE BR EM CONJUNTO COM IFSP (2025.2)`**), título com gradiente dinâmico e síntese dos recursos de prontuário, triagem e fila virtual.
* **4 Botões de Acesso Imediato:**
  1. 🩺 **Área Médica:** Acesso exclusivo ao corpo clínico e gestores com login seguro.
  2. 👤 **Já sou Paciente (Login):** Entrada direta para pacientes previamente cadastrados.
  3. 💳 **Novo Cadastro Real:** Formulário oficial em 4 etapas para abertura de prontuário eletrônico completo.
  4. 🧪 **Modo de Testes:** Demonstração expressa com fluxo ágil e telão de chamadas embutido na tela.
* **Cards de Recursos & Destaques:** Apresentação das 144 perguntas clínicas oficiais, da fila virtual em tempo real (SSE) e do módulo de evolução médica com receituário digital.
* **Rodapé Institucional com Identidade Visual:** Logotipo oficial do **Instituto Hardware BR**, autoria dos desenvolvedores e orientação acadêmica do Prof. Kesede R. Julio.

---

### 2. 🩺 Dashboard Clínico do Médico
> **Arquivo:** `docs/images/06-medico-dashboard-clinico.png`

<div align="center">
  <img src="./docs/images/06-medico-dashboard-clinico.png" alt="Dashboard Clínico do Médico" width="95%">
</div>

* **Métricas em Tempo Real (KPIs):** Indicadores consolidados de *Total de Pacientes*, *Agendamentos do Dia*, *Pacientes na Fila de Espera* e *Atendimentos Concluídos*.
* **Gráficos Analíticos (Chart.js):**
  * **Incidência de Alopecias (Barras):** Distribuição epidemiológica dos diagnósticos (Androgenética, Areata, Eflúvio Telógeno, Cicatriciais).
  * **Tipos de Cabelo (Donut):** Distribuição percentual das hastes capilares (Liso, Ondulado, Cacheado, Crespo).
* **Fila de Espera no Consultório:** Controle interativo com ações de **Chamar Paciente**, **Iniciar Consulta**, **Finalizar**, **Marcar Ausente** e **Abrir Prontuário/Anamnese**.
* **Solicitações de Consulta & Triagem:** Lista de agendamentos pendentes com opções de **Confirmar**, **Propor Reagendamento com Justificativa** e **Adicionar à Fila do Dia**.
* **Editor de Perguntas da Anamnese (144 Itens):** Catálogo dinâmico com busca instantânea e ativação/desativação de questões sem necessidade de alterações no código-fonte.

---

### 3. 📺 Portal do Paciente com Telão & Fila Integrada
> **Arquivo:** `docs/images/01-paciente-portal-fila-integrada.png`

<div align="center">
  <img src="./docs/images/01-paciente-portal-fila-integrada.png" alt="Portal do Paciente com Telão e Fila Integrada" width="95%">
</div>

* **Painel da Recepção ao Vivo no Topo:** O paciente visualiza na mesma tela o relógio sincronizado da clínica, o nome do paciente chamado no momento, a sala e o médico responsável.
* **Status Pessoal na Fila:** Indicador visual de posição na fila de espera com animação pulsante em azul quando o médico dispara o chamado (*"É A SUA VEZ!"*).
* **Passo 1 (Ficha de Anamnese):** Coleta da queixa principal, tipo de fio, oleosidade do couro, histórico de procedimentos químicos e estilo de vida.
* **Passo 2 (Agendamento de Consulta):** Escolha de data e horário com trava de segurança que exige o preenchimento prévio do Passo 1.

---

### 4. 🔒 Área Médica — Autenticação Segura & Acesso Restrito
> **Arquivo:** `docs/images/02-medico-login-restrito.png`

<div align="center">
  <img src="./docs/images/02-medico-login-restrito.png" alt="Área Médica - Login Seguro" width="65%">
</div>

* **Blindagem de Segurança:** Interface limpa, com campos protegidos e sem exposição de senhas pré-preenchidas.
* **Governança de Acesso:** Provisionamento restrito aos médicos e administradores registrados pela gestão da clínica.

---

### 5. 🧪 Portal do Paciente — Cadastro Rápido (Modo de Teste)
> **Arquivo:** `docs/images/03-paciente-cadastro-teste.png`

<div align="center">
  <img src="./docs/images/03-paciente-cadastro-teste.png" alt="Cadastro de Paciente Teste" width="65%">
</div>

* **Onboarding Ágil:** Cadastro rápido com Nome, WhatsApp, Login, Senha, Sexo e Cidade.
* **Entrada Automática:** Login imediato após o cadastro, redirecionando o paciente diretamente para o portal de triagem e fila.

---

### 6. 🔑 Portal do Paciente — Login (Modo de Teste)
> **Arquivo:** `docs/images/04-paciente-login-teste.png`

<div align="center">
  <img src="./docs/images/04-paciente-login-teste.png" alt="Login do Paciente Teste" width="65%">
</div>

* **Acesso Simples para Pacientes de Demonstração:** Permite ao paciente retornar à sessão para acompanhar seu agendamento e chamadas na fila.

---

### 7. 📝 Cadastro de Paciente Real — Prontuário Oficial Completo
> **Arquivo:** `docs/images/05-paciente-cadastro-real-completo.png`

<div align="center">
  <img src="./docs/images/05-paciente-cadastro-real-completo.png" alt="Cadastro Completo de Paciente Real" width="65%">
</div>

* **Seção 1 — Identificação:** Nome Completo, CPF (validação única), RG, Data de Nascimento, Sexo, Estado Civil e Profissão.
* **Seção 2 — Contato & Emergência:** Celular/WhatsApp, Telefone Fixo, E-mail e Contato de Emergência (Nome e Telefone).
* **Seção 3 — Endereço Completo:** CEP, Logradouro, Número, Complemento, Bairro, Cidade e Estado (UF).
* **Seção 4 — Credenciais:** Definição de usuário e senha criptografada para acesso ao portal e histórico de receituários digitais.

---

## 🔒 Controle de Acesso & Segurança

O acesso à **Área Médica e Administrativa** é estritamente restrito aos profissionais de saúde e gestores autorizados. O cadastro e provisionamento de contas médicas é realizado de forma interna e exclusiva pela administração do sistema, não havendo autocadastro público para profissionais. O autocadastro na plataforma é disponibilizado unicamente para os pacientes.

---

## 📋 Estrutura da Ficha de Avaliação Capilar (144 Perguntas)

A anamnese tricologia digitalizada divide-se em 8 seções clínicas:

```
Ficha de Avaliação Capilar (SPA Brasil Cursos — 144 Perguntas)
├── 1. Tricologia & Queixa Principal (Tipos de cabelo, pigmentação, características do couro)
├── 2. Alimentação & Hábitos (Frutas, verduras, ingestão hídrica, glúten, lactose e gorduras)
├── 3. Histórico de Saúde Geral (25 Patologias: coração, diabetes, tireoide, COVID-19, autoimunes, etc.)
├── 4. Medicamentos & Fisiologia (Uso contínuo, anticoncepcionais, ciclo menstrual e Escala de Bristol 1 a 7)
├── 5. Histórico da Queda Capilar (Início, eventos marcantes, novos fios, perda corporal e densidade)
├── 6. Aspecto do Cabelo & Química (Procedimentos em 12 meses: tinturas, luzes, alisamentos, condição da haste)
├── 7. Couro Cabeludo & Tricoscopia (Teste de tração, caspas, foliculite, 12 achados microscópicos anatômicos)
└── 8. Alopecias, Exames & Termo (Hamilton/Ludwig, 21 marcadores laboratoriais e aceite digital de responsabilidade)
```

---

## 🏗️ Arquitetura Técnica & Tecnologias

O projeto adota Clean Architecture em camadas com comunicação reativa:

```
┌─────────────────────────────────────────────────────────────┐
│                 Frontend Web (Thymeleaf UI)                 │
│         HTML5 • Vanilla CSS Glassmorphism • Chart.js        │
└──────────────────────────────┬──────────────────────────────┘
                               │ HTTP REST & SSE Streams
┌──────────────────────────────▼──────────────────────────────┐
│                    Spring Boot Controllers                  │
│   (Auth, Agendamento, Fila, Dashboard, Prontuario, Anamnese)│
└──────────────────────────────┬──────────────────────────────┘
                               │ DTOs & Validation Layer
┌──────────────────────────────▼──────────────────────────────┐
│                     Service Layer (Negócio)                 │
│         (Regras de Triagem, SSE Emitter, Prontuário)        │
└──────────────────────────────┬──────────────────────────────┘
                               │ JPA Repositories
┌──────────────────────────────▼──────────────────────────────┐
│                   MariaDB 11.4 Database                     │
│                  (Instância Porta 3307)                     │
└─────────────────────────────────────────────────────────────┘
```

### Tecnologias Utilizadas
* **Backend:** Java 21 LTS, Spring Boot 4.1.0 (Spring MVC, Spring Data JPA, Validation, DevTools).
* **Frontend:** Thymeleaf Template Engine, HTML5 Semântico, CSS3 Moderno (Glassmorphism), JavaScript ES6+ Nativo, Chart.js, FontAwesome 6.4.
* **Banco de Dados:** MariaDB 11.4 (porta dedicada 3307).
* **Comunicação em Tempo Real:** Server-Sent Events (SSE) via `SseEmitter` Spring.
* **Documentação de API:** Springdoc OpenAPI / Swagger UI 3.0.
* **Túnel Público Seguro:** Cloudflare Tunnel (HTTPS criptografado de ponta a ponta).

---

## ⚡ Comunicação em Tempo Real (Server-Sent Events)

O sistema conta com um barramento de eventos instantâneos sem necessidade de recarregar páginas:

| Evento | Origem | Destino | Efeito Visual / Ação |
| :--- | :--- | :--- | :--- |
| `NOVO_AGENDAMENTO` | Paciente | Médico | Atualiza tabela de agendamentos e dispara toast |
| `AGENDAMENTO_CONFIRMADO` | Médico | Paciente | Atualiza status da consulta para "Confirmado" |
| `REAGENDAMENTO_SOLICITADO` | Médico | Paciente | Exibe card de proposta de novo horário com justificativa |
| `REAGENDAMENTO_ACEITO` | Paciente | Médico | Atualiza consulta remarcada na agenda |
| `PACIENTE_CHAMADO` | Médico | Paciente & Telão TV | Dispara alerta sonoro, pisca painel e exibe sala de destino |
| `FILA_ATUALIZADA` | Médico / Sistema | Geral | Recalcula posições e tempos de espera |
| `NOVO_RECEITUARIO_DISPONIVEL`| Médico | Paciente | Disponibiliza imediatamente o receituário digital para impressão |

---

## 📚 Endpoints da API REST (Swagger OpenAPI)

A documentação interativa completa está disponível em `/swagger-ui.html`.

### 🔑 Autenticação & Cadastro (`/api/auth`)
* `POST /api/auth/cadastro-teste`: Cria paciente express e usuário de teste.
* `POST /api/auth/cadastro-real`: Cria cadastro completo com prontuário oficial.
* `POST /api/auth/login`: Autentica usuário e retorna perfil e rota inteligente.

### 📅 Agendamentos & Triagem (`/api/agendamentos`)
* `POST /api/agendamentos`: Solicita novo agendamento (exige anamnese).
* `POST /api/agendamentos/{id}/confirmar`: Médico confirma a consulta.
* `POST /api/agendamentos/reagendar`: Médico propõe novo horário com motivo.
* `POST /api/agendamentos/{id}/aceitar-reagendamento`: Paciente aceita proposta médica.

### 🚶 Fila de Atendimento (`/api/fila`)
* `POST /api/fila/adicionar`: Adiciona paciente agendado à fila do dia.
* `POST /api/fila/chamar`: Dispara chamada sonora e visual para o paciente e telão TV.
* `POST /api/fila/{id}/status`: Altera status (`EM_ATENDIMENTO`, `FINALIZADO`, `AUSENTE`).
* `GET /api/fila/realtime/stream`: Stream SSE para atualização em tempo real.

### 📋 Prontuário & Receituário Digital (`/api/prontuarios`)
* `POST /api/prontuarios`: Médico grava evolução clínica, diagnóstico e receita.
* `GET /api/prontuarios/paciente/{pacienteId}`: Histórico cronológico completo.
* `GET /api/prontuarios/paciente/{pacienteId}/resumo`: Resumo do receituário para o paciente.

---

## 🚀 Como Executar Localmente

### 1. Pré-requisitos
* **Java 21 LTS** (`java -version`)
* **Maven 3.9+** (ou utilizar o `./mvnw.cmd` incluído)
* **MariaDB 11.4** ou **MySQL 8.0+**

### 2. Execução
```powershell
# Executar a aplicação via Maven Wrapper
.\mvnw.cmd spring-boot:run

# Ou compilar o pacote JAR de produção
.\mvnw.cmd clean package -DskipTests
java -jar target/doc-emed-1.0.1.jar
```

Acesse em seu navegador: **`http://localhost:8080/`**

---

## 🖥️ Implantação 24/7 na Máquina Virtual

A plataforma está implantada de forma 100% autônoma dentro da VM **Hyper-V (`RageMU-Dev` - `192.168.240.10`)**:
* **Diretório:** `C:\DocEMed-App`
* **Serviço do Windows:** Tarefa Agendada `DocEMed-24x7` executada no boot como `SYSTEM`.
* **Isolamento Total:** Banco MariaDB na porta **3307** dedicada (os bancos SQL Server 1433 e MySQL 3306 permanecem intocados).
* **Túnel Cloudflare:** Mantém o acesso público HTTPS ativo de forma permanente e gratuita.

---

## 👥 Equipe de Desenvolvimento & Orientação

<div align="center">
  <img src="./docs/images/logo-hardware-br.png" alt="Instituto Hardware BR" width="220px" style="margin-bottom: 12px;">
</div>

Projeto desenvolvido para o Projeto Final do **Instituto Hardware BR** em conjunto com o **Instituto Federal de São Paulo (IFSP — 2025.2)**:

* **Vagner Domingos da Silva** — *Desenvolvedor & Arquiteto de Software*
* **Jorge Wilker Mamede de Andrade** — *Desenvolvedor*
* **Luis Javier Leon Cardenas** — *Desenvolvedor*

**Orientação Acadêmica:**
* **Prof. Kesede R. Julio** — *Professor Orientador (Instituto Hardware BR / IFSP — 2025.2)*

---

## ⚖️ Licença

Este projeto está licenciado sob os termos da licença [Apache 2.0](https://www.apache.org/licenses/LICENSE-2.0).