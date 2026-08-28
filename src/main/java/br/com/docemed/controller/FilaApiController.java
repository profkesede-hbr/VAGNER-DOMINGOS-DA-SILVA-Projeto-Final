package br.com.docemed.controller;

import br.com.docemed.dto.FilaItemDTO;
import br.com.docemed.model.FilaAtendimento;
import br.com.docemed.model.StatusFila;
import br.com.docemed.service.FilaService;
import br.com.docemed.service.RealtimeNotificationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/fila")
@RequiredArgsConstructor
@Tag(name = "Fila & Tempo Real", description = "Endpoints para Controle de Fila, Chamada de Pacientes e SSE em Tempo Real")
public class FilaApiController {

    private final FilaService filaService;
    private final RealtimeNotificationService realtimeService;

    @GetMapping("/ativa")
    @Operation(summary = "Listar Fila de Atendimento Ativa")
    public ResponseEntity<List<FilaItemDTO>> listarFilaAtiva() {
        return ResponseEntity.ok(filaService.listarFilaAtiva());
    }

    @PostMapping("/adicionar")
    @Operation(summary = "Adicionar Paciente à Fila de Espera (Médico/Recepção)")
    public ResponseEntity<FilaAtendimento> adicionarAFila(@RequestBody Map<String, Object> body) {
        Long pacienteId = Long.valueOf(body.get("pacienteId").toString());
        Long agendamentoId = body.get("agendamentoId") != null ? Long.valueOf(body.get("agendamentoId").toString()) : null;
        String sala = body.get("sala") != null ? body.get("sala").toString() : "Consultório 01";
        String medicoNome = body.get("medicoNome") != null ? body.get("medicoNome").toString() : "Dr. Especialista Tricologista";

        FilaAtendimento item = filaService.adicionarAFila(pacienteId, agendamentoId, sala, medicoNome);
        return ResponseEntity.ok(item);
    }

    @PostMapping("/reordenar")
    @Operation(summary = "Reordenar Fila de Espera (Médico)")
    public ResponseEntity<Void> reordenarFila(@RequestBody List<Long> idsOrdenados) {
        filaService.reordenarFila(idsOrdenados);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/chamar")
    @Operation(summary = "Chamar Próximo Paciente para Atendimento (Médico)")
    public ResponseEntity<FilaAtendimento> chamarPaciente(@RequestBody Map<String, Object> body) {
        Long filaId = Long.valueOf(body.get("filaId").toString());
        String sala = body.get("sala") != null ? body.get("sala").toString() : "Consultório 01";
        String medicoNome = body.get("medicoNome") != null ? body.get("medicoNome").toString() : "Dr. Especialista Tricologista";

        FilaAtendimento item = filaService.chamarPaciente(filaId, sala, medicoNome);
        return ResponseEntity.ok(item);
    }

    @PostMapping("/{id}/status")
    @Operation(summary = "Alterar Status de Atendimento (Em Atendimento, Finalizado, Ausente)")
    public ResponseEntity<FilaAtendimento> atualizarStatus(@PathVariable Long id, @RequestParam StatusFila status) {
        return ResponseEntity.ok(filaService.atualizarStatus(id, status));
    }

    @GetMapping("/paciente/{pacienteId}/status")
    @Operation(summary = "Consultar Status do Paciente na Fila")
    public ResponseEntity<FilaItemDTO> consultarStatusPaciente(@PathVariable Long pacienteId) {
        return filaService.buscarStatusPaciente(pacienteId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ─── STREAMING DE EVENTOS EM TEMPO REAL (SSE) ───────────────────────────
    @GetMapping(value = "/realtime/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    @Operation(summary = "Stream SSE Global (Médicos / Telão TV)")
    public SseEmitter streamGlobal() {
        return realtimeService.subscribeGlobal();
    }

    @GetMapping(value = "/realtime/paciente/{pacienteId}/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    @Operation(summary = "Stream SSE Específico do Paciente")
    public SseEmitter streamPaciente(@PathVariable Long pacienteId) {
        return realtimeService.subscribePaciente(pacienteId);
    }
}
