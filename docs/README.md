# Doc-eMed — Documentação Técnica

Sistema de gestão clínica para terapeutas capilares. Digitaliza a **Ficha de Avaliação Capilar** da SPA Brasil Cursos com 144 questões clínicas.

---

## Como executar o projeto

### Pré-requisitos
- Java 17+ instalado
- MySQL rodando localmente na porta `3306`
- Banco de dados `docemed` (criado automaticamente pelo Spring)

### Iniciar o servidor

```bash
.\mvnw.cmd spring-boot:run
```

O servidor sobe na porta **8080**.

### Acessar o Swagger
```
http://localhost:8080/swagger-ui.html
```

---

## Endpoints disponíveis

### Pacientes — `/pacientes`
| Método | Rota | Descrição |
|---|---|---|
| POST | `/pacientes` | Cadastrar novo paciente |
| GET | `/pacientes` | Listar todos os pacientes |
| GET | `/pacientes/{id}` | Buscar paciente por ID |
| GET | `/pacientes/buscar?nome=` | Buscar paciente por nome |
| PUT | `/pacientes/{id}` | Atualizar dados do paciente |
| DELETE | `/pacientes/{id}` | Inativar paciente |

### Anamnese — `/anamnese`
| Método | Rota | Descrição |
|---|---|---|
| POST | `/anamnese` | Criar nova ficha de avaliação |
| GET | `/anamnese/{id}` | Buscar ficha por ID |
| GET | `/anamnese/paciente/{id}` | Histórico completo do paciente |
| GET | `/anamnese/paciente/{id}/recente` | Última avaliação do paciente |
| DELETE | `/anamnese/{id}` | Remover ficha |

### Perguntas — `/anamnese/perguntas`
| Método | Rota | Descrição |
|---|---|---|
| GET | `/anamnese/perguntas` | Listar perguntas ativas (144) |
| GET | `/anamnese/perguntas/todas` | Listar todas (incluindo inativas) |
| POST | `/anamnese/perguntas` | Criar nova pergunta |
| PUT | `/anamnese/perguntas/{id}` | Editar pergunta |
| PATCH | `/anamnese/perguntas/{id}/desativar` | Desativar pergunta |
| PATCH | `/anamnese/perguntas/{id}/ativar` | Ativar pergunta |
| DELETE | `/anamnese/perguntas/{id}` | Excluir pergunta |

---

## Documentação detalhada

- [arquitetura.md](./arquitetura.md) — Estrutura de pacotes, tecnologias e fluxo em camadas
- [requisitos.md](./requisitos.md) — Requisitos funcionais e não funcionais detalhados
- [roadmap.md](./roadmap.md) — Histórico de entregas e próximas etapas
- [diario.md](./diario.md) — Diário de desenvolvimento
- [uso-de-ia.md](./uso-de-ia.md) — Registro do uso de IA no desenvolvimento
