package br.com.docemed.controller;

import br.com.docemed.dto.PacienteRequestDTO;
import br.com.docemed.dto.PacienteResponseDTO;
import br.com.docemed.service.PacienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller REST para o módulo de Cadastro de Pacientes/Clientes (Doc-eMed).
 *
 * Endpoints:
 *   POST   /pacientes              → cadastra novo paciente (passo 1 da Ficha)
 *   GET    /pacientes              → lista todos os pacientes ativos
 *   GET    /pacientes/{id}         → busca paciente por ID
 *   GET    /pacientes/buscar?nome= → busca pacientes por nome
 *   PUT    /pacientes/{id}         → atualiza dados do paciente
 *   DELETE /pacientes/{id}         → inativa paciente (soft delete)
 */
@Tag(name = "0. Cadastro de Pacientes", description = "Passo 1 da Ficha: Dados do Cliente — Nome, Contato, Endereço e Convênio")
@RestController
@RequestMapping("/pacientes")
@RequiredArgsConstructor
public class PacienteController {

    private final PacienteService service;

    @Operation(
            summary = "Cadastrar novo Paciente",
            description = "Primeiro passo da Ficha de Avaliação: registra Nome, Data de Nasc., Endereço, Contato, Convênio e Queixa Principal."
    )
    @PostMapping
    public ResponseEntity<PacienteResponseDTO> cadastrar(@RequestBody @Valid PacienteRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.cadastrar(dto));
    }

    @Operation(summary = "Listar todos os pacientes ativos")
    @GetMapping
    public ResponseEntity<List<PacienteResponseDTO>> listar() {
        return ResponseEntity.ok(service.listarAtivos());
    }

    @Operation(summary = "Buscar paciente por ID")
    @GetMapping("/{id}")
    public ResponseEntity<PacienteResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @Operation(summary = "Buscar paciente por nome", description = "Busca parcial e sem distinção de maiúsculas/minúsculas")
    @GetMapping("/buscar")
    public ResponseEntity<List<PacienteResponseDTO>> buscarPorNome(@RequestParam String nome) {
        return ResponseEntity.ok(service.buscarPorNome(nome));
    }

    @Operation(summary = "Atualizar dados do Paciente")
    @PutMapping("/{id}")
    public ResponseEntity<PacienteResponseDTO> atualizar(
            @PathVariable Long id,
            @RequestBody @Valid PacienteRequestDTO dto) {
        return ResponseEntity.ok(service.atualizar(id, dto));
    }

    @Operation(summary = "Inativar Paciente", description = "Realiza a inativação do cadastro (soft delete — o registro não é apagado do banco)")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> inativar(@PathVariable Long id) {
        service.inativar(id);
        return ResponseEntity.noContent().build();
    }
}
