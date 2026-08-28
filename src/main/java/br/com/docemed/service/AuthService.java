package br.com.docemed.service;

import br.com.docemed.dto.CadastroPacienteTesteDTO;
import br.com.docemed.dto.LoginDTO;
import br.com.docemed.dto.LoginResponseDTO;
import br.com.docemed.model.Paciente;
import br.com.docemed.model.PerfilUsuario;
import br.com.docemed.model.Usuario;
import br.com.docemed.repository.PacienteRepository;
import br.com.docemed.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final PacienteRepository pacienteRepository;

    @Transactional
    public LoginResponseDTO cadastrarPacienteTeste(CadastroPacienteTesteDTO dto) {
        if (usuarioRepository.existsByLogin(dto.getLogin())) {
            throw new IllegalArgumentException("O login '" + dto.getLogin() + "' já está em uso. Por favor, escolha outro.");
        }

        // 1. Criar registro de Paciente
        Paciente paciente = new Paciente();
        paciente.setNome(dto.getNome());
        paciente.setTelefone(dto.getTelefoneWhatsapp());
        paciente.setCelularWhatsapp(dto.getTelefoneWhatsapp());
        paciente.setEmail(dto.getEmail() != null && !dto.getEmail().isBlank() ? dto.getEmail() : dto.getLogin() + "@docemed.local");
        paciente.setCpf(dto.getCpf() != null && !dto.getCpf().isBlank() ? dto.getCpf() : "000.000.000-00");
        paciente.setSexo(dto.getSexo() != null ? dto.getSexo() : "Não informado");
        paciente.setCidade(dto.getCidade() != null ? dto.getCidade() : "São Paulo");
        paciente.setAtivo(true);
        paciente.setDataCadastro(LocalDateTime.now());

        if (dto.getDataNascimento() != null && !dto.getDataNascimento().isBlank()) {
            try {
                paciente.setDataNascimento(LocalDate.parse(dto.getDataNascimento()));
            } catch (Exception ignored) {
                paciente.setDataNascimento(LocalDate.of(1995, 1, 1));
            }
        } else {
            paciente.setDataNascimento(LocalDate.of(1995, 1, 1));
        }

        paciente = pacienteRepository.save(paciente);

        // 2. Criar registro de Usuário
        Usuario usuario = Usuario.builder()
                .login(dto.getLogin().trim().toLowerCase())
                .senha(dto.getSenha())
                .nome(dto.getNome())
                .perfil(PerfilUsuario.PACIENTE)
                .pacienteId(paciente.getId())
                .telefoneWhatsapp(dto.getTelefoneWhatsapp())
                .ativo(true)
                .dataCadastro(LocalDateTime.now())
                .build();

        usuario = usuarioRepository.save(usuario);

        return LoginResponseDTO.builder()
                .id(usuario.getId())
                .login(usuario.getLogin())
                .nome(usuario.getNome())
                .perfil(usuario.getPerfil())
                .pacienteId(paciente.getId())
                .token("TOKEN_SESSAO_" + usuario.getId())
                .redirectUrl("/paciente/portal")
                .build();
    }

    public LoginResponseDTO autenticar(LoginDTO dto) {
        Usuario usuario = usuarioRepository.findByLogin(dto.getLogin().trim().toLowerCase())
                .orElseThrow(() -> new IllegalArgumentException("Usuário ou senha inválidos."));

        if (!usuario.getSenha().equals(dto.getSenha())) {
            throw new IllegalArgumentException("Usuário ou senha inválidos.");
        }

        if (!usuario.getAtivo()) {
            throw new IllegalStateException("Esta conta está desativada no sistema.");
        }

        String redirectUrl = usuario.getPerfil() == PerfilUsuario.MEDICO || usuario.getPerfil() == PerfilUsuario.ADMIN
                ? "/medico/portal"
                : "/paciente/portal";

        return LoginResponseDTO.builder()
                .id(usuario.getId())
                .login(usuario.getLogin())
                .nome(usuario.getNome())
                .perfil(usuario.getPerfil())
                .pacienteId(usuario.getPacienteId())
                .token("TOKEN_SESSAO_" + usuario.getId())
                .redirectUrl(redirectUrl)
                .build();
    }
}
