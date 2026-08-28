package br.com.docemed.controller;

import br.com.docemed.dto.*;
import br.com.docemed.model.Agendamento;
import br.com.docemed.model.FilaAtendimento;
import br.com.docemed.model.Paciente;
import br.com.docemed.model.ProntuarioAtendimento;
import br.com.docemed.service.RecepcaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/recepcao")
@RequiredArgsConstructor
@Tag(name = "Recepção & Atendimento Presencial", description = "Endpoints para cadastro presencial, reset de senhas, inclusão e rechamada na fila, agendamento e visualização de receituários")
public class RecepcaoApiController {

    private final RecepcaoService recepcaoService;

    @PostMapping("/cadastrar-paciente")
    @Operation(summary = "Cadastrar Paciente Presencial (Sem celular/web)", description = "Cria paciente, gera login com CPF, cria senha provisória e simula envio de e-mail de acesso.")
    public ResponseEntity<LoginResponseDTO> cadastrarPacientePresencial(@Valid @RequestBody CadastroPresencialRequestDTO dto) {
        return ResponseEntity.ok(recepcaoService.cadastrarPacientePresencial(dto));
    }

    @GetMapping("/pacientes")
    @Operation(summary = "Listar/Buscar Pacientes", description = "Retorna lista de pacientes com filtro por nome, CPF ou e-mail.")
    public ResponseEntity<List<Paciente>> listarPacientes(@RequestParam(required = false) String query) {
        return ResponseEntity.ok(recepcaoService.listarPacientes(query));
    }

    @PutMapping("/pacientes/{id}")
    @Operation(summary = "Atualizar Dados Cadastrais do Paciente", description = "Edita informações demográficas, endereço e contato do paciente.")
    public ResponseEntity<Paciente> atualizarPaciente(@PathVariable Long id, @RequestBody PacienteEdicaoDTO dto) {
        return ResponseEntity.ok(recepcaoService.atualizarPaciente(id, dto));
    }

    @PostMapping("/pacientes/{id}/reset-senha")
    @Operation(summary = "Resetar / Trocar Senha do Paciente", description = "Altera a senha da conta do paciente e envia e-mail com as novas credenciais.")
    public ResponseEntity<?> resetarSenha(@PathVariable Long id, @RequestBody ResetSenhaRequestDTO dto) {
        recepcaoService.resetarSenhaPaciente(id, dto);
        return ResponseEntity.ok(Map.of("message", "Senha redefinida com sucesso. Notificação enviada por e-mail!"));
    }

    @PostMapping("/fila/incluir")
    @Operation(summary = "Incluir Paciente na Fila do Dia", description = "Adiciona paciente à fila de espera em tempo real.")
    public ResponseEntity<FilaAtendimento> incluirNaFila(@RequestParam Long pacienteId,
                                                         @RequestParam(defaultValue = "Consultório 01") String consultorio,
                                                         @RequestParam(defaultValue = "Dr. Vagner Domingos — Tricologia Integrada") String medicoNome) {
        return ResponseEntity.ok(recepcaoService.incluirPacienteNaFila(pacienteId, consultorio, medicoNome));
    }

    @PostMapping("/fila/{id}/remover")
    @Operation(summary = "Remover Paciente da Fila", description = "Altera status para cancelado e remove da fila ativa.")
    public ResponseEntity<?> removerDaFila(@PathVariable Long id) {
        recepcaoService.removerPacienteDaFila(id);
        return ResponseEntity.ok(Map.of("message", "Paciente removido da fila com sucesso."));
    }

    @PostMapping("/fila/{id}/rechamar")
    @Operation(summary = "Rechamar Paciente na Fila", description = "Dispara nova chamada visual e sonora no telão da recepção.")
    public ResponseEntity<FilaAtendimento> rechamarPaciente(@PathVariable Long id) {
        return ResponseEntity.ok(recepcaoService.rechamarPaciente(id));
    }

    @GetMapping("/agendamentos/hoje")
    @Operation(summary = "Listar Agendamentos de Hoje", description = "Retorna todas as consultas marcadas para a data de hoje.")
    public ResponseEntity<List<Agendamento>> listarAgendamentosHoje() {
        return ResponseEntity.ok(recepcaoService.listarAgendamentosHoje());
    }

    @PostMapping("/agendamentos")
    @Operation(summary = "Agendar Consulta Presencial", description = "Cria um novo agendamento de consulta na recepção.")
    public ResponseEntity<Agendamento> agendarPresencial(@RequestParam Long pacienteId,
                                                         @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataHora,
                                                         @RequestParam(defaultValue = "Dr. Vagner Domingos — Tricologia Integrada") String medicoNome) {
        return ResponseEntity.ok(recepcaoService.agendarConsultaPresencial(pacienteId, dataHora, medicoNome));
    }

    @GetMapping("/pacientes/{pacienteId}/receituarios")
    @Operation(summary = "Consultar Receituários do Paciente (Somente Leitura)", description = "Visualiza o histórico de prescrições médicas para impressão na recepção.")
    public ResponseEntity<List<ProntuarioAtendimento>> consultarReceituarios(@PathVariable Long pacienteId) {
        return ResponseEntity.ok(recepcaoService.consultarReceituariosPaciente(pacienteId));
    }
}
