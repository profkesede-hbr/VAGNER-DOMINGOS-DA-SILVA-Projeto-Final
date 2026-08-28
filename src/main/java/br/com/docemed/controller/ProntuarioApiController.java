package br.com.docemed.controller;

import br.com.docemed.dto.ProntuarioPacienteResumoDTO;
import br.com.docemed.dto.ProntuarioRequestDTO;
import br.com.docemed.model.ProntuarioAtendimento;
import br.com.docemed.service.ProntuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/prontuarios")
@RequiredArgsConstructor
@Tag(name = "Prontuário & Receituário", description = "Endpoints para Evolução Clínica, Diagnóstico e Prescrição Digital")
public class ProntuarioApiController {

    private final ProntuarioService prontuarioService;

    @PostMapping
    @Operation(summary = "Registrar Evolução Clínica & Receituário (Médico)")
    public ResponseEntity<ProntuarioAtendimento> registrarAtendimento(@Valid @RequestBody ProntuarioRequestDTO dto) {
        ProntuarioAtendimento p = prontuarioService.registrarAtendimento(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(p);
    }

    @GetMapping("/paciente/{pacienteId}")
    @Operation(summary = "Listar Histórico Completo de Prontuários (Médico)")
    public ResponseEntity<List<ProntuarioAtendimento>> listarHistoricoPaciente(@PathVariable Long pacienteId) {
        return ResponseEntity.ok(prontuarioService.listarHistoricoPaciente(pacienteId));
    }

    @GetMapping("/paciente/{pacienteId}/resumo")
    @Operation(summary = "Obter Prontuário Resumido & Receituário do Paciente (Paciente)")
    public ResponseEntity<ProntuarioPacienteResumoDTO> obterResumoPaciente(@PathVariable Long pacienteId) {
        return prontuarioService.obterResumoPaciente(pacienteId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar Atendimento por ID")
    public ResponseEntity<ProntuarioAtendimento> buscarPorId(@PathVariable Long id) {
        return prontuarioService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
