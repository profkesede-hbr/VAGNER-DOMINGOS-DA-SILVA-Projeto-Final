# Roadmap — Doc-eMed

## ✅ Fase 1 — Backend Core (Concluída)

### Sprint 1 — Estrutura Inicial
- [x] Criação do projeto Spring Boot
- [x] Configuração do banco MySQL local
- [x] Configuração do Swagger UI
- [x] Remoção de arquivos legados do projeto anterior (FilaMed)

### Sprint 2 — Módulo de Pacientes
- [x] Entidade `Paciente` com todos os campos da ficha (Nome, CPF, Data Nasc., WhatsApp, Endereço, CEP, Bairro, Cidade, E-mail, Profissão, Convênio, Indicado por, Queixa principal)
- [x] Campo `dataCadastro` gerado automaticamente via `@PrePersist`
- [x] CRUD completo: criar, listar, buscar por ID, buscar por nome, atualizar, inativar
- [x] Tratamento de erro: CPF duplicado retorna mensagem clara
- [x] GlobalExceptionHandler com respostas JSON padronizadas

### Sprint 3 — Módulo de Anamnese
- [x] Entidade `Anamnese` vinculada ao `Paciente` via `pacienteId`
- [x] Seção de Tricologia Inicial (tipo de cabelo, pigmentação, característica do couro)
- [x] Seção de Alimentação (frutas, legumes, água, glúten/lactose/gordura)
- [x] Histórico de Saúde — Perguntas 1 a 25 (doenças sistêmicas, alergias, cirurgias...)
- [x] Medicamentos e Saúde Ginecológica — Perguntas 26 a 32
- [x] Escala de Bristol implementada como Enum (`TIPO_1` a `TIPO_7`)
- [x] Histórico da Queda Capilar — Perguntas 33 a 37
- [x] Aspecto do Cabelo (química, tricoptilose, triconodose, fio poroso/desidratado...)
- [x] Dados Clínicos (implante, teste de tração, caspa, dermatite, psoríase, pediculose...)
- [x] Hábitos de estilo de vida (lavagem, secador, prancha, atividade física...)
- [x] Histórico Familiar (calvície, alopecia areata)

### Sprint 4 — Tricoscopia e Exames
- [x] `TricoscopiaInfo` (@Embeddable) com 12 campos de exame tricoscópico e localização
- [x] `ExameLaboratorial` (@Embeddable) com 21 exames: Hemograma, Ferritina, Vitamina D, TSH, T4 Livre, Anti-TPO, DHT, Testosterona, Zinco, Cobre, etc.
- [x] Exames laboratoriais integrados como parte da Anamnese (não módulo separado)

### Sprint 5 — Alopecias e Indicação do Terapeuta
- [x] `AlopeciaInfo` (@Embeddable): Androgenética (Hamilton/Ludwig + caminho do scan), Areata, Eflúvio Telógeno (10 causas), Eflúvio Anágeno (5 causas), Cicatriciais (5 tipos)
- [x] Indicação do Terapeuta: aspecto observado, tratamento indicado, número de aplicações, manutenção em casa, termo de responsabilidade

### Sprint 6 — Catálogo de Perguntas
- [x] Entidade `PerguntaAnamnese` com texto, tipo, ordem e status
- [x] `DataLoader` carregando automaticamente as 144 perguntas do PDF na inicialização
- [x] Endpoints CRUD completos para gerenciar o catálogo
- [x] Verificação: `GET /anamnese/perguntas` retorna exatamente 144 itens

---

## 🔜 Fase 2 — Funcionalidades Avançadas (Próximas Etapas)

- [ ] Upload de arquivo digitalizado (scan das alopecias) vinculado à anamnese
- [ ] Geração de PDF da ficha de avaliação preenchida
- [ ] Autenticação JWT (login do terapeuta/administrador)
- [ ] Controle de acesso por perfil (ROLE_ADMIN, ROLE_TERAPEUTA)
- [ ] Dashboard com estatísticas: total de pacientes, avaliações por período, tipos de alopecia mais frequentes

---

## 🔜 Fase 3 — Frontend / Aplicativo

- [ ] Definir tecnologia do frontend (web app ou aplicativo Android)
- [ ] Tela de cadastro de pacientes
- [ ] Tela de preenchimento digital da ficha de anamnese
- [ ] Tela de histórico/evolução do paciente
- [ ] Integração com backend via REST API
