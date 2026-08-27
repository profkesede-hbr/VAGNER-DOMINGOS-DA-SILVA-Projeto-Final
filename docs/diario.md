# Diário de Desenvolvimento — Doc-eMed

## Agosto 2026

### 25/08/2026
- ✅ Removido pacote legado `br.com.filamed` (FilamedBackendApplication + TesteController)
- ✅ Criada classe de teste oficial: `DoceMedApplicationTests`
- ✅ Documentação da pasta `docs/` atualizada com dados reais do projeto
- ✅ Confirmado: `GET /anamnese/perguntas` retorna exatamente **144 itens** ativos
- ✅ Servidor rodando limpo sem conflitos de classes `main`

### 24/08/2026
- ✅ Corrigido erro 500 na criação de paciente (tabela não existia por conflito de pacotes)
- ✅ Seed completo: `DataLoader` carrega as 144 perguntas do PDF automaticamente
- ✅ Implementadas seções: Alopecias Não Cicatriciais, Alopecias Cicatriciais, Indicação do Terapeuta
- ✅ Campos `dataCadastro` e `dataPreenchimento` com `columnDefinition = "DATETIME"` para compatibilidade MySQL 5.5
- ✅ `pom.xml` configurado com `<mainClass>br.com.docemed.DoceMedApplication</mainClass>` para evitar ambiguidade

### 23/08/2026 (estimado)
- ✅ Implementação de `TricoscopiaInfo` (@Embeddable) com 12 campos tricoscópicos
- ✅ Implementação de `ExameLaboratorial` (@Embeddable) com 21 exames laboratoriais
- ✅ Exames laboratoriais integrados como parte do contexto da Anamnese
- ✅ Implementação do fluxo de Alopecias (abordagem híbrida: texto digitado + scan opcional)
- ✅ Campo `dataCadastro` adicionado ao cadastro de pacientes

### 22/08/2026 (estimado)
- ✅ Módulo de Pacientes: CRUD completo com validação de CPF duplicado
- ✅ Módulo de Anamnese: Histórico de Saúde, Medicamentos, Saúde Ginecológica
- ✅ Escala de Bristol implementada como Enum (`TipoIntestinalBristol`)
- ✅ Aspecto do Cabelo, Dados Clínicos, Hábitos de vida implementados
- ✅ Histórico Familiar implementado
- ✅ `GlobalExceptionHandler` com respostas JSON padronizadas
- ✅ Configuração do Swagger UI ativa em `http://localhost:8080/swagger-ui.html`

### 18/08/2026
- ✅ Projeto criado com Spring Initializr
- ✅ Estrutura de pacotes definida: `model`, `dto`, `controller`, `service`, `repository`, `exception`, `config`
- ✅ Conexão com MySQL local configurada (`docemed` database)
- ✅ Primeiros endpoints da Anamnese testados no Swagger
