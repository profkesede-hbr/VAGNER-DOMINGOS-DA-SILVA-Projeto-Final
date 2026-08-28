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

    @Transactional
    public LoginResponseDTO cadastrarPacienteReal(br.com.docemed.dto.CadastroPacienteRealDTO dto) {
        if (usuarioRepository.existsByLogin(dto.getLogin())) {
            throw new IllegalArgumentException("O login '" + dto.getLogin() + "' já está em uso. Por favor, escolha outro.");
        }

        Paciente paciente = new Paciente();
        paciente.setNome(dto.getNome().trim());
        paciente.setCpf(dto.getCpf() != null ? dto.getCpf().trim() : null);
        paciente.setRg(dto.getRg());
        paciente.setSexo(dto.getSexo() != null ? dto.getSexo() : "Não informado");
        paciente.setEstadoCivil(dto.getEstadoCivil());
        paciente.setProfissao(dto.getProfissao());
        paciente.setIndicadoPor(dto.getIndicadoPor());
        paciente.setTelefone(dto.getTelefone());
        paciente.setCelularWhatsapp(dto.getCelularWhatsapp() != null ? dto.getCelularWhatsapp().trim() : null);
        paciente.setEmail(dto.getEmail() != null ? dto.getEmail().trim() : null);
        paciente.setCep(dto.getCep());
        paciente.setEndereco(dto.getEndereco());
        paciente.setNumero(dto.getNumero());
        paciente.setComplemento(dto.getComplemento());
        paciente.setBairro(dto.getBairro());
        paciente.setCidade(dto.getCidade());
        paciente.setEstado(dto.getEstado());
        paciente.setContatoEmergenciaNome(dto.getContatoEmergenciaNome());
        paciente.setContatoEmergenciaTelefone(dto.getContatoEmergenciaTelefone());
        paciente.setConvenio(dto.getConvenio());
        paciente.setAtivo(true);
        paciente.setDataCadastro(LocalDateTime.now());

        if (dto.getDataNascimento() != null && !dto.getDataNascimento().isBlank()) {
            try {
                paciente.setDataNascimento(LocalDate.parse(dto.getDataNascimento()));
            } catch (Exception ignored) {
                paciente.setDataNascimento(LocalDate.of(1995, 1, 1));
            }
        }

        paciente = pacienteRepository.save(paciente);

        Usuario usuario = Usuario.builder()
                .login(dto.getLogin().trim().toLowerCase())
                .senha(dto.getSenha())
                .nome(dto.getNome().trim())
                .perfil(PerfilUsuario.PACIENTE)
                .pacienteId(paciente.getId())
                .telefoneWhatsapp(dto.getCelularWhatsapp() != null ? dto.getCelularWhatsapp().trim() : null)
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
                .redirectUrl("/paciente/real-portal")
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

        String redirectUrl;
        if (usuario.getPerfil() == PerfilUsuario.MEDICO || usuario.getPerfil() == PerfilUsuario.ADMIN) {
            redirectUrl = "/medico/portal";
        } else if (usuario.getPerfil() == PerfilUsuario.RECEPCAO) {
            redirectUrl = "/recepcao/portal";
        } else {
            if (usuario.getPacienteId() != null) {
                boolean isReal = pacienteRepository.findById(usuario.getPacienteId())
                        .map(p -> p.getCpf() != null && !p.getCpf().equals("000.000.000-00") && !p.getCpf().isBlank())
                        .orElse(false);
                redirectUrl = isReal ? "/paciente/real-portal" : "/paciente/portal";
            } else {
                redirectUrl = "/paciente/portal";
            }
        }

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
