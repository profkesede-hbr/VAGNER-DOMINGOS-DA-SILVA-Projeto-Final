package br.com.docemed.controller;

import br.com.docemed.dto.AnamneseRequestDTO;
import br.com.docemed.dto.AnamneseResponseDTO;
import br.com.docemed.service.AnamneseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "1. Anamnese do Paciente", description = "Endpoints para registro, consulta e histórico de fichas médicas de anamnese")
@RestController
@RequestMapping("/anamnese")
@RequiredArgsConstructor
public class AnamneseController {

    private final AnamneseService service;

    @Operation(summary = "Registrar nova Anamnese", description = "Salva o formulário preenchido pelo paciente (perguntas fixas do documento e respostas dinâmicas)")
    @PostMapping
    public ResponseEntity<AnamneseResponseDTO> registrar(@RequestBody AnamneseRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.registrar(dto));
    }

    @Operation(summary = "Buscar Anamnese por ID")
    @GetMapping("/{id}")
    public ResponseEntity<AnamneseResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @Operation(summary = "Histórico de Anamneses do Paciente", description = "Lista todas as anamneses registradas para um determinado paciente")
    @GetMapping("/paciente/{pacienteId}")
    public ResponseEntity<List<AnamneseResponseDTO>> listarPorPaciente(@PathVariable Long pacienteId) {
        return ResponseEntity.ok(service.listarPorPaciente(pacienteId));
    }

    @Operation(summary = "Buscar Anamnese mais recente do Paciente", description = "Retorna a última anamnese preenchida pelo paciente para o médico visualizar na consulta")
    @GetMapping("/paciente/{pacienteId}/recente")
    public ResponseEntity<AnamneseResponseDTO> buscarMaisRecentePorPaciente(@PathVariable Long pacienteId) {
        return ResponseEntity.ok(service.buscarMaisRecentePorPaciente(pacienteId));
    }

    @Operation(summary = "Excluir Anamnese")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
