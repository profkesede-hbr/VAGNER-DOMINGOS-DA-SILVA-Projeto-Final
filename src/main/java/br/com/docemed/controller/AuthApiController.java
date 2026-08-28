package br.com.docemed.controller;

import br.com.docemed.dto.CadastroPacienteTesteDTO;
import br.com.docemed.dto.LoginDTO;
import br.com.docemed.dto.LoginResponseDTO;
import br.com.docemed.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(name = "Autenticação", description = "Endpoints para Login e Cadastro Rápido de Pacientes")
public class AuthApiController {

    private final AuthService authService;

    @PostMapping("/cadastro-teste")
    @Operation(summary = "Cadastrar Paciente Teste e Criar Usuário", description = "Cria um cadastro simplificado de paciente teste e conta de usuário para testes da plataforma.")
    public ResponseEntity<LoginResponseDTO> cadastrarPacienteTeste(@Valid @RequestBody CadastroPacienteTesteDTO dto) {
        LoginResponseDTO response = authService.cadastrarPacienteTeste(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/login")
    @Operation(summary = "Autenticar Usuário", description = "Valida login e senha para acesso de médico ou paciente.")
    public ResponseEntity<LoginResponseDTO> login(@Valid @RequestBody LoginDTO dto) {
        LoginResponseDTO response = authService.autenticar(dto);
        return ResponseEntity.ok(response);
    }
}
