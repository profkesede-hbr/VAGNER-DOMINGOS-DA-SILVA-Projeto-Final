package br.com.docemed.controller;

import br.com.docemed.dto.AgendamentoRequestDTO;
import br.com.docemed.dto.ReagendamentoDTO;
import br.com.docemed.model.Agendamento;
import br.com.docemed.service.AgendamentoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/agendamentos")
@RequiredArgsConstructor
@Tag(name = "Agendamentos", description = "Gestão de Consultas, Confirmação e Reagendamento Médico")
public class AgendamentoApiController {

    private final AgendamentoService agendamentoService;

    @PostMapping
    @Operation(summary = "Solicitar Agendamento", description = "Registra a solicitação de consulta após o preenchimento obrigatório da anamnese.")
    public ResponseEntity<?> solicitarAgendamento(@Valid @RequestBody AgendamentoRequestDTO dto) {
        try {
            Agendamento agendamento = agendamentoService.solicitarAgendamento(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(agendamento);
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping
    @Operation(summary = "Listar Todos os Agendamentos")
    public ResponseEntity<List<Agendamento>> listarTodos() {
        return ResponseEntity.ok(agendamentoService.listarTodos());
    }

    @GetMapping("/paciente/{pacienteId}")
    @Operation(summary = "Listar Agendamentos por Paciente")
    public ResponseEntity<List<Agendamento>> listarPorPaciente(@PathVariable Long pacienteId) {
        return ResponseEntity.ok(agendamentoService.listarPorPaciente(pacienteId));
    }

    @GetMapping("/paciente/{pacienteId}/recente")
    @Operation(summary = "Obter Agendamento mais recente do Paciente")
    public ResponseEntity<Agendamento> buscarRecentePorPaciente(@PathVariable Long pacienteId) {
        Agendamento ag = agendamentoService.buscarRecentePorPaciente(pacienteId);
        if (ag == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(ag);
    }

    @PostMapping("/{id}/confirmar")
    @Operation(summary = "Confirmar Consulta (Médico)")
    public ResponseEntity<Agendamento> confirmarAgendamento(@PathVariable Long id) {
        return ResponseEntity.ok(agendamentoService.confirmarAgendamento(id));
    }

    @PostMapping("/reagendar")
    @Operation(summary = "Reagendar Consulta com Justificativa (Médico)")
    public ResponseEntity<Agendamento> reagendarMedico(@Valid @RequestBody ReagendamentoDTO dto) {
        return ResponseEntity.ok(agendamentoService.reagendarMedico(dto));
    }

    @PostMapping("/{id}/aceitar-reagendamento")
    @Operation(summary = "Aceitar Novo Horário de Consulta (Paciente)")
    public ResponseEntity<Agendamento> aceitarReagendamento(@PathVariable Long id) {
        return ResponseEntity.ok(agendamentoService.aceitarReagendamento(id));
    }
}
