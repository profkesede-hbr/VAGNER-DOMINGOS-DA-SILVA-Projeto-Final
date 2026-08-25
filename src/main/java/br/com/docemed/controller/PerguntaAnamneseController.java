package br.com.docemed.controller;

import br.com.docemed.dto.PerguntaAnamneseRequestDTO;
import br.com.docemed.dto.PerguntaAnamneseResponseDTO;
import br.com.docemed.service.PerguntaAnamneseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "2. Perguntas da Anamnese", description = "Endpoints para gerenciamento de perguntas dinâmicas da anamnese pelo Administrador")
@RestController
@RequestMapping("/anamnese/perguntas")
@RequiredArgsConstructor
public class PerguntaAnamneseController {

    private final PerguntaAnamneseService service;

    @Operation(summary = "Listar perguntas ativas", description = "Retorna apenas as perguntas ativas que o paciente deve responder no aplicativo")
    @GetMapping
    public ResponseEntity<List<PerguntaAnamneseResponseDTO>> listarAtivas() {
        return ResponseEntity.ok(service.listarAtivas());
    }

    @Operation(summary = "Listar todas as perguntas", description = "Retorna todas as perguntas (ativas e inativas) para o painel do Administrador")
    @GetMapping("/todas")
    public ResponseEntity<List<PerguntaAnamneseResponseDTO>> listarTodas() {
        return ResponseEntity.ok(service.listarTodas());
    }

    @Operation(summary = "Buscar pergunta por ID")
    @GetMapping("/{id}")
    public ResponseEntity<PerguntaAnamneseResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @Operation(summary = "Cadastrar nova pergunta (Admin)")
    @PostMapping
    public ResponseEntity<PerguntaAnamneseResponseDTO> criar(
            @RequestBody @Valid PerguntaAnamneseRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.criar(dto));
    }

    @Operation(summary = "Atualizar pergunta existente (Admin)")
    @PutMapping("/{id}")
    public ResponseEntity<PerguntaAnamneseResponseDTO> atualizar(
            @PathVariable Long id,
            @RequestBody @Valid PerguntaAnamneseRequestDTO dto) {
        return ResponseEntity.ok(service.atualizar(id, dto));
    }

    @Operation(summary = "Ativar pergunta (Admin)")
    @PatchMapping("/{id}/ativar")
    public ResponseEntity<PerguntaAnamneseResponseDTO> ativar(@PathVariable Long id) {
        return ResponseEntity.ok(service.alterarStatus(id, true));
    }

    @Operation(summary = "Desativar pergunta (Admin)")
    @PatchMapping("/{id}/desativar")
    public ResponseEntity<PerguntaAnamneseResponseDTO> desativar(@PathVariable Long id) {
        return ResponseEntity.ok(service.alterarStatus(id, false));
    }

    @Operation(summary = "Excluir pergunta (Admin)")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
