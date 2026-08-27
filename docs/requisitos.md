# Requisitos do Sistema — Doc-eMed

## Objetivo

O **Doc-eMed** é um sistema backend para gestão clínica de terapeutas capilares.

Digitaliza a **Ficha de Avaliação Capilar** (SPA Brasil Cursos) com 144 questões clínicas, permitindo cadastro de pacientes, preenchimento da anamnese, gerenciamento do catálogo de perguntas e upload de documentos digitalizados.

---

## Perfis de Usuário

### Terapeuta / Profissional de Saúde

- Cadastrar e pesquisar pacientes
- Preencher a ficha de anamnese clínica completa
- Consultar histórico de avaliações do paciente
- Visualizar a última avaliação realizada
- Indicar tratamento e manutenção em casa
- Registrar observações tricoscópicas

### Administrador

- Gerenciar o catálogo de perguntas (criar, editar, ativar/desativar)
- Adicionar perguntas personalizadas sem alterar código
- Visualizar todas as perguntas (ativas e inativas)

---

## Funcionalidades Implementadas

### Cadastro de Pacientes (`/pacientes`)

| Campo | Tipo | Obrigatório |
|---|---|---|
| Nome completo | Texto | ✅ |
| CPF | Texto (único) | ✅ |
| Data de nascimento | Data | ✅ |
| Sexo | Texto | — |
| Telefone fixo | Texto | — |
| Celular / WhatsApp | Texto | — |
| E-mail | Texto | — |
| Endereço completo | Texto | — |
| Bairro, Cidade, CEP | Texto | — |
| Profissão | Texto | — |
| Convênio / Plano de saúde | Texto | — |
| Indicado por | Texto | — |
| Queixa principal | Texto longo | — |
| Data de cadastro | Data/Hora | Auto (sistema) |

---

### Ficha de Anamnese Clínica (`/anamnese`)

A ficha digital replica integralmente o formulário físico da SPA Brasil Cursos, estruturado em 8 seções:

**Seção 1 — Tricologia Inicial**
- Tipo de cabelo, pigmentação residual, característica do couro cabeludo

**Seção 2 — Alimentação e Hábitos**
- Consumo de frutas, legumes, água, restrições alimentares (glúten, lactose, gordura)

**Seção 3 — Histórico de Saúde (Perguntas 1 a 25)**
- Doenças cardiovasculares, diabetes, câncer, alergias, Covid-19, cirurgias, hepatites, etc.

**Seção 4 — Medicamentos & Saúde Ginecológica (Perguntas 26 a 32)**
- Uso de medicamentos, anticoncepcionais, SOP, menstruação, classificação pela Escala de Bristol

**Seção 5 — Histórico da Queda Capilar (Perguntas 33 a 37)**
- Tempo de queda, período marcante, nascimento de fios novos

**Seção 6 — Aspecto do Cabelo**
- Química nos últimos 12 meses, tricoptilose, triconodose, estado do fio

**Seção 7 — Dados Clínicos do Couro Cabeludo**
- Implante/transplante, teste de tração, caspa (seca/úmida), dermatite seborreica, psoríase, pediculose, tricotilomania, foliculite, sinais, falhas/rarefação

**Seção 8 — Hábitos (Estilo de Vida)**
- Frequência de lavagem, uso de secador/prancha, leave-in, cabelo preso, atividade física, dieta restritiva, uso de boné/capacete, tratamentos anteriores

**Seção 9 — Histórico Familiar**
- Calvície na família, casos de Alopecia Areata

**Seção 10 — Tricoscopia (Haste/Couro Cabeludo)**
- Diversidade de diâmetros, fios vellus, distância entre folículos, fios em tufos, pontos pretos/amarelos/brancos/vermelhos, sinal peripilar, regiões sem folículos, vasos arborizantes, descamação

**Seção 11 — Exames Laboratoriais (21 exames)**
- Hemograma, ácido fólico, ferritina, vitamina D, biotina, B12, B6, zinco, cobre, PCR, VHS, FAN, vitamina A, vitamina C, selênio, DHT, TSH, T4 Livre, Anti-TPO, vitamina E, testosterona

**Seção 12 — Alopecias**
- Não cicatriciais: Androgenética (Hamilton/Ludwig), Areata, Eflúvio Telógeno (causas), Eflúvio Anágeno (causas)
- Cicatriciais: Foliculite Queloidiana, Foliculite Decalvante, Fibrosante Frontal, Líquen Plano Pilar, Lúpus Discoide

**Seção 13 — Indicação do Terapeuta**
- Aspecto observado, tratamento indicado e número de aplicações, manutenção em casa, termo de responsabilidade

---

### Catálogo de Perguntas (`/anamnese/perguntas`)

- 144 perguntas/itens da ficha original carregados automaticamente na inicialização
- Perguntas ordenadas sequencialmente conforme o PDF da ficha física
- Administrador pode criar, editar, ativar/desativar ou excluir perguntas pelo Swagger
- Cada pergunta possui: texto, tipo de resposta, número de ordem, status (ativo/inativo)

---

## Requisitos Não Funcionais

- API REST com padrão de resposta JSON em todos os endpoints
- Tratamento de erros padronizado com mensagens claras (GlobalExceptionHandler)
- Documentação automática via Swagger UI (`http://localhost:8080/swagger-ui.html`)
- Banco de dados compatível com MySQL 5.5+
- Execução local via Maven Wrapper (`.\mvnw.cmd spring-boot:run`)
- `dataCadastro` gerada automaticamente pelo servidor (sem intervenção do usuário)

---

## Funcionalidades Futuras

- [ ] Autenticação e controle de acesso (JWT)
- [ ] Upload de arquivo digitalizado vinculado à alopecia
- [ ] Geração de relatório PDF da ficha de avaliação
- [ ] Dashboard com estatísticas de atendimentos
- [ ] Módulo de evolução e acompanhamento do paciente