<div align="center">

<img src="./docs/images/logo-hardware-br.png" alt="Instituto Hardware BR" width="300px" style="margin-bottom: 20px;">

# 🏥 Doc-eMed (v1.1.0) — Plataforma Integrada de Gestão Clínica, Recepção Presencial & Prontuário Tricológico

### Sistema Inteligente de Acolhimento, Triagem Médica, Chat Clínico 1-para-1 em Tempo Real, Fila Virtual Hospitalar e Digitalização da Ficha de Avaliação Capilar (144 Questões)

Plataforma Web Full Stack desenvolvida em **Java 21 LTS** com **Spring Boot 4.1.0**, **Thymeleaf**, **Server-Sent Events (SSE)** com keepalive em tempo real e banco de dados **MariaDB 11.4** para gestão de pacientes presenciais e online, triagem ambulatorial, prontuário eletrônico, receituário digital magistral e sistema de filas com suporte a telão na recepção.

[![Java](https://img.shields.io/badge/Java-21%20LTS-orange?style=for-the-badge&logo=openjdk)](https://openjdk.org/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.1.0-6DB33F?style=for-the-badge&logo=springboot)](https://spring.io/projects/spring-boot)
[![MariaDB](https://img.shields.io/badge/MariaDB-11.4-003545?style=for-the-badge&logo=mariadb)](https://mariadb.org/)
[![Swagger](https://img.shields.io/badge/Swagger-OpenAPI%203.0-85EA2D?style=for-the-badge&logo=swagger)](https://swagger.io/)
[![Version](https://img.shields.io/badge/Release-v1.1.0-blue?style=for-the-badge)](https://github.com/profkesede-hbr/VAGNER-DOMINGOS-DA-SILVA-Projeto-Final)
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
- [🎧 Módulo Completo de Recepção & Acolhimento Presencial](#-módulo-completo-de-recepção--acolhimento-presencial)
- [💬 Chat Privado 1-para-1 em Tempo Real (Médicos ↔ Recepção)](#-chat-privado-1-para-1-em-tempo-real-médicos--recepção)
- [📸 Demonstração Visual das Interfaces (Screenshots Detalhados)](#-demonstração-visual-das-interfaces-screenshots-detalhados)
  - [1. Landing Page Oficial](#1--landing-page-oficial)
  - [2. Módulo de Recepção — Gestão de Fila de Espera](#2--módulo-de-recepção--gestão-de-fila-de-espera)
  - [3. Módulo de Recepção & Médico — Chat Privado em Tempo Real](#3--módulo-de-recepção--médico--chat-privado-em-tempo-real)
  - [4. Dashboard Clínico do Médico](#4--dashboard-clínico-do-médico)
  - [5. Telão da Recepção (TV) — Chamada Ambulatorial em Tela Cheia](#5--telão-da-recepção-tv--chamada-ambulatorial-em-tela-cheia)
  - [6. Portal do Paciente com Fila Integrada](#6--portal-do-paciente-com-fila-integrada)
  - [7. Área Médica — Autenticação Restrita](#7--área-médica--autenticação-restrita)
  - [8. Cadastro Completo de Paciente Real (Prontuário Oficial)](#8--cadastro-completo-de-paciente-real-prontuário-oficial)
- [🔐 Credenciais de Acesso & Perfis Oficiais](#-credenciais-de-acesso--perfis-oficiais)
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

O **Doc-eMed (v1.1.0)** é um ecossistema completo de gestão clínica, recepção presencial, prontuário eletrônico e fluxo ambulatorial especializado em **Terapia Capilar e Tricologia Integrada**, desenvolvido como projeto de conclusão de curso realizado pelo **Instituto Hardware BR** em conjunto com o **Instituto Federal de São Paulo (IFSP — 2025.2)**.

A plataforma integra os três pilares do atendimento em saúde:
1. **Recepção & Balcão Presencial:** Cadastro para clientes sem smartphone/internet com login por CPF, envio de e-mail de boas-vindas com senha provisória, rechamada no telão TV, reset administrativo de senhas, consulta de receitas (somente leitura) e chat confidencial.
2. **Consultório & Corpo Clínico:** Anamnese de 144 questões (SPA Brasil Cursos), prontuário eletrônico com histórico cronológico, emissão de receituários magistrais, controle de fila e chat interno.
3. **Pacientes & Acolhimento:** Ficha digital prévia, acompanhamento de posição na fila com alertas sonoros e histórico de prescrições.

---

## 🧭 Módulos e Rotas da Aplicação

| Módulo | Rota | Público-Alvo | Descrição |
| :--- | :--- | :--- | :--- |
| **Página Inicial** | `/` | Geral | Apresentação institucional com navegação direta para todos os módulos |
| **Recepção (Login)** | `/recepcao/login` | Atendimento / Balcão | Acesso seguro e restrito para recepcionistas (`recep` / `recep123`) |
| **Portal da Recepção** | `/recepcao/portal` | Atendimento / Balcão | Cadastro presencial via CPF, reset de senhas, fila, agenda do dia, consulta de receitas e chat |
| **Área Médica (Login)** | `/medico/login` | Médicos / Gestores | Autenticação restrita para o corpo clínico autorizado (`admin` / `admin123`) |
| **Painel do Médico** | `/medico/portal` | Médicos / Gestores | Dashboard com gráficos, triagem, prontuário, receitas e chat com recepção |
| **Telão da Recepção (TV)** | `/painel-chamada` | Recepção / Sala de Espera | Telão em tela cheia com sintetizador de áudio para televisores |
| **Login do Paciente** | `/paciente/login` | Pacientes Cadastrados | Acesso unificado com login por CPF ou usuário |
| **Cadastro Paciente Real** | `/paciente/real-cadastro` | Novos Pacientes | Formulário de 4 etapas para abertura oficial de prontuário eletrônico |
| **Portal do Paciente Real** | `/paciente/real-portal` | Paciente Real | Anamnese completa (144 perguntas) e histórico de prescrições |
| **Modo de Testes Express** | `/paciente/acesso` | Demonstração | Cadastro rápido e login de teste com telão de recepção embutido na tela |
| **Portal do Paciente Teste** | `/paciente/portal` | Paciente Teste | Ficha rápida, agendamento interativo e telão integrado com alertas sonoros |
| **Swagger UI** | `/swagger-ui.html` | Desenvolvedores / Auditores | Documentação interativa e console de testes de todos os endpoints REST |

---

## 🎧 Módulo Completo de Recepção & Acolhimento Presencial

Desenvolvido para atender pacientes que comparecem diretamente à clínica física (incluindo clientes sem celular ou sem acesso prévio à internet):

1. **📇 Cadastro Presencial de Balcão (Login Oficial por CPF):**
   - O atendente preenche o cadastro completo (dados pessoais, contato, endereço e emergência).
   - O sistema define automaticamente o **CPF do paciente (apenas dígitos)** como seu **Login Oficial**.
   - Gera uma senha provisória criptografada e dispara um **e-mail de boas-vindas com as credenciais e link direto** para que o paciente possa acessar o portal e alterar sua senha quando quiser.
   - Opção de **inclusão imediata na fila de espera do dia** com um único clique.

2. **🚶 Gestão Inteligente da Fila Presencial & Telão TV:**
   - Visualização em tempo real de todos os pacientes aguardando atendimento.
   - **Botão "Rechamar":** Dispara evento SSE `PACIENTE_CHAMADO` com alerta sonoro e visual imediato no Telão da Recepção ([`/painel-chamada`](https://publicly-chem-nursery-chapter.trycloudflare.com/painel-chamada)) para pacientes que perderam a chamada ou que precisam retornar ao consultório.
   - Remoção, cancelamento ordenado e acompanhamento de status (`AGUARDANDO`, `CHAMADO`, `EM_ATENDIMENTO`, `FINALIZADO`, `AUSENTE`).

3. **👥 Gestão de Pacientes & Redefinição de Senhas (Reset):**
   - Busca instantânea por Nome, CPF ou E-mail.
   - Edição de contatos, endereço e responsáveis de emergência.
   - **Reset de Senha Administrativo:** A recepcionista pode alterar a senha de qualquer paciente que tenha esquecido o acesso, com disparo de notificação por e-mail.

4. **📄 Consulta e Impressão de Receituários (Modo Somente Leitura):**
   - A recepcionista pode pesquisar e visualizar as receitas médicas emitidas pelo corpo clínico para impressão e entrega ao paciente.
   - **Blindagem Clínica:** Bloqueio total de edição para a recepção (somente leitura), preservando a integridade das decisões médicas.

5. **📅 Agenda Ambulatorial do Dia:**
   - Visualização das consultas marcadas para hoje.
   - Agendamento presencial rápido de novos horários com seleção de médico especialista.

---

## 💬 Chat Privado 1-para-1 em Tempo Real (Médicos ↔ Recepção)

Sistema de comunicação interna seguro, ágil e confidencial entre recepcionistas e médicos:
- **Conversas Individuais e Privadas:** Selecione qualquer médico ou atendente conectado para iniciar uma conversa direta.
- **Status Online em Tempo Real:** Indicador visual (verde = online / cinza = visto recentemente) atualizado via heartbeat automático.
- **Sincronização Dupla (SSE + Fallback 2,5s):** Entrega instantânea de mensagens com barramento de eventos SSE e keepalive a cada 15s, além de sincronização contínua de polling a cada 2,5s para garantir zero perda de mensagens mesmo em caso de oscilação de rede.
- **Alertas Sonoros & Badges:** Notificação com sinal sonoro hospitalar agradável e contador de mensagens não lidas no topo da página.

---

## 📸 Demonstração Visual das Interfaces (Screenshots Detalhados)

Abaixo são demonstradas as principais telas da aplicação:

---

### 1. 🌐 Landing Page Oficial
> **Arquivo:** `docs/images/00-landing-page-oficial.png`

<div align="center">
  <img src="./docs/images/00-landing-page-oficial.png" alt="Landing Page Oficial Doc-eMed" width="95%">
</div>

* **Barra de Navegação Superior:** Logotipo estilizado com identidade visual tricologia, botão de acesso direto **"Recepção"**, **"Área Médica"**, **"Login do Paciente"** e **"Telão TV"**.
* **Seção Hero Institucional:** Identificação oficial (**`INSTITUTO HARDWARE BR EM CONJUNTO COM IFSP (2025.2)`**), título com gradiente dinâmico e síntese dos recursos.
* **Cards de Acesso:** Acesso à Recepção, Área Médica, Cadastro Real e Modo de Demonstração.

---

### 2. 🎧 Módulo de Recepção — Gestão de Fila de Espera
> **Arquivo:** `docs/images/08-recepcao-portal-fila.png`

<div align="center">
  <img src="./docs/images/08-recepcao-portal-fila.png" alt="Módulo de Recepção - Fila de Espera" width="95%">
</div>

* **Abas de Navegação:** *Fila de Espera do Dia*, *Novo Cadastro Presencial*, *Gestão de Pacientes & Senhas*, *Agenda do Dia*, *Consulta de Receituários* e *Chat Privado Médicos*.
* **Tabela de Fila:** Posição, Nome do Paciente, Status Atual, Consultório, Médico Responsável e Ações com botões de **Rechamar** e **Remover**.
* **Header Informativo:** Identificação da atendente conectada e botão de acesso rápido ao Telão TV e Chat.

---

### 3. 💬 Módulo de Recepção & Médico — Chat Privado em Tempo Real
> **Arquivo:** `docs/images/09-recepcao-chat-tempo-real.png`

<div align="center">
  <img src="./docs/images/09-recepcao-chat-tempo-real.png" alt="Chat Privado em Tempo Real Recepção e Médico" width="95%">
</div>

* **Lista Lateral de Contatos:** Exibe médicos e recepcionistas com foto, status online e consultório.
* **Janela de Mensagens Privadas:** Histórico cronológico bidirecional com balões diferenciados (laranja para recepção, azul para médico).
* **Campo de Envio Ágil:** Envio com Enter ou clique no botão de aviãozinho com foco automático.

---

### 4. 🩺 Dashboard Clínico do Médico
> **Arquivo:** `docs/images/06-medico-dashboard-clinico.png`

<div align="center">
  <img src="./docs/images/06-medico-dashboard-clinico.png" alt="Dashboard Clínico do Médico" width="95%">
</div>

* **Métricas em Tempo Real (KPIs):** Indicadores de *Total de Pacientes*, *Agendamentos do Dia*, *Pacientes na Fila* e *Atendimentos Concluídos*.
* **Gráficos Analíticos (Chart.js):** Incidência de Alopecias (Barras) e Tipos de Cabelo (Donut).
* **Fila no Consultório & Prontuário:** Botões de **Chamar**, **Iniciar Consulta**, **Prontuário** e **Ver Anamnese**.

---

### 5. 📺 Telão da Recepção (TV) — Chamada Ambulatorial em Tela Cheia
> **Arquivo:** `docs/images/07-telao-recepcao-tv.png`

<div align="center">
  <img src="./docs/images/07-telao-recepcao-tv.png" alt="Telão da Recepção TV" width="95%">
</div>

* **Exibição Hospitalar em Tela Cheia:** Interface otimizada para televisores e monitores de recepção com alto contraste e relógio digital sincronizado.
* **Card Central de Chamada Instantânea:** Efeito visual em neon azul com **Nome do Paciente**, **Consultório** e **Médico**.
* **Alertas Sonoros Automatizados:** Emite toque sonoro característico de chamada hospitalar a cada acionamento.

---

### 6. 📱 Portal do Paciente com Fila Integrada
> **Arquivo:** `docs/images/01-paciente-portal-fila-integrada.png`

<div align="center">
  <img src="./docs/images/01-paciente-portal-fila-integrada.png" alt="Portal do Paciente com Fila Integrada" width="95%">
</div>

---

### 7. 🔒 Área Médica — Autenticação Restrita
> **Arquivo:** `docs/images/02-medico-login-restrito.png`

<div align="center">
  <img src="./docs/images/02-medico-login-restrito.png" alt="Área Médica - Login Seguro" width="65%">
</div>

---

### 8. 📝 Cadastro Completo de Paciente Real (Prontuário Oficial)
> **Arquivo:** `docs/images/05-paciente-cadastro-real-completo.png`

<div align="center">
  <img src="./docs/images/05-paciente-cadastro-real-completo.png" alt="Cadastro Completo de Paciente Real" width="65%">
</div>

---

## 🔐 Credenciais de Acesso & Perfis Oficiais

Para testes, homologação e bancas examinadoras, o sistema disponibiliza as contas pré-configuradas:

| Perfil | Usuário / Login | Senha | Rota de Acesso | Atribuições |
| :--- | :--- | :--- | :--- | :--- |
| **Recepção / Balcão** | `recep` | `recep123` | [`/recepcao/login`](https://publicly-chem-nursery-chapter.trycloudflare.com/recepcao/login) | Cadastro presencial via CPF, e-mails de boas-vindas, reset de senhas, inclusão/rechamada na fila, agenda do dia, consulta de receitas e chat |
| **Médico (Admin)** | `admin` | `admin123` | [`/medico/login`](https://publicly-chem-nursery-chapter.trycloudflare.com/medico/login) | Prontuário, prescrição digital, gestão de fila com chamadas sonoras, catálogo de 144 perguntas e chat privado |
| **Médico (Corpo Clínico)** | `medico` | `medico123` | [`/medico/login`](https://publicly-chem-nursery-chapter.trycloudflare.com/medico/login) | Atendimento ambulatorial, triagem de agendamentos, emissão de receitas e chat privado |
| **Paciente Presencial** | *(CPF do paciente)* | *(Senha provisória)* | [`/paciente/login`](https://publicly-chem-nursery-chapter.trycloudflare.com/paciente/login) | Login gerado automaticamente no balcão pela recepção com envio por e-mail |
| **Paciente Real** | *(Criado pelo usuário)*| *(Definida pelo usuário)*| [`/paciente/login`](https://publicly-chem-nursery-chapter.trycloudflare.com/paciente/login) | Acesso ao portal completo de 144 perguntas e histórico de prescrições |
| **Paciente de Teste** | `teste` *(ou via cadastro express)* | `123456` | [`/paciente/login`](https://publicly-chem-nursery-chapter.trycloudflare.com/paciente/login) | Modo de demonstração rápida |

---

## 📋 Estrutura da Ficha de Avaliação Capilar (144 Perguntas)

A anamnese tricológica digitalizada divide-se em 8 seções clínicas:

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
│   (Auth, Recepcao, Chat, Agendamento, Fila, Prontuario)     │
└──────────────────────────────┬──────────────────────────────┘
                                │ DTOs & Validation Layer
┌──────────────────────────────▼──────────────────────────────┐
│                     Service Layer (Negócio)                 │
│   (Recepcao, Chat, Triagem, Realtime SSE, Email, Prontuario)│
└──────────────────────────────┬──────────────────────────────┘
                                │ JPA Repositories
┌──────────────────────────────▼──────────────────────────────┐
│                   MariaDB 11.4 Database                     │
│                  (Instância Porta 3307)                     │
└─────────────────────────────────────────────────────────────┘
```

### Tecnologias Utilizadas
* **Backend:** Java 21 LTS, Spring Boot 4.1.0 (Spring MVC, Spring Data JPA, Validation, Scheduling, DevTools).
* **Frontend:** Thymeleaf Template Engine, HTML5 Semântico, CSS3 Moderno (Glassmorphism), JavaScript ES6+ Nativo, Chart.js, FontAwesome 6.4.
* **Banco de Dados:** MariaDB 11.4 (porta dedicada 3307).
* **Comunicação em Tempo Real:** Server-Sent Events (SSE) via `SseEmitter` Spring com keepalive a cada 15s e fallback polling a cada 2,5s.
* **Notificações por E-mail:** `EmailNotificacaoService` para envio assíncrono de credenciais provisórias e confirmações.
* **Documentação de API:** Springdoc OpenAPI / Swagger UI 3.0.
* **Túnel Público Seguro:** Cloudflare Tunnel (HTTPS criptografado de ponta a ponta).

---

## ⚡ Comunicação em Tempo Real (Server-Sent Events)

| Evento | Origem | Destino | Efeito Visual / Ação |
| :--- | :--- | :--- | :--- |
| `NOVO_AGENDAMENTO` | Paciente / Recepção | Médico | Atualiza tabela de agendamentos e dispara toast |
| `AGENDAMENTO_CONFIRMADO` | Médico | Paciente | Atualiza status da consulta para "Confirmado" |
| `REAGENDAMENTO_SOLICITADO` | Médico | Paciente | Exibe card de proposta de novo horário com justificativa |
| `REAGENDAMENTO_ACEITO` | Paciente | Médico | Atualiza consulta remarcada na agenda |
| `PACIENTE_CHAMADO` | Médico / Recepção | Paciente & Telão TV | Dispara alerta sonoro, pisca painel e exibe sala de destino |
| `FILA_ATUALIZADA` | Médico / Recepção | Geral | Recalcula posições e tempos de espera |
| `NOVA_MENSAGEM_CHAT` | Médico / Recepção | Destinatário Privado | Entrega mensagem instantânea no chat e atualiza badge |
| `NOVO_RECEITUARIO_DISPONIVEL`| Médico | Paciente / Recepção | Disponibiliza imediatamente o receituário digital para impressão |
| `PING` | Servidor | Clientes SSE | Keepalive periódico a cada 15s para manter conexão estável |

---

## 📚 Endpoints da API REST (Swagger OpenAPI)

A documentação interativa completa está disponível em [`/swagger-ui.html`](https://publicly-chem-nursery-chapter.trycloudflare.com/swagger-ui.html).

### 🔑 Autenticação & Cadastro (`/api/auth`)
* `POST /api/auth/cadastro-teste`: Cria paciente express e usuário de teste.
* `POST /api/auth/cadastro-real`: Cria cadastro completo com prontuário oficial.
* `POST /api/auth/login`: Autentica usuário e retorna perfil e rota inteligente (`/medico/portal`, `/recepcao/portal`, etc.).

### 🎧 Recepção & Atendimento Presencial (`/api/recepcao`)
* `POST /api/recepcao/cadastrar-paciente`: Cadastro presencial com CPF como login, envio de e-mail com senha e inclusão opcional na fila.
* `GET /api/recepcao/pacientes`: Listagem e busca de pacientes por Nome, CPF ou E-mail.
* `PUT /api/recepcao/pacientes/{id}`: Atualização de dados cadastrais demográficos.
* `POST /api/recepcao/pacientes/{id}/reset-senha`: Redefinição administrativa de senha de paciente.
* `POST /api/recepcao/fila/incluir`: Inclusão direta de paciente na fila de espera do dia.
* `POST /api/recepcao/fila/{filaId}/remover`: Cancelamento e remoção de paciente da fila.
* `POST /api/recepcao/fila/{filaId}/rechamar`: Disparo de nova chamada com alerta sonoro no telão TV.
* `GET /api/recepcao/agenda-hoje`: Lista de agendamentos ambulatoriais do dia.
* `POST /api/recepcao/agendar-presencial`: Agendamento presencial rápido de consulta.
* `GET /api/recepcao/pacientes/{pacienteId}/receituarios`: Consulta de receitas médicas do paciente (somente leitura).

### 💬 Chat Privado em Tempo Real (`/api/chat`)
* `POST /api/chat/enviar`: Envio de mensagem privada 1-para-1 com broadcast SSE instantâneo.
* `GET /api/chat/conversa`: Recuperação do histórico cronológico de conversa privada entre dois usuários.
* `GET /api/chat/equipe`: Listagem dos membros da equipe (médicos e recepção) com status online/offline em tempo real.
* `POST /api/chat/heartbeat`: Atualização do status online do usuário conectado.

### 📅 Agendamentos & Triagem (`/api/agendamentos`)
* `POST /api/agendamentos`: Solicita novo agendamento (exige anamnese).
* `POST /api/agendamentos/{id}/confirmar`: Médico confirma a consulta.
* `POST /api/agendamentos/reagendar`: Médico propõe novo horário com motivo.
* `POST /api/agendamentos/{id}/aceitar-reagendamento`: Paciente aceita proposta médica.

### 🚶 Fila de Atendimento (`/api/fila`)
* `POST /api/fila/adicionar`: Adiciona paciente agendado à fila do dia.
* `POST /api/fila/chamar`: Dispara chamada sonora e visual para o paciente e telão TV.
* `POST /api/fila/{id}/status`: Altera status (`EM_ATENDIMENTO`, `FINALIZADO`, `AUSENTE`, `CANCELADO`).
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
java -jar target/doc-emed-1.1.0.jar
```

Acesse em seu navegador: **`http://localhost:8080/`**

---

## 🖥️ Implantação 24/7 na Máquina Virtual

A plataforma está implantada de forma 100% autônoma dentro da VM **Hyper-V (`RageMU-Dev` - `192.168.240.10`)**:
* **Diretório:** `C:\DocEMed-App`
* **JAR:** `doc-emed-1.1.0.jar`
* **Serviço do Windows:** Tarefa Agendada `DocEMed-24x7` executada no boot como `SYSTEM`.
* **Isolamento Total:** Banco MariaDB na porta **3307** dedicada.
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