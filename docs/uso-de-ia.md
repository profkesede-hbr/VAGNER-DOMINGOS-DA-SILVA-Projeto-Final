# Uso de Inteligência Artificial — Doc-eMed

## Ferramentas Utilizadas

Este projeto foi desenvolvido com o auxílio da ferramenta de IA **Google Antigravity (AGY)** como assistente de par de programação (*pair programming*).

---

## Como a IA foi utilizada

### 1. Arquitetura e Decisões de Design
- Definição da estrutura de pacotes (`model`, `dto`, `service`, `controller`, `repository`, `exception`, `config`)
- Escolha do padrão `@Embeddable` para as seções da ficha clínica (TricoscopiaInfo, AlopeciaInfo, ExameLaboratorial) para manter tudo em uma única tabela `anamneses`
- Separação em `RequestDTO` / `ResponseDTO` para proteção dos dados do modelo de banco

### 2. Geração e Revisão de Código
- Geração das entidades JPA com mapeamento de todos os campos da ficha física (144 questões)
- Implementação do `DataLoader` para seed automático das 144 perguntas na inicialização
- Implementação do `GlobalExceptionHandler` com respostas de erro padronizadas em JSON
- Criação do Enum `TipoIntestinalBristol` para a Escala de Bristol

### 3. Resolução de Problemas Técnicos
- Diagnóstico e correção do erro 500 causado por conflito de pacotes (`br.com.filamed` vs `br.com.docemed`)
- Correção de incompatibilidade do Hibernate 7 com MySQL 5.5.27 (campo `DATETIME(6)` não suportado → solução: `columnDefinition = "DATETIME"`)
- Identificação e remoção de código legado do projeto FilaMed que residia no repositório

### 4. Documentação
- Geração da documentação técnica completa da pasta `docs/` com dados reais do projeto
- Explicação didática do funcionamento de cada endpoint, pasta e tecnologia utilizada

---

## Limites do uso de IA

- Todas as decisões de negócio (campos da ficha, fluxo clínico, tipos de alopecias) foram definidas pelo desenvolvedor com base no **PDF original da Ficha de Avaliação Capilar da SPA Brasil Cursos**
- A IA foi usada como **assistente técnico**, não como tomador de decisões clínicas
- O código gerado foi sempre revisado e validado pelo desenvolvedor antes de ser aplicado

---

## Impacto no desenvolvimento

| Atividade | Sem IA (estimado) | Com IA |
|---|---|---|
| Estrutura inicial do projeto | 4-6 horas | ~30 minutos |
| Mapeamento das 144 perguntas | 2-3 dias | ~2 horas |
| Correção do erro MySQL 5.5 | Horas de pesquisa | ~15 minutos |
| Documentação técnica | 3-4 horas | ~30 minutos |
